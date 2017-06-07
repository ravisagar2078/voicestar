package com.example.ravisagar.hci;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by Ravi Sagar on 10/23/2016.
 */
public class Tab1Activity extends AppCompatActivity {
    Handler handler;
    TextView tv,tv1,textView2;
    MediaRecorder mRecorder;
    String fileName="";
    Boolean isRecording;
    int recordTime, playTime;
    String m_Text="",name;

    SeekBar seekBar;
    MediaPlayer mPlayer;
    soap soap1=new soap(this);

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //java.util.Date noteTS = Calendar.getInstance().getTime();
    //String time = "hh:mm:sec"; // 12:00
    int numberOfHours;
    int numberOfMinutes;
    int numberOfSeconds;

    //private String password1;
    AlertDialog alertdialogbox;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);


        GlobalClass g = (GlobalClass)getApplication();
        name  = g.getName();
        tv = (TextView) findViewById(R.id.txttime);
        //tv.setText(Html.fromHtml("00<sup>h</sup>"));
        tv1 = (TextView) findViewById(R.id.textView3);
        textView2=(TextView) findViewById(R.id.textView2);
        seekBar = (SeekBar) findViewById(R.id.seek1);
//        tv.setText(DateFormat.format(time, noteTS));
        // imageButton2 = (ImageButton) findViewById(R.id.imageButton2);

        handler = new Handler();
        fileName = Environment.getExternalStorageDirectory() + "/Song-" +name+"-"+ System.currentTimeMillis() + ".3gp";
            //fileName = Environment.getExternalStorageDirectory()  +name+"_"+ m_Text + ".3gp";


//        fileName = Environment.getExternalStorageDirectory() + "/"+password1+"-" +name+"-"+ System.currentTimeMillis() + ".3gp";
        isRecording = false;
