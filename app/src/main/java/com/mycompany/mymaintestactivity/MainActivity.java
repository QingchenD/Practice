package com.mycompany.mymaintestactivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.MessageQueue;
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
import com.mycompany.eventbus.EventBusTestActivity;
import com.mycompany.fragment.FragmentPracticeActivity;
import com.mycompany.fragment.FragmentTestActivity;
import com.mycompany.Messenger.MessengerTestActivity;
import com.mycompany.aidlTest.AidlTestActivity;
import com.mycompany.annotation.ButterknifeTestActivity;
import com.mycompany.broadcastbestpractice.LoginActiviy;
import com.mycompany.hencoder_practice.HencoderActivity;
import com.mycompany.launchmodeTest.LaunchModeTestActivity;
import com.mycompany.menu.PopUpMenuTestActivity;
import com.mycompany.net.NetClientActivity;
import com.mycompany.net.NetServerActivity;
import com.mycompany.net.OkHttpTestActivity;
import com.mycompany.recycleview.RecycleViewTest;
import com.mycompany.retrofit.RetrofitTestActivity;
import com.mycompany.rxjava.RxJavaTestActivity;
import com.mycompany.surface.SurfaceViewTestActivty;
import com.mycompany.threadLocal.ThreadLocalTestActivity;
import com.mycompany.time.AlarmManagerTestActivity;
import com.mycompany.view.SurfaceViewDemoActivity;

