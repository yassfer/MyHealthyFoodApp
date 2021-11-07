package tn.esprit.myhealthyfoodapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.RecipesAdapter;
import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.model.Recipe;

public class DietActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Double calories;
    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        calories= Double.valueOf(getIntent().getExtras().getInt("calories"));
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_diet);

        recyclerView = findViewById(R.id.DRrecyclerView);
        bottomNav = findViewById(R.id.navigation);

        loadFromDBToMemory();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerView.Adapter mAdapter = new RecipesAdapter(this, Recipe.recipeListD);

        recyclerView.setAdapter(mAdapter);
        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.DRtoolbar);
        setSupportActionBar(toolbar);
    }

    private void loadFromDBToMemory() {
        Recipe.recipeListD.clear();
        for (Recipe recipe: Recipe.recipeList) {
            if(recipe.getCalories()<= calories){
                Recipe.recipeListD.add(recipe);
            }
        }
        System.out.println(Recipe.recipeListD.size());
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
                activity = new RecepiesActivity();
                break;
            case  R.id.navigation_sport:
                activity = new ListExercisesActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}