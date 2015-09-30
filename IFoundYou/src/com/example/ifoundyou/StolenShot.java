package com.example.ifoundyou;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;
import com.example.ifoundyou.objects.SendMailService;

public class StolenShot extends SherlockActivity implements SurfaceHolder.Callback{
	// =========================================================================
	// TODO Variables
	// =========================================================================
	private ImageView ivPic;
	private SurfaceView sv;
	private SurfaceHolder sHolder;
	private Bitmap bmp;
	private Camera camera;
	private Parameters params;
	
	private String Latitude = "0";
	private String Longitude = "0";
	
	public static String ACTION = "action";
	
	private String getAction ="";
	// =========================================================================
	// TODO Life Cycles
	// =========================================================================
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//
		setContentView(R.layout.stolen_shot);
		//
		ivPic = (ImageView)findViewById(R.id.ivPic);
		sv = (SurfaceView)findViewById(R.id.svCapture);
		sHolder = sv.getHolder(); 
		sHolder.addCallback(this);
		sHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		//
		// GPS Coordinates
		LocationManager lm = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener ll = new LocationListener() {
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
			}
			@Override
			public void onProviderEnabled(String provider) {
			}
			@Override
			public void onProviderDisabled(String provider) {
			}
			@Override
			public void onLocationChanged(Location location) {
				Latitude = location.getLatitude()+"";
				Longitude = location.getLongitude()+"";
			}
		};
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, ll);
		//
		new Handler().postDelayed(runner, 3000);
	}
	// =========================================================================
	// TODO Implementation
	// =========================================================================
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//
		camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
		try {
			camera.setPreviewDisplay(holder);
		} catch (Exception e) {
			// 
			camera.release();
			camera = null;
		}
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
		//
		params = camera.getParameters();
		camera.setParameters(params);
		camera.startPreview();
		
		camera.takePicture(null, null, call);
		//
	}
	

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//
		camera.stopPreview();
		camera.release();
		camera = null;
		//
		
		//finish();
		
	}

	PictureCallback call = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			//
			bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
			ivPic.setImageBitmap(bmp);
			
			new SavePhoto().execute(data);
		}
	};
	
	private Runnable runner = new Runnable(){
		@Override
		public void run() {
			finish();
		}
	};
	// =========================================================================
	// TODO Inner Class
	// =========================================================================
	private class SavePhoto extends AsyncTask<byte[], String, String>{

		@SuppressLint("SimpleDateFormat")
		@Override
		protected String doInBackground(byte[]... params) {
			// 
			File dirRoot = new File(Environment.getExternalStorageDirectory(),"/IFoundYou");
			if(!dirRoot.exists())
				dirRoot.mkdir();
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd.hh.mm");
			String filename = "Snatcher_" + formatter.format(today) + ".jpg";
			
			File photo = new File(dirRoot.getAbsolutePath(), filename);
			if(photo.exists()){
				photo.delete();
			}
			 
			
			try {
				FileOutputStream fos = new FileOutputStream(photo.getPath());
				fos.write(params[0]);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return photo.getPath();
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			String act = getIntent().getExtras().getString(ACTION);
			Intent intent = new Intent(getBaseContext(), SendMailService.class);
			intent.putExtra(SendMailService.FILEPATH, result);
			intent.putExtra(SendMailService.ACTION, act);
			intent.putExtra(SendMailService.LATITUDE, Latitude);
			intent.putExtra(SendMailService.LONGITUDE, Longitude);
			getBaseContext().startService(intent);
			
			finish();
			//System.exit(0);
		}
		
	}
}
