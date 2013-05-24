package com.practice.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
 

public class WebActivity extends Activity
{
      
   
    TextView source;
    TextView result;
 
   
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.web_activity);
               
                    String url = "http://google.com/"; // Random site with XML data
 
                    String[] tags = new String[] { "ARTIST" };
                    String code = "";
                    String parsed = "";
                      try
                    {
                    	/*SrcGrabber mGrabber = new SrcGrabber();
                        code = mGrabber.grabSource(url);
                        System.out.println("source code =" +code);
                        parsed = mGrabber.parseTags(code, tags);*/
                    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                    	StrictMode.setThreadPolicy(policy); 
                    	
                    	
                    	HttpClient httpclient = new DefaultHttpClient(); // Create HTTP Client
                    	HttpGet httpget = new HttpGet("http://www.google.com/"); // Set the action you want to do
                    	HttpResponse response = httpclient.execute(httpget); // Executeit
                    	HttpEntity entity = response.getEntity(); 
                    	InputStream is = entity.getContent(); // Create an InputStream with the response
                    	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                    	StringBuilder sb = new StringBuilder();
                    	String line = null;
                    	while ((line = reader.readLine()) != null) // Read line by line
                    	    sb.append(line + "\n");

                    	code = sb.toString(); // Result is here

                    	is.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        return;
                    }
 
                    TextView source = (TextView) findViewById(R.id.source);
                    source.setText(code);
 
                   /* TextView result = (TextView) findViewById(R.id.result);
                    result.setText(parsed);*/
                   
        }      
}