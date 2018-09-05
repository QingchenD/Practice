package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Qingweid on 2015/12/10.
 */
public class SingeltonTestActivity extends Activity {

    TextView textSingleton;
    Button singletonBtnGet;
    Button singletonBtnSet;
    EditText editSingleton;
    Button btnBew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.singleton_test_layout);

        singletonBtnGet = (Button) findViewById(R.id.btn_singleton_get);
        textSingleton = (TextView) findViewById(R.id.text_singleton);
        singletonBtnSet = (Button) findViewById(R.id.btn_singleton_set);
        editSingleton = (EditText) findViewById(R.id.edit_singleton);
        btnBew = (Button) findViewById(R.id.btn_new);

        MyOnClickListener listener = new MyOnClickListener();
        singletonBtnGet.setOnClickListener(listener);
        singletonBtnSet.setOnClickListener(listener);
        btnBew.setOnClickListener(listener);

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

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.btn_singleton_get:
                    singletonTestGet();
                    break;

                case R.id.btn_singleton_set:
                    singletonTestSet();
                    break;

                case R.id.btn_new:
                    newOneInstance();
                    break;

                default:
                    break;
            }
        }
    }

    private void singletonTestGet() {
        SingletonDemoClass temp = SingletonDemoClass.getInstance();
        String oldstr = temp.getStr();
        textSingleton.setText(oldstr);
    }

    private void singletonTestSet() {
        SingletonDemoClass temp = SingletonDemoClass.getInstance();
        String str = editSingleton.getText().toString();
        temp.setStr(str);
    }

    private void newOneInstance () {
        SingletonDemoClass temp = new SingletonDemoClass();

        temp.setStr("nihao");
    }


    public void OnFinish(View view) {
        finish();
    }
}