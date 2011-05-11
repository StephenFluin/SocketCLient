package com.mortalpowers.android.sockettest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class Communicator extends
		android.os.AsyncTask<String, String, Boolean> {
	BufferedReader in;
	BufferedWriter out;
	CommActivity parent = null;
	
	public Communicator(CommActivity commActivity) {
		this.parent = commActivity;
	}

	@Override
	protected void onProgressUpdate(String... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected Boolean doInBackground(String... params) {
		Log.d("Comm",params.toString());
		// TODO Auto-generated method stub
		
		Socket s;
		try {
			s = new Socket("mortalpowers.com", 5000);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			send("Client connected.");
			String line;
			line = receive();
			while(line != null) {
				
				publishProgress(line);
				line = receive();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		parent.debugMsg("Finished communication background.");
		
		return null;
	}
	
	public void send(String s) {
		try {
			out.write(s);
			parent.debugMsg("Sending " + s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String receive() {
		try {
			String s = in.readLine();
			parent.debugMsg("Receive:" + s);
			return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


}
