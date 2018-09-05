package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Security;

import org.xbill.DNS.*;

/**
 * Created by Qingweid on 2016/4/10.
 */
public class Ym2IPTestActivity extends Activity {

    EditText yuming;
    String IPAddress = "";
    InetAddress ReturnStr1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ym_test_layout);

        yuming = (EditText) findViewById(R.id.text_ym);

        Security.setProperty("networkaddress.cache.ttl", String.valueOf(-1));
        Security.setProperty("networkaddress.cache.negative.ttl", String.valueOf(0));
        System.out.println("[Mydebug] " + " networkaddress.cache.ttl:" + Security.getProperty("networkaddress.cache.ttl") +
                " networkaddress.cache.negative.ttl:" + Security.getProperty("networkaddress.cache.negative.ttl"));
//        Field f= InetAddressCachePolicy.getDeclaredField("cachePolicy");
//        f.setAccessible(true);
//        f.setInt(0);
//        System.out.println(InetAddressCachePolicy.get());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onDoDNS(View view){
        String strym = yuming.getText().toString();

//        GetInetAddress(strym);
//        doDNS();
        doDNSBFromGoogle();
//        Google Public DNS （8.8.8.8， 8.8.4.4）      mean(2.3 + 4.3 + 0.2 + 2.4 + 1.3 + 5.6 + 0.2 + 0.3 + 3.4 + 2.2) = 2.22
//        OpenDNS （208.67.222.222， 208.67.220.220）  mean(0.4 + 4.2 + 1.2 + 4.3 + 0.3 + 1.5 + 0.2 + 0.3 + 2.7 + 0.2) = 1.53
//        OpenDNS Family （208.67.222.123， 208.67.220.123）
//        V2EX DNS （199.91.73.222， 178.79.131.110）
//        Dyn DNS （216.146.35.35， 216.146.36.36）
//        Comodo Secure (8.26.56.26， 8.20.247.20)
//        UltraDNS （156.154.70.1， 156.154.71.1）
//        Norton ConnectSafe (199.85.126.10, 199.85.127.10)
//        String server = null;
//        local
//        doDNSBFromServer(server);
    }

    public void GetInetAddress(String  host){
        final String strHost = host;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ReturnStr1 = java.net.InetAddress.getByName(strHost);
                    IPAddress = ReturnStr1.getHostAddress();
                    System.out.println(IPAddress);
                    System.out.println("[Mydebug] " + " ip:" + IPAddress);
                }catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void doDNS() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
//                    Lookup l = new Lookup("test.server.c4mi.com", Type.TXT, DClass.CH);
                    Lookup l = new Lookup("test.server.c4mi.com") ;
                    l.setResolver(new SimpleResolver());
                    l.run();
                    if (l.getResult() == Lookup.SUCCESSFUL)
                        System.out.println("[Mydebug]" + " ip:" +  l.getAnswers()[0].rdataToString());
                    else {
                        System.out.println("[Mydebug] " + " result:" + l.getResult() + " error:" + l.getErrorString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void doDNSBFromGoogle() {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
//                    Lookup l = new Lookup("test.server.c4mi.com", Type.TXT, DClass.CH);
                    String ip = null;
                    Lookup l = new Lookup("oplab09.mivatek.com.tw") ; //"test.server.c4mi.com"
                    SimpleResolver resolver = new SimpleResolver("8.8.8.8");
                    resolver.setTimeout(1);
                    l.setResolver(resolver);
                    l.setCache(null);
                    long timeStartms = System.currentTimeMillis();
                    for (int i = 0 ; (i < 100) & (ip == null) ; i ++ ) {
                        l.run();
                        long timeEndms = System.currentTimeMillis();
                        if (l.getResult() == Lookup.SUCCESSFUL) {
                            ip = l.getAnswers()[0].rdataToString();
                            System.out.println("[Mydebug]" + " index:" + i + " google return ip:" + l.getAnswers()[0].rdataToString() + " time:" + (timeEndms - timeStartms));
                        } else {
                            System.out.println("[Mydebug] " + " index:" + i +  " google result:" + l.getResult() + " error:" + l.getErrorString()
                                    + " time out:" + (timeEndms - timeStartms));
                            if (l.getResult() == Lookup.HOST_NOT_FOUND) {
                                l.setDefaultCache(null,1);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void doDNSBFromServer(String s) {
        final String server = s;


        new Thread(new Runnable() {

            @Override
            public void run() {
                SimpleResolver resolver;
                try {
//                    Lookup l = new Lookup("test.server.c4mi.com", Type.TXT, DClass.CH);
                    String ip = null;
                    Lookup l = new Lookup("test.server.c4mi.com") ;
                    if (server != null) {
                        resolver = new SimpleResolver(server);
                    } else {
                        resolver = new SimpleResolver();
                    }
                    resolver.setTimeout(1);
                    l.setResolver(resolver);
                    long timeStartms = System.currentTimeMillis();
                    for (int i = 0 ; (i < 100) & (ip == null) ; i ++ ) {
                        l.run();
                        long timeEndms = System.currentTimeMillis();
                        if (l.getResult() == Lookup.SUCCESSFUL) {
                            ip = l.getAnswers()[0].rdataToString();
                            System.out.println("[Mydebug]" + " index:" + i + " server:" + ((server==null)?"local":server) +" return ip:" + l.getAnswers()[0].rdataToString() + " time:" + (timeEndms - timeStartms));
                        } else {
                            System.out.println("[Mydebug] " + " index:" + i + " server:" + ((server==null)?"local":server) +  " result:" + l.getResult() + " error:" + l.getErrorString()
                                    + " time out:" + (timeEndms - timeStartms));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
