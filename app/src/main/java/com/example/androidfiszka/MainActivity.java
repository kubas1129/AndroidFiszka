package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        UpdateAllCategories();

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CLICK CATEGORY", Toast.LENGTH_LONG).show();
                openCreateCategory();
            }
        });

        btnAddFiszka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "CLICK FISZKA", Toast.LENGTH_LONG).show();
                openCreateFiszka();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = parent.getItemAtPosition(position).toString();
                openFiszka(category);
            }
        });
    }


    public void openFiszka(String category){
        Intent intent = new Intent(this, FiszkaActivity.class);
        intent.putExtra("EXTRA_CATEGORY",category);
        startActivity(intent);
    }

    public void openCreateFiszka() {
        Intent intent = new Intent(this, CreateFiszkaActivity.class);
        startActivity(intent);
    }

    public void openCreateCategory() {
        Intent intent = new Intent(this, CreateCategoryActivity.class);
        startActivity(intent);
    }

    public void UpdateAllCategories(){
        List<String> cateogies = new ArrayList<>();
        Cursor data = myDB.GetAllCategories();


        if(data.getCount() == 0){
            List<String> test = new ArrayList<>();
            test.add("No record here");
            ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,test);
            listView.setAdapter(listAdapter);
        }
        else{
            while(data.moveToNext()){
                cateogies.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 ,cateogies);
                listView.setAdapter(listAdapter);
            }
        }

    }

}
