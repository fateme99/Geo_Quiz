package com.example.geo_quiz.model;

import java.io.Serializable;

public class Setting implements Serializable {

    private int mSize;
    private String mBgColorName;
    private boolean mHide_False, mHide_true,mHide_cheat,mHide_next,mHide_prev,mHide_first,mHide_last;

    public boolean isHide_False() {
        return mHide_False;
    }

    public void setHide_False(boolean hide_False) {
        mHide_False = hide_False;
    }

    public boolean isHide_true() {
        return mHide_true;
    }

    public void setHide_true(boolean hide_true) {
        this.mHide_true = hide_true;
    }

    public boolean isHide_cheat() {
        return mHide_cheat;
    }

    public void setHide_cheat(boolean hide_cheat) {
        mHide_cheat = hide_cheat;
    }

    public boolean isHide_next() {
        return mHide_next;
    }

    public void setHide_next(boolean hide_next) {
        mHide_next = hide_next;
    }

    public boolean isHide_prev() {
        return mHide_prev;
    }

    public void setHide_prev(boolean hide_prev) {
        mHide_prev = hide_prev;
    }

    public boolean isHide_first() {
        return mHide_first;
    }

    public void setHide_first(boolean hide_first) {
        mHide_first = hide_first;
    }

    public boolean isHide_last() {
        return mHide_last;
    }

    public void setHide_last(boolean hide_last) {
        mHide_last = hide_last;
    }

    public String getBgColorName() {
        return mBgColorName;
    }

    public void setBgColorName(String bgColorName) {
        this.mBgColorName = bgColorName;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }
}
