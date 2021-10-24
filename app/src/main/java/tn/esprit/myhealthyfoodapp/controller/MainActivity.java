package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import tn.esprit.myhealthyfoodapp.R;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    private static final long SPLASH_TIMER = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), tn.esprit.myhealthyfoodapp.controller.GetStartedActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_TIMER);




    }



}