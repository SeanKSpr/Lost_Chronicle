package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class Wallet {
	private int gold;

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}
	
	public void subtractGold(int gold) {
		this.gold -= gold;
	}
}
