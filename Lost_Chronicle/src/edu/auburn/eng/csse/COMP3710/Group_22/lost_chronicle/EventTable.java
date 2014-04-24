package edu.auburn.eng.csse.COMP3710.Group_22.lost_chronicle;

public class EventTable {
	//static constants for table and column names
		public static final String TABLE_EVENTS = "events";
		public static final String COLUMN_START_TIME ="start_time";
		public static final String COLUMN_END_TIME ="end_time";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_ONGOING = "ongoing";
		public static final String COLUMN_TYPE = "type";
		public static final String COLUMN_DIFFICULTY = "difficulty";
		public static final String COLUMN_EVAL = "eval";
		public static final String COLUMN_DESCRIPTION = "description";
		public static final String COLUMN_ID = "_id";
		
		public static final String[] COLUMNS = {COLUMN_ID, COLUMN_TITLE, COLUMN_START_TIME,
			COLUMN_END_TIME, COLUMN_TYPE, COLUMN_ONGOING, COLUMN_DIFFICULTY, COLUMN_EVAL,
			COLUMN_DESCRIPTION};
		
		
		//static constant to create the db
		public static final String DATABASE_CREATE = "CREATE TABLE " 
				+ TABLE_EVENTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE 
				+ "," + COLUMN_START_TIME + "," + COLUMN_END_TIME + "," 
				+ COLUMN_TYPE + "," + COLUMN_ONGOING + "," + COLUMN_DIFFICULTY 
				+ ", " + COLUMN_EVAL + "," + COLUMN_DESCRIPTION + ")";
		
		public static final String EVENT_DROP = "DROP TABLE IF EXISTS " + TABLE_EVENTS;

}
