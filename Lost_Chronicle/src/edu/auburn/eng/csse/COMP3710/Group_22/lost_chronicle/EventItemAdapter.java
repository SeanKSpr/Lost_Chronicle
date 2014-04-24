package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EventItemAdapter extends ArrayAdapter<Event>{
	private ArrayList<Event> events;
	
	public EventItemAdapter(Context context, int textViewResourceId, ArrayList<Event> eventsIn)
	{
		super(context, textViewResourceId, eventsIn);
		this.events = eventsIn;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
		if (v == null)
		{
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item, null);
		}
		
		Event i = events.get(position);
		if(i != null)
		{
			TextView eventName = (TextView) v.findViewById(R.id.toptext);
			TextView eventType = (TextView) v.findViewById(R.id.middletext);
			TextView timeSlot = (TextView) v.findViewById(R.id.bottomtext);
			TextView descTop = (TextView) v.findViewById(R.id.descriptiontext);
			TextView descBottom = (TextView) v.findViewById(R.id.desctext);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (eventName != null){
				eventName.setText(i.getTitle());
			}
			if (eventType != null){
				eventType.setText(i.getType() + " Building Event");
			}
			if (timeSlot != null){
				timeSlot.setText(i.getStart_time().toString() + " - " + i.getEnd_time().toString());
			}
			if (descTop != null){
				descTop.setText("Description:");
			}
			if(descBottom != null){
				descBottom.setText(i.getDescription());
			}
			
		}
		return v;
	}

}
