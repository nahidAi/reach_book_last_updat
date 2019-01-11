package money.system.book.maker;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Random;

import br.com.goncalves.pugnotification.notification.PugNotification;
import money.system.book.maker.Activity.Main3Activity;

import static android.app.Notification.DEFAULT_VIBRATE;

public class NotificationHelper {
    private final Context mContext;
    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

    public NotificationHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void ListQuoteAndSendNotification() {
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        databaseHelper.readQuoteFromDatabase();
        int quoteSize = databaseHelper.readQuoteFromDatabase().size();

        Random rand = new Random();
        int n = rand.nextInt(quoteSize);
        databaseHelper.readQuoteFromDatabase().get(n);

        Toast.makeText(mContext, "size is :" + databaseHelper.readQuoteFromDatabase().size(), Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, "" + n, Toast.LENGTH_SHORT).show();

        setNotification(databaseHelper.readQuoteFromDatabase().get(n));


    }

    public void setNotification(Quote quote) {
        Intent intent = new Intent(mContext, Main3Activity.class);
        Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nikandroid.com/"));

        PendingIntent pi = PendingIntent.getActivity(mContext, 0, intent, 0);
        PendingIntent pi1 = PendingIntent.getActivity(mContext, 0, intent1, 0);

        PugNotification.with(mContext)
                .load()
                .identifier(1)
                .title(quote.getName())
                .message(quote.getBody())
                .bigTextStyle(quote.body)
                .smallIcon(R.drawable.heart)
                .largeIcon(R.drawable.arrow_right)
                .flags(Notification.DEFAULT_ALL)
                .button(R.drawable.email, "email", pi)
                .button(R.drawable.internet_explorer, "internet", pi1)
                .click(Main3Activity.class)
                .dismiss(Main3Activity.class)
                .color(R.color.colorAccent)
                .ticker("ticker")
                .when(System.currentTimeMillis())
                .vibrate(new long[]{DEFAULT_VIBRATE})
                .lights(0xff00ff00, 300, 1000)
                .sound(path)
                .autoCancel(true)
                .simple()
                .build();










       /* Notification notification = new NotificationCompat.Builder(mContext)
                .setTicker("نوتیف")
                .setContentTitle(quote.getName())
                .setContentText("این یک تست است")
                .setSmallIcon(R.drawable.information)
                .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText(quote.getMore()))
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.arrow_right))
                // .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.image_1)))
                .setAutoCancel(true)
                .setSound(path)
                .addAction(R.drawable.arrow_right, "Open Activity", pi)
                .addAction(R.drawable.arrow_right, "Open Site", pi1)
                .build();
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);*/


    }
}
