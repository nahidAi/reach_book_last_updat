package money.system.book.maker.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import money.system.book.maker.DatabaseHelper;
import money.system.book.maker.Models.Quote2;
import money.system.book.maker.NotificationHelper;
import money.system.book.maker.R;

public class Main3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Quote2 quote2;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        quote2 = databaseHelper.selectNotifById(NotificationHelper.mId);
        String notifName = quote2.getName();
        String notifContent = quote2.getContent();
        shareIntent.putExtra(Intent.EXTRA_TEXT, notifName + "  : " + notifContent);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(shareIntent);
        // با این دستور موقع زدم بک دیگه این اکتیویتی نشون داده نمیشه و بسته میشه
        finish();

    }


}
