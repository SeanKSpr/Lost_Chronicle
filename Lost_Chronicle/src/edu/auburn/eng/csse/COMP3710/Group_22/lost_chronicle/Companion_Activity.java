package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Companion_Activity extends FragmentActivity implements StoreCommunicator, KanojoInfoCommunicator {
	TextView mView;
	String mCurrentCompanionName;
	Jukebox music;
	private static final String KANOJO_STORE_KEY = "KanojoStore";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_companion_activity_screen);
		setUpBackground();
		if(music == null)
		{
			music = new Jukebox(this);
		}
	}
	private void setUpBackground() {
		Companion currentCompanion = getCurrentCompanion();
		LayoutInflater inflater;
		LinearLayout layout;
		if (currentCompanion != null) {
			inflater = getLayoutInflater();
			layout = (LinearLayout) inflater.inflate(R.layout.activity_companion_activity_screen, null);
			layout.setBackgroundResource((int) currentCompanion.getFullViewResource());
		}
		else {
			inflater = getLayoutInflater();
			layout = (LinearLayout) inflater.inflate(R.layout.activity_companion_activity_screen, null);
			layout.setBackgroundResource(R.drawable.no_companion);
		}
		this.setContentView(layout);
		launchInformationFragment();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		music.start(Jukebox.COMPANION_SCREEN);
	}
	
	@Override
	protected void onPause() {
		music.stop();
		super.onPause();
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	private void launchInformationFragment() {
		FragmentManager fm = getFragmentManager();
		Fragment statFragment = fm.findFragmentById(R.id.store_fragment_container);
		if (statFragment == null) {
			statFragment = new KanojoInformationFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.store_fragment_container, statFragment).commit();
		}
		else {
			fm.beginTransaction().remove(fm.findFragmentById(R.id.store_fragment_container)).commit();
			statFragment = new KanojoInformationFragment();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.store_fragment_container, statFragment).commit();
		}
		
	}
	//contacts database to get all the companions
	//takes each element within that arraylist of companions and places it in an array of Purchasables
	@Override
	public ArrayList<Purchasable> getPurchasables() {
		CompanionDataSource companionDBHelper = new CompanionDataSource(this);
		ArrayList<Companion> companionList = companionDBHelper.getAllCompanions();
		ArrayList<Purchasable> purchasableList = new ArrayList<Purchasable>();
		for (Companion companion : companionList) {
			purchasableList.add(companion);
		}
		return purchasableList;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.home_button:
				Intent intent = new Intent(Companion_Activity.this, Selection_Screen.class);
				Companion_Activity.this.startActivity(intent);
				return true;
			case R.id.store_button:
				launchKanojoStore();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	public void onBackPressed() {
		FragmentManager fm = getFragmentManager();
		if (fm.getBackStackEntryCount() > 0) {
			fm.popBackStack();
		}
		else {
			super.onBackPressed();
		}
	}
	
	private void launchKanojoStore() {
		FragmentManager fm = getFragmentManager();
		int topOfStack = fm.getBackStackEntryCount() - 1;
		if (fm.getBackStackEntryCount() > 0 && !fm.getBackStackEntryAt(topOfStack).getName().equals(KANOJO_STORE_KEY)) {
			KanojoStore kanojoStoreFragment = new KanojoStore();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.store_fragment_container, kanojoStoreFragment).addToBackStack(KANOJO_STORE_KEY).commit();
		}
		else if (fm.getBackStackEntryCount() == 0) {
			KanojoStore kanojoStoreFragment = new KanojoStore();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.replace(R.id.store_fragment_container, kanojoStoreFragment).addToBackStack(KANOJO_STORE_KEY).commit();
		}
	}
	
	@Override
	public void updatePurchasable(Purchasable purchasable) {
		CompanionDataSource companionDBHelper = new CompanionDataSource(this);
		Companion companion = (Companion) purchasable;
		companion.setPurchased(true);
		Companion oldCurrentCompanion = getCurrentCompanion();
		if (oldCurrentCompanion != null) {
			oldCurrentCompanion.setActiveCompanion(false);
			companionDBHelper.updateCompanion(oldCurrentCompanion);
			int oldId = (int) oldCurrentCompanion.getId();
			oldCurrentCompanion = companionDBHelper.getCompanion(oldId);
			if (oldCurrentCompanion.getId() != companion.getId()) {
				companion.setActiveCompanion(true);
				companionDBHelper.updateCompanion(companion);
			}
		}
		else {
			companion.setActiveCompanion(true);
			companionDBHelper.updateCompanion(companion);
		}
		setUpBackground();
	}
	
	@Override
	public Companion getCurrentCompanion() {
		CompanionDataSource companionDBHelper = new CompanionDataSource(this);
		ArrayList<Companion> companionList = companionDBHelper.getAllCompanions();
		Companion currentCompanion = null;
		for (Companion companion : companionList) {
			if (companion.isActiveCompanion()) {
				return companion;
			}
		}
		return currentCompanion;
	}
	@Override
	public void performTransaction(int cost) {
		//get Avatar object, subtract cost from his current gold, update Avatar in database
		AvatarDataSource avatarDBHelper = new AvatarDataSource(this);
		Avatar avatar = avatarDBHelper.getAvatar();
		avatar.getWallet().subtractGold(cost);
		avatarDBHelper.updateAvatar(avatar);
	}
	@Override
	public int getWallet() {
		// TODO get Avatar object, return the amount of gold he has.
		AvatarDataSource avatarDBHelper = new AvatarDataSource(this);
		Avatar avatar = avatarDBHelper.getAvatar();
		return avatar.getWallet().getGold();
	}
	@Override
	public Purchasable getPurchasable(int Id) {
		CompanionDataSource companionDBHelper = new CompanionDataSource(this);
		Companion companion = companionDBHelper.getCompanion(Id);
		return companion;
	}	
}
