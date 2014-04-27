package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

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
import android.widget.Toast;


public class Scheduler_Activity extends FragmentActivity implements EventCommunicator{
	//private ArrayList<Event> eventList = new ArrayList<Event>();
	private EventScheduler  eventDBHelper;
	private Event mCurrentEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheduler_screen);
	}
	
	protected void onStart() 
	{
		launchListView();
		super.onStart();
	}
	
	
	@Override
	protected void onResume() {
		launchListView();
		super.onResume();
	}

	@Override
	public void respond(Event eventIn)
	{
		if(eventIn.timeConflicts())
		{
			Toast.makeText(getApplicationContext(), R.string.time_conflict_toast, Toast.LENGTH_SHORT).show();

		}
		else 
		{
		this.onBackPressed();

		//EventScheduler eventDBHelper = new EventScheduler(this);		
		FragmentManager fm = getFragmentManager();
		Fragment fragmentHolder = fm.findFragmentById(R.id.schedulerScreen);
		
		if(fragmentHolder instanceof EventListFragment)
		{
			Log.i("notjustatag", "made it here");
			EventListFragment frag = (EventListFragment) fragmentHolder;
			frag.updateList(eventIn);
			
		}
		}
	}
	
	
	
	@Override
	public void onBackPressed() {
		FragmentManager fm = getFragmentManager();
		int numOfFragments = fm.getBackStackEntryCount();
		if(numOfFragments > 1)
		{
			fm.popBackStackImmediate();
		}
		else
		{
			super.onBackPressed();
		}
	}

	private void launchListView()
	{
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

	@Override
	public void addRating(Event event) {
		mCurrentEvent = event;
		FragmentManager fm = getFragmentManager();
		Fragment fragmentHolder = fm.findFragmentById(R.id.ratingSlot);
		if (fragmentHolder == null) {
			fragmentHolder = new EventCompletionFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.ratingSlot, fragmentHolder).addToBackStack(null).commit();
		}
		
	}

	@Override
	public void updateEvent(float rating) {
		eventDBHelper = new EventScheduler(this);
		mCurrentEvent.setOnGoing((short) 0);
		mCurrentEvent.setEval(rating);
		eventDBHelper.updateEvent(mCurrentEvent);
		
		AvatarDataSource avatarDBHelper = new AvatarDataSource(this);
		Avatar update = avatarDBHelper.getAvatar();
		String statType = mCurrentEvent.getType();
		
		int statIncrease = (int)Math.ceil((mCurrentEvent.getDifficulty() + mCurrentEvent.getLengthInMinutes() + mCurrentEvent.getEval()) % 60);
		
		switch(statType)
		{
		case "Strength":
			update.statStruct.addStrength(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Strength!", Toast.LENGTH_SHORT).show();
			break;
		case "Dexterity":
			update.statStruct.addDexterity(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Dexterity!", Toast.LENGTH_SHORT).show();
			break;
		case "Constitution":
			update.statStruct.addConstitution(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Constitution!", Toast.LENGTH_SHORT).show();
			break;
		case "Charisma":
			update.statStruct.addCharisma(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Charisma!", Toast.LENGTH_SHORT).show();
			break;
		case "Intellect":
			update.statStruct.addIntellect(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Intellect!", Toast.LENGTH_SHORT).show();
			break;
		case "Wisdom":
			update.statStruct.addWisdom(statIncrease);
			Toast.makeText(this, "You Earned " + statIncrease + " Wisdom!", Toast.LENGTH_SHORT).show();
			break;
		}
		
		avatarDBHelper.updateAvatar(update);
		
	}
		
}
