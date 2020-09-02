package com.example.geo_quiz.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.controller.activity.Quiz_list_itemActivity;
import com.example.geo_quiz.model.Question;


public class Quiz_listFragment extends Fragment {
    public static String EXTRA_QUIESTION_ID="com.example.geo_quiz.quiestionId";
    private RecyclerView mRecyclerView_QuizList;

    public Quiz_listFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_quiz_list, container, false);
        findViews(view);
        initViews();
        return view;
    }
    private void findViews(View view){
        mRecyclerView_QuizList=view.findViewById(R.id.recyclerForQuizList);
    }
    private void initViews(){
        mRecyclerView_QuizList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    private class QuizHolder extends RecyclerView.ViewHolder{
        private TextView mTextView_question,mTextView_answer;
        private CheckBox mCheckBox_is_cheated;
        private Question mQuestion;

        public QuizHolder(@NonNull View itemView) {
            super(itemView);
            mTextView_question=itemView.findViewById(R.id.txtView_question);
            mTextView_answer=itemView.findViewById(R.id.txtView_answer);
            mCheckBox_is_cheated=itemView.findViewById(R.id.checkbox_is_cheated);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), Quiz_list_itemActivity.class);
                    intent.putExtra(EXTRA_QUIESTION_ID,mQuestion.getId());
                    startActivity(intent);
                }
            });


        }
        public void bindQuestion(Question question){
            mQuestion=question;
            mTextView_question.setText(mQuestion.getQuestionResId());
            mTextView_answer.setText(mQuestion.isAnswerTrue() ? R.string.btn_true_text : R.string.btn_false_text);
            mCheckBox_is_cheated.setChecked(mQuestion.isCheat());

        }
    }

    
}