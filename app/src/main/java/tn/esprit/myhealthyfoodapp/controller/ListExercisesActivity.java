package tn.esprit.myhealthyfoodapp.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import tn.esprit.myhealthyfoodapp.adapter.RecyclerViewAdapter;
import tn.esprit.myhealthyfoodapp.Utils.DataInitializer;
import tn.esprit.myhealthyfoodapp.model.Exercise;

import java.util.ArrayList;
import java.util.List;
import tn.esprit.myhealthyfoodapp.R;

public class ListExercisesActivity extends AppCompatActivity {

    private List<Exercise> exerciseList = new ArrayList<>();

    private RecyclerView.LayoutManager layoutManager;

    private RecyclerView recyclerView;

    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        DataInitializer.initData(exerciseList);

        recyclerView = (RecyclerView) findViewById(R.id.list_exercises);

        recyclerViewAdapter = new RecyclerViewAdapter(exerciseList, getBaseContext());

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
