package com.example.simplelist;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleListAdapter extends BaseAdapter{

	
	//1.  So, first of all, the adapter needs to keep a copy of all of the things that it is managing.  One way to think of a "thing" that the adapter is 
	//managing is that each "thing" is just a collection of the data that will go into a single element on the list.  In this case, each "thing" being managed is 
	//a SimpleItem, which is a class I defined.  The only reason I defined the class was as a container for the three fields of Picture, filename and filesize.  In any event,
	//you will want the adapter to keep a list of Type <Class> where Class is a class of items that represent all the stuff that will go into a single list item. 
	//So, we do this here.  Also, we will need a reference to the application's context, so we have that too...
	ArrayList<SimpleItem> items;
	Context mContext;
		
	//2.  So, let's say I want to add a SimpleItem to my ListView. What I actually do is add it to my adapter, and then the adapter refreshes the ListView, rebuilding it
	//with all of the things it contains (including the thing I just added).  The adding of an item to the list of items the adapter is managing is done by the items.add command.
	//Then, the notifyDataSetChanged method will automatically call some other stuff in order to rebuild the ListView, one list item at a time.
	
	//So, addItem(item) does that.
	public void addItem(SimpleItem item){
		items.add(item);
		notifyDataSetChanged();
	}
	
	//3.  As I mentioned, in the MainActivity.java comments, I also split apart the adding of an item and list refreshing into two methods, so that you can load a bunch
	//of items and then only refresh the list once, if you wish.  That is the purpose of addButDontUpdate(SimpleItem item) and updateManually().
	
	public void addButDontUpdate(SimpleItem item){
		items.add(item);
	}
	
	public void updateManually(){
		notifyDataSetChanged();
	}
	
	
	//4. Here is the adapter's constructor.  Again, we need to get the context of the application, so this ensures that we do.
	public SimpleListAdapter(Context context){
		mContext = context;
		items = new ArrayList<SimpleItem>();
	}
	
	
	//5.  Now, I think what happens is that when you call notifyDataSetChanged, it iterates through your list, and builds a new View for each list item.
	//How does it know how to iterate through the list?  I suspect it is through the following three methods... getCount(), getItem(int position), getItemId(int position).
	//Anyway, they just need to do the obvious things you would expect of them...
	
			//getCount needs to return the size of your list (probably because there is a "for (int i=0; i<adapter.getCount(); i++)" somewhere in notifyDataSetChanged...
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

			//getItem needs to, given a certain position in the list, return the item at that position.  Again, notifyDataSetChanged probably has a
			//"Object foo = adapter.getItem(i);" somewhere, being used to pull up your items as it iterates through all the positions.
	@Override
	public Object getItem(int position) {
		
		return items.get(position);
	}

			//getItemId returns a unique ID associated with each item... maybe to prevent list duplicates if you want?  I'm not sure, but I just return back position as the ID and
			//that works.
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	//6.  Finally, there is the getView method.  Here is where the real work of the adapter comes in.	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//So, first of all, we are going to create a new View, which will represent one item on our list.  What does View look like?  Well, it looks like
		//R.layout.listitem.  So, we create the java object itemView using LayoutInflater.
		View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem, null);
		
		//Now that itemView exists, and since it corresponds to the R.layout.listitem, we can count on finding some other views as children of it
		//(because we build R.layout.listitem to contain precisely those views).  So, we find the child views, and give them names...
		TextView et = (TextView)itemView.findViewById(R.id.TextView1);		
		TextView et2 = (TextView)itemView.findViewById(R.id.TextView2);		
		ImageView image = (ImageView) itemView.findViewById(R.id.imageView1);
		
		//Now, we fill our child views with the appropriate data.  What data?  Data corresponding to the fields of a SimpleItem.  Which SimpleItem?  The one in 
		//spot number "position" in our list "items".
		et.setText(items.get(position).name);
		et2.setText(items.get(position).size);
		
		//This stuff is to create the picture for the ImageView in our list, and resize it a bit.
		Bitmap b = ((BitmapDrawable)items.get(position).pic).getBitmap();	
		Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 100, false); 
		image.setImageDrawable(new BitmapDrawable(mContext.getResources(), bitmapResized));
		
		//Finally, we return this particular view, now all constructed and populated.
		return itemView;
	}

}
