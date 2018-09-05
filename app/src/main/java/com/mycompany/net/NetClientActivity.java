package com.mycompany.net;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

/**
 * Created by Qingweid on 2016/10/19.
 */

public class NetClientActivity extends Activity implements View.OnClickListener{

    TcpClient tcpClient;
    UDPClient udpClient;

    private TextView tvSend;
    private Button btnNetSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_client);

        tvSend = (TextView) findViewById(R.id.tv_send);
        btnNetSend = (Button) findViewById(R.id.btn_send);
        btnNetSend.setOnClickListener(this);

//        tcpClient = new TcpClient();

        udpClient = new UDPClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        try {
//            tcpClient.sendMsg("nihao");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String msg = "This is a test!!!";
                tvSend.setText("Start send:[" + msg + "]");
                sendMessage(msg);
                tvSend.setText("End send:[" + msg + "]" + " Success!");
                break;
        }
    }


    public void sendMessage(final String msg) {

        new Thread(){
            @Override
            public void run() {
                try {
                    udpClient.sendMessage(6166, "192.168.43.255",msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
