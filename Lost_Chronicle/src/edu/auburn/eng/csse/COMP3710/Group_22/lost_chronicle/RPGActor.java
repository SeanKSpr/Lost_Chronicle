package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Arrays;
import java.util.Random;


public class RPGActor {
	protected String mName;
	protected int mLevel;
	protected long mSpriteResource;
	protected Stat statStruct;
	protected Attribute attributeStruct;
	protected long id;
	private Attack mAttack;
	private int currentHealth;
	private int turnsCharmed;
	private int turnsToBeHealed;
	public RPGActor() {
		statStruct = new Stat();
		attributeStruct = new Attribute();
	}
	
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public int getLevel() {
		return mLevel;
	}
	public void setLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public long getSpriteReource() {
		return mSpriteResource;
	}
	public void setSpriteResource(long mSpriteResource) {
		this.mSpriteResource = mSpriteResource;
	}
	public Stat getStatStruct() {
		return statStruct;
	}
	public void setStatStruct(Stat statStruct) {
		this.statStruct = statStruct;
	}
	public Attribute getAttributeStruct() {
		return attributeStruct;
	}
	public void setAttributeStruct(Attribute attributeStruct) {
		this.attributeStruct = attributeStruct;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void calculateLevel() {
		mLevel = (int) Math.ceil(statStruct.getStatPool() / 10);
	}
	public void calculateHealth() {
		attributeStruct.setHealth(mLevel * 100 + statStruct.getConstitution() * 17);
		this.setCurrentHealth(attributeStruct.getHealth());
	}

	public Attack getAttack() {
		return mAttack;
	}

	public void setAttack(Attack mAttack) {
		this.mAttack = mAttack;
	}
	
	public Attack attack() {
		Attack attack = new Attack();
		attack.setPhysDmg(this.attributeStruct.getAttack());
		attack.setMagicDmg(this.attributeStruct.getMagicAttack());
		attack.determineCritical(this.attributeStruct.getCrit());
		return attack;
	}
	
	public void takeDamage(Attack attack) {
		int physDmgTaken = attack.getPhysDmg() - this.attributeStruct.getDefence();
		int magicDmgTaken = attack.getMagicDmg() - this.attributeStruct.getMagicDefence();
		if (attack.isCrit()) {
			physDmgTaken *= 2;
			magicDmgTaken *= 2;
		}
		int totalDmgTaken = physDmgTaken + magicDmgTaken;
		if (!dodgedAttack(attack.getHitChance())) {
			loseHealth(totalDmgTaken);
		}
	}
	public void takeSpecialAttack(SpecialAttack attack) {
		switch (attack.getAttackType()) {
		case STRENGTH :
			takeDamage(attack);
			break;
		case DEXTERITY :
			for (int i = 0; i < attack.getNumOfShots(); i++) {
				takeDamage(attack);
			}
			break;
		case CONSTITUTION :
			this.attributeStruct.lowerDefence(attack.getSunderAmount());
			break;
		case INTELLECT :
			takeDamage(attack);
			break;
		case WISDOM :
			this.healHealth(attack.getHealAmount());
			break;
		case CHARISMA :
			this.setTurnsCharmed(attack.getNumCharmTurns());
			break;	
		}
	}
	public boolean dodgedAttack(double hitChance) {
		double dodgeChance = this.attributeStruct.getDodge();
		dodgeChance = dodgeChance - hitChance;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 23);
		int baseDodgeChance = (rand.nextInt() % 100) + 1;
		double totalChance = baseDodgeChance + dodgeChance;
		if (Math.abs(100 - totalChance) < 0.0000001) {
			return true;
		}
		return false;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public void healHealth(int healAmount) {
		currentHealth += healAmount;
	}
	
	public void loseHealth(int damageAmount) {
		currentHealth -= damageAmount;
	}
	
	public SpecialAttack specialAttack() {
		SpecialAttack specialAttack = generateSpecialAbility();
		setUpSpecialAttack(specialAttack);
		return specialAttack;
		
	}
	private void setUpSpecialAttack(SpecialAttack specialAttack) {
		switch (specialAttack.getAttackType()) {
		case STRENGTH :
			setUpHeroicStrike(specialAttack);
			break;
		case DEXTERITY :
			setUpRapidShot(specialAttack);
			break;
		case CONSTITUTION :
			setUpSunderArmor(specialAttack);
			break;
		case INTELLECT :
			setUpArcaneBarrage(specialAttack);
			break;
		case WISDOM :
			setUpLifeBloom(specialAttack);
			break;
		case CHARISMA :
			setUpCharm(specialAttack);
			break;	
		}
	}
	
	public SpecialAttack generateSpecialAbility() {
		SpecialAttack specialAttack = new SpecialAttack();
		int[] statDispersal = disperseBonusChancePool();
		SpecialAttackPercentage[] attackChanceArray = calculateSpecialAbilityChance(statDispersal);
		Arrays.sort(attackChanceArray, new SpecAttackPercenComparator());
		for (int i = 0; i < attackChanceArray.length; i++) {
			SpecialAttackPercentage struct = attackChanceArray[i];
			specialAttack = specialAttack.launchSpecialAttack(struct);
			if (specialAttack != null) {
				return specialAttack;
			}
		}
		return null;
	}


	private SpecialAttackPercentage[] calculateSpecialAbilityChance(int[] statDispersal) {
		SpecialAttackPercentage[] specialAbilityChanceArray = new SpecialAttackPercentage[statDispersal.length];
		for (int i = 0; i < specialAbilityChanceArray.length; i++) {
			specialAbilityChanceArray[i] = new SpecialAttackPercentage(statDispersal[i], i);
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			int baseChance = (rand.nextInt() % 100) + 1;
			int totalChance = baseChance + statDispersal[i];
			specialAbilityChanceArray[i].setPercentChance(totalChance);
		}
		return specialAbilityChanceArray;
	}

	private int[] disperseBonusChancePool() {
		final int BONUS_DISPERSAL_POOL = 30;
		int statPool = this.getStatStruct().getStatPool();
		int strPercentage =  (int) ((double) statStruct.getStrength() / statPool * BONUS_DISPERSAL_POOL);
		int dexPercentage = (int) ((double) statStruct.getDexterity() / statPool * BONUS_DISPERSAL_POOL);
		int conPercentage = (int) ((double) statStruct.getConstitution() / statPool * BONUS_DISPERSAL_POOL);
		int intPercentage = (int) ((double) statStruct.getIntellect() / statPool * BONUS_DISPERSAL_POOL);
		int wisPercentage = (int) ((double) statStruct.getWisdom() / statPool * BONUS_DISPERSAL_POOL);
		int chaPercentage = (int) ((double) statStruct.getCharisma() / statPool * BONUS_DISPERSAL_POOL);
		int[] dispersalArray = {strPercentage, dexPercentage, conPercentage, intPercentage, wisPercentage, chaPercentage};
		return dispersalArray;
	}

	private void setUpHeroicStrike(SpecialAttack heroicStrike) {
		int attack = this.attributeStruct.getAttack();
		int magicAttack = this.attributeStruct.getMagicAttack();
		heroicStrike.setMagicDmg((int)(1.5 * attack + magicAttack));
		heroicStrike.setPhysDmg((int) (attack + 1.5 * magicAttack));
	}
	
	private void setUpRapidShot(SpecialAttack rapidShot) {
		rapidShot.setPhysDmg(this.attributeStruct.getAttack() / 2);
		rapidShot.generateNumOfShots();
	}
	
	private void setUpSunderArmor(SpecialAttack  sunderArmor) {
		int sunderAmount = 0;
		int defence = this.attributeStruct.getDefence();
		int magicalDefence = this.attributeStruct.getMagicDefence();
		sunderAmount = defence + magicalDefence;
		sunderArmor.setSunderAmount(sunderAmount);
	}
	
	private void setUpArcaneBarrage(SpecialAttack  arcaneBarrage) {
		int magicDmg = this.attributeStruct.getMagicAttack();
		int intellect = this.statStruct.getIntellect();
		int wisdom = this.statStruct.getWisdom();
		arcaneBarrage.setMagicDmg((int) (magicDmg * 1.5 + intellect * 1.5 + wisdom * 1.5));
	}
	
	private void setUpLifeBloom(SpecialAttack lifeBloom) {
		int magicHeal = this.attributeStruct.getMagicAttack();
		int magicDefence = this.attributeStruct.getMagicDefence();
		int intellect = this.statStruct.getIntellect();
		int wisdom = this.statStruct.getWisdom();
		magicHeal = (int) (magicHeal / 2  + magicDefence / 2 + intellect * 1.2 + wisdom * 1.2);
		lifeBloom.setHealAmount(magicHeal);
		this.setTurnsToBeHealed(SpecialAttack.NUM_OF_HEAL_TURNS);
	}
	
	private void setUpCharm(SpecialAttack charm) {
		charm.generateCharmTurns();
	}

	public int getTurnsCharmed() {
		return turnsCharmed;
	}

	public void setTurnsCharmed(int turnsCharmed) {
		this.turnsCharmed = turnsCharmed;
	}
	
	public void decrementTurnsCharmed() {
		this.turnsCharmed--;
	}

	public int getTurnsToBeHealed() {
		return turnsToBeHealed;
	}

	public void setTurnsToBeHealed(int turnsToBeHealed) {
		this.turnsToBeHealed = turnsToBeHealed;
	}
	
	public void decrementTurnsToBeHealed() {
		this.turnsToBeHealed--;
	}
	
	public void cleanUpAfterBattleTurn() {
		if (this.turnsCharmed > 0)
			this.decrementTurnsCharmed();
		if (this.turnsToBeHealed > 0)
			this.decrementTurnsToBeHealed();
	}
}
