package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.os.Parcelable;

public interface Purchasable extends Parcelable{
	final int UNKNOWN_KANOJO = R.drawable.hidden_kanojo;
	/**
	 * Returns the id of the icon image resource associated with the purchasable object
	 * @return int : id of image resource
	 */
	long getThumbnailResource();
	/**
	 * Assigns thumbResourceIn to an integer class variable in implementing classes.
	 */
	void setThumbnailResource(long thumbResourceIn);
	/**
	 * Returns the name associated with the purchasable object
	 * @return String : name of purchasable object
	 */
	String getPurchaseName();
	/**
	 * Returns the unique identifier of the purchase for database matching
	 * 
	 * @return long : id of the purchasable stored in the database
	 */
	long getId();
	/**
	 * Returns the name of the table the purchase is stored in.
	 * @return String : Name of the table the purchasable is stored in within the database
	 */
	String getTableName();
	/**
	 * Returns the price (in gold) associated with the purchasable object
	 * @return int : price of purchasable object
	 */
	int getPrice();
	
	void setPurchased(boolean beenPurchased);
	/**
	 * Returns the purchase status of the Purchasable: true if purchased; false otherwise
	 * @return boolean - Purchase status of the Purchasable.
	 */
	boolean hasBeenPurchased();
	
}
