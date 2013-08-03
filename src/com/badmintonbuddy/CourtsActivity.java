package com.badmintonbuddy;

import com.weboapps.badmintonbuddy.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CourtsActivity extends SlidingMenuActivity {
	
	ListView mListView;
	CourtsListAdapter mCourtsListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courts);
		setMenuDrawer(R.layout.courts, "Courts", R.color.green_light);
		
		initialize();
	}

	private void initialize() {
		mListView = (ListView)findViewById(R.id.listViewCourts);
		mCourtsListAdapter  = new CourtsListAdapter();
		
	}
	
	
	public class CourtsListAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		public CourtsListAdapter(){
			mInflater = LayoutInflater.from(CourtsActivity.this);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
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
			TextView textViewCourtName = null;
			TextView textViewCourtArea =null;
			
			if (convertView == null) 
				convertView = mInflater.inflate(R.layout.courts_list_item, null);
			
			textViewCourtName = (TextView)convertView.findViewById(R.id.textViewCourtName);
			textViewCourtArea = (TextView)convertView.findViewById(R.id.textViewCourtArea);
			
			return convertView;
		}
	}

}
