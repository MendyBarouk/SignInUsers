package com.firstproject.mendy.signinusers;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firstproject.mendy.signinusers.infrastructure.Validation;

public class RegisterActivity extends BaseActivity {

    private SharedPreferences settings;                         // the object that related the file.
    private SharedPreferences.Editor editor;                    // the object that can insert data to the file.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        settings = getSharedPreferences(BaseActivity.FILE_NAME, Context.MODE_PRIVATE);      // read data from file.
        editor = settings.edit();                                                           // write data to file.

        final EditText userName = (EditText) findViewById(R.id.activity_register_userName_editText);
        final EditText password = (EditText) findViewById(R.id.activity_register_password_editText);
        final EditText confirmPassword = (EditText) findViewById(R.id.activity_register_confirmPassword_editText);

        findViewById(R.id.activity_register_signUp_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Validation.validateRegister(userName, password, confirmPassword)){
                    String user = userName.getText().toString().trim();
                    String pass = password.getText().toString();
                    saveData(user, pass);
                    moveToAnotherActivity(ProfileActivity.class, true);
                }
            }

        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                moveToAnotherActivity(LoginActivity.class, true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveData(String user, String pass) {
        editor.putString(USER_NAME, user);
        editor.putString(PASSWORD, pass);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        moveToAnotherActivity(MainActivity.class, true);
    }
}
