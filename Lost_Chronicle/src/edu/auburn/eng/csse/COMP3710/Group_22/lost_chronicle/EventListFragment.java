package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class EventListFragment extends ListFragment {
	private EventItemAdapter m_adapter;
	private ArrayList<Event> eventList = new ArrayList<Event>();
	//private ListView mListView;
	private EventScheduler eventDBHelper;
	private EventCommunicator comm;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment_event_list, container, false);
		//mListView = (ListView) v.findViewById(R.id.list);
		EventScheduler eventDBHelper = new EventScheduler(getActivity());
		eventList = eventDBHelper.getAllEvents();
		if(eventList != null && !eventList.isEmpty())
		{
			Collections.reverse(eventList);
			m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
		}
		return v;
		
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		if(eventList.get(position).isOnGoing() == 0)
		{
			Toast.makeText(getActivity(), "This Event is already Completed", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Date date = new Date();
			if(eventList.get(position).getEnd_time().compareTo(date) > 0)//.compareTo(date))
			{
				Toast.makeText(getActivity(), "This Event is still ongoing", Toast.LENGTH_SHORT).show();
			}
			else
			{
				comm.addRating(eventList.get(position));
			}
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		setListAdapter(m_adapter);
		//mListView.setAdapter(m_adapter);
	}
	
	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		comm = (EventCommunicator)activity;
	}
	
	public boolean updateList(Event eventIn)
	{
		boolean addable = true;
		eventDBHelper = new EventScheduler(getActivity());
		if(eventList.isEmpty())
		{
			if(eventIn.timeConflicts())
			{
				addable = false;
			}
			if(addable)
			{
			eventList.add(0, eventIn);
			Log.i("notjustatag", "made it here");
			eventDBHelper.addEvent(eventIn);
			Log.i("notjustatag", "made it here");
			m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
			setListAdapter(m_adapter);
			return true;
			}

		}
		else
		{
			for(Event event : eventList)
			{
				Log.i("notjustatag", "made it here");
				if(eventIn.timeConflicts(event))
				{
					addable = false;
				}
			}
			if(addable)
			{
				Log.i("notjustatag", "made it here");
				eventList.add(0, eventIn);
				eventDBHelper.addEvent(eventIn);
				m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
				setListAdapter(m_adapter);
				return true;
			}
			
		}
		if(!addable)
		{
			Toast.makeText(getActivity(), R.string.time_conflict_toast, Toast.LENGTH_SHORT).show();

		}
		return addable;

	}


}
