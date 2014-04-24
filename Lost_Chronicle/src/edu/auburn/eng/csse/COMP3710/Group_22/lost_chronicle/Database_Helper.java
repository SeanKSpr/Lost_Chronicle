package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//Author: Sean Walker
//Build: 1.0
//Date: 4/17/2014
public class Database_Helper extends SQLiteOpenHelper{
	
	//Database version
	public static final int DATABASE_VERSION = 1;
	//Database name
	public static final String DATABASE_NAME = "lost_chronicle_db.db";
	
	
	
	
	public Database_Helper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//create fresh event table
		db.execSQL(EventTable.DATABASE_CREATE);
		db.execSQL(CompanionTable.COMPANION_CREATE);
		db.execSQL(AvatarTable.AVATAR_CREATE);
		//populate companion table with all the companions
		//populate hair table with all the hairs
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop older event table if existed
		db.execSQL(EventTable.EVENT_DROP);
		db.execSQL(CompanionTable.COMPANION_DROP);
		db.execSQL(AvatarTable.AVATAR_DROP);
		//create fresh event table
		this.onCreate(db);
		
	}
	
	
	
}