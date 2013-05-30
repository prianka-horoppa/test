package com.prianka.homeapplication;


import android.app.Activity;
import android.content.DialogInterface;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends Activity implements OnClickListener
{
    EditText username;
    EditText password;
    Button ok;
    
    protected void onCreate(Bundle savedInstanceState)
    {
   
            //sending current application state to the parent activity class    	
            super.onCreate(savedInstanceState);
            //set the current gui xml layout of the activity 
            setContentView(R.layout.registration_activity);
            username = (EditText) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);
            ok = (Button) findViewById(R.id.ok);
            ok.setOnClickListener(this);
            
    }
	
    public void onClick(View v) 
	{
		
		switch(v.getId())
		{
		case R.id.ok:
			System.out.println("Pressed button ok");
			Intent in = new Intent(getApplicationContext(), MenuPageActivity.class);
			startActivity(in);
			break;
			
		}
		
	}

	


	

} 


