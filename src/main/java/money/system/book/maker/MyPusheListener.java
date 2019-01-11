package money.system.book.maker;


import android.util.Log;

import org.json.JSONObject;

import co.ronash.pushe.PusheListenerService;

public class MyPusheListener extends PusheListenerService{
    @Override
    public void onMessageReceived(JSONObject json, JSONObject messageContent) {
        super.onMessageReceived(json, messageContent);
        Log.i("Output :",json.toString());

    }
}
