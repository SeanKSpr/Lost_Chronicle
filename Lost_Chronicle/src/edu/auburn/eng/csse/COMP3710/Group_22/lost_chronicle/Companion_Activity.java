package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


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
	@Override
	public String getKanojoName(KanojoStore store) {
		return null;
	}
	
}
