package com.example.bmicalculatorjava4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText height, weight;

    //final EditText heightText = findViewById(R.id.height);
    //final EditText weightText = findViewById(R.id.weight);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

       //SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        //int height1 = Integer.parseInt(sp.getString(String.valueOf(height), ""));
        //int weight1 = Integer.parseInt(sp.getString(String.valueOf(weight),""));

        //height.setText(height1);
        //weight.setText(weight1);


        myButtonListenerMethod();

        button = (Button) findViewById(R.id.about_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

    }

    public void openActivity2(){
        Intent intent = new Intent (this,About.class);
        startActivity(intent);

    }

    //SharedPreferences start here
    // Fetch the stored data in onResume()
    // Because this is what will be called
    // when the app opens again

    @Override
    protected void onResume() {
        super.onResume();


        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        int s1 = sh.getInt("height", 0);
        int a = sh.getInt("weight", 0);

        // Setting the fetched data
        // in the EditTexts
        height.setText(String.valueOf(s1));
        weight.setText(String.valueOf(a));
    }

    // Store the data in the SharedPreference
    // in the onPause() method
    // When the user closes the application
    // onPause() will be called
    // and data will be stored

    @Override
    protected void onPause() {
        super.onPause();

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putInt("height", Integer.parseInt(height.getText().toString()));
        myEdit.putInt("weight", Integer.parseInt(weight.getText().toString()));
        myEdit.apply();
    }

    //SharedPreferences end here

    public void myButtonListenerMethod() {
        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //onPause();

                //final EditText heightText = findViewById(R.id.height);
                String heightStr = height.getText().toString();
                double height = Double.parseDouble(heightStr);
                double heightM = height/100;
                //final EditText weightText = findViewById(R.id.weight);
                String weightStr = weight.getText().toString();
                double weight = Double.parseDouble(weightStr);
                double BMI = (weight) / (heightM * heightM);
                DecimalFormat df = new DecimalFormat("#.#");
                double BMI_trimmed = Double.parseDouble(df.format(BMI));
                final TextView BMIResult = findViewById(R.id.result);
                BMIResult.setText(Double.toString(BMI_trimmed));

                String BMI_Cat;
                String BMI_Risk;

                if (BMI < 18.4){
                    BMI_Cat = "Underweight";
                    BMI_Risk = "Malnutrition Risk";
                }
                else if (BMI >= 18.5 && BMI < 24.9){
                    BMI_Cat = "Normal Weight";
                    BMI_Risk = "Low Risk";
                }
                else if (BMI >=25 && BMI < 29.9){
                    BMI_Cat = "Overweight";
                    BMI_Risk = "Enhanced Risk";
                }
                else if (BMI >=30 && BMI < 34.9){
                    BMI_Cat = "Moderately Obese";
                    BMI_Risk = "Medium Risk";
                }
                else if (BMI >= 35 && BMI < 39.9){
                    BMI_Cat = "Severely Obese";
                    BMI_Risk = "High Risk";
                }
                else {
                    BMI_Cat = "Very Severely Obese";
                    BMI_Risk = "Very High Risk";
                }

                final TextView BMICategory = findViewById(R.id.bmiCat);
                BMICategory.setText(BMI_Cat);
                final TextView BMIRisk = findViewById(R.id.bmiRisk);
                BMIRisk.setText(BMI_Risk);

            }


        });
    }
}
