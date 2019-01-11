package money.system.book.maker;


import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

public class G extends Application{
    public static AlarmManager alarmManager;
    @Override
    public void onCreate() {
        super.onCreate();
        alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);


    }

}
