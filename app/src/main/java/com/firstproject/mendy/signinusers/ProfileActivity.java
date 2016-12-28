package com.firstproject.mendy.signinusers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.firstproject.mendy.signinusers.infrastructure.Validation;
import com.soundcloud.android.crop.Crop;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    private static final int LAUNCH_CAMERA_APP_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_CODE = 2;
    public static final int IMAGE_VIEW_AVATAR_TAG = 1;

    private SharedPreferences.Editor editor;
    private FrameLayout changeAvatarFrameLayout;
    private ImageView avatarImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        changeAvatarFrameLayout = (FrameLayout) findViewById(R.id.activity_profile_change_avatar_frameLayout);
        avatarImageView = (ImageView) findViewById(R.id.activity_profile_avatar_imageView);
        avatarImageView.setTag(IMAGE_VIEW_AVATAR_TAG);

        changeAvatarFrameLayout.setOnClickListener(this);

        SharedPreferences setting = getSharedPreferences(BaseActivity.FILE_NAME, Context.MODE_PRIVATE);
        editor = setting.edit();

        String userName = setting.getString(BaseActivity.USER_NAME, null);

        Toast.makeText(this, "The username is : " + userName, Toast.LENGTH_SHORT).show();

        final EditText firstName = (EditText) findViewById(R.id.activity_profile_firtName_editText);
        final EditText lastName = (EditText) findViewById(R.id.activity_profile_lastName_editText);
        final EditText phoneNumber = (EditText) findViewById(R.id.activity_profile_phoneNumber_editText);

        findViewById(R.id.activity_profile_set_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.validateProfil(firstName, lastName, phoneNumber, avatarImageView)) {
                    String fName = firstName.getText().toString().trim();
                    String lName = lastName.getText().toString().trim();
                    String phone = phoneNumber.getText().toString().trim();
                    saveData(fName, lName, phone);
                    moveToAnotherActivity(MainActivity.class, true);
                }

            }
        });

    }

    private void saveData(String fName, String lName, String phone) {
        editor.putString(BaseActivity.FIRST_NAME, fName);
        editor.putString(BaseActivity.LAST_NAME, lName);
        editor.putString(BaseActivity.PHONE, phone);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        if (v == changeAvatarFrameLayout) {
            /*Uri uri = Uri.parse("tel:0526309305");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);*/

            /*Uri uri = Uri.parse("http://www.google.fr");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);*/

            /*Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);*/

            /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, LAUNCH_CAMERA_APP_REQUEST_CODE);*/

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LAUNCH_CAMERA_APP_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");

                avatarImageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(this, "cancelled!", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == GALLERY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Uri imageUri = data.getData();
                Crop.of(imageUri, imageUri).asSquare().start(this);
            }
        } else if (requestCode == Crop.REQUEST_CROP){
            if (resultCode == RESULT_OK){
                avatarImageView.setImageURI(Crop.getOutput(data));
                avatarImageView.setTag(0);
            }
        }
    }
}
