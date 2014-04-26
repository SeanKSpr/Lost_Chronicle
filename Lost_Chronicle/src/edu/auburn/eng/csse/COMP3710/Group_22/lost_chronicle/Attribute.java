package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.text.DecimalFormat;

public class Attribute {
	private int mAttack;
	private int mDefence;
	private double mDodge;
	private int mHealth;
	private double mHit;
	private double mCrit;
	private int mMagicDefence;
	private int mMagicAttack;
	
	public Attribute() {
		// TODO Auto-generated constructor stub
	}

	public int getAttack() {
		return mAttack;
	}

	public void setAttack(int mAttack) {
		this.mAttack = mAttack;
	}

	public int getDefence() {
		return mDefence;
	}

	public void setDefence(int mDefence) {
		this.mDefence = mDefence;
	}

	public double getDodge() {
		return mDodge;
	}

	public void setDodge(double mDodge) {
		this.mDodge = mDodge;
	}

	public int getHealth() {
		return mHealth;
	}

	public void setHealth(int mHealth) {
		this.mHealth = mHealth;
	}

	public double getHit() {
		return mHit;
	}

	public void setHit(double mHit) {
		this.mHit = mHit;
	}

	public int getMagicDefence() {
		return mMagicDefence;
	}

	public void setMagicDefence(int mMagicDefence) {
		this.mMagicDefence = mMagicDefence;
	}

	public int getMagicAttack() {
		return mMagicAttack;
	}

	public void setMagicAttack(int mMagicAttack) {
		this.mMagicAttack = mMagicAttack;
	}
	
	public void generateAttributes (Stat statStruct) {
		int strength = statStruct.getStrength();
		int dexterity = statStruct.getDexterity();
		int constitution = statStruct.getConstitution();
		int intellect = statStruct.getIntellect();
		int wisdom = statStruct.getWisdom();
		int charisma = statStruct.getCharisma();
		
		generateAttack(strength, dexterity, charisma);
		generateMagicAttack(intellect, wisdom, charisma);
		generateDefence(constitution, charisma, strength);
		generateMagicDefence(constitution, wisdom, charisma, intellect);
		generateCrit(dexterity, charisma, wisdom);
		generateHit(dexterity, charisma, intellect);
		generateDodge(dexterity, charisma, constitution);
	}

	private void generateDodge(int dexterity, int charisma, int constitution) {
		mDodge = dexterity * .8 / 2 + charisma * .5 / 2 + constitution * .5 / 2;
		DecimalFormat df = new DecimalFormat("#.00");
		String dodgeStr = df.format(mDodge);
		mDodge = Double.parseDouble(dodgeStr);
	}

	private void generateHit(int dexterity, int charisma, int intellect) {
		mHit = dexterity * .8 / 2 + charisma * .5 / 2 + intellect * .3 / 2;
		DecimalFormat df = new DecimalFormat("#.00");
		String hitStr = df.format(mHit);
		mHit = Double.parseDouble(hitStr);
	}

	private void generateCrit(int dexterity, int charisma, int wisdom) {
		mCrit = dexterity * .9 / 2 + charisma * .4 / 2 + wisdom * .3 / 2;
		DecimalFormat df = new DecimalFormat("#.00");
		String critStr = df.format(mCrit);
		mCrit = Double.parseDouble(critStr);
	}

	private void generateMagicDefence(int constitution, int wisdom,
			int charisma, int intellect) {
		mMagicDefence = (int) (constitution + 2 * wisdom + charisma * .4 + intellect * .5);
	}

	private void generateDefence(int constitution, int charisma, int strength) {
		mDefence = (int) (constitution * 1.5 + charisma * .4 + strength * .5);		
	}

	private void generateMagicAttack(int intellect, int wisdom, int charisma) {
		mMagicAttack = (int) (intellect * 2 + wisdom * .7 + charisma * .4);
	}

	private void generateAttack(int strength, int dexterity, int charisma) {
		mAttack = (int) (strength * 2.5 + dexterity * .5 + charisma * .4);
	}

	public double getCrit() {
		return mCrit;
	}

	public void setCrit(double mCrit) {
		this.mCrit = mCrit;
	}
	
	public void lowerDefence(int amount) {
		this.mDefence -= amount;
		if (this.mDefence < 0) {
			mDefence = 0;
		}
	}
	
}