/*
        byte[] soundBytes;

        try {
            InputStream inputStream = getContentResolver().openInputStream(Uri.fromFile(new File(fileName)));
            soundBytes = new byte[inputStream.available()];

            soundBytes = toByteArray(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
*/
    }
    public byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read = 0;
        byte[] buffer = new byte[1024];
        while (read != -1) {
            read = in.read(buffer);
            if (read != -1)
                out.write(buffer,0,read);
        }
        out.close();
        return out.toByteArray();
    }
    private boolean paused =true;

    public void buttonPressed(View view) {
     //   GlobalClass g = (GlobalClass)getApplication();
   //     String name  = g.getName();

        ImageButton imageButton = (ImageButton) view;
        int icon;
    //    icon = R.drawable.play;
//        imageButton.setImageDrawable(
  //              ContextCompat.getDrawable(getApplicationContext(), icon));

        if (paused) {
            paused = false;
            icon = R.drawable.pause1;
          //  imageButton2.setEnabled(false);
            //showalert();
          //  fileName = Environment.getExternalStorageDirectory() + "/"+password1+"-" +name+"-"+ System.currentTimeMillis() + ".3gp";
textView2.setText("Press Button to Stop");
            startRecording();
         //   System.out.print("pausesonu");
             }
            else {
                paused = true;
                icon = R.drawable.button21;
            textView2.setText("Press Button to Record");
            stopRecording();
            //System.out.print("playsonu");
            }

            imageButton.setImageDrawable(
                    ContextCompat.getDrawable(getApplicationContext(), icon));


        }
    private boolean paused1 =true;

    public void buttonPressed2(View view) {

       ImageButton imageButton2 = (ImageButton) view;
        int icon;
        //    icon = R.drawable.play;
//        imageButton.setImageDrawable(
        //              ContextCompat.getDrawable(getApplicationContext(), icon));
        if (paused1) {
            paused1 = false;
            icon = R.drawable.pause1;
           // startRecording(view);
            playIt(view);
         //   System.out.print("pausesonu");
        }
        else {
            paused1 = true;
            icon = R.drawable.play1;
           stopp(view);
            // stopRecording(view);
           // System.out.print("playsonu");
        }

        imageButton2.setImageDrawable(
                ContextCompat.getDrawable(getApplicationContext(), icon));}


    public void startRecording() {
        if (!isRecording) {
            //Create MediaRecorder and initialize audio source, output format, and audio encoder
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(fileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            // Starting record time
            recordTime = 0;
            // Show TextView that displays record time
            tv.setVisibility(TextView.VISIBLE);
            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e("LOG_TAG", "prepare failed");
            }
            // Start record job
            mRecorder.start();
            // Change isRecroding flag to true
            isRecording = true;
            // Post the record progress
            handler.post(UpdateRecordTime);

        }
    }

    public void stopRecording() {
        if (isRecording) {
            // Stop recording and release resource
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            // Change isRecording flag to false
            isRecording = false;
            tv.setText("00:00:00");
            showalert();


        }
    }
    public  void doupload(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to share?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                byte[] soundBytes;

                try
                {
                    InputStream inputStream = getContentResolver().openInputStream(Uri.fromFile(new File(fileName)));
                    soundBytes = new byte[inputStream.available()];

                    soundBytes = toByteArray(inputStream);
                    String type="up";
                  //  String fil=name+"_"+m_Text;
                    Log.d("Befre",fileName);
                    soap1.execute(type,name,fileName);

                }catch(Exception e){
                    e.printStackTrace();
                }
            //    soap soap1=new soap();
              //  soap1.execute(name,fileName,soundBytes);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                File from= new File(fileName);
                from.delete();
            }
        });

        builder.show();


    }
    public void showalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter FileName");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
               // fileName = Environment.getExternalStorageDirectory()  +name+"_"+ m_Text+ ".3gp";

                File from= new File(fileName);
                File to = new File(Environment.getExternalStorageDirectory()+"/"+name+"_"+m_Text+".3gp");
                from.renameTo(to);
                fileName=Environment.getExternalStorageDirectory()+"/"+name+"_"+m_Text+".3gp";
                Log.d("change",fileName);
                doupload();

          //      Toast.makeText(this, to.getName(), Toast.LENGTH_SHORT)
            //            .show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                File from= new File(fileName);
                from.delete();
            }
        });

        builder.show();
        //return m_Text;
    }

    public void playIt(View view) {
        // Create MediaPlayer object
        mPlayer = new MediaPlayer();
        // set start time
    //   mPlayer.setDataSource(getApplicationContext(),newUri);
        playTime = 0;
        // Reset max and progress of the SeekBar
        seekBar.setMax(recordTime);
        seekBar.setProgress(0);
        try {
            // Initialize the player and start playing the audio
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
            mPlayer.start();
            // Post the play progress
            handler.post(UpdatePlayTime);
        } catch (IOException e) {
            Log.e("LOG_TAG", "prepare failed");
        }
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
                //progress1 = new ProgressDialog(Tab1Activity.this);
                //progress1.setTitle("Logout");
                //progress1.setMessage("Please wait...");

                getApplicationContext().startActivity(intent);
                //progress1.show();
                Toast.makeText(this, "logout ", Toast.LENGTH_SHORT)
                        .show();
                break;
          /*  case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            */default:
                break;
        }

        return true;
    }
    public  void stopp(View view){mPlayer.pause();}

    Runnable UpdateRecordTime = new Runnable() {
        public void run() {
            if (isRecording) {


                numberOfHours = (recordTime % 86400 ) / 3600 ;
                numberOfMinutes = ((recordTime % 86400 ) % 3600 ) / 60;
                numberOfSeconds = ((recordTime % 86400 ) % 3600 ) % 60  ;
                //String.valueOf(numberOfHours);
                //System.out.print(numberOfHours);
                //System.out.print(numberOfMinutes);
                //System.out.print(numberOfSeconds);
                tv.setText(String.valueOf(numberOfHours)+":"+String.valueOf(numberOfMinutes)+":"+String.valueOf(numberOfSeconds));
              //tv.setText(String.valueOf(recordTime));
//                tv.setText(DateFormat.format(time, noteTS));

                recordTime += 1;
                // Delay 1s before next call
                handler.postDelayed(this, 1000);

            }
        }
    };
    Runnable UpdatePlayTime = new Runnable() {
        public void run() {
            if (mPlayer.isPlaying()) {
               //tv.setText(String.valueOf(playTime));
   //             tv.setText(DateFormat.format(time, noteTS));

                // Update play time and SeekBar
                //numberOfHours = (playTime % 86400 ) / 3600 ;
                numberOfMinutes = ((playTime % 86400 ) % 3600 ) / 60;
                numberOfSeconds = ((playTime % 86400 ) % 3600 ) % 60  ;
                //String.valueOf(numberOfHours);
                //System.out.print(numberOfHours);
                //System.out.print(numberOfMinutes);
                //System.out.print(numberOfSeconds);
                tv1.setText(String.valueOf(numberOfMinutes)+":"+String.valueOf(numberOfSeconds));


                playTime += 1;
                seekBar.setProgress(playTime);
                // Delay 1s before next call
                handler.postDelayed(this, 1000);
            }
        }
    };


}
