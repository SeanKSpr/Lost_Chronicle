package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Companion extends Avatar implements Purchasable {
	private int mRank;
	private long mPurchasableResource, mMainMenuImage, mFullViewResource;
	private int mPrice;
	private String mType;
	private boolean mPurchased, mActiveCompanion;

	@SuppressWarnings("unused")
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
	public void makeStatFromAvatar(Avatar avatar) {
		Stat avatarStats = avatar.getStatStruct();
		double statPoolPercentage = (100 - (4 - mRank) * 5.0) / 100;
		int companionStatPool = (int) (statPoolPercentage * avatarStats.getStatPool());
		int[] statDistribution = getStatDistributionByClass();
		this.distributeStats(statDistribution, companionStatPool);
	}
	private void distributeStats(int[] statDistribution, int companionStatPool) {
		Stat companionStats = this.getStatStruct();
		companionStats.setStrength((int) (statDistribution[0] / 100.0 * companionStatPool));
		companionStats.setDexterity((int) (statDistribution[1] / 100.0 * companionStatPool));
		companionStats.setConstitution((int) (statDistribution[2] / 100.0 * companionStatPool));
		companionStats.setWisdom((int) (statDistribution[3] / 100.0 * companionStatPool));
		companionStats.setIntellect((int) (statDistribution[4] / 100.0 * companionStatPool));
		companionStats.setCharisma((int) (statDistribution[5] / 100.0 * companionStatPool));
	}
	private int[] getStatDistributionByClass() {
		if (mType.equals("Strength")) {
			int[] statDistribution = {35, 17, 23, 10, 5, 10};
			return statDistribution;
		}
		else if (mType.equals("Dexterity")) {
			int[] statDistribution = {20, 35, 15, 7, 7, 16};
			return statDistribution;
		}
		else if (mType.equals("Constitution")) {
			int[] statDistribution = {15, 15, 40, 20, 5, 5};
			return statDistribution;
		}
		else if (mType.equals("Wisdom")) {
			int[] statDistribution = {5, 12, 12, 37, 25, 10};
			return statDistribution;
		}
		else if (mType.equals("Intelligence")) {
			int[] statDistribution = {5, 20, 15, 15, 40, 5};
			return statDistribution;
		}
		else if (mType.equals("Charisma")) {
			int[] statDistribution = {32, 16, 15, 2, 5, 30};
			return statDistribution;
		}
		else {
			Log.e("UNKNOWN_CLASS", "Encountered an unknown class. Are you sure you spelled the class correctly?");
		}
		return null;
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
	
	public ClassAttack convertTypeToClassType() {
		if (this.mType.equals("Strength")) {
			return ClassAttack.STRENGTH;
		}
		else if (this.mType.equals("Dexterity")) {
			return ClassAttack.DEXTERITY;
		}
		else if (this.mType.equals("Constitution")) {
			return ClassAttack.CONSTITUTION;
		}
		else if (this.mType.equals("Intelligence")) {
			return ClassAttack.INTELLECT;
		}
		else if (this.mType.equals("Wisdom")) {
			return ClassAttack.WISDOM;
		}
		else if (this.mType.equals("Charisma")) {
			return ClassAttack.CHARISMA;
		}
		else {
			Log.e("convertTypeToClassAttack", "mType doesn't match any of the pre-defined values. Check the booleans.");
		}
		return null;
	}
}
