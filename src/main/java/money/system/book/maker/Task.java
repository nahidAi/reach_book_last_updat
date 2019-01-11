package money.system.book.maker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Task extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
       // Toast.makeText(context, "اجرا در زمان مشخص شده", Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper = new NotificationHelper(context);
      //  notificationHelper.ListQuoteAndSendNotification();
    }
}
