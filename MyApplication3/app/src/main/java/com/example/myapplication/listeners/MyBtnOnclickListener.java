package com.example.myapplication.listeners;

import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class MyBtnOnclickListener implements View.OnClickListener{

    MainActivity activity;
    public MyBtnOnclickListener(MainActivity activity){
        this.activity=activity;
    }
    @Override
    public void onClick(View view) {
        int color= ContextCompat.getColor(view.getContext(), R.color.black);
        //view.setBackgroundColor(color);
        this.activity.toast("Hello from click");
    }
}
