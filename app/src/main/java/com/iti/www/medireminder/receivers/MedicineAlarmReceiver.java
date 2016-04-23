package com.iti.www.medireminder.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.iti.www.medireminder.R;
import com.iti.www.medireminder.activities.AlarmTrigger;
import com.iti.www.medireminder.activities.HomeActivity;

/**
 * Created by Shall on 18/4/2016.
 */
public class MedicineAlarmReceiver extends BroadcastReceiver {
    int notificationId;
    String notificationMessage;
    NotificationManager mNotificationManager;
    MediaPlayer mMediaPlayer;
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        context = context;
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Log.i("MedicineAlarmsReceiver", "bundle is null");
            return;
        }

        notificationId = bundle.getInt("notificationId");
        notificationMessage = bundle.getString("notificationMessage");


        Toast.makeText(context, notificationMessage,
                Toast.LENGTH_LONG).show();
//		Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Image");
//		try {
//			context.setWallpaper(bitmap);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        Log.i("Messgae ", notificationMessage);
        Log.i("ID ", notificationId + "");
        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        String s = Context.NFC_SERVICE;
        Notification notification = new Notification(R.drawable.logo,
                "Time to take medicine", System.currentTimeMillis());
        CharSequence contentTitle = "Medicine time";
        CharSequence contentText = notificationMessage;
        Intent notificationIntent = new Intent(context, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);
        notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
//        notification.setLatestEventInfo(context, contentTitle, contentText,
//                contentIntent);
        final int HELLO_ID = 001;

       // mNotificationManager.notify(HELLO_ID, notification);
       // mNotificationManager.


        //------------------------------------------------------------------------------------------

        notification = new NotificationCompat.Builder(context)
                .setAutoCancel(false)
                .setContentTitle("Exercise of Notification!")
                .setContentText("http://android-er.blogspot.com/")
                .setTicker("Notification!")
                .setSmallIcon(R.drawable.logo)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .setNumber(notificationId)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();

        mNotificationManager.notify(HELLO_ID, notification);
        //------------------------------------------------------------------------------------------
        Intent i = new Intent(context, AlarmTrigger.class);
        i.putExtra("notificationId", notificationId);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}

