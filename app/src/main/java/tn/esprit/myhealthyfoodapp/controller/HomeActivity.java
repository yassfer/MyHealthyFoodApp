package tn.esprit.myhealthyfoodapp.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.CategoriesAdapter;
import tn.esprit.myhealthyfoodapp.model.Categories;
import tn.esprit.myhealthyfoodapp.model.Category;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BottomNavigationView bottomNav;

    Categories categories = generateCategories();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        bottomNav = findViewById(R.id.navigation);

        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        for (int i = 0; i < categories.getCategoryList().size(); i++) {
            DisplayRecipeCards();

        }
    }


    public void DisplayRecipeCards() {
        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        RecyclerView.Adapter mAdapter = new CategoriesAdapter(this, categories.getCategoryList());
        recyclerView.setAdapter(mAdapter);
    }

    private Categories generateCategories() {
        Category category1 = new Category(
                1,
                "Beaf",
                "Beaf",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/roast-beef-recipes-536cd86.jpg?quality=90&resize=440,400"
        );
        Category category2 = new Category(
                2,
                "Chicken",
                "Chicken",
                "https://assets.bonappetit.com/photos/5f32b10ad8a686d03a8e3087/1:1/w_2560%2Cc_limit/Caesar-Salad-Roast-Chicken.jpg"
        );

        Category category3 = new Category(
                3,
                "Salads",
                "Salads",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKohmRwkizxD_Yx1NQzHkWNE6Pn_cgcrGQ8g&usqp=CAU"
        );

        Category category4 = new Category(
                4,
                "Breakfast",
                "Breakfast",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/creamy-oatmeal-bowl-with-banana-blueberries-royalty-free-image-1619015007.?crop=0.806xw:1.00xh;0.0749xw,0&resize=640:*"
        );

        Category category5 = new Category(
                5,
                "Dessert",
                "Dessert",
                "https://i.f1g.fr/media/madame/1900x1900/sites/default/files/img/2016/08/inspirations-photo-20.jpg"
        );

        Category category6 = new Category(
                6,
                "Drinks",
                "Drinks",
                "https://www.myfussyeater.com/wp-content/uploads/2017/06/Healthy-Watermelon-Lemonade_002.jpg"
        );
        return new Categories(Arrays.asList(category1, category2, category3, category4, category5, category6));
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
                activity = new ExtraActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

}