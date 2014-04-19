package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


//Author: Sean Walker
//Build: 1.0
//Date: 4/17/2014
public class Event_Scheduler extends SQLiteOpenHelper{
	
	//Database version
	public static final int DATABASE_VERSION = 1;
	//Database name
	public static final String DATABASE_NAME = "Events.db";
	
	//static constants for table and column names
	private static final String DATABASE_EVENTS = "events";
	private static final String COLUMN_START_TIME ="start time";
	private static final String COLUMN_END_TIME ="end time";
	private static final String COLUMN_TITLE = "title";
	private static final String COLUMN_ONGOING = "ongoing";
	private static final String COLUMN_TYPE = "type";
	private static final String COLUMN_DIFFICULTY = "difficulty";
	private static final String COLUMN_EVAL = "eval";
	private static final String COLUMN_DESCRIPTION = "description";
	private static final String COLUMN_ID = "_id";
	
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_START_TIME,
		COLUMN_END_TIME, COLUMN_TYPE, COLUMN_ONGOING, COLUMN_DIFFICULTY, COLUMN_EVAL,
		COLUMN_DESCRIPTION};
	
	
	//static constant to create the db
	private static final String DATABASE_CREATE = "CREATE TABLE " 
			+ DATABASE_EVENTS + "(" + COLUMN_ID + "," + COLUMN_TITLE 
			+ "," + COLUMN_START_TIME + "," + COLUMN_END_TIME + "," 
			+ COLUMN_TYPE + "," + COLUMN_ONGOING + "," + COLUMN_DIFFICULTY 
			+ "," + COLUMN_EVAL + "," + COLUMN_DESCRIPTION + ")";
	
	
	public Event_Scheduler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//create fresh event table
		db.execSQL(DATABASE_CREATE);
		db.execSQL(CompanionTable.COMPANION_CREATE);
		
		//populate companion table with all the companions
		//populate hair table with all the hairs
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop older event table if existed
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_EVENTS);
		db.execSQL(CompanionTable.COMPANION_DROP);
		//create fresh event table
		this.onCreate(db);
		
	}
	
	public void addEvent(Event event) {
		Log.d("addEvent", event.toString());
		
		//get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		
		//create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(COLUMN_TITLE, event.getTitle());
		values.put(COLUMN_START_TIME, event.getStart_time());
		values.put(COLUMN_END_TIME, event.getEnd_time());
		values.put(COLUMN_TYPE, event.getType());
		values.put(COLUMN_ONGOING, event.isOnGoing());
		values.put(COLUMN_DIFFICULTY, event.getDifficulty());
		values.put(COLUMN_EVAL, event.getEval());
		values.put(COLUMN_DESCRIPTION, event.getDescription());
		
		//insert into table
		db.insert(DATABASE_EVENTS, null, values);
		
		//close the db
		db.close();				
	}

	
	public Event getEvent(int id) {
		//get reference to readable DB
		SQLiteDatabase db = this.getReadableDatabase();
		
		//build query
		Cursor cursor =
				db.query(DATABASE_EVENTS, COLUMNS, " id = ?", new String[] {String.valueOf(id)},
						null, null, null, null);
		
		// if we got results get the first
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		//build the event object
		Event event = new Event();
		event.setId(Integer.parseInt(cursor.getString(0)));
		event.setTitle(cursor.getString(1));
		event.setStart_time(cursor.getString(2));
		event.setEnd_time(cursor.getString(3));
		event.setType(cursor.getString(4));
		event.setOnGoing((short) Integer.parseInt(cursor.getString(5)));
		event.setDifficulty((short) Integer.parseInt(cursor.getString(6)));
		event.setEval((short) Integer.parseInt(cursor.getString(7)));
		event.setDescription(cursor.getString(8));
		
		//logcat it
		Log.d("getEvent(" + id +")", event.toString());
		
		return event;		
	}
	
	public List<Event> getAllEvents() {
		List<Event> events = new LinkedList<Event>();
		
		//build query
		String query = "SELECT  * FROM " + DATABASE_EVENTS;
		
		//get reference to writable db
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		
		//go over each row, build and add it to list
		Event event = null;
		if(cursor.moveToFirst()) {
			do {
				event = new Event();
				event.setId(Integer.parseInt(cursor.getString(0)));
				event.setTitle(cursor.getString(1));
				event.setStart_time(cursor.getString(2));
				event.setEnd_time(cursor.getString(3));
				event.setType(cursor.getString(4));
				event.setOnGoing((short) Integer.parseInt(cursor.getString(5)));
				event.setDifficulty((short) Integer.parseInt(cursor.getString(6)));
				event.setEval((short) Integer.parseInt(cursor.getString(7)));
				event.setDescription(cursor.getString(8));
				
				//add event to events
				events.add(event);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllEvents()", events.toString());
		
		return events;
	}
	
	public int updateEvent(Event event) {
		
		//get reference to writable db
		SQLiteDatabase db = this.getWritableDatabase();
		
		//create ContentValues to add key "column"/value
		ContentValues  values = new ContentValues();
		values.put(COLUMN_TITLE, event.getTitle());
		values.put(COLUMN_START_TIME, event.getStart_time());
		values.put(COLUMN_END_TIME, event.getEnd_time());
		values.put(COLUMN_TYPE, event.getType());
		values.put(COLUMN_ONGOING, event.isOnGoing());
		values.put(COLUMN_DIFFICULTY, event.getDifficulty());
		values.put(COLUMN_EVAL, event.getEval());
		values.put(COLUMN_DESCRIPTION, event.getDescription());
		
		//updating row
		int i = db.update(DATABASE_EVENTS, values, COLUMN_ID + " = ?",
				new String[] {String.valueOf(event.getId()) });
		
		//close the db
		db.close();
		
		return i;
	}
	
	//Deleting a single entry
	public void deleteEvent(Event event) {
		
		//get reference to writable db
		SQLiteDatabase db = this.getWritableDatabase();
		
		//delete
		db.delete(DATABASE_EVENTS, COLUMN_ID + " = ?", 
				new String[] {String.valueOf(event.getId()) });
		
		//close db
		db.close();
		
		Log.d("deleteEvent", event.toString());
		
	}
}

