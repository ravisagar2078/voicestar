package com.example.ravisagar.hci;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progress;
    EditText UsernameEt,PasswordEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameEt=(EditText) findViewById(R.id.etUserName);
        PasswordEt=(EditText) findViewById(R.id.etPassword);


    }

    public void onlogin(View view)
    {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type="login";
        //BackgroundWorker backgroundWorker = new BackgroundWorker(this);

        soap soap1=new soap(this);
        soap1.execute(type,username,password);

        progress = new ProgressDialog(MainActivity.this);
        progress.setTitle("Authentication");
        progress.setMessage("Please wait...");
        progress.show();
    //    backgroundWorker.execute(type,username,password);

        progress.dismiss();


//

    }
    public void OpenReg(View view)
    {
    startActivity(new Intent(this,Register.class) );

    }


    public void OpenForgot(View view)
    {
        startActivity(new Intent(this,forgot.class) );

    }


}
