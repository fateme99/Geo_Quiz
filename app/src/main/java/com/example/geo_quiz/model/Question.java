package com.example.geo_quiz.model;

import java.io.Serializable;

public class Question implements Serializable {
    private int mQuestionResId;
    private boolean mIsAnswerTrue;
    private boolean mdisable;
    private boolean mIsCheat;

    public boolean isCheat() {
        return mIsCheat;
    }

    public void setCheat(boolean cheat) {
        mIsCheat = cheat;
    }

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
        mIsCheat=false;
    }

    public Question() {
        mdisable=false;
    }
}
