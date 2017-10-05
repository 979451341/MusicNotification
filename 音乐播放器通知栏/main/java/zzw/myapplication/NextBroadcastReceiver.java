package zzw.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Administrator on 2017/8/29.
 */

public class NextBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        final MainActivity musicActivity = MainActivity.getInstance();
        musicActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                musicActivity.nextMusic();
            }
        });

    }
}
