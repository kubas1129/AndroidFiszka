package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createCategoryButton;
    private Button createFiszka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCategoryButton = (Button) findViewById(R.id.createCategoryButton);
        createCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateCategory();
            }
        });
        createCategoryButton = (Button) findViewById(R.id.createFiszkaButton);
        createCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateFiszka();
            }
        });
    }

    public void openCreateCategory() {
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivity(intent);
    }

    public void openCreateFiszka() {
        Intent intent = new Intent(this, CreateFiszkaActivity.class);
        startActivity(intent);
    }

}
