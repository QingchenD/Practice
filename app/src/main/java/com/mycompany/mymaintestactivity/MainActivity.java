package com.mycompany.mymaintestactivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ViewTouchEvent.ViewTouchEventTestActivity;
import com.mycompany.BinderTest.BinderTestActivity;
import com.mycompany.fragment.FragmentPracticeActivity;
import com.mycompany.fragment.FragmentTestActivity;
import com.mycompany.Messenger.MessengerTestActivity;
import com.mycompany.aidlTest.AidlTestActivity;
import com.mycompany.annotation.ButterknifeTestActivity;
import com.mycompany.broadcastbestpractice.LoginActiviy;
import com.mycompany.launchmodeTest.LaunchModeTestActivity;
import com.mycompany.menu.PopUpMenuTestActivity;
import com.mycompany.net.NetClientActivity;
import com.mycompany.net.NetServerActivity;
import com.mycompany.net.OkHttpTestActivity;
import com.mycompany.recycleview.RecycleViewTest;
import com.mycompany.surface.SurfaceViewTestActivty;

import java.security.Security;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends Activity {
    private final String TAG = "MainActivity";

    public final int ACTIVITY_REQESET_CODE = 2;

    Button btn1;
    Button btn2;
    Button btnSecond;
    TextView btn2BackText;
    Button btnSingleton;
    Button btnSyncTest;
    Button btnPopupWindow;
    Button btnXYSeries;
    Button btnPlayAudio;
    Button btnPlayVideo;
    Button btnOpenFile;
    Button btnVibrateTest;
    Button btnServiceTest;
    Button btnTextureViewTest;
    Button btnShowImg;
    Button btnScanFile;
    Button btnDateFormat;
    Button btnMessage;
    Button btnFragment;
    Button btnFragmentBestPractice;
    Button btnNetworkChange;
    Button btnSendBroadcast;
    Button btnBroadcastPractice;
    Button btnSaveData;
    Button btnym2ip;
    Button btnSharedPreference;
    Button btnSQLiteDatabase;
    Button btnReadContact;
    Button btnNotificationTest;
    Button btnSmsTest;
    Button btnChoosePic;
    Button btnBluetooth;
    Button btnMyService;
    Button btnServiceBestPractice;
    Button btnWebViewTest;
    Button btnHttpConnection;
    Button btnLocationTest;
    Button btnLightSensorTest;
    Button btnAccelerometerSensorTest;
    Button btnCompassTest;
    Button btnFilterFile;
    Button btnLandPort;
    Button btnHandlerTest;
    Button btnJSON;
    Button btnUsb;
    Button btnImageTst;
    Button btnConnectUsb;
    Button btnSeekBar;
    Button btnWiFiHotSpot;
    Button btnMotionEventTest;
    Button btnUnderLine;
    Button btnQrCode;
    Button btnPhoneInfo;
    Button btnDialogTst;
    Button btnNetClient;
    Button btnNetServer;
    Button btnGetMac;
    Button btnGetPermission;
    Button btnMoveView;
    Button btnAnimator;
    Button btnKotlin;
    Button btnAudioRecord;
    Button btnLaunchMode;
    Button btnScrollContainList;

    @Bind(R.id.btn_aidl_test)
    Button btnAidlTest;

    Button btnAsyncTask;
    Button btnOkhttp;

    Button btnEventTouchTest;
    Button btnMessengerTest;
    Button btnBinderTest;
    Button btnPopupMenuTest;
    Button btnButterKnifeTest;
    Button btnSurfaceTest;
    Button btnRecyclerViewTest;


    private static Context Scontext;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Mydebug" + "onCreate() ");
        if (savedInstanceState != null) {
            String test = savedInstanceState.getString("extra_test");
            Log.d(TAG, "[onCreate] restore extra_test:" + test);
        }

        ActionBar bar = getActionBar();
        if (bar != null) {
            bar.show();
        }

        MyOnClickListener listener = new MyOnClickListener();
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btnSecond = (Button) findViewById(R.id.btn_seccond);
        btn2BackText = (TextView) findViewById(R.id.text_btn2_result);
        btnSingleton = (Button) findViewById(R.id.btn_singleton_tst);
        btnSyncTest = (Button) findViewById(R.id.btn_sync_tst);
        btnPopupWindow = (Button) findViewById(R.id.btn_popWindow_tst);
        btnXYSeries = (Button) findViewById(R.id.btn_XYSeries);
        btnPlayAudio = (Button) findViewById(R.id.btn_playAudio);
        btnPlayVideo = (Button) findViewById(R.id.btn_playVideo);
        btnOpenFile = (Button) findViewById(R.id.btn_open_file);
        btnVibrateTest = (Button) findViewById(R.id.btn_vibrate_test);
        btnServiceTest = (Button) findViewById(R.id.btn_service_test);
        btnTextureViewTest = (Button) findViewById(R.id.btn_textureview_test);
        btnShowImg = (Button) findViewById(R.id.btn_show_img);
        btnScanFile = (Button) findViewById(R.id.btn_scan_file);
        btnDateFormat = (Button) findViewById(R.id.btn_date_format);
        btnMessage = (Button) findViewById(R.id.btn_message);
        btnFragment = (Button) findViewById(R.id.btn_fragment);
        btnFragmentBestPractice = (Button) findViewById(R.id.btn_fragment_practice);
        btnNetworkChange = (Button) findViewById(R.id.btn_network_change);
        btnSendBroadcast = (Button) findViewById(R.id.btn_send_broadcast);
        btnBroadcastPractice = (Button) findViewById(R.id.btn_broadcast_practice);
        btnSaveData = (Button) findViewById(R.id.btn_save_data);
        btnym2ip = (Button) findViewById(R.id.btn_ym2ip);
        btnSharedPreference = (Button) findViewById(R.id.btn_shared_preference);
        btnSQLiteDatabase = (Button) findViewById(R.id.btn_sql_test);
        btnReadContact = (Button) findViewById(R.id.btn_read_contact);
        btnNotificationTest = (Button) findViewById(R.id.btn_notification_test);
        btnSmsTest = (Button) findViewById(R.id.btn_sms_test);
        btnChoosePic = (Button) findViewById(R.id.btn_choose_pic);
        btnBluetooth = (Button) findViewById(R.id.btn_blue_tooth_test);
        btnMyService = (Button) findViewById(R.id.btn_my_service);
        btnServiceBestPractice = (Button) findViewById(R.id.btn_service_best_practice);
        btnWebViewTest = (Button) findViewById(R.id.btn_webView_test);
        btnHttpConnection = (Button) findViewById(R.id.btn_http_connection);
        btnLocationTest = (Button) findViewById(R.id.btn_location_test);
        btnLightSensorTest = (Button) findViewById(R.id.btn_light_sensor_test);
        btnAccelerometerSensorTest = (Button) findViewById(R.id.btn_accelerometer_sensor_test);
        btnCompassTest = (Button) findViewById(R.id.btn_compass_test);
        btnFilterFile = (Button) findViewById(R.id.btn_filter_file);
        btnLandPort = (Button) findViewById(R.id.btn_landscape_portrait);
        btnHandlerTest = (Button) findViewById(R.id.btn_handler);
        btnJSON = (Button) findViewById(R.id.btn_json);
        btnUsb = (Button) findViewById(R.id.btn_usb_tst);
        btnImageTst = (Button) findViewById(R.id.btn_Image_test);
        btnConnectUsb = (Button) findViewById(R.id.btn_connect_usb);
        btnSeekBar = (Button) findViewById(R.id.btn_seek_bar);
        btnWiFiHotSpot = (Button) findViewById(R.id.btn_wifi_hotspot);
        btnMotionEventTest  = (Button) findViewById(R.id.btn_motion_event);
        btnUnderLine = (Button) findViewById(R.id.btn_under_line);
        btnQrCode = (Button) findViewById(R.id.btn_qr_code);
        btnPhoneInfo = (Button) findViewById(R.id.btn_phone_info);
        btnDialogTst = (Button) findViewById(R.id.btn_dialog_tst);
        btnNetClient = (Button) findViewById(R.id.btn_net_client);
        btnNetServer = (Button) findViewById(R.id.btn_net_server);
        btnGetMac = (Button) findViewById(R.id.btn_get_mac);
        btnGetPermission = (Button) findViewById(R.id.btn_get_permission);
        btnMoveView = (Button) findViewById(R.id.btn_move_view);
        btnAnimator = (Button) findViewById(R.id.btn_animator);
        btnKotlin = (Button) findViewById(R.id.btn_kotlin);
        btnAudioRecord = (Button) findViewById(R.id.btn_record_audio);
        btnLaunchMode = (Button) findViewById(R.id.btn_lanchmode);
        btnScrollContainList = (Button) findViewById(R.id.btn_scroll_list);
        btnAidlTest = (Button) findViewById(R.id.btn_aidl_test);
        btnAsyncTask = (Button) findViewById(R.id.btn_async_test);
        btnOkhttp = (Button) findViewById(R.id.btn_okhttp_test);
        btnEventTouchTest = (Button) findViewById(R.id.btn_event_touch_test);
        btnMessengerTest = (Button) findViewById(R.id.btn_messenger_test);
        btnBinderTest = (Button) findViewById(R.id.btn_binder_test);
        btnPopupMenuTest = (Button) findViewById(R.id.btn_popup_menu_test);
        btnButterKnifeTest = (Button) findViewById(R.id.btn_annotation_test);
        btnSurfaceTest = (Button) findViewById(R.id.btn_surface_test);
        btnRecyclerViewTest = (Button) findViewById(R.id.btn_recycle_view_test);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btnSecond.setOnClickListener(listener);
        btnSingleton.setOnClickListener(listener);
        btnSyncTest.setOnClickListener(listener);
        btnPopupWindow.setOnClickListener(listener);
        btnXYSeries.setOnClickListener(listener);
        btnPlayAudio.setOnClickListener(listener);
        btnPlayVideo.setOnClickListener(listener);
        btnOpenFile.setOnClickListener(listener);
        btnVibrateTest.setOnClickListener(listener);
        btnServiceTest.setOnClickListener(listener);
        btnTextureViewTest.setOnClickListener(listener);
        btnShowImg.setOnClickListener(listener);
        btnScanFile.setOnClickListener(listener);
        btnDateFormat.setOnClickListener(listener);
        btnMessage.setOnClickListener(listener);
        btnFragment.setOnClickListener(listener);
        btnFragmentBestPractice.setOnClickListener(listener);
        btnNetworkChange.setOnClickListener(listener);
        btnSendBroadcast.setOnClickListener(listener);
        btnBroadcastPractice.setOnClickListener(listener);
        btnSaveData.setOnClickListener(listener);
        btnym2ip.setOnClickListener(listener);
        btnSharedPreference.setOnClickListener(listener);
        btnSQLiteDatabase.setOnClickListener(listener);
        btnReadContact.setOnClickListener(listener);
        btnNotificationTest.setOnClickListener(listener);
        btnSmsTest.setOnClickListener(listener);
        btnChoosePic.setOnClickListener(listener);
        btnBluetooth.setOnClickListener(listener);
        btnMyService.setOnClickListener(listener);
        btnServiceBestPractice.setOnClickListener(listener);
        btnWebViewTest.setOnClickListener(listener);
        btnHttpConnection.setOnClickListener(listener);
        btnLocationTest.setOnClickListener(listener);
        btnLightSensorTest.setOnClickListener(listener);
        btnAccelerometerSensorTest.setOnClickListener(listener);
        btnCompassTest.setOnClickListener(listener);
        btnFilterFile.setOnClickListener(listener);
        btnLandPort.setOnClickListener(listener);
        btnHandlerTest.setOnClickListener(listener);
        btnJSON.setOnClickListener(listener);
        btnUsb.setOnClickListener(listener);
        btnImageTst.setOnClickListener(listener);
        btnConnectUsb.setOnClickListener(listener);
        btnSeekBar.setOnClickListener(listener);
        btnWiFiHotSpot.setOnClickListener(listener);
        btnMotionEventTest.setOnClickListener(listener);
        btnUnderLine.setOnClickListener(listener);
        btnQrCode.setOnClickListener(listener);
        btnPhoneInfo.setOnClickListener(listener);
        btnDialogTst.setOnClickListener(listener);
        btnNetClient.setOnClickListener(listener);
        btnNetServer.setOnClickListener(listener);
        btnGetMac.setOnClickListener(listener);
        btnGetPermission.setOnClickListener(listener);
        btnMoveView.setOnClickListener(listener);
        btnAnimator.setOnClickListener(listener);
        btnKotlin.setOnClickListener(listener);
        btnAudioRecord.setOnClickListener(listener);
        btnLaunchMode.setOnClickListener(listener);
        btnScrollContainList.setOnClickListener(listener);
        btnAidlTest.setOnClickListener(listener);
        btnAsyncTask.setOnClickListener(listener);
        btnOkhttp.setOnClickListener(listener);
        btnEventTouchTest.setOnClickListener(listener);
        btnMessengerTest.setOnClickListener(listener);
        btnBinderTest.setOnClickListener(listener);
        btnPopupMenuTest.setOnClickListener(listener);
        btnButterKnifeTest.setOnClickListener(listener);
        btnSurfaceTest.setOnClickListener(listener);
        btnRecyclerViewTest.setOnClickListener(listener);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " xdpi:" + xdpi + " ydpi:" + ydpi);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        int maxheapSize = manager.getLargeMemoryClass();

        Log.i(TAG, "mydebug  heapsize:" + heapSize + " maxHeapSize:" + maxheapSize);
