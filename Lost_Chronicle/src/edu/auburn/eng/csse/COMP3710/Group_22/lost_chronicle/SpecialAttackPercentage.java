package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class SpecialAttackPercentage {
	private int abilityType;
	private int percentChance;
	public SpecialAttackPercentage(int percentage, int type) {
		abilityType = type;
		percentChance = percentage;
	}
	public int getAbilityType() {
		return abilityType;
	}
	public void setAbilityType(int abilityType) {
		this.abilityType = abilityType;
	}
	public int getPercentChance() {
		return percentChance;
	}
	public void setPercentChance(int percentChance) {
		this.percentChance = percentChance;
	}

}
