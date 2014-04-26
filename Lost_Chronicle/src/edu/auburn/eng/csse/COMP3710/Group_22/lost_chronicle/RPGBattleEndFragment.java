package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RPGBattleEndFragment extends Fragment {
	public static final String BATTLE_END = "end_battle_image";
	private LinearLayout mLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_end_battle_screen, container);
		//mLayout = (LinearLayout) v.findViewById(R.id.rpg_battle_end_screen);		
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		String endScreenType = this.getArguments().getString(BATTLE_END);
		setUpBackground(endScreenType);
		
		mLayout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "Touch the screen to continue", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(RPGBattleEndFragment.this.getActivity(), RPGSelectionScreen.class);
				startActivity(intent);
				return false;
			}
		});
	}

	private void setUpBackground(String endScreenType) {
		if (endScreenType.equals("game_over")) {
			mLayout.setBackgroundResource(R.drawable.game_over);
		}
		else {
			mLayout.setBackgroundResource(R.drawable.victory);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public RPGBattleEndFragment() {
		// TODO Auto-generated constructor stub
	}

}
