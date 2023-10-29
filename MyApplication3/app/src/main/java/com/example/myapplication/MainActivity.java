package com.example.myapplication;

import static java.lang.Math.log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.listeners.MyBtnOnclickListener;
import com.example.myapplication.textWatchers.MyTextWatcher;
import com.example.myapplication.validations.MyRegisterFormValidator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        MyRegisterFormValidator validator=new MyRegisterFormValidator(this);
        ((EditText)findViewById(R.id.inpEmail)).addTextChangedListener(validator);
        ((EditText)findViewById(R.id.inpPassword)).addTextChangedListener(validator);
        ((EditText)findViewById(R.id.inpPasswordConfirm)).addTextChangedListener(validator);
    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        EditText inp=(EditText) findViewById(R.id.inpText);
//        //Second variant
//        inp.addTextChangedListener(new MyTextWatcher(this));
        //First variant
//        inp.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //log(Double.parseDouble("before"+charSequence));
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                //log(Double.parseDouble("on"+charSequence));
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//               // log(Double.parseDouble("after"+editable.toString()));
//                Log.d("after",editable.toString());
//            }

        //});
    //}

    //@Override
    protected void onCreateBtn(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn=(Button) findViewById(R.id.btnClickMe);
        btn.setText("My new btn text");

        //First variant
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("btn","Click on");
//            }
//        });
        //Second variant-when I describe listener separately
        btn.setOnClickListener(new MyBtnOnclickListener(this));
    }

    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void log(String msg) {
        Log.d("MainActivity", msg);
    }
}