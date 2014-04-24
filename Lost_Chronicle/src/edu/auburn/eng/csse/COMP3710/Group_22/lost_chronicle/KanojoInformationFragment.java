package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
		mStrength = (TextView) v.findViewById(R.id.strength_field);
		mIntellect = (TextView) v.findViewById(R.id.intellect_field);
		mDexterity = (TextView) v.findViewById(R.id.dexterity_field);
		mConstitution = (TextView) v.findViewById(R.id.constitution_field);
		mWisdom = (TextView) v.findViewById(R.id.wisdom_field);
		mCharisma = (TextView) v.findViewById(R.id.charisma_field);
		mHealth = (TextView) v.findViewById(R.id.health_field);
		mAttack = (TextView) v.findViewById(R.id.attack_field);
		mDefence = (TextView) v.findViewById(R.id.defence_field);
		mMagicalDefence = (TextView) v.findViewById(R.id.magic_defence_field);
		mDodge = (TextView) v.findViewById(R.id.dodge_field);
		mHit = (TextView) v.findViewById(R.id.hit_field);
		mCrit = (TextView) v.findViewById(R.id.crit_field);
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		Companion currentCompanion = mCommunicator.getCurrentCompanion();
		if (currentCompanion != null) {
			Stat statStruct = currentCompanion.getStatStruct();
			mStrength.setText(String.valueOf(statStruct.getStrength()));
			mIntellect.setText(String.valueOf(statStruct.getIntellect()));
			mDexterity.setText(String.valueOf(statStruct.getDexterity()));
			mConstitution.setText(String.valueOf(statStruct.getConstitution()));
			mWisdom.setText(String.valueOf(statStruct.getWisdom()));
			mCharisma.setText(String.valueOf(statStruct.getCharisma()));
		}
		else {
			TextView textView;
			RelativeLayout layout = (RelativeLayout) this.getActivity().findViewById(R.id.kanojo_info_layout);
			for (int i = 0; i < layout.getChildCount(); i++) {
				if (layout.getChildAt(i) instanceof TextView) {
					textView = (TextView) layout.getChildAt(i);
					textView.setText("");
				}
			}
		}
		
	}
	
}
