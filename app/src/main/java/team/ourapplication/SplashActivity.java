package team.ourapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import team.ourapplication.Activities.BottomActivity;
import team.ourapplication.Activities.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    final int TIME_TO_WAIT = 1000;
    private Boolean remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences dafter = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        remember = dafter.getBoolean("RememberMe", false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (remember){

                    Intent go=new Intent(SplashActivity.this, BottomActivity.class);
                    startActivity(go);
                    finish();
                }
                else {

                    Intent i=new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            }

        },TIME_TO_WAIT);

    }
}
