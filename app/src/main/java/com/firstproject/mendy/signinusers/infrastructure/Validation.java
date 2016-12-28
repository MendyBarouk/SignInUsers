package com.firstproject.mendy.signinusers.infrastructure;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firstproject.mendy.signinusers.ProfileActivity;
import com.firstproject.mendy.signinusers.R;

/**
 * Created by Mendy on 22/12/2016.
 */

public class Validation {
    private static boolean validateNoSpace(String str){
        return !str.isEmpty() && !str.contains(" ");
    }

    public static boolean validateLogin(EditText userName, EditText password){
        String user = userName.getText().toString().trim();
        String pass = password.getText().toString();
        Boolean valid = true;
        if (!validateNoSpace(user)){
            userName.setError("username problem");
            valid = false;
        }
        if (!validateNoSpace(pass)){
            password.setError("password problem");
            valid = false;
        }
        return valid;
    }

    public static boolean validateRegister(EditText userName, EditText password, EditText confirmPassword){
        if (validateLogin(userName, password)){
            if (!(password.getText().toString().equals(confirmPassword.getText().toString()))){
                confirmPassword.setError("Doesn't match  the password");
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean validateProfil(EditText firstName, EditText lastName, EditText phoneNumber, ImageView avatarImageView){
        String fName = firstName.getText().toString().trim();
        String lName = lastName.getText().toString().trim();
        String phoneN = phoneNumber.getText().toString().trim();
        boolean valid = true;
        if (fName.isEmpty()){
            firstName.setError("First name no correct");
            valid = false;
        }
        if (lName.isEmpty()){
            lastName.setError("last name no correct");
            valid = false;
        }
        if (phoneN.length() < 10){
            phoneNumber.setError("number of phone no correct");
            valid = false;
        }
        if (avatarImageView.getTag().equals((int) ProfileActivity.IMAGE_VIEW_AVATAR_TAG)){
            ((TextView) avatarImageView.getRootView().findViewById(R.id.activity_profile_avatar_textView)).setError("please choose pictur");
            valid = false;
        }
        return valid;

    }
}
