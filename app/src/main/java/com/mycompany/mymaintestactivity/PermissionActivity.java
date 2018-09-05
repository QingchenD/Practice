package com.mycompany.mymaintestactivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Qingweid on 2017/1/20.
 */

public class PermissionActivity extends Activity {

    private final String TAG = "PermissionActivity" ;
    private final int MY_PERMISSION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPerMission();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getPerMission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {// 没有权限。
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CHANGE_NETWORK_STATE)) {
//                    Log.i(TAG, " shouldShowRequestPermissionRationale ");
//                } else {
//                    Log.i(TAG, " requestPermissions  READ_CONTACTS ");
//                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CHANGE_NETWORK_STATE}, MY_PERMISSION);
//                }
//            }
        }

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSION: {
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Log.i(TAG, " GRANTED  CHANGE_NETWORK_STATE ");
//                } else {
//                    Log.i(TAG, " DENY  CHANGE_NETWORK_STATE ");
//                }
//                return;
//            }
//        }
//    }
}
