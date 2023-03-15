package com.pedrycz.app1;

import android.os.Bundle;

import com.pedrycz.app1.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


        EditText nameInput = findViewById(R.id.nameInput);
        EditText surnameInput = findViewById(R.id.surnameInput);
        EditText gradeNumberInput = findViewById(R.id.gradeNumberInput);
        Button gradesButton = findViewById(R.id.gradesButton);


        gradesButton.setVisibility(View.GONE);

        nameInput.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean isFocused) {
                        boolean isValid = nameInput.getText().toString().trim().length() > 0;
                        if (!isFocused && !isValid) {
                            Toast toast = Toast.makeText(nameInput.getContext(), "Puste imie!", Toast.LENGTH_SHORT);
                            nameInput.setError("Puste imie!");
                            toast.show();
                        } else if (!isFocused && isValid){
                            nameInput.setError(null);
                        }
                        setButtonVisible(nameInput.getText().toString(),
                                surnameInput.getText().toString(),
                                gradeNumberInput.getText().toString(),
                                gradesButton);
                    }
                }
        );

        surnameInput.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean isFocused) {
                        boolean isValid = surnameInput.getText().toString().trim().length() > 0;
                        if (!isFocused && !isValid) {
                            Toast toast = Toast.makeText(nameInput.getContext(), "Puste nazwisko!", Toast.LENGTH_SHORT);
                            surnameInput.setError("Puste nazwisko!");
                            toast.show();
                        } else if (!isFocused && isValid){
                            surnameInput.setError(null);
                        }
                        setButtonVisible(nameInput.getText().toString(),
                                surnameInput.getText().toString(),
                                gradeNumberInput.getText().toString(),
                                gradesButton);
                    }
                }
        );

        gradeNumberInput.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean isFocused) {
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
                        } else if(!isFocused && isGradeNumberValid){
                            gradeNumberInput.setError(null);
                        }
                        setButtonVisible(nameInput.getText().toString(),
                                surnameInput.getText().toString(),
                                gradeNumberInput.getText().toString(),
                                gradesButton);
                    }
                }
        );
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