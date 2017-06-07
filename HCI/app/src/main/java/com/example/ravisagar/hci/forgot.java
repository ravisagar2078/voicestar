package com.example.ravisagar.hci;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ravi Sagar on 12/5/2016.
 */
public class forgot extends AppCompatActivity {
    EditText email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        //String url="http://192.168.0.101/pp.html";
      //  WebView view =(WebView) this.findViewById(R.id.webView2);
    //    view.getSettings().setJavaScriptEnabled(true);
       // view.loadUrl(url);
        email= (EditText)findViewById(R.id.et_email);
    }

    public void onsend(View view)
    {
        String getEmailId = email.getText().toString();
        //String str_email = email.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not

        // Check if email id is valid or not
        if (!m.find())
            Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
        else{
        Toast.makeText(getApplicationContext(),"We sent an email to your email address with instructions on how to reset your password. ",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this,MainActivity.class) );
    }}
    private void checkValidation() {
        // Get email id and password
       String getEmailId = email.getText().toString();
        //String str_email = email.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not

        // Check if email id is valid or not
        if (!m.find())
            Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
        // new CustomToast().Show_Toast(getActivity(), view,
             //       "Your Email Id is Invalid.");
            // Else do login and do your stuff
        else
            Toast.makeText(this, " ", Toast.LENGTH_SHORT).show();

    }
}



