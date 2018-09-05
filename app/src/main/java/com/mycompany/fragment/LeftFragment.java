package com.mycompany.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2016/3/18.
 */
public class LeftFragment extends Fragment {

    private final String TAG = "LeftFragment";

    TextView tv;

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach() start ++++");
        super.onAttach(context);
        Log.i(TAG, "onAttach() end   ----");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate() start ++++");
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.i(TAG, "onCreate() end   ----");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() start ++++");

        View view = inflater.inflate(R.layout.left_fragment, container, false);
        tv = (TextView) view.findViewById(R.id.left_tv);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated() start ++++");
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated() end   ----");
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart() start ++++");
        super.onStart();
        Log.i(TAG, "onStart() end  ----");
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume() start ++++");
        super.onResume();
        Log.i(TAG, "onResume() end   ----");
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause() start ++++");
        super.onPause();
        Log.i(TAG, "onPause()  end  ----");
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop() start ++++");
        super.onStop();
        Log.i(TAG, "onStop() end   ----");
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView() start ++++");
        super.onDestroyView();
        Log.i(TAG, "onDestroyView() end   ----");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy() start ++++");
        super.onDestroy();
        Log.i(TAG, "onDestroy() end   ----");
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach() start ++++");
        super.onDetach();
        Log.i(TAG, "onDetach() end  ----");
    }
}
