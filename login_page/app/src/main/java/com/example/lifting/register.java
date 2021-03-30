package com.example.lifting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class register extends AppCompatActivity {

    EditText email_input, password_input,height_input,weight_input;
    Button SendInfo_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);
        height_input = findViewById(R.id.height_input);
        weight_input = findViewById(R.id.weight_input);

//        SendInfo_btn.findViewById(R.id.SendInfo_btn);
//        SendInfo_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               finish();
//            }
//        });
    }
}
