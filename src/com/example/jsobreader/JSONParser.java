package com.example.jsobreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser 
{
	    JSONObject jObj = null;
	    String json = "";
	 
	    // constructor
	    public JSONParser() 
	    {

	    }
	 
	    public JSONObject getJSONFromFile(InputStream is) 
	    {	 	         
	        try
	        {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            json = sb.toString();
	        } 
	        
	        catch (Exception e)
	        {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }
	 
	        // try parse the string to a JSON object
	        try
	        {
	            jObj = new JSONObject(json);
	        } 
	        catch (JSONException e)
	        {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	 
	        // return JSON String
	        return jObj;
	 
	    }

}
