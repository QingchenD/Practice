package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Scroller;

import butterknife.Bind;


/**
 * Created by Qingweid on 2017/4/10.
 */

public class MoveViewActivity extends Activity implements View.OnTouchListener{
    private final String TAG = "MoveViewActivity";

//    @Bind(R.id.btn_move_1)
    Button btnMove1;

    Scroller scroller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.move_view_layout);

        scroller = new Scroller(this);

        btnMove1 = (Button) findViewById(R.id.btn_move_1);
        btnMove1.setOnTouchListener(this);
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


    private void smoothScrollTo(int destX , int destY) {
        int scrollX = btnMove1.getScrollX();
        int deltaX = destX - scrollX;
        scroller.startScroll(scrollX, 0 , deltaX, 0, 1000);
        btnMove1.invalidate();
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Log.i(TAG, "onTouch() " + event.getAction() );

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_UP:
                smoothScrollTo(100, 0);
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }
        return false;
    }
}