import java.security.Security;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends Activity {
    private final String TAG = "MainActivity";

    public final int ACTIVITY_REQESET_CODE = 2;

    TextView btn2BackText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        btn2BackText = (TextView) findViewById(R.id.text_btn2_result);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " xdpi:" + xdpi + " ydpi:" + ydpi);

        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        int maxheapSize = manager.getLargeMemoryClass();

        Log.i(TAG, "mydebug  heapsize:" + heapSize + " maxHeapSize:" + maxheapSize);

        Runtime runtime = Runtime.getRuntime();
        Log.i(TAG, " totalMemory:" + runtime.totalMemory() + " maxMem:" + runtime.maxMemory()
         + " freeMem:" + runtime.freeMemory());

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

        // 拿到主线程的MessageQueue
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {

            @Override
            public boolean queueIdle() {
                //  在这里去处理你想延时加载的东西
                //delayLoad();

                // 最后返回false，后续不用再监听了。
                return false;
            }
        });

        getMaxMemoryInfo();
        getMemoryInfo();
        getPath();
    }

    private void getMaxMemoryInfo(){
        Runtime rt = Runtime.getRuntime();
        long maxMemory = rt.maxMemory();
        Log.d("MaxMemory:", Long.toString(maxMemory/(1024*1024)));
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        Log.d("MemoryClass:", Long.toString(activityManager.getMemoryClass()));
        Log.d("LargeMemoryClass:", Long.toString(activityManager.getLargeMemoryClass()));
    }

    private void getMemoryInfo() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        manager.getMemoryInfo(info);
        Log.e("Memory","系统总内存:"+(info.totalMem / (1024*1024))+"M");
        Log.e("Memory","系统剩余内存:"+(info.availMem / (1024*1024))+"M");
        Log.e("Memory","系统是否处于低内存运行："+info.lowMemory );
        Log.e("Memory","系统剩余内存低于"+( info.threshold  / (1024*1024))+"M时为低内存运行");
    }

    private void getPath() {
        //内部存储
        System.out.println("Environment.getDataDirector: " + Environment.getDataDirectory());
        System.out.println("getFilesDir().getAbsolutePath():" + getFilesDir().getAbsolutePath());
        System.out.println("getCacheDir().getAbsolutePath():" + getCacheDir().getAbsolutePath());
        System.out.println("getDir():" + getDir("", MODE_PRIVATE));

        //external   storage
        System.out.println("Environment.getExternalStorageDirectory():" + Environment.getExternalStorageDirectory().getAbsolutePath());
        System.out.println("Environment.getExternalStoragePublicDirectory():" + Environment.getExternalStoragePublicDirectory("").getAbsolutePath());
        System.out.println("getExternalFilesDir():" + getExternalFilesDir("").getAbsolutePath());
        System.out.println("getExternalCacheDir():" + getExternalCacheDir().getAbsolutePath());
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
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "Mydebug" + "onWindowFocusChanged()");
        super.onWindowFocusChanged(hasFocus);
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


                default:
                    break;
            }
        }
    }


    //OnClick function
    public void onBtn1Click(View v) {
        launchActivityone();
    }

    public void onBtn2Click(View v) {
        launchSecondActivity();
    }

    public void onStartActivityForResultClick(View v) {
        launchActivityTwo();
    }

    public void onSingletonClick(View v) {
        launchSingletonActivity();
    }

    public void onThreadSyncClick(View v) {
        launchThreadSyncActivity();
    }

    public void onBtn5Click(View v) {
        launchPopupWindowActivity();
    }

    public void onBtn6Click(View v) {
        launchXySeriesActivity();
    }

    public void onBtn7Click(View v) {
        launchPlayAudioActivity();
    }

    public void onPlayVideoClick(View v) {
        launchPlayVideoActivity();
    }

    public void onBtn8Click(View v) {
        launchOpenFileActivity();
    }

    public void onBtn9Click(View v) {
        launchVirbrateActivity();
    }

    public void onBtn10Click(View v) {
        launchServiceDemoActivity();
    }

    public void onBtn11Click(View v) {
        launchTextureViewActivity();
    }

    public void onBtn12Click(View v) {
        launchShowImgActivity();
    }

    public void onBtn13Click(View v) {
        launchScanFileActivity();
    }

    public void onBtn14Click(View v) {
        launchDateFormatActivity();
    }

    public void onBtn15Click(View v) {
        launchMessageActivity();
    }

    public void onBtn16Click(View v) {
        launchFragmentActivity();
    }

    public void onBtn17Click(View v) {
        launchFragmentPracticeActivity();
    }

    public void onBtn18Click(View v) {
        launchNetworkChangeActivity();
    }

    public void onBtn19Click(View v) {
        launchSendBraodcastActivity();
    }

    public void onBtn20Click(View v) {
        launchBroadcastPracticeActivity();
    }

    public void onBtn21Click(View v) {
        launchSaveDataActivity();
    }

    public void onBtn22Click(View v) {
        launchYm2IPTestActivity();
    }

    public void onBtn23Click(View v) {
        launchSharePreferenceActivity();
    }

    public void onBtn24Click(View v) {
        launchSQLiteDatabaseActivity();
    }

    public void onBtn25Click(View v) {
        launReadContactsActivity();
    }

    public void onBtn26Click(View v) {
        launchNotificationActivity();
    }

    public void onBtn27Click(View v) {
        launchSMSTestActivity();
    }

    public void onBtn28Click(View v) {
        launchChoosePicTestActivity();
    }

    public void onBtn29Click(View v) {
        launchBlueToothTestActivity();
    }

    public void onBtn30Click(View v) {
        launchMyServiceActivity();
    }

    public void onBtn31Click(View v) {
        launchServiceBestPractice();
    }

    public void onBtn32Click(View v) {
        launchWebviewTestActivity();
    }

    public void onBtn33Click(View v) {
        launchHttpConnectionActivity();
    }

    public void onBtn34Click(View v) {
        launchLocationTestActivity();
    }

    public void onBtn35Click(View v) {
        launchLightSensorTestActivity();
    }

    public void onBtn36Click(View v) {
        launchAccelerometerSensorTestActivity();
    }

    public void onBtn37Click(View v) {
        launchCompassTestActivity();
    }

    public void onBtn38Click(View v) {
        launchFilterFileActivity();
    }

    public void onBtn39Click(View v) {
        launchLandscapePortraitActivity();
    }

    public void onBtn40Click(View v) {
        launchHandlerTestActivity();
    }

    public void onBtn41Click(View v) {
        launchJSONTestActivity();
    }

    public void onBtn42Click(View v) {
        launchSetImageManualActivity();
    }

    public void onBtn43Click(View v) {
        launchUsbTestActivity();
    }

    public void onBtn44Click(View v) {
        launchConnectUsbActivity();
    }

    public void onBtn45Click(View v) {
        launchSeekBarTestActivity();
    }

    public void onBtn46Click(View v) {
        launchWiFiHotSpotActivity();
    }

    public void onBtn47Click(View v) {
        launchMotionEventTestActivity();
    }

    public void onBtn48Click(View v) {
        launchUnderLineTestActivity();
    }

    public void onBtn49Click(View v) {
        launchQRCodeTestActivity();
    }

    public void onBtn50Click(View v) {
        launchPhoneInforActivity();
    }

    public void onBtn51Click(View v) {
        launchDialogTestActivity();
    }

    public void onBtn52Click(View v) {
        launchNetClientActivity();
    }

    public void onBtn53Click(View v) {
        launchNetServerActivity();
    }

    public void onBtn54Click(View v) {
        launchGetMACActivity();
    }

    public void onBtn55Click(View v) {
        launPermissionActivity();
    }

    public void onBtn56Click(View v) {
        launchMoveViewActivity();
    }

    public void onBtn57Click(View v) {
        launchAnimatorActivity();
    }

    public void onBtn58Click(View v) {
        launchKotlinActivity();
    }

    public void onBtn59Click(View v) {
        launchAudioRecordActivity();
    }

    public void onBtn60Click(View v) {
        launchModeTestActivity();
    }

    public void onBtn61Click(View v) {
        launchScrollContainList();
    }

    public void onBtn62Click(View v) {
        launchAidlTestActivity();
    }

    public void onBtn63Click(View v) {
        launchAsyncTaskTestActivity();
    }

    public void onBtn64Click(View v) {
        launchOkHttpTestActivity();
    }

    public void onBtn65Click(View v) {
        launchViewTouchEventTestActivity();
    }

    public void onBtn66Click(View v) {
        launchMessengerTestActivity();
    }

    public void onBtn67Click(View v) {
        launchBinderTestActivity();
    }

    public void onBtn68Click(View v) {
        launchPopupMenuTestActivity();
    }

    public void onBtn69Click(View v) {
        launchAnnotationTestActivity();
    }

    public void onBtn70Click(View v) {
        launchSurfaceViewTestActivity();
    }

    public void onBtn71Click(View v) {
        launchRecyclerViewTestActivity();
    }

    public void onBtn72Click(View v) {
        launchEventBuSTestActivity();
    }

    public void onBtn73Click(View v) {
        launchRxJavaTestActivity();
    }

    public void onThreadLocalClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, ThreadLocalTestActivity.class);
        startActivity(intent);
    }

    //75
    public void onRetrofitClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, RetrofitTestActivity.class);
        startActivity(intent);
    }

    //76
    public void onImportMiView (View v) {
        Intent intent = new Intent();
        intent.setClass(this, MiViewTestActivity.class);
        startActivity(intent);
    }

    //77
    public void onHencoderClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, HencoderActivity.class);
        startActivity(intent);
    }

    //78
    public void onAlarmManagerClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, AlarmManagerTestActivity.class);
        startActivity(intent);
    }

    //79
    public void onSurfaceViewDemoClick(View v) {
        Intent intent = new Intent();
        intent.setClass(this, SurfaceViewDemoActivity.class);
        startActivity(intent);
    }



    //launch function
    private void launchActivityone() {
//        Intent intent = new Intent(this, StartOneActitity.class);
//        intent.putExtra("time", System.currentTimeMillis());
//        startActivity(intent);

        Intent intent = new Intent("com.mycompany.mymaintestactivity.ACTION_START");
        intent.addCategory("android.intent.category.MY_CATEGORY");
        Bundle bundle = new Bundle();
        bundle.putString("one", "This may be the first start!!!");
        intent.putExtras(bundle);
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

    private void launchEventBuSTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, EventBusTestActivity.class);
        startActivity(intent);
    }

    private void launchRxJavaTestActivity() {
        Intent intent = new Intent();
        intent.setClass(this, RxJavaTestActivity.class);
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


