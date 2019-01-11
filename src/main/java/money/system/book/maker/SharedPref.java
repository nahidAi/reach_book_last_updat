package money.system.book.maker;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedPreferences;

    public SharedPref(Context context){
       sharedPreferences= context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }
    // این متد برای ذخیره کردن حالت شب و روز
    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();

    }
    //این متد برای لود کردن حالت شب است
    public Boolean loadNightMdeState(){
        Boolean state = sharedPreferences.getBoolean("NightMode",false);
        return  state;

    }

}
