package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class KanojoInformationFragment extends Fragment {
	TextView mStrength, mIntellect, mDexterity, mConstitution, mWisdom, mCharisma;
	TextView  mHealth, mAttack, mDefence, mMagicalDefence, mDodge, mHit, mCrit;
	KanojoInfoCommunicator mCommunicator;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCommunicator = (KanojoInfoCommunicator) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_kanojo_information, container, false);
		TextView mStrength = (TextView) v.findViewById(R.id.strength_field);
		TextView mIntellect = (TextView) v.findViewById(R.id.intellect_field);
		TextView mDexterity = (TextView) v.findViewById(R.id.dexterity_field);
		TextView mConstitution = (TextView) v.findViewById(R.id.constitution_field);
		TextView mWisdom = (TextView) v.findViewById(R.id.wisdom_field);
		TextView mCharisma = (TextView) v.findViewById(R.id.charisma_field);
		TextView mHealth = (TextView) v.findViewById(R.id.health_field);
		TextView mAttack = (TextView) v.findViewById(R.id.attack_field);
		TextView mDefence = (TextView) v.findViewById(R.id.defence_field);
		TextView mMagicalDefence = (TextView) v.findViewById(R.id.magic_defence_field);
		TextView mDodge = (TextView) v.findViewById(R.id.dodge_field);
		TextView mHit = (TextView) v.findViewById(R.id.hit_field);
		TextView mCrit = (TextView) v.findViewById(R.id.crit_field);
		return v;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
}
