package com.pasupalan;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahis.R;
import com.pasupalan.cow_general_search.SaveChanges;

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

public class cow_animal_sales extends Fragment {
ProgressDialog pDialog;
	
	JSONParser jsonParser = new JSONParser();
	
	
	EditText Tagid;
	EditText BuyerFarmerId;
	EditText SoldDate;
	EditText SellingPrice;
	
	Button addrecord;
	private static final String TAG_SUCCESS = "success";
	
	private static String url = "http://10.0.3.2/ahis/cow_animal_sales.php";
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Tagid = (EditText) getView().findViewById(R.id.etTagId);
		BuyerFarmerId = (EditText) getView().findViewById(R.id.edtBuyerFarmerId);
		SoldDate = (EditText) getView().findViewById(R.id.edtSoldDate);
		SellingPrice = (EditText) getView().findViewById(R.id.edtSellingPrice);
		addrecord = (Button) getView().findViewById(R.id.AddRecord);
		
		addrecord.setOnClickListener(new View.OnClickListener (){
			public void onClick(View view) {
				new add_animal_sales().execute();
				
			}
		});
	}
	
class add_animal_sales extends AsyncTask<String, String, String> {
		
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
			String buyerfarmerid = BuyerFarmerId.getText().toString();
			String solddate = SoldDate.getText().toString();
			String sellingprice = SellingPrice.getText().toString();
		
		
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("tagid", tagid));
		params.add(new BasicNameValuePair("buyerfamerid", buyerfarmerid));
		params.add(new BasicNameValuePair("solddate", solddate));
		params.add(new BasicNameValuePair("sellingprice", sellingprice));
		
	
		
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
  
        View rootView = inflater.inflate(R.layout.cow_animal_sales, container, false);
          
        return rootView;
    }
}
