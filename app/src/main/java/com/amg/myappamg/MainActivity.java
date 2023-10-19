package com.amg.myappamg;
// Working
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amg.myappamg.Activites.MainMenuActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private CheckBox rememberMe;
    private MaterialButton loginBtn;
    private ImageView facebookIcon, githubIcon, twitterIcon;
    private TextView forgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);
        loginBtn = (MaterialButton) findViewById(R.id.loginBtn);
        facebookIcon = (ImageView) findViewById(R.id.facebookIcon);
        githubIcon = (ImageView) findViewById(R.id.githubIcon);
        twitterIcon = (ImageView) findViewById(R.id.twitterIcon);
        forgotPass = (TextView) findViewById(R.id.forgotPass);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = passwordInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();

                if (pass.isEmpty() && email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in all these", Toast.LENGTH_SHORT).show();
                }
                if (email.equals("amg@mail.com") || email.equals("nei@mail.com") && pass.equals("123456")) {
                    // Create an AlertDialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Login Alert")
                            .setMessage("Hello " + email)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    goToDashboard();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else
                    Toast.makeText(MainActivity.this, "Wrong pass " + email, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void methodMessage(){
        String github = "Github";
        String facebook = "Facebook";
        String Twitter = facebookIcon.toString().trim();
        facebookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void goToDashboard(){
        Intent x = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(x);
    }
}