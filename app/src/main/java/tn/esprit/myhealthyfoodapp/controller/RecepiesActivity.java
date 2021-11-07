package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.RecipesAdapter;
import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.model.Recipe;

public class RecepiesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private int idCat;
    private BottomNavigationView bottomNav;
    private RecipesAdapter  adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recepies);

        recyclerView = findViewById(R.id.RrecyclerView);
        bottomNav = findViewById(R.id.navigation);
        idCat = getIntent().getExtras().getInt("ID_CATEGORY");
        deleteBeforLoad();

        MyDatabaseHelper db = new MyDatabaseHelper(RecepiesActivity.this);
        db.addRecipe("Thai Basil Beef", "https://images.themodernproper.com/billowy-turkey/production/posts/2020/Thai-Basil-Beef-11.jpg?w=1200&auto=compress%2Cformat&fit=crop&fp-x=0.5&fp-y=0.5&dm=1606103993&s=d566d278b6f76803ed574ede574ac7c7", 4, 25, 327, 1,"1,2,3","Cook the veggies. In a wok or large skillet over medium high heat add the oil, shallots, garlic, ginger and bell peppers and fry for 3 minutes. Push the veggies off to the side.\n" +
                "Add ground beef.  Turn the heat to high and add the ground beef, breaking it up into small bits with a spoon. (I like to spoon the cooked ground beef into my food processor and pulse it barely a few times to break it into really small pieces).\n" +
                "Make the sauce. In a small bowl stir together the brown sugar, fish sauce, soy sauce, oyster sauce, cornstarch beef broth and water and add to the pan. Cook for 2 minutes.\n" +
                "Add the basil, and stir-fry until wilted.\n" +
                "Serve over hot cooked rice.");
        db.addRecipe("Gingery Ground Beef", "https://images.themodernproper.com/billowy-turkey/production/posts/2020/Ginger-Ground-Beef-9.jpg?w=667&auto=compress%2Cformat&fit=crop&fp-x=0.5&fp-y=0.5&dm=1606105580&s=f28a3dc2e50e46fbd6e686ea9192dfb9", 4, 20, 269, 1,"1","Step 1\n" +
                "Heat a large skillet over medium-high heat. Add beef and cook, stirring and crumbling into small pieces until browned, 5 to 7 minutes. Drain excess grease.\n" +
                "\n" +
                "Step 2\n" +
                "Add garlic, ginger, and sesame oil, stirring until fragrant, about 2 minutes. Stir in soy sauce, brown sugar, and red pepper. Cook until some of the sauce absorbs into the beef, about 7 minutes. Add 1/2 of the chopped green onions.\n" +
                "\n" +
                "Step 3\n" +
                "Serve beef over hot cooked rice; garnished with sesame seeds and remaining green onions.");

        db.addRecipe("Avocado salad", "https://www.kindpng.com/picc/m/110-1105729_meal-png-download-image-food-top-view-png.png", 6, 30, 300, 3, "2","In the bottom of a salad bowl, whisk the lime juice, olive oil, garlic, jalapeno, and salt together. Stir in the herbs. (If making ahead of time, cover and refrigerate up to one day.)\n" +
                "\n" +
                "When ready to serve the salad, cut the avocados in half, remove the pits, and cut into cubes.\n" +
                "\n" +
                "Add the cubed avocado and cucumber to the bowl with the dressing. Gently toss the avocado and cucumber around the bowl so that they are well coated in the dressing. Taste, and then season with additional salt and your favorite hot sauce (optional).");
        db.addRecipe("Broccoli salad", "https://www.fifteenspatulas.com/wp-content/uploads/2014/01/Broccoli-Salad-Fifteen-Spatulas-7-500x427.jpg", 6, 30, 320, 3, "3","test inst");
        db.addRecipe("Chicken salad", "https://www.eatwell101.com/wp-content/uploads/2019/04/Blackened-Chicken-and-Avocado-Salad-recipe-1.jpg", 6, 30, 250, 3, "3,2","test inst");

        loadFromDBToMemory(idCat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerView.Adapter mAdapter = new RecipesAdapter(this, Recipe.recipeListT);

        recyclerView.setAdapter(mAdapter);
        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.Rtoolbar);
        setSupportActionBar(toolbar);
    }

    private void loadFromDBToMemory(int id) {
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.getRecipesListArray();
        Recipe.recipeListT.clear();
        for (Recipe recipe: Recipe.recipeList) {
            if(recipe.getId_category() == id){
                Recipe.recipeListT.add(recipe);
            }
        }
        System.out.println(Recipe.recipeListT.size());
    }

    private void deleteBeforLoad(){
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.tryDB2();
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
            case R.id.navigation_favorite:
                activity = new FavoriteActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        System.out.println("hhhhh1");
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("eeeee");
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/
}