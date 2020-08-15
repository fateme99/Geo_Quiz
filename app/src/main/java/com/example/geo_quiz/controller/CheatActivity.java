package com.example.geo_quiz.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Setting;

import java.io.Serializable;

public class CheatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cheat);
        FragmentManager fragmentManager=getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.cheatFram_container);
        if (fragment==null){
            CheatFragment cheatFragment=new CheatFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.cheatFram_container,cheatFragment)
                    .commit();
        }


    }

}