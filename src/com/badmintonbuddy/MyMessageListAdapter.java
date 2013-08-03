package com.badmintonbuddy;

import com.weboapps.badmintonbuddy.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyMessageListAdapter extends BaseAdapter {

	Context mContext;
	private LayoutInflater mInflater;
	public MyMessageListAdapter(Context context){
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
		
		TextView textViewName = null;
		TextView textViewArea = null;
		TextView textViewMessage = null;
		
		
			if (convertView == null) 
				convertView = mInflater.inflate(R.layout.my_message_list_item, null);
			textViewName = (TextView)convertView.findViewById(R.id.textViewName);
			textViewArea = (TextView)convertView.findViewById(R.id.textViewArea);
			textViewMessage = (TextView)convertView.findViewById(R.id.textViewMessage);
		return convertView;
	}

}
