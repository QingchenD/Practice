package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/4/25.
 */
public class NotificationActivity extends Activity implements View.OnClickListener{

    private Button btnSendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_test_layout);
        btnSendNotice = (Button) findViewById(R.id.btn_send_notice);
        btnSendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_notice:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification(R.drawable.christmas_crown ,"This is ticker text" , System.currentTimeMillis());
                notification.defaults = Notification.DEFAULT_ALL;

                //添加点击功能
                Intent intent = new Intent(this,SecondActivity.class);
                PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                notification.setLatestEventInfo(this,"This is content title", "This is content text",pi);
                manager.notify(1, notification);
                break;

            default:
                break;
        }
    }
}
