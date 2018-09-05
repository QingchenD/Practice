package com.mycompany.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2016/3/21.
 */
public class AnotherRightFragment extends Fragment {

    private static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.another_right_fragment, container,false);
        Button btn = (Button) view.findViewById(R.id.btn_right);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                LeftFragment fragment = (LeftFragment) manager.findFragmentById(R.id.left_fragment);
                TextView tv = (TextView) fragment.getView().findViewById(R.id.left_tv);
                tv.setText("Right has clicked!");
            }
        });
        return view;
    }


}
