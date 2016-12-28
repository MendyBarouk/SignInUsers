package com.firstproject.mendy.signinusers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mendy on 22/12/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected static final String FILE_NAME = "myPrefsFile";
    protected static final String USER_NAME = "username";
    protected static final String PASSWORD = "password";
    protected static final String PHONE = "phone";
    protected static final String FIRST_NAME = "firstName";
    protected static final String LAST_NAME = "lastName";


    protected void moveToAnotherActivity(Class targetClass, boolean finish){
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
        if (finish)
            finish();
    }
}
