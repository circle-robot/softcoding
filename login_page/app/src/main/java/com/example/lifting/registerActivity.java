package com.example.lifting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class registerActivity extends AppCompatActivity {

    EditText email_input, password_input,height_input,weight_input,nickname_input;
    Button SendInfo_btn,check_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_input = (EditText)findViewById(R.id.email_input);
        password_input = (EditText)findViewById(R.id.password_input);
        height_input = (EditText)findViewById(R.id.height_input);
        weight_input = (EditText)findViewById(R.id.weight_input);
        nickname_input = (EditText)findViewById(R.id.nickname_input);

        SendInfo_btn = (Button)findViewById(R.id.SendInfo_btn);
        SendInfo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String strEmail = email_input.getText().toString();
//                String strPassword = password_input.getText().toString();
               finish();
            }
        });

        check_btn = (Button)findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strEmail = email_input.getText().toString();
                String strPassword = password_input.getText().toString();
                CustomTask task = new CustomTask();
                try {
                    String result = task.execute(strEmail).get();
                    Log.i("return val",result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                finish();

            }
        });
    }
    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://13.125.127.86:8080/data.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "EUC-KR");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }
}
