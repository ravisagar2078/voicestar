package com.example.ravisagar.hci;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    ProgressDialog progress;
    EditText email,username,password;
    String str_email,str_username,str_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        name= (EditText)findViewById(R.id.et_name);
        email= (EditText)findViewById(R.id.et_email);
       // age= (EditText)findViewById(R.id.et_age);
        username= (EditText)findViewById(R.id.et_username);
        password= (EditText)findViewById(R.id.et_password);


    }
        public void onReg(View view) {
            //String str_name = name.getText().toString();
            String str_email = email.getText().toString();
            //String str_age = age.getText().toString();
            String str_username = username.getText().toString();
            String str_password = password.getText().toString();

            String type = "register";
            // System.out.println(str_username);
            //System.out.println(str_password);
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type,  str_email, str_username, str_password);
     //       progress = new ProgressDialog(Register.this);
      //      progress.setTitle("Sign Up");
        //    progress.setMessage("Please wait...");
          //  progress.show();

        }
            public void onlogin(View view){
        startActivity(new Intent(this,MainActivity.class) );

    }


    }


