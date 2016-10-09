package edu.uco.hsung.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Calendar;

/*
     This example was adopted from:
     http://android-er.blogspot.com/2015/04/example-of-using-alarmmanager-to.html
 */


public class MainActivity extends Activity implements View.OnClickListener {

    final static int RQS_1 = 1;
    Chronometer chronometer;
    Button btnSetNoCheck, btnSetWithVerCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer)findViewById(R.id.chronometer);
        btnSetNoCheck = (Button)findViewById(R.id.setnocheck);
        btnSetNoCheck.setOnClickListener(this);
        btnSetWithVerCheck = (Button)findViewById(R.id.setwithversioncheck);
        btnSetWithVerCheck.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        //10 seconds later
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent =
                PendingIntent.getBroadcast(getBaseContext(),
                        RQS_1, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager =
                (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        if(v==btnSetNoCheck) {
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    cal.getTimeInMillis(), pendingIntent);
            Toast.makeText(getBaseContext(),
                    "call alarmManager.set()",
                    Toast.LENGTH_SHORT).show();
        } else {
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(), pendingIntent);
                Toast.makeText(getBaseContext(),
                        "call alarmManager.set()",
                        Toast.LENGTH_SHORT).show();
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                        cal.getTimeInMillis(), pendingIntent);
                Toast.makeText(getBaseContext(),
                        "call alarmManager.setExact()",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

}

