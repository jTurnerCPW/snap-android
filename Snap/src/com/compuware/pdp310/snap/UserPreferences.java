/*===================================================================================================
 *	UserPreferences.java
 *	
 *	Authors: Jackson Turner
 *	Supporting Contributors: Michael Allon, Richard Kenny, Josh Nimtz, Kim Pilbeam, Vincent Sam, CommonsWare
 *	
 * 	Last Edit: 4/23/2103
 * 
 * 	Major: Allows user to change specified user name, targeted to devices below Android 3.0
 * 
 * 	Minor: 
 * 
 *  Notes:
 *===================================================================================================
 */

package com.compuware.pdp310.snap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class UserPreferences extends PreferenceActivity {
	
  @SuppressWarnings("deprecation")
@Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    addPreferencesFromResource(R.layout.preferences);
    
    Intent intent = getIntent();
	boolean userSet = intent.getBooleanExtra("userSet", true);
    
    if(userSet == false) {
		createUsernameNotSetAlert();
	}
  }

  private void createUsernameNotSetAlert(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(
				"Username not set!  Please enter your Compuware LAN ID.  ")
				.setCancelable(false)
				.setPositiveButton("Okay.",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.setNegativeButton("NEVER!!!",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
