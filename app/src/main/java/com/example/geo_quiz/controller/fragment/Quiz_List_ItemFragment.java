package com.example.geo_quiz.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.geo_quiz.R;

public class Quiz_List_ItemFragment extends Fragment {
    private TextView mTextView_question,mTextView_answer;
    private CheckBox mCheckBox_is_cheated;
    public Quiz_List_ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_quiz__list__item, container, false);
        findViews(view);

        return view;
    }
    private void findViews(View view){
        mTextView_question=view.findViewById(R.id.txtView_question);
        mTextView_answer=view.findViewById(R.id.txtView_answer);
        mCheckBox_is_cheated=view.findViewById(R.id.checkbox_is_cheated);
    }
}