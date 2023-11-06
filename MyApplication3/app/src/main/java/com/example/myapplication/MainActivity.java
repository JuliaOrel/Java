package com.example.myapplication;

import static java.lang.Math.log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.listeners.MyBtnOnclickListener;
import com.example.myapplication.textWatchers.MyTextWatcher;
import com.example.myapplication.validations.MyRegisterFormValidator;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_grid_layout);
        gridLayout=findViewById(R.id.gridLayout);

        int numRows=4;
        int numCols=4;
        createGridView(numRows, numCols);

    }

    private void createGridView(int numRows, int numCols) {
        gridLayout.removeAllViews();

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ImageView imageView = new ImageView(this);
                // Пример загрузки изображения из ресурсов
                imageView.setImageResource(R.drawable.koran);
                // Другие настройки для imageView

                // Добавление imageView в gridLayout
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(row, 1f);
                params.columnSpec = GridLayout.spec(col, 1f);
                imageView.setLayoutParams(params);

                gridLayout.addView(imageView);
            }
        }
    }

    protected void createMemoryGamePad(){

    }


    protected void createMemoryGamePadOneIcon() {
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.koran);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        imageView.setLayoutParams(layoutParams);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast("Click");
            }
        });

        constraintLayout.addView(imageView);

        setContentView(constraintLayout);
    }

    protected void createElements(){
        ConstraintLayout constraintLayout=new ConstraintLayout(this);

        Button btn=new Button(this);
        btn.setText("Hello, guys!");

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);

        btn.setLayoutParams(layoutParams);
        constraintLayout.addView(btn);
        setContentView(constraintLayout);
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calculator_layout);
////        MyRegisterFormValidator validator=new MyRegisterFormValidator(this);
////        ((EditText)findViewById(R.id.inpEmail)).addTextChangedListener(validator);
////        ((EditText)findViewById(R.id.inpPassword)).addTextChangedListener(validator);
////        ((EditText)findViewById(R.id.inpPasswordConfirm)).addTextChangedListener(validator);
//    }
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