package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CompanionDataSource {
	
	private SQLiteDatabase database;
	private Event_Scheduler dbHelper;
	private String[] allColumns = CompanionTable.allColumns;
	
	public CompanionDataSource(Context context) {
		dbHelper = new Event_Scheduler(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public boolean insertCompanion(Companion companion) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(CompanionTable.COLUMN_ID, companion.getId());
		values.put(CompanionTable.COLUMN_NAME, companion.getName());
		values.put(CompanionTable.COLUMN_PRICE, companion.getPrice());
		values.put(CompanionTable.COLUMN_PURCHASED, companion.hasBeenPurchased());
		values.put(CompanionTable.COLUMN_RANK, companion.getRank());
		values.put(CompanionTable.COLUMN_FULLVIEW_IMAGE_RESOURCE, companion.getFullViewResource());
		values.put(CompanionTable.COLUMN_SELECTION_SCREEN_IMAGE, companion.getMainMenuImage());
		values.put(CompanionTable.COLUMN_THUMBNAIL_IMAGE_RESOURCE, companion.getThumbnailResource());
		values.put(CompanionTable.COLUMN_SPRITE_IMAGE_RESOURCE, companion.getSpriteReource());
		values.put(CompanionTable.COLUMN_TYPE, companion.getType());
		values.put(CompanionTable.COLUMN_CURRENT_COMPANION, companion.isActiveCompanion());
		database.insert(CompanionTable.TABLE_COMPANION, null, values);
		this.close();
		return true;
	}
	public int updateCompanion(Companion companion) {
		this.open();
		ContentValues values = new ContentValues();
		values.put(CompanionTable.COLUMN_ID, companion.getId());
		values.put(CompanionTable.COLUMN_NAME, companion.getName());
		values.put(CompanionTable.COLUMN_PRICE, companion.getPrice());
		values.put(CompanionTable.COLUMN_PURCHASED, companion.hasBeenPurchased());
		values.put(CompanionTable.COLUMN_RANK, companion.getRank());
		values.put(CompanionTable.COLUMN_FULLVIEW_IMAGE_RESOURCE, companion.getFullViewResource());
		values.put(CompanionTable.COLUMN_SELECTION_SCREEN_IMAGE, companion.getMainMenuImage());
		values.put(CompanionTable.COLUMN_THUMBNAIL_IMAGE_RESOURCE, companion.getThumbnailResource());
		values.put(CompanionTable.COLUMN_SPRITE_IMAGE_RESOURCE,  companion.getSpriteReource());
		values.put(CompanionTable.COLUMN_TYPE, companion.getType());
		values.put(CompanionTable.COLUMN_CURRENT_COMPANION, companion.isActiveCompanion());
		int rowsModified = database.update(CompanionTable.TABLE_COMPANION, values, CompanionTable.COLUMN_ID + " = " + companion.getId(), null);
		this.close();
		return rowsModified;
	}
	
	public boolean deleteCompanion(Companion companion) {
		database.delete(CompanionTable.TABLE_COMPANION, CompanionTable.COLUMN_ID + " = ?", 
				new String[] {String.valueOf(companion.getId())});
		return true;
	}
	
	public ArrayList<Companion> getAllCompanions() {
		this.open();
		ArrayList<Companion> list = new ArrayList<Companion>();
		Cursor cursor = database.query(CompanionTable.TABLE_COMPANION, allColumns, null, null, null, null, CompanionTable.COLUMN_ID + " DESC");
		Companion companion;
		if (cursor.moveToFirst()) {
			do {
				companion = new Companion();
				companion.setId(cursor.getLong(0));
				companion.setName(cursor.getString(1));
				companion.setThumbnailResource(cursor.getLong(2));
				companion.setMainMenuImage(cursor.getLong(3));
				companion.setFullViewResource(cursor.getLong(4));
				companion.setSpriteResource(cursor.getLong(5));
				companion.setPrice(cursor.getInt(6));
				companion.setRank(cursor.getInt(7));
				companion.setPurchased((byte)cursor.getShort(8) != 0);
				companion.setType(cursor.getString(9));
				companion.setActiveCompanion((byte) cursor.getShort(10) != 0);
				list.add(companion);
			} while(cursor.moveToNext());
		}
		this.close();
		return list;
	}
	
	public Companion getCompanion(int companionID) {
		this.open();
		Cursor cursor = database.query(CompanionTable.TABLE_COMPANION, allColumns, " " + CompanionTable.COLUMN_ID + " = " + companionID,
				null, null, null, null);
		Companion companion = null;
		if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			companion = new Companion();
			companion.setId(cursor.getLong(0));
			companion.setName(cursor.getString(1));
			companion.setThumbnailResource(cursor.getLong(2));
			companion.setMainMenuImage(cursor.getLong(3));
			companion.setFullViewResource(cursor.getLong(4));
			companion.setSpriteResource(cursor.getLong(5));
			companion.setPrice(cursor.getInt(6));
			companion.setRank(cursor.getInt(7));
			companion.setPurchased((byte)cursor.getShort(8) != 0);
			companion.setType(cursor.getString(9));
			companion.setActiveCompanion((byte) cursor.getShort(10) != 0);
		}
		this.close();
		return companion;
	}
	
	public void initializeTable() {
		ArrayList<Companion> list = CompanionTable.intializeCompanionTable();
		for (Companion companion : list) {
			this.insertCompanion(companion);
		}
	}
	public void dropTable() {
		this.open();
		database.execSQL(CompanionTable.COMPANION_DROP);
		this.close();
	}
}
