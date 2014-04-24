package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Scheduler_Activity extends FragmentActivity implements EventCommunicator{
	private ArrayList<Event> eventList = new ArrayList<Event>();
	private EventItemAdapter m_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventScheduler eventDBHelper = new EventScheduler(this);
		eventList = (ArrayList<Event>) eventDBHelper.getAllEvents();
		setContentView(R.layout.activity_scheduler_screen);
		m_adapter = new EventItemAdapter(this, R.layout.list_item, eventList);
		setListAdapter(m_adapter);
		}
	
		protected void onStart() {
			super.onStart();
		}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.event_menu, menu);
			return true;
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
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
		@Override
		public void respond(Event eventIn)
		{
			boolean addable = true;
			if(eventList.isEmpty())
			{
				eventList.add(eventIn);
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
					EventScheduler eventDBHelper = new EventScheduler(this);
					eventDBHelper.addEvent(eventIn);
				}
			}
		}
		private void launchAddEvent() {
			FragmentManager fm = getFragmentManager();
			Fragment addEventFragment = fm.findFragmentById(R.id.schedulerScreen);
			if (addEventFragment == null) {
				addEventFragment = new AddEventFragment();
				FragmentTransaction transaction = fm.beginTransaction();
				transaction.add(R.id.schedulerScreen, addEventFragment).addToBackStack(null).commit();
			}
		}
}
