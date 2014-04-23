package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class AvatarTable {
	
	public static final String TABLE_AVATAR = "avatar";
	public static final String COLUMN_NAME = "avatar_name";
	public static final String COLUMN_SPRITE_IMAGE_RESOURCE = "sprite_resource";
	public static final String COLUMN_GOLD = "gold";
	public static final String COLUMN_BATTLES_WON = "battles_won";
	public static final String COLUMN_STRENGTH = "strength";
	public static final String COLUMN_DEXTERITY = "dexterity";
	public static final String COLUMN_CONSTITUTION = "constitution";
	public static final String COLUMN_WISDOM = "wisdom";
	public static final String COLUMN_CHARISMA = "charisma";
	public static final String COLUMN_INTELLECT = "intellect";
	
	public static final String AVATAR_CREATE = "create table " + TABLE_AVATAR + "(" + COLUMN_NAME + "," + COLUMN_SPRITE_IMAGE_RESOURCE + ","
			+ COLUMN_GOLD + "," + COLUMN_BATTLES_WON + "," + COLUMN_STRENGTH + "," + COLUMN_DEXTERITY + "," + COLUMN_CONSTITUTION
			 + "," + COLUMN_WISDOM + "," + COLUMN_CHARISMA + "," + COLUMN_INTELLECT + ")"; 
	
	public static final String AVATAR_DROP = "DROP TABLE IF EXISTS " + TABLE_AVATAR;
	
	public static final String[] allColumns = {COLUMN_NAME, COLUMN_NAME, COLUMN_SPRITE_IMAGE_RESOURCE, COLUMN_GOLD, COLUMN_BATTLES_WON, 
		COLUMN_STRENGTH, COLUMN_DEXTERITY, COLUMN_CONSTITUTION, COLUMN_WISDOM, COLUMN_CHARISMA, COLUMN_INTELLECT};
	
	public AvatarTable() {
		// TODO Auto-generated constructor stub
	}
	
	public static Avatar initializeTableItem() {
		Avatar avatar = new Avatar();
		Stat statStruct = new Stat();
		avatar.setName("The Lost");
		avatar.setSpriteResource(R.drawable.profile_mc);
		avatar.setBatlesWon(0);
		avatar.getWallet().setGold(0);
		statStruct.setCharisma(0);
		statStruct.setConstitution(0);
		statStruct.setDexterity(0);
		statStruct.setIntellect(0);
		statStruct.setStrength(0);
		statStruct.setWisdom(0);
		//TODO initialize Avatar column to default values on Database creation (in order of columns appearing)
		//The Lost
		//resource file name
		//0
		//0
		//1
		//1
		//1
		//1
		//1
		//1
		return null;
	}

}
