package money.system.book.maker;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.media.RatingCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

import money.system.book.maker.Activity.Main3Activity;
import money.system.book.maker.Activity.MainActivity;
import money.system.book.maker.Models.Quote2;

public class NotificationHelper {
    private final Context mContext;
    int n;
    public static int mId;
    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    private Quote2 quote2;

    public NotificationHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void ListQuoteAndSendNotification() {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        databaseHelper.readQuoteFromDatabase();
        int quoteSize = databaseHelper.readQuoteFromDatabase().size();


        Random rand = new Random();
        n = rand.nextInt(quoteSize);
        databaseHelper.readQuoteFromDatabase().get(n);
        mId = databaseHelper.readQuoteFromDatabase().get(n).getId();

        // Toast.makeText(mContext, "size is :" + quoteSize, Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, "" + n + " id is : " + mId, Toast.LENGTH_SHORT).show();

        setNotification(databaseHelper.readQuoteFromDatabase().get(n));


    }

    public void setNotification(Quote2 quote2) {
        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }
        /*RemoteViews contentView = new RemoteViews("money.system.book.maker", R.layout.custom_notif);
        contentView.setImageViewResource(R.id.image, R.drawable.ic_message_orange_24dp);
        contentView.setTextViewText(R.id.title, quote2.getName());
        contentView.setTextViewText(R.id.text, quote2.getContent());*/


        Intent intentShare = new Intent(mContext, Main3Activity.class);
        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intentShare, 0);

       // Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://google.com/"));
       // PendingIntent pi1 = PendingIntent.getActivity(mContext, 0, intent1, 0);

        assert CHANNEL_ID != null;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message_orange_24dp)
                .setContentTitle(quote2.getName())
                .setColor(0xFB8C00)
                //.addAction(R.drawable.ic_cancel_white_24dp, "حذف ", pi1)
                .addAction(R.drawable.ic_share_white_24dp, "اشتراک گذاری", pi)
                .setSound(path)
                .setAutoCancel(true)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setLights(Color.YELLOW, 3000, 3000)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_message_orange_24dp))
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(quote2.getContent()));
// اگر بخواهیم با زدن روی نوتیف یه اکتیویتی باز بشه این کدها فعال میشن
        // Intent resultIntent = new Intent(mContext, Main3Activity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
//        stackBuilder.addParentStack(MainActivity.class);
//        stackBuilder.addNextIntent(resultIntent);
        // PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //   builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
















       /* Intent intent = new Intent(mContext, Main3Activity.class);
        Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nikandroid.com/"));
        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, 0);
        PendingIntent pi1 = PendingIntent.getActivity(mContext, 0, intent1, 0);
        Notification notification = new NotificationCompat.Builder(mContext)
                .setTicker("نوتیف")
                .setContentTitle(quote2.getName())
               // .setContentText(quote2.getContent())
                .setSmallIcon(R.drawable.information)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(quote2.getContent()))
               // .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrow_right))
                // .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.image_1)))
                .setAutoCancel(true)
                .setSound(path)
                .addAction(R.drawable.arrow_right, "Open Activity", pi)
                .addAction(R.drawable.arrow_right, "Open Site", pi1)
                .build();
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);*/


    }
}
