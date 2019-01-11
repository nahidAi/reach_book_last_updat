package money.system.book.maker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "bookdb.sql";
    private static String DB_PATH = "";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;
    private String favoriteState;


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 17)
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        else
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        this.mContext = context;

        copyDataBase();

        this.getReadableDatabase();
        openDataBase();

    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();

    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        // InputStream mInput = mContext.getResources().openRawResource(R.raw.info);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

    public ArrayList<Quote> selectPerson() {
        ArrayList<Quote> person = new ArrayList<Quote>();
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM tbl ", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            int free = cursor.getInt(cursor.getColumnIndex("free"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));

            Quote quote = new Quote(name, body, fav, image, free, id);
            quote.setName(name);
            quote.setBody(body);
            quote.setFav(fav);
            quote.setImage(image);
            quote.setId(id);
            person.add(quote);

        }
        return person;
    }

    public ArrayList<Quote> selectFavorite() {
        ArrayList<Quote> favorite = new ArrayList<Quote>();
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM tbl WHERE fav = 1", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            int free = cursor.getInt(cursor.getColumnIndex("free"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));

            Quote quote = new Quote(name, body, fav, image, free, id);
            quote.setName(name);
            quote.setBody(body);
            quote.setFav(fav);
            quote.setImage(image);
            quote.setId(id);
            favorite.add(quote);

        }
        return favorite;
    }

    public Quote selectPersonById(int mId) {
        ArrayList<Quote> person = new ArrayList<>();
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM tbl WHERE id = '" + mId + "'", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            int free = cursor.getInt(cursor.getColumnIndex("free"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));

            Quote quote = new Quote(name, body, fav, image, free, id);
            quote.setName(name);
            quote.setBody(body);
            quote.setFav(fav);
            quote.setImage(image);
            quote.setId(id);
            person.add(quote);


        }
        return person.get(0);
    }

    public boolean selectFavoriteState(int mId) {
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM tbl WHERE id = " + mId, null);
        while (cursor.moveToNext()) {
            favoriteState = cursor.getString(cursor.getColumnIndex("fav"));

        }
        if (favoriteState.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    public void updateFavorite(int mId) {
        mDataBase.execSQL("UPDATE tbl SET fav = 1 WHERE id = " + mId);

    }

    public void updateUnFavorite(int mId) {
        mDataBase.execSQL("UPDATE tbl SET fav=0 WHERE id = " + mId);
    }

    public ArrayList<Quote> readQuoteFromDatabase() {
        ArrayList<Quote> quoteArrayList = new ArrayList<Quote>();
        Cursor cursor = mDataBase.rawQuery("SELECT * FROM tbl_b_n ", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String body = cursor.getString(cursor.getColumnIndex("body"));
            int fav = cursor.getInt(cursor.getColumnIndex("fav"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            int free = cursor.getInt(cursor.getColumnIndex("free"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));

            Quote quote = new Quote(name, body, fav, image, free, id);
            quote.setName(name);
            quote.setBody(body);
            quote.setFav(fav);
            quote.setImage(image);
            quote.setId(id);
            quoteArrayList.add(quote);

        }
        // Toast.makeText(context, "" + quoteArrayList.size(), Toast.LENGTH_SHORT).show();
        return quoteArrayList;
    }




}