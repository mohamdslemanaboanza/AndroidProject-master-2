package team.ourapplication.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;
import team.ourapplication.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView userImage;

    private EditText etFirstName, etLastName, etPhone,
                etEmail, etPassword, etConformPassword;

     private Button btnSignUp;

    private Uri pickedImgUri;

    static int PReqCode = 1;
    static int REQUESCODE = 1;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        userImage = findViewById(R.id.user_image);

        etFirstName = findViewById(R.id.user_firstName);
        etLastName = findViewById(R.id.user_lastName);
        etPhone = findViewById(R.id.user_phone);
        etEmail = findViewById(R.id.user_Register_email);
        etPassword = findViewById(R.id.user_Register_Pass);
        etConformPassword = findViewById(R.id.user_Register_Pass2);

        btnSignUp = findViewById(R.id.btn_register);

        btnSignUp.setOnClickListener(this);
        userImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnSignUp){

            final String FName = etFirstName.getText().toString();
            final String LName = etLastName.getText().toString();
            final String Phone = etPhone.getText().toString();
            final String Email = etEmail.getText().toString();
            final String Password = etPassword.getText().toString();
            final String ConformPass = etConformPassword.getText().toString();

            final String Name = FName +" "+ LName;

            if (FName.isEmpty() || LName.isEmpty() || Phone.isEmpty() || Email.isEmpty() || Password.isEmpty() || ConformPass.isEmpty()){

                if (TextUtils.isEmpty(etFirstName.getText().toString())){

                    etFirstName.setError("مطلوب");
                    return;
                }

                if (TextUtils.isEmpty(etLastName.getText().toString())){

                    etLastName.setError("مطلوب");
                    return;
                }

                if (TextUtils.isEmpty(etPhone.getText().toString())){

                    etPhone.setError("مطلوب");
                    return;
                }

                if (TextUtils.isEmpty(etEmail.getText().toString())){

                    etEmail.setError("مطلوب");
                    return;
                }

                if (TextUtils.isEmpty(etPassword.getText().toString())){

                    etPassword.setError("مطلوب");
                    return;
                }

                if (TextUtils.isEmpty(etConformPassword.getText().toString())){

                    etConformPassword.setError("مطلوب");
                    return;
                }
            }

            else
                createUserAccount(Name, Email, Password);
        }

        else if (v == userImage){

            if (Build.VERSION.SDK_INT >= 22)
                checkAndRequestForPermission();

            else
                openGallery();
        }

    }

    private void openGallery() {

        //TODO: open gallery intent and wait for user to pick an image !

        Intent intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
        intentGallery.setType("image/*");
        startActivityForResult(intentGallery, REQUESCODE);
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
                Toast.makeText(RegisterActivity.this, R.string.please_accept, Toast.LENGTH_SHORT).show();

            else
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
        }

        else
            openGallery();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null){

            pickedImgUri = data.getData();
            userImage.setImageURI(pickedImgUri);
        }
    }

    private void createUserAccount(final String name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(RegisterActivity.this, R.string.Account_Created, Toast.LENGTH_SHORT).show();

                            updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());
                        }

                        else
                            Toast.makeText(RegisterActivity.this, R.string.Account_Creation_Failed + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        //first we need to upload user photo to firebase storage and get url
try {
    StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("user_photos");
    final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());

    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            // image uploaded successfully
            // now we can get our image url

            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    //uri contain user image url

                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .setPhotoUri(uri)
                            .build();

                    currentUser.updateProfile(profileUpdate)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        //user info updated successfully

                                        Toast.makeText(RegisterActivity.this, R.string.Registration_complete, Toast.LENGTH_SHORT).show();

                                        updateUI();
                                    } else
                                        Toast.makeText(RegisterActivity.this, R.string.Registration_Failed, Toast.LENGTH_SHORT).show();

                                }
                            });

                }
            });
        }
    });
}
catch (Exception e ){
    //if the user does not choose a image
    userImage.setImageResource(R.drawable.user);
    Intent goToLoginWithoutSetImager = new Intent(this,LoginActivity.class);
    startActivity(goToLoginWithoutSetImager);
    finish();
}
    }

    private void updateUI() {

        Intent go = new Intent(this, LoginActivity.class);
        startActivity(go);
        finish();
    }


}
