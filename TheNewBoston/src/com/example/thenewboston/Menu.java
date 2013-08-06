package com.example.thenewboston;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	String classes[] = {"TheStartingPoint", "TextPlay", "Email", "Camera", "Data", "GFX", "GFXSurface", "SoundStuff", "Slider"};
	//creates a menu that shows when menu button is pressed
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.cool_menu, menu);
		return true;
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		
		try
		{
			Class ourClass = Class.forName("com.example.thenewboston." + cheese);
			Intent ourIntent = new Intent(Menu.this, ourClass);
			startActivity(ourIntent);
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	//what to do if a menu button is pressed
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.aboutUs:
			Intent i = new Intent("com.example.thenewboston.ABOUTUS");
			startActivity(i);
			break;
		
		case R.id.preferences:
			Intent in = new Intent("com.example.thenewboston.PREFS");
			startActivity(in);
			break;
			
		case R.id.exit:
			finish();
			break;
		}
		return false;
	}


@Override
protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1, classes));
	}


}
