//
// This example is adopted from Android Programming for Handheld Devices
//          by Dr. Adam Porter
//          www.coursera.com
//

/*
 * When "Load Icon" Button is pressed throws
 * android.view.ViewRootImpl$CalledFromWrongThreadException:
 * Only the original thread that created a view hierarchy can touch its views.
 */
package edu.uco.hsung.threadwrong;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "ThreadWrong";

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.toString());
                }
                mBitmap = BitmapFactory.decodeResource(getResources(),
                        R.drawable.painter);

                // This doesn't work in Android
                mIView.setImageBitmap(mBitmap);
            }
        }).start();
    }
}