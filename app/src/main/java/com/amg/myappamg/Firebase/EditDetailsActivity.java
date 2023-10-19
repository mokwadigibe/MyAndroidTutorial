package com.amg.myappamg.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amg.myappamg.R;

public class EditDetailsActivity extends AppCompatActivity {
    TextView enterEmailEditText;
    EditText updateUsernameEditText, updatePhoneNumberEditText;
    ImageView updateProfileImageView;

    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
    }
}