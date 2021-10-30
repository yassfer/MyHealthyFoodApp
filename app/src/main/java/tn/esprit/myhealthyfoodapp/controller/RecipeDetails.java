package tn.esprit.myhealthyfoodapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.RecyclerViewAdapterForIngredients;
import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.model.Ingredient;
import tn.esprit.myhealthyfoodapp.model.Recipe;
import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT;
import static androidx.core.text.HtmlCompat.fromHtml;




public class RecipeDetails extends AppCompatActivity {

    private TextView title, ready_in, servings, healthy, instructions;
    private ImageView img, vegetarian;
    private final List<Ingredient> ingredientsLst = new ArrayList<Ingredient>();
    private RecyclerView ingredientsRecyclerView;
    private String recipeId;
    private final String TAG = "RecipeDetails";
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList ingredientsArr;
    private BottomNavigationView bottomNav;

    private int idRecipe;
    MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        bottomNav = findViewById(R.id.navigation);

        this.configureBottomView();

        final Intent intent = getIntent();
        title = findViewById(R.id.recipeTitleD);
        img = (ImageView) findViewById(R.id.recipeImage);
        ready_in = (TextView) findViewById(R.id.recipe_ready_in_min);
        servings = (TextView) findViewById(R.id.num_of_serving);
        instructions = (TextView) findViewById(R.id.recipe_instructions);
        healthy = (TextView) findViewById(R.id.is_recipe_healthy);
        idRecipe= getIntent().getExtras().getInt("ID_RECIPE");
        dbhelper.tryDB3();
        dbhelper.addIngredient("Oil","https://www.pinclipart.com/picdir/big/168-1689881_olive-pomace-oil-png-png-images-jug-of.png");
        dbhelper.addIngredient("Letus","https://w7.pngwing.com/pngs/385/581/png-transparent-green-vegetable-butterhead-lettuce-iceberg-lettuce-caesar-salad-lettuce-green-leafy-vegetables-leaf-vegetable-food-leaf.png");
        dbhelper.addIngredient("Salmon","https://img.favpng.com/19/20/11/smoked-salmon-lox-caviar-food-png-favpng-ike96tQG34quCtm79rJpWi6VZ.jpg");

        fetchAndDisplayRecipeInfo(idRecipe);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.Rtoolbar);
        setSupportActionBar(toolbar);

    }

    private void fetchAndDisplayRecipeInfo(final int recipeId) {


        Recipe recipe= dbhelper.getRecipeById(recipeId);
        title.setText(recipe.getRecipe_title());
        ready_in.setText(Integer.toString(recipe.getReady_in_mins()));
        servings.setText(Integer.toString(recipe.getNum_servings())+" persons");
        try {
            Picasso.get().load(recipe.getRecipe_image()).into(img);
        } catch (Exception e) {
            img.setImageResource(R.drawable.nopicture);
        }
        healthy.setText(Float.toString(recipe.getCalories())+" calories");
        instructions.setText(recipe.getInstructions());

        System.out.println((dbhelper.getIngredientById(1)).getName());
        System.out.println((dbhelper.getIngredientById(2)).getName());
        System.out.println((dbhelper.getIngredientById(3)).getName());
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(recipe.getIngredients().split(",")));
         for (int i = 0; i < myList.size(); i++) {
             dbhelper.getIngredientById(Integer.valueOf(myList.get(i)));
             ingredientsLst.add(dbhelper.getIngredientById(Integer.valueOf(myList.get(i))));
        }

        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recipe_ingredients_rv);
        ingredientsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new RecyclerViewAdapterForIngredients(getApplicationContext(), ingredientsLst);

        ingredientsRecyclerView.setLayoutManager(mLayoutManager);
        ingredientsRecyclerView.setAdapter(mAdapter);
        ingredientsRecyclerView.setAdapter(mAdapter);


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
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }
    }

