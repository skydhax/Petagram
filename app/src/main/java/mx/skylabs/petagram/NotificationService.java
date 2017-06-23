package mx.skylabs.petagram;


import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;


import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.content.Intent;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by sky on 24/05/17.
 */

public class NotificationService extends FirebaseMessagingService {


    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG,remoteMessage.getFrom());

        Intent i = new Intent();
        i.setAction("LIKE");
        i.setAction("UNFOLLOW");
        i.setAction("OPEN_MY_PROFILE");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.paw, "Te han dado Like", pendingIntent).build();
        NotificationCompat.Action actionUnfollow = new NotificationCompat.Action.Builder(R.drawable.paw, "Te han dejado de seguir", pendingIntent).build();
        NotificationCompat.Action actionOpenMyProfile = new NotificationCompat.Action.Builder(R.drawable.paw, "Abriendo mi perfil", pendingIntent).build();


        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.background))
                .setGravity(Gravity.CENTER_VERTICAL);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.bone1)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                //.addAction(R.drawable.paw, "Te han dado Like",pendingIntent)
                .extend(wearableExtender.addAction(action))
                .extend(wearableExtender.addAction(actionUnfollow))
                //.extend(wearableExtender.addAction(actionOpenMyProfile))
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);


        notificationManager.notify(NOTIFICATION_ID,builder.build());

    }


}