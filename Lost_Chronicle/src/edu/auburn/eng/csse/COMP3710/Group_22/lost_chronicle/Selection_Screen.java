package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Selection_Screen extends Activity {

	private Button mSchedulerButton, mRPGButton, mKanojoButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection__screen);
		mSchedulerButton = (Button) this.findViewById(R.id.scheduler_activity_button);
		mRPGButton = (Button) this.findViewById(R.id.rpg_battle_activity_button);
		mKanojoButton = (Button) this.findViewById(R.id.companion_activity_button);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		mSchedulerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Selection_Screen.this, Scheduler_Activity.class);
				intent.putExtra("test", "Scheduler");
				Selection_Screen.this.launchActivity(intent);
			}
		});
		
		mRPGButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Selection_Screen.this, RPG_Battle.class);
				intent.putExtra("test", "RPG");
				Selection_Screen.this.launchActivity(intent);
			}
		});
		
		mKanojoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Selection_Screen.this, Companion_Activity.class);
				intent.putExtra("test", "Kanojo");
				Selection_Screen.this.launchActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.home_button:
				//go home - launch intent to selection screen
				return true;
			case R.id.store_button:
				//go to store - launch store fragment for your activity
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	private void launchActivity(Intent intent) {
		startActivity(intent);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle saveInstanceState) {
		super.onSaveInstanceState(saveInstanceState);
	}
}