package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class EventScheduler {
	private SQLiteDatabase database;
	private Database_Helper dbHelper;
	private String[] allColumns = EventTable.COLUMNS;
	
	public EventScheduler(Context context) {
		dbHelper = new Database_Helper(context);
	}
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	public void addEvent(Event event) {
		Log.d("addEvent", event.toString());
		
		//get reference to writable DB
		this.open();
		
		//create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(EventTable.COLUMN_TITLE, event.getTitle());
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = formatter.format(event.getStart_time()); 
		String endTime = formatter.format(event.getEnd_time()); 
		values.put(EventTable.COLUMN_START_TIME, startTime);
		values.put(EventTable.COLUMN_END_TIME, endTime);
		values.put(EventTable.COLUMN_TYPE, event.getType());
		values.put(EventTable.COLUMN_ONGOING, event.isOnGoing());
		values.put(EventTable.COLUMN_DIFFICULTY, event.getDifficulty());
		values.put(EventTable.COLUMN_EVAL, event.getEval());
		values.put(EventTable.COLUMN_DESCRIPTION, event.getDescription());
		
		//insert into table
		database.insert(EventTable.TABLE_EVENTS, null, values);
		//eventgas = this.getEvent(0);
		//close the db
		this.close();				
	}

	
	public Event getEvent(int id) {
		//get reference to readable DB
		this.open();
		
		//build query
		Cursor cursor =
				database.query(EventTable.TABLE_EVENTS, EventTable.COLUMNS, " _id = ?", new String[] {String.valueOf(id)},
						null, null, null, null);
		
		// if we got results get the first
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		//build the event object
		Event event = new Event();
		//event.setId(Integer.parseInt(cursor.getString(0)));
		event.setTitle(cursor.getString(1));
		event.setStart_time(parseStringToDate(cursor.getString(2)));
		event.setEnd_time(parseStringToDate(cursor.getString(3)));
		event.setType(cursor.getString(4));
		event.setOnGoing(Short.parseShort(cursor.getString(5)));
		event.setDifficulty(Float.parseFloat(cursor.getString(6)));
		event.setEval(Float.parseFloat(cursor.getString(7)));
		event.setDescription(cursor.getString(8));
		
		//logcat it
		Log.d("getEvent(" + id +")", event.toString());
		this.close();
		
		return event;		
	}
	
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		
		//build query
		
		//get reference to writable db
		this.open();
		Cursor cursor = database.query(EventTable.TABLE_EVENTS, allColumns, null, null, null, null, EventTable.COLUMN_ID);
		
		//go over each row, build and add it to list
		Event event = null;
		if(cursor.moveToFirst()) {
			do {
				event = new Event();
				event.setId(Integer.parseInt(cursor.getString(0)));
				event.setTitle(cursor.getString(1));
				event.setStart_time(parseStringToDate(cursor.getString(2)));
				event.setEnd_time(parseStringToDate(cursor.getString(3)));
				event.setType(cursor.getString(4));
				event.setOnGoing(Short.parseShort(cursor.getString(5)));
				event.setDifficulty(Float.parseFloat(cursor.getString(6)));
				event.setEval(Float.parseFloat(cursor.getString(7)));
				event.setDescription(cursor.getString(8));
				
				//add event to events
				events.add(event);
			} while (cursor.moveToNext());
		}
		
		Log.d("getAllEvents()", events.toString());
		this.close();
		
		return events;
	}
	
	public int updateEvent(Event event) {
		
		//get reference to writable db
		this.open();
		
		//create ContentValues to add key "column"/value
		ContentValues  values = new ContentValues();
		values.put(EventTable.COLUMN_TITLE, event.getTitle());
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = formatter.format(event.getStart_time()); 
		String endTime = formatter.format(event.getEnd_time());
		values.put(EventTable.COLUMN_START_TIME, startTime);
		values.put(EventTable.COLUMN_END_TIME, endTime);
		values.put(EventTable.COLUMN_TYPE, event.getType());
		values.put(EventTable.COLUMN_ONGOING, event.isOnGoing());
		values.put(EventTable.COLUMN_DIFFICULTY, event.getDifficulty());
		values.put(EventTable.COLUMN_EVAL, event.getEval());
		values.put(EventTable.COLUMN_DESCRIPTION, event.getDescription());
		
		//updating row
		int i = database.update(EventTable.TABLE_EVENTS, values, EventTable.COLUMN_ID + " = ?",
				new String[] {String.valueOf(event.getId()) });
		
		//close the db
		this.close();
		
		return i;
	}
	
	//Deleting a single entry
	public void deleteEvent(Event event) {
		
		//get reference to writable db
		this.open();
		
		//delete
		database.delete(EventTable.TABLE_EVENTS, EventTable.COLUMN_ID + " = ?", 
				new String[] {String.valueOf(event.getId()) });
		
		//close db
		this.close();
		
		Log.d("deleteEvent", event.toString());
		
	}
	public static Date parseStringToDate(String dateIn) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd hh:mm", Locale.US);
		Date date = null;
		try {
			date = formatter.parse(dateIn);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
