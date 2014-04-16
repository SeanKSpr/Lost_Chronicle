package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selection_Screen extends Activity {

	private Button mSchedulerButton, mRPGButton, mKanojoButton;
	public static final String PURCHASE_KEY = "edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle_PURCHASABLE";
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