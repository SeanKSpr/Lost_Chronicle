package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

import android.util.Log;


public class SpecialAttack extends Attack {
	private ClassAttack attackType;
	private int numCharmTurns;
	private int sunderAmount;
	private int healAmount;
	private int numOfShots;
	public static final int NUM_OF_HEAL_TURNS = 3;
		
	
		public SpecialAttack() {
			
		}
		public ClassAttack getAttackType() {
			return attackType;
		}
		public void setAttackType(ClassAttack attackType) {
			this.attackType = attackType;
		}
		public int getNumCharmTurns() {
			return numCharmTurns;
		}
		public void setNumCharmTurns(int numCharmTurns) {
			this.numCharmTurns = numCharmTurns;
		}
		public int getSunderAmount() {
			return sunderAmount;
		}
		public void setSunderAmount(int sunderAmount) {
			this.sunderAmount = sunderAmount;
		}
		public SpecialAttack arcaneBarrage() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.INTELLECT;
			return specialAttack;
		}
		public SpecialAttack lifeBloom() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.WISDOM;
			return specialAttack;
		}
		public SpecialAttack sunderArmor() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.CONSTITUTION;
			return specialAttack;
		}
		public SpecialAttack charm() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.CHARISMA;
			return specialAttack;
		}
		public SpecialAttack heroicStrike() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.STRENGTH;
			return specialAttack;
		}
		public SpecialAttack rapidShot() {
			SpecialAttack specialAttack = new SpecialAttack();
			specialAttack.attackType = ClassAttack.DEXTERITY;
			return specialAttack;
		}
		public int getHealAmount() {
			return healAmount;
		}
		public void setHealAmount(int healAmount) {
			this.healAmount = healAmount;
		}
		
		public SpecialAttack launchSpecialAttack(SpecialAttackPercentage attackStruct) {
			int abilityType = attackStruct.getAbilityType();
			int percentChance = attackStruct.getPercentChance();
			if (percentChance >= 100) {
			switch (abilityType) {
				case 0:
					return heroicStrike();
				case 1:
					return rapidShot();
				case 2:
					return sunderArmor();
				case 3:
					return arcaneBarrage();
				case 4:
					return lifeBloom();
				case 5:
					return charm();
				default:
					Log.e("launchSpecialAttack", "Invalid attack identifier found.");
				}
			}
			return null;
			
		}
		public int getNumOfShots() {
			return numOfShots;
		}
		public void setNumOfShots(int numOfShots) {
			this.numOfShots = numOfShots;
		}
		public void generateNumOfShots() {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis() * 23);
			numOfShots = (rand.nextInt() % 3) + 2;
		}
		
		public void generateCharmTurns() {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis() * 23);
			numCharmTurns = (rand.nextInt() % 2) + 1;
		}
		public int getNUM_OF_HEAL_TURNS() {
			return NUM_OF_HEAL_TURNS;
		}
		
}
