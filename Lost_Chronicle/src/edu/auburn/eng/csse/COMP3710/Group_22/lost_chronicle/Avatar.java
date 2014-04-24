package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

public class Avatar extends RPGActor {
	private Wallet mWallet;
	private int mBattlesWon;
	public Avatar() {
		mWallet = new Wallet();
	}
	
	public Wallet getWallet() {
		return mWallet;
	}

	public int getBattlesWon() {
		return mBattlesWon;
	}
	
	public void incrementBattlesWon() {
		mBattlesWon++;
	}
	
	public void setBatlesWon(int battlesWon) {
		mBattlesWon = battlesWon;
	}
	
	public int arcaneBarrage() {
		int magicDmg = this.attributeStruct.getMagicAttack();
		int intellect = this.statStruct.getIntellect();
		int wisdom = this.statStruct.getWisdom();
		
		return (int)(magicDmg * 1.5 + intellect * 1.5 + wisdom * 1.5);
	}
	
	public int lifebloom() {
		int magicHeal = this.attributeStruct.getMagicAttack();
		int magicDefence = this.attributeStruct.getMagicDefence();
		int intellect = this.statStruct.getIntellect();
		int wisdom = this.statStruct.getWisdom();
		magicHeal = (int) (magicHeal * 1.5 + magicDefence * 2 + intellect * 1.2 + wisdom * 1.2);
		return magicHeal;
		
	}
	
	public int sunderArmor() {
		int sunderAmount = 0;
		int defence = this.attributeStruct.getDefence();
		int magicalDefence = this.attributeStruct.getMagicDefence();
		sunderAmount = defence + magicalDefence;
		return sunderAmount;
	}
	
	public int charm() {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		int numOfTurnsCharmed = (rand.nextInt() % 3) + 1;
		return numOfTurnsCharmed;
		
	}
	
	public int heroicStrike() {
		int attack = this.attributeStruct.getAttack();
		return (int)(2.5 * attack);
		
	}
	
	//Attack Struct
	//int physDmg
	//int magicDmg
	//boolean isCrit
	//int chanceToHit
	
	//RPGActor.attack(Attack attackStruct); //calculate out if you are going to do a crit and return your physDmg
		//and magicDmg as well as whether the attack is a crit or not and the hit chance
	//RPGActor.takeDamage(Attack attackStruct); //calculate out how much damage you are going to take from getting
		//hit by that attack. Reduce the damage of the physDmg by your defence, the magicDmg by your magicDefence,
		//and then calculate to see if you are going to be hit by that attack based on your dodge. Then update your
		//health accordingly.
	
	public int rapidShot() {
		int rapidShot = 0;
		for (int i = 0; i < 5; i++) {
			rapidShot += quickAttack();
		}
		return rapidShot;
	}
	
	public int quickAttack() {
		int thirdOfAttack = this.getAttributeStruct().getAttack() / 3;
		return thirdOfAttack;//needs to include the chance to crit like a regular attack along with the other sides of it
	}
}
