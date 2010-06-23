package com.digichubs.entryme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.digichubs.dbase.*;
import com.digichubs.misc.Constants;

public class EntryMe extends ListActivity {

	List<Map<String, String>> list = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		DBAdapter db = new DBAdapter(this);		
		
		db.open();//open db
			
			Cursor c = db.getAllItems();
			list = new ArrayList<Map<String, String>>();
			
			if (c.moveToFirst()) {				
		       do 
		       {
		        Map<String, String> map = new HashMap<String, String>();
				map.put("id", c.getString(0));
				map.put("icon", Integer.toString(R.drawable.icon));
				map.put("entryTitle", c.getString(1));	
				map.put("entryLabels", "labels: " + c.getString(2));
				System.out.println("EntryTitle: " +c.getString(1) + " , Category: " + c.getString(3));
				list.add(map);
		       } 
		       while(c.moveToNext());
	        }//if
		
		db.close();//close db
		
		SimpleAdapter adapter = new SimpleAdapter(
				this.getApplicationContext(),
				list, 
				R.layout.entrymelist, 
				new String[] {"icon","entryLabels", "entryTitle" }, 
				new int[] {R.id.icon,R.id.entryLabels, R.id.entryTitle });

		setListAdapter(adapter);		
		registerForContextMenu(getListView());

	}// onCreate
	

	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(getApplicationContext(), list.get(position).get("entryTitle") + "\n" + list.get(position).get("entryLabels"),
					   Toast.LENGTH_SHORT).show();
				
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		menu.setHeaderTitle(list.get(info.position).get("entryTitle"));
		menu.add(0, 1, 0, "Edit");
		menu.add(0, 2, 0,  "Share");
		menu.add(0, 3, 0,  "Delete");		
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();								
				
		Intent intent = new Intent();
		Bundle extras = new Bundle();	
		
		switch (item.getItemId()) {
		case 1:
			//Edit 
			intent = new Intent(EntryMe.this,EditEntry.class);
			extras.putString("id", list.get(info.position).get("id"));
			intent.putExtras(extras);
			EntryMe.this.startActivity(intent);
			return true;
		case 2:
			//Share
			Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();
			return true;
		case 3:
			//Delete
			Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
	
	/* Menu Item Created */
	public boolean onCreateOptionsMenu(Menu menu){
		
		boolean result = super.onCreateOptionsMenu(menu);
		
		SubMenu addentry = menu.addSubMenu(0, Constants.ADD_ENTRY, 0, "Add");
		SubMenu searchlist = menu.addSubMenu(0, Constants.SEARCH_LIST, 0, "Search");
		
		addentry.add(0, Constants.Appointment, 0, "Appointment");
		addentry.add(0, Constants.Events, 0, "Events");
		addentry.add(0, Constants.Notes, 0, "Notes");
		addentry.add(0, Constants.Task, 0, "Task");
		addentry.add(0, Constants.ToDo, 0, "ToDo");
		addentry.add(0, Constants.Travel, 0, "Travel");
		
		return result;
		
	}//onCreateOptionsMenu
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent();	
		Bundle extras = new Bundle();
		
		switch(item.getItemId()) 
		{
			//0
			case Constants.Appointment:
				intent = new Intent(EntryMe.this,AddEntry.class);					
				extras.putInt("itemType", Constants.Appointment);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			//1
			case Constants.Events:
				intent = new Intent(EntryMe.this,AddEntry.class);	
				extras.putInt("itemType", Constants.Events);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			//2
			case Constants.Notes:
				intent = new Intent(EntryMe.this,AddEntry.class);	
				extras.putInt("itemType", Constants.Notes);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			//3
			case Constants.Task:
				intent = new Intent(EntryMe.this,AddEntry.class);	
				extras.putInt("itemType", Constants.Task);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			//4
			case Constants.ToDo:
				intent = new Intent(EntryMe.this,AddEntry.class);	
				extras.putInt("itemType", Constants.ToDo);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			//5
			case Constants.Travel:
				intent = new Intent(EntryMe.this,AddEntry.class);	
				extras.putInt("itemType", Constants.Travel);
				intent.putExtras(extras);
				EntryMe.this.startActivity(intent);
				return true;
			
			//6	
			case Constants.SEARCH_LIST:
				return true;
				
			default:
				return false;
		}			   
	    
	}//onOptionsItemSelected

}// EntryMe