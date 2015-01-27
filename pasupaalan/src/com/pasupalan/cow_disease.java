package com.pasupalan;



import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ahis.R;


public class cow_disease extends Fragment {
	boolean[] checkedsymptoms;
	protected Button selectsymptomsButton;
	//protected Button selectsymptomsButton;

	protected CharSequence[] symptoms = { "lameness", "greyishhair", "highfever", "swelling", "rapidbreathing", "lossofappetite" };
	
	protected ArrayList<CharSequence> selectedsymptoms = new ArrayList<CharSequence>();
	
//protected CharSequence[] symptoms = { "Red", "Green", "Blue", "Yellow", "Orange", "Purple" };
	//protected ArrayList<CharSequence> selectedsymptoms = new ArrayList<CharSequence>();
	

	public void onActivityCreated(Bundle savedInstanceState) {
    	super.onActivityCreated(savedInstanceState);
    	
    	selectsymptomsButton = (Button) getView().findViewById(R.id.select_symptoms);
		selectsymptomsButton.setOnClickListener(new View.OnClickListener (){
			public void onClick(View view) {
				switch(view.getId()){
				case R.id.select_symptoms:
					showSelectsymptomsDialog();
					break;

				default:
					break;
			}

				
			}
		});
	}
	

	protected void showSelectsymptomsDialog() {
		boolean[] checkedsymptoms = new boolean[symptoms.length];
		int count = symptoms.length;

		for(int i = 0; i < count; i++)
			checkedsymptoms[i] = selectedsymptoms.contains(symptoms[i]);

		DialogInterface.OnMultiChoiceClickListener symptomsDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				if(isChecked)
					selectedsymptoms.add(symptoms[which]);
				else
					selectedsymptoms.remove(symptoms[which]);

				onChangeSelectedsymptoms();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Select 3 symptoms");
		builder.setMultiChoiceItems(symptoms, checkedsymptoms, symptomsDialogListener);

		AlertDialog dialog = builder.create();
		dialog.show();
	}
	int correctCount=0, incorrectCount = 0;

	protected void onChangeSelectedsymptoms() {
		
		if((selectedsymptoms.contains("lameness")&&(selectedsymptoms.contains("highfever")&&(selectedsymptoms.contains("rapidbreathing"))))){
			builders("1");
			
		}
		else if((selectedsymptoms.contains("greyishhair")&&(selectedsymptoms.contains("swelling") && (selectedsymptoms.contains("lossofappetite"))))){
			builders("2");

		}
		else{
		}
	
		}

	protected void builders(String i){
		
	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	builder.setTitle("Disease");
	
	if(i.equals("1")){
		builder.setMessage("Your animal may be suffering from BLACKLEGS" +  "\n treatment: Penicillin Vaccine")
		.setPositiveButton("CALL VET", new DialogInterface.OnClickListener() 
        {                   
            @Override
            public void onClick(DialogInterface arg0, int arg1) 
            {
                try
                {
                	Intent callIntent = new Intent(Intent.ACTION_CALL);
    				callIntent.setData(Uri.parse("tel:9849439943"));
    				startActivity(callIntent);
     
                }//end try
                catch(Exception e)
                {
                    Toast.makeText(getActivity(),  "", Toast.LENGTH_LONG).show();
                }//end catch
            }//end onClick()
        }).create(); ;
		
		
	}
	else if(i.equals("2")){
		builder.setMessage("Your animal may be suffering from RINGWORM" + "\n Treatment: Thiabenzadole paste")
		.setPositiveButton("CALL VET", new DialogInterface.OnClickListener() 
        {                   
            @Override
            public void onClick(DialogInterface arg0, int arg1) 
            {
                try
                {
                	Intent callIntent = new Intent(Intent.ACTION_CALL);
    				callIntent.setData(Uri.parse("tel:9849439943"));
    				startActivity(callIntent);
     
                }//end try
                catch(Exception e)
                {
                    Toast.makeText(getActivity(),  "", Toast.LENGTH_LONG).show();
                }//end catch
            }//end onClick()
        }).create(); ;
		
		;
	}else{
		
	}
AlertDialog dialog = builder.create();
	dialog.show();
	}
	
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.main, container, false);
          
        return rootView;
    } 

}
	

