package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class Scheduler_Activity extends FragmentActivity implements EventCommunicator{
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private EventScheduler  eventDBHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Log.i("notjustatag", "made it here");
		setContentView(R.layout.activity_scheduler_screen);
		EventScheduler eventDBHelper = new EventScheduler(this);
		eventList = (ArrayList<Event>) eventDBHelper.getAllEvents();
		
	}
	
	protected void onStart() 
	{
		launchListView();
		super.onStart();
	}
	
	
	
	@Override
	public void respond(Event eventIn)
	{
		EventScheduler eventDBHelper = new EventScheduler(this);	
		
		FragmentManager fm = getFragmentManager();
		Fragment fragmentHolder = fm.findFragmentById(R.id.schedulerScreen);
		//if (fragmentHolder == null) {
			fragmentHolder = new EventListFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.schedulerScreen, fragmentHolder).addToBackStack(null).commit();
		//}

		boolean addable = true;
		if(eventList.isEmpty())
		{
			eventList.add(eventIn);
			eventDBHelper.addEvent(eventIn);
			//FragmentManager fm = getFragmentManager();
			fragmentHolder = fm.findFragmentById(R.id.schedulerScreen);
			if(fragmentHolder instanceof EventListFragment)
			{
				EventListFragment frag = (EventListFragment) fragmentHolder;
				frag.updateList(eventList);
			}
			
		}
		else
		{
			for(Event event : eventList)
			{
				if(eventIn.timeConflicts(event))
				{
					addable = false;
				}
			}
			if(addable)
			{
				eventList.add(eventIn);
				eventDBHelper.addEvent(eventIn);
				//FragmentManager fm = getFragmentManager();
				fragmentHolder = fm.findFragmentById(R.id.schedulerScreen);
				if(fragmentHolder instanceof EventListFragment)
				{
					EventListFragment frag = (EventListFragment) fragmentHolder;
					frag.updateList(eventList);
				}
			}
		}
	}
	
	
	
	private void launchListView()
	{
		Log.i("notjustatag", "made it here");
		FragmentManager fm = getFragmentManager();
		Fragment fragmentHolder = fm.findFragmentById(R.id.schedulerScreen);
		if (fragmentHolder == null) {
			fragmentHolder = new EventListFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.schedulerScreen, fragmentHolder).addToBackStack(null).commit();
		}
	}
	private void launchAddEvent() 
	{
		FragmentManager fm = getFragmentManager();
		Fragment addEventFragment = fm.findFragmentById(R.id.schedulerScreen);
		addEventFragment = new AddEventFragment();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.schedulerScreen, addEventFragment).addToBackStack(null).commit();
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.event_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
			case R.id.home_button:
				Intent intent = new Intent(Scheduler_Activity.this, Selection_Screen.class);
				Scheduler_Activity.this.startActivity(intent);
				return true;
			case R.id.add_event_button:
				launchAddEvent();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
		
}
