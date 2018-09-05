package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.achartengine.model.Point;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Qingweid on 2016/5/11.
 */
public class LocationTestActivity extends Activity {
    private TextView positionTextView;
    private LocationManager locationManager;
    private String provider;

    private final String TAG = "LocationTestActivity";

    public static final int SHOW_LOCATION = 0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.location_test_layout);
//        positionTextView = (TextView) findViewById(R.id.position_text_view);
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //获取所有可用的位置提供器
//        List<String> providerList = locationManager.getProviders(true);
//        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
//            provider = LocationManager.GPS_PROVIDER;
//        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
//            provider = LocationManager.NETWORK_PROVIDER;
//        } else {
//            //当没有可用的位置提供器时，弹出Toast提示用户
//            Toast.makeText(this, "No location provider to use", Toast.LENGTH_SHORT).show();
//
//            return;
//        }
//
//        Location location = locationManager.getLastKnownLocation(provider);
//        if (location != null) {
//            //显示当前设备的位置信息
//            showLocation(location);
//        }
//
//        locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (locationManager != null) {
//            //关闭程序时将监听器移除
//            locationManager.removeUpdates(locationListener);
//        }
//    }
//
//    LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            //更新当前设备的位置信息
//            showLocation(location);
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String provider) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//
//        }
//    };

