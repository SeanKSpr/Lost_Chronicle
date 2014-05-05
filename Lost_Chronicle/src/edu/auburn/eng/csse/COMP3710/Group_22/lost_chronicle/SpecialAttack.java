package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Arrays;
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
			numOfShots = Math.abs((rand.nextInt() % 3)) + 3;
		}
		
		public void generateCharmTurns() {
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis() * 23);
			numCharmTurns = Math.abs((rand.nextInt() % 2)) + 2;
		}
		public int getNUM_OF_HEAL_TURNS() {
			return NUM_OF_HEAL_TURNS;
		}
		
		/**
		 * Generates and returns the SpecialAttack which had the highest chance of going off.
		 * If none of the SpecialAttack chances reached 100% or above, then null is returned.
		 * @return new SpecialAttack or null if none of the chance percentages reached 100% or above.
		 */
		public SpecialAttack generateSpecialAbility(Stat rpgActorStats, Attribute rpgActorAttributes) {
			SpecialAttack specialAttack = new SpecialAttack();
			int[] statDispersal = disperseBonusChancePool(rpgActorStats);
			SpecialAttackPercentage[] attackChanceArray = calculateSpecialAbilityChance(statDispersal);
			Arrays.sort(attackChanceArray, new SpecAttackPercenComparator());
			for (int i = 0; i < attackChanceArray.length; i++) {
				specialAttack = new SpecialAttack();
				SpecialAttackPercentage percentage = attackChanceArray[i];
				specialAttack = specialAttack.launchSpecialAttack(percentage);
				if (specialAttack != null) {
					setUpSpecialAttack(specialAttack, rpgActorStats, rpgActorAttributes);
					return specialAttack;
				}
			}
			return null;
		}

		/**
		 * Calculates the total percentage for a particular SpecialAttack to occur. 
		 * 
		 * @param statDispersal	the array of Stat percentages which are used as SpecialAttack chance modifiers
		 * @return An array of the actual percentages for a particular SpecialAttack to go off.
		 */
		private SpecialAttackPercentage[] calculateSpecialAbilityChance(int[] statDispersal) {
			SpecialAttackPercentage[] specialAbilityChanceArray = new SpecialAttackPercentage[statDispersal.length];
			Random rand = new Random();
			for (int i = 0; i < specialAbilityChanceArray.length; i++) {
				rand.setSeed(System.nanoTime());
				int baseChance = Math.abs((rand.nextInt() % 100)) + 1;
				specialAbilityChanceArray[i] = new SpecialAttackPercentage(statDispersal[i], i);
				int totalChance = baseChance + statDispersal[i];
				specialAbilityChanceArray[i].setPercentChance(totalChance);
			}
			return specialAbilityChanceArray;
		}
		
		/**
		 * Creates an array of Stat percentages based on each stat's relative proportion to the total stat pool.
		 * The sum of these percentages amount to roughly 30 to represent a total 30% chance of a SpecialAttack being activated.
		 * The percentages are used as SpecialAttack chance modifiers.
		 * 
		 * @return an array of Stat percentages.
		 */
		protected int[] disperseBonusChancePool(Stat statStruct) {
			final int BONUS_DISPERSAL_POOL = 30;
			int statPool = statStruct.getStatPool();
			int strPercentage =  (int) Math.ceil(((double) statStruct.getStrength() / statPool * BONUS_DISPERSAL_POOL));
			int dexPercentage = (int) Math.ceil(((double) statStruct.getDexterity() / statPool * BONUS_DISPERSAL_POOL));
			int conPercentage = (int)  Math.ceil(((double) statStruct.getConstitution() / statPool * BONUS_DISPERSAL_POOL));
			int intPercentage = (int)  Math.ceil(((double) statStruct.getIntellect() / statPool * BONUS_DISPERSAL_POOL));
			int wisPercentage = (int)  Math.ceil(((double) statStruct.getWisdom() / statPool * BONUS_DISPERSAL_POOL));
			int chaPercentage = (int)  Math.ceil(((double) statStruct.getCharisma() / statPool * BONUS_DISPERSAL_POOL));
			int[] dispersalArray = {strPercentage, dexPercentage, conPercentage, intPercentage, wisPercentage, chaPercentage};
			return dispersalArray;
		}
		
		private void setUpSpecialAttack(SpecialAttack specialAttack, Stat rpgActorStats, Attribute rpgActorAttributes) {
			if (specialAttack != null) {
				switch (specialAttack.getAttackType()) {
				case STRENGTH :
					setUpHeroicStrike(specialAttack, rpgActorStats, rpgActorAttributes);
					break;
				case DEXTERITY :
					setUpRapidShot(specialAttack, rpgActorStats, rpgActorAttributes);
					break;
				case CONSTITUTION :
					setUpSunderArmor(specialAttack, rpgActorStats, rpgActorAttributes);
					break;
				case INTELLECT :
					setUpArcaneBarrage(specialAttack, rpgActorStats, rpgActorAttributes);
					break;
				case WISDOM :
					setUpLifeBloom(specialAttack, rpgActorStats, rpgActorAttributes);
					break;
				case CHARISMA :
					setUpCharm(specialAttack, rpgActorStats, rpgActorAttributes);
					break;	
				}
			}
		}

		private void setUpHeroicStrike(SpecialAttack heroicStrike, Stat rpgActorStats, Attribute rpgActorAttributes) {
			int attack = rpgActorAttributes.getAttack();
			int magicAttack = rpgActorAttributes.getMagicAttack();
			heroicStrike.setMagicDmg((int)(1.5 * attack + magicAttack));
			heroicStrike.setPhysDmg((int) (attack + 1.5 * magicAttack));
		}
		
		private void setUpRapidShot(SpecialAttack rapidShot, Stat rpgActorStats, Attribute rpgActorAttributes) {
			rapidShot.setPhysDmg((int) Math.round(rpgActorAttributes.getAttack() / 2.5));
			rapidShot.generateNumOfShots();
		}
		
		private void setUpSunderArmor(SpecialAttack sunderArmor, Stat rpgActorStats, Attribute rpgActorAttributes) {
			int sunderAmount = 0;
			int defence = rpgActorAttributes.getDefence();
			int magicalDefence = rpgActorAttributes.getMagicDefence();
			sunderAmount = defence + magicalDefence;
			sunderArmor.setSunderAmount(sunderAmount);
		}
		
		private void setUpArcaneBarrage(SpecialAttack arcaneBarrage, Stat rpgActorStats, Attribute rpgActorAttributes) {
			int magicDmg = rpgActorAttributes.getMagicAttack();
			int intellect = rpgActorStats.getIntellect();
			int wisdom = rpgActorStats.getWisdom();
			arcaneBarrage.setMagicDmg((int) (magicDmg * 2.5 + intellect * 1.5 + wisdom * 1.5));
		}
		
		private void setUpLifeBloom(SpecialAttack lifeBloom, Stat rpgActorStats, Attribute rpgActorAttributes) {
			int magicHeal = rpgActorAttributes.getMagicAttack();
			int magicDefence = rpgActorAttributes.getMagicDefence();
			int intellect = rpgActorStats.getIntellect();
			int wisdom = rpgActorStats.getWisdom();
			magicHeal = (int) (magicHeal / 2  + magicDefence / 2 + intellect + wisdom * 1.5);
			lifeBloom.setHealAmount(magicHeal);
		}
		
		private void setUpCharm(SpecialAttack charm, Stat rpgActorStats, Attribute rpgActorAttributes) {
			charm.generateCharmTurns();
		}
}
