package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class KanojoInformationFragment extends Fragment {
	TextView mStrength, mIntellect, mDexterity, mConstitution, mWisdom, mCharisma;
	TextView  mHealth, mAttack, mMagicAttack, mDefence, mMagicalDefence, mDodge, mHit, mCrit;
	KanojoInfoCommunicator mCommunicator;
	TextView mName;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mCommunicator = (KanojoInfoCommunicator) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_kanojo_information, container, false);
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
		Companion currentCompanion = mCommunicator.getCurrentCompanion();
		if (currentCompanion != null) {
			Stat statStruct = currentCompanion.getStatStruct();
			Attribute attrStruct = currentCompanion.getAttributeStruct();
			mStrength.setText(String.valueOf(statStruct.getStrength()));
			mIntellect.setText(String.valueOf(statStruct.getIntellect()));
			mDexterity.setText(String.valueOf(statStruct.getDexterity()));
			mConstitution.setText(String.valueOf(statStruct.getConstitution()));
			mWisdom.setText(String.valueOf(statStruct.getWisdom()));
			mCharisma.setText(String.valueOf(statStruct.getCharisma()));
			mAttack.setText(String.valueOf(attrStruct.getAttack()));
			mMagicAttack.setText(String.valueOf(attrStruct.getMagicAttack()));
			mDefence.setText(String.valueOf(attrStruct.getDefence()));
			mMagicalDefence.setText(String.valueOf(attrStruct.getMagicDefence()));
			mDodge.setText(String.valueOf(attrStruct.getDodge()));
			mHit.setText(String.valueOf(attrStruct.getHit()));
			mCrit.setText(String.valueOf(attrStruct.getCrit()));
			mHealth.setText(String.valueOf(attrStruct.getHealth()));
			setUpNameField(currentCompanion);
			
		}
		else {
			TextView textView;
			RelativeLayout layout = (RelativeLayout) this.getActivity().findViewById(R.id.avatar_info_layout);
			for (int i = 0; i < layout.getChildCount(); i++) {
				if (layout.getChildAt(i) instanceof TextView) {
					textView = (TextView) layout.getChildAt(i);
					textView.setText("");
				}
			}
		}
	}

	private void setUpNameField(Companion currentCompanion) {
		TextView nameView = new TextView(this.getActivity());
		RelativeLayout parent = (RelativeLayout) this.getActivity().findViewById(R.id.avatar_info_layout);
		TextView sibling = (TextView) this.getActivity().findViewById(R.id.health_title_viewA);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ABOVE, sibling.getId());
		parent.addView(nameView, params);
		nameView.setText("Name           " + currentCompanion.getName());
		nameView.setTextSize(22);
		nameView.setTextColor(Color.parseColor("#ffffff"));
	}
	
}
