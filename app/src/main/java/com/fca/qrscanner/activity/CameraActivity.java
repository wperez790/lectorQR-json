package com.fca.qrscanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.fca.qrscanner.R;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {

    private SurfaceView cameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layout);
        cameraView = findViewById(R.id.camera_view);
        createCameraSource();
    }


private void createCameraSource() {

    BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();

    final CameraSource cameraSource  = new CameraSource.Builder(this,barcodeDetector)
            .setAutoFocusEnabled(true)
            .setRequestedFps(24)
            .setFacing(CameraSource.CAMERA_FACING_BACK)
            .setRequestedPreviewSize(1920, 1080).build();

    cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                cameraSource.start(cameraView.getHolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            cameraSource.stop();
        }
    });

    barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
        @Override
        public void release() {

        }

        @Override
        public void receiveDetections(Detector.Detections<Barcode> detections) {

            SparseArray<Barcode> barcodes = detections.getDetectedItems();
            if(barcodes.size() > 0 ) {
                Intent i = new Intent();
                i.putExtra("barcode",barcodes.valueAt(0) );
                setResult(CommonStatusCodes.SUCCESS, i);
            }

        }
    });
    }


}