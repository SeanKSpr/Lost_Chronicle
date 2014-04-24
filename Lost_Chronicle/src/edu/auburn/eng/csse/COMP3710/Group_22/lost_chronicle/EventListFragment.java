package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EventListFragment extends Fragment {
	private EventItemAdapter m_adapter;
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private ListView mListView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		View v = inflater.inflate(R.layout.fragment_event_list, container, false);
		mListView = (ListView) v.findViewById(R.id.eventlist);
		return v;
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		//mListView.setAdapter(m_adapter);
	}
	
	public void updateList(ArrayList<Event> listIn)
	{
		eventList = listIn;
		m_adapter = new EventItemAdapter(this.getActivity(), R.layout.list_item, eventList);
		mListView.setAdapter(m_adapter);
	}


}
