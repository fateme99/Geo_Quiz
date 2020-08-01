package com.example.geo_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private Button mFalseButton;
    private Button mTrueButton;
    private TextView mQuestionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        findViews();
        setListeners();
    }
    public void findViews(){
        mFalseButton=findViewById(R.id.false_btn);
        mTrueButton=findViewById(R.id.true_btn);
        mQuestionTextView=findViewById(R.id.question_text);
    }
    public void setListeners(){
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}