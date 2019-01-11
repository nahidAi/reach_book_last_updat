package money.system.book.maker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import money.system.book.maker.Activity.Main2Activity;


public class SettingActivity extends AppCompatActivity {
    private ToggleButton nightMode;
    private boolean size2 = false;
    public static Typeface typeface;
    SharedPref sharedPref;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView menuToolbar;
    private DiscreteSeekBar seekBar;
    int size = 0;
    TextView sample;
    TextView size4;
    public  LinearLayout keepon;
    public static ToggleButton screenon;
    private AppCompatSpinner spinnerFont;
    TextView keepOnText, txtnightModeText, s1, current_font, fontSizeText, currentSize, s6;
    public static int Defualt_Font = 1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMdeState() == true) {
            setTheme(R.style.darkTheme);

        } else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_setting);


        setupToolbarMenu();
        seekBarSetting();
        setDeveloperEmail();

        spinnerFont = (AppCompatSpinner) findViewById(R.id.spinner_font);
        current_font = findViewById(R.id.current_font);
        final String[] fonts = {"sans", "arabic", "koodak", "sans_bold", "yekan", "naskh"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fonts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFont.setAdapter(adapter);
        size = loadFontType(SettingActivity.this);
        spinnerFont.setSelection(size);
        spinnerFont.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveFontType(position, SettingActivity.this);
                switch (position) {
                    case 0:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "سانس");
                        break;
                    case 1:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "عربیک");
                        break;
                    case 2:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "کودک");
                        break;
                    case 3:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "سانس");
                        break;
                    case 4:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "یکان");
                        break;
                    case 5:
                        sample.setTypeface(typeface);
                        current_font.setText("فونت فعلی: " + "نسخ");
                        break;

                }


                // txtCurrentFont.setText("فونت فعلی: " + fonts[position]);**************************************
                //مشاهده فونت تغییر یافته
                typeface = Typeface.createFromAsset(getAssets(), "fonts/" + fonts[position] + ".ttf");

                keepOnText = findViewById(R.id.keepontext);
                txtnightModeText = findViewById(R.id.txtNightMode);
                s1 = findViewById(R.id.s1);
                fontSizeText = findViewById(R.id.fontSizeText);
                currentSize = findViewById(R.id.currentSize);
                s6 = findViewById(R.id.s6);


                //////

                keepOnText.setTypeface(typeface);
                txtnightModeText.setTypeface(typeface);
               // s1.setTypeface(typeface);
                currentSize.setTypeface(typeface);
                current_font.setTypeface(typeface);

                fontSizeText.setTypeface(typeface);
                s6.setTypeface(typeface);
                // current_font.setText("سایز فعلی: " + size);
                switch (size) {
                    case 0:
                        sample.setTypeface(typeface);
                        //current_font.setText("فونت فعلی: " + "سانس");
                        break;
                    case 1:
                        sample.setTypeface(typeface);
                      //  current_font.setText("فونت فعلی: " + "عربیک");
                        break;
                    case 2:
                        sample.setTypeface(typeface);
                       // current_font.setText("فونت فعلی: " + "کودک");
                        break;
                    case 3:
                        sample.setTypeface(typeface);
                       // current_font.setText("فونت فعلی: " + "بولد سانس");
                        break;
                    case 4:
                        sample.setTypeface(typeface);
                       // current_font.setText("فونت فعلی: " + "یکان");
                        break;
                    case 5:
                        sample.setTypeface(typeface);
                       // current_font.setText("فونت فعلی: " + "نسخ");
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //  night mode ////////////////////////////////////////////////////////////////////
        nightMode = (ToggleButton) findViewById(R.id.switch_night_mode);
        if (sharedPref.loadNightMdeState() == true) {
            nightMode.setToggleOn();
        }
        nightMode.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    sharedPref.setNightModeState(true);
                    restartApp();
                    Toast.makeText(SettingActivity.this, " متن اصلی به حالت مطالعه در شب تغییر کرد", Toast.LENGTH_SHORT).show();
                } else {
                    sharedPref.setNightModeState(false);
                    restartApp();

                }
            }
        });

        screenon = findViewById(R.id.screenon);
        ///////////////////////////////////////////////////////////////////////////////////
        keepon = (LinearLayout) findViewById(R.id.keepon);
        if (loadKeepOn(SettingActivity.this)) {
            screenon.setToggleOn();
        } else {
            screenon.setToggleOff();
        }
        keepon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadKeepOn(SettingActivity.this)) {
                    screenon.setToggleOff();

                    saveKeepOn(false, SettingActivity.this);
                } else {
                    screenon.setToggleOn();
                    saveKeepOn(true, SettingActivity.this);


                }
            }
        });

        screenon.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (!on) {
                    saveKeepOn(false, SettingActivity.this);
                } else {
                    saveKeepOn(true, SettingActivity.this);


                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();

        }
    }

    public void setupToolbarMenu() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        menuToolbar = (ImageView) findViewById(R.id.opener);
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
                    finish();
                    Intent intent = new Intent(SettingActivity.this, SettingActivity.class);
                    startActivity(intent);

                } else if (id == R.id.about) {
                    Toast.makeText(SettingActivity.this, " کليک شد", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.source) {
                    drawerLayout.closeDrawers();
                    final NiftyDialogBuilder dialogBuilder;
                    dialogBuilder = NiftyDialogBuilder.getInstance(SettingActivity.this);
                    dialogBuilder
                            .withTitle("منابع")
                            .withMessage("کتاب افسانه کارآفريني مايکل گربر- کتاب چهارراه پولسازي - کتاب پدر پولدار پدر بي پول رابرت کيوساکي - www.milyoner.blogfa.com - www.colab.ir - www.12ceo.ir-www.google.com")
                            .withTitleColor("#FFFFFF")
                            .withDividerColor("#FFFFFF")
                            .withDialogColor("#BDBDBD")
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
                    Toast.makeText(SettingActivity.this, " کليک شد", Toast.LENGTH_SHORT).show();


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

    @SuppressLint("CutPasteId")
    public void seekBarSetting() {
        seekBar = (DiscreteSeekBar) findViewById(R.id.setting_custom_seekBar);
        size4 = (TextView) findViewById(R.id.currentSize);
        sample = (TextView) findViewById(R.id.sample);
        DiscreteSeekBar seek = (DiscreteSeekBar) findViewById(R.id.setting_custom_seekBar);

        seekBar.setMin(16);
        seekBar.setMax(25);
        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                size = value;
                saveFontSize(size, SettingActivity.this);
                sample.setTextSize(size);
                size4.setText("سایز فعلی: " + size);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });
        size = loadFontSize(SettingActivity.this);
        sample.setTextSize(size);
        size4.setText("سایز فعلی: " + size);
        seek.setProgress(size);


    }

    public void setDeveloperEmail() {
        LinearLayout mail = (LinearLayout) findViewById(R.id.email);
        final String developerMail = "na.mehr20@gmail.com";
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{developerMail});
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                try {
                    startActivity(Intent.createChooser(i, "Send Email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SettingActivity.this, "برنامه جیمیل را نصب ندارید؟!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void saveFontSize(int value, Context c) {
        SharedPreferences sp = c.getSharedPreferences("size", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("size", value);
        editor.commit();
    }

    public static int loadFontSize(Context c) {
        SharedPreferences sp = c.getSharedPreferences("size", Activity.MODE_PRIVATE);
        return sp.getInt("size", 18);

    }

    public static void saveKeepOn(boolean value, Context c) {
        SharedPreferences sp = c.getSharedPreferences("keep", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("keep", value);
        editor.commit();
        // Log.e("s","screen on="+value);
    }

    public static boolean loadKeepOn(Context c) {
        SharedPreferences sp = c.getSharedPreferences("keep", Activity.MODE_PRIVATE);
        return sp.getBoolean("keep", true);
    }

    public void restartApp() {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
        finish();
    }

    public static void saveFontType(int value, Context c) {
        SharedPreferences sp = c.getSharedPreferences("font", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("font", value);
        editor.commit();
    }

    public static int loadFontType(Context c) {
        SharedPreferences sp = c.getSharedPreferences("font", Activity.MODE_PRIVATE);
        int check = sp.getInt("font", 100);
        if (check == 100) {
            Log.e("s", "test");
            switch (Defualt_Font) {
                case 1:
                    return sp.getInt("font", 0);
                case 2:
                    return sp.getInt("font", 1);
                case 3:
                    return sp.getInt("font", 2);
                case 4:
                    return sp.getInt("font", 3);
                case 5:
                    return sp.getInt("font", 4);
                case 6:
                    return sp.getInt("font", 5);
                default:
                    return sp.getInt("font", 0);

            }
        } else {
            return sp.getInt("font", 0);
        }
    }

}
