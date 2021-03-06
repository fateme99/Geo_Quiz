package com.example.geo_quiz.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geo_quiz.R;
import com.example.geo_quiz.controller.activity.CheatActivity;
import com.example.geo_quiz.controller.activity.LoginActivity;
import com.example.geo_quiz.controller.activity.SettingActivity;
import com.example.geo_quiz.model.Question;
import com.example.geo_quiz.model.Setting;
import com.example.geo_quiz.model.User;
import com.example.geo_quiz.repository.QuestionRepository;

import java.io.Serializable;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class QuizFragment extends Fragment {

    private User mUser;
    private Setting mSetting=new Setting();
    public static int request_code_cheat = 0;
    public static int request_code_setting = 1;
    public static final String EXTRA_ANSWER_QUESTION = "com.example.geo_quiz.answer_question";
    public static final String EXTRA_SETTINGINFO ="com.example.geo_quiz.settingInfo";
    public static String mBankString = "";
    public static final String CURRENT_INDEX = "mCurrentIndex";
    public static final String SCORE_KEY = "score_key";
    public static final String NUM_OF_ANSWER_KEY = "numOfAnswer_key";
    public static final String BANK_KEY = "bank_key";
    public static final String SETTING_KEY="setting_key";
    private Button mCheatBtn;
    private ImageButton mFalseButton, mTrueButton, mNextBtn, mPrevBtn, mFirstBtn, mLastBtn, mResetGame, mSettingBtn;
    private TextView mQuestionTextView, mScoreTextView, mFinalScore,mUserShow;
    private int mCurrentIndex = 0, mScore = 0, mNumOfAnswers = 0;
    private LinearLayout mMainLayout, mScoreLayour,mRootLayoout;

    private QuestionRepository mQuestionRepository;
    private Question[] mQuestionsBank;


    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionRepository=QuestionRepository.getsInstance();
        mQuestionsBank= mQuestionRepository.getQuestions().toArray(new Question[0]);
        mUser= (User) getActivity().getIntent().getSerializableExtra(LoginActivity.EXTRA_USERINFO);
        UUID uuid = (UUID) getActivity().getIntent().getSerializableExtra(Quiz_listFragment.EXTRA_QUIESTION_ID);
        setCurrentIndex(uuid);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_quiz, container, false);
        if (savedInstanceState != null) {

            mQuestionsBank = (Question[]) savedInstanceState.getSerializable(BANK_KEY);
            mSetting= (Setting) savedInstanceState.getSerializable(SETTING_KEY);

            mScore = savedInstanceState.getInt(SCORE_KEY, 0);
            mNumOfAnswers = savedInstanceState.getInt(NUM_OF_ANSWER_KEY, 0);
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
        }
        findViews(view);

        setListeners();
        setQuestionText(mCurrentIndex);
        setScore();
        setDefaultValue();
        theEnd();
        setUserName();

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null)
            return;

        if (requestCode == request_code_cheat) {
            mQuestionsBank[mCurrentIndex].setCheat(data.getBooleanExtra(CheatFragment.EXTRA_IS_CHEATED, false));
        }
        else if (requestCode==request_code_setting){
            mSetting= (Setting) data.getSerializableExtra(SettingActivity.EXTRA_SETTING_INFO);
            setDefaultValue();



        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX, mCurrentIndex);
        outState.putInt(SCORE_KEY, mScore);
        outState.putInt(NUM_OF_ANSWER_KEY, mNumOfAnswers);
        Serializable serializable = mQuestionsBank;
        outState.putSerializable(BANK_KEY, serializable);
        Serializable setting=mSetting;
        outState.putSerializable(SETTING_KEY,setting);


    }

    public void findViews(View view) {
        mUserShow=view.findViewById(R.id.userNamehello);
        mRootLayoout=view.findViewById(R.id.root_layout_quiz);
        mFalseButton = view.findViewById(R.id.false_btn);
        mTrueButton = view.findViewById(R.id.true_btn);
        mQuestionTextView = view.findViewById(R.id.question_text);
        mNextBtn = view.findViewById(R.id.btn_next);
        mPrevBtn = view.findViewById(R.id.btn_prev);
        mFirstBtn = view.findViewById(R.id.btn_double_next);
        mLastBtn = view.findViewById(R.id.btn_double_prev);
        mScoreTextView = view.findViewById(R.id.score_text);
        mMainLayout = view.findViewById(R.id.main_layout);
        mScoreLayour = view.findViewById(R.id.score_layout);
        mFinalScore = view.findViewById(R.id.score_final);
        mResetGame = view.findViewById(R.id.reset_btn);
        mCheatBtn = view.findViewById(R.id.cheat_btn);
        mSettingBtn = view.findViewById(R.id.setting_btn);
    }

    public void setListeners() {
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].setMdisable(true);
                checked(false);


            }
        });
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].setMdisable(true);
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

        mFirstBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length - 1);
            }
        });
        mLastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setQuestionText(mQuestionsBank.length);
            }
        });
        mResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScore = 0;
                mCurrentIndex = 0;
                mNumOfAnswers = 0;
                setQuestionText(mCurrentIndex);
                setScore();
                mMainLayout.setVisibility(View.VISIBLE);
                mScoreLayour.setVisibility(View.GONE);
                for (int i = 0; i < mQuestionsBank.length; i++) {
                    mQuestionsBank[i].setMdisable(false);
                }
                mTrueButton.setEnabled(true);
                mFalseButton.setEnabled(true);

            }
        });
        mCheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheatActivity.class);
                intent.putExtra(EXTRA_ANSWER_QUESTION, mQuestionsBank[mCurrentIndex].isAnswerTrue());
                intent.putExtra(EXTRA_SETTINGINFO,mSetting);
                startActivityForResult(intent, request_code_cheat);

            }
        });
        mSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra(EXTRA_SETTINGINFO,mSetting);
                intent.putExtra(LoginActivity.EXTRA_USERINFO,mUser);
                startActivityForResult(intent, request_code_setting);
            }
        });
    }

    public void checked(boolean pressedUser) {
        mFalseButton.setEnabled(false);
        mTrueButton.setEnabled(false);
        mNumOfAnswers++;
        if (mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].isCheat()) {
            Toast.makeText(getActivity(), R.string.judgment_txt, Toast.LENGTH_SHORT).show();
        } else {
            if (pressedUser == mQuestionsBank[(mCurrentIndex + mQuestionsBank.length) % mQuestionsBank.length].isAnswerTrue()) {
                Toast toast = Toast.makeText(getActivity(), R.string.tost_true_text, Toast.LENGTH_SHORT);
                toast.getView().setBackground(getActivity().getDrawable(R.color.true_toast_bg));
                TextView toastTextView = toast.getView().findViewById(android.R.id.message);
                toastTextView.setTextColor(getResources().getColor(R.color.black_color));
                toastTextView.setTextSize(30);
                toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_checked, 0);
                toast.show();
                mQuestionTextView.setTextColor(getResources().getColor(R.color.true_toast_bg));
                mScore++;

            } else {
                Toast toast = Toast.makeText(getActivity(), R.string.tost_false_text, Toast.LENGTH_SHORT);
                toast.getView().setBackground(getActivity().getDrawable(R.color.false_toast_bg));
                toast.setGravity(Gravity.TOP, 0, 0);
                TextView toastTextView = toast.getView().findViewById(android.R.id.message);
                toastTextView.setTextColor(getResources().getColor(R.color.black_color));
                toastTextView.setTextSize(30);
                toastTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0);
                toast.show();
                mQuestionTextView.setTextColor(getResources().getColor(R.color.false_toast_bg));
                if (mSetting.isNegAnswer() && mScore>0)
                    mScore--;
            }
        }

        theEnd();
    }

    private void theEnd() {
        if (mNumOfAnswers == mQuestionsBank.length) {
            mMainLayout.setVisibility(View.GONE);
            mScoreLayour.setVisibility(View.VISIBLE);
            mFinalScore.setText(mQuestionsBank.length + " / " + mScore + getString(R.string.score_text));
        }
    }

    public void setQuestionText(int index) {
        setScore();
        if (mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].isMdisable()) {

            mQuestionTextView.setText(mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(false);
            mTrueButton.setEnabled(false);
        } else {

            mQuestionTextView.setText(mQuestionsBank[(index + mQuestionsBank.length) % mQuestionsBank.length].getQuestionResId());
            mQuestionTextView.setTextColor(getResources().getColor(R.color.black_color));
            mFalseButton.setEnabled(true);
            mTrueButton.setEnabled(true);


        }


    }

    public void setScore() {
        mScoreTextView.setText(mQuestionsBank.length + " / " + mScore + getString(R.string.score_text));

    }
    public  void setSizeOfAll(int size){
        if (size==0)
            return;
        mQuestionTextView.setTextSize(size);
        mCheatBtn.setTextSize(size);
        mScoreTextView.setTextSize(size);

    }
    public void setBackGroundColor(String name){
        if (name==null)
            return;
        if (name.equals("lightRed"))
            mRootLayoout.setBackground(getActivity().getDrawable(R.color.light_red));
        else if (name.equals("lightBlue"))
            mRootLayoout.setBackground(getActivity().getDrawable(R.color.light_blue));
        else if (name.equals("lightGreen"))
            mRootLayoout.setBackground(getActivity().getDrawable(R.color.light_green));
        else
            mRootLayoout.setBackground(getActivity().getDrawable(R.color.white));
    }
    public void setHides(){
        if (mSetting.isHide_true())
            mTrueButton.setVisibility(View.GONE);
        else
            mFalseButton.setVisibility(View.VISIBLE);
        if (mSetting.isHide_False())
            mFalseButton.setVisibility(View.GONE);
        else
            mFalseButton.setVisibility(View.VISIBLE);
        if (mSetting.isHide_cheat())
            mCheatBtn.setVisibility(View.GONE);
        else
            mCheatBtn.setVisibility(View.VISIBLE);
        if (mSetting.isHide_next())
            mNextBtn.setVisibility(View.GONE);
        else
            mNextBtn.setVisibility(View.VISIBLE);
        if (mSetting.isHide_prev())
            mPrevBtn.setVisibility(View.GONE);
        else
            mPrevBtn.setVisibility(View.VISIBLE);
        if (mSetting.isHide_first())
            mFirstBtn.setVisibility(View.GONE);
        else
            mFirstBtn.setVisibility(View.VISIBLE);
        if (mSetting.isHide_last())
            mLastBtn.setVisibility(View.GONE);
        else mLastBtn.setVisibility(View.VISIBLE);
    }

    public void setDefaultValue(){
        setBackGroundColor(mSetting.getBgColorName());
        setSizeOfAll(mSetting.getSize());
        setHides();
    }
    public void setUserName(){
        if (mUser==null)
            return;
        else {
            mUserShow.setText(mUser.getUserName());
        }
    }
    private void setCurrentIndex(UUID uuid){
        for (int i = 0; i <mQuestionsBank.length ; i++) {
            if (mQuestionsBank[i].getId().equals(uuid)){
                mCurrentIndex=i;
            }
        }
    }
}