package com.prianka.homeapplication;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ListShowActivity extends Activity
{

    private static final String TAG_DATA = "data";
    private static final String TAG_NAME = "name";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_ID = "id";
    private static final String TAG_IMAGE = "image";
    ArrayList<HashMap<String, String>> entryList = new ArrayList<HashMap<String, String>>(); 
    
 
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
  
        ArrayList<EntryDetails> image_details = GetEntriesFromJsonFile();
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        lv1.invalidateViews();
        
        lv1.setOnItemClickListener(new OnItemClickListener() 
        {
        	@Override
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) 
        	{ 
        		
        		// getting values from selected ListItem
				/*String description = image_details.get(position).get(TAG_DESCRIPTION);
				String name = results.get(position).get(TAG_NAME);
				System.out.println(""+ name);
				System.out.println(""+description);*/
        		Object o = lv1.getItemAtPosition(position);
            	EntryDetails obj_itemDetails = (EntryDetails)o;
            	String name = obj_itemDetails.getName();
            	String description = obj_itemDetails.getDescription();
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), DescriptionActivity.class);
				in.putExtra(TAG_DESCRIPTION, description);
				in.putExtra(TAG_NAME, name);
				startActivity(in);

        	}  
        });
    }
    
    private ArrayList<EntryDetails> GetEntriesFromJsonFile()
    {
        
    	ArrayList<EntryDetails> results = new ArrayList<EntryDetails>();
   	    InputStream is = getResources().openRawResource(R.raw.list);
		
		JSONParser jParser = new JSONParser();
		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromFile(is);
		if(json==null)
		{
		 System.out.println("json null");
		}
		
		try {
			// Getting Array of Contacts
			JSONArray entriesforlist = json.getJSONArray(TAG_DATA);
		    // looping through All Contacts
			for(int i = 0; i < entriesforlist.length(); i++)
			{
		    	EntryDetails item_details = new EntryDetails();
				JSONObject c = entriesforlist.getJSONObject(i);
				
				// Storing each json item in variable
				String entry = c.getString(TAG_NAME);
				String description = c.getString(TAG_DESCRIPTION);
				
		    	item_details.setDescription(description);
				item_details.setName(entry);
		    	item_details.setImageNumber(i+1);
		    	results.add(item_details);
		    	//entryList.addAll(item_details);
		    }
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		
    	return results;
    }

}
