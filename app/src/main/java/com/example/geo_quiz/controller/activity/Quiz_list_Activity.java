package com.example.geo_quiz.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.geo_quiz.R;
import com.example.geo_quiz.controller.activity.SingleFragmentActivity;
import com.example.geo_quiz.controller.fragment.Quiz_listFragment;

public class Quiz_list_Activity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new Quiz_listFragment();
    }

}