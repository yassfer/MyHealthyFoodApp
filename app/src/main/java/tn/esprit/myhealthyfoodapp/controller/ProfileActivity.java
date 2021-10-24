package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_AGE = "SHARED_PREF_USER_INFO_AGE";
    private static final String SHARED_PREF_USER_INFO_HEIGHT = "SHARED_PREF_USER_INFO_HEIGHT";
    private static final String SHARED_PREF_USER_INFO_WEIGHT = "SHARED_PREF_USER_INFO_WEIGHT";

    private BottomNavigationView bottomNav;
    private TextView profile_name;
    private TextView profile_imc;
    private TextView profile_cal;
    private EditText profile_userName;
    private EditText profile_age;
    private EditText profile_height;
    private EditText profile_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNav = findViewById(R.id.navigation);

        profile_name = findViewById(R.id.profile_name);
        profile_imc = findViewById(R.id.profile_imc);
        profile_cal = findViewById(R.id.profile_calories);
        profile_userName = findViewById(R.id.profile_userName);
        profile_age = findViewById(R.id.profile_age);
        profile_height = findViewById(R.id.profile_height);
        profile_weight = findViewById(R.id.profile_weight);

        this.configureBottomView();
        initilizeProfile();
    }

    private void configureBottomView(){
        bottomNav.setOnNavigationItemSelectedListener(item -> updateActivity(item.getItemId()));
    }
    private Boolean updateActivity(Integer integer){
        Activity activity = null;
        switch (integer) {
            case R.id.navigation_home:
                activity = new HomeActivity();
                break;

            case R.id.navigation_profile:
                activity = new ProfileActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

    private void initilizeProfile() {
        String userName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int age = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_AGE, -1);
        int height = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_HEIGHT, -1);
        int weight = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_WEIGHT, -1);

        if (userName != null) {
            profile_name.setText(userName);
            profile_age.setText(Integer.toString(age));
            profile_height.setText(Integer.toString(height));
            profile_weight.setText(Integer.toString(weight));
            profile_imc.setText(Float.toString(weight/height) );
            profile_cal.setText(Integer.toString(1000));
        }
    }
}