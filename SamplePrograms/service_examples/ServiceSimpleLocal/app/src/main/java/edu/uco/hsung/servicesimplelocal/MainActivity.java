package edu.uco.hsung.servicesimplelocal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private int counter = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        BackgroundService.class);
                intent.putExtra("counter", counter++);
                startService(intent);
            }
        });

        Button stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stopService(new Intent(MainActivity.this, BackgroundService.class)))
                    Log.v(TAG, "stopService was successful");
                else
                    Log.v(TAG, "stopService was unsuccessful");
            }
        });
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(MainActivity.this, BackgroundService.class));
        super.onDestroy();
    }
}
