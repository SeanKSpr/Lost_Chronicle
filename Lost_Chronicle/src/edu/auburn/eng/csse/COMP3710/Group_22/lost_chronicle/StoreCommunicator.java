package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

public interface StoreCommunicator {
	public ArrayList<Purchasable> getPurchasables();
	public Purchasable getPurchasable(int Id);
	public void updatePurchasable(Purchasable purchasable);
	public void performTransaction(int cost);
	public int getWallet();
}
