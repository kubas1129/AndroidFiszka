package com.example.androidfiszka;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FiszkaActivity extends AppCompatActivity {

    Button btnShowAnswer, btnNextQuestion;
    TextView txCategory, txQuestion, txAnswer;
    DatabaseHelper myDB;
    String choosedCategory;
    Cursor data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiszka);

        btnNextQuestion = (Button) findViewById(R.id.nextQuestionButton);
        btnShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        txCategory = (TextView) findViewById(R.id.categoryTextView);
        txQuestion = (TextView) findViewById(R.id.questionTextView);
        txAnswer = (TextView) findViewById(R.id.answerTextView);
        myDB = new DatabaseHelper(this);
        choosedCategory = getIntent().getStringExtra("EXTRA_CATEGORY");
        data = myDB.GetAllFiszkaByCategory(choosedCategory);
        txCategory.setText(choosedCategory);

        RandomQuestion();

        btnShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomQuestion();
            }
        });

    }

    public void RandomQuestion(){
        if(data.getCount() == 0){
            txQuestion.setText("NO QUESTIONS");
            txAnswer.setText("NO ANSWERS");
            btnNextQuestion.setActivated(false);
            btnShowAnswer.setActivated(false);
        }
        else{
            Random r = new Random();
            int pos = r.nextInt(data.getCount());
            data.moveToPosition(pos);
            txQuestion.setText(data.getString(2));
            txAnswer.setText(data.getString(3));
        }
    }

}
