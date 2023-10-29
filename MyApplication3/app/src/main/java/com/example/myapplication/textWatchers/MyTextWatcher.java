package com.example.myapplication.textWatchers;

import android.text.Editable;
import android.text.TextWatcher;

import com.example.myapplication.MainActivity;

public class MyTextWatcher implements TextWatcher {
    MainActivity activity;
    public MyTextWatcher(MainActivity activity){
        this.activity = activity;
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        activity.log("beforeTextChanged: " + charSequence);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        activity.log("onTextChanged: " + charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        activity.log("afterTextChanged: " + editable.toString());
        activity.log("-----");
    }
}
