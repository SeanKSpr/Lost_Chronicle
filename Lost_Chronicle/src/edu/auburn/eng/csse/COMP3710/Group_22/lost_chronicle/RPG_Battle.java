package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RPG_Battle extends Activity {
	
	RPG_Battle world = this;
	//MediaPlayer player;
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
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_rpg_battle_screen, null);
		LinearLayout enemySide = (LinearLayout) layout.findViewById(R.id.enemy_side);
		LinearLayout enemyRowOneHealth = (LinearLayout) enemySide.findViewById(R.id.row_1_enemy_health);
		LinearLayout enemyRowOne = (LinearLayout) enemySide.findViewById(R.id.row_1_enemy);
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
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.CENTER_VERTICAL|Gravity.LEFT;
		enemyRowOne.addView(mEnemyView, params);
		
		mEnemyHealthText = new TextView(this);
		LinearLayout.LayoutParams enemyHealthParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mEnemyHealthText.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
		enemyRowOneHealth.addView(mEnemyHealthText, enemyHealthParams);
		setContentView(layout);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		mHeroHealthText = (TextView) this.findViewById(R.id.heroHealthTextView);
		mCompanionHealthText = (TextView) this.findViewById(R.id.companionHealthTextView);
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

		mEnemyHealthText.setTextColor(Color.parseColor("#00ffa0"));
		mEnemyHealthText.setTextSize(16);
		mEnemyHealthText.setBackgroundColor(Color.parseColor("#55000000"));
		mEnemyHealthText.setText(String.valueOf(mEnemy.getCurrentHealth()));
		mHeroHealthText.setText(String.valueOf(mAvatar.getCurrentHealth()));
		if (mCompanion != null) {
			mCompanionHealthText.setText(String.valueOf(mCompanion.getCurrentHealth()));
		}
		else {
			LinearLayout layout = (LinearLayout) this.findViewById(R.id.companionHealthTextView).getParent();
			layout.removeView(findViewById(R.id.companionHealthTextView));
			layout = (LinearLayout) findViewById(R.id.companion_button).getParent();
			layout.removeView(findViewById(R.id.companion_button));
		}
		mAvatarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!avatarHasAttacked) {
					if (!AvatarIsIncapacitated()) {
						mEnemy.takeDamage(mAvatar.attack());
						SpecialAttack specialAttack = mAvatar.specialAttack();
						if (specialAttack != null) {
							if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
								mAvatar.takeSpecialAttack(specialAttack);
								if (mCompanion != null) {
									mCompanion.takeSpecialAttack(specialAttack);
								}
							}
							else {
								mEnemy.takeSpecialAttack(specialAttack);
							}
						}
					}
				}
				avatarHasAttacked = true;
				checkForEndTurn();
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
								else {
									mEnemy.takeSpecialAttack(specialAttack);
								}
							}
						}
					}
					companionHasAttacked = true;
					checkForEndTurn();
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
		if (!enemyIsIncapacitated()) {
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
		}
		performAfterTurnCleanup();
		
	}
	private boolean enemyIsIncapacitated() {
		if (mEnemy.getTurnsCharmed() > 0) {
			return true;
		}
		return false;
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
		
		mHeroHealthText.setText(String.valueOf(mAvatar.getCurrentHealth()));
		if (mCompanion != null) {
			mCompanionHealthText.setText(String.valueOf(mCompanion.getCurrentHealth()));
			mCompanionHealthText.invalidate();
		}
		mEnemyHealthText.setText(String.valueOf(mEnemy.getCurrentHealth()));
		mHeroHealthText.invalidate();
		mEnemyHealthText.invalidate();
		
	}
	private void performBattleLost() {
		mAvatar.getWallet().subtractGold(mEnemy.getWallet().getGold());
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = new RPGBattleEndFragment();
		Bundle args = new Bundle();
		args.putString(RPGBattleEndFragment.BATTLE_END, "game_over");
		fragment.setArguments(args);
		transaction.add(R.id.endgame_fragment_container, fragment).commit();
	}
	private void performBattleWon() {
		mAvatar.getWallet().addGold(mEnemy.getWallet().getGold());
		FragmentManager fm = this.getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		Fragment fragment = new RPGBattleEndFragment();
		Bundle args = new Bundle();
		args.putString(RPGBattleEndFragment.BATTLE_END, "victory");
		fragment.setArguments(args);
		transaction.add(R.id.endgame_fragment_container, fragment).commit();
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
