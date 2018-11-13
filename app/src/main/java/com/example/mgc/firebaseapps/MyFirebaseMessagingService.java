package com.example.mgc.firebaseapps;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by mgc on 7/27/2017.
 */
public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG="Android";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //It is optional
        Log.e(TAG, "Form:" + remoteMessage.getFrom());
        Log.e(TAG, "Notification Message Body:" + remoteMessage.getNotification().getBody());

        //calling method to generate notification
        sendNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

    }
    //This method is only generating push notification

    private void sendNotification(String title,String messageBody){
        Intent intent =new Intent (this,Main2Activity.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        NotificationCompat.Builder notificationBuilder=  new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(messageBody);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defaultSoundUri).setLargeIcon(icon);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,notificationBuilder.build());

    }



}
