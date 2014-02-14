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

	//1.  For any list, I need a ListView, and an adapter to go with it...
	public ListView listView;
	public SimpleListAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//2.  Set the content view to a particular layout file.
		setContentView(R.layout.activity_main);			
		
		//3.  Now I need to initiate the listView variable, and actually assign it to a list view.  I do this with "findViewById".  I can run this here, 
		//because my MainActivity class extends Activity, which comes with a "findViewById" method.  This activity knows what R.id.listView1 is, because 
		//R.id.listView1 is a child of the R.layout.activity_main file, which is this Activity's current layout. 
		listView = (ListView)findViewById(R.id.listView1);
		
		//4.  Also, I make the background black because its easier to see...
		listView.setBackgroundColor(Color.BLACK);
		
		//5.  Now, I need to initiate the adapter to go with my list view.  It needs a reference to the context, so I give it that.
		adapter = new SimpleListAdapter(getApplicationContext());
		
		//6.  Once I create the adapter, I have to actually pair it up with the listView...
		listView.setAdapter(adapter);		
		
		//7.  Now, I run a special method which populates the list.  Go down to getPics() to see more detail.
		getPics();
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	private void getPics(){
		
		
		//1.  So the goal here is to read all of the pictures out of a certain directory, and use them to create list items that then go into my listView.  So, step 1 is 
		//I go in and designate the directory I want to pull data out of...
		File sdDir = new File("/sdcard/DCIM/Camera");
		
		//2.  Now, I make an array of all of the files in that directory.		
		File[] sdDirFiles = sdDir.listFiles();
		
		//3.  Now, for each file in that directory, I will do something...
		for(File singleFile : sdDirFiles)
		{		   
			
		  //4.  For each file in the directory, I am going to create a "SimpleItem".  This is a class I have created, and all it is is a thumbnail of the picture,
		  //a String for the file name, and another String for the file size.  So, first I will create the thumbnail.  The Drawable class has a method that lets
		  //me do that, given a file path, so I run that for each file...
		  Drawable d = Drawable.createFromPath(singleFile.getAbsolutePath());
		  
		  //5.  Next, I get the name and file size of the file.
		  String name = singleFile.getName();
		  String size = String.valueOf(singleFile.length());
		 
		  //6.  Now, I take this data, and put it into a new SimpleItem that I have constructed.
		  SimpleItem i = new SimpleItem();
		  i.name = name;
		  i.size = size;
		  i.pic = d;
		  
		  //7.  Finally, I add this new item to the ListAdapter.  In there is where the real work happens.  I have two ways of adding items.  Just "adapter.add(SimpleItem i)" 
		  //will add the item, and make the list refresh itself.  Or, if you want to add a bunch of items first and only refresh the list once, 
		  //you can use "adapter.addButDontUpdate(SimpleItem i)", and then later run adapter.updateManually().  Now, go over to SimpleListAdapter to see how this works.
		  
		  adapter.addButDontUpdate(i);
		  
		}
		adapter.updateManually();
		
	}
	
}
