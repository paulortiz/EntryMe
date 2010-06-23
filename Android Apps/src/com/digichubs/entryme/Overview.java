package com.digichubs.entryme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;

import com.digichubs.dbase.*;
import com.digichubs.misc.Constants;

public class Overview extends Activity  implements Runnable{
	
	
	EditText overview;
	EditText notes;
	EditText labels;	
	private ProgressDialog pd;
	private String pi_string;
	private TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);
        
        DBAdapter db = new DBAdapter(this);
        
        final ToggleButton togglebutton = (ToggleButton) findViewById(R.id.togglebutton);
        togglebutton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                if (togglebutton.isChecked()) {
                	System.out.println("===========================" );
                    System.out.println("Toggle Button: ON " );
                    System.out.println("===========================" );
                } else {
                	System.out.println("===========================" );
                	System.out.println("Toggle Button: OFF " );
                	System.out.println("===========================" );
                }
            }
        });
        
        overview = (EditText)findViewById(R.id.overview);
        notes = (EditText)findViewById(R.id.notes);
        labels = (EditText)findViewById(R.id.labels);
        
	}// onCreate
	
	/* Menu Item Created */
	public boolean onCreateOptionsMenu(Menu menu){
		
		boolean result = super.onCreateOptionsMenu(menu);
		
		menu.add(0, 1, 0, "Save");
		menu.add(0, 2, 0, "Clear");					
		
		return result;
		
	}//onCreateOptionsMenu
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent();	
		Bundle bundle = new Bundle();
		
		try {
			switch(item.getItemId()) {
			
				case 1:	
					pd = ProgressDialog.show(this, "Working..", "Saving Item.", true,false);
				    Thread thread = new Thread(this);
					Thread.sleep(100, 1000);
				    thread.start();
					return true;
				case 2:
					//clear
					return true;
				default:
					return false;
			}	
		} catch(Exception e) {
			
		}
	    
		return false;
	}//onOptionsItemSelected
	
	public boolean saveItems() {
		
		return false;
	}
	
	@Override
	public void run() {
		//pi_string = Pi.computePi(800).toString();
		handler.sendEmptyMessage(0);
		
	}
	
	private Handler handler = new Handler() {
	     public void handleMessage(Message msg) {
	              pd.dismiss();
	     }
	};

}// AddEntry
