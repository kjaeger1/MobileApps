package edu.uco.hsung.broadcastreceiverstaticregistration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

	private final String TAG = "Receiver";

	@Override
	public void onReceive(Context context, Intent intent) {

		Log.i(TAG, "INTENT RECEIVED");

		Toast.makeText(context, "INTENT RECEIVED by Receiver (Static)",
				Toast.LENGTH_SHORT).show();

	}

}
