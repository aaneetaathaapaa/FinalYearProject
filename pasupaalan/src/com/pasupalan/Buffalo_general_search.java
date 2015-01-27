package com.pasupalan;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahis.R;
import com.pasupalan.Cow_general.AddnewCow;

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
import android.widget.EditText;
import android.widget.RadioButton;

public class Buffalo_general_search extends Fragment{
	
	ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	String tagid1;
	
	EditText etHerdId;
	EditText etFarmerId;
	EditText etWeight;
	EditText etAge;
	EditText BirthInfo;
	Button save;
	Button delete;
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_HERDID = "herdid";
	private static final String TAG_FARMERID = "farmerid";
	private static final String TAG_WEIGHT = "weight";
	private static final String TAG_AGE = "age";
	private static final String TAG_BIRTHINFO = "birthinfo";
	private static final String TAG_GENERAL = "general";
	

	String hid;
	String fid;
	String wt;
	String age;
	String birth;
	
	JSONArray general=null;
	
	
	private static String url = "http://10.0.3.2/ahis/cow_general_search.php";	

	public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	// Edit Text
		
		etHerdId = (EditText) getView().findViewById(R.id.etHerdId1);
		etFarmerId = (EditText) getView().findViewById(R.id.etFarmerId1);
		etWeight = (EditText) getView().findViewById(R.id.etWeight1);
		etAge = (EditText) getView().findViewById(R.id.etAge1);
		BirthInfo = (EditText) getView().findViewById(R.id.edtBirthInfo1);
		
		
		save= (Button)getView().findViewById(R.id.btnSave);
		delete = (Button)getView().findViewById(R.id.btnDelete);
    	
		tagid1= getArguments().getString("tagid1");
		
		//etHerdId.setText(tagid);
		
		new Search().execute();

			
		 
	}

	@Override
	


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.buffalo_general_search, container, false);
          
        return rootView;
    }
	

	
	class Search extends AsyncTask<String, String, String> {

   		/**
   		 * Before starting background thread Show Progress Dialog
   		 * */
   		@Override
   		protected void onPreExecute() {
   			super.onPreExecute();
   			pDialog = new ProgressDialog(getActivity());					
   			pDialog.setMessage("Searching..");
   			pDialog.setIndeterminate(false);
   			pDialog.setCancelable(true);
   			pDialog.show();
   		}
       
   		
   		

   		@Override
		protected String doInBackground(String... args)
		{
   			
			//Tagid = tagid.getS().toString();
			/*String farmerid= etFarmerId.getText().toString();
			String weight = etWeight.getText().toString();
			String age = etAge.getText().toString();
			String birthinfo = BirthInfo.getText().toString();
			*/
							// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
		
			params.add(new BasicNameValuePair("tagid1", tagid1));
							// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url,
					"GET", params);
			
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				
				general  = json.getJSONArray(TAG_GENERAL);
				 for(int i = 0; i<general.length(); i++){
					 JSONObject obj = general.getJSONObject(i);
					 
					 hid= obj.getString(TAG_HERDID);
					 fid = obj.getString(TAG_FARMERID);
					 wt = obj.getString(TAG_WEIGHT);
					 age = obj.getString(TAG_AGE);
					 birth = obj.getString(TAG_BIRTHINFO);
					 
					 
					 
				 }
				int success = json.getInt(TAG_SUCCESS);
				
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
			etHerdId.setText(hid);
			etFarmerId.setText(fid);
			etWeight.setText(wt);
			etAge.setText(age);
			BirthInfo.setText(birth);
			
		}

	
	}

	

}

