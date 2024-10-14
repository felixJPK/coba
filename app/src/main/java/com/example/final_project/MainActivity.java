package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextBerat, editTextTinggi;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button button = findViewById(R.id.oldPatientButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        editTextBerat = findViewById(R.id.editTextBerat);
        editTextTinggi = findViewById(R.id.editTextTinggi);
        textViewResult = findViewById(R.id.text_Result);
    }
    public void hitunganBMI(View view){
        double berat = Double.parseDouble(editTextBerat.getText().toString());
        double tinggi = Double.parseDouble(editTextTinggi.getText().toString());
        double bmi = berat / (tinggi * tinggi);
        if(bmi < 18.5){
            textViewResult.setText(bmi + "(Berat badan kurang)");
        }else if(bmi >= 18.5 && bmi <=24.9){
            textViewResult.setText(bmi + "(Normal)");
        }else if(bmi >= 18.5 && bmi <=24.9){
            textViewResult.setText(bmi + "(Berat badan Berlebih)");
        }else{
            textViewResult.setText(bmi + "(Obesitas)");
        }

    }
}