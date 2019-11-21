package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper myDB;
    Button btnAddCategory, btnAddFiszka;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddCategory = (Button) findViewById(R.id.createCategoryButton);
        btnAddFiszka = (Button) findViewById(R.id.createFiszkaButton);
        listView = (ListView) findViewById(R.id.categoryList);
        myDB = new DatabaseHelper(this);

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openCreateCategory(); -> na razie skomentowane, to na dole przeniesc do nowych activity!

                ArrayList<String> cateogies = new ArrayList<>();
                Cursor data = myDB.GetAllCategories();

                if (data.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "EMPTY DB", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "NOT EMPTY DB", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnAddFiszka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CLICK FISZKA", Toast.LENGTH_LONG).show();
                openCreateFiszka();
            }
        });

    }

    public void openCreateFiszka() {
        Intent intent = new Intent(this, CreateFiszkaActivity.class);
        startActivity(intent);
    }

    public void openCreateCategory() {
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivity(intent);
    }

}
