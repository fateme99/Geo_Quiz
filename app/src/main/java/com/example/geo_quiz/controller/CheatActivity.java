package com.example.geo_quiz.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geo_quiz.R;

import java.io.Serializable;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEATED = "com.example.geo_quiz.is_cheated";
    public static final String IS_CHEATED = "is_Cheated";
    public static final String ANSWER_TXT_VIEW = "answer_txtView";
    public TextView mAnswerTextView;
    public Button mCheatButton;
    public CharSequence mCheat_answer="";
    private boolean mAnswer,mIsCheat=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState !=null){
            mIsCheat=savedInstanceState.getBoolean(IS_CHEATED);
            mCheat_answer=(savedInstanceState.getCharSequence(ANSWER_TXT_VIEW));
            if (mIsCheat)
                isShownCheat(true);

        }
        setContentView(R.layout.activity_cheat);
        mAnswer=getIntent().getBooleanExtra(QuizActivity.EXTRA_ANSWER_QUESTION,false);
        findViews();
        setListeners();
        mAnswerTextView.setText(mCheat_answer);
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
        mIsCheat=true;
        Intent intent=new Intent();
        intent.putExtra(EXTRA_IS_CHEATED,ischeated);
        setResult(RESULT_OK,intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_CHEATED,mIsCheat);
        outState.putCharSequence(ANSWER_TXT_VIEW,mAnswerTextView.getText());
    }
}