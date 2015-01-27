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

public class Buffalo_general extends Fragment {
	
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		EditText etTagId1;
		EditText etHerdId1;
		EditText etFarmerId1;
		EditText etWeight1;
		EditText etAge1;
		RadioButton rbPurchased1; 
		RadioButton rbRaised1; 
		Button submit1;
		// url to create new product
		
		private static String URL = "http://10.0.3.2/ahis/buffalo_general.php";
		
		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		
		@Override
	
	
	public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	// Edit Text
		etTagId1 = (EditText) getView().findViewById(R.id.etTagId1);
		etHerdId1 = (EditText) getView().findViewById(R.id.etHerdId1);
		etFarmerId1 = (EditText) getView().findViewById(R.id.etFarmerId1);
		etWeight1 = (EditText) getView().findViewById(R.id.etWeight1);
		etAge1 = (EditText) getView().findViewById(R.id.etAge1);
		rbPurchased1 = (RadioButton)getView().findViewById(R.id.rbPurchased1);
		rbRaised1 = (RadioButton)getView().findViewById(R.id.rbRaised1);
		submit1= (Button)getView().findViewById(R.id.btnSubmit1);
    	
		submit1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new AddnewBuffalo().execute();
			}
		});
	}
		
       class AddnewBuffalo extends AsyncTask<String, String, String> {

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
       
   		
   		protected String doInBackground(String... args) {
			String tagid1 = etTagId1.getText().toString();
			String herdid1 = etHerdId1.getText().toString();
			String farmerid1 = etFarmerId1.getText().toString();
			String weight1 = etWeight1.getText().toString();
			String age1 = etAge1.getText().toString();
			String birthinfo1;
			if(rbRaised1.isChecked()){
				birthinfo1 = rbRaised1.getText().toString();
			}else{
				birthinfo1 = rbPurchased1.getText().toString();
			}
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tagid1", tagid1));
			params.add(new BasicNameValuePair("herdid1", herdid1));
			params.add(new BasicNameValuePair("farmerid1", farmerid1));
			params.add(new BasicNameValuePair("weight1", weight1));
			params.add(new BasicNameValuePair("age1", age1));
			params.add(new BasicNameValuePair("birthInfo1", birthinfo1));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(URL,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					//Intent i = new Intent(getActivity().getBaseContext(), Cow.class);
					//startActivity(i);
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
	
		View rootView = inflater.inflate(R.layout.buffalo_add, container, false);
		return rootView;
	}
	

}
   