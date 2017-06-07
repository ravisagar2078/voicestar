package com.example.ravisagar.hci;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SoapActivity extends AppCompatActivity {
TextView tv;
    AsyncCallWS aa= new AsyncCallWS();
    String u,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soap);
         tv = (TextView) findViewById(R.id.spTextView);
       //  aa.execute();
        aa.execute();
        //tv.setText(cs.getGret());
    }
    public void log(String type,String username,String password)
    {
      //  aa.execute(type,username,password);
//        Toast.makeText(this, "logout", Toast.LENGTH_SHORT)
  //              .show();
    }
    private class AsyncCallWS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            CallSoap cs = new CallSoap();

/*
            String t=params[0];

            if(t.equals("login"))
            {
                u=params[1];
                p=params[2];
                return toString(cs.login(u,p));
            }
*/
            return cs.getGret();
        }

        @Override
        protected void onPostExecute(String result) {
        tv.setText(result);
            Log.d("key",result);
 //           AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        //    Intent intent=new Intent(getApplicationContext(), Dashboard.class);
          //  intent.putExtra("abhi", u);

          //  getApplicationContext().startActivity(intent);
/*
            if (result.equals("true")) {
                Intent intent=new Intent(getApplicationContext(), Dashboard.class);
                intent.putExtra("abhi", u);

                getApplicationContext().startActivity(intent);

            }*/


        }

        @Override
        protected void onPreExecute() {
   //         Log.i(TAG, "onPreExecute");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
     //       Log.i(TAG, "onProgressUpdate");
        }

    }

}
