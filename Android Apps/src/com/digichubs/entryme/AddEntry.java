package com.digichubs.entryme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.digichubs.dbase.*;
import com.digichubs.misc.Constants;

public class AddEntry extends TabActivity  {
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabdetails);
		
		Bundle extras = getIntent().getExtras();
		int itemType = extras.getInt("itemType");
		
		String category = "";
		
		Resources res = getResources(); 
		TabHost tabHost = getTabHost(); 
		TabHost.TabSpec spec;  
		Intent intent; 
		
	    intent = new Intent().setClass(this, Overview.class);
	    spec = tabHost.newTabSpec("overview").setIndicator("Overivew").setContent(intent);
	    tabHost.addTab(spec);
	    
	    switch(itemType)
	    {
	    	case Constants.Appointment:
	    			category = "Appointment";
	    		break;
	    		
	    	case Constants.Events:
    				category = "Events";
		    		intent = new Intent().setClass(this, EventsToDoTravel.class);
		    	    spec = tabHost.newTabSpec("details").setIndicator("Event Details").setContent(intent);
		    	    tabHost.addTab(spec);
	    		break;
	    		
	    	case Constants.Notes:
    				category = "Notes";
		    		intent = new Intent().setClass(this, EventsToDoTravel.class);
		    	    spec = tabHost.newTabSpec("details").setIndicator("Notes Details").setContent(intent);
		    	    tabHost.addTab(spec);
	    		break;
	    		
	    	case Constants.Task:
    				category = "Task";
	    		break;
	    		
	    	case Constants.ToDo:
    				category = "ToDo";
		    		intent = new Intent().setClass(this, EventsToDoTravel.class);
		    	    spec = tabHost.newTabSpec("details").setIndicator("ToDo Details").setContent(intent);
		    	    tabHost.addTab(spec);
	    		break;
	    		
	    	case Constants.Travel:
    				category = "Travel";
		    		intent = new Intent().setClass(this, EventsToDoTravel.class);
		    	    spec = tabHost.newTabSpec("details").setIndicator("Travel Details").setContent(intent);
		    	    tabHost.addTab(spec);
	    		break;
	    }
	   
	    /*
	    intent = new Intent().setClass(this, Details.class);
	    spec = tabHost.newTabSpec("details").setIndicator("Details").setContent(intent);
	    tabHost.addTab(spec);
	    */

	}// onCreate
	

}// AddEntry
