package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;


public class RPGActor {
	protected String mName;
	protected int mLevel;
	protected long mSpriteResource;
	protected Stat Stats;
	protected Attribute Attributes;
	protected long id;
	protected Attack mAttack;
	protected int currentHealth;
	protected int turnsCharmed;
	protected int turnsToBeHealed;
	protected int amountToBeHealed;
	public RPGActor() {
		Stats = new Stat();
		Attributes = new Attribute();
	}
	
	public int getAmountToBeHealed() {
		return amountToBeHealed;
	}

	public void setAmountToBeHealed(int amountToBeHealed) {
		this.amountToBeHealed = amountToBeHealed;
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
		return Stats;
	}
	public void setStatStruct(Stat statStruct) {
		this.Stats = statStruct;
	}
	public Attribute getAttributeStruct() {
		return Attributes;
	}
	public void setAttributeStruct(Attribute attributeStruct) {
		this.Attributes = attributeStruct;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void calculateLevel() {
		mLevel = (int) Math.ceil(Stats.getStatPool() / 10);
	}
	public void calculateHealth() {
		Attributes.setHealth(mLevel * 100 + Stats.getConstitution() * 17);
		this.setCurrentHealth(Attributes.getHealth());
	}

	public Attack getAttack() {
		return mAttack;
	}

	public void setAttack(Attack mAttack) {
		this.mAttack = mAttack;
	}
	
	public Attack attack() {
		Random rand = new Random();
		int physDmg = Attributes.getAttack();
		int magicDmg = Attributes.getMagicAttack();
		double physDmgModifier = ((rand.nextInt() % (physDmg + 1)) + 1) / 3;
		double magicDmgModifier = ((rand.nextInt() % (magicDmg + 1)) + 1) / 3;
		Attack attack = new Attack();
		attack.setPhysDmg((int)(physDmg + physDmgModifier));
		attack.setMagicDmg((int)(magicDmg + magicDmgModifier));
		attack.determineCritical(this.Attributes.getCrit());
		return attack;
	}
	
	public int takeDamage(Attack attack) {
		int physDmgTaken = Math.abs(attack.getPhysDmg() - this.Attributes.getDefence());
		int magicDmgTaken = Math.abs(attack.getMagicDmg() - this.Attributes.getMagicDefence());
		if (attack.isCrit()) {
			physDmgTaken *= 2;
			magicDmgTaken *= 2;
		}
		int totalDmgTaken = physDmgTaken + magicDmgTaken;
		if (!dodgedAttack(attack.getHitChance())) {
			loseHealth(totalDmgTaken);
			return totalDmgTaken;
		}
		return 0;
	}
	public int takeSpecialAttack(SpecialAttack attack) {
		if (attack != null) {
			switch (attack.getAttackType()) {
			case STRENGTH :
				return takeDamage(attack);
			case DEXTERITY :
				int totalDamageDone = 0;
				for (int i = 0; i < attack.getNumOfShots(); i++) {
					 totalDamageDone += takeDamage(attack);
				}
				return totalDamageDone;
			case CONSTITUTION :
				this.Attributes.lowerDefence(attack.getSunderAmount());
				return 0;
			case INTELLECT :
				return takeDamage(attack);
			case WISDOM :
				this.setTurnsToBeHealed(SpecialAttack.NUM_OF_HEAL_TURNS);
				this.setAmountToBeHealed(attack.getHealAmount());
				return attack.getHealAmount();
			case CHARISMA :
				this.setTurnsCharmed(attack.getNumCharmTurns());
				return 0;	
			}
		}
		return 0;
	}
	public boolean dodgedAttack(double hitChance) {
		double dodgeChance = this.Attributes.getDodge();
		dodgeChance = dodgeChance - hitChance;
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 23);
		int baseDodgeChance = Math.abs((rand.nextInt() % 100)) + 1;
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
		if (currentHealth > Attributes.getHealth()) {
			currentHealth = Attributes.getHealth();
		}
	}
	
	public void loseHealth(int damageAmount) {
		currentHealth -= damageAmount;
		if (currentHealth < 0) {
			currentHealth = 0;
		}
	}
	
	/**
	 * Attempts to generate a SpecialAttack based on the RPGActor's stats. 
	 * @return a new SpecialAttack or null if a SpecialAttack fails to be created.
	 */
	public SpecialAttack specialAttack() {
		SpecialAttack specialAttack = new SpecialAttack();
		specialAttack = specialAttack.generateSpecialAbility(Stats, Attributes);
		return specialAttack;
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
	public boolean isCharmed() {
		return turnsCharmed > 0;
	}
	public void cleanUpAfterBattleTurn() {
		if (this.turnsCharmed > 0)
			this.decrementTurnsCharmed();
		if (this.turnsToBeHealed > 0) {
			this.healHealth(amountToBeHealed);
			this.decrementTurnsToBeHealed(); 
		}
	}
}
