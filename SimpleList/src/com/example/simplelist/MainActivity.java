package com.example.simplelist;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	public ListView listView;
	public SimpleListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		listView = (ListView)findViewById(R.id.listView1);
		listView.setBackgroundColor(Color.BLACK);
		adapter = new SimpleListAdapter(getApplicationContext());
		listView.setAdapter(adapter);		
		getPics();
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	private void getPics(){
		
		File sdDir = new File("/sdcard/DCIM/Camera");
		File[] sdDirFiles = sdDir.listFiles();
		for(File singleFile : sdDirFiles)
		{		   
			
		  Drawable d = Drawable.createFromPath(singleFile.getAbsolutePath());
		  String name = singleFile.getName();
		  String size = String.valueOf(singleFile.length());
		 
		  SimpleItem i = new SimpleItem();
		  i.name = name;
		  i.size = size;
		  i.pic = d;
		  
		  adapter.addButDontUpdate(i);
		  
		}
		adapter.updateManually();
		
	}
	
}
