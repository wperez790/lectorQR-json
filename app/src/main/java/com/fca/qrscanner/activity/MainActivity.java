package com.fca.qrscanner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fca.qrscanner.R;

public class MainActivity extends AppCompatActivity {

    private Button openScannerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        openScannerBtn = findViewById(R.id.openScannerBtn);

        openScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraScanner();
            }
        });


    }

    private void openCameraScanner() {

        Intent i = new Intent(this, CameraActivity.class);
        startActivity(i);
        finish();
    }
}
