package com.badmintonbuddy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.badmintonbuddy.helpers.AppStatus;
import com.badmintonbuddy.models.AreaResult;
import com.weboapps.badmintonbuddy.R;

public class AreaListActivity extends Activity {
	
	final static String TAG = "AreaListActivity";
    AppStatus appStatus;
    AreaResult mAreaResult=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arealist);
        
        mAreaResult=(AreaResult) getIntent().getSerializableExtra("area_list");
        
        appStatus = AppStatus.getInstance(this);
        ListView mListView=(ListView) findViewById(android.R.id.list);
        
        AreaListAdapter mAreaListAdapter=new AreaListAdapter(AreaListActivity.this,mAreaResult.getArea());
        mListView.setAdapter(mAreaListAdapter);
        
    }
    
}
