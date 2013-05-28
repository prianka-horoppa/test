package com.example.jsobreader;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



public class DescriptionActivity extends Activity
{
	String name;
	String description;
	
	TextView nameview;
	TextView descriptionview;
	
	private static final String TAG_NAME = "name";
	private static final String TAG_DESCRIPTION = "description";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_activity);
		
		TextView nameview = (TextView) findViewById(R.id.nameview);
		TextView descriptionview = (TextView) findViewById(R.id.descriptionview);
		
		Intent i = getIntent();
		name = i.getStringExtra(TAG_NAME);
		description = i.getStringExtra(TAG_DESCRIPTION);
		System.out.println(""+ name);
		System.out.println (""+description);
		
		nameview.setText(name);
		descriptionview.setText(description);
		
		
		
		
	}

}
