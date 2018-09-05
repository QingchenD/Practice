package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Qingweid on 2016/6/17.
 */
public class LandscapePortraitActivity extends Activity {

    private Button btn1;
    private Button btn2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landscape_portrait);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        tv1 = (TextView) findViewById(R.id.text1);
        tv2 = (TextView) findViewById(R.id.text2);
        tv3 = (TextView) findViewById(R.id.text3);

        btn1.setTextColor(Color.BLUE);
        btn2.setTextColor(Color.BLUE);

        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.RED);
        tv3.setTextColor(Color.RED);

    }
}
