package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Companion_Activity extends Activity {
	TextView mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_companion_activity_screen);
		mView = (TextView) this.findViewById(R.id.textView1);
	}
	protected void onStart() {
		super.onStart();
		mView.setText(this.getIntent().getStringExtra("test"));
	}
}
