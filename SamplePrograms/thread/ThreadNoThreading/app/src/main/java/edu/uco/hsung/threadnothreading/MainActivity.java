//
// This example is adopted from Android Programming for Handheld Devices
//          by Dr. Adam Porter
//          www.coursera.com

package edu.uco.hsung.threadnothreading;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Bitmap mBitmap;
    private ImageView mIView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIView = (ImageView) findViewById(R.id.imageView);

        final Button loadButton = (Button) findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadIcon();
                if (null != mBitmap) {
                    mIView.setImageBitmap(mBitmap);
                }
            }
        });

        final Button otherButton = (Button) findViewById(R.id.otherButton);
        otherButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "I'm Working",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadIcon() {
        // to simulate long loading process
        try {
            Thread.sleep(5000); // 5 sec delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.painter);
    }
}