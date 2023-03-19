package com.pedrycz.app1;

import android.content.Intent;
import android.os.Bundle;

import com.pedrycz.app1.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        Intent gradesActivity = new Intent(MainActivity.this, GradesActivity.class);

        findViewById(R.id.meanScore).setVisibility(View.GONE);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText surnameInput = findViewById(R.id.surnameInput);
        EditText gradeNumberInput = findViewById(R.id.gradeNumberInput);
        Button gradesButton = findViewById(R.id.gradesButton);


        gradesButton.setVisibility(View.GONE);

        nameInput.setOnFocusChangeListener(
                (view, isFocused) -> {
                    boolean isValid = nameInput.getText().toString().trim().length() > 0;
                    if (!isFocused && !isValid) {
                        Toast toast = Toast.makeText(nameInput.getContext(), "Puste imie!", Toast.LENGTH_SHORT);
                        nameInput.setError("Puste imie!");
                        toast.show();
                    } else if (!isFocused){
                        nameInput.setError(null);
                    }
                    setButtonVisible(nameInput.getText().toString(),
                            surnameInput.getText().toString(),
                            gradeNumberInput.getText().toString(),
                            gradesButton);
                }
        );

        surnameInput.setOnFocusChangeListener(
                (view, isFocused) -> {
                    boolean isValid = surnameInput.getText().toString().trim().length() > 0;
                    if (!isFocused && !isValid) {
                        Toast toast = Toast.makeText(nameInput.getContext(), "Puste nazwisko!", Toast.LENGTH_SHORT);
                        surnameInput.setError("Puste nazwisko!");
                        toast.show();
                    } else if (!isFocused){
                        surnameInput.setError(null);
                    }
                    setButtonVisible(nameInput.getText().toString(),
                            surnameInput.getText().toString(),
                            gradeNumberInput.getText().toString(),
                            gradesButton);
                }
        );

        gradeNumberInput.setOnFocusChangeListener(
                (view, isFocused) -> {
                    boolean isGradeNumberValid;

                    String gradeNumber = gradeNumberInput.getText().toString();
                    if (gradeNumber.trim().length() < 1) {
                        isGradeNumberValid = false;
                    } else {
                        int gradeNumberValue = Integer.parseInt(gradeNumber);
                        isGradeNumberValid = gradeNumberValue >= 5 && gradeNumberValue <= 15;
                    }
                    if (!isFocused && !isGradeNumberValid) {
                        Toast toast = Toast.makeText(nameInput.getContext(), "Wartość poza przedziałem!", Toast.LENGTH_SHORT);
                        gradeNumberInput.setError("Wartość poza przedziałem!");
                        toast.show();
                    } else if(!isFocused){
                        gradeNumberInput.setError(null);
                    }
                    setButtonVisible(nameInput.getText().toString(),
                            surnameInput.getText().toString(),
                            gradeNumberInput.getText().toString(),
                            gradesButton);
                }
        );

        gradesButton.setOnClickListener(
                view -> {
                    gradesActivity.putExtra("GRADE_NUM", Integer.parseInt(gradeNumberInput.getText().toString()));
                    startActivityForResult(gradesActivity, 1);
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Float meanScore = (Float) data.getExtras().get("MEAN_SCORE");
            TextView info = findViewById(R.id.meanScore);
            info.setText("Twoja średnia to: " + meanScore);
            info.setVisibility(View.VISIBLE);
            Button gradesButton = findViewById(R.id.gradesButton);
            if (meanScore >= 3) {
                gradesButton.setText(R.string.labelButtonSukces);
            } else {
                gradesButton.setText(R.string.labelButtonPorazka);
            }

            gradesButton.setOnClickListener(view -> {
                if(meanScore >= 3){
                    Toast toast = Toast.makeText(gradesButton.getContext(), "Gratulacje! Otrzymujesz zaliczenie!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    gradesButton.setText(R.string.labelButtonPorazka);
                    Toast toast = Toast.makeText(gradesButton.getContext(), "Wysyłam podanie o zaliczenie warunkowe", Toast.LENGTH_LONG);
                    toast.show();
                }
                finish();
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        EditText nameInput = findViewById(R.id.nameInput);
        EditText surnameInput = findViewById(R.id.surnameInput);
        EditText gradeNumberInput = findViewById(R.id.gradeNumberInput);
        outState.putString("NAME", nameInput.getText().toString());
        outState.putString("SURNAME", surnameInput.getText().toString());
        outState.putString("GRADE_NUM", gradeNumberInput.getText().toString());
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText nameInput = findViewById(R.id.nameInput);
        EditText surnameInput = findViewById(R.id.surnameInput);
        EditText gradeNumberInput = findViewById(R.id.gradeNumberInput);
        nameInput.setText(savedInstanceState.getString("NAME"));
        surnameInput.setText(savedInstanceState.getString("SURNAME"));
        gradeNumberInput.setText(savedInstanceState.getString("GRADE_NUM"));
    }

    private void setButtonVisible(String name, String surname, String gradeNumber, Button button) {
        boolean isFormValid;
        if (name.length() < 1 || surname.length() < 1 || gradeNumber.trim().length() < 1) {
            isFormValid = false;
        } else {
            int gn = Integer.parseInt(gradeNumber);
            isFormValid = name.trim().length() > 0 && surname.trim().length() > 0 && gn >= 5 && gn <= 15;
        }
        if (isFormValid) button.setVisibility(View.VISIBLE);
        else button.setVisibility(View.GONE);
    }
}