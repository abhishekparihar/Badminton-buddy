package com.badmintonbuddy;

import java.util.ArrayList;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.badmintonbuddy.helpers.LogUtils;
import com.badmintonbuddy.models.BuddyResult;
import com.badmintonbuddy.models.BuddyResult.User;
import com.weboapps.badmintonbuddy.R;

public class NeabyBuddyListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	NearbyBuddyActivity mContext;
	private BuddyResult mUsers;
	
	public List<String> mSelectedList=new ArrayList<String>();
	

	public NeabyBuddyListAdapter(NearbyBuddyActivity nearbyBuddyActivity,
			BuddyResult users) {
		mContext = nearbyBuddyActivity;
		mInflater = LayoutInflater.from(mContext);
		mUsers=users;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mUsers.getUsers().size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mUsers.getUsers().get(arg0);
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
		
		try{
		if (convertView == null) 
			convertView = mInflater.inflate(R.layout.nearby_buddy_list_item, null);
		
		  final User result = (User) getItem(position);
	       
		
		textViewBuddyName = (TextView) convertView.findViewById(R.id.textViewBuddyName);
		textViewBuddyLocation = (TextView) convertView.findViewById(R.id.textViewBuddyLocation);
		checkBoxSelected = (CheckBox)convertView.findViewById(R.id.checkBoxSelected);
		checkBoxSelected.setTag(position);
		
		checkBoxSelected.setOnCheckedChangeListener(mChangeListener);
		
		textViewBuddyLocation.setText(mUsers.getArea().getName());
		textViewBuddyName.setText(result.getName());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return convertView;
	}
	
	OnCheckedChangeListener mChangeListener=new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			try{
		    LogUtils.LOGV("NeabyBuddyListAdapter", ""+buttonView.getTag());	
			
		    checkIdExists(""+buttonView.getTag());
			}catch(Exception e){
				e.printStackTrace();
			}
		}

		private void checkIdExists(String mId) {
			for(int i=0;i<mSelectedList.size();i++){
				if(0 == mSelectedList.get(i).compareTo(mId)){
				    mSelectedList.remove(i);
				    return;
				}
			}
			
			mSelectedList.add(mId);
		}
	};
	

}
