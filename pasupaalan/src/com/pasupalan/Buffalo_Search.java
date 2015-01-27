package com.pasupalan;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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

public class Buffalo_Search extends Fragment{
	// Progress Dialog
			private ProgressDialog pDialog;

			JSONParser jsonParser = new JSONParser();
			
			EditText etTagId;
			Button search;
			String tagid1;
			private static final String TAG_SUCCESS = "success";
			private static final String TAG_TID = "tid";
			
			private static String url_create_product = "http://10.0.3.2/ahis/buffalo_general_search.php";

			public void onActivityCreated(Bundle savedInstanceState) {
		    	super.onActivityCreated(savedInstanceState);
		    	
		    	// Edit Text
				etTagId = (EditText) getView().findViewById(R.id.etTagId1);
				search= (Button)getView().findViewById(R.id.btnSearch1);
				
				
		    	
				search.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {
						
						tagid1 = etTagId.getText().toString(); 
						Bundle bundle = new Bundle();
						bundle.putString("tagid", tagid1);
						
						Fragment fragment = null; 
						
						fragment = new cow_general_search();
						fragment.setArguments(bundle);
					
					if (fragment != null) {
			            FragmentManager fragmentManager = getFragmentManager();
			            fragmentManager.beginTransaction()
			                    .replace(R.id.content_frame, fragment).commit();
					}
						
						
						// creating new product in background thread
					}
				});
				 
			}		    

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.buffalo_search, container, false);
          
        return rootView;
    }
		
}
