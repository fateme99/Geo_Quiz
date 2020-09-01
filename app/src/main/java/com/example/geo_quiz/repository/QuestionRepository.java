package com.example.geo_quiz.repository;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.Question;

import java.util.ArrayList;
import java.util.UUID;

public class QuestionRepository {
    private static QuestionRepository sInstance;
    private ArrayList<Question>mQuestions;
    private QuestionRepository() {
        mQuestions=new ArrayList<>();
        mQuestions.add(new Question(R.string.question_tehran, true));
        mQuestions.add(new Question(R.string.question_oceans, true));
        mQuestions.add(new Question(R.string.question_middle_east, false));
        mQuestions.add(new Question(R.string.question_australia, false));
        mQuestions.add(new Question(R.string.question_egypt, true));
        mQuestions.add(new Question(R.string.question_america, false));
        mQuestions.add(new Question(R.string.question_asia, false));
        mQuestions.add(new Question(R.string.question_tehran, true));
        mQuestions.add(new Question(R.string.question_oceans, true));
        mQuestions.add(new Question(R.string.question_middle_east, false));
        mQuestions.add(new Question(R.string.question_australia, false));
        mQuestions.add(new Question(R.string.question_egypt, true));
        mQuestions.add(new Question(R.string.question_america, false));
        mQuestions.add(new Question(R.string.question_asia, false));
        mQuestions.add(new Question(R.string.question_tehran, true));
        mQuestions.add(new Question(R.string.question_oceans, true));
        mQuestions.add(new Question(R.string.question_middle_east, false));
        mQuestions.add(new Question(R.string.question_australia, false));
        mQuestions.add(new Question(R.string.question_egypt, true));
        mQuestions.add(new Question(R.string.question_america, false));
        mQuestions.add(new Question(R.string.question_asia, false));
        mQuestions.add(new Question(R.string.question_tehran, true));
        mQuestions.add(new Question(R.string.question_oceans, true));
        mQuestions.add(new Question(R.string.question_middle_east, false));
        mQuestions.add(new Question(R.string.question_australia, false));
        mQuestions.add(new Question(R.string.question_egypt, true));
        mQuestions.add(new Question(R.string.question_america, false));
        mQuestions.add(new Question(R.string.question_asia, false));


    }

    public static QuestionRepository getsInstance() {
        if (sInstance ==null){
            sInstance =new QuestionRepository();
        }
        return sInstance;
    }
    public ArrayList<Question>getQuestions(){
        return mQuestions;
    }
    public Question getQuestion(UUID id){
        for (Question question :mQuestions) {
            if (question.getId().equals(id))
                return question;
        }
        return null;
    }
    public void insertQuestion(Question question){
        mQuestions.add(question);
    }
    //TODO
    public void deleteQuestion(UUID id){

    }
    //TODO
    public void updateQuestion(){

    }
}
