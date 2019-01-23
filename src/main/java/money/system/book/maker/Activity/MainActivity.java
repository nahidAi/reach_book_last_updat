package money.system.book.maker.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.Calendar;

import co.ronash.pushe.Pushe;
import money.system.book.maker.Adapter.AdapterFragment;
import money.system.book.maker.DatabaseHelper;
import money.system.book.maker.G;
import money.system.book.maker.R;
import money.system.book.maker.Task;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuToolbar;
    DatabaseHelper databaseHelper;
    private FloatingActionButton floatingActionButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        Pushe.initialize(this, true);


        //String am_pm = "";
        Calendar calendar = Calendar.getInstance();
       /* calendar.set(Calendar.YEAR,2019);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,21);*/
        calendar.set(Calendar.HOUR_OF_DAY,6);
        calendar.set(Calendar.MINUTE,33);
        calendar.set(Calendar.SECOND, 0);

       /* if (calendar.get(Calendar.AM_PM) == Calendar.AM)
            am_pm = "AM";
        else if (calendar.get(Calendar.AM_PM) == Calendar.PM)
            am_pm = "PM";*/
//      با این شرط قبل از ارسال نوتیف چک میکنه بعد نوتیفیکیشن میفرسته
        Calendar cur = Calendar.getInstance();
        if (cur.after(calendar )) {
            calendar.add(Calendar.DATE, 1);
        }
        Intent intent = new Intent(this, Task.class);
        int ALARM1_ID = 10000;
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, ALARM1_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      //  G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
       G.alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),1440*60*1000,pendingIntent);


        databaseHelper = new DatabaseHelper(MainActivity.this);


        setTabOption();
        setNavigationViewAndFloating();


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

                } else if (id == R.id.about) {
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.source) {
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


                } else if (id == R.id.comment) {
                    Toast.makeText(MainActivity.this, " کلیک شد", Toast.LENGTH_SHORT).show();


                } else if (id == R.id.exit) {
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


