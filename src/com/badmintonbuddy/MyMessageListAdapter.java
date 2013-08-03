package com.badmintonbuddy;

import com.badmintonbuddy.models.MyMessagesResult;
import com.badmintonbuddy.models.MyMessagesResult.Messages;
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
	MyMessagesResult messages;
	
	public MyMessageListAdapter(MyMessageActivity myMessageActivity,
			MyMessagesResult messagesResult) {
		mContext = myMessageActivity;
		messages=messagesResult;
		mInflater = LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return messages.getMessages().size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return messages.getMessages().get(arg0);
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
			
			Messages messages=(Messages)getItem(position);
			
			textViewName.setText(messages.getSender());
			textViewMessage.setText(messages.getMessage());
			
			
		return convertView;
	}

}