//        SystemClock.sleep(10*1000);
//        Scontext = this;

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                testANR();
//            }
//        }).start();

//        SystemClock.sleep(10);
//        initView();
    }

    private synchronized void testANR() {
        SystemClock.sleep(30*1000); //
    }

    private synchronized void initView() {

    }


    public void setString(String s) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clikced Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.removed_item:
                Toast.makeText(this, "You clikced Reomove", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("[Mydebug]" + this.getClass().getSimpleName() + " onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQESET_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                System.out.println("[Mydebug]" + " result:" + result);
                btn2BackText.setText(result);
            }
        }
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "Mydebug" + "onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "Mydebug" + "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Mydebug" + "onResume()");

        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " onRsume()" + " taskID:" + getTaskId());
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() +  " networkaddress.cache.ttl:"  +  Security.getProperty("networkaddress.cache.ttl") +
                " networkaddress.cache.negative.ttl:" +  Security.getProperty("networkaddress.cache.negative.ttl") );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Mydebug" + "onPause()");
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "Mydebug" + "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Mydebug" + "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, " onNewIntent() time=" + intent.getLongExtra("time",0));
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn1:
                    launchActivityone();
                    break;

                case R.id.btn2:
                    launchActivityTwo();
                    break;

                case R.id.btn_seccond:
                    launchSecondActivity();
                    break;

                case R.id.btn_singleton_tst:
                    launchSingletonActivity();
                    break;

                case R.id.btn_sync_tst:
                    launchThreadSyncActivity();
                    break;

                case R.id.btn_popWindow_tst:
                    launchPopupWindowActivity();
                    break;

                case R.id.btn_XYSeries:
                    launchXySeriesActivity();
                    break;

                case R.id.btn_playAudio:
                    launchPlayAudioActivity();
                    break;

                case R.id.btn_playVideo:
                    launchPlayVideoActivity();
                    break;

                case R.id.btn_open_file:
                    launchOpenFileActivity();
                    break;

                case R.id.btn_vibrate_test:
                    launchVirbrateActivity();
                    break;

                case R.id.btn_service_test:
                    launchServiceDemoActivity();
                    break;

                case R.id.btn_textureview_test:
                    launchTextureViewActivity();
                    break;

                case R.id.btn_show_img:
                    launchShowImgActivity();
                    break;

                case R.id.btn_scan_file:
                    launchScanFileActivity();
                    break;

                case R.id.btn_date_format:
                    launchDateFormatActivity();
                    break;

                case R.id.btn_message:
                    launchMessageActivity();
                    break;

                case R.id.btn_fragment:
                    launchFragmentActivity();
                    break;

                case R.id.btn_fragment_practice:
                    launchFragmentPracticeActivity();
                    break;

                case R.id.btn_network_change:
                    launchNetworkChangeActivity();
                    break;

                case R.id.btn_send_broadcast:
                    launchSendBraodcastActivity();
                    break;

                case R.id.btn_broadcast_practice:
                    launchBroadcastPracticeActivity();
                    break;

                case R.id.btn_save_data:
                    launchSaveDataActivity();
                    break;

                case R.id.btn_ym2ip:
                    launchYm2IPTestActivity();
                    break;

                case R.id.btn_shared_preference:
                    launchSharePreferenceActivity();
                    break;

                case R.id.btn_sql_test:
                    launchSQLiteDatabaseActivity();
                    break;

                case R.id.btn_read_contact:
                    launReadContactsActivity();
                    break;

                case R.id.btn_notification_test:
                    launchNotificationActivity();
                    break;

                case R.id.btn_sms_test:
                    launchSMSTestActivity();
                    break;

                case R.id.btn_choose_pic:
                    launchChoosePicTestActivity();
                    break;


                case R.id.btn_blue_tooth_test:
                    launchBlueToothTestActivity();
                    break;

                case R.id.btn_my_service:
                    launchMyServiceActivity();
                    break;

                case R.id.btn_service_best_practice:
                    launchServiceBestPractice();
                    break;

                case R.id.btn_webView_test:
                    launchWebviewTestActivity();
                    break;

                case R.id.btn_http_connection:
                    launchHttpConnectionActivity();
                    break;

                case R.id.btn_location_test:
                    launchLocationTestActivity();
                    break;

                case R.id.btn_light_sensor_test:
                    launchLightSensorTestActivity();
                    break;

                case R.id.btn_accelerometer_sensor_test:
                    launchAccelerometerSensorTestActivity();
                    break;

                case R.id.btn_compass_test:
                    launchCompassTestActivity();
                    break;

                case R.id.btn_filter_file:
                    launchFilterFileActivity();
                    break;

                case R.id.btn_landscape_portrait:
                    launchLandscapePortraitActivity();
                    break;

                case R.id.btn_handler:
                    launchHandlerTestActivity();
                    break;

                case R.id.btn_json:
                    launchJSONTestActivity();
                    break;

                case R.id.btn_usb_tst:
                    launchUsbTestActivity();
                    break;

                case R.id.btn_Image_test:
                    launchSetImageManualActivity();
                    break;

                case R.id.btn_connect_usb:
                    launchConnectUsbActivity();
                    break;

                case R.id.btn_seek_bar:
                    launchSeekBarTestActivity();
                    break;

                case R.id.btn_wifi_hotspot:
                    launchWiFiHotSpotActivity();
                    break;


                case R.id.btn_motion_event:
                    launchMotionEventTestActivity();
                    break;

                case R.id.btn_under_line:
                    launchUnderLineTestActivity();
                    break;

                case R.id.btn_qr_code:
                    launchQRCodeTestActivity();
                    break;

                case R.id.btn_phone_info:
                    launchPhoneInforActivity();
                    break;

                case R.id.btn_dialog_tst:
                    launchDialogTestActivity();
                    break;

                case R.id.btn_net_client:
                    launchNetClientActivity();
                    break;

                case R.id.btn_net_server:
                    launchNetServerActivity();
                    break;

                case R.id.btn_get_mac:
                    launchGetMACActivity();
                    break;

                case R.id.btn_get_permission:
                    launPermissionActivity();
                    break;

                case R.id.btn_move_view:
                    launchMoveViewActivity();
                    break;


                case R.id.btn_animator:
                    launchAnimatorActivity();
                    break;

                case R.id.btn_kotlin:
                    launchKotlinActivity();
                    break;

                case R.id.btn_record_audio:
                    launchAudioRecordActivity();
                    break;

                case R.id.btn_lanchmode:
                    launchModeTestActivity();
                    break;

                case R.id.btn_scroll_list:
                    launchScrollContainList();
                    break;

                case R.id.btn_aidl_test:
                    launchAidlTestActivity();
                    break;

                case R.id.btn_async_test:
                    launchAsyncTaskTestActivity();
                    break;

                case R.id.btn_okhttp_test:
                    launchOkHttpTestActivity();
                    break;

                case R.id.btn_event_touch_test:
                    launchViewTouchEventTestActivity();
                    break;

                case R.id.btn_messenger_test:
                    launchMessengerTestActivity();
                    break;

                case R.id.btn_binder_test:
                    launchBinderTestActivity();
                    break;

                case R.id.btn_popup_menu_test:
                    launchPopupMenuTestActivity();
                    break;

                case R.id.btn_annotation_test:
                    launchAnnotationTestActivity();
                    break;

                case R.id.btn_surface_test:
                    launchSurfaceViewTestActivity();
                    break;

                case R.id.btn_recycle_view_test:
                    launchRecyclerViewTestActivity();
                    break;

                default:
                    break;
            }
        }
    }

    private void launchActivityone() {
//        Intent intent = new Intent(this, StartOneActitity.class);
//        intent.putExtra("time", System.currentTimeMillis());
//        startActivity(intent);

        Intent intent = new Intent("com.mycompany.mymaintestactivity.ACTION_START");
        intent.addCategory("android.intent.category.MY_CATEGORY");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfoList.size() > 0) {
            for (ResolveInfo resolveInfo : resolveInfoList)
            Log.d(TAG, " packageName:" + resolveInfo.activityInfo.packageName + " name:" + resolveInfo.activityInfo.name );
        }
        Context context = getApplicationContext();

        context.startActivity(intent);
    }

    private void launchActivityTwo() {
        Intent intent = new Intent(this, StartOneActivityForResult.class);
        intent.putExtra("two", true);
        startActivityForResult(intent, ACTIVITY_REQESET_CODE);
    }

    private void launchSecondActivity() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void launchSingletonActivity () {
        Intent intent = new Intent(this , SingeltonTestActivity.class);
        startActivity(intent);
    }

    private void launchThreadSyncActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ThreadSyncActivity.class);
        startActivity(intent);
    }

    private void launchPopupWindowActivity() {
        Intent intent = new Intent(this, PopWindowTestActivity.class);
        startActivity(intent);
    }

    private void launchXySeriesActivity() {
        Intent intent = new Intent(this, XYSeriesRendererActivity.class);
        startActivity(intent);
    }

    private void launchPlayAudioActivity() {
        Intent intent = new Intent(this, PlayAudioActivity.class);
        startActivity(intent);
    }

    private void launchPlayVideoActivity() {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        startActivity(intent);
    }

    private void launchOpenFileActivity() {
        Intent intent = new Intent(this, OpenFileActivity.class);
        startActivity(intent);
    }

    private void launchVirbrateActivity() {
        Intent intent = new Intent(this, VirbrateActivity.class);
        startActivity(intent);
    }

    private void launchServiceDemoActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ServiceDemoActivity.class);
        startActivity(intent);
    }

    private void launchTextureViewActivity() {
        Intent intent = new Intent();
        intent.setClass(this,TextureViewActivity.class);
        startActivity(intent);
    }

    private void launchShowImgActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ShowImgActivity.class);
        startActivity(intent);
    }

    private void launchScanFileActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ScanFileActivity.class);
        startActivity(intent);
    }

    private void launchDateFormatActivity() {
        Intent intent = new Intent();
        intent.setClass(this,DateFormatActivity.class);
        startActivity(intent);
    }

    private void launchMessageActivity() {
        Intent intent = new Intent();
        intent.setClass(this,MessageActivity.class);
        startActivity(intent);
    }

    public void launchFragmentActivity() {
        Intent intent = new Intent();
        intent.setClass(this, FragmentTestActivity.class);
        startActivity(intent);
    }

    public void launchFragmentPracticeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, FragmentPracticeActivity.class);
        startActivity(intent);
    }

    public void launchNetworkChangeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, NetworkChangeReceiverActivity.class);
        startActivity(intent);
    }

    public void launchSendBraodcastActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SendBroadcastActivity.class);
        startActivity(intent);
    }

    public void launchBroadcastPracticeActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActiviy.class);
        startActivity(intent);
    }

    public void launchSaveDataActivity () {
        Intent intent = new Intent();
        intent.setClass(this, SaveDataActivity.class);
        startActivity(intent);
    }

    public void launchYm2IPTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, Ym2IPTestActivity.class);
        startActivity(intent);
    }

    public void launchSharePreferenceActivity () {
        Intent intent = new Intent();
        intent.setClass(this, SharedPreferencesActivity.class);
        startActivity(intent);
    }

    public void launchSQLiteDatabaseActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SQLiteDatabaseActivity.class);
        startActivity(intent);
    }

    public void launReadContactsActivity () {
        Intent intent  = new Intent();
        intent.setClass(this, ReadContactsActivity.class);
        startActivity(intent);
    }

    public void launchNotificationActivity() {
        Intent intent = new Intent();
        intent.setClass(this, NotificationActivity.class);
        startActivity(intent);
    }

    public void launchSMSTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SMSTestActivity.class);
        startActivity(intent);
    }

    public void launchChoosePicTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this,ChoosePicTestActivity.class);
        startActivity(intent);
    }

    public void launchBlueToothTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, BlueToothTestActivity.class);
        startActivity(intent);
    }

    public void launchMyServiceActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MyServiceActivity.class);
        startActivity(intent);
    }

    public void launchServiceBestPractice() {
        Intent intent = new Intent();
        intent.setClass(this, ServiceBestPracticeActivity.class);
        startActivity(intent);
    }

    public void launchWebviewTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, WebViewTestActivity.class);
        startActivity(intent);
    }

    public void launchHttpConnectionActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HttpConnectionActivity.class);
        startActivity(intent);
    }

    public void launchLocationTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LocationTestActivity.class) ;
        startActivity(intent);
    }

    public void launchLightSensorTestActivity () {
        Intent intent = new Intent();
        intent.setClass(this, LightSensorTestActivity.class);
        startActivity(intent);
    }

    public void launchAccelerometerSensorTestActivity () {
        Intent intent = new Intent();
        intent.setClass(this, AccelerometerSensorTestActivity.class);
        startActivity(intent);
    }

    public void launchCompassTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, CompassTestActivity.class);
        startActivity(intent);
    }

    public void launchFilterFileActivity() {
        Intent intent = new Intent();
        intent.setClass(this, FilterFileActivity.class);
        startActivity(intent);
    }

    public void launchLandscapePortraitActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LandscapePortraitActivity.class);
        startActivity(intent);
    }

    private void launchHandlerTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, HandlerTestActivity.class);
        startActivity(intent);
    }

    private void launchJSONTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, JSONTestActivity.class);
        startActivity(intent);
    }

    private void launchUsbTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, UsbTestActivity.class);
        startActivity(intent);
    }

    private void launchSetImageManualActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SetImageManualActivity.class);
        startActivity(intent);
    }

    private void launchConnectUsbActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ConnectUsbActivity.class);
        startActivity(intent);
    }

    private void launchSeekBarTestActivity () {
        Intent intent = new Intent();
        intent.setClass(this, SeekBarTestActivity.class);
        startActivity(intent);
    }

    private void launchWiFiHotSpotActivity () {
        Intent intent = new Intent();
        intent.setClass(this, WiFiHotSpotActivity.class);
        startActivity(intent);
    }


    private void launchMotionEventTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MotionEventTestActivity.class);
        startActivity(intent);
    }

    private void launchUnderLineTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, UnderLineTestActivity.class);
        startActivity(intent);
    }

    private void launchQRCodeTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, QRCodeTestActivity.class);
        startActivity(intent);
    }

    private void launchPhoneInforActivity() {
        Intent intent = new Intent();
        intent.setClass(this, PhoneInfoActivity.class);
        startActivity(intent);
    }

    private void launchDialogTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this,DialogTestActivity.class);
        startActivity(intent);
    }

    private void launchNetClientActivity() {
        Intent intent = new Intent();
        intent.setClass(this, NetClientActivity.class);
        startActivity(intent);
    }

    private void launchNetServerActivity() {
        Intent intent = new Intent();
        intent.setClass(this, NetServerActivity.class);
        startActivity(intent);
    }

    private void launchGetMACActivity() {
        Intent intent = new Intent();
        intent.setClass(this, GetMacActivity.class);
        startActivity(intent);
    }

    private void launPermissionActivity() {
        Intent intent = new Intent();
        intent.setClass(this, PermissionActivity.class);
        startActivity(intent);
    }

    private void launchMoveViewActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MoveViewActivity.class);
        startActivity(intent);
    }

    private void launchAnimatorActivity() {
        Intent intent = new Intent();
        intent.setClass(this,AnimatorActivity.class);
        startActivity(intent);
    }

    private void launchKotlinActivity() {
        Intent intent = new Intent();
        intent.setClass(this,KotlinTest.class);
        startActivity(intent);
    }

    private void launchAudioRecordActivity() {
        Intent intent = new Intent();
        intent.setClass(this,AudioRecordActivity.class);
        startActivity(intent);
    }

    private void launchModeTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LaunchModeTestActivity.class);
        startActivity(intent);
    }

    private void launchScrollContainList() {
        Intent intent = new Intent();
        intent.setClass(this, ScrollContainListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_aidl_test)
    public void launchAidlTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, AidlTestActivity.class);
        startActivity(intent);
    }

    public void launchAsyncTaskTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, AsyncTaskTestActivity.class);
        startActivity(intent);
    }

    public void launchOkHttpTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, OkHttpTestActivity.class);
        startActivity(intent);
    }

    public void launchViewTouchEventTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ViewTouchEventTestActivity.class);
        startActivity(intent);
    }

    private void launchMessengerTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MessengerTestActivity.class);
        startActivity(intent);
    }

    private void launchBinderTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, BinderTestActivity.class);
        startActivity(intent);
    }

    private void launchPopupMenuTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, PopUpMenuTestActivity.class);
        startActivity(intent);
    }

    private void launchAnnotationTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, ButterknifeTestActivity.class);
        startActivity(intent);
    }

    private void launchSurfaceViewTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SurfaceViewTestActivty.class);
        startActivity(intent);
    }

    private void launchRecyclerViewTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, RecycleViewTest.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("extra_test", "test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String test = savedInstanceState.getString("extra_test");
        Log.d(TAG, "[onRestoreInstanceState]restore extra_test:" + test);
    }

    class Nihao {
        int i;
        int j;

        Nihao(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static class Nihao2 {
        int x;
        int y;
        Nihao2(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}


