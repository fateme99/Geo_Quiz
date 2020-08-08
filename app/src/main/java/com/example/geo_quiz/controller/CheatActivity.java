package com.example.geo_quiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geo_quiz.R;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEATED = "com.example.geo_quiz.is_cheated";
    public TextView mAnswerTextView;
    public Button mCheatButton;
    private boolean mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswer=getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_QUESTION,false);
        findViews();
        setListeners();
    }
    public void findViews(){
        mAnswerTextView=findViewById(R.id.answer_txtView);
        mCheatButton=findViewById(R.id.cheat_btn);
    }
    public void setListeners(){
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAnswer)
                    mAnswerTextView.setText(R.string.btn_true_text);
                else
                    mAnswerTextView.setText(R.string.btn_false_text);
                isShownCheat(true);
            }
        });
    }
    public void isShownCheat(boolean ischeated){
        Intent intent=new Intent();
        intent.putExtra(EXTRA_IS_CHEATED,ischeated);
        setResult(RESULT_OK,intent);
    }
}