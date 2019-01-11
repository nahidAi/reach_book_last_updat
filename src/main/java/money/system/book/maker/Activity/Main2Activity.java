package money.system.book.maker.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import money.system.book.maker.DatabaseHelper;
import money.system.book.maker.Quote;
import money.system.book.maker.R;
import money.system.book.maker.SettingActivity;
import money.system.book.maker.SharedPref;
import money.system.book.maker.ToggleButton;

public class Main2Activity extends AppCompatActivity {
    private int id;
    private String name;
    private int fav;
    private String body;
    private int free;
    private String image;
    private int layoutId;
    private String pageName;
    private Quote mQuote;
    SharedPref sharedPref;


    //  private TextView txtContent;
    private TextView txtMore;
    private ImageView imgAvatar;
    // private ImageView imgCopy;
    //  private ImageView imgShare;
    private FloatingActionButton floatingActionButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightMdeState() == true) {
            setTheme(R.style.darkTheme);

        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // ////////////////////////////////////////////////////////////////////روشن ماندن این صفحه در برنامه
        if (SettingActivity.loadKeepOn(Main2Activity.this)) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        ///////////////////////////////////////////////////////////-------------------------------










        final DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        layoutId = Integer.parseInt(bundle.getString("id"));
        pageName = bundle.getString("name");
        if (bundle != null) {
            if (pageName.equals("BigPerson")) {
                mQuote = databaseHelper.selectPersonById(layoutId);
                id = mQuote.getId();
                name = mQuote.getName();
                body = mQuote.getBody();
                fav = mQuote.getFav();
                free = mQuote.getFree();
                image = mQuote.getImage();


            } else if (pageName.equals("favorite")) {
                mQuote = databaseHelper.selectPersonById(layoutId);
                id = mQuote.getId();
                name = mQuote.getName();
                body = mQuote.getBody();
                fav = mQuote.getFav();
                image = mQuote.getImage();
                free = mQuote.getFree();

            }

        }

        //txtContent = (TextView) findViewById(R.id.txtContent);
        txtMore = findViewById(R.id.txtMore);
        imgAvatar = (ImageView) findViewById(R.id.avatar);
        //imgShare = (ImageView) findViewById(R.id.imgShare);
       /* imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, name);
                startActivity(Intent.createChooser(shareIntent, "اشتراک"));

            }
        });*/
        // imgCopy = (ImageView) findViewById(R.id.imgCopy);
       /* imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) Main2Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    final android.content.ClipData clipData = android.content.ClipData.newPlainText(body,body);
                    clipboardManager.setPrimaryClip(clipData);


                } else {
                    final android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) Main2Activity.this.getSystemService(Context.CLIPBOARD_SERVICE);
                    clipboardManager.setText(body);
                }
                Snackbar.make(v, " متن کپی شد", Snackbar.LENGTH_LONG).show();
            }
        });*/
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collAps);
        collapsingToolbarLayout.setTitle(name);

        collapsingToolbarLayout.setExpandedTitleMarginBottom(30);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        //txtContent.setText(Html.fromHtml(body));
        SharedPreferences preferences = getSharedPreferences("size", MODE_PRIVATE);
        int value = preferences.getInt("size", 16);
        txtMore.setTextSize(value);
        txtMore.setText(Html.fromHtml(body));
        txtMore.setTypeface(SettingActivity.typeface);




        int imgId = getResources().getIdentifier(image, "drawable", getPackageName());
        imgAvatar.setImageResource(imgId);

        if (databaseHelper.selectFavoriteState(id)) {
            floatingActionButton.setImageResource(R.drawable.heart);
        } else {
            floatingActionButton.setImageResource(R.drawable.heart_outline);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.selectFavoriteState(id)) {
                    floatingActionButton.setImageResource(R.drawable.heart_outline);
                    databaseHelper.updateUnFavorite(id);
                } else {
                    floatingActionButton.setImageResource(R.drawable.heart);
                    databaseHelper.updateFavorite(id);
                }
            }
        });

    }

}
