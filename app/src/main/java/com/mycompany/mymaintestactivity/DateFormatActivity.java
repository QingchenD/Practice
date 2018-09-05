package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Qingweid on 2016/2/1.
 */
public class DateFormatActivity extends Activity {

    TextView formatText1;
    TextView dateText1;
    TextView formatText2;
    TextView dateText2;
    TextView formatText3;
    TextView dateText3;
    TextView formatText4;
    TextView dateText4;
    TextView formatText5;
    TextView dateText5;
    TextView formatText6;
    TextView dateText6;
    TextView formatText7;
    TextView dateText7;
    TextView formatText8;
    TextView dateText8;
    TextView formatText9;
    TextView dateText9;
    TextView formatText10;
    TextView dateText10;
    TextView formatText11;
    TextView dateText11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_format_layout);

        formatText1 = (TextView) findViewById(R.id.txt_format_1);
        dateText1 = (TextView) findViewById(R.id.txt_date_1);
        formatText2 = (TextView) findViewById(R.id.txt_format_2);
        dateText2 = (TextView) findViewById(R.id.txt_date_2);
        formatText3 = (TextView) findViewById(R.id.txt_format_3);
        dateText3 = (TextView) findViewById(R.id.txt_date_3);
        formatText4 = (TextView) findViewById(R.id.txt_format_4);
        dateText4 = (TextView) findViewById(R.id.txt_date_4);
        formatText5 = (TextView) findViewById(R.id.txt_format_5);
        dateText5 = (TextView) findViewById(R.id.txt_date_5);
        formatText6 = (TextView) findViewById(R.id.txt_format_6);
        dateText6 = (TextView) findViewById(R.id.txt_date_6);
        formatText7 = (TextView) findViewById(R.id.txt_format_7);
        dateText7 = (TextView) findViewById(R.id.txt_date_7);
        formatText8 = (TextView) findViewById(R.id.txt_format_8);
        dateText8 = (TextView) findViewById(R.id.txt_date_8);
        formatText9 = (TextView) findViewById(R.id.txt_format_9);
        dateText9 = (TextView) findViewById(R.id.txt_date_9);
        formatText10 = (TextView) findViewById(R.id.txt_format_10);
        dateText10 = (TextView) findViewById(R.id.txt_date_10);
        formatText11 = (TextView) findViewById(R.id.txt_format_11);
        dateText11 = (TextView) findViewById(R.id.txt_date_11);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Date date = new Date();

        String strFormat = formatText1.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat(strFormat, Locale.getDefault());
        dateText1.setText(format.format(date));

        strFormat = formatText2.getText().toString();
        format.applyPattern(strFormat);
        dateText2.setText(format.format(date));

        strFormat = formatText3.getText().toString();
        format.applyPattern(strFormat);
        dateText3.setText(format.format(date));

        strFormat = formatText4.getText().toString();
        format.applyPattern(strFormat);
        dateText4.setText(format.format(date));

        strFormat = formatText5.getText().toString();
        format.applyPattern(strFormat);
        dateText5.setText(format.format(date));

        strFormat = formatText6.getText().toString();
        format.applyPattern(strFormat);
        dateText6.setText(format.format(date));

        strFormat = formatText7.getText().toString();
        format.applyPattern(strFormat);
        dateText7.setText(format.format(date));

        strFormat = formatText8.getText().toString();
        format.applyPattern(strFormat);
        dateText8.setText(format.format(date));

        strFormat = formatText9.getText().toString();
        format.applyPattern(strFormat);
        dateText9.setText(format.format(date));

        strFormat = formatText10.getText().toString();
        format.applyPattern(strFormat);
        dateText10.setText(format.format(date));

        strFormat = formatText11.getText().toString();
        format.applyPattern(strFormat);
        dateText11.setText(format.format(date));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
