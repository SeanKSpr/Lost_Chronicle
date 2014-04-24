package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class RPGSelectionScreen extends FragmentActivity implements AvatarInfoCommunicator{
	Button mCharacterScreenButton, mRPGBattleButton;
	final RPGSelectionScreen activity = this;
	public RPGSelectionScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_selection_screen);
		mCharacterScreenButton = (Button) this.findViewById(R.id.character_screen_button);
		mRPGBattleButton = (Button) findViewById(R.id.battle_screen_button);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mCharacterScreenButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//LinearLayout layout = (LinearLayout) activity.findViewById(R.layout.activity_rpg_selection_screen);
				//layout.removeView(mRPGBattleButton);
				View buttonToBeRemoved = (View) activity.findViewById(R.id.character_screen_button);
				ViewManager parent = (ViewManager) v.getParent();
				parent.removeView(buttonToBeRemoved);
				launchAvatarInformationFragment();
			}
		});
		
		mRPGBattleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RPGSelectionScreen.this, RPG_Battle.class);
				RPGSelectionScreen.this.startActivity(intent);
				
			}
		});
	}
	
	private void launchAvatarInformationFragment() {
		FragmentManager fm = getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.avatar_info_fragment_container);
		if (fragment == null) {
			fragment = new AvatarInformationFragment();
			fm.beginTransaction().addToBackStack("AvatarInfoFragment").add(R.id.avatar_info_fragment_container, fragment).commit();
		}
	}
	@Override
	public Avatar getAvatar() {
		AvatarDataSource avatarDBHelper = new AvatarDataSource(this);
		return avatarDBHelper.getAvatar();
	}
}
