package com.pedrycz.app1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.TextView;

import com.pedrycz.app1.databinding.ActivityGradesBinding;

import java.util.ArrayList;
import java.util.Objects;

public class GradesActivity extends AppCompatActivity {

    private ActivityGradesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGradesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Bundle data = getIntent().getExtras();

        ArrayList<Grade> grades = new ArrayList<>();
        String[] gradeNames = getResources().getStringArray(R.array.gradeTypes);
        for(int i = 0; i < data.getInt("GRADE_NUM"); i++){
            grades.add(new Grade(gradeNames[i], 2));
        }

        GradesAdapter gradesAdapter = new GradesAdapter(this, grades);

        RecyclerView gradeList = findViewById(R.id.gradeList);
        gradeList.setAdapter(gradesAdapter);
        gradeList.setLayoutManager(new LinearLayoutManager(this));



        Button meanButton = findViewById(R.id.meanButton);

        meanButton.setOnClickListener(
                view -> {
                    Intent intent = new Intent();
                    float mean = 0;
                    for(Grade g: grades){
                        mean += g.getValue();
                    }
                    mean /= grades.size();
                    intent.putExtra("MEAN_SCORE", mean);
                    setResult(RESULT_OK, intent);
                    finish();
                }
        );

    }
}