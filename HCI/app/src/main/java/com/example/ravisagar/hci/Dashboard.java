package com.example.ravisagar.hci;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;
;

/**
 * Created by Ravi Sagar on 10/19/2016.
 */
public class Dashboard extends  TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        Toast.makeText(this, "Welcome To Singing World!!", Toast.LENGTH_SHORT)
                .show();
        Intent intent = getIntent();
        String kuto = intent.getExtras().getString("abhi");
    //    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
      //  alertDialog.setTitle("Login Status");
        System.out.println(kuto);
        GlobalClass g = (GlobalClass)getApplication();
        g.setName(kuto);
//        GlobalClass globalVariable = (GlobalClass) getApplicationContext();
  //      globalVariable.setName(kuto);

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Recorder");
      //  intent.putExtra("abhi", kuto);
       // Intent intent2=new Intent(getApplicationContext(), Tab1Activity.class);
        //intent2.putExtra("abhi", kuto);
        //getApplicationContext().startActivity(intent2);
        tab1.setContent(new Intent(this,Tab1Activity.class));

        tab2.setIndicator("Popular");
        tab2.setContent(new Intent(this,Tab2Activity.class));

        tab3.setIndicator("Gallery");
        tab3.setContent(new Intent(this,Tab3Activity.class));


        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);


    }/*
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
 //                       SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                        //Getting editor
   //                     SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
//                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
  //                      editor.putString(Config.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
    //                    editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(Dashboard.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
*/



    /*   @Override
       public boolean onCreateOptionsMenu(Menu menu) {
           //Adding our menu to toolbar
           getMenuInflater().inflate(R.menu.menu, menu);
           return true;
       }

       @Override
       public boolean onOptionsItemSelected(MenuItem item) {
           int id = item.getItemId();
           if (id == R.id.menuLogout) {
               //calling logout method when the logout button is clicked
     //          logout();
           }
           return super.onOptionsItemSelected(item);
       }
   */
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
     MenuInflater inflater = getMenuInflater();
     inflater.inflate(R.menu.mainmenu, menu);
     return true;
 }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
          /*  case R.id.action_refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;*/
            // action with ID action_settings was selected
            case R.id.Logout:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                getApplicationContext().startActivity(intent);

                Toast.makeText(this, "logout", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }
}


