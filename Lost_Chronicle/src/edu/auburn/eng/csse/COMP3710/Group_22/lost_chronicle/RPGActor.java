package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


public class RPGActor {
	protected String mName;
	protected int mLevel;
	protected int mImageResource;
	protected Stat statStruct;
	protected Attribute attributeStruct;
	protected long id;
	
	public RPGActor() {
		// TODO Auto-generated constructor stub
	}
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmLevel() {
		return mLevel;
	}
	public void setmLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public int getmImageResource() {
		return mImageResource;
	}
	public void setmImageResource(int mImageResource) {
		this.mImageResource = mImageResource;
	}
	public Stat getStatStruct() {
		return statStruct;
	}
	public void setStatStruct(Stat statStruct) {
		this.statStruct = statStruct;
	}
	public Attribute getAttributeStruct() {
		return attributeStruct;
	}
	public void setAttributeStruct(Attribute attributeStruct) {
		this.attributeStruct = attributeStruct;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
