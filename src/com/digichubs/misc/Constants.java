package com.digichubs.misc;

public class Constants {
	
	public static final int ADD_ENTRY = 1;
	public static final int SEARCH_LIST = 6;
	
	
	/* Database */
	public static final String DATABASE_NAME 		= "EntryMeDB";
	public static final String ENTRYLIST_TABLE 		= "EntryList";
	public static final String LABEL_TABLE 			= "Labels";
	public static final String APPOINTMENT_TABLE 	= "Appointment";
	public static final String ToDo_TABLE 			= "ToDo";
	public static final String EVENT_TABLE 			= "Event";
	public static final String TRAVEL_TABLE 		= "Travel";
	public static final String TASK_TABLE 			= "Task";
	public static final String DB_SQL 	    		=  "";
	public static final int DATABASE_VERSION 		= 1;
	
	public static final String Create_EntryList_table 		= "CREATE TABLE EntryList (" +
																"entryID INTEGER PRIMARY KEY AUTOINCREMENT," +
																"overview TEXT NOT NULL, " +
																"labels TEXT NULL, " +
																"notes TEXT NULL, " +
																"notify TEXT NULL, " +
																"notifywhen TEXT NULL, " +
																"category TEXT NOT NULL," +
																"hidden TEXT NOT NULL, " +
																"dateEntered TEXT NOT NULL, " +
																"dateUpdated TEXT NOT NULL);";
	
	public static final String Create_Labels_table 	 		=  "CREATE TABLE Labels (" +
															 	"labelID INTEGER PRIMARY KEY AUTOINCREMENT," +
															 	"entryID TEXT NULL, " +
															 	"labels TEXT NULL);";

	public static final String Create_ToDo_table 	 		=  "CREATE TABLE ToDo (" +
														   		"toDoID INTEGER PRIMARY KEY AUTOINCREMENT," +
														   		"entryID TEXT NULL, " +
														   		"todo_date TEXT NULL, " +
														   		"todo_time TEXT NULL);";
	
	public static final String Create_Appointment_table 	=  "CREATE TABLE Appointment (" +
																"appointmentID INTEGER PRIMARY KEY AUTOINCREMENT," +
																"entryID TEXT NULL, " +
																"appoinment_date TEXT NULL, " +
																"appointment_time TEXT NULL, " +
																"appointment_with TEXT NULL," +
																"location TEXT NULL);";
	
	public static final String Create_Event_table 			=  "CREATE TABLE Event (" +
																"eventID INTEGER PRIMARY KEY AUTOINCREMENT," +
																"entryID TEXT NULL, " +
																"event_date TEXT NULL, " +
																"event_time TEXT NULL, " +
																"location TEXT NULL);";
	
	public static final String Create_Travel_table 			=  "CREATE TABLE Travel (" +
																"travelID INTEGER PRIMARY KEY AUTOINCREMENT," +
																"entryID TEXT NULL, " +
																"travel_date TEXT NULL, " +
																"travel_time TEXT NULL, " +
																"location TEXT NULL);";
	
	public static final String Create_Task_table 			=  "CREATE TABLE Task (" +
																"taskID INTEGER PRIMARY KEY AUTOINCREMENT," +
																"entryID TEXT NULL, " +
																"startDate TEXT NULL, " +
																"endDate TEXT NULL, " +
																"completedDate TEXT NULL" +
																"progress TEXT NULL, " +
																"priority TEXT NULL);";
	
	public static String dbSQL(String query) {return query;}
	
	
	/* ADD ENTRY */
	public static final int Appointment = 101;
	public static final int Events = 102;
	public static final int Notes = 103;
	public static final int Task = 104;
	public static final int ToDo = 105;
	public static final int Travel = 106;
	
}
