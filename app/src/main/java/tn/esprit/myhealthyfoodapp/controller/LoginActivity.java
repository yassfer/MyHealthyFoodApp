package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tn.esprit.myhealthyfoodapp.R;

public class LoginActivity extends AppCompatActivity {

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_AGE = "SHARED_PREF_USER_INFO_AGE";
    private static final String SHARED_PREF_USER_INFO_HEIGHT = "SHARED_PREF_USER_INFO_HEIGHT";
    private static final String SHARED_PREF_USER_INFO_WEIGHT = "SHARED_PREF_USER_INFO_WEIGHT";

    private TextView mGreetingText;
    private EditText mUsernameText;
    private EditText mAgeText;
    private EditText mHeightText;
    private EditText mWeightText;
    private Button mGoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mGreetingText = findViewById(R.id.main_textview_greeting);
        mUsernameText = findViewById(R.id.name_textview);
        mAgeText = findViewById(R.id.age_textview);
        mHeightText = findViewById(R.id.height_textview);
        mWeightText = findViewById(R.id.weight_textview);
        mGoButton = findViewById(R.id.loginButton);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(SHARED_PREF_USER_INFO_NAME, mUsernameText.getText().toString())
                        .putInt(SHARED_PREF_USER_INFO_AGE, Integer.parseInt(mAgeText.getText().toString()))
                        .putInt(SHARED_PREF_USER_INFO_HEIGHT, Integer.parseInt(mHeightText.getText().toString()))
                        .putInt(SHARED_PREF_USER_INFO_WEIGHT, Integer.parseInt(mWeightText.getText().toString()))
                        .apply();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });
        greetUser();
    }

    private void greetUser() {
        String userName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int age = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_AGE, -1);
        int height = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_HEIGHT, -1);
        int weight = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_WEIGHT, -1);

        if (userName != null) {
            //mGreetingText.setText("Welcome back, {userName} !");
            /*if (score != -1) {
                mGreetingTextView.setText(getString(R.string.welcome_back_with_score, firstName, score));
            } else {
                mGreetingTextView.setText(getString(R.string.welcome_back, firstName));
            }*/
            mUsernameText.setText(userName);
            mAgeText.setText(Integer.toString(age));
            mHeightText.setText(Integer.toString(height));
            mWeightText.setText(Integer.toString(weight));
        }
    }
}