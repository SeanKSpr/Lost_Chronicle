package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.os.Parcel;
import android.os.Parcelable;

public class Companion extends Avatar implements Purchasable {
	//Used to determine statPool multiplier. mRank goes up to 4. Rank 1 gives .85*avatar.statPool, Rank 2 gives .90 * statPool and so on.
	private int mRank;
	private long mPurchasableResource, mMainMenuImage, mFullViewResource;
	private int mPrice;
	private String mType;
	private boolean mPurchased, mActiveCompanion;
	private CompanionCreator creator;
	public Companion() {
		
	}
	public Companion(Parcel source) {
		creator = new CompanionCreator();
		mRank = source.readInt();
		mPurchasableResource = source.readLong();
		mMainMenuImage = source.readLong();
		mFullViewResource = source.readLong();
		mSpriteResource = source.readLong();
		mPrice = source.readInt();
		mType = source.readString();
		mPurchased = source.readByte() != 0;
		mActiveCompanion = source.readByte() != 0;
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
	public long getThumbnailResource() {
		return mPurchasableResource;
	}



	public int getRank() {
		return mRank;
	}



	public void setRank(int mRank) {
		this.mRank = mRank;
	}



	public long getFullViewResource() {
		return mFullViewResource;
	}



	public void setFullViewResource(long fullViewResourceIn) {
		this.mFullViewResource = fullViewResourceIn;
	}


	@Override
	public void setThumbnailResource(long thumbnailResourceIn) {
		mPurchasableResource = thumbnailResourceIn;
		
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
		dest.writeLong(mPurchasableResource);
		dest.writeLong(mMainMenuImage);
		dest.writeLong(mFullViewResource);
		dest.writeLong(mSpriteResource);
		dest.writeInt(mPrice);
		dest.writeString(mType);
		dest.writeByte( (byte) (mPurchased ? 1 : 0));
		dest.writeByte((byte) (mActiveCompanion ? 1 : 0));
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
	public void setPurchased(boolean beenPurchased) {
		mPurchased = beenPurchased;
	}
	@Override
	public boolean hasBeenPurchased() {
		return mPurchased;
	}
	public long getMainMenuImage() {
		return mMainMenuImage;
	}
	public void setMainMenuImage(long mainMenuImageIn) {
		this.mMainMenuImage = mainMenuImageIn;
	}
	public String getType() {
		return mType;
	}
	public void setType(String mType) {
		this.mType = mType;
	}
	
	public boolean isActiveCompanion() {
		return mActiveCompanion;
	}
	
	public void setActiveCompanion(boolean mActiveCompanion) {
		this.mActiveCompanion = mActiveCompanion;
	}
}
