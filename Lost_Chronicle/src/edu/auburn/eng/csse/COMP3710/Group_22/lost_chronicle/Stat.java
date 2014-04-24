package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class Stat {
	private int Strength;
	private int Intellect;
	private int Dexterity;
	private int Constitution;
	private int Wisdom;
	private int Charisma;
	public Stat() {
		Strength = 0;
		Intellect = 0;
		Dexterity = 0;
		Constitution = 0;
		Wisdom = 0;
		Charisma = 0;
	}
	public int getStrength() {
		return Strength;
	}
	public void setStrength(int strength) {
		Strength = strength;
	}
	public int getIntellect() {
		return Intellect;
	}
	public void setIntellect(int intellect) {
		Intellect = intellect;
	}
	public int getDexterity() {
		return Dexterity;
	}
	public void setDexterity(int dexterity) {
		Dexterity = dexterity;
	}
	public int getConstitution() {
		return Constitution;
	}
	public void setConstitution(int constitution) {
		Constitution = constitution;
	}
	public int getWisdom() {
		return Wisdom;
	}
	public void setWisdom(int wisdom) {
		Wisdom = wisdom;
	}
	public int getCharisma() {
		return Charisma;
	}
	public void setCharisma(int charisma) {
		Charisma = charisma;
	}
	public int getStatPool() {
		return Strength + Dexterity + Constitution + Wisdom + Intellect + Charisma;
	}
	public void addStrength(int amount) {
		Strength += amount;
	}
	public void addDexterity(int amount) {
		Dexterity += amount;
	}
	public void addConstitution(int amount) {
		Constitution += amount;
	}
	public void addWisdom(int amount) {
		Wisdom += amount;
	}
	public void addIntellect(int amount) {
		Intellect += amount;
	}
	public void addCharisma(int amount) {
		Charisma += amount;
	}
}
