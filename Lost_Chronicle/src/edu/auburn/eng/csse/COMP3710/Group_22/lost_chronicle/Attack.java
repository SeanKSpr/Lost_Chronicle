package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.Random;

public class Attack {
	
	private int physDmg;
	private int magicDmg;
	private double hitChance;
	private boolean isCrit;
	
	public Attack() {
		physDmg = 0;
		magicDmg = 0;
		isCrit = false;
	}

	public int getPhysDmg() {
		return physDmg;
	}

	public void setPhysDmg(int physDmg) {
		this.physDmg = physDmg;
	}

	public int getMagicDmg() {
		return magicDmg;
	}

	public void setMagicDmg(int magicDmg) {
		this.magicDmg = magicDmg;
	}

	public boolean isCrit() {
		return isCrit;
	}

	public void setCrit(boolean isCrit) {
		this.isCrit = isCrit;
	}
	
	public void determineCritical(double critChance) {
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis() * 23);
		int baseChance = Math.abs((rand.nextInt() % 100)) + 1;
		double totalChance = critChance + baseChance;
		if (100 - totalChance <= 0) {
			isCrit = true;
		}
	}

	public double getHitChance() {
		return hitChance;
	}

	public void setHitChance(double hitChance) {
		this.hitChance = hitChance;
	}
}
