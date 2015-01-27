package com.pasupalan;

import com.ahis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;



public class Home extends Activity{
	private String mTitle = "PASUPALAN";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		mTitle = "PASUPALAN";
		getActionBar().setTitle(mTitle);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		
		ImageButton cow = (ImageButton)findViewById(R.id.imageButtonCow);
		ImageButton buff = (ImageButton)findViewById(R.id.imageButtonBuffalo);
	getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		cow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this,CowSubmenu.class);
				startActivity(i);
			}
		});
		
		buff.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Home.this,BuffaloSubMenu.class);
				startActivity(i);
				
			}
		});	   
		
	}

}
