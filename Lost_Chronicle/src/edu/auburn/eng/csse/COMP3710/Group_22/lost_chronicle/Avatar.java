package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class Avatar extends RPGActor {
	private Wallet mWallet;
	private int mBattlesWon;
	public Avatar() {
		// TODO Auto-generated constructor stub
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
	
}
