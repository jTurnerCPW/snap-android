/***
Copyright (c) 2008-2012 CommonsWare, LLC
Licensed under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain	a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
by applicable law or agreed to in writing, software distributed under the
License is distributed on an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
OF ANY KIND, either express or implied. See the License for the specific
language governing permissions and limitations under the License.
	
From _The Busy Coder's Guide to Android Development_
  http://commonsware.com/Android
*/

package com.compuware.pdp310.snap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

//This warning is irrelevant since we already do a check for the API level when the menu option is selected.  
@SuppressLint("NewApi")
public class StockPreferenceFragment extends PreferenceFragment {
@Override
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  
  Log.v("STOCK-Pref", "addPref");
  addPreferencesFromResource(R.layout.preferences);
}
}
