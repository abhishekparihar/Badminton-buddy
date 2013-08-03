package com.badmintonbuddy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.badmintonbuddy.helpers.AppStatus;
import com.weboapps.badmintonbuddy.R;

public class AreaListActivity extends Activity {
	
	final static String TAG = "AreaListActivity";
    AppStatus appStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arealist);
        
        appStatus = AppStatus.getInstance(this);
        ListView mListView=(ListView) findViewById(android.R.id.list);
        
        List<String> areas = new ArrayList<String>(5);
        for(int i=0;i<5;i++){
        	areas.add("Bavdhan ..");
        }
        
        AreaListAdapter mAreaListAdapter=new AreaListAdapter(AreaListActivity.this, areas);
        mListView.setAdapter(mAreaListAdapter);
        
    }
    
    
}
