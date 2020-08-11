package com.example.geo_quiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

import java.io.Serializable;

public class SettingActivity extends AppCompatActivity {

    public static final String EXTRA_SETTING_INFO = "com.example.geo_quiz.settingInfo";
    private RadioButton mSmallRadioBtn,mMeduimRadioBtn,mLargeRadioBtn;
    private TextView mQuestionTextView;
    private Setting mSetting;
    private Button mSaveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        setListeners();
        mSetting=new Setting();
    }
    public void findViews(){
        mLargeRadioBtn=findViewById(R.id.larg_radioBtn);
        mSmallRadioBtn=findViewById(R.id.small_radioBtn);
        mMeduimRadioBtn=findViewById(R.id.meduim_radioBtn);
        mQuestionTextView=findViewById(R.id.question_text);
        mSaveBtn=findViewById(R.id.setting_save_btn);
    }
    public void setListeners(){
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Serializable serializable=mSetting;
                intent.putExtra(EXTRA_SETTING_INFO,serializable);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        mLargeRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setSize(26);
            }
        });
        mSmallRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setSize(14);
            }
        });
        mMeduimRadioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setSize(18);
            }
        });
    }
}