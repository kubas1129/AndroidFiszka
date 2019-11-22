package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateCategoryActivity extends AppCompatActivity {

    Button btnSave;
    EditText nameText;
    EditText descText;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);


        btnSave = (Button) findViewById(R.id.SaveCategoryButton);
        nameText = (EditText) findViewById(R.id.categoryName);
        descText = (EditText) findViewById(R.id.categoryDesciption);
        myDB = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.AddCategory(nameText.getText().toString(),descText.getText().toString());
                Back();
            }
        });
    }

    public void Back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
