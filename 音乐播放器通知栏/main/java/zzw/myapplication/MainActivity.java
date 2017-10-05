package zzw.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1; // 如果id设置为0,会导致不能设置为前台service
    public static NotificationManager manager;
    RemoteViews remoteViews;
    Notification notification;
    private Button btn;
    private TextView tv;
    static MainActivity appCompatActivity;
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(appCompatActivity == null){
            appCompatActivity = this;
        }

        btn = (Button)findViewById(R.id.btn);
        tv = (TextView)findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotifcation();
            }
        });

    }

    public void createNotifcation(){
        notification = new Notification();
        notification.icon =R.drawable.musicfile;
        notification.tickerText = "title";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        remoteViews = new RemoteViews(getPackageName(),
                R.layout.widget);
        remoteViews.setTextViewText(R.id.wt_title, "title");
        remoteViews.setImageViewResource(R.id.icon1, R.drawable.musicfile);
        if(state == 0){
            remoteViews.setImageViewResource(R.id.wt_play,R.drawable.pause);
        }else {
            remoteViews.setImageViewResource(R.id.wt_play,R.drawable.play);
        }

        Intent previous=new Intent("com.example.broadcasttest.PREVIOUS");
        PendingIntent pi_previous = PendingIntent.getBroadcast(MainActivity.this,0,
                previous,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wt_previous,pi_previous);


        Intent play=new Intent("com.example.broadcasttest.PLAY");
        PendingIntent pi_play = PendingIntent.getBroadcast(MainActivity.this,0,
                play,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wt_play,pi_play);

        Intent next=new Intent("com.example.broadcasttest.NEXT");
        PendingIntent pi_next = PendingIntent.getBroadcast(MainActivity.this,0,
                next,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wt_next,pi_next);

        Intent clear=new Intent("com.example.broadcasttest.CLEAR");
        PendingIntent pi_clear = PendingIntent.getBroadcast(MainActivity.this,0,
                clear,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.wt_clear,pi_clear);

        notification.contentView = remoteViews;
        manager = (NotificationManager) getSystemService(
                Context.NOTIFICATION_SERVICE);

        manager.notify(NOTIFICATION_ID,notification);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(manager != null ){
            manager.cancel(NOTIFICATION_ID);
        }
    }

    public static MainActivity getInstance(){

        return appCompatActivity;
    }

    public void nextMusic(){
        tv.setText("下一首");
    }

    public void playMusic(){

        if(state == 0){
            tv.setText("播放");
            state =1;
        }else {
            tv.setText("暂停");
            state = 0;
        }

        createNotifcation();


    }
    public void previousMusic(){
        tv.setText("上一首");
    }

    public void clearMusic(){
        finish();
    }
}