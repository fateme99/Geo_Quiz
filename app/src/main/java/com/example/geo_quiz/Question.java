package com.example.geo_quiz;

public class Question {
    private int mQuestionResId;
    private boolean mIsAnswerTrue;

    public int getQuestionResId() {
        return mQuestionResId;
    }

    public void setQuestionResId(int questionResId) {
        mQuestionResId = questionResId;
    }

    public boolean isAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mIsAnswerTrue = answerTrue;
    }

    public Question(int questionResId, boolean isAnswerTrue) {
        mQuestionResId = questionResId;
        mIsAnswerTrue = isAnswerTrue;
    }

    public Question() {
    }
}
