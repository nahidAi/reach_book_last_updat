package money.system.book.maker.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Calendar;

import co.ronash.pushe.Pushe;
import money.system.book.maker.Adapter.AdapterFragment;
import money.system.book.maker.DatabaseHelper;
import money.system.book.maker.G;
import money.system.book.maker.NotificationHelper;
import money.system.book.maker.R;
import money.system.book.maker.SettingActivity;
import money.system.book.maker.Task;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuToolbar;
    DatabaseHelper databaseHelper;
    private FloatingActionButton floatingActionButton;
    private Button showNotification;
    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    NotificationHelper notificationHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        Pushe.initialize(this,true);


        Intent intent = new Intent(this, Task.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2018);
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,11);
        calendar.set(Calendar.HOUR,1);
        calendar.set(Calendar.MINUTE,32);
        calendar.set(Calendar.SECOND,00);

       // G.alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1*60*1000,pendingIntent);




        databaseHelper = new DatabaseHelper(MainActivity.this);
        setTabOption();
        setNavigationViewAndFloating();
        notificationHelper = new NotificationHelper(this);
       // notificationHelper.ListQuoteAndSendNotification();



    }

    private void setTabOption() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new AdapterFragment(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);

        TabLayout tabStrip = (TabLayout) findViewById(R.id.tabLayout);
        tabStrip.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();

        }

    }

    public void setNavigationViewAndFloating() {
       // showNotification = (Button) findViewById(R.id.showNotification);
       /* showNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                Intent intent1 = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://nikandroid.com/"));

                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                PendingIntent pi1 = PendingIntent.getActivity(MainActivity.this, 0, intent1, 0);


                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setTicker("نوتیف")
                        .setContentTitle("تایتل")
                        //.setContentText("این یک تست است")
                        .setSmallIcon(R.drawable.information)
                        .setStyle(new android.support.v4.app.NotificationCompat.BigTextStyle().bigText("لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است. چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد. "))
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow_right))
                        // .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.image_1)))
                        .setAutoCancel(true)
                        .setSound(path)
                        .addAction(R.drawable.arrow_right, "Open Activity", pi)
                        .addAction(R.drawable.arrow_right, "Open Site", pi1)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, notification);


            }
        });*/




        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(floatingActionButton, "کلیک شد", Snackbar.LENGTH_LONG).show();
            }
        });


        menuToolbar = (ImageView) findViewById(R.id.menu_toolbar);
        menuToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.setting) {
                    drawerLayout.closeDrawers();
                   Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                   startActivity(intent);

                }else if (id == R.id.about){
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();

                }else  if (id == R.id.source){
                    drawerLayout.closeDrawers();
                    final NiftyDialogBuilder dialogBuilder;
                    dialogBuilder = NiftyDialogBuilder.getInstance(MainActivity.this);
                    dialogBuilder
                            .withTitle("منابع")
                            .withMessage("کتاب افسانه کارآفرینی مایکل گربر- کتاب چهارراه پولسازی - کتاب پدر پولدار پدر بی پول رابرت کیوساکی - www.milyoner.blogfa.com - www.colab.ir - www.12ceo.ir-www.google.com")
                            .withTitleColor("#ffffff")
                            .withDividerColor("#FFFFFF")
                            .withMessageColor("#424242")
                            .withDialogColor("#F5F5F5")
                            .withEffect(Effectstype.Fliph)
                            .withDuration(500)
                            .withButton1Text("خب")
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialogBuilder.cancel();
                                }
                            })
                            .show();


                }else  if (id == R.id.comment){
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();


                }else if (id == R.id.exit){
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }

                return true;
            }
        });

    }


}


