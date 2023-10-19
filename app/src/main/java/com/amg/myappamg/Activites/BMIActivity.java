package com.amg.myappamg.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.amg.myappamg.R;


public class BMIActivity extends AppCompatActivity {

    private EditText heightInput;
    private EditText weightInput;
    private Button calculateButton;
    private TextView resultText,bmiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);
        bmiResult=findViewById(R.id.bmiResult);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = heightInput.getText().toString();
        String weightStr = weightInput.getText().toString();

        if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
            float height = Float.parseFloat(heightStr);
            float weight = Float.parseFloat(weightStr);

            float bmi = weight / (height * height);

            String bmiCategory;
            int bgColor;

            if (bmi < 18.5) {
                bmiCategory = "underweight";
                bgColor = ContextCompat.getColor(getApplicationContext(), R.color.bgUnderweight);
            } else if (bmi >= 18.5 && bmi < 24.9) {
                bmiCategory = "healthy";
                bgColor = ContextCompat.getColor(getApplicationContext(), R.color.bgHealthy);
            } else if (bmi >= 24.9 && bmi < 30) {
                bmiCategory = "overweight";
                bgColor = ContextCompat.getColor(getApplicationContext(), R.color.bgOverweight);
            } else {
                bmiCategory = "obese";
                bgColor = ContextCompat.getColor(getApplicationContext(), R.color.bgObese);
            }

            bmiResult.setText(String.format("%.2f", bmi));
            resultText.setText(String.format("Your BMI is in the %s category.", bmiCategory));
            resultText.setBackgroundColor(bgColor);
            resultText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        } else {
            resultText.setText("Please enter valid values.");
            // Reset to default colors
            resultText.setBackgroundColor(Color.TRANSPARENT);
            resultText.setTextColor(Color.BLACK);
        }
    }


}