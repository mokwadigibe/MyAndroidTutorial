package com.amg.myappamg.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amg.myappamg.R;


public class MainMenuActivity extends AppCompatActivity {
    ImageView bmi_imageview, whatsappImageView;
    TextView bmi_textview, whatsappTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bmi_imageview = (ImageView) findViewById(R.id.bmi_imageview);
        bmi_textview = (TextView) findViewById(R.id.bmi_textview);
        whatsappImageView = (ImageView) findViewById(R.id.whatsappImageView);
        whatsappTextView = (TextView) findViewById(R.id.whatsappTextView);

        bmi_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBMICalculator();
            }
        });
        bmi_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToBMICalculator();
            }
        });
        whatsappImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWhatsapp();
            }
        });
        whatsappTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWhatsapp();
            }
        });

    }

    public void goToBMICalculator(){
        Intent x = new Intent(MainMenuActivity.this, BMIActivity.class);
        startActivity(x);
    }

    public void goToWhatsapp(){
        Intent x = new Intent(MainMenuActivity.this, WhatsappActivity.class);
        startActivity(x);
    }
}