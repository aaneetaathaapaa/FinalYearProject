package com.pasupalan;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahis.R;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/*import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;*/
import android.widget.EditText;
import android.widget.RadioButton;


/*import android.widget.CheckBox;
import android.widget.CompoundButton;*/

	public class Cow_general extends Fragment {
		
		
		// Progress Dialog
			private ProgressDialog pDialog;
			

			JSONParser jsonParser = new JSONParser();
			
			EditText etTagId;
			EditText etHerdId;
			EditText etFarmerId;
			EditText etWeight;
			EditText etAge;
			RadioButton rbPurchased; 
			RadioButton rbRaised; 
			Button submit;
			// url to create new product
			private static String url_create_product = "http://127.0.0.1/ahis/cow_general.php";
			
			// JSON Node names
			private static final String TAG_SUCCESS = "success";
			
			@Override
		
		
		public void onActivityCreated(Bundle savedInstanceState) {
	    	super.onActivityCreated(savedInstanceState);
	    	
	    	// Edit Text
			etTagId = (EditText) getView().findViewById(R.id.etTagId);
			etHerdId = (EditText) getView().findViewById(R.id.etHerdId);
			etFarmerId = (EditText) getView().findViewById(R.id.etFarmerId);
			etWeight = (EditText) getView().findViewById(R.id.etWeight);
			etAge = (EditText) getView().findViewById(R.id.etAge);
			rbPurchased = (RadioButton)getView().findViewById(R.id.rbPurchased);
			rbRaised = (RadioButton)getView().findViewById(R.id.rbRaised);
			submit= (Button)getView().findViewById(R.id.btnSubmit);
	    	
			submit.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View view) {
					// creating new product in background thread
					new AddnewCow().execute();
				}
			});
			 
		}
			
	       class AddnewCow extends AsyncTask<String, String, String> {
	
	   		/**
	   		 * Before starting background thread Show Progress Dialog
	   		 * */
	   		@Override
	   		protected void onPreExecute() {
	   			super.onPreExecute();
	   			pDialog = new ProgressDialog(getActivity());					
	   			pDialog.setMessage("Inserting..");
	   			pDialog.setIndeterminate(false);
	   			pDialog.setCancelable(true);
	   			pDialog.show();
	   		}
	       
	   		
	   		
	
	   		@Override
			protected String doInBackground(String... args)
	   		{
				String tagid = etTagId.getText().toString();
				String herdid = etHerdId.getText().toString();
				String farmerid = etFarmerId.getText().toString();
				String weight = etWeight.getText().toString();
				String age = etAge.getText().toString();
				String birthinfo;
				if(rbRaised.isChecked()){
					birthinfo = rbRaised.getText().toString();
				}else{
					birthinfo = rbPurchased.getText().toString();
				}
				
	
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("tagid", tagid));
				params.add(new BasicNameValuePair("herdid", herdid));
				params.add(new BasicNameValuePair("farmerid", farmerid));
				params.add(new BasicNameValuePair("weight", weight));
				params.add(new BasicNameValuePair("age", age));
				params.add(new BasicNameValuePair("birthInfo", birthinfo));
	
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
						Intent i = new Intent(getActivity(), Cow.class);
						startActivity(i);
						
						getActivity().finish();
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
	    
	    
		
		@Override 
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState1) {
		
			View rootView = inflater.inflate(R.layout.cow_add, container, false);
			return rootView;
		}
		
	
	}
	   