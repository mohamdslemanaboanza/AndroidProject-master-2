package team.ourapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import team.ourapplication.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin;
    TextView tRegister;
    CheckBox chRemember;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.user_email);
        etPassword = findViewById(R.id.user_password);

        btnLogin = findViewById(R.id.btn_login);
        tRegister = findViewById(R.id.text_register);

        chRemember = findViewById(R.id.remember_me);

        btnLogin.setOnClickListener(this);
        tRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v == btnLogin){

            final String mail = etEmail.getText().toString();
            final String pass = etPassword.getText().toString();

            if (mail.isEmpty() || pass.isEmpty()) {

                if (TextUtils.isEmpty(etEmail.getText().toString())) {

                    etEmail.setError("مطلوب");
                    return;
                }
                if (TextUtils.isEmpty(etPassword.getText().toString())) {

                    etPassword.setError("مطلوب");
                    return;
                }
            }
            else
                singIn(mail, pass);


        }
        else if (v == tRegister){

            Intent go = new Intent(this, RegisterActivity.class);
            startActivity(go);
        }

    }

    private void singIn(String mail, String pass) {

        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                SharedPreferences daftar= PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);

                if (task.isSuccessful()) {
                    updateUI();

                    SharedPreferences.Editor pen = daftar.edit();
                    pen.putBoolean("RememberMe", chRemember.isChecked());
                    pen.apply();
                }
                else
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void updateUI() {

        Intent go = new Intent(this, BottomActivity.class);
        startActivity(go);
        finish();
    }
}
