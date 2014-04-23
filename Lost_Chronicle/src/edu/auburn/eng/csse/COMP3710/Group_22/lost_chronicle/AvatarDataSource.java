package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;


public class AvatarDataSource {
	private SQLiteDatabase database;
	private Database_Helper dbHelper;
	private String[] allColumns = AvatarTable.allColumns;
	
	public AvatarDataSource(Context context) {
		dbHelper = new Database_Helper(context);
	}
	
	public void open() throws SQLiteException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public long insertAvatar(Avatar avatar) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(AvatarTable.COLUMN_NAME, avatar.getName());
		values.put(AvatarTable.COLUMN_SPRITE_IMAGE_RESOURCE, avatar.getSpriteReource());
		values.put(AvatarTable.COLUMN_GOLD, avatar.getWallet().getGold());
		values.put(AvatarTable.COLUMN_BATTLES_WON, avatar.getBattlesWon());
		values.put(AvatarTable.COLUMN_STRENGTH, avatar.getStatStruct().getStrength());
		values.put(AvatarTable.COLUMN_DEXTERITY, avatar.getStatStruct().getDexterity());
		values.put(AvatarTable.COLUMN_CONSTITUTION, avatar.getStatStruct().getConstitution());
		values.put(AvatarTable.COLUMN_WISDOM, avatar.getStatStruct().getWisdom());
		values.put(AvatarTable.COLUMN_CHARISMA, avatar.getStatStruct().getCharisma());
		values.put(AvatarTable.COLUMN_INTELLECT, avatar.getStatStruct().getIntellect());
		long rowsModified = database.insert(AvatarTable.TABLE_AVATAR, null, values);
		this.close();
		return rowsModified;
	}
	
	public int updateAvatar(Avatar avatar) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(AvatarTable.COLUMN_NAME, avatar.getName());
		values.put(AvatarTable.COLUMN_SPRITE_IMAGE_RESOURCE, avatar.getSpriteReource());
		values.put(AvatarTable.COLUMN_GOLD, avatar.getWallet().getGold());
		values.put(AvatarTable.COLUMN_BATTLES_WON, avatar.getBattlesWon());
		values.put(AvatarTable.COLUMN_STRENGTH, avatar.getStatStruct().getStrength());
		values.put(AvatarTable.COLUMN_DEXTERITY, avatar.getStatStruct().getDexterity());
		values.put(AvatarTable.COLUMN_CONSTITUTION, avatar.getStatStruct().getConstitution());
		values.put(AvatarTable.COLUMN_WISDOM, avatar.getStatStruct().getWisdom());
		values.put(AvatarTable.COLUMN_CHARISMA, avatar.getStatStruct().getCharisma());
		values.put(AvatarTable.COLUMN_INTELLECT, avatar.getStatStruct().getIntellect());
		int rowsModified = database.update(AvatarTable.TABLE_AVATAR, values, null, null);
		this.close();
		return rowsModified;
	}
	
	public int deleteAvatar(Avatar avatar) {
		return database.delete(AvatarTable.TABLE_AVATAR, null, null);
	}
	
	public Avatar getAvatar() {
		this.open();
		Cursor cursor = database.query(AvatarTable.TABLE_AVATAR, allColumns, null, null, null, null, null);
		Avatar avatar = null;
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			avatar = new Avatar();
			Stat statStruct = new Stat();
			avatar.setName(cursor.getString(0));
			avatar.setSpriteResource(cursor.getInt(1));
			avatar.getWallet().setGold(cursor.getInt(2));
			avatar.setBatlesWon(cursor.getInt(3));
			statStruct.setStrength(cursor.getInt(4));
			statStruct.setDexterity(cursor.getInt(5));
			statStruct.setConstitution(cursor.getInt(6));
			statStruct.setWisdom(cursor.getInt(7));
			statStruct.setCharisma(cursor.getInt(8));
			statStruct.setIntellect(cursor.getInt(9));
			avatar.setStatStruct(statStruct);
		}
		this.close();
		return avatar;
	}
	
	public void initializeTable() {
		Avatar avatar = AvatarTable.initializeTableItem();
	}
}
