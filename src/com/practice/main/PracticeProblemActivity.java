package com.practice.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PracticeProblemActivity extends Activity implements OnClickListener
{
	
	Button list_button;
	Button web_button;
	Button twiter_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.practice_problem_activity);
		
		list_button = (Button) findViewById(R.id.list_button);
		web_button = (Button) findViewById(R.id.web_button);
		twiter_button = (Button) findViewById(R.id.twiter_button);
		list_button.setOnClickListener(this);
		web_button.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		
		{
		case R.id.list_button:
			System.out.println("List adapter viewing");
			Intent i = new Intent(PracticeProblemActivity.this,ListViewActivity.class );
			startActivity(i);
			break;
			
		case R.id.web_button:
			
			 String url = "http://www.xmlfiles.com/examples/cd_catalog.xml" ;// Random site with XML data

			    String[] tags = new String[] { "ARTIST" };
			    String code = "";
			    String parsed = "";

			    try
			    {
			        WebActivity mGrabber;
					code = mGrabber.grabSource(url);
			        parsed = mGrabber.parseTags(code, tags);
			    }
			    catch (Exception e)
			    {
			        e.printStackTrace();
			        return;
			    }

			    TextView source = (TextView) findViewById(R.id.source);
			    source.setText(code); 

			    TextView result = (TextView) findViewById(R.id.result);
			    result.setText(parsed);
			System.out.println("Web page viewing");
			Intent intent = new Intent(PracticeProblemActivity.this,WebActivity.class );
			startActivity(intent);
			break;
		} 
		
	}
 
}