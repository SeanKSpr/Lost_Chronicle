package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Selection_Screen extends Activity {

	private Button mSchedulerButton, mRPGButton, mKanojoButton;
	public static final String PURCHASE_KEY = "edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle_PURCHASABLE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.deleteDatabase("Events.db");
		setContentView(R.layout.activity_selection__screen);
		mSchedulerButton = (Button) this.findViewById(R.id.scheduler_activity_button);
		mRPGButton = (Button) this.findViewById(R.id.rpg_battle_activity_button);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		createButton((LinearLayout) this.findViewById(R.id.selection_screen_layout));
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
	
	private void createButton(LinearLayout layout) {	
		if (layout.findViewById(24) == null) {
			LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			buttonParams.weight = (float) 0.33;
			mKanojoButton = new Button(this);
			mKanojoButton.setId(24);
			layout.addView(mKanojoButton, buttonParams);
		}
		setButtonBackground(mKanojoButton);
	}
	
	private void setButtonBackground(Button button) {
		CompanionDataSource companionDBHelper = new CompanionDataSource(this);
		ArrayList<Companion> list = companionDBHelper.getAllCompanions();
		Companion currentCompanion = null;
		for (int i = 0; i < list.size() && currentCompanion == null; i++) {
			if (list.get(i).isActiveCompanion()) {
				currentCompanion = list.get(i);
				button.setBackgroundResource((int) currentCompanion.getMainMenuImage());
			}
		}
		if (currentCompanion == null) {
			button.setBackgroundResource(R.drawable.no_companion_banner);
		}
	}
}