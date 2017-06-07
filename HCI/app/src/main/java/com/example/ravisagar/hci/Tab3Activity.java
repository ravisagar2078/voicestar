package com.example.ravisagar.hci;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
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
public class Tab3Activity extends AppCompatActivity {
ImageButton button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab3);
        button =(ImageButton) findViewById(R.id.button);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {  if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED )
        {
            requestPermissions(new String []{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            return;
        }
        }
        enable_button();
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
                GlobalClass g = (GlobalClass)getApplication();
                g.setName("");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //      progress1 = new ProgressDialog(Tab3Activity.this);
            //    progress1.setTitle("Logout");
              //  progress1.setMessage("Please wait...");
               // progress1.show();

                getApplicationContext().startActivity(intent);

                //getApplicationContext().startActivity(intent);

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
    private  void enable_button()
    {
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                new MaterialFilePicker().withActivity(Tab3Activity.this).withRequestCode(10).start();
            }
        });

    }
    @Override
    public  void onRequestPermissionsResult(int requestcode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if(requestcode==100&&(grantResults[0]==PackageManager.PERMISSION_GRANTED))
        {enable_button();}
        else {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            {
                requestPermissions(new String []{Manifest.permission.READ_EXTERNAL_STORAGE},100);

            }
        }
    }
    ProgressDialog progress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK){

            progress = new ProgressDialog(Tab3Activity.this);
            progress.setTitle("Uploading");
            progress.setMessage("Please wait...");
            progress.show();
            Toast.makeText(getApplicationContext(),"File Uploaded ",Toast.LENGTH_SHORT).show();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    File f  = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type  = getMimeType(f.getPath());
                    //System.out.print("me"+content_type);
                    String file_path = f.getAbsolutePath();
                  //  Toast.makeText(getApplicationContext(),file_path.substring(file_path.lastIndexOf("/")+1),Toast.LENGTH_SHORT).show();

                    OkHttpClient client = new OkHttpClient();
                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);

                    RequestBody request_body = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("type",content_type)
                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                            .build();

                    Request request = new Request.Builder()
                            .url("http://10.12.60.65/hciup.php")
                            .post(request_body)
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        if(!response.isSuccessful()){
                            throw new IOException("Error : "+response);
                        }

                        progress.dismiss();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });

            t.start();}}
    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

}





