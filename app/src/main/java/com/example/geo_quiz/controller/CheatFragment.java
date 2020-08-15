package com.example.geo_quiz.controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

import static android.app.Activity.RESULT_OK;


public class CheatFragment extends Fragment {
    private Setting mSetting = new Setting();
    public static final String EXTRA_IS_CHEATED = "com.example.geo_quiz.is_cheated";
    public static final String IS_CHEATED = "is_Cheated";
    public static final String ANSWER_TXT_VIEW = "answer_txtView";
    public TextView mAnswerTextView, mSureTextView;
    public Button mCheatButton;
    public CharSequence mCheat_answer = "";
    private boolean mAnswer, mIsCheat = false;
    private LinearLayout mRoot_cheat;

    public CheatFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIsCheat = savedInstanceState.getBoolean(IS_CHEATED);
            mCheat_answer = (savedInstanceState.getCharSequence(ANSWER_TXT_VIEW));
            if (mIsCheat)
                isShownCheat(true);

        }
        mAnswer = getActivity().getIntent().getBooleanExtra(QuizFragment.EXTRA_ANSWER_QUESTION, false);
        mSetting = (Setting) getActivity().getIntent().getSerializableExtra(QuizFragment.EXTRA_SETTINGINFO);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cheat, container, false);
        findViews(view);
        setListeners();
        setDefaultValue();
        mAnswerTextView.setText(mCheat_answer);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_CHEATED, mIsCheat);
        outState.putCharSequence(ANSWER_TXT_VIEW, mAnswerTextView.getText());
    }

    public void findViews(View view) {
        mAnswerTextView = view.findViewById(R.id.answer_txtView);
        mCheatButton = view.findViewById(R.id.cheatInCheat_btn);
        mSureTextView = view.findViewById(R.id.sure_textView);
        mRoot_cheat = view.findViewById(R.id.root_layout_cheat);
    }

    public void setListeners() {
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

    public void isShownCheat(boolean ischeated) {
        mIsCheat = true;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEATED, ischeated);
        getActivity().setResult(RESULT_OK, intent);
    }

    public void setSizeOfAll(int size) {
        if (size>0){
            mAnswerTextView.setTextSize(size);
            mCheatButton.setTextSize(size);
            mSureTextView.setTextSize(size);
        }


    }

    public void setBackGroundColor(String name) {
        if (name == null)
            return;
        if (name.equals("lightRed"))
            mRoot_cheat.setBackground(getActivity().getDrawable(R.color.light_red));
        else if (name.equals("lightBlue"))
            mRoot_cheat.setBackground(getActivity().getDrawable(R.color.light_blue));
        else if (name.equals("lightGreen"))
            mRoot_cheat.setBackground(getActivity().getDrawable(R.color.light_green));
        else
            mRoot_cheat.setBackground(getActivity().getDrawable(R.color.white));
    }

    public void setDefaultValue() {

        setSizeOfAll(mSetting.getSize());
        setBackGroundColor(mSetting.getBgColorName());


    }
}