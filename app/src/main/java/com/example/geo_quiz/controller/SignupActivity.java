package com.example.geo_quiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;

public class SignupActivity extends AppCompatActivity {

    private TextInputLayout mUsernameForm;
    private TextInputLayout mPasswordForm;
    private TextInputLayout mPasswordForm2;
    private TextInputEditText mUsername;
    private TextInputEditText mPassword;
    private TextInputEditText mPassword2;
    private Button mSubmit;
    private User mUser;
    public static final String  EXTRA_USERiNFO="com.example.geo_quiz.userInfo";
    public static String userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mUser= (User) getIntent().getSerializableExtra(LoginActivity.EXTRA_USER_INFO_FOR_SIGNUP);
        findViews();
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()){
                    mUser=new User(mUsername.getText().toString() ,mPassword.getText().toString());
                    Toast.makeText(SignupActivity.this, "ثبت نام موفق", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent();
                    intent.putExtra(EXTRA_USERiNFO,mUser);
                    setResult(RESULT_OK,intent);
                    finish();
                }

                else{
                    Toast.makeText(SignupActivity.this, "ثبت نام نا موفق", Toast.LENGTH_LONG).show();
                }
            }
        });
        setDefaultValue();

    }

    private void findViews(){
        mPassword = findViewById(R.id.password);
        mPassword2=findViewById(R.id.password2);
        mUsername = findViewById(R.id.username);
        mUsernameForm = findViewById(R.id.username_form);
        mPasswordForm = findViewById(R.id.password_form);
        mPasswordForm2=findViewById(R.id.password_form2);
        mSubmit = findViewById(R.id.submit);
    }
    private boolean validateInput() {
        if (mUsername.getText().toString().trim().isEmpty()) {
            mUsernameForm.setErrorEnabled(true);
            mUsernameForm.setError("Field cannot be empty!");
            return false;
        }
        if (mPassword.getText().toString().trim().isEmpty() || mPassword2.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "لطفا پسورد را وارد کنید", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mPassword.getText().toString().equals(mPassword2.getText().toString())){
            mUsernameForm.setErrorEnabled(false);
            return true;
        }
        else
            return false;

    }
    private void setDefaultValue(){
        if (mUser !=null){
            mUsername.setText(mUser.getUserName());
            mPassword.setText(mUser.getPassWord());
            mPassword2.setText(mUser.getPassWord());
        }

    }

}