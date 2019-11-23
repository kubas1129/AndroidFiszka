package com.example.androidfiszka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fiszkaDB.db";
    public static final String CATEGORY_TABLE_NAME = "fiszkaCategories";
    public static final String FISZKA_TABLE_NAME = "fiszka";

    public DatabaseHelper(Context context){super(context,DATABASE_NAME,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCategories = "CREATE TABLE " + CATEGORY_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT)";
        db.execSQL(createCategories);
        String createFiszka = "CREATE TABLE " + FISZKA_TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY_NAME TEXT, QUESTION TEXT, ANSWER TEXT)";
        db.execSQL(createFiszka);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CATEGORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FISZKA_TABLE_NAME);
    }

    public boolean AddCategory(String categoryName, String categoryDesc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",categoryName);
        contentValues.put("DESCRIPTION", categoryDesc);

        long result =  db.insert(CATEGORY_TABLE_NAME,null,contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public Cursor GetAllCategories(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT *  FROM "  + CATEGORY_TABLE_NAME,null);
        return data;
    }


    public boolean AddFiszka(String categoryName, String question, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CATEGORY_NAME",categoryName);
        contentValues.put("QUESTION",question);
        contentValues.put("ANSWER", answer);

        long result = db.insert(FISZKA_TABLE_NAME,null,contentValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public Cursor GetAllFiszkaByCategory(String categoryName){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "  + FISZKA_TABLE_NAME + " WHERE CATEGORY_NAME = " + "'" + categoryName +"'",null);
        return data;
    }


}
