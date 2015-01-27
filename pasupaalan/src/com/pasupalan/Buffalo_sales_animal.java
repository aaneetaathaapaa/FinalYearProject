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
import android.widget.EditText;

public class Buffalo_sales_animal extends Fragment {
	
	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();
	
	EditText edtSoldTagId1;
	EditText edtBuyerFarmerId1;
	EditText edtSellingPrice1;
	EditText edtSoldDate1;
	Button btnAdd1;
	// url to create new product
	private static String url_create_product = "http://10.0.3.2/ahis/buffalo_animal_sales.php";
	
	// JSON Node names
	private static final String TAG_SUCCESS = "success";

	//@Override


public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);

	// Edit Text
	edtSoldTagId1 = (EditText) getView().findViewById(R.id.edtSoldTagId1);
	edtBuyerFarmerId1 = (EditText) getView().findViewById(R.id.edtBuyerFarmerId1);
	edtSellingPrice1 = (EditText) getView().findViewById(R.id.edtSellingPrice1);
	edtSoldDate1 = (EditText) getView().findViewById(R.id.edtSoldDate1);
	
	btnAdd1= (Button)getView().findViewById(R.id.btnAdd1);
	
	btnAdd1.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			// creating new product in background thread
			new Addanimalsales().execute();
		}
	});
	 
}
	
   class Addanimalsales extends AsyncTask<String, String, String> {

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
		String SoldTagId1 = edtSoldTagId1.getText().toString();
		String BuyerFarmerId1 = 	edtBuyerFarmerId1.getText().toString();
		String SellingPrice1 = edtSellingPrice1.getText().toString();
		String SoldDate1 = edtSoldDate1.getText().toString();
		

		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("SoldTagId1", SoldTagId1));
		params.add(new BasicNameValuePair("BuyerFarmerId1", BuyerFarmerId1));
		params.add(new BasicNameValuePair("SellingPrice1", SellingPrice1));
		params.add(new BasicNameValuePair("SoldDate1", SoldDate1));
		
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
				Intent i = new Intent(getActivity(), Buffalo_buffalo.class);
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

	View rootView = inflater.inflate(R.layout.buffalo_animal_sales, container, false);
	return rootView;
}


}

