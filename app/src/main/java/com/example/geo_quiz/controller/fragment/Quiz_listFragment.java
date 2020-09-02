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
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.geo_quiz.R;
import com.example.geo_quiz.controller.activity.QuizActivity;
import com.example.geo_quiz.controller.activity.Quiz_list_itemActivity;
import com.example.geo_quiz.model.Question;
import com.example.geo_quiz.repository.QuestionRepository;

import java.util.List;


public class Quiz_listFragment extends Fragment {
    public static String EXTRA_QUIESTION_ID="com.example.geo_quiz.quiestionId";
    private RecyclerView mRecyclerView_QuizList;
    private QuestionRepository mQuestionRepository;

    public Quiz_listFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestionRepository=QuestionRepository.getsInstance();

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
        List<Question>questions=mQuestionRepository.getQuestions();
        mRecyclerView_QuizList.setAdapter(new QuizAdapter(questions));
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
                    Intent intent=new Intent(getActivity(), QuizActivity.class);
                    intent.putExtra(EXTRA_QUIESTION_ID,mQuestion.getId());
                    startActivity(intent);
                }
            });


        }
        public void bindQuestion(Question question){
            mQuestion=question;
            mTextView_question.setText(mQuestion.getQuestionResId());
            mTextView_answer.setText(mQuestion.isAnswerTrue() ? R.string.btn_true_text : R.string.btn_false_text);
            mTextView_answer.setBackground(mQuestion.isAnswerTrue() ? getActivity().getDrawable(R.color.true_toast_bg) : getActivity().getDrawable(R.color.false_toast_bg));
            mCheckBox_is_cheated.setChecked(mQuestion.isCheat());

        }

    }
    private class QuizAdapter extends RecyclerView.Adapter<QuizHolder> {

        private List<Question>mQuestions;
        public void setQuestions(List<Question>questions){
            mQuestions=questions;
        }

        public List<Question> getQuestions() {
            return mQuestions;
        }

        public QuizAdapter(List<Question> questions) {
            mQuestions = questions;
        }

        @Override
        public int getItemCount() {
            return mQuestions.size();
        }
        @NonNull
        @Override
        public QuizHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_quiz__list__item,parent,false);
            return new QuizHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizHolder holder, int position) {
            Question question=mQuestions.get(position);
            holder.bindQuestion(question);
        }



    }


}