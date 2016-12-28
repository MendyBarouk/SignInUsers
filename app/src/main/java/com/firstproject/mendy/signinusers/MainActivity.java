package com.firstproject.mendy.signinusers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences settings = getSharedPreferences(BaseActivity.FILE_NAME, Context.MODE_PRIVATE);
        String userName = settings.getString(BaseActivity.USER_NAME, null);
        String phoneNumber = settings.getString(BaseActivity.PHONE, null);
        if (userName == null){
            moveToAnotherActivity(LoginActivity.class, true);
        } else if (userName != null && phoneNumber == null){
            moveToAnotherActivity(ProfileActivity.class, true);
        }


        findViewById(R.id.activity_main_logout_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().clear().commit();
                moveToAnotherActivity(LoginActivity.class, true);
            }
        });

        String password = settings.getString(BaseActivity.PASSWORD, null);
        String firstName = settings.getString(BaseActivity.FIRST_NAME, null);
        String lastName = settings.getString(BaseActivity.LAST_NAME, null);

        ((TextView) findViewById(R.id.activity_main_userName_textView)).setText(userName);
        ((TextView) findViewById(R.id.activity_main_password_textView)).setText(password);
        ((TextView) findViewById(R.id.activity_main_firstName_textView)).setText(firstName);
        ((TextView) findViewById(R.id.activity_main_lastName_textView)).setText(lastName);
        ((TextView) findViewById(R.id.activity_main_phoneNumber_textView)).setText(phoneNumber);


    }
}
