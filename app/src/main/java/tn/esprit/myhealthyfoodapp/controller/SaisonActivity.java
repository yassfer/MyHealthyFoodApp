package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import tn.esprit.myhealthyfoodapp.R;
import tn.esprit.myhealthyfoodapp.adapter.SaisonAdapter;
import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.model.Saison;

public class SaisonActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int idSaison;
    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_saison);

        recyclerView = findViewById(R.id.saison_recyclerView);
        bottomNav = findViewById(R.id.navigation);

        deleteBeforLoad();

        MyDatabaseHelper db = new MyDatabaseHelper(SaisonActivity.this);
        db.addSaison("January", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/insign_l2v_janvier2018_fruits_legumes.png");
        db.addSaison("Februry", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/02_fevrier.jpg");
        db.addSaison("March", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/03_mars_0.png");
        db.addSaison("April", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/04_avril_def.png");
        db.addSaison("May", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20170503_fruits_et_legumes_mai_insign_l2v_mai_fruits_legumes-v2.jpg");
        db.addSaison("June", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20170621_insign_l2v_juin_fruits_legumes.png");
        db.addSaison("July", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20170630_fruits_et_legumes_juillet_insign_l2v_aout_fruits_legumes-1.png");
        db.addSaison("August", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20170801_insign_l2v_aout_fruits_legumes.png");
        db.addSaison("September", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20170901_fruits_et_legumes_septembre.png");
        db.addSaison("October", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20171003_insign-l2v-design-fruitsdesaison-octobre-v1-pomme3.png");
        db.addSaison("November", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20171102_insign-l2v-design-fruitsdesaison-novembre.png");
        db.addSaison("December", "https://www.les2vaches.com/sites/l2v/files/wysiwyg/20171201_insign-l2v-design-fruitsdesaison-decembre.png");

        loadFromDBToMemory();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        System.out.println("test 11: "+ Saison.saisonListT);
        RecyclerView.Adapter mAdapter = new SaisonAdapter(this, Saison.saisonListT);

        recyclerView.setAdapter(mAdapter);
        this.configureBottomView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toolbar toolbar = findViewById(R.id.saison_toolbar);
        setSupportActionBar(toolbar);
    }

    private void deleteBeforLoad(){
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.tryDB4();
    }

    private void loadFromDBToMemory() {
        MyDatabaseHelper dbhelper = MyDatabaseHelper.instanceOfDatabase(this);
        dbhelper.getSaisonsListArray();
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