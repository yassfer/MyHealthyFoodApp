package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.RecipesAdapter;
import tn.esprit.myhealthyfoodapp.model.Recipe;
import tn.esprit.myhealthyfoodapp.model.Recipes;

public class RecepiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    Recipes recipes = generateRecipes();

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepies);

        recyclerView = findViewById(R.id.RrecyclerView);

        bottomNav = findViewById(R.id.navigation);

        this.configureBottomView();
        //recyclerView.setAdapter(new CustomRecyclerViewAdapter(this, recipes.getRecepies()));

        // RecyclerView scroll vertical
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.Rtoolbar);
        setSupportActionBar(toolbar);
        for (int i = 0; i < recipes.getRecepies().size(); i++) {
            DisplayRecipeCards();
        }
    }


    public void DisplayRecipeCards() {
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerView.Adapter mAdapter = new RecipesAdapter(this, recipes.getRecepies());

        recyclerView.setAdapter(mAdapter);
    }
    private Recipes generateRecipes(){
        Recipe recipe1= new Recipe("1", "Avocado salad", "https://www.kindpng.com/picc/m/110-1105729_meal-png-download-image-food-top-view-png.png", 6, 30, 50, 50, 3);
        Recipe recipe2= new Recipe("2", "Broccoli salad", "https://www.fifteenspatulas.com/wp-content/uploads/2014/01/Broccoli-Salad-Fifteen-Spatulas-7-500x427.jpg", 6, 30, 50, 50,3);
        Recipe recipe3= new Recipe("3", "Chicken salad", "https://www.eatwell101.com/wp-content/uploads/2019/04/Blackened-Chicken-and-Avocado-Salad-recipe-1.jpg", 6, 30, 50, 50,3);

        return new Recipes(Arrays.asList(recipe1, recipe2, recipe3));
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
            case  R.id.navigation_notifications:
                activity = new RecepiesActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
}