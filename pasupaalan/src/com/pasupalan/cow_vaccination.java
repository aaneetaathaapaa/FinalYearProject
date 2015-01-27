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

public class cow_vaccination extends Fragment {
	
	
	// Progress Dialog
		private ProgressDialog pDialog;

		JSONParser jsonParser = new JSONParser();
		
		EditText etTagId;
		EditText edtDate;
		EditText edtAmount;
		EditText edtVaccination;
		
		Button btnAdd;
		
		// url to create new product
		private static String url_create_product = "http://10.0.3.2/ahis/cow_medical_vaccination.php";
		
		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		
		@Override
	
	
	public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	
    	
    	// Edit Text
		etTagId = (EditText) getView().findViewById(R.id.edtTagId);
		edtDate = (EditText) getView().findViewById(R.id.edtDate);
		edtAmount = (EditText) getView().findViewById(R.id.edtAmount);
		edtVaccination = (EditText) getView().findViewById(R.id.edtVaccination);
		
		btnAdd= (Button) getView().findViewById(R.id.btnAdd);
    	
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// creating new product in background thread
				new AddcowVaccination().execute();
			}
		});
		 
	}
		
       class AddcowVaccination extends AsyncTask<String, String, String> {

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
			String etDate = edtDate.getText().toString();
			String etAmount = edtAmount.getText().toString();
			String etVaccination = edtVaccination.getText().toString();
			
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tagid", tagid));
			params.add(new BasicNameValuePair("etDate", etDate));
			params.add(new BasicNameValuePair("etAmount", etAmount));
			params.add(new BasicNameValuePair("etVaccination", etVaccination));
			
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
					Intent i = new Intent(getActivity(),CowSubmenu.class);
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
	
		View rootView = inflater.inflate(R.layout.cow_vaccination, container, false);
		return rootView;
	}
	

}
   /*package com.ahis;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class cow_vaccination extends Fragment{
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.cow_vaccination, container, false);
          
        return rootView;
    }

}
*/