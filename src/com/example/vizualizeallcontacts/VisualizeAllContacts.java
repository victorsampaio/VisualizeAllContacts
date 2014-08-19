package com.example.vizualizeallcontacts;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VisualizeAllContacts extends Activity implements OnClickListener {

	private static final int SELECT_CONTACT = 1;
	private static final String CATEGORY = "android";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualize_all_contacts);

		Button btn = (Button) findViewById(R.id.btnVisuAllContacts);
		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		Uri uri = Uri.parse("content://com.android.contacts/contacts/");

		Intent it = new Intent(Intent.ACTION_PICK, uri);
		startActivityForResult(it, SELECT_CONTACT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent it) {
		
		/** Returning results to Intent (1)**/
		/*
		  if (it == null) { 
		  Toast.makeText(this, "No Contact", Toast.LENGTH_SHORT).show(); 
		  return;
		  } 
		 
		  // To visualize the selected contact 
		  Uri uri = it.getData(); 
		  Toast.makeText(this, "Contact: " + uri, Toast.LENGTH_SHORT).show();
		 */

		/** Returning results to Intent (2) **/
		/*
		  Uri uri = it.getData(); 
		  startActivity(new Intent(Intent.ACTION_VIEW, uri));
		 */

		/** Returning results to Intent (3)**/
		Uri uri = it.getData();

		Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		cursor.moveToNext();

		int nameColumn = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
		String name = cursor.getString(nameColumn);
		Toast.makeText(this, "Name: " + name, Toast.LENGTH_SHORT).show();
		cursor.close();
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.visualize_all_contacts, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
