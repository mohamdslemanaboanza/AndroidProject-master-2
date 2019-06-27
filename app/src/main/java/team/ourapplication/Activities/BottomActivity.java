package team.ourapplication.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import team.ourapplication.Fragments.AboutFragment;
import team.ourapplication.Fragments.AdviceFragment;
import team.ourapplication.Fragments.HomeFragment;
import team.ourapplication.Fragments.LocationFragment;
import team.ourapplication.R;

public class BottomActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                .commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                            .commit();
                    return true;

                case R.id.navigation_location:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LocationFragment())
                            .commit();
                    return true;

                case R.id.navigation_advice:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdviceFragment())
                            .commit();
                    return true;

                case R.id.navigation_about:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.exit_app,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.exit) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.logOut_Title)
                    .setMessage(R.string.logOut)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
 SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(BottomActivity.this);
            SharedPreferences.Editor pen = daftar.edit();
            pen.clear();
            pen.apply();

            Intent i = new Intent(BottomActivity.this, LoginActivity.class);
            startActivity(i);

            finish();
                        }
                    }).setNegativeButton(R.string.no, null).show();


        }
        return super.onOptionsItemSelected(item);

    }

}
