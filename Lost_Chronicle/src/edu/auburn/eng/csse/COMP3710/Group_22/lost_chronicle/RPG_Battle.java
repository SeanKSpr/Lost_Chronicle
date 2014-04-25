package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RPG_Battle extends Activity {
	//MediaPlayer player;
	Avatar mAvatar;
	Jukebox music;
	Companion mCompanion;
	RPGEnemy mEnemy;
	TextView mHeroHealthText, mCompanionHealthText;
	Button mAvatarButton, mCompanionButton;
	ImageView mEnemyView;
	int backgrounds[] = {R.drawable.background_bonuslvl, R.drawable.background_castle, R.drawable.background_castleroof,
						 R.drawable.background_desertcave, R.drawable.background_icy, R.drawable.background_magicarena,
						 R.drawable.background_magicdungeon, R.drawable.background_mine, R.drawable.background_minelvltwo,
						 R.drawable.background_mystery, R.drawable.background_mysterytwo, R.drawable.background_roots,
						 R.drawable.background_rootstwo, R.drawable.background_ship, R.drawable.background_worldsend};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LayoutInflater inflater = getLayoutInflater();
		
		Random rand = new Random(System.currentTimeMillis() * 127);
		int decision = rand.nextInt(15);
		RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_rpg_battle_screen, null);
		layout.setBackgroundResource(backgrounds[decision]);
		
		AvatarDataSource avatarHelper = new AvatarDataSource(this);
		mAvatar = avatarHelper.getAvatar();
		
		mEnemy = new RPGEnemy();
		mEnemy.generateStats(mAvatar);
		
		mEnemyView = new ImageView(this);
		mEnemyView.setBackgroundResource(R.drawable.kaiser_dragon);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layout.addView(mEnemyView, params);
		
		setContentView(layout);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		if(music == null)
		{
			music = new Jukebox(this);
		}
		
		
		CompanionDataSource companionHelper = new CompanionDataSource(this);
		ArrayList<Companion> allCompanions = companionHelper.getAllCompanions();
		for(Companion c : allCompanions)
		{
			if(c.isActiveCompanion())
			{
				mCompanion = c;
			}
		}
		
		
		mAvatarButton = (Button) this.findViewById(R.id.avatar_button);
		mAvatarButton.setBackgroundResource(R.drawable.sprite_mc);
		mCompanionButton = (Button) this.findViewById(R.id.companion_button);
		mCompanionButton.setBackgroundResource((int)mCompanion.getSpriteReource());
		}
	protected void onStart() {
		super.onStart();
		//music.start();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		music.stop();
	}
	
	@Override
	protected void onResume() {
		music.start();
		super.onResume();
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
