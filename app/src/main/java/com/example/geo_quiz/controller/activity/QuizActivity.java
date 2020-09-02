package com.example.geo_quiz.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.geo_quiz.controller.fragment.QuizFragment;
import com.example.geo_quiz.R;

public class QuizActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new QuizFragment();
    }



}