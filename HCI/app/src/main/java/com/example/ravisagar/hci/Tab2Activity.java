package com.example.ravisagar.hci;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Ravi Sagar on 10/23/2016.
 */
public class Tab2Activity extends AppCompatActivity {
//CallSoap a=new CallSoap();
   // String n ="http://192.168.0.104/hciupload/";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        //  String url="http://192.168.0.108/new2.php";
        // WebView view =(WebView) this.findViewById(R.id.webView);
        //view.getSettings().setJavaScriptEnabled(true);
        //view.loadUrl(url);
        String type = "getlist";
      /*  soap a = (soap) new soap(new soap.Aaa() {
            @Override
            public void processfinish(String output) {
                System.out.print("li"+output);
                Log.d("list",output);
            }
        }).execute(type);*/
soap  a=new soap(this);
  a.execute(type);

     //   String lis =a.getPlayList();
       // Intent intent = getIntent();
     //   String lis = intent.getExtras().getString("playlist");
    //  String q=  a.getrecording();
       // System.out.print("list hai"+lis);
        //Log.d("tab2",q);
 //   GlobalClass gg=(GlobalClass)getApplication();
   //     System.out.print("list hai global"+gg.getName2());

    //    Intent intent = getIntent();
//        String kuto = intent.getExtras().getString("playlist");
  //      System.out.print("list"+kuto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    ProgressDialog progress1;
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
             //   getApplicationContext().startActivity(intent);
                GlobalClass g = (GlobalClass)getApplication();
                g.setName("");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // progress1 = new ProgressDialog(Tab2Activity.this);
                //progress1.setTitle("Logout");
                //progress1.setMessage("Please wait...");
                //progress1.show();

                getApplicationContext().startActivity(intent);

                Toast.makeText(this, "logout", Toast.LENGTH_SHORT)
                        .show();
                break;
/*            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
  */          default:
                break;
        }

        return true;
    }
}

