package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;

public class ProfilActivity extends AppCompatActivity {

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
    private Button updateBtn;
    private Button dietBtn;
    private int calories = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_profil);

        bottomNav = findViewById(R.id.navigation);
        profile_name = findViewById(R.id.profile_name);
        profile_imc = findViewById(R.id.profile_imc);
        profile_cal = findViewById(R.id.profile_calories);
        profile_userName = findViewById(R.id.profile_userName);
        profile_age = findViewById(R.id.profile_age);
        profile_height = findViewById(R.id.profile_height);
        profile_weight = findViewById(R.id.profile_weight);
        updateBtn = findViewById(R.id.update_profile_btn);
        dietBtn = findViewById(R.id.diet_btn);
        initilizeProfile();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                        .edit()
                        .putString(SHARED_PREF_USER_INFO_NAME, profile_userName.getText().toString())
                        .putInt(SHARED_PREF_USER_INFO_AGE, Integer.parseInt(profile_age.getText().toString()))
                        .putInt(SHARED_PREF_USER_INFO_HEIGHT, Integer.parseInt(profile_height.getText().toString()))
                        .putInt(SHARED_PREF_USER_INFO_WEIGHT, Integer.parseInt(profile_weight.getText().toString()))
                        .apply();
                Intent i = new Intent(ProfilActivity.this, ProfilActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        dietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(v.getContext(), DietActivity.class);
                in.putExtra("calories", calories);
                v.getContext().startActivity(in);
            }
        });

        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initilizeProfile() {
        String userName = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int age = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_AGE, -1);
        int height = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_HEIGHT, -1);
        int weight = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_WEIGHT, -1);

        if (userName != null) {
            System.out.println("naaaame:: "+userName);
            profile_name.setText(userName);
            profile_userName.setText(userName);
            profile_age.setText(Integer.toString(age));
            profile_height.setText(Integer.toString(height));
            profile_weight.setText(Integer.toString(weight));
            System.out.println("weight/height=> "+weight/height);
            profile_imc.setText(Float.toString(weight/height) );
            profile_cal.setText(Integer.toString(calories));
        }

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
                activity = new ProfilActivity();
                break;
            case  R.id.navigation_notifications:
                activity = new ExtraActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}