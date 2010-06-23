package com.digichubs.dbase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.digichubs.misc.*;

public class DBAdapter {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_ISBN = "isbn";
	public static final String KEY_TITLE = "title";
	public static final String KEY_PUBLISHER = "publisher";
	private static final String TAG = "DBAdapter";

	private static final String DATABASE_NAME 		= "EntryMeDB";
	private static final String DATABASE_TABLE 		= "titles";
	
	private static final int DATABASE_VERSION = 1;
	

	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(Constants.Create_EntryList_table);
			db.execSQL(Constants.Create_Labels_table);
			db.execSQL(Constants.Create_ToDo_table);
			db.execSQL(Constants.Create_Appointment_table);
			db.execSQL(Constants.Create_Event_table);
			db.execSQL(Constants.Create_Travel_table);
			db.execSQL(Constants.Create_Task_table);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS titles");
			onCreate(db);
		}
	}

	// ---opens the database---
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		DBHelper.close();
	}
	
	// ---retrieves all items---
	public Cursor getAllItems() {	
		return db.query(Constants.ENTRYLIST_TABLE, 
			   new String[] {"entryID", 
							 "overview", 
							 "labels",
							 "category"}, 
							 null, null, null, null, null);
	}
	
	public long insertInitialItem(String overview, String labels, String notes, String category, String notify, String notifyWhen, String hide) {			
		
		ContentValues initialValues = new ContentValues();
		
		initialValues.put("overview",overview);
		initialValues.put("labels",labels);
		initialValues.put("notes",notes);
		initialValues.put("category",category);		
		initialValues.put("notify",notify);
		initialValues.put("notifyWhen",notifyWhen);
		initialValues.put("hidden",hide);
		initialValues.put("dateEntered","");
		initialValues.put("dateUpdated","");
			
		return db.insert(Constants.ENTRYLIST_TABLE, null, initialValues);
	}

	// ---deletes a particular title---
	public boolean deleteTitle(long rowId) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	public Cursor getEntry(int id) {
		Cursor cursor = null;
		cursor = db.query(true, Constants.ENTRYLIST_TABLE, 
				 new String[]{"entryID",
							  "overview",
							  "labels", 
							  "notes", 
							  "notify", 
							  "notifywhen", 
							  "category", 
							  "hidden", 
							  "dateEntered",
							  "dateUpdated"}, 
				 "entryID=" + id, null, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	// ---retrieves a particular title---
	public Cursor getTitle(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID, KEY_ISBN, KEY_TITLE, KEY_PUBLISHER }, KEY_ROWID
				+ "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// ---updates a title---
	public boolean updateTitle(long rowId, String isbn, String title,
			String publisher) {
		ContentValues args = new ContentValues();
		args.put(KEY_ISBN, isbn);
		args.put(KEY_TITLE, title);
		args.put(KEY_PUBLISHER, publisher);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}

}
