package com.pasupalan;


import com.ahis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Buffalo_sales extends Activity {
	
	Button product, animal;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buffalo_salespurchase);
		
		product = (Button) findViewById(R.id.btnProduct1);
		animal = (Button) findViewById(R.id.btnAnimal1);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

				
		product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Buffalo_sales.this,Buffalo_salesproduct.class);
				startActivity(i);
			}
		});
	
animal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Buffalo_sales.this,Buffalo_salesanimal.class);
				startActivity(i);
			}
		});
}
}

