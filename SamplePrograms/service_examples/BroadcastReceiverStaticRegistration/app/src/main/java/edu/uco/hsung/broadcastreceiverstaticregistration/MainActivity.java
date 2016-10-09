//
// This example is adopted from Android Programming for Handheld Devices
//          by Dr. Adam Porter
//          www.coursera.com
//

package edu.uco.hsung.broadcastreceiverstaticregistration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String CUSTOM_INTENT =
            "course.examples.BroadcastReceiver.show_toast";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                sendBroadcast(new Intent(CUSTOM_INTENT));
            }
        });
    }
}