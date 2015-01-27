package com.pasupalan;

import com.ahis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class BuffaloSubMenu extends Activity {
	
	Button general1, medical1, sales1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buffalo_submenu);
		
		general1 = (Button) findViewById(R.id.btnGeneral1);
		medical1 = (Button) findViewById(R.id.btnMedical1);
		sales1 = (Button) findViewById(R.id.btnSales1);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		general1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(BuffaloSubMenu.this,Buffalo_buffalo.class);
				startActivity(i);
			}
		});
		
		medical1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(BuffaloSubMenu.this,Buffalo_medical.class);
				startActivity(i);
			}
		});

		sales1.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(BuffaloSubMenu.this,Buffalo_sales.class);
				startActivity(i);
			}
		});
	}
	
}
	
	

