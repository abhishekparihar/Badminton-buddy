package com.badmintonbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.weboapps.badmintonbuddy.R;

public class NeabyBuddyListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	Context mContext;
	
	public NeabyBuddyListAdapter(Context context){
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textViewBuddyName = null;
		TextView textViewBuddyLocation = null;
		CheckBox checkBoxSelected;
		
		if (convertView == null) 
			convertView = mInflater.inflate(R.layout.nearby_buddy_list_item, null);
		
		textViewBuddyName = (TextView) convertView.findViewById(R.id.textViewBuddyName);
		textViewBuddyLocation = (TextView) convertView.findViewById(R.id.textViewBuddyLocation);
		checkBoxSelected = (CheckBox)convertView.findViewById(R.id.checkBoxSelected);
		return convertView;
	}

}
