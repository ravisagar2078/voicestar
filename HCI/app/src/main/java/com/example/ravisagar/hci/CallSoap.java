package com.example.ravisagar.hci;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Ravi Sagar on 5/7/2017.
 */
public class CallSoap {

    public final String SOAP_ACTION = "http://tempuri.org/Add";

    public  final String OPERATION_NAME = "Add";

    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public  final String SOAP_ADDRESS = "http://192.168.0.101/vrService/Service1.asmx";
    public CallSoap()
    {
    }
    public String getGret()
    {
        String sop_action = "http://tempuri.org/HelloWorld";
        String op_name = "HelloWorld";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,op_name);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(sop_action, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        Log.d("SOAP RESPONSE:",response.toString());
        return response.toString();

    }

    public String login(String user,String pass)
    {
        String sop_action = "http://tempuri.org/isuser";
        String op_name = "isuser";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,op_name);
        //---------------------------
        request.addProperty("username",user);
        request.addProperty("pass",pass);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        Log.d("SOAP RESPONSE","insi");
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(sop_action, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        Log.d("SOAP RESPONSE:",response.toString());
        return response.toString();

    }

    public String getrecording()
    {
        String sop_action = "http://tempuri.org/update_table_And";
        String op_name = "update_table_And";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,op_name);
        //---------------------------
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        Log.d("SOAP RESPONSE","deepak");
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        String jsonResult;
        ArrayList<Playlist> arrayList = new ArrayList<>();
        try
        {
            httpTransport.call(sop_action, envelope);
            response = envelope.getResponse();
            //SoapPrimitive result=(SoapPrimitive)envelope.getResponse();
            Log.d("jason1", response.toString());
            jsonResult=response.toString();
            StringBuilder builder = new StringBuilder("tab/");
            try
            {
                JSONObject jsonResponse = new JSONObject(jsonResult);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("tab");

                for (int i = 0; i < jsonMainNode.length(); i++)
                {
                    JSONObject row = jsonMainNode.getJSONObject(i);
                    final Playlist route = new Playlist(row.optString("rec"),row.optInt("Likes"));
                   // System.out.println("mmmm"+row.optString("rec")+"--"+row.optInt("Likes"));
                    builder.append(row.optString("rec")+"-"+row.optInt("Likes")+"/");
                    arrayList.add(route);
                }
                System.out.print("builder "+builder.toString());
                return builder.toString();
            }
            catch (JSONException e)
            {
                //Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                  //      Toast.LENGTH_LONG).show();

            }

//            JSONArray JSONArr = null;
//            JSONObject JSONObj = null;
//            StringBuilder bundleresult=new StringBuilder();
//            if (be.startsWith("["))
//            { // if JSON string is an array
//
//                JSONArr = new JSONArray(be);
//
//                System.out.println("length" + JSONArr.length());
//                for (int i = 0; i < JSONArr.length(); i++)
//                {
//                    JSONObj = (JSONObject) JSONArr.get(i);
//                    bundleresult.append(String.valueOf(i)+"-"+JSONObj.toString()+"\n");
//                    System.out.println("mmmm"+String.valueOf(i)+"-"+JSONObj.toString()+"\n");
//                }
//
//            }
         //   System.out.println("bundle result is"+bundleresult);

       //     return bundleresult.toString();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        //Log.d("SOAP RESPONSE:",response.toString());
        return null;

    }
    public byte[] toByteArray(File in) throws IOException {
        /*  ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read = 0;

        byte[] buffer = new byte[1024];
        while (read != -1)
        {
            read = in.read(buffer);
            if (read != -1)
                out.write(buffer,0,read);
        }
        out.close();
        return out.toByteArray();*/
        byte[] bytArr = new byte[(int)in.length()];
        FileInputStream fis = new FileInputStream(in);
        fis.read();
        fis.close();
        return bytArr;

    }


    public String Upload(String user,String filename)
    {
        byte[] soundBytes=null;

        try
        {
            //InputStream inputStream = new FileInputStream(new File(filename));
           // soundBytes = new byte[inputStream.available()];
            soundBytes = toByteArray(new File(filename));
            System.out.println("gggg"+soundBytes);
        }catch(Exception e){
            e.printStackTrace();
        }

        String sop_action = "http://tempuri.org/Add_Recording";
        String op_name = "Add_Recording";
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,op_name);
        //---------------------------
        String[] array = filename.split("/");
        Log.d("fff",filename);
        Log.d("fffddd",array[4]);
        String[] array1=array[4].split(".3gp");
        Log.d("fffddd",array1[0]);
        request.addProperty("filename",array1[0]);
        request.addProperty("user_name",user);
        request.addProperty("clip",soundBytes);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;
        //Log.d("SOAP RESPONSE","insi");
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(sop_action, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        Log.d("SOAP RESPONSE:",response.toString());
        return response.toString();

    }
  /*  public String Call(int a,int b)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,OPERATION_NAME);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("a");
        pi.setValue(a);
        pi.setType(Integer.class);
        request.addProperty(pi);
        pi=new PropertyInfo();
        pi.setName("b");
        pi.setValue(b);
        pi.setType(Integer.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }*/
}

