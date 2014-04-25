package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public enum ClassAttack {
	STRENGTH, DEXTERITY, CONSTITUTION, INTELLECT, WISDOM, CHARISMA;
	
	private int ID;
	static {
		STRENGTH.ID = 0;
		DEXTERITY.ID = 1;
		CONSTITUTION.ID = 2;
		INTELLECT.ID = 3;
		WISDOM.ID = 4;
		CHARISMA.ID = 5;
	}
	
	public int getClassAttack() {
		return ID;
	}
}