//    private void showLocation(final Location location) {
//        String currentPostion = "latitude is " + location.getLatitude() + "\n" +
//                "longitude is " + location.getLongitude();
//        positionTextView.setText(currentPostion);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //组装反向地理编码的借口地址
//                    StringBuilder url = new StringBuilder();
//                    url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
//                    url.append(location.getLatitude()).append(",");
//                    url.append(location.getLongitude());
//                    url.append("&sensor=false");
//                    HttpClient httpClient = new DefaultHttpClient();
//                    HttpGet httpGet = new HttpGet(url.toString());
//                    //在请求消息头中指定语言，保证服务器会返回中文数据
//                    httpGet.addHeader("Accept-Language","zh-CN");
//                    HttpResponse httpResponse = httpClient.execute(httpGet);
//                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                        HttpEntity entity = httpResponse.getEntity();
//                        String response = EntityUtils.toString(entity, "utf-8");
//
//                        JSONObject jsonObject = new JSONObject(response);
//                        //获取results节点下的位置信息
//                        JSONArray resultArray = jsonObject.getJSONArray("results");
//                        if (resultArray.length() > 0) {
//                            JSONObject subObject = resultArray.getJSONObject(0);
//                            //取出格式化的位置信息
//                            String address = subObject.getString("formatted_address");
//                            Message message = new Message();
//                            message.what = SHOW_LOCATION;
//                            message.obj = address;
//                            handler.sendMessage(message);
//                        }
//                    } else {
//                        System.out.println(" http return status" + httpResponse.getStatusLine().getStatusCode());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    private EditText mETLat;
    private EditText mETLon;
    private Button mBTNTransfer;
    private TextView mTransferValue;
    private Button mBTNTransferToGCJ;
    private TextView mTransferValueGCJ;
    private Button mBtnIsInChina;
    private TextView mIsInChina;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_test_layout);

        mETLat = (EditText) findViewById(R.id.latitude_et);
        mETLon = (EditText) findViewById(R.id.longitude_et);
        mBTNTransfer = (Button) findViewById(R.id.btn_transfer);
        mTransferValue = (TextView) findViewById(R.id.transfer_value);
        mBTNTransferToGCJ = (Button) findViewById(R.id.btn_transfer_gcj);
        mTransferValueGCJ = (TextView) findViewById(R.id.transfer_value_gcj);
        mBtnIsInChina = (Button) findViewById(R.id.btn_in_china);
        mIsInChina = (TextView) findViewById(R.id.tst_in_china_value);

        mBTNTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transfer();
            }
        });

        mBTNTransferToGCJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transferGCJ();
            }
        });

        InitPolygon();
        mBtnIsInChina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInChina();
            }
        });
    }

    private void  transfer () {
        String strLat = mETLat.getText().toString();
        String strLon = mETLon.getText().toString();

        double dLat = Double.valueOf(strLat);
        double dLon = Double.valueOf(strLon);

        Gps gps2gcj = gps84_To_Gcj02(dLat, dLon);
        if (gps2gcj == null) {
            Log.i(TAG, " gps84_To_Gcj02 fail !");
            return;
        }
        Gps gcj2bd = gcj02_To_Bd09(gps2gcj.getWgLat(), gps2gcj.getWgLon());

        mTransferValue.setText(gcj2bd.tostring());
    }

    private void transferGCJ() {
        String strLat = mETLat.getText().toString();
        String strLon = mETLon.getText().toString();

        double dLat = Double.valueOf(strLat);
        double dLon = Double.valueOf(strLon);
        Gps gps2gcj = gps84_To_Gcj02(dLat, dLon);
        if (gps2gcj == null) {
            Log.i(TAG, " gps84_To_Gcj02 fail !");
            return;
        }
        mTransferValueGCJ.setText(gps2gcj.tostring());
    }

    private void isInChina() {
        String strLat = mETLat.getText().toString();
        String strLon = mETLon.getText().toString();

        double dLat = Double.valueOf(strLat);
        double dLon = Double.valueOf(strLon);

        Gps gps = new Gps(dLat,dLon);
        Log.i(TAG, "Where() Start Time:" + System.currentTimeMillis());
        String ret = where(gps);
        Log.i(TAG, "where() End Time:" + System.currentTimeMillis());
        mIsInChina.setText(ret);

    }


    public static double pi = 3.1415926535897932384626;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     */
    public static Gps gcj02_To_Bd09(double gg_lat, double gg_lon) {
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        return new Gps(bd_lat, bd_lon);
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 BD-09 坐标转换成GCJ-02 坐标
     */
    public static Gps bd09_To_Gcj02(double bd_lat, double bd_lon) {
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);
        double gg_lon = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        return new Gps(gg_lat, gg_lon);
    }


    /**
     * 地球坐标系 (WGS84) 与火星坐标系 (GCJ-02) 的转换算法 将 WGS84 坐标转换成 GCJ-02 坐标
     */
    public static Gps gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return null;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }

    /**
     * 地球坐标系 (WGS84) 与火星坐标系 (GCJ-02) 的转换算法 将 GCJ-02 坐标转换成 WGS84 坐标
     */
    public static Gps gcj_To_Gps84(double lat, double lon) {
        Gps gps = transform(lat, lon);
        double lontitude = lon * 2 - gps.getWgLon();
        double latitude = lat * 2 - gps.getWgLat();
        return new Gps(latitude, lontitude);
    }


    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347) return true;
        if (lat < 0.8293 || lat > 55.8271) return true;
        return false;
    }
    private static Gps transform(double lat, double lon) {
        if (outOfChina(lat, lon)) return new Gps(lat, lon);
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new Gps(mgLat, mgLon);
    }
    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }
    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }

    private  Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_LOCATION:
                    String currentPositon = (String) msg.obj;
                    positionTextView.setText(currentPositon);
                    break;

                default:
                    break;
            }
        }
    };

    private static class Gps {
        private double mLat = 0.0;
        private double mLon = 0.0;

        Gps(double lat , double lon) {
            mLat = lat;
            mLon = lon;
        }

        double getWgLon() {
           return mLon;
        }

        double getWgLat() {
            return mLat;
        }

        String tostring () {
            return mLat + "," + mLon;
        }
    }


    private Point[] main_polygon_map;
    private Point []chong_ming_map;
    private Point []hai_lan_map;
    private Point []hong_kong_map;
    private Point []macao_map;
    private Point []taiwan_map;

    private void InitPolygon()
    {
        main_polygon_map = new Point[]  //Mainland
        {
            new Point(27.32083f, 88.91693f),
            new Point(27.54243f, 88.76464f),
            new Point(28.00805f, 88.83575f),
            new Point(28.1168f, 88.62435f),
            new Point(27.86605f, 88.14279f),
            new Point(27.82305f, 87.19275f),
            new Point(28.11166f, 86.69527f),
            new Point(27.90888f, 86.45137f),
            new Point(28.15805f, 86.19769f),
            new Point(27.88625f, 86.0054f),
            new Point(28.27916f, 85.72137f),
            new Point(28.30666f, 85.11095f),
            new Point(28.59104f, 85.19518f),
            new Point(28.54444f, 84.84665f),
            new Point(28.73402f, 84.48623f),
            new Point(29.26097f, 84.11651f),
            new Point(29.18902f, 83.5479f),
            new Point(29.63166f, 83.19109f),
            new Point(30.06923f, 82.17525f),
            new Point(30.33444f, 82.11123f),
            new Point(30.385f, 81.42623f),
            new Point(30.01194f, 81.23221f),
            new Point(30.20435f, 81.02536f),
            new Point(30.57552f, 80.207f),
            new Point(30.73374f, 80.25423f),
            new Point(30.96583f, 79.86304f),
            new Point(30.95708f, 79.55429f),
            new Point(31.43729f, 79.08082f),
            new Point(31.30895f, 78.76825f),
            new Point(31.96847f, 78.77075f),
            new Point(32.24304f, 78.47594f),
            new Point(32.5561f, 78.40595f),
            new Point(32.63902f, 78.74623f),
            new Point(32.35083f, 78.9711f),
            new Point(32.75666f, 79.52874f),
            new Point(33.09944f, 79.37511f),
            new Point(33.42863f, 78.93623f),
            new Point(33.52041f, 78.81387f),
            new Point(34.06833f, 78.73581f),
            new Point(34.35001f, 78.98535f),
            new Point(34.6118f, 78.33707f),
            new Point(35.28069f, 78.02305f),
            new Point(35.49902f, 78.0718f),
            new Point(35.50133f, 77.82393f),
            new Point(35.6125f, 76.89526f),
            new Point(35.90665f, 76.55304f),
            new Point(35.81458f, 76.18061f),
            new Point(36.07082f, 75.92887f),
            new Point(36.23751f, 76.04166f),
            new Point(36.66343f, 75.85984f),
            new Point(36.73169f, 75.45179f),
            new Point(36.91156f, 75.39902f),
            new Point(36.99719f, 75.14787f),
            new Point(37.02782f, 74.56543f),
            new Point(37.17f, 74.39089f),
            new Point(37.23733f, 74.91574f),
            new Point(37.40659f, 75.18748f),
            new Point(37.65243f, 74.9036f),
            new Point(38.47256f, 74.85442f),
            new Point(38.67438f, 74.35471f),
            new Point(38.61271f, 73.81401f),
            new Point(38.88653f, 73.70818f),
            new Point(38.97256f, 73.85235f),
            new Point(39.23569f, 73.62005f),
            new Point(39.45483f, 73.65569f),
            new Point(39.59965f, 73.95471f),
            new Point(39.76896f, 73.8429f),
            new Point(40.04202f, 73.99096f),
            new Point(40.32792f, 74.88089f),
            new Point(40.51723f, 74.8588f),
            new Point(40.45042f, 75.23394f),
            new Point(40.64452f, 75.58284f),
            new Point(40.298f, 75.70374f),
            new Point(40.35324f, 76.3344f),
            new Point(41.01258f, 76.87067f),
            new Point(41.04079f, 78.08083f),
            new Point(41.39286f, 78.39554f),
            new Point(42.03954f, 80.24513f),
            new Point(42.19622f, 80.23402f),
            new Point(42.63245f, 80.15804f),
            new Point(42.81565f, 80.25796f),
            new Point(42.88545f, 80.57226f),
            new Point(43.02906f, 80.38405f),
            new Point(43.1683f, 80.81526f),
            new Point(44.11378f, 80.36887f),
            new Point(44.6358f, 80.38499f),
            new Point(44.73408f, 80.51589f),
            new Point(44.90282f, 79.87106f),
            new Point(45.3497f, 81.67928f),
            new Point(45.15748f, 81.94803f),
            new Point(45.13303f, 82.56638f),
            new Point(45.43581f, 82.64624f),
            new Point(45.5831f, 82.32179f),
            new Point(47.20061f, 83.03443f),
            new Point(46.97332f, 83.93026f),
            new Point(46.99361f, 84.67804f),
            new Point(46.8277f, 84.80318f),
            new Point(47.0591f, 85.52257f),
            new Point(47.26221f, 85.70139f),
            new Point(47.93721f, 85.53707f),
            new Point(48.39333f, 85.76596f),
            new Point(48.54277f, 86.59791f),
            new Point(49.1102f, 86.87602f),
            new Point(49.09262f, 87.34821f),
            new Point(49.17295f, 87.8407f),
            new Point(48.98304f, 87.89291f),
            new Point(48.88103f, 87.7611f),
            new Point(48.73499f, 88.05942f),
            new Point(48.56541f, 87.99194f),
            new Point(48.40582f, 88.51679f),
            new Point(48.21193f, 88.61179f),
            new Point(47.99374f, 89.08514f),
            new Point(47.88791f, 90.07096f),
            new Point(46.95221f, 90.9136f),
            new Point(46.57735f, 91.07027f),
            new Point(46.29694f, 90.92151f),
            new Point(46.01735f, 91.02651f),
            new Point(45.57972f, 90.68193f),
            new Point(45.25305f, 90.89694f),
            new Point(45.07729f, 91.56088f),
            new Point(44.95721f, 93.5547f),
            new Point(44.35499f, 94.71735f),
            new Point(44.29416f, 95.41061f),
            new Point(44.01937f, 95.34109f),
            new Point(43.99311f, 95.53339f),
            new Point(43.28388f, 95.87901f),
            new Point(42.73499f, 96.38206f),
            new Point(42.79583f, 97.1654f),
            new Point(42.57194f, 99.51012f),
            new Point(42.67707f, 100.8425f),
            new Point(42.50972f, 101.8147f),
            new Point(42.23333f, 102.0772f),
            new Point(41.88721f, 103.4164f),
            new Point(41.87721f, 104.5267f),
            new Point(41.67068f, 104.5237f),
            new Point(41.58666f, 105.0065f),
            new Point(42.46624f, 107.4758f),
            new Point(42.42999f, 109.3107f),
            new Point(42.64576f, 110.1064f),
            new Point(43.31694f, 110.9897f),
            new Point(43.69221f, 111.9583f),
            new Point(44.37527f, 111.4214f),
            new Point(45.04944f, 111.873f),
            new Point(45.08055f, 112.4272f),
            new Point(44.8461f, 112.853f),
            new Point(44.74527f, 113.638f),
            new Point(45.38943f, 114.5453f),
            new Point(45.4586f, 115.7019f),
            new Point(45.72193f, 116.2104f),
            new Point(46.29583f, 116.5855f),
            new Point(46.41888f, 117.3755f),
            new Point(46.57069f, 117.425f),
            new Point(46.53645f, 117.8455f),
            new Point(46.73638f, 118.3147f),
            new Point(46.59895f, 119.7068f),
            new Point(46.71513f, 119.9315f),
            new Point(46.90221f, 119.9225f),
            new Point(47.66499f, 119.125f),
            new Point(47.99475f, 118.5393f),
            new Point(48.01125f, 117.8046f),
            new Point(47.65741f, 117.3827f),
            new Point(47.88805f, 116.8747f),
            new Point(47.87819f, 116.2624f),
            new Point(47.69186f, 115.9231f),
            new Point(47.91749f, 115.5944f),
            new Point(48.14353f, 115.5491f),
            new Point(48.25249f, 115.8358f),
            new Point(48.52055f, 115.8111f),
            new Point(49.83047f, 116.7114f),
            new Point(49.52058f, 117.8747f),
            new Point(49.92263f, 118.5746f),
            new Point(50.09631f, 119.321f),
            new Point(50.33028f, 119.36f),
            new Point(50.39027f, 119.1386f),
            new Point(51.62083f, 120.0641f),
            new Point(52.115f, 120.7767f),
            new Point(52.34423f, 120.6259f),
            new Point(52.54267f, 120.7122f),
            new Point(52.58805f, 120.0819f),
            new Point(52.76819f, 120.0314f),
            new Point(53.26374f, 120.8307f),
            new Point(53.54361f, 123.6147f),
            new Point(53.18832f, 124.4933f),
            new Point(53.05027f, 125.62f),
            new Point(52.8752f, 125.6573f),
            new Point(52.75722f, 126.0968f),
            new Point(52.5761f, 125.9943f),
            new Point(52.12694f, 126.555f),
            new Point(51.99437f, 126.4412f),
            new Point(51.38138f, 126.9139f),
            new Point(51.26555f, 126.8176f),
            new Point(51.31923f, 126.9689f),
            new Point(51.05825f, 126.9331f),
            new Point(50.74138f, 127.2919f),
            new Point(50.31472f, 127.334f),
            new Point(50.20856f, 127.5861f),
            new Point(49.80588f, 127.515f),
            new Point(49.58665f, 127.838f),
            new Point(49.58443f, 128.7119f),
            new Point(49.34676f, 129.1118f),
            new Point(49.4158f, 129.4902f),
            new Point(48.86464f, 130.2246f),
            new Point(48.86041f, 130.674f),
            new Point(48.60576f, 130.5236f),
            new Point(48.3268f, 130.824f),
            new Point(48.10839f, 130.6598f),
            new Point(47.68721f, 130.9922f),
            new Point(47.71027f, 132.5211f),
            new Point(48.09888f, 133.0827f),
            new Point(48.06888f, 133.4843f),
            new Point(48.39112f, 134.4153f),
            new Point(48.26713f, 134.7408f),
            new Point(47.99207f, 134.5576f),
            new Point(47.70027f, 134.7608f),
            new Point(47.32333f, 134.1825f),
            new Point(46.64017f, 133.9977f),
            new Point(46.47888f, 133.8472f),
            new Point(46.25363f, 133.9016f),
            new Point(45.82347f, 133.4761f),
            new Point(45.62458f, 133.4702f),
            new Point(45.45083f, 133.1491f),
            new Point(45.05694f, 133.0253f),
            new Point(45.34582f, 131.8684f),
            new Point(44.97388f, 131.4691f),
            new Point(44.83649f, 130.953f),
            new Point(44.05193f, 131.298f),
            new Point(43.53624f, 131.1912f),
            new Point(43.38958f, 131.3104f),
            new Point(42.91645f, 131.1285f),
            new Point(42.74485f, 130.4327f),
            new Point(42.42186f, 130.6044f),
            new Point(42.71416f, 130.2468f),
            new Point(42.88794f, 130.2514f),
            new Point(43.00457f, 129.9046f),
            new Point(42.43582f, 129.6955f),
            new Point(42.44624f, 129.3493f),
            new Point(42.02736f, 128.9269f),
            new Point(42.00124f, 128.0566f),
            new Point(41.58284f, 128.3002f),
            new Point(41.38124f, 128.1529f),
            new Point(41.47249f, 127.2708f),
            new Point(41.79222f, 126.9047f),
            new Point(41.61176f, 126.5661f),
            new Point(40.89694f, 126.0118f),
            new Point(40.47037f, 124.8851f),
            new Point(40.09362f, 124.3736f),
            new Point(39.82777f, 124.128f),
            new Point(39.8143f, 123.2422f),
            new Point(39.67388f, 123.2167f),
            new Point(38.99638f, 121.648f),
            new Point(38.8611f, 121.6982f),
            new Point(38.71909f, 121.1873f),
            new Point(38.91221f, 121.0887f),
            new Point(39.09013f, 121.6794f),
            new Point(39.2186f, 121.5994f),
            new Point(39.35166f, 121.7511f),
            new Point(39.52847f, 121.2283f),
            new Point(39.62322f, 121.533f),
            new Point(39.81138f, 121.4683f),
            new Point(40.00305f, 121.881f),
            new Point(40.50562f, 122.2987f),
            new Point(40.73874f, 122.0521f),
            new Point(40.92194f, 121.1775f),
            new Point(40.1961f, 120.4468f),
            new Point(39.87242f, 119.5264f),
            new Point(39.15693f, 118.9715f),
            new Point(39.04083f, 118.3273f),
            new Point(39.19846f, 117.889f),
            new Point(38.67555f, 117.5364f),
            new Point(38.38666f, 117.6722f),
            new Point(38.16721f, 118.0281f),
            new Point(38.1529f, 118.8378f),
            new Point(37.87832f, 119.0355f),
            new Point(37.30054f, 118.9566f),
            new Point(37.14361f, 119.2328f),
            new Point(37.15138f, 119.7672f),
            new Point(37.35228f, 119.8529f),
            new Point(37.83499f, 120.7371f),
            new Point(37.42458f, 121.58f),
            new Point(37.55256f, 122.1282f),
            new Point(37.41833f, 122.1814f),
            new Point(37.39624f, 122.5586f),
            new Point(37.20999f, 122.5972f),
            new Point(37.02583f, 122.4005f),
            new Point(37.01978f, 122.5392f),
            new Point(36.89361f, 122.5047f),
            new Point(36.84298f, 122.1923f),
            new Point(37.00027f, 121.9566f),
            new Point(36.75889f, 121.5944f),
            new Point(36.61666f, 120.7764f),
            new Point(36.52638f, 120.96f),
            new Point(36.37582f, 120.8753f),
            new Point(36.42277f, 120.7062f),
            new Point(36.14075f, 120.6956f),
            new Point(36.0419f, 120.3436f),
            new Point(36.26345f, 120.3078f),
            new Point(36.19998f, 120.0889f),
            new Point(35.95943f, 120.2378f),
            new Point(35.57893f, 119.6475f),
            new Point(34.88499f, 119.1761f),
            new Point(34.31145f, 120.2487f),
            new Point(32.97499f, 120.8858f),
            new Point(32.63889f, 120.8375f),
            new Point(32.42958f, 121.3348f),
            new Point(32.11333f, 121.4412f),
            new Point(32.02166f, 121.7066f),
            new Point(31.67833f, 121.8275f),
            new Point(31.86639f, 120.9444f),
            new Point(32.09361f, 120.6019f),
            new Point(31.94555f, 120.099f),
            new Point(32.30638f, 119.8267f),
            new Point(32.26277f, 119.6317f),
            new Point(31.90388f, 120.1364f),
            new Point(31.98833f, 120.7026f),
            new Point(31.81944f, 120.7196f),
            new Point(31.30889f, 121.6681f),
            new Point(30.97986f, 121.8828f),
            new Point(30.85305f, 121.8469f),
            new Point(30.56889f, 120.9915f),
            new Point(30.33555f, 120.8144f),
            new Point(30.39298f, 120.4586f),
            new Point(30.19694f, 120.15f),
            new Point(30.31027f, 120.5082f),
            new Point(30.06465f, 120.7916f),
            new Point(30.30458f, 121.2808f),
            new Point(29.96305f, 121.6778f),
            new Point(29.88211f, 122.1196f),
            new Point(29.51167f, 121.4483f),
            new Point(29.58916f, 121.9744f),
            new Point(29.19527f, 121.9336f),
            new Point(29.18388f, 121.8119f),
            new Point(29.37236f, 121.7969f),
            new Point(29.19729f, 121.7444f),
            new Point(29.29111f, 121.5611f),
            new Point(29.1634f, 121.4135f),
            new Point(29.02194f, 121.6914f),
            new Point(28.9359f, 121.4908f),
            new Point(28.72798f, 121.6113f),
            new Point(28.84215f, 121.1464f),
            new Point(28.66993f, 121.4844f),
            new Point(28.34722f, 121.6417f),
            new Point(28.13889f, 121.3419f),
            new Point(28.38277f, 121.1651f),
            new Point(27.98222f, 120.9353f),
            new Point(28.07944f, 120.5908f),
            new Point(27.87229f, 120.84f),
            new Point(27.59319f, 120.5812f),
            new Point(27.45083f, 120.6655f),
            new Point(27.20777f, 120.5075f),
            new Point(27.28278f, 120.1896f),
            new Point(27.14764f, 120.4211f),
            new Point(26.89805f, 120.0332f),
            new Point(26.64465f, 120.128f),
            new Point(26.51778f, 119.8603f),
            new Point(26.78823f, 120.0733f),
            new Point(26.64888f, 119.8668f),
            new Point(26.79611f, 119.7879f),
            new Point(26.75625f, 119.5503f),
            new Point(26.44222f, 119.8204f),
            new Point(26.47388f, 119.5775f),
            new Point(26.33861f, 119.658f),
            new Point(26.36777f, 119.9489f),
            new Point(25.99694f, 119.4253f),
            new Point(26.14041f, 119.0975f),
            new Point(25.93788f, 119.354f),
            new Point(25.99069f, 119.7058f),
            new Point(25.67996f, 119.5807f),
            new Point(25.68222f, 119.4522f),
            new Point(25.35333f, 119.6454f),
            new Point(25.60649f, 119.3149f),
            new Point(25.42097f, 119.1053f),
            new Point(25.25319f, 119.3526f),
            new Point(25.17208f, 119.2726f),
            new Point(25.2426f, 118.8749f),
            new Point(24.97194f, 118.9866f),
            new Point(24.88291f, 118.5729f),
            new Point(24.75673f, 118.7631f),
            new Point(24.52861f, 118.5953f),
            new Point(24.53638f, 118.2397f),
            new Point(24.68194f, 118.1688f),
            new Point(24.44024f, 118.0199f),
            new Point(24.46019f, 117.7947f),
            new Point(24.25875f, 118.1237f),
            new Point(23.62437f, 117.1957f),
            new Point(23.65919f, 116.9179f),
            new Point(23.355f, 116.7603f),
            new Point(23.42024f, 116.5322f),
            new Point(23.23666f, 116.7871f),
            new Point(23.21083f, 116.5139f),
            new Point(22.93902f, 116.4817f),
            new Point(22.73916f, 115.7978f),
            new Point(22.88416f, 115.6403f),
            new Point(22.65889f, 115.5367f),
            new Point(22.80833f, 115.1614f),
            new Point(22.70277f, 114.8889f),
            new Point(22.53305f, 114.8722f),
            new Point(22.64027f, 114.718f),
            new Point(22.81402f, 114.7782f),
            new Point(22.69972f, 114.5208f),
            new Point(22.50423f, 114.6136f),
            new Point(22.55004f, 114.2223f),
            new Point(22.42993f, 114.3885f),
            new Point(22.26056f, 114.2961f),
            new Point(22.36736f, 113.9056f),
            new Point(22.50874f, 114.0337f),
            new Point(22.47444f, 113.8608f),
            new Point(22.83458f, 113.606f),
            new Point(23.05027f, 113.5253f),
            new Point(23.11724f, 113.8219f),
            new Point(23.05083f, 113.4793f),
            new Point(22.87986f, 113.3629f),
            new Point(22.54944f, 113.5648f),
            new Point(22.18701f, 113.5527f),
            new Point(22.56701f, 113.1687f),
            new Point(22.17965f, 113.3868f),
            new Point(22.04069f, 113.2226f),
            new Point(22.20485f, 113.0848f),
            new Point(21.8693f, 112.94f),
            new Point(21.96472f, 112.824f),
            new Point(21.70139f, 112.2819f),
            new Point(21.91611f, 111.8921f),
            new Point(21.75139f, 111.9669f),
            new Point(21.77819f, 111.6762f),
            new Point(21.61264f, 111.7832f),
            new Point(21.5268f, 111.644f),
            new Point(21.52528f, 111.0285f),
            new Point(21.21138f, 110.5328f),
            new Point(21.37322f, 110.3944f),
            new Point(20.84381f, 110.1594f),
            new Point(20.84083f, 110.3755f),
            new Point(20.64f, 110.3239f),
            new Point(20.48618f, 110.5274f),
            new Point(20.24611f, 110.2789f),
            new Point(20.2336f, 109.9244f),
            new Point(20.4318f, 110.0069f),
            new Point(20.92416f, 109.6629f),
            new Point(21.44694f, 109.9411f),
            new Point(21.50569f, 109.6605f),
            new Point(21.72333f, 109.5733f),
            new Point(21.49499f, 109.5344f),
            new Point(21.39666f, 109.1428f),
            new Point(21.58305f, 109.1375f),
            new Point(21.61611f, 108.911f),
            new Point(21.79889f, 108.8702f),
            new Point(21.59888f, 108.7403f),
            new Point(21.93562f, 108.4692f),
            new Point(21.59014f, 108.5125f),
            new Point(21.68999f, 108.3336f),
            new Point(21.51444f, 108.2447f),
            new Point(21.54241f, 107.99f),
            new Point(21.66694f, 107.7831f),
            new Point(21.60526f, 107.3627f),
            new Point(22.03083f, 106.6933f),
            new Point(22.45682f, 106.5517f),
            new Point(22.76389f, 106.7875f),
            new Point(22.86694f, 106.7029f),
            new Point(22.91253f, 105.8771f),
            new Point(23.32416f, 105.3587f),
            new Point(23.18027f, 104.9075f),
            new Point(22.81805f, 104.7319f),
            new Point(22.6875f, 104.3747f),
            new Point(22.79812f, 104.1113f),
            new Point(22.50387f, 103.9687f),
            new Point(22.78287f, 103.6538f),
            new Point(22.58436f, 103.5224f),
            new Point(22.79451f, 103.3337f),
            new Point(22.43652f, 103.0304f),
            new Point(22.77187f, 102.4744f),
            new Point(22.39629f, 102.1407f),
            new Point(22.49777f, 101.7415f),
            new Point(22.20916f, 101.5744f),
            new Point(21.83444f, 101.7653f),
            new Point(21.14451f, 101.786f),
            new Point(21.17687f, 101.2919f),
            new Point(21.57264f, 101.1482f),
            new Point(21.76903f, 101.099f),
            new Point(21.47694f, 100.6397f),
            new Point(21.43546f, 100.2057f),
            new Point(21.72555f, 99.97763f),
            new Point(22.05018f, 99.95741f),
            new Point(22.15592f, 99.16785f),
            new Point(22.93659f, 99.56484f),
            new Point(23.08204f, 99.5113f),
            new Point(23.18916f, 98.92747f),
            new Point(23.97076f, 98.67991f),
            new Point(24.16007f, 98.89073f),
            new Point(23.92999f, 97.54762f),
            new Point(24.26055f, 97.7593f),
            new Point(24.47666f, 97.54305f),
            new Point(24.73992f, 97.55255f),
            new Point(25.61527f, 98.19109f),
            new Point(25.56944f, 98.36137f),
            new Point(25.85597f, 98.7104f),
            new Point(26.12527f, 98.56944f),
            new Point(26.18472f, 98.73109f),
            new Point(26.79166f, 98.77777f),
            new Point(27.52972f, 98.69699f),
            new Point(27.6725f, 98.45888f),
            new Point(27.54014f, 98.31992f),
            new Point(28.14889f, 98.14499f),
            new Point(28.54652f, 97.55887f),
            new Point(28.22277f, 97.34888f),
            new Point(28.46749f, 96.65387f),
            new Point(28.35111f, 96.40193f),
            new Point(28.525f, 96.34027f),
            new Point(28.79569f, 96.61373f),
            new Point(29.05666f, 96.47083f),
            new Point(28.90138f, 96.17532f),
            new Point(29.05972f, 96.14888f),
            new Point(29.25757f, 96.39172f),
            new Point(29.46444f, 96.08315f),
            new Point(29.03527f, 95.38777f),
            new Point(29.33346f, 94.64751f),
            new Point(29.07348f, 94.23456f),
            new Point(28.6692f, 93.96172f),
            new Point(28.61876f, 93.35194f),
            new Point(28.3193f, 93.22205f),
            new Point(28.1419f, 92.71044f),
            new Point(27.86194f, 92.54498f),
            new Point(27.76472f, 91.65776f),
            new Point(27.945f, 91.66277f),
            new Point(28.08111f, 91.30138f),
            new Point(27.96999f, 91.08693f),
            new Point(28.07958f, 90.3765f),
            new Point(28.24257f, 90.38898f),
            new Point(28.32369f, 89.99819f),
            new Point(28.05777f, 89.48749f),
            new Point(27.32083f, 88.91693f)
        };

        chong_ming_map  = new Point[]  //Chongming
        {
            new Point(31.80054f, 121.2039f),
            new Point(31.49972f, 121.8736f),
            new Point(31.53111f, 121.5464f),
            new Point(31.80054f, 121.2039f)
        };

        hai_lan_map = new Point[]  //Hainan
        {
            new Point(19.52888f, 110.855f),
            new Point(19.16761f, 110.4832f),
            new Point(18.80083f, 110.5255f),
            new Point(18.3852f, 110.0503f),
            new Point(18.39152f, 109.7594f),
            new Point(18.19777f, 109.7036f),
            new Point(18.50562f, 108.6871f),
            new Point(19.28028f, 108.6283f),
            new Point(19.76f, 109.2939f),
            new Point(19.7236f, 109.1653f),
            new Point(19.89972f, 109.2572f),
            new Point(19.82861f, 109.4658f),
            new Point(19.99389f, 109.6108f),
            new Point(20.13361f, 110.6655f),
            new Point(19.97861f, 110.9425f),
            new Point(19.63829f, 111.0215f),
            new Point(19.52888f, 110.855f)
        };

        taiwan_map = new Point[]  //Taiwan
        {
            new Point(25.13474f, 121.4441f),
            new Point(25.28361f, 121.5632f),
            new Point(25.00722f, 122.0004f),
            new Point(24.85028f, 121.8182f),
            new Point(24.47638f, 121.8397f),
            new Point(23.0875f, 121.3556f),
            new Point(21.92791f, 120.7196f),
            new Point(22.31277f, 120.6103f),
            new Point(22.54044f, 120.3071f),
            new Point(23.04437f, 120.0539f),
            new Point(23.61708f, 120.1112f),
            new Point(25.00166f, 121.0017f),
            new Point(25.13474f, 121.4441f)
        };

        /**
         * @var array 香港坐标范围
         */
        hong_kong_map = new Point[] {
            new Point(22.249999892647594f, 114.50441355319435f),
            new Point(22.368126048048683f, 114.50443993961295f),
            new Point(22.466006243124475f, 114.45971254142662f),
            new Point(22.542254757750978f, 114.4599611599683f),
            new Point(22.55932090471159f, 114.43884768753253f),
            new Point(22.565206463021347f, 114.344184162758f),
            new Point(22.562671018902346f, 114.28081502180774f),
            new Point(22.55043259777784f, 114.2477793560842f),
            new Point(22.541094108513626f, 114.23236040195029f),
            new Point(22.551982474025873f, 114.22396081221211f),
            new Point(22.55453972269849f, 114.20533830058012f),
            new Point(22.55110903642457f, 114.1858636778768f),
            new Point(22.55693640884497f, 114.18190816970951f),
            new Point(22.556282253648927f, 114.16886549165515f),
            new Point(22.559054819129546f, 114.16620633895373f),
            new Point(22.552294621440392f, 114.16167796133432f),
            new Point(22.55204329573786f, 114.15710142281712f),
            new Point(22.538902216990667f, 114.15341156277034f),
            new Point(22.539153556037714f, 114.13578028510814f),
            new Point(22.537293224840262f, 114.13400173187256f),
            new Point(22.536817569098282f, 114.13061141967773f),
            new Point(22.528929372495444f, 114.12095546722412f),
            new Point(22.52670949724544f, 114.11662101745605f),
            new Point(22.53031677641036f, 114.11335945129395f),
            new Point(22.531307770674214f, 114.10297393798828f),
            new Point(22.53396360023646f, 114.09808158874512f),
            new Point(22.53343707193792f, 114.09372327592442f),
            new Point(22.529642896249428f, 114.09070014953613f),
            new Point(22.52705622228592f, 114.08348778048558f),
            new Point(22.51556035023105f, 114.07731556551114f),
            new Point(22.513722117271967f, 114.06898855529884f),
            new Point(22.50994035876407f, 114.0640926361084f),
            new Point(22.502447107410138f, 114.06181812286377f),
            new Point(22.499512110074636f, 114.05704842171406f),
            new Point(22.505012706928436f, 114.02876351373768f),
            new Point(22.497720366785614f, 114.01753996106691f),
            new Point(22.50764657336277f, 114.00005749188864f),
            new Point(22.469371790345302f, 113.952660425717f),
            new Point(22.42579717853363f, 113.87398492346281f),
            new Point(22.33031555294684f, 113.87396523778321f),
            new Point(22.27015932886428f, 113.85229493623311f),
            new Point(22.26479247430387f, 113.84392493006567f),
            new Point(22.214179718613675f, 113.82209377441288f),
            new Point(22.18096131238036f, 113.83732167052551f),
            new Point(22.139561111546026f, 113.90135982635742f),
            new Point(22.133772057053257f, 113.92718432055024f),
            new Point(22.14553272502743f, 113.94436561774847f),
            new Point(22.14569569642202f, 114.24078049550716f),
            new Point(22.135940549792845f, 114.25929643465665f),
            new Point(22.145716826576773f, 114.2887681363611f),
            new Point(22.145698263611518f, 114.50439118472904f),
            new Point(22.249999892647594f, 114.50441355319435f)
        };
        /**
         * @var array 澳门边界 坐标
         */
        macao_map = new Point[] {
            new Point(22.20040420937448f, 113.576824210468f),
            new Point(22.210899257214723f, 113.56521182956781f),
            new Point(22.214325537708863f, 113.54938073074514f),
            new Point(22.213549910287544f, 113.54297161102295f),
            new Point(22.209854951267875f, 113.53400230407715f),
            new Point(22.20602083179696f, 113.53479623794556f),
            new Point(22.198610552801735f, 113.53803634643555f),
            new Point(22.184469689186336f, 113.53329828238885f),
            new Point(22.178363784618646f, 113.5328029478798f),
            new Point(22.154100922132123f, 113.54207038879395f),
            new Point(22.141370325851674f, 113.5540582813416f),
            new Point(22.109866179779f, 113.55374832388527f),
            new Point(22.102301049950423f, 113.55989800975762f),
            new Point(22.102436011325323f, 113.57091494192457f),
            new Point(22.10839000904641f, 113.58756638865023f),
            new Point(22.118335841443894f, 113.6014786594061f),
            new Point(22.130923195366428f, 113.60988163834484f),
            new Point(22.142441290591666f, 113.6087190335273f),
            new Point(22.178629428116107f, 113.5930442414218f),
            new Point(22.20040420937448f, 113.576824210468f),
	    };

        /**
         * @var array 台湾边界坐标
         */
//        taiwan_map = new Point[] {
//            new Point(25.45319497952487f, 121.431884765625f),
//            new Point(24.14174098050432f, 120.069580078125f),
//            new Point(23.61432859499168f, 119.366455078125f),
//            new Point(23.089838367476705f, 119.3389892578125f),
//            new Point(22.54807431541815f, 119.827880859375f),
//            new Point(21.662533492414674f, 120.5255126953125f),
//            new Point(21.53995662308542f, 121.3275146484375f),
//            new Point(22.319589442833912f, 122.0635986328125f),
//            new Point(23.775291236451384f, 121.9537353515625f),
//            new Point(24.617057340809524f, 122.200927734375f),
//            new Point(25.418470119273117f, 122.1185302734375f),
//            new Point(25.45319497952487f, 121.431884765625f)
//        };

    }

    private String where(Gps location)
    {
        String area = "";
        if (isInMainPolygon(location)) {
            area = "MainLand";
        }
        if (isInHaiLan(location) ) {
            area += " HaiLan";
        }
        if (isInChongMing(location) ) {
            area += " ChongMing";
        }
        if (isInTaiwan(location)) {
            area += " Taiwan";
        }
        if (isInHongKong(location)){
            area += " HongKong";
        }
        if (isInMacao(location)) {

            area += " Macao";
        }

        return  area;
    }

    private boolean isInsideChina(Gps location) {
        return isInMainPolygon(location) || isInHaiLan(location) || isInChongMing(location) ||
                isInTaiwan(location) || isInMacao(location) || isInHongKong(location);
    }

    private boolean isInMainLand(Gps location) {
        return isInsideChina(location) && !isInHongKong(location) && !isInTaiwan(location) && !isInMacao(location);
    }

    private boolean isInMainPolygon(Gps location) {
        if (location == null) {
            return false;
        }
        return isInsidePolygon(main_polygon_map, location.getWgLat(), location.getWgLon());
    }

    private boolean isInChongMing(Gps location) {
        if (location == null) {
            return false;
        }
        return isInsidePolygon(chong_ming_map, location.getWgLat(), location.getWgLon());
    }


    private boolean isInHaiLan(Gps location) {

        if (location == null) {
            return false;
        }
        return isInsidePolygon(hai_lan_map, location.getWgLat(), location.getWgLon());
    }

    private boolean isInHongKong(Gps location) {

        if (location == null) {
            return false;
        }
        return isInsidePolygon(hong_kong_map, location.getWgLat(), location.getWgLon());
    }

    private boolean isInMacao(Gps location) {
        if (location == null) {
            return false;
        }
        return isInsidePolygon(macao_map, location.getWgLat(), location.getWgLon());
    }

    private boolean isInTaiwan(Gps location) {
        if (location == null) {
            return false;
        }
        return isInsidePolygon(taiwan_map, location.getWgLat(), location.getWgLon());
    }

    private boolean isInsidePolygon(Point[] poly, double x, double y)
    {
        int index = 0;
        boolean inside = false;
        Point prePoint = poly[0];
        for (index = 1; index < poly.length; index++)
        {
            Point nextPoint = poly[index];
            if (y > Math.min(prePoint.getY(), nextPoint.getY())
                    && y <= Math.max(prePoint.getY(), nextPoint.getY())
                    && x <= Math.max(prePoint.getX(), nextPoint.getX())
                    && prePoint.getY() != nextPoint.getY())
            {
                double xinters = (y - prePoint.getY()) * (nextPoint.getX() - prePoint.getX()) / (nextPoint.getY() - prePoint.getY()) + prePoint.getX();
                if (prePoint.getX() == nextPoint.getX() || x <= xinters)
                    inside ^= true;
            }
            prePoint = nextPoint;
        }
        return inside;
    }

}


