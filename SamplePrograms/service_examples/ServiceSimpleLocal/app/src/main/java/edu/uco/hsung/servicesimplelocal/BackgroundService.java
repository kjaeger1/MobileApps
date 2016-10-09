package edu.uco.hsung.servicesimplelocal;

// BackgroundService.java
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BackgroundService extends Service {
	
	private static final String TAG = "BackgroundService";
	private static final int MY_NOTIFICATION_ID = 1;
	private NotificationManager notificationMgr;
	private Thread serviceThread;
	private boolean stopThread = false;

	@Override
	public void onCreate() {
		super.onCreate();

		Log.v(TAG, "in onCreate()");
		notificationMgr = (NotificationManager)
				getSystemService(NOTIFICATION_SERVICE);
		displayNotificationMessage("Background Service is running", 0);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		int counter = intent.getExtras().getInt("counter");
        serviceThread = new Thread(new ServiceWorker());
        serviceThread.start();
		return START_NOT_STICKY;
	}

	class ServiceWorker implements Runnable {
		private int counter = 1;
		public void run() {
			try {
				while (!stopThread) {
					displayNotificationMessage(
							new java.util.Date().toString(), counter++);
					Thread.sleep(1000); // sleeping for 1 sec
				}
			} catch (InterruptedException e) {
                e.printStackTrace();
			}

		}
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy() of background service");
		stopThread = true;
		notificationMgr.cancelAll();
		super.onDestroy();
	}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

	private void displayNotificationMessage(String message, int counter) {
		
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, MainActivity.class), 0);
		
		Notification.Builder notificationBuilder = new Notification.Builder(
				getApplicationContext())
				.setSmallIcon(R.drawable.emo_im_winking)
				.setAutoCancel(true)
				.setContentIntent(contentIntent)
				.setContentTitle(message)
				.setContentText("Counter=" + counter + " "+System.currentTimeMillis());

		notificationMgr.notify(MY_NOTIFICATION_ID, notificationBuilder.build());
	}
}
