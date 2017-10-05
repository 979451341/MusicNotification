package zzw.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2016/11/23.
 */
public class PreviousBroadcastReceiver extends BroadcastReceiver {
         @Override
        public void onReceive(Context context,Intent intent){
             final MainActivity musicActivity = MainActivity.getInstance();
             musicActivity.runOnUiThread(new Runnable() {

                 @Override
                 public void run() {
                     musicActivity.previousMusic();
                 }
             });
         }
}
