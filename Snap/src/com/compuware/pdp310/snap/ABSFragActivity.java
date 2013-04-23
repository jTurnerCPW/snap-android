/*===================================================================================================
 *	ABSFragActivity.java
 *
 *	Authors: Jackson Turner
 *	Supporting Contributors: Michael Allon, Richard Kenny, Josh Nimtz, Kim Pilbeam, Vincent Sam
 *	
 * 	Last Edit: 4/23/2103
 * 
 * 	Major: Extends SherlockFragmentActivity to localize additional content available to descendants. 
 * 
 * 	Minor: Styling of the ABSFragActivity
 * 
 *  Notes:
 *===================================================================================================
 */

package com.compuware.pdp310.snap;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

//TODO:eventually add UEM back in... 5.5?!
//import com.compuware.apm.uem.mobile.android.CompuwareUEM;

/*
 * Inherits from SherlockFragmentActivity, extends with TODO
 */
public class ABSFragActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// initialize actionBar
		actionBar = getSupportActionBar();
		actionBar.setTitle(getResources().getString(R.string.app_name));
		actionBar.setIcon(R.drawable.users);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(false);
	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {

		//inflate standard dashboard menu
		getSupportMenuInflater().inflate(R.menu.dashboard_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {

		case android.R.id.home:
			goHome();
			return true;			
		case R.id.menu_settings:
			startPreferences();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/*
	 * Override this method in child classes that implement a refreshable list.  There you can implement more specific 
	 * functionality.  
	 */
	public void refreshList(){};

	/*
	 * Set up the intent and launch the scanner.
	 */
	protected void startScanner() {
		
		Intent intent = new Intent();	
		/* setting the action string.  No other apps should respond to this request
		 * because of the unique action string.  All consts in Constants.java
		 */
		intent.setAction(Constants.SNAP_SCAN_ACTION);
		intent.putExtra(Constants.ABS_SCAN_MODE, Constants.ABS_QR_CODE_MODE);
		startActivityForResult(intent, Constants.BARCODE_SCAN_REQUEST);
		
		//TODO:reenable this when appropriate, rename more precisely.  
		//decide if this is an appropriate placement because of potential user influence
		// Dynatrace Scanner Time Enter
		//CompuwareUEM.enterAction("Scan Time");
	}

	protected boolean startPreferences() {
		
		/* Start preferences activity for versions below android 3.0 */
		if (Build.VERSION.SDK_INT<Build.VERSION_CODES.HONEYCOMB) {
			startActivity(new Intent(this, UserPreferences.class));
			return true;
		}
		/* Start preferences fragment for android 3.0+ */
		else {
			Intent intent = new Intent( this, UserPreferencesHC.class );
			/* Adding extras to skip showing headers in the preference activity */
			intent.putExtra( UserPreferencesHC.EXTRA_SHOW_FRAGMENT, StockPreferenceFragment.class.getName() );
			intent.putExtra( UserPreferencesHC.EXTRA_NO_HEADERS, true );
			startActivity(intent);
			return true;
		}
		
	}
	
	protected void goHome() {
		
		Intent intent = new Intent(this, DashboardActivity.class);
		startActivity(intent);
		finish();
	}

}
