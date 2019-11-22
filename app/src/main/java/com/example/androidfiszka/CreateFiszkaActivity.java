package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CreateFiszkaActivity extends AppCompatActivity {


    Button btnSave;
    EditText fiszkaQuestion;
    EditText fiszkaAnswer;
    Spinner spinner;
    DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fiszka);

        spinner = (Spinner) findViewById(R.id.spinnerCategoryList);
        btnSave = (Button) findViewById(R.id.SaveFiszkaButton);
        fiszkaQuestion = (EditText) findViewById(R.id.fiszkaQuestion);
        fiszkaAnswer  = (EditText) findViewById(R.id.fiszkaAnswer);
        myDB = new DatabaseHelper(this);

        UpdateSpinner();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.AddFiszka(spinner.getSelectedItem().toString(),fiszkaQuestion.getText().toString(),fiszkaAnswer.getText().toString());
                Back();
            }
        });
    }

    public void UpdateSpinner(){
        List<String> cateogies = new ArrayList<>();
        Cursor data = myDB.GetAllCategories();

        while(data.moveToNext()) {
            cateogies.add(data.getString(1));
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item ,cateogies);
            spinner.setAdapter(listAdapter);
        }
    }

    public void Back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
