package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
=======
import android.media.MediaPlayer;
>>>>>>> 7bf7c4adeb9cf75dc7813cf7f80545b990194302
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class RPG_Battle extends Activity {
<<<<<<< HEAD
	RPG_Battle world = this;
=======
	//MediaPlayer player;
>>>>>>> 7bf7c4adeb9cf75dc7813cf7f80545b990194302
	Avatar mAvatar;
	Jukebox music;
	Companion mCompanion;
	RPGEnemy mEnemy;
	TextView mHeroHealthText, mCompanionHealthText, mEnemyHealthText;
	Button mAvatarButton, mCompanionButton;
	ImageView mEnemyView;
	int backgrounds[] = {R.drawable.background_bonuslvl, R.drawable.background_castle, R.drawable.background_castleroof,
						 R.drawable.background_desertcave, R.drawable.background_icy, R.drawable.background_magicarena,
						 R.drawable.background_magicdungeon, R.drawable.background_mine, R.drawable.background_minelvltwo,
						 R.drawable.background_mystery, R.drawable.background_mysterytwo, R.drawable.background_roots,
						 R.drawable.background_rootstwo, R.drawable.background_ship, R.drawable.background_worldsend};
	private boolean companionHasAttacked;
	private boolean avatarHasAttacked;
	
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
		mEnemy.getAttributeStruct().generateAttributes(mEnemy.getStatStruct());
		mEnemy.calculateLevel();
		mEnemy.calculateHealth();
		mEnemyView = new ImageView(this);
		mEnemyView.setBackgroundResource(R.drawable.kaiser_dragon);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		layout.addView(mEnemyView, params);
		
		mEnemyHealthText = new TextView(this);
		RelativeLayout.LayoutParams enemyHealthParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		enemyHealthParams.addRule(RelativeLayout.ALIGN_BOTTOM, RelativeLayout.TRUE);
		layout.addView(mEnemyHealthText, enemyHealthParams);
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
		if (mCompanion != null) {
			mCompanionButton = (Button) this.findViewById(R.id.companion_button);
			mCompanionButton.setBackgroundResource((int)mCompanion.getSpriteReource());
		}
		else {
			companionHasAttacked = true;
		}
	}
	protected void onStart() {
		super.onStart();
<<<<<<< HEAD
		mEnemyHealthText.setText(mEnemy.getCurrentHealth());
		mHeroHealthText.setText(mAvatar.getCurrentHealth());
		mCompanionHealthText.setText(mCompanion.getCurrentHealth());
		mAvatarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!avatarHasAttacked) {
					if (!AvatarIsIncapacitated()) {
						mEnemy.takeDamage(mAvatar.attack());
						SpecialAttack specialAttack = mAvatar.specialAttack();
						if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
							mAvatar.takeSpecialAttack(specialAttack);
							if (mCompanion != null) {
								mCompanion.takeSpecialAttack(specialAttack);
							}
						}
					}
					avatarHasAttacked = true;
					checkForEndTurn();
				}
			}
		});
		
		if (mCompanion != null) {
			mCompanionButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (!companionHasAttacked) {
						if (!CompanionIsIncapacitated()) {
							mEnemy.takeDamage(mCompanion.attack());
							SpecialAttack specialAttack = mCompanion.specialAttack();
							if (specialAttack != null) {
								if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
									mAvatar.takeSpecialAttack(specialAttack);
									mCompanion.takeSpecialAttack(specialAttack);
								}
							}
						}
						companionHasAttacked = true;
						checkForEndTurn();
					}
					
				}
			});
		}
	}
	protected boolean AvatarIsIncapacitated() {
		boolean isIncapacitated = false;
		if (mAvatar.getCurrentHealth() <= 0 || mAvatar.getTurnsCharmed() != 0) {
			isIncapacitated = true;
		}
		return isIncapacitated;
	}
	protected boolean CompanionIsIncapacitated() {
		boolean isIncapacitated = false;
		if (mCompanion.getCurrentHealth() <= 0 || mCompanion.getTurnsCharmed() != 0) {
			isIncapacitated = true;
		}
		return isIncapacitated;
	}
	protected void checkForEndTurn() {
		if (avatarHasAttacked && companionHasAttacked) {
			performEnemyTurn();
			performAfterTurnCleanup();
		}
		
	}
	private void performEnemyTurn() {
		if (mEnemy.getCurrentHealth() > 0) {
			int randomShift = 2;
			if (mCompanion == null) {
				randomShift = 1;
			}
			Random rand = new Random();
			rand.setSeed(System.nanoTime());
			int selectHero = Math.abs((rand.nextInt() % randomShift + 1));
			Avatar hero;
			hero = mAvatar;
			if (selectHero == 2) {
				hero = mCompanion;
			}
			hero.takeDamage(mEnemy.attack());
			SpecialAttack specialAttack = mEnemy.specialAttack();
			if (specialAttack != null) {
				if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
					mEnemy.takeSpecialAttack(specialAttack);
				}
				else {
					hero.takeDamage(specialAttack);
				}
			}
		}
		performAfterTurnCleanup();
		
	}
	private void performAfterTurnCleanup() {
		if (mEnemy.getCurrentHealth() <= 0) {
			performBattleWon();
		}
		else if (mAvatar.getCurrentHealth() <= 0) {
			if (mCompanion == null || mCompanion.getCurrentHealth() <= 0) {
				performBattleLost();
			}
		}
		mAvatar.cleanUpAfterBattleTurn();
		avatarHasAttacked = false;
		if (mCompanion != null) {
			mCompanion.cleanUpAfterBattleTurn();
			companionHasAttacked = false;
		}
		mEnemy.cleanUpAfterBattleTurn();
		
		mHeroHealthText.setText(mAvatar.getCurrentHealth());
		mCompanionHealthText.setText(mCompanion.getCurrentHealth());
		
	}
	private void performBattleLost() {
		mAvatar.getWallet().subtractGold(mEnemy.getWallet().getGold());
		Intent intent = new Intent(RPG_Battle.this, Selection_Screen.class);
		RPG_Battle.this.startActivity(intent);
		
	}
	private void performBattleWon() {
		mAvatar.getWallet().addGold(mEnemy.getWallet().getGold());
		Intent intent = new Intent(RPG_Battle.this, Selection_Screen.class);
		RPG_Battle.this.startActivity(intent);
=======
		//music.start();
>>>>>>> 7bf7c4adeb9cf75dc7813cf7f80545b990194302
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
