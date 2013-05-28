package com.example.jsobreader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewActivity extends ListActivity
{
	  
	    private static final String TAG_DATA = "data";
	    private static final String TAG_NAME = "name";
	    private static final String TAG_ID = "id";
	    private static final String TAG_DESCRIPTION = "description";
	    
		// contacts JSONArray
		JSONArray entries = null;
	    
	    
	    private ListView mainListView ;  
	    private ArrayAdapter<String> listAdapter ;  
	    
		// Hashmap for ListView
		ArrayList<HashMap<String, String>> entryList = new ArrayList<HashMap<String, String>>();    
	    
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) 
	    {  
		    super.onCreate(savedInstanceState);  
		    setContentView(R.layout.list_view_activity);  
	     
		    mainListView = getListView();  
		  
		    // Create and populate a List of planet names.    
		    ArrayList<String> planeList = new ArrayList<String>();   
		      
		    // Create ArrayAdapter using the planet list.  
		    listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planeList);  	       
		    listAdapter.add( "none" );  
		
		    // Set the ArrayAdapter as the ListView's adapter.  
		    mainListView.setAdapter( listAdapter );  	   
		    
		    JsonReaderTask jtask= new JsonReaderTask();
		    jtask.execute();
	  }  
	    
	  class JsonReaderTask extends AsyncTask<String, String, String> 
		{
			// Creating JSON Parser object
			 // Creating JSON Parser instance
			 JSONParser jParser;
			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() 
			{
				super.onPreExecute();
				System.out.println("on preexecute");
			}

			/**
			 * getting All products from url
			 * */
			protected String doInBackground(String... args) 
			{
				InputStream is = ListViewActivity.this.getResources().openRawResource(R.raw.test);
				
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
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String description = c.getString(TAG_DESCRIPTION);
	            		// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();
						
						// adding each child node to HashMap key => value
						map.put(TAG_ID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_DESCRIPTION, description);


						// adding HashList to ArrayList
						entryList.add(map);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}				
		        
		        return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) 
			{
				// updating UI from Background Thread
				runOnUiThread(new Runnable() 
				{
					public void run() 
					{
						/**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(ListViewActivity.this, entryList,
								R.layout.simplerow,
								new String[] { TAG_NAME}, new int[] {
										R.id.name});

						setListAdapter(adapter);
						((SimpleAdapter)adapter).notifyDataSetChanged();					
						// selecting single ListView item
						ListView lv = getListView();

						// Launching new screen on Selecting Single ListItem
						lv.setOnItemClickListener(new OnItemClickListener() 
						{
							@Override						
							public void onItemClick(AdapterView<?> parent, View view, int position, long id)
							{
								// getting values from selected ListItem
								String description = entryList.get(position).get(TAG_DESCRIPTION);
								String name = entryList.get(position).get(TAG_NAME);
								
								// Starting new intent
								Intent in = new Intent(getApplicationContext(), DescriptionActivity.class);
								in.putExtra(TAG_DESCRIPTION, description);
								in.putExtra(TAG_NAME, name);
								startActivity(in);
							}
						});	
							
					}
				});				
			}

		}
	    
	


}
