package edu.uco.hsung.smsmessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
    private String TAG = "SmsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        // Get the data (SMS data) bound to intent
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "";
        if (bundle != null) {
            // Retrieve the SMS Messages received
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            // For every SMS message received
            for (int i=0; i < msgs.length; i++) {
                // Convert Object array: API 23 or higher
                String format = bundle.getString("format");
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i], format);
                // Sender's phone number
                str += "SMS from " + msgs[i].getOriginatingAddress() + " : ";
                // Fetch the text message
                str += msgs[i].getMessageBody().toString();
                str += "\n";
            }
            // Display the entire SMS Message
            Log.d(TAG, str);
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}