package com.pasupalan;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahis.R;
import com.pasupalan.cow_animal_sales.add_animal_sales;

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

public class cow_animal_purchase extends Fragment{
ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	
	EditText Tagid;
	EditText SellerFarmerId1;
	EditText BuyingPrice1;
	EditText PurchasedDate1;
	
	Button Add1;
	private static final String TAG_SUCCESS = "success";
	
	private static String url = "http://10.0.3.2/ahis/cow_animal_purchase.php";
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Tagid = (EditText) getView().findViewById(R.id.etTagId);
		SellerFarmerId1 = (EditText) getView().findViewById(R.id.edtSellerFarmerId1);
		BuyingPrice1 = (EditText) getView().findViewById(R.id.edtBuyingPrice1);
		PurchasedDate1 = (EditText) getView().findViewById(R.id.edtPurchasedDate1);
		Add1 = (Button) getView().findViewById(R.id.btnAdd1);
		
		Add1.setOnClickListener(new View.OnClickListener (){
			public void onClick(View view) {
				new add_animal_purchase().execute();
				
			}
		});
	}
	
class add_animal_purchase extends AsyncTask<String, String, String> {
		
		protected void onPreExecute() {
   			super.onPreExecute();
   			pDialog = new ProgressDialog(getActivity());					
   			pDialog.setMessage("Adding..");
   			pDialog.setIndeterminate(false);
   			pDialog.setCancelable(true);
   			pDialog.show();
		}	

		protected String doInBackground(String... args)
		{

			String tagid = Tagid.getText().toString();
			String SellerFarmerId = SellerFarmerId1.getText().toString();
			String BuyingPrice = BuyingPrice1.getText().toString();
			String PurchasedDate = PurchasedDate1.getText().toString();
		
		
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tagid", tagid));
		params.add(new BasicNameValuePair("SellerFarmerId", SellerFarmerId));
		params.add(new BasicNameValuePair("BuyingPrice", BuyingPrice));
		params.add(new BasicNameValuePair("PurchasedDate", PurchasedDate));
		
	
		
						// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json = jsonParser.makeHttpRequest(url,
				"POST", params);
		
		
		// check log cat from response
		Log.d("Create Response", json.toString());
		try {
			int success = json.getInt(TAG_SUCCESS);
			if(success==1)
			{
				
				Intent in = new Intent(getActivity(), Cow_sales.class);
				startActivity(in);
				}
			
			} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
			
		}
}
	


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.cow_animal_purchase, container, false);
          
        return rootView;
    }
}
