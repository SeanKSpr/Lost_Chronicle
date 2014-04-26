package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RPGBattleEndFragment extends Fragment {
	public static final String BATTLE_END = "end_battle_image";
	private final RPGBattleEndFragment thisFragment = this;
	private LinearLayout mLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_end_battle_screen, container, false);
		mLayout = (LinearLayout) v;
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		mLayout.setLayoutParams(params);
		mLayout.invalidate();
		String battleResult = this.getArguments().getString(BATTLE_END);
		int gold = this.getArguments().getInt(RPG_Battle.ENEMY_WALLET);
		setUpBackground(battleResult);
		updateAvatarGold(gold, battleResult);
		Toast.makeText(getActivity(), "Touch the screen to continue", Toast.LENGTH_LONG).show();
		mLayout.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				thisFragment.getActivity().onBackPressed();
				return false;
			}
		});
	}
	private void updateAvatarGold(int gold, String battleResult) {
		AvatarDataSource avatarDBHelper = new AvatarDataSource(this.getActivity());
		Avatar avatar = avatarDBHelper.getAvatar();
		if (battleResult.equals("game_over")) {
			avatar.getWallet().subtractGold(gold);
		}
		else {
			avatar.getWallet().addGold(gold);
		}
		avatarDBHelper.updateAvatar(avatar);
	}
	private void setUpBackground(String battleResult) {
		if (battleResult.equals("game_over")) {
			mLayout.setBackgroundResource(R.drawable.game_over);
		}
		else {
			mLayout.setBackgroundResource(R.drawable.victory_land);
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
