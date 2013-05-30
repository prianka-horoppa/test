package com.prianka.homeapplication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageActivity extends Activity
{
	TextView statusView;
	TextView descriptionView;
	ImageView imageView;
	
	String status;
	String description;

	private static final String TAG_DATA = "data";
	private static final String TAG_STATUS = "status";
	private static final String TAG_DESCRIPTION = "description";
	JSONArray entries = null;
	
	ArrayList<HashMap<String, String>> entryList = new ArrayList<HashMap<String, String>>(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page_activity);
		
		//ArrayList<EntryDetails> image_details = GetEntriesFromJsonFile();
		
		statusView = (TextView) findViewById(R.id.status);
		descriptionView = (TextView) findViewById(R.id.description);
		imageView = (ImageView) findViewById(R.id.imageView1);
		
		JsonReaderTask jtask= new JsonReaderTask();
	    jtask.execute();
	    
	   
		
		
	
	}
	
	class JsonReaderTask extends AsyncTask<String, String, String> 
	{

		JSONParser jParser;
		
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			System.out.println("on preexecute");
		}
		
		
		@Override
		protected String doInBackground(String... params) 
		{
			InputStream is = HomePageActivity.this.getResources().openRawResource(R.raw.home);
			
			jParser = new JSONParser();
			// getting JSON string from URL
			JSONObject json = jParser.getJSONFromFile(is);
			
			try {
				// Getting Array of Contacts
				entries = json.getJSONArray(TAG_DATA);
				
				// looping through All Contacts
				for(int i = 0; i < entries.length(); i++)
				{
					JSONObject c = entries.getJSONObject(i);
					
					// Storing each json item in variable
					
					 status = c.getString(TAG_STATUS);
					 description = c.getString(TAG_DESCRIPTION);
            		
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}				
	        
	        return null;
			
		}
		
		protected void onPostExecute(String file_url) 
		{
			statusView.setText(status);
			descriptionView.setText(description);
			imageView.setImageResource(R.drawable.bb1);
			
		}
		
	}
	
	 
}
