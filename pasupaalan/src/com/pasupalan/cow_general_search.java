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
import android.app.FragmentManager;
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

public class cow_general_search extends Fragment{
	
	ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	String tagid;
	
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
	

	String hid; String hid1;
	String fid; String fid1;
	String wt1;String wt;
	String age;	String birth;
	String age2;
	
	
	JSONArray general=null;
		
	private static String url = "http://10.0.3.2/ahis/cow_general_search.php";	
	private static String Delete = "http://10.0.3.2/ahis/cow_general_delete.php";
	private static String Save = "http://10.0.3.2/ahis/cow_general_save.php";
	

	public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	// Edit Text
		
		etHerdId = (EditText) getView().findViewById(R.id.etHerdId);
		etFarmerId = (EditText) getView().findViewById(R.id.etFarmerId);
		etWeight = (EditText) getView().findViewById(R.id.etWeight);
		etAge = (EditText) getView().findViewById(R.id.etAge);
		BirthInfo = (EditText) getView().findViewById(R.id.edtBirthInfo);
		
		
		save= (Button)getView().findViewById(R.id.btnSave);
		delete = (Button)getView().findViewById(R.id.btnDelete);
    	
		tagid= getArguments().getString("tagid");
		
		//etHerdId.setText(tagid);
		
		new Search().execute();

		save.setOnClickListener(new View.OnClickListener (){
			public void onClick(View view) {
				new SaveChanges().execute();
				
			}
		});
		 
		delete.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new Delete().execute();
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.cow_general_search, container, false);
          
        return rootView;
    }
	
	//delete asynctask
	class Delete extends AsyncTask<String, String, String> {
		
		protected void onPreExecute() {
   			super.onPreExecute();
   			pDialog = new ProgressDialog(getActivity());					
   			pDialog.setMessage("Deleting..");
   			pDialog.setIndeterminate(false);
   			pDialog.setCancelable(true);
   			pDialog.show();
		}	

   		@Override
		protected String doInBackground(String... args)
		{
   			List<NameValuePair> params = new ArrayList<NameValuePair>();
   			params.add(new BasicNameValuePair("tagid", tagid));
   			
			// getting JSON Object
   			
			JSONObject json = jsonParser.makeHttpRequest(Delete,
					"GET", params);
			
			
			
			// check log cat from response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				if(success==1)
				{
					
					Bundle bundle = new Bundle();
					bundle.putString("tagid", tagid);
					
					Fragment fragment = null; 
					
					fragment = new Cow_search();
					fragment.setArguments(bundle);
				
				if (fragment != null) {
		            FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction()
		                    .replace(R.id.content_frame, fragment).commit();
				}
					
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

class SaveChanges extends AsyncTask<String, String, String> {
		@Override
   		protected void onPreExecute() {
   			super.onPreExecute();
   			pDialog = new ProgressDialog(getActivity());					
   			pDialog.setMessage("Saving..");
   			pDialog.setIndeterminate(false);
   			pDialog.setCancelable(true);
   			pDialog.show();
		}	

   		@Override
		protected String doInBackground(String... args)
		{
   			
			String herdid1 = etHerdId.getText().toString();
			String farmerid1 = etFarmerId.getText().toString();
			String weight1 = etWeight.getText().toString();
			String age1 = etAge.getText().toString();
			

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("herdid1", herdid1));
			params.add(new BasicNameValuePair("farmerid1", farmerid1));
			params.add(new BasicNameValuePair("weight1", weight1));
			params.add(new BasicNameValuePair("age1", age1));
			params.add(new BasicNameValuePair("tagid", tagid));
		
			
							// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(Save,
					"GET", params);
			
			
			// check log cat from response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);
				if(success==1)
				{
					
					Bundle bundle = new Bundle();
					bundle.putString("tagid", tagid);
					
					Fragment fragment = null; 
					
					fragment = new Cow_search();
					fragment.setArguments(bundle);
				
				if (fragment != null) {
		            FragmentManager fragmentManager = getFragmentManager();
		            fragmentManager.beginTransaction()
		                    .replace(R.id.content_frame, fragment).commit();
				}
					
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
   			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
		
			params.add(new BasicNameValuePair("tagid", tagid));
							// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(url,
					"GET", params);
			
			
			// check log cat from response
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

