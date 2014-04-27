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
		int numOfTurnsCharmed = Math.abs((rand.nextInt() % 3)) + 1;
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
	
	///SpecialAttack generateSpecialAbility(); Takes a percentage pool of 30% and divides it amongst the 6 special attack types
	//By this I mean that it will distribute the 30% special attack chance stat proportionally to the special attack associated with strength,
	//the special attack associated with dexterity, and so on (SUBFUNCTION called generateSpecialAbilityChance which returns an array of percentages).
	//Then after getting those percentages, we will determine which special ability happens by the following formula
	//RANDOM_CHANCE + SPECIAL_ATTACK_CHANCE >= 100 ? LAUNCH_SPECIAL : DO_NOTHING
	//To make this slightly fairier, we will order the special ability selections by highest stat to lowest stat. This means that if my strength
	//is my highest stat, then not only will it have the highest chance of going off, but it will also be the first special attack evaluated to see if
	//it happens. Once we see that a special ability is going to go off, we return that special attack. Otherwise we will return null.
	//Quick function breakdown by name
	//SpecialAttack generateSpecialAbility() --> int[] disperseBonusChancePool() --> int[] calculateSpecialAbilityChance 
	// --> sort int[] in nondecreasing order --> for each int i in the int[] do SpecialAttack launchSpecialAttack(int[i], int i) -->  if 
	//percentage >= 100 then enter case table to choose the special ability returned based on the int i passed.
	
}
