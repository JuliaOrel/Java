package com.example.myapplication.validations;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class MyRegisterFormValidator implements TextWatcher {

    MainActivity activity;
    EditText inpEmail;
    EditText inpPassword;
    EditText inpPasswordConfirm;
    Button btnRegister;


    public MyRegisterFormValidator (
            MainActivity activity,
            EditText inpEmail,
            EditText inpPassword,
            EditText inpPasswordConfirm,
            Button btnRegister
    ) {
        this.activity = activity;
        this.inpEmail = inpEmail;
        this.inpPassword = inpPassword;
        this.inpPasswordConfirm = inpPasswordConfirm;
        this.btnRegister = btnRegister;
    }

    public MyRegisterFormValidator (
            MainActivity activity) {
        this.activity = activity;
        this.inpEmail = (EditText) activity.findViewById(R.id.inpEmail);
        this.inpPassword = (EditText) activity.findViewById(R.id.inpPassword);
        this.inpPasswordConfirm = (EditText) activity.findViewById(R.id.inpPasswordConfirm);
        this.btnRegister = (Button) activity.findViewById(R.id.btnRegister);

        this.inpEmail.addTextChangedListener(this);
        this.inpPassword.addTextChangedListener(this);
        this.inpPasswordConfirm.addTextChangedListener(this);
    }

    void doValidate(){
        if(inpPassword.getText().length() < 4) {
            activity.toast(" Password < 4");
            btnRegister.setEnabled(false);
            return;
        }
        if (!inpPassword.getText().toString().equals(inpPasswordConfirm.getText().toString())) {
            activity.toast(" Confirm Password");
            btnRegister.setEnabled(false);
            return;
        }

        activity.toast("Welcome");
        btnRegister.setEnabled(true);
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        doValidate();
    }
}
