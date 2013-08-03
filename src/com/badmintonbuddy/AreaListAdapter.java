package com.badmintonbuddy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.badmintonbuddy.models.AreaResult.CourtArea;
import com.weboapps.badmintonbuddy.R;

public class AreaListAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<CourtArea> areas;
    AreaListActivity main_context;
   
    public AreaListAdapter(AreaListActivity context, List<CourtArea> list) {
        this.context = context;
        this.areas = list;
        main_context =context;
    }

	@Override
    public int getCount() {
        if ( areas != null )
            return areas.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int index) {
        return areas.get(index).getName();
    }

    @Override
    public long getItemId(int position) {
        return 0;//areas.get(position).getOrganization().getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout v = (RelativeLayout) inflater.inflate(R.layout.area_list_item, parent, false);
        
        TextView tv = (TextView) v.findViewById(R.id.areaName);
        final String result = (String) getItem(position);
        tv.setText(result);

      
        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // itemClick(imageView, !result.getOrganization().getSupported(), position);
            }

        });*/
        return v;
    }

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
}
