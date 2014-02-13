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

	ArrayList<SimpleItem> items;
	Context mContext;
		
	public void addItem(SimpleItem item){
		items.add(item);
		notifyDataSetChanged();
	}
	
	public void addButDontUpdate(SimpleItem item){
		items.add(item);
	}
	
	public void updateManually(){
		notifyDataSetChanged();
	}
	
	public SimpleListAdapter(Context context){
		mContext = context;
		items = new ArrayList<SimpleItem>();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View itemView = LayoutInflater.from(mContext).inflate(R.layout.listitem, null);
		TextView et = (TextView)itemView.findViewById(R.id.TextView1);
		et.setText(items.get(position).name);
		TextView et2 = (TextView)itemView.findViewById(R.id.TextView2);
		et2.setText(items.get(position).size);
		ImageView image = (ImageView) itemView.findViewById(R.id.imageView1);
		
		
		Bitmap b = ((BitmapDrawable)items.get(position).pic).getBitmap();	
		Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 100, false); 
		image.setImageDrawable(new BitmapDrawable(mContext.getResources(), bitmapResized));
		
		return itemView;
	}

}
