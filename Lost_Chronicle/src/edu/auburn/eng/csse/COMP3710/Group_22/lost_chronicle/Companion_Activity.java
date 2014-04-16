package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


import java.util.ArrayList;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class Companion_Activity extends FragmentActivity implements StoreCommunicator {
	TextView mView;
	String mCurrentCompanionName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_companion_activity_screen);
		mView = (TextView) this.findViewById(R.id.item_name_text);
	}
	protected void onStart() {
		super.onStart();
		mView.setText(this.getIntent().getStringExtra("test"));
	}
	
	@Override
	public ArrayList<Purchasable> getPurchasables() {
		Companion grea1 = new Companion();
		Companion grea2 = new Companion();
		Companion grea3 = new Companion();
		Companion grea4 = new Companion();
		
		grea1.setId(1);
		grea1.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail1);
		grea1.setmName("Grea the Dragonborn");
		grea2.setId(2);
		grea2.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail2);
		grea2.setmName("Grea the Dragonborn");
		grea3.setId(3);
		grea3.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail3);
		grea3.setmName("Grea the Dragonborn");
		grea4.setId(4);
		grea4.setThumbnailResource(R.drawable.grea_the_dragonborn_thumbnail4);
		grea4.setmName("Grea the Dragonborn");
		ArrayList<Purchasable> purchasableArray = new ArrayList<Purchasable>();
		purchasableArray.add(grea1);
		purchasableArray.add(grea2);
		purchasableArray.add(grea3);
		purchasableArray.add(grea4);
		return purchasableArray;
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
				Companion_Activity.this.launchActivity(intent);
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
		Fragment kanojoStoreFragment = fm.findFragmentById(R.id.store_fragment_container);
		if (kanojoStoreFragment == null) {
			kanojoStoreFragment = new KanojoStore();
			FragmentTransaction transaction = fm.beginTransaction();
			transaction.add(R.id.store_fragment_container, kanojoStoreFragment).addToBackStack(null).commit();
		}
	}
	
	private void launchActivity(Intent intent) {
		startActivity(intent);
	}
	
}
