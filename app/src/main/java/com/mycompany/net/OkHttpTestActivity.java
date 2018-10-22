package com.mycompany.net;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mycompany.mymaintestactivity.R;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_layout);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });


        Button btn2 = (Button) findViewById(R.id.btn_pause);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPause();
            }
        });

        Button btn3 = (Button) findViewById(R.id.btn_stop);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStop();
            }
        });

        Button btn4 = (Button) findViewById(R.id.btn_destroy);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDestroy();
            }
        });

        Button btn5 = (Button) findViewById(R.id.btn_finish);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        getDataSync();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Mydebug", " OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Mydebug", " OnStop()");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Mydebug", "onDestroy");
    }


    public void getDataSync() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(5, TimeUnit.SECONDS);
                    builder.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts());
                    builder.hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    });

                    OkHttpClient client = builder.build();

                    //OkHttpClient client = new OkHttpClient();  //创建OkHttpClient对象

                    Request request = new Request.Builder()
                            .get()
                            .url("https://www.baidu.com")//请求接口。如果需要传参拼接到接口后面。

                            .build();//创建Request 对象
                    Response response = null;
                    client.newCall(request).enqueue(new Callback() {

                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                        }
                    });

                    response = client.newCall(request).execute();//得到Response 对象
                    if (response.isSuccessful()) {
                        Log.d("Mydebug","response.code()=="+response.code());
                        Log.d("Mydebug","response.message()=="+response.message());
                        Log.d("Mydebug","res=="+response.body().string());
                        //此时的代码执行在子线程，修改UI的操作请使用handler跳转到UI线程。
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {return new X509Certificate[0];}
    }

}

