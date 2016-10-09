//
// This example is adopted from Android Programming for Handheld Devices
//          by Dr. Adam Porter
//          www.coursera.com
//

package edu.uco.hsung.broadcastreceiverdynamicregistration;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String CUSTOM_INTENT =
            "course.examples.BroadcastReceiver.show_toast";
    private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);
    private final Receiver receiver = new Receiver();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerReceiver(receiver, intentFilter);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendBroadcast(new Intent(CUSTOM_INTENT));
            }
        });
    }

    @Override
    protected void onRestart() {
        registerReceiver(receiver, intentFilter);
        super.onRestart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(receiver);
        super.onStop();
    }

}