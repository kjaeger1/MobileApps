package edu.uco.hsung.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
     in ManifestFile:
     <receiver android:name=".AlarmReceiver" android:process=":remote" />

     android:process=":remotee" means this receiver will run in a different process
     i.e., in a different VM
     By doing this, this process can keep running in its own process
     after the main application is shutdown.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,
                "AlarmReceiver.onReceive()",
                Toast.LENGTH_LONG).show();
    }

}
