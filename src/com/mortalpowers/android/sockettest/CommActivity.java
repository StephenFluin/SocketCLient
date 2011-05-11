package com.mortalpowers.android.sockettest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CommActivity extends Activity {
	Communicator comBadge;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		Context c = getBaseContext();
		final EditText msg = (EditText) findViewById(R.id.editText1);
		Button b = (Button) findViewById(R.id.button1);
		comBadge = new Communicator(this);
		comBadge.execute("Let's do this");
		debugMsg("Started thing");

		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try {
					debugMsg( "sending string" + msg.getText().toString());
					comBadge.send(msg.getText().toString());
				} catch (Exception e) {
					debugMsg( "error was:" + e.getMessage());
				}
			}
		});

	}

	public void debugMsg(String message) {
		Log.d("Comm",message);
	}

}