package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AvatarInformationFragment extends Fragment {
	TextView mStrength, mIntellect, mDexterity, mConstitution, mWisdom, mCharisma;
	TextView  mHealth, mAttack, mMagicAttack, mDefence, mMagicalDefence, mDodge, mHit, mCrit;
	AvatarInfoCommunicator mCommunicator;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCommunicator = (AvatarInfoCommunicator) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_avatar_information, container, false);
		mStrength = (TextView) v.findViewById(R.id.strength_fieldA);
		mIntellect = (TextView) v.findViewById(R.id.intellect_fieldA);
		mDexterity = (TextView) v.findViewById(R.id.dexterity_fieldA);
		mConstitution = (TextView) v.findViewById(R.id.constitution_fieldA);
		mWisdom = (TextView) v.findViewById(R.id.wisdom_fieldA);
		mCharisma = (TextView) v.findViewById(R.id.charisma_fieldA);
		mHealth = (TextView) v.findViewById(R.id.health_fieldA);
		mAttack = (TextView) v.findViewById(R.id.attack_fieldA);
		mMagicAttack = (TextView) v.findViewById(R.id.magic_attack_fieldA);
		mDefence = (TextView) v.findViewById(R.id.defence_fieldA);
		mMagicalDefence = (TextView) v.findViewById(R.id.magic_defence_fieldA);
		mDodge = (TextView) v.findViewById(R.id.dodge_fieldA);
		mHit = (TextView) v.findViewById(R.id.hit_fieldA);
		mCrit = (TextView) v.findViewById(R.id.crit_fieldA);
		return v;
	}

	@Override
	public void onStart() {
		super.onStart();
		Avatar avatar = mCommunicator.getAvatar();
		if (avatar != null) {
			Stat statStruct = avatar.getStatStruct();
			mStrength.setText(String.valueOf(statStruct.getStrength()));
			mIntellect.setText(String.valueOf(statStruct.getIntellect()));
			mDexterity.setText(String.valueOf(statStruct.getDexterity()));
			mConstitution.setText(String.valueOf(statStruct.getConstitution()));
			mWisdom.setText(String.valueOf(statStruct.getWisdom()));
			mCharisma.setText(String.valueOf(statStruct.getCharisma()));
		}
	}
	
}