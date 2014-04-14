package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RPG_Battle extends Activity {
	TextView mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_battle_screen);
		mView = (TextView) this.findViewById(R.id.item_name_text);
		}
	protected void onStart() {
		super.onStart();
		mView.setText(this.getIntent().getStringExtra("test"));
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
