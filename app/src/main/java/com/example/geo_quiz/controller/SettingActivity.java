package com.example.geo_quiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

public class SettingActivity extends AppCompatActivity {

    public static final String EXTRA_SETTING_INFO = "com.example.geo_quiz.settingInfo";
    private RadioButton mSmallRadioBtn,mMeduimRadioBtn,mLargeRadioBtn,mLightRedBtn,mLightBlueBtn
            ,mLightGreenBtn,mWhiteBtn;
    private CheckBox mHide_true,mHide_false,mHide_cheat,mHide_next,mHide_prev,mHide_first,mHide_last;
    private TextView mQuestionTextView;
    private Setting mSetting;
    private Button mSaveBtn,mDiscardBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViews();
        setListeners();
        mSetting=new Setting();
        mSetting= (Setting) getIntent().getSerializableExtra(QuizActivity.EXTRA_SETTINGINFO);
        setDefaultValue();
    }
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.hide_trueBtn:
                if (checked)
                    mSetting.setHide_true(true);
                else
                    mSetting.setHide_true(false);
                break;
            case R.id.hide_falseBtn:
                if (checked)
                    mSetting.setHide_False(true);
                else
                    mSetting.setHide_False(false);
                break;
            case R.id.hide_cheatBtn:
                if (checked)
                    mSetting.setHide_cheat(true);
                else
                    mSetting.setHide_cheat(false);
                break;
            case R.id.hide_nextBtn:
                if (checked)
                    mSetting.setHide_next(true);
                else
                    mSetting.setHide_next(false);
                break;
            case R.id.hide_prevBtn:
                if (checked)
                    mSetting.setHide_prev(true);
                else
                    mSetting.setHide_prev(false);
                break;
            case R.id.hide_firstBtn:
                if (checked)
                    mSetting.setHide_first(true);
                else
                    mSetting.setHide_first(false);
                break;
            case R.id.hide_lastBtn:
                if (checked)
                    mSetting.setHide_last(true);
                else
                    mSetting.setHide_last(false);

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
        mDiscardBtn=findViewById(R.id.setting_discard_btn);
        mHide_true=findViewById(R.id.hide_trueBtn);
        mHide_false=findViewById(R.id.hide_falseBtn);
        mHide_cheat=findViewById(R.id.hide_cheatBtn);
        mHide_next=findViewById(R.id.hide_nextBtn);
        mHide_prev=findViewById(R.id.hide_prevBtn);
        mHide_first=findViewById(R.id.hide_firstBtn);
        mHide_last=findViewById(R.id.hide_lastBtn);
    }
    public void setListeners(){
        mDiscardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
                mSetting.setBgColorName("lightRed");
            }
        });
        mLightBlueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("lightBlue");
            }
        });
        mLightGreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("lightGreen");
            }
        });
        mWhiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSetting.setBgColorName("white");
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
    public void setDefaultValue(){
        if (mSetting==null)
            return;
        int size=mSetting.getSize();
        switch (size){
            case 14:
                mSmallRadioBtn.setChecked(true);
                break;
            case 18:
                mMeduimRadioBtn.setChecked(true);
                break;
            case 26:
                mLargeRadioBtn.setChecked(true);
                break;
        }
        String color=mSetting.getBgColorName();
        if (color!=null){
            switch (color){
                case "lightRed":
                    mLightRedBtn.setChecked(true);
                    break;
                case "lightBlue":
                    mLightBlueBtn.setChecked(true);
                    break;
                case "lightGreen":
                    mLightGreenBtn.setChecked(true);
                    break;
                case "white":
                    mWhiteBtn.setChecked(true);
                    break;

            }
        }

        if (mSetting.isHide_true())
            mHide_true.setChecked(true);
        if (mSetting.isHide_False())
            mHide_false.setChecked(true);
        if (mSetting.isHide_cheat())
            mHide_cheat.setChecked(true);
        if (mSetting.isHide_next())
            mHide_next.setChecked(true);
        if (mSetting.isHide_prev())
            mHide_prev.setChecked(true);
        if (mSetting.isHide_first())
            mHide_first.setChecked(true);
        if (mSetting.isHide_last())
            mHide_last.setChecked(true);

    }
    public void setSizeOfAll(int size){

    }
}