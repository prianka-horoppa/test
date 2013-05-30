package com.prianka.homeapplication;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MenuPageActivity extends Activity 
{
    private static final String TAG_DATA = "data";
    private static final String TAG_ENTRY = "entry";
    private static final String TAG_ID = "id";
    private static final String TAG_IMAGE = "image";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayList<EntryDetails> image_details = GetEntriesFromJsonFile();
      
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        ItemListBaseAdapter itemListBaseAdapter = new ItemListBaseAdapter(this, image_details);
        lv1.setAdapter(itemListBaseAdapter);
        //itemListBaseAdapter.notifyDataSetChanged();
        //lv1.invalidateViews();
      
        
        lv1.setOnItemClickListener(new OnItemClickListener() 
        {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
        	{ 
        		Object o = lv1.getItemAtPosition(position);
            	EntryDetails obj_itemDetails = (EntryDetails)o;
        		//Toast.makeText(ListViewImagesActivity.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        		String name = obj_itemDetails.getName();
        		//System.out.println(""+o);
        		switch (position)
        		{
        		case 0:
        			Intent i1 = new Intent(getApplicationContext(),HomePageActivity.class);
        			startActivity(i1);
        			
        			break;
        		case 1:
        			System.out.println("this is for showing list");
        			Intent in = new Intent(getApplicationContext(),ListShowActivity.class);
        			startActivity(in);
        			break;
        		case 2:
        		
        			Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
        			startActivity(i);
        			break;
        		}
        		
        		
        	}  
        });
    }
    
    private ArrayList<EntryDetails> GetEntriesFromJsonFile()
    {
    	ArrayList<EntryDetails> results = new ArrayList<EntryDetails>();
   

   	
		InputStream is = getResources().openRawResource(R.raw.firstpage);
		
		JSONParser jParser = new JSONParser();
		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromFile(is);
		if(json==null)
		{
		 System.out.println("json null");
		}
		
		try {
			// Getting Array of Contacts
			JSONArray entries = json.getJSONArray(TAG_DATA);
			
			// looping through All Contacts
			for(int i = 0; i < entries.length(); i++)
			{
		    	EntryDetails item_details = new EntryDetails();
		    	
				JSONObject c = entries.getJSONObject(i);
				
				// Storing each json item in variable
				String entry = c.getString(TAG_ENTRY);
				
		    	item_details.setName(entry);
		    	item_details.setImageNumber(i+1);
		    	results.add(item_details);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
    	return results;
    }
}