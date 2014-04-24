package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;


public class RPGActor {
	protected String mName;
	protected int mLevel;
	protected long mSpriteResource;
	protected Stat statStruct;
	protected Attribute attributeStruct;
	protected long id;
	
	public RPGActor() {
		statStruct = new Stat();
		attributeStruct = new Attribute();
	}
	
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public int getLevel() {
		return mLevel;
	}
	public void setLevel(int mLevel) {
		this.mLevel = mLevel;
	}
	public long getSpriteReource() {
		return mSpriteResource;
	}
	public void setSpriteResource(long mSpriteResource) {
		this.mSpriteResource = mSpriteResource;
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
	public void calculateLevel() {
		mLevel = (int) Math.ceil(statStruct.getStatPool() / 10);
	}
}
