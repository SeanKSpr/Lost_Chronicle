package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventListFragment extends ListFragment {
	private EventItemAdapter m_adapter;
	private ArrayList<Event> eventList = new ArrayList<Event>();
	//private ListView mListView;
	private EventScheduler eventDBHelper;
	


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment_event_list, container, false);
		//mListView = (ListView) v.findViewById(R.id.list);
		EventScheduler eventDBHelper = new EventScheduler(getActivity());
		eventList = eventDBHelper.getAllEvents();
		if(eventList != null && !eventList.isEmpty())
		{
			m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
		}
		return v;
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		setListAdapter(m_adapter);
		//mListView.setAdapter(m_adapter);
	}
	
	public void updateList(Event eventIn)
	{
		boolean addable = true;
		eventDBHelper = new EventScheduler(getActivity());
		if(eventList.isEmpty())
		{
			eventList.add(eventIn);
			Log.i("notjustatag", "made it here");
			eventDBHelper.addEvent(eventIn);
			Log.i("notjustatag", "made it here");
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
				eventList.add(eventIn);
				eventDBHelper.addEvent(eventIn);
			}
		}
		m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
		setListAdapter(m_adapter);
	}


}
