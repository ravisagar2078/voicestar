package com.example.ravisagar.hci;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Ravi Sagar on 5/25/2017.
 */
public class soap extends AsyncTask<String, Void, String> {
/*
  public interface Aaa{

      void processfinish(String output);
  }
    public  Aaa dele=null;
    public soap(Aaa dele)
    {
        this.dele=dele;
    }*/
    String username,password;
    String fn;
    Context context;
    String playlist;

    soap(Context ctx)
    {
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        CallSoap cs = new CallSoap();
        String t=params[0];

        if(t.equals("login"))
        {
            username=params[1];
            password=params[2];
            return (cs.login(username,password));
        }

        else if(t.equals("up"))
        {
            username=params[1];
            fn=params[2];
            // convertin into bytes
            return  (cs.Upload(username,fn));
        }
        else if(t.equals("getlist"))
        {

            return  (cs.getrecording());

        }
        return null;
    }
    public String getPlayList()
    {
        return playlist;
    }
    @Override
    protected void onPostExecute(String result) {
//        tv.setText(result);

       // Log.d("key",result);
        //           AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
  /*          Intent intent=new Intent(context, Dashboard.class);
            intent.putExtra("abhi", username);
            context.startActivity(intent);
*/

            if (result.equals("true")) {
           //     dele.processfinish(result);
                Intent intent=new Intent(context, Dashboard.class);
                intent.putExtra("abhi", username);

                context.startActivity(intent);

            }
            else if(result.contains("tab"))
            {
//                playlist=result;
             //   GlobalClass g = (GlobalClass)context;
           //     g.setName2(result);
                Log.d("inpost",result);
            //  dele.processfinish(result);
                //  String q=result.toString();
                //Intent intent1=new Intent(context, Tab2Activity.class);
               //intent1.putExtra("playlist", q);
                //context.startActivity(intent1);
            }
        else
            {}

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

