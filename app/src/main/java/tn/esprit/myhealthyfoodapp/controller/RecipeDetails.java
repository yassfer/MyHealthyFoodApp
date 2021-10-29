package tn.esprit.myhealthyfoodapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.model.Ingredient;


public class RecipeDetails extends AppCompatActivity {

    private TextView title, ready_in, servings, healthy, instructions;
    private ImageView img, vegetarian;
    private final List<Ingredient> ingredientsLst = new ArrayList<Ingredient>();
    private RecyclerView ingredientsRecyclerView;
    private String recipeId;
    private final String TAG = "RecipeDetails";
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int idRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        final Intent intent = getIntent();
        title = findViewById(R.id.recipeTitleD);
        String cs = "Bonjour !!";
        //recipeId = (String) Objects.requireNonNull(intent.getExtras()).getString("id");
        //System.out.println((String) Objects.requireNonNull(intent.getExtras()).getString("title"));
        //CharSequence cs = intent.getExtras().getString("title");
        title.setText(cs);
        //System.out.println((String) Objects.requireNonNull(intent.getExtras()).getString("img"));
        //fetchAndDisplayRecipeInfo(recipeId);

        idRecipe= getIntent().getExtras().getInt("ID_RECIPE");
        System.out.println("id recipe:: "+idRecipe);
    }

    @Override
    protected void onStart() {
        super.onStart();
        img = (ImageView) findViewById(R.id.recipeImage);
        title = (TextView) findViewById(R.id.recipeTitleD);
        ready_in = (TextView) findViewById(R.id.recipe_ready_in_min);
        servings = (TextView) findViewById(R.id.num_of_serving);
        vegetarian = (ImageView) findViewById(R.id.recipe_vegetarian);
        instructions = (TextView) findViewById(R.id.recipe_instructions);
        healthy = (TextView) findViewById(R.id.is_recipe_healthy);
    }

    private void fetchAndDisplayRecipeInfo(final String recipeId) {




    }
}
