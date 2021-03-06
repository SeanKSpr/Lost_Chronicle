package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class RPGSelectionScreen extends FragmentActivity implements AvatarInfoCommunicator{

	private Button  mRPGBattleButton;
	private Jukebox music;

	public RPGSelectionScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_selection_screen);
		mRPGBattleButton = (Button) findViewById(R.id.battle_screen_button);
		if(music == null)
		{
			music = new Jukebox(this);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		music.start(Jukebox.CHARACTER_SCREEN);
		
		launchAvatarInformationFragment();
		mRPGBattleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RPGSelectionScreen.this, RPG_Battle.class);
				RPGSelectionScreen.this.startActivity(intent);
				
			}
		});
	}
	
	@Override
	protected void onPause() {
		music.stop();
		super.onPause();
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
