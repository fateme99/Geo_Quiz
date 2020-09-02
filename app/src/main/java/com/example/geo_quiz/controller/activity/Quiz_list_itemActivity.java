package com.example.geo_quiz.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.geo_quiz.controller.activity.SingleFragmentActivity;
import com.example.geo_quiz.controller.fragment.Quiz_List_ItemFragment;

public class Quiz_list_itemActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new Quiz_List_ItemFragment();
    }


}