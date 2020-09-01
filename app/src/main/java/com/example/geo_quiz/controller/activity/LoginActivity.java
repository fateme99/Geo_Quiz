package com.example.geo_quiz.controller.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.geo_quiz.R;
import com.example.geo_quiz.model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_USERINFO="com.example.geo_quiz.username";
    public static final String EXTRA_USER_INFO_FOR_SIGNUP="com.example.geo_quiz.userInfo";
    private TextInputLayout mUsernameForm;
    private TextInputLayout mPasswordForm;
    private TextInputEditText mUsername;
    private TextInputEditText mPassword;
    private Button mSubmit;
    private Button mSignUp;
    private final int mRequest_code_signUp=0;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
        mUser= (User) getIntent().getSerializableExtra(EXTRA_USERINFO);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null || resultCode!=RESULT_OK)
            return;
        if (requestCode==mRequest_code_signUp){
            mUser= (User) data.getSerializableExtra(SignupActivity.EXTRA_USERiNFO);
        }
        mSubmit.setClickable(true);
        setDefaultValue();
    }

    private void findViews(){
        mPassword = findViewById(R.id.password_login);
        mUsername = findViewById(R.id.username_login);
        mUsernameForm = findViewById(R.id.username_form_login);
        mPasswordForm = findViewById(R.id.password_form_login);
        mSubmit = findViewById(R.id.submit_login);
        mSignUp=findViewById(R.id.signUp_login);
    }
    private void setListeners(){
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.getText().toString().trim().isEmpty() || mPassword.getText().toString().trim().isEmpty()){
                    Snackbar.make(mPasswordForm,"لطفا اطلاعات را وارد کنید",Snackbar.LENGTH_LONG).show();
                }
                else if (mUser==null){
                    mUser=new User(mUsername.getText().toString(),mPassword.getText().toString());
                    Toast.makeText(LoginActivity.this, "ابتدا وارد شوید", Toast.LENGTH_SHORT).show();
                    mSubmit.setClickable(false);
                }
                else {
                    if (mUser.getUserName().equals(mUsername.getText().toString())  &&  mUser.getPassWord().equals(mPassword.getText().toString())){
                        Snackbar.make(mPasswordForm,"ورود موفق",Snackbar.LENGTH_LONG).show();
                        final Intent intent=new Intent(LoginActivity.this, QuizActivity.class);
                        intent.putExtra(EXTRA_USERINFO,mUser);
                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },4000);

                    }
                }
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                intent.putExtra(EXTRA_USER_INFO_FOR_SIGNUP,mUser);
                startActivityForResult(intent,mRequest_code_signUp);
            }
        });
    }
    public void setDefaultValue(){
        if (mUser!= null){
            mUsername.setText(mUser.getUserName());
            mPassword.setText(mUser.getPassWord());
        }

    }


}