package com.example.geo_quiz.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geo_quiz.model.Question;
import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

import java.io.Serializable;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.quizFram_container);
        if (fragment==null){
            QuizFragment quizFragment=new QuizFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.quizFram_container,quizFragment)
                    .commit();
        }
    }


}