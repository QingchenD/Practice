package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/4/22.
 */
public class SharedPreferencesActivity extends Activity {

    private Button btnSaveSharPreference;
    private Button btnRestoreData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_preference_layout);

        btnSaveSharPreference = (Button) findViewById(R.id.btn_save_share_preference);
        btnRestoreData = (Button) findViewById(R.id.btn_restore_data);

        btnSaveSharPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.commit();
            }
        });

        btnRestoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("name","");
                int age = pref.getInt("age",0);
                boolean married = pref.getBoolean("married", false);
                System.out.println("[Mydebug] " + " Restore shared preference data " + " name:" + name + " age:" + age + " married:" +married );

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
