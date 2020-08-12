package com.example.geo_quiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

import java.io.Serializable;

public class SettingActivity extends AppCompatActivity {

    public static final String EXTRA_SETTING_INFO = "com.example.geo_quiz.settingInfo";
    private RadioButton mSmallRadioBtn,mMeduimRadioBtn,mLargeRadioBtn,mLightRedBtn,mLightBlueBtn
            ,mLightGreenBtn,mWhiteBtn;
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
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.hide_trueBtn:
                if (checked)
                    mSetting.setMHide_true(true);
                break;
            case R.id.hide_falseBtn:
                if (checked)
                    mSetting.setHide_False(true);
                break;
            case R.id.hide_cheatBtn:
                if (checked)
                    mSetting.setHide_cheat(true);
                break;
            case R.id.hide_nextBtn:
                if (checked)
                    mSetting.setHide_next(true);
                break;
            case R.id.hide_prevBtn:
                if (checked)
                    mSetting.setHide_prev(true);
                break;
            case R.id.hide_firstBtn:
                if (checked)
                    mSetting.setHide_first(true);
                break;
            case R.id.hide_lastBtn:
                if (checked)
                    mSetting.setHide_last(true);





        }
    }
    public void findViews(){
        mLargeRadioBtn=findViewById(R.id.larg_radioBtn);
        mSmallRadioBtn=findViewById(R.id.small_radioBtn);
        mMeduimRadioBtn=findViewById(R.id.meduim_radioBtn);
        mQuestionTextView=findViewById(R.id.question_text);
        mSaveBtn=findViewById(R.id.setting_save_btn);
        mLightBlueBtn=findViewById(R.id.lightBlue_id);
        mLightGreenBtn=findViewById(R.id.lightGreen_id);
        mLightRedBtn=findViewById(R.id.lightRed_id);
        mWhiteBtn=findViewById(R.id.white_id);
    }
    public void setListeners(){
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra(EXTRA_SETTING_INFO,mSetting);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        mLightRedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("lightRed_id");
            }
        });
        mLightBlueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("lightBlue_id");
            }
        });
        mLightGreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("lightGreen_id");
            }
        });
        mWhiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("white_id");
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