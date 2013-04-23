/*===================================================================================================
 *	UserPreferencesHC.java
 *	
 *	Authors: Jackson Turner
 *	Supporting Contributors: Michael Allon, Richard Kenny, Josh Nimtz, Kim Pilbeam, Vincent Sam, CommonsWare
 *	
 * 	Last Edit: 4/23/2103
 * 
 * 	Major: Allows user to change specified user name, targeted for devices running Android 3.0+ (Honeycomb)
 * 
 * 	Minor: 
 * 
 *  Notes:
 *===================================================================================================
 */

package com.compuware.pdp310.snap;

import java.util.List;

import android.annotation.SuppressLint;
import android.preference.PreferenceActivity;

public class UserPreferencesHC extends PreferenceActivity {

	@SuppressLint("NewApi")
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.layout.preference_headers, target);
	}
}
