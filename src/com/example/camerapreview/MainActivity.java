package com.example.camerapreview;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	Camera mCamera;
	CameraPreview mPreview;
	final static String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mCamera = getCameraInstance();
		mPreview = new CameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}
	
	public static Camera getCameraInstance() {
		Camera c = null;
		
		try {
			c = Camera.open();
		} catch (Exception e) {
			Log.e(TAG, "failed to open camera: " + e.getMessage(), e);
		}
		
		return c;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mPreview.stop();
		mPreview = null;
	}
	
	/*
	private boolean safeCameraOpen(int id) {
		boolean qOpened = false;
		
		try {
			releaseCameraAndPreview();
			mCamera = Camera.open(id);
			qOpened = (mCamera != null);
		} catch (Exception e) {
			Log.e(getString(R.string.app_name), "failed to open Camera");
			e.printStackTrace();
		}
		
		return qOpened;
	}
	
	private void releaseCameraAndPreview() {
		mPreview.setCamera(null);
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
	}
	*/
	
	
}
