/*===================================================================================================
 *	DashboardFragment.java
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

//TODO
//import com.compuware.apm.uem.mobile.android.CompuwareUEM;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DashboardFragment extends Fragment implements OnItemClickListener{

	RelativeLayout view;
    static final LauncherIcon[] ICONS = {
    	
    	//TODO
    	//letting this fly to get running, but I suspect launcherIcons may not be appropriate in this case
    	//if it turns out they are acceptable [ o_O ] i expect i'll need more sizes. 
    	new LauncherIcon(R.drawable.users, "Scan Printer QR Code", "qrbypdpicon.png"),
    	new LauncherIcon(R.drawable.users,"Select Printer", "color_printer.png"),
        new LauncherIcon(R.drawable.users, "Select Job", "jobs.png")
    };
    
    public DashboardFragment() {};
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	
		// Inflate the view
		view = (RelativeLayout)
				//TODO
				//deal with the gridview to make the dashboard buttons real buttons.  refer to GARAGZE!
				inflater.inflate(R.layout.dashboard_gridview, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.dashboard_grid);
        gridview.setAdapter(new ImageAdapter(getActivity()));
        gridview.setOnItemClickListener(this);
 
        // Hack to disable GridView scrolling
        gridview.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getAction() == MotionEvent.ACTION_MOVE;
            }
        });
        
		return view;
	}	
	
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    	switch(position)
    	{
    		case(0):
    		{
    			Intent intent = new Intent();	
    			/* setting the action string.  No other apps should respond to this request
    			 * because of the unique action string
    			 */
    			intent.setAction(Constants.SNAP_SCAN_ACTION);
    			intent.putExtra(Constants.ABS_SCAN_MODE, Constants.ABS_QR_CODE_MODE);
    			startActivityForResult(intent, Constants.BARCODE_SCAN_REQUEST);
			
    			//TODO
    			// Dynatrace Scanner Time Enter
    			//CompuwareUEM.enterAction("Scan Time");
    			break;
    		}
    		default:
    		{break;}
    	}
    }
 
    static class LauncherIcon {
        final String text;
        final int imgId;
        final String map;
 
        public LauncherIcon(int imgId, String text, String map) {
            super();
            this.imgId = imgId;
            this.text = text;
            this.map = map;
        }
 
    }
 
    static class ImageAdapter extends BaseAdapter {
        private Context mContext;
 
        public ImageAdapter(Context c) {
            mContext = c;
        }
 
        @Override
        public int getCount() {
            return ICONS.length;
        }
 
        @Override
        public LauncherIcon getItem(int position) {
            return null;
        }
 
        @Override
        public long getItemId(int position) {
            return 0;
        }
 
        static class ViewHolder {
            public ImageView icon;
            public TextView text;
        }
 
        // Create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) { 
            View v = convertView;
            ViewHolder holder;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
 
                v = vi.inflate(R.layout.dashboard_icon, null);
                holder = new ViewHolder();
                holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
                holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
 
            holder.icon.setImageResource(ICONS[position].imgId);
            holder.text.setText(ICONS[position].text);
 
            return v;
        }
    }
}
