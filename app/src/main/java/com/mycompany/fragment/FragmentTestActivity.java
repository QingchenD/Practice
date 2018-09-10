package com.mycompany.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.mymaintestactivity.RightFragment;

/**
 * Created by Qingweid on 2016/3/18.
 */
public class FragmentTestActivity extends Activity implements OnClickListener {

    private String TAG = "FragmentTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,  "onCreate() start ");

        super.onCreate(savedInstanceState);
        Log.i(TAG,  "onCreate() before setContentView()");
        setContentView(R.layout.fragment_activity_layout);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Log.i(TAG,  "onCreate() end");
     }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                AnotherRightFragment fragment = new AnotherRightFragment();
                Log.d("FragmentTestActivity", "1 tag:" + fragment.getTag());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragmentManager.findFragmentByTag("Another") != null) {
                    transaction.remove(fragmentManager.findFragmentByTag("Another"));
                }
                transaction.replace(R.id.right_layout, fragment, "Another");
                //transaction.add(R.id.right_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.d("FragmentTestActivity", "2 tag:" + fragment.getTag());
                break;
            }

            case R.id.button2: {
                RightFragment fragment = new RightFragment();
                Log.d("FragmentTestActivity", "1 tag:" + fragment.getTag());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.right_layout, fragment, "Right");
                //transaction.add(R.id.right_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.d("FragmentTestActivity", "2 tag:" + fragment.getTag());

                break;
            }

            default:
                break;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onPostCreate() start");
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate() end ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState() start");
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState() end");
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart() start");
        super.onStart();
        Log.i(TAG, "onStart() end");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume() start");
        super.onResume();
        Log.i(TAG, "onResume() end");
    }

    @Override
    protected void onPostResume() {
        Log.i(TAG, "onPostResume() start");
        super.onPostResume();
        Log.i(TAG, "onPostResume() end");
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause() start");
        super.onPause();
        Log.i(TAG, "onPause() end");
    }


    @Override
    protected void onStop() {
        Log.i(TAG, "onStop() start");
        super.onStop();
        Log.i(TAG, "onStop() end");
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy() start");
        super.onDestroy();
        Log.i(TAG, "onDestroy() end");
    }
}
