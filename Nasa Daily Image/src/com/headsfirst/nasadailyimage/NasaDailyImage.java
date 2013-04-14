package com.headsfirst.nasadailyimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class NasaDailyImage extends Fragment {

	private static final String URL = "http://www.nasa.gov/rss/image_of_the_day.rss";
	private IotdHandler iotdHandler;
	private Bitmap dailyImage;

	public void onSetWallpaper(View view) {
		Thread th = new Thread() {
		
			public void run() {
				WallpaperManager wallpaperManager = WallpaperManager
						.getInstance(getActivity());
				try {
					wallpaperManager.setBitmap(dailyImage);
					
					Toast.makeText(getActivity(),
							"Wallpaper set",
							Toast.LENGTH_SHORT).show();
					
					
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(getActivity(),
							"Error setting wallpaper",
							Toast.LENGTH_SHORT).show();
				}
			}
		};
		th.start();

	}

	public void onRefreshButtonClicked(View view) {
		refreshFromFeed();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		return inflater.inflate(
				R.layout.activity_nasa_daily_image, container, false); 		
	}
	
	public void onStart() {
		super.onStart();
		refreshFromFeed();

	}

	private void refreshFromFeed() {

		RetreiveFeedTask retreiveFeedTask = new RetreiveFeedTask(getActivity());

		try {
			IotdHandler iotdHandler = retreiveFeedTask.execute(URL).get();
			resetDisplay(iotdHandler.getTitle(), iotdHandler.getDate(),
					iotdHandler.getUrl(), iotdHandler.getDescription());
		} catch (Exception e) {
			Log.e("", e.toString());
			System.out.println(e.toString());
		}
	}

	private void resetDisplay(String title, String date, String imageUrl,
			String description) throws InterruptedException, ExecutionException {
		TextView titleView = (TextView) getActivity().findViewById(R.id.imageTitle);
		titleView.setText(title);

		TextView dateView = (TextView) getActivity().findViewById(R.id.imageDate);
		dateView.setText(date);

		ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageDisplay);
		setDailyImage(new RetrieveURLImage().execute(imageUrl).get());
		imageView.setImageBitmap(dailyImage);

		TextView descriptionView = (TextView) getActivity().findViewById(R.id.imageDescription);
		descriptionView.setText(description);
	}

	public Bitmap getDailyImage() {
		return dailyImage;
	}

	public void setDailyImage(Bitmap dailyImage) {
		this.dailyImage = dailyImage;
	}

}