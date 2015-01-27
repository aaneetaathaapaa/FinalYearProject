package com.pasupalan;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahis.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cow_salesproduct extends Activity {
	private String mTitle = "PASUPALAN";


	// Progress Dialog
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	
	EditText tagid;
	EditText soldamount;
	EditText homeuse;
	EditText milkproduced;
	EditText dailysales;

	// url to create new product
	private static String url_create_product = "http://10.0.3.2/ahis/cow_sales_product.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cow_salesproduct);
		mTitle = "PASUPALAN";
		getActionBar().setTitle(mTitle);
		
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));

		// Edit Text
		
		tagid = (EditText) findViewById(R.id.edtTagId);
		milkproduced = (EditText) findViewById(R.id.edtMilkProduced);
		homeuse = (EditText) findViewById(R.id.edtHomeUse);
		soldamount =(EditText) findViewById(R.id.edtSoldAmount); 
		dailysales = (EditText) findViewById(R.id.edtdailysales);

		// Create button
		Button add = (Button) findViewById(R.id.btnSubmit);

		// button click event
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new CreateNewProduct().execute();
			}
		});
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			pDialog = new ProgressDialog(cow_salesproduct.this);
			pDialog.setMessage("Inserting..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		protected String doInBackground(String... args) {
			
			String Tagid = tagid.getText().toString();
			String Milkproduced = milkproduced.getText().toString();
			String HomeUse = homeuse.getText().toString();
			String Soldamount = soldamount.getText().toString();
			String Dailysales = dailysales.getText().toString();
			
			
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("Tagid", Tagid));
			params.add(new BasicNameValuePair("Milkproduced", Milkproduced));
			params.add(new BasicNameValuePair("HomeUse", HomeUse));
			params.add(new BasicNameValuePair("Soldamount", Soldamount));
			params.add(new BasicNameValuePair("Dailysales", Dailysales));
			
			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url_create_product,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), cow_salesproduct.class);
					startActivity(i);
					
					// closing this screen
					finish();
				} else {
					// failed to create product
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
