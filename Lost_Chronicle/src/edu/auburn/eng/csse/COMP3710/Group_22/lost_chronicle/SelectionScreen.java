package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.util.Log;
public class SelectionScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection_screen);
      Log.i("TAG", "Git hub testing part dos");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection_screen, menu);
		return true;
	}

}
