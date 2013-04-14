package com.headsfirst.nasadailyimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class RetrieveURLImage extends AsyncTask<String, Void, Bitmap> {
	


	@Override
	protected Bitmap doInBackground(String... url) {
		try { 
			HttpURLConnection connection = (HttpURLConnection)new java.net.URL(url[0]).openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(input);
			input.close();
			connection.disconnect();
			return bitmap;
		} catch (IOException ioe) {
			return null;
		}
	}

}
