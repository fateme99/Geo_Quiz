package com.example.geo_quiz.model;

import android.widget.RadioButton;

import java.io.Serializable;

public class Setting implements Serializable {

    private int mSize;

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }
}
