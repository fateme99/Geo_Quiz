package com.example.geo_quiz;

public class Question {
    private int mQuestionResId;
    private boolean mIsAnswerTrue;
    private boolean mdisable;

    public boolean isMdisable() {
        return mdisable;
    }

    public void setMdisable(boolean mdisable) {
        this.mdisable = mdisable;
    }

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
        mdisable=false;
    }

    public Question() {
        mdisable=false;
    }
}
