package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.CategoriesAdapter;
import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.model.Category;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        bottomNav = findViewById(R.id.navigation);

        deleteBeforLoad();
        MyDatabaseHelper db = new MyDatabaseHelper(HomeActivity.this);
        db.addCategory(
                "Beaf",
                "Beaf",
                "https://images.immediate.co.uk/production/volatile/sites/30/2020/08/roast-beef-recipes-536cd86.jpg?quality=90&resize=440,400"
        );
        db.addCategory(
                "Chicken",
                "Chicken",
                "https://assets.bonappetit.com/photos/5f32b10ad8a686d03a8e3087/1:1/w_2560%2Cc_limit/Caesar-Salad-Roast-Chicken.jpg"
        );
        db.addCategory(
                "Salads",
                "Salads",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKohmRwkizxD_Yx1NQzHkWNE6Pn_cgcrGQ8g&usqp=CAU"
        );
        db.addCategory(
                "Breakfast",
                "Breakfast",
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/creamy-oatmeal-bowl-with-banana-blueberries-royalty-free-image-1619015007.?crop=0.806xw:1.00xh;0.0749xw,0&resize=640:*"
        );
        db.addCategory(
                "Dessert",
                "Dessert",
                "https://i.f1g.fr/media/madame/1900x1900/sites/default/files/img/2016/08/inspirations-photo-20.jpg"
        );

        db.addCategory(
                "Drinks",
                "Drinks",
                "https://www.myfussyeater.com/wp-content/uploads/2017/06/Healthy-Watermelon-Lemonade_002.jpg"
        );
        loadFromDBToMemory();

        recyclerView.setHasFixedSize(true);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        RecyclerView.Adapter mAdapter = new CategoriesAdapter(this, Category.categoryList);
        recyclerView.setAdapter(mAdapter);

        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void loadFromDBToMemory() {
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.getCategoriesListArray();
    }

    private void deleteBeforLoad(){
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.tryDB1();
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
            case R.id.navigation_favorite:
                activity = new FavoriteActivity();
                break;
            case  R.id.navigation_sport:
                activity = new ListExercisesActivity();
                break;
        }
        startActivity(new Intent(this, activity.getClass()));
        return true;
    }

}