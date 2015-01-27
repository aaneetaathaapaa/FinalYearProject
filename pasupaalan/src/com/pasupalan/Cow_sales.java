package com.pasupalan;


import com.ahis.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Cow_sales extends Activity {
	
	Button product, animal;
	private String mTitle = "PASUPALAN";


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cow_salespurchase);
		
		mTitle = "PASUPALAN";
		getActionBar().setTitle(mTitle);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));
		
		
		product = (Button) findViewById(R.id.btnProduct);
		animal = (Button) findViewById(R.id.btnAnimal);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		product.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Cow_sales.this,cow_salesproduct.class);
				startActivity(i);
			}
		});
	
		animal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Cow_sales.this,cow_salesanimal.class);
				startActivity(i);
			}
		});
		
}
}

