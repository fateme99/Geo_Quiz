package com.example.geo_quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class QuizActivity extends AppCompatActivity {

    public static String mBankString="";
    public static final String CURRENT_INDEX = "mCurrentIndex";
    public static final String SCORE_KEY = "score_key";
    public static final String NUM_OF_ANSWER_KEY = "numOfAnswer_key";
    public static final String BANK_KEY = "bank_key";
    private ImageButton mFalseButton,mTrueButton,mNextBtn,mPrevBtn,mDoubleNext,mDoublePrev,mResetGame;
    private TextView mQuestionTextView,mScoreTextView,mFinalScore;
    private int mCurrentIndex=0 , mScore =0,mNumOfAnswers=0;
    private LinearLayout mMainLayout,mScoreLayour;
    private Question[] mQuestionsBank={
        new Question(R.string.question_tehran,true)
            , new Question(R.string.question_oceans,true)
            , new Question(R.string.question_middle_east,false)
            , new Question(R.string.question_australia,false)
            , new Question(R.string.question_egypt,true)
            , new Question(R.string.question_america,false)
            , new Question(R.string.question_asia,false)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

            mQuestionsBank= (Question[]) savedInstanceState.getSerializable(BANK_KEY);

            /*String[] questions=savedInstanceState.getStringArray(BANK_KEY);
            for (int i = 0; i <questions.length ; i++) {
                JsonParser parser = new JsonParser();
                JsonElement mJson =  parser.parse(questions[i]);
                Gson gson = new Gson();
                Question object = gson.fromJson(mJson, Question.class);
                mQuestionsBank[i]=object;
            }*/

            mScore=savedInstanceState.getInt(SCORE_KEY,0);
            mNumOfAnswers=savedInstanceState.getInt(NUM_OF_ANSWER_KEY,0);
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
        }
        setContentView(R.layout.activity_quiz);

        findViews();
        setListeners();
        setQuestionText(mCurrentIndex);
        setScore();
        theEnd();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX,mCurrentIndex);
        outState.putInt(SCORE_KEY,mScore);
        outState.putInt(NUM_OF_ANSWER_KEY,mNumOfAnswers);
        Serializable serializable=mQuestionsBank;
        outState.putSerializable(BANK_KEY,serializable);
        /*Gson gson=new Gson();
        String[] questions = new String[mQuestionsBank.length];
        for (int i = 0; i <mQuestionsBank.length ; i++) {
            String bankQuestion=gson.toJson(mQuestionsBank);
            questions[i]=bankQuestion;
        }

        outState.putStringArray(BANK_KEY,questions);*/


    }

    public void findViews(){
        mFalseButton=findViewById(R.id.false_btn);
        mTrueButton=findViewById(R.id.true_btn);
        mQuestionTextView=findViewById(R.id.question_text);
        mNextBtn=findViewById(R.id.btn_next);
        mPrevBtn=findViewById(R.id.btn_prev);
        mDoubleNext=findViewById(R.id.btn_double_next);
        mDoublePrev=findViewById(R.id.btn_double_prev);
        mScoreTextView=findViewById(R.id.score_text);
        mMainLayout=findViewById(R.id.main_layout);
        mScoreLayour=findViewById(R.id.score_layout);
        mFinalScore=findViewById(R.id.score_final);
        mResetGame=findViewById(R.id.reset_btn);
    }
    public void setListeners(){
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mQuestionsBank[(mCurrentIndex+mQuestionsBank.length)%mQuestionsBank.length].setMdisable(true);
                    checked(false);


            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionsBank[(mCurrentIndex+mQuestionsBank.length)%mQuestionsBank.length].setMdisable(true);
                mTrueButton.setEnabled(false);
                checked(true);

            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(++mCurrentIndex);
            }
        });
        mPrevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(--mCurrentIndex);
            }
        });

        mDoubleNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length-1);
            }
        });
        mDoublePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length);
            }
        });
        mResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScore=0;
                mCurrentIndex=0;
                mNumOfAnswers=0;
                setQuestionText(mCurrentIndex);
                setScore();
                mMainLayout.setVisibility(View.VISIBLE);
                mScoreLayour.setVisibility(View.GONE);
                for (int i = 0; i <mQuestionsBank.length ; i++) {
                    mQuestionsBank[i].setMdisable(false);
                }
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);

            }
        });
    }
    public void checked(boolean pressedUser){
        mFalseButton.setEnabled(false);
        mTrueButton.setEnabled(false);
        mNumOfAnswers++;
        if (pressedUser==mQuestionsBank[(mCurrentIndex+mQuestionsBank.length)%mQuestionsBank.length].isAnswerTrue()){
            Toast toast=Toast.makeText(this, R.string.tost_true_text, Toast.LENGTH_SHORT);
            toast.getView().setBackground(getDrawable(R.color.true_toast_bg));
            TextView toastTextView=toast.getView().findViewById(android.R.id.message);
            toastTextView.setTextColor(getResources().getColor(R.color.black_color));
            toastTextView.setTextSize(30);
            toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_checked,0);
            toast.show();
            mQuestionTextView.setTextColor(getResources().getColor(R.color.true_toast_bg));
            mScore++;

        }
        else {
            Toast toast=Toast.makeText(this, R.string.tost_false_text, Toast.LENGTH_SHORT);
            toast.getView().setBackground(getDrawable(R.color.false_toast_bg));
            toast.setGravity(Gravity.TOP,0,0);
            TextView toastTextView=toast.getView().findViewById(android.R.id.message);
            toastTextView.setTextColor(getResources().getColor(R.color.black_color));
            toastTextView.setTextSize(30);
            toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_close,0);
            toast.show();
            mQuestionTextView.setTextColor(getResources().getColor(R.color.false_toast_bg));
        }
        theEnd();
    }

    private void theEnd() {
        if (mNumOfAnswers==mQuestionsBank.length){
            mMainLayout.setVisibility(View.GONE);
            mScoreLayour.setVisibility(View.VISIBLE);
            mFinalScore.setText(mQuestionsBank.length+" / "+mScore  +getString(R.string.score_text));
        }
    }

    public void setQuestionText(int index){
        setScore();
        if (mQuestionsBank[(index+mQuestionsBank.length)%mQuestionsBank.length].isMdisable()){

            mQuestionTextView.setText(mQuestionsBank[(index+mQuestionsBank.length)%mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        }
        else {

            mQuestionTextView.setText(mQuestionsBank[(index+mQuestionsBank.length)%mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);


        }


    }
    public void setScore(){
        mScoreTextView.setText( mQuestionsBank.length+" / "+mScore  +getString(R.string.score_text));

    }
}