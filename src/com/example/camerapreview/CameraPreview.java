package com.example.camerapreview;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	private SurfaceHolder mHolder;
	private Camera mCamera;
	private final String TAG = "CameraPreview";
	
	public CameraPreview(Context context, Camera camera) {
		super(context);
		mCamera = camera;
		
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mCamera.setPreviewDisplay(holder);
			mCamera.startPreview();
		} catch (IOException e) {
			mCamera.release();
			mCamera = null;
			Log.d(TAG, "Error setting camera preview: " + e.getMessage());
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		stop();
	}
	
	public void stop() {
		if (null == mCamera) {
			return;
		}
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		if (mHolder.getSurface() == null) {
			return;
		}
		
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			
		}
		
		try {
			mCamera.startPreview();
		} catch (Exception e) {
			Log.d(TAG, "Error starting camera preview: " + e.getMessage());
		}
	}
}
