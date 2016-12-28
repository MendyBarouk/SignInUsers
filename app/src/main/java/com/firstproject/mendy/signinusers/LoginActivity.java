package com.firstproject.mendy.signinusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firstproject.mendy.signinusers.infrastructure.Validation;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText userName;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.activity_login_userName_editText);
        password = (EditText) findViewById(R.id.activity_login_password_editText);

        findViewById(R.id.activity_login_login_button).setOnClickListener(this);
        findViewById(R.id.activity_login_register_textView).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_login_button:
                if (Validation.validateLogin(userName, password)) {
                    moveToAnotherActivity(MainActivity.class, true);
                }
                break;
            case R.id.activity_login_register_textView: {
                moveToAnotherActivity(RegisterActivity.class, true);
                break;
            }
        }
    }
}
