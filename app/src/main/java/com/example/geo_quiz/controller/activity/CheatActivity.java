package com.example.geo_quiz.controller.activity;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.geo_quiz.R;
import com.example.geo_quiz.controller.fragment.CheatFragment;

public class CheatActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new CheatFragment();
    }


}