package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.os.Parcel;
import android.os.Parcelable;

public class Companion extends Avatar implements Purchasable {
	//Used to determine statPool multiplier. mRank goes up to 4. Rank 1 gives .85*avatar.statPool, Rank 2 gives .90 * statPool and so on.
	private int mRank;
	private int mPurchasableResource, mFullViewResource;
	private int mPrice;
	private CompanionCreator creator;
	private boolean mPurchased;
	public Companion() {
		
	}
	public Companion(Parcel source) {
		creator = new CompanionCreator();
		mRank = source.readInt();
		mPurchasableResource = source.readInt();
		mFullViewResource = source.readInt();
		mPrice = source.readInt();
	}

	@Override
	public int getPrice() {
		return mPrice;
	}
	
	public void setPrice(int priceIn) {
		mPrice = priceIn;
	}
	/**
	 *Get the Avatar's statPool and create Companion statStruct based on rank, type, and the statPool.
	 *ex. if mRank = 1 and type = "CHARISMA" then you could set Companion statPool to avatar.statPool * .85. Then allocate 
	 *35% of the stats to Charisma and the rest to other stats.  
	 */
	public void makeStatFromAvatar() {
		
	}
	@Override
	public int getThumbnailResource() {
		return mPurchasableResource;
	}



	public int getRank() {
		return mRank;
	}



	public void setRank(int mRank) {
		this.mRank = mRank;
	}



	public int getFullViewResource() {
		return mFullViewResource;
	}



	public void setFullViewResource(int mFullViewResource) {
		this.mFullViewResource = mFullViewResource;
	}


	@Override
	public void setThumbnailResource(int thumbResourceIn) {
		mPurchasableResource = thumbResourceIn;
		
	}


	@Override
	public String getPurchaseName() {
		return super.getName();
	}


	@Override
	public String getTableName() {
		return "COMPANION";
	}


	@Override
	public int describeContents() {
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(mRank);
		dest.writeInt(mPurchasableResource);
		dest.writeInt(mFullViewResource);
		dest.writeInt(mPrice);
	}
	
	public class CompanionCreator implements Parcelable.Creator<Companion> {

		@Override
		public Companion createFromParcel(Parcel source) {
			return new Companion(source);
		}

		@Override
		public Companion[] newArray(int size) {
			return new Companion[size];
		}
		
	}

	@Override
	public void setAsPurchased() {
		mPurchased = true;
		
	}
	@Override
	public void setAsNotPurchased() {
		mPurchased = false;
		
	}
	@Override
	public boolean hasBeenPurchased() {
		return mPurchased;
	}
}
