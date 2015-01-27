package com.pasupalan;




import com.ahis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class CowSubmenu extends Activity {
	
	Button general, medical, sales;
	
	private String mTitle = "PASUPALAN";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cow_submenu);
		mTitle = "PASUPALAN";
		getActionBar().setTitle(mTitle);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		general = (Button) findViewById(R.id.btnGeneral);
		medical = (Button) findViewById(R.id.btnMedical);
		sales = (Button) findViewById(R.id.btnSales);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		general.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(CowSubmenu.this,Cow.class);
				startActivity(i);
			}
		});
		
		medical.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(CowSubmenu.this,Cow_medical.class);
				startActivity(i);
			}
		});

		sales.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(CowSubmenu.this,Cow_sales.class);
				startActivity(i);
			}
		});
	}
	
}
	
	

