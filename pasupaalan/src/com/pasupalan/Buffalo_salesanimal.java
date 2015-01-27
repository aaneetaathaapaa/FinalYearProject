package com.pasupalan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.ahis.R;

public class Buffalo_salesanimal extends Activity {

	private DrawerLayout mDrawerLayout;

	// ListView 
	private ListView mDrawerList;

	// ActionBarDrawerToggle 
	private ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	private String mTitle = "AHIS";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buffalo);

		mTitle = "AHIS";
		getActionBar().setTitle(mTitle);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroung));
		

		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();

			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Buffalo");
				invalidateOptionsMenu();
			}	
		};
		
		
		if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
		
		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), 
				R.layout.drawer_list_item, getResources().getStringArray(R.array.menus));*/
		
		List<HashMap<String,String>> data = GetSampleData();
		SimpleAdapter adapter = new SimpleAdapter(this, data, 
				 R.layout.drawer_list_item, new String[] { "image", "list" },
		 new int[] { R.id.image,android.R.id.text1 });
		 
		
		

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			
			
		@Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
			
		});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
     * Diplaying fragment view for selected nav drawer list item
     * */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        switch (position) {
        case 0:
            fragment = new Buffalo_sales_animal();
            break;
            
       case 1:
        	fragment= new Buffalo_animal_purchase();
        	break;
            
      /* case 2:
            fragment = new Medical();
            break;
        
        case 3:
            fragment = new Sales();
            break;*/
            
        default:
            break;
        }
 
        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment).commit();
 
            String[] menuItems = getResources().getStringArray(R.array.menus2);
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
			mTitle = menuItems[position];
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }
    
    //Event called when volume key up is pressed
	public boolean onKeyUp(int keyCode, KeyEvent event) { 
		   if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) { 
		      
			  Toast.makeText(this, "Volume up pressed ", Toast.LENGTH_SHORT).show();
		       return true;
		   } else {
		       return super.onKeyUp(keyCode, event); 
		   }
		}
	
	//Event called when volume key down is pressed   
    public boolean onKeyDown(int keyCode, KeyEvent event) { 
	   if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) { 
	       // Do your thing 
		   
		   Toast.makeText(this, "Volume down pressed ", Toast.LENGTH_SHORT).show();
	       return true;
	   } else {
	       return super.onKeyDown(keyCode, event); 
	   }
	}
    
    List<HashMap<String,String>> GetSampleData()
    {
    	int[] pic = new int[]{R.drawable.sales,R.drawable.sales/*, R.drawable.medical, R.drawable.general*/};
    List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    
    HashMap<String,String> map = new HashMap<String,String>();
    
    map.put("list", "Sales");
    map.put("image", String.valueOf(pic[0]));
    list.add(map);
    
    map = new HashMap<String,String>();
    map.put("image", String.valueOf(pic[1]));
    map.put("list", "Purchased");
    list.add(map);
    
    
    /*map = new HashMap<String,String>();
    map.put("image", String.valueOf(pic[2]));
    map.put("list", "Medical");
    list.add(map);
    
    map = new HashMap<String,String>();
    map.put("image", String.valueOf(pic[3]));
    map.put("list", "SalesPurchase");
    list.add(map);
    */
   return list;
    }
}
