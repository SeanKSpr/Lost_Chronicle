package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class RPGSelectionScreen extends FragmentActivity implements AvatarInfoCommunicator{
	Button  mRPGBattleButton;
	final RPGSelectionScreen activity = this;
	public RPGSelectionScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_selection_screen);
		mRPGBattleButton = (Button) findViewById(R.id.battle_screen_button);
	}

	@Override
	protected void onStart() {
		super.onStart();
		launchAvatarInformationFragment();
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
		Avatar avatar = avatarDBHelper.getAvatar();
		return avatar;
	}
}
