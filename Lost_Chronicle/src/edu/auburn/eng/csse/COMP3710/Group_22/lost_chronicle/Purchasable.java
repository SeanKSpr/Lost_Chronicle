package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public interface Purchasable {
	/**
	 * Returns the id of the icon image resource associated with the purchasable object
	 * @return int : id of image resource
	 */
	int getImageResource();
	/**
	 * Returns the name associated with the purchasable object
	 * @return String : name of purchasable object
	 */
	String getName();
	/**
	 * Returns the price (in gold) associated with the purchasable object
	 * @return int : price of purchasable object
	 */
	int getPrice();
}
