package com.mycompany.eventbus;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/9/10.
 */

public class EventBusTestActivity extends Activity {

    @Bind(R.id.tv)
    TextView mText;

    @OnClick(R.id.btn_send)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                EventBus.getDefault().post(new MessageEvent("欢迎大家浏览我写的博客|||||"));
                break;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_bus);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

//        Button mBtn  = (Button) findViewById(R.id.btn_send);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        EventBus.getDefault().post(new MessageEvent("欢迎大家浏览我写的博客"));
//                                    }
//                                }
//        );


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        mText.setText(messageEvent.getMessage());
    }

}
