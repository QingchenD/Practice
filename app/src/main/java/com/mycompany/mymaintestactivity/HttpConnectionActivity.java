package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Qingweid on 2016/5/9.
 */
public class HttpConnectionActivity extends Activity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();

    public static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private TextView responseText;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    //UI显示
                    responseText.setText(response);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_connection_test_layout);
        sendRequest = (Button) findViewById(R.id.btn_send_request);
        responseText = (TextView) findViewById(R.id.tv_response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_request:
//                sendRequestWithHttpURLConnection();
//                sendRequestWithHttpClient();
                break;

            default:
                break;
        }
    }


    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try{
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    //将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

//    private void sendRequestWithHttpClient() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpGet httpGet = new HttpGet("http://192.168.100.253/get_data.json"); //("http://192.168.100.253/get_data.xml"); //("http://www.baidu.com");
//                    HttpResponse httpResponse = httpClient.execute(httpGet);
//                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                        //请求响应成功
//                        HttpEntity entity = httpResponse.getEntity();
//                        String response = EntityUtils.toString(entity, "utf-8");
////                        parseXMLWithPull(response);
////                        parseXMLWithSAX(response);
////                        parseJSONWitheJSONObject(response);
//                        parseJSONWithGSON(response);
//                        Message message = new Message();
//                        message.what = SHOW_RESPONSE;
//                        //将服务器返回的结果存放到Message中
//                        message.obj = response.toString();
//                        handler.sendMessage(message);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    private void parseXMLWithPull (String xmlDate) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlDate));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;

                    //完成解析某个节点
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d("HttpConnectionActivity", " id is " + id + " name is " + name + " version is " + version);
                        }
                        break;

                    default:
                        break;
                }

                eventType = xmlPullParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void parseXMLWithSAX (String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            MyContentHandler handler = new MyContentHandler();
            //将MyContentHandler的实例设置到XMLReader
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void parseJSONWitheJSONObject(String jsonData) {
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0 ; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                System.out.println(TAG + " id is " + id + " name is " + name + " version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for (App app : appList) {
            System.out.println(TAG + " id is " + app.getId() + " name is " + app.getName() + " version is " + app.getVersion() );
        }
    }
}
