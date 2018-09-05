package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Qingweid on 2016/8/29.
 */
public class MotionEventTestActivity extends Activity {

    final String TAG = getClass().getSimpleName();

    Button btnMotionTest;
    MyView btnMyView;

    EditText inputPhone;
    Button btnChange;
    TextView tvResult;

    EditText inputPwd;
    Button btnCheckPwd;
    TextView tvPwdCheckResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motion_event_test_layout);

        btnMotionTest = (Button) findViewById(R.id.motion_tst);
        btnMyView = (MyView) findViewById(R.id.myview);

        inputPhone = (EditText) findViewById(R.id.input_phone_number);
        btnChange = (Button) findViewById(R.id.change_number);
        tvResult = (TextView) findViewById(R.id.change_result);

        inputPwd = (EditText) findViewById(R.id.input_pwd);
        btnCheckPwd = (Button) findViewById(R.id.pwd_check);
        tvPwdCheckResult = (TextView) findViewById(R.id.pwd_check_result);

        MyTouchListener listener = new MyTouchListener();
        btnMotionTest.setOnTouchListener(listener);

        MyViewTouchListener l = new MyViewTouchListener();
        btnMyView.setOnTouchListener(l);
    }


    public void onChange(View v) {
        String phone = "+" + inputPhone.getText().toString();
        String twoLetterCountryCode = "CN";
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phone, twoLetterCountryCode);
//            // 0936 111 222

            String withSpaceSignStr = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
            System.out.println(" space number:" + withSpaceSignStr);
            //            // trim spaces
            String withoutSpaceSignStr = withSpaceSignStr.replace("\\s", "");
            System.out.println(" no space number:" + withoutSpaceSignStr);

            numberProto = phoneUtil.parse(phone, twoLetterCountryCode);
            System.out.println(" **** phone:" +  Long.toString(numberProto.getNationalNumber()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final static boolean isValidPassword(CharSequence target) {
        // final String PASSWORD_PATTERN =
        // "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
        final String PASSWORD_PATTERN = "[A-Za-z0-9]{8,20}";

        try {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(target);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }

    public void onPwdCheck(View v) {
        String pwd = inputPwd.getText().toString();
        boolean bValid = isValidPassword(pwd);

        if (bValid) {
            tvPwdCheckResult.setText("Valid");
        } else {
            tvPwdCheckResult.setText("Invalid");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            System.out.println("[Mydebug] Button***" + TAG + " action:" + event.getAction());
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_CANCEL:
                    break;

                case MotionEvent.ACTION_MOVE:
                    break;
            }
            return false;
        }
    }

    class MyViewTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            System.out.println("[Mydebug]  MyView+++" + TAG + " action:" + event.getAction());
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_CANCEL:
                    break;

                case MotionEvent.ACTION_MOVE:
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_CANCEL:
                break;

            case MotionEvent.ACTION_MOVE:
                break;
        }

        return true;
    }
}
