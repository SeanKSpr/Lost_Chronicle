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
import android.widget.Toast;

public class RPG_Battle extends Activity {
	
	public static final String ENEMY_WALLET = "enemy wallet";
	private final RPG_Battle world = this;
	//MediaPlayer player;
	private Avatar mAvatar;
	private Jukebox music;
	private Companion mCompanion;
	private RPGEnemy mEnemy;
	private TextView mHeroHealthText, mCompanionHealthText, mEnemyHealthText;
	private Button mAvatarButton, mCompanionButton;
	private ImageView mEnemyView;
	private int backgrounds[] = {R.drawable.background_bonuslvl, R.drawable.background_castle, R.drawable.background_castleroof,
						 R.drawable.background_desertcave, R.drawable.background_icy, R.drawable.background_magicarena,
						 R.drawable.background_magicdungeon, R.drawable.background_mine, R.drawable.background_minelvltwo,
						 R.drawable.background_mystery, R.drawable.background_mysterytwo, R.drawable.background_roots,
						 R.drawable.background_rootstwo, R.drawable.background_ship, R.drawable.background_worldsend};
	private boolean companionHasAttacked;
	private boolean avatarHasAttacked;
	private boolean mBattleOver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AvatarDataSource avatarHelper = new AvatarDataSource(this);
		mAvatar = avatarHelper.getAvatar();
		mBattleOver = false;
		LayoutInflater inflater = getLayoutInflater();
		Random rand = new Random(System.currentTimeMillis() * 127);
		int decision = rand.nextInt(15);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_rpg_battle_screen, null);
		layout.setBackgroundResource(backgrounds[decision]);
		setUpEnemy();
		setUpEnemyView(layout);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		if(music == null)
		{
			music = new Jukebox(this);
		}
		setCurrentCompanion();
		mHeroHealthText = (TextView) this.findViewById(R.id.heroHealthTextView);
		mCompanionHealthText = (TextView) this.findViewById(R.id.companionHealthTextView);
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
	
	private void setUpEnemyView(LinearLayout layout) {
		mEnemyView = new ImageView(this);
		mEnemyView.setBackgroundResource(R.drawable.kaiser_dragon);
		LinearLayout enemySide = (LinearLayout) layout.findViewById(R.id.enemy_side);
		LinearLayout enemyRowOneHealth = (LinearLayout) enemySide.findViewById(R.id.row_1_enemy_health);
		LinearLayout enemyRowOne = (LinearLayout) enemySide.findViewById(R.id.row_1_enemy);
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
		
	}
	private void setUpEnemy() {
		mEnemy = new RPGEnemy();
		mEnemy.generateStats(mAvatar, mCompanion);
		mEnemy.getAttributeStruct().generateAttributes(mEnemy.getStatStruct());
		mEnemy.calculateLevel();
		mEnemy.calculateHealth();
		
	}
	
	protected void onStart() {
		super.onStart();
		setUpRPGActorHealth();
		if (mCompanion == null) {
			removeCompanionViews();
		}
		
		mAvatarButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!avatarHasAttacked) {
					if (!AvatarIsIncapacitated()) {
						avatarAttack();
					}
				}
				avatarHasAttacked = true;
				checkForEndTurn();
			}
			
			private void avatarAttack() {
				Toast.makeText(world, mAvatar.getName() + " deals " + String.valueOf(mEnemy.takeDamage(mAvatar.attack())) 
						+ " damage to Serithrasas", Toast.LENGTH_SHORT).show();
				SpecialAttack specialAttack = mAvatar.specialAttack();
				int specialAttackDamage;
				if (specialAttack != null) {
					if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
						specialAttackDamage = mAvatar.takeSpecialAttack(specialAttack);
						displaySpecialAttackNotification(mAvatar, mAvatar, specialAttack, specialAttackDamage);
						if (mCompanion != null) {
							specialAttackDamage = mCompanion.takeSpecialAttack(specialAttack);
							displaySpecialAttackNotification(mAvatar, mCompanion, specialAttack, specialAttackDamage);
						}
					}
					else {
						specialAttackDamage = mEnemy.takeSpecialAttack(specialAttack);
						displaySpecialAttackNotification(mAvatar, mEnemy, specialAttack, specialAttackDamage);
					}
				}
				
			}
		});
		
		if (mCompanion != null) {
			mCompanionButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (!companionHasAttacked) {
						if (!CompanionIsIncapacitated()) {
							companionAttack();
						}
					}
					companionHasAttacked = true;
					checkForEndTurn();
				}

				private void companionAttack() {
					Toast.makeText(world, mCompanion.getName() + " deals " + String.valueOf(mEnemy.takeDamage(mCompanion.attack()) 
							+ " damage to Serithrasas"), Toast.LENGTH_SHORT).show();
					SpecialAttack specialAttack = mCompanion.specialAttack();
					if (specialAttack != null) {
						int specialAttackDamage;
						if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
							specialAttackDamage = mAvatar.takeSpecialAttack(specialAttack);
							displaySpecialAttackNotification(mCompanion, mAvatar, specialAttack, specialAttackDamage);
							specialAttackDamage = mCompanion.takeSpecialAttack(specialAttack);
							displaySpecialAttackNotification(mCompanion, mCompanion, specialAttack, specialAttackDamage);
						}
						else {
							specialAttackDamage = mEnemy.takeSpecialAttack(specialAttack);
							displaySpecialAttackNotification(mCompanion, mEnemy, specialAttack, specialAttackDamage);
						}
					}
				}	
			});
		}
	}
	
	private void setUpRPGActorHealth() {
		mEnemyHealthText.setTextColor(Color.parseColor("#00ffa0"));
		mEnemyHealthText.setTextSize(16);
		mEnemyHealthText.setBackgroundColor(Color.parseColor("#55000000"));
		mEnemyHealthText.setText(String.valueOf(mEnemy.getCurrentHealth()));
		mHeroHealthText.setText(String.valueOf(mAvatar.getCurrentHealth()));
		if (mCompanion != null) {
			mCompanionHealthText.setText(String.valueOf(mCompanion.getCurrentHealth()));
		}
	}
	private void removeCompanionViews() {
		LinearLayout layout = (LinearLayout) this.findViewById(R.id.companionHealthTextView).getParent();
		layout.removeView(findViewById(R.id.companionHealthTextView));
		layout = (LinearLayout) findViewById(R.id.companion_button).getParent();
		layout.removeView(findViewById(R.id.companion_button));
		
	}
	private void setCurrentCompanion() {
		CompanionDataSource companionHelper = new CompanionDataSource(this);
		ArrayList<Companion> allCompanions = companionHelper.getAllCompanions();
		for(Companion c : allCompanions)
		{
			if(c.isActiveCompanion())
			{
				mCompanion = c;
			}
		}
		
	}

	protected boolean AvatarIsIncapacitated() {
		boolean isIncapacitated = false;
		if (mAvatar.getCurrentHealth() <= 0 || mAvatar.isCharmed()) {
			isIncapacitated = true;
		}
		return isIncapacitated;
	}
	protected boolean CompanionIsIncapacitated() {
		boolean isIncapacitated = false;
		if (mCompanion.getCurrentHealth() <= 0 || mCompanion.isCharmed()) {
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
				Toast.makeText(world, String.valueOf("Serithrasas deals " + hero.takeDamage(mEnemy.attack())) 
						+ " damage to " + hero.getName() , Toast.LENGTH_SHORT).show();
				SpecialAttack specialAttack = mEnemy.specialAttack();
				if (specialAttack != null) {		
					int specialAttackDamage;
					if (specialAttack.getAttackType() == ClassAttack.WISDOM) {
						specialAttackDamage = mEnemy.takeSpecialAttack(specialAttack);
						displaySpecialAttackNotification(mEnemy, mEnemy, specialAttack, specialAttackDamage);
					}
					else {
						specialAttackDamage = hero.takeSpecialAttack(specialAttack);
						displaySpecialAttackNotification(mEnemy, hero, specialAttack, specialAttackDamage);
					}
					
				}
			}
		}
	}
	
	private void displaySpecialAttackNotification(RPGActor attacker, RPGActor receiver,
			SpecialAttack specialAttack, int damageTaken) {
		switch (specialAttack.getAttackType()) {
		case STRENGTH:
			Toast.makeText(this, attacker.getName() + " deals a mighty blow dealing " 
		+ damageTaken + " damage to " + receiver.getName(), Toast.LENGTH_SHORT).show();
			break;
		case DEXTERITY:
			Toast.makeText(this, attacker.getName() + " lets loose a flurry of " + specialAttack.getNumOfShots() + " attacks and deals " 
					+ damageTaken + " damage to " + receiver.getName(), Toast.LENGTH_SHORT).show();
			break;
		case CONSTITUTION:
			Toast.makeText(this, attacker.getName() + " attacks with sunder armor and reduces " + receiver.getName() + "'s defence by "
					+ specialAttack.getSunderAmount(), Toast.LENGTH_SHORT).show();
			break;
		case INTELLECT:
			Toast.makeText(this, attacker.getName() + " channels the energies of the arcane and deals " + damageTaken
					+ " damage to " + receiver.getName(), Toast.LENGTH_SHORT).show();
			break;
		case WISDOM:
			Toast.makeText(this, attacker.getName() + " gathers the energy from the aether to heal allies for " + 
					specialAttack.getHealAmount() + " for the next " + SpecialAttack.NUM_OF_HEAL_TURNS + " turns", Toast.LENGTH_SHORT).show();
			break;
		case CHARISMA:
			Toast.makeText(this, attacker.getName() + " enchants the target, stunning them for the next "
					+ specialAttack.getNumCharmTurns() + " turns" , Toast.LENGTH_SHORT).show();
			break;
		}
		
	}

	private boolean enemyIsIncapacitated() {
		if (mEnemy.isCharmed()) {
			return true;
		}
		return false;
	}
	
	private void performAfterTurnCleanup() {
		if (!mBattleOver) {
			mAvatar.cleanUpAfterBattleTurn();
			avatarHasAttacked = false;
			if (mCompanion != null) {
				mCompanion.cleanUpAfterBattleTurn();
				companionHasAttacked = false;
			}
			mEnemy.cleanUpAfterBattleTurn();
			updateRPGActorHealthViews();
			if (mEnemy.getCurrentHealth() <= 0) {
				mBattleOver = true;
				battleEnd("victory");
			}
			else if (mAvatar.getCurrentHealth() <= 0) {
				if (mCompanion == null || mCompanion.getCurrentHealth() <= 0) {
					mBattleOver  = true;
					battleEnd("game_over");
				}
			}
		}
	}
	

	private void updateRPGActorHealthViews() {
		mHeroHealthText.setText(String.valueOf(mAvatar.getCurrentHealth()));
		if (mCompanion != null) {
			mCompanionHealthText.setText(String.valueOf(mCompanion.getCurrentHealth()));
			mCompanionHealthText.invalidate();
		}
		mEnemyHealthText.setText(String.valueOf(mEnemy.getCurrentHealth()));
		mHeroHealthText.invalidate();
		mEnemyHealthText.invalidate();
		
	}
	
	private void battleEnd(String battleResult) {
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout parent = (LinearLayout) inflater.inflate(R.layout.activity_rpg_battle_screen, null);
		for (int i = 0; i < parent.getChildCount(); i++) {
			LinearLayout child = (LinearLayout) parent.getChildAt(i);
			int childId = child.getId();
			int fragmentContainerId = R.id.endgame_fragment_container;
			if (childId!= fragmentContainerId) {
				child.removeAllViews();
				parent.removeViewAt(i);
				i--;
			}
		}
		Wallet enemyWallet = mEnemy.getWallet();
		FragmentManager fm = this.getFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.endgame_fragment_container);
		if (fragment == null) {
			FragmentTransaction transaction = fm.beginTransaction();
			fragment = new RPGBattleEndFragment();
			Bundle args = new Bundle();
			args.putString(RPGBattleEndFragment.BATTLE_END, battleResult);
			args.putInt(ENEMY_WALLET, enemyWallet.getGold());
			fragment.setArguments(args);
			transaction.add(R.id.endgame_fragment_container, fragment, "EndGameFragment").commit();
			
		}
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
