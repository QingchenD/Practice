package com.mycompany.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mycompany.mymaintestactivity.MainActivity;
import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2016/3/28.
 */
public class LoginActiviy extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText accountEdit;
    private EditText passwrodEdit;
    private Button btnLogin;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwrodEdit = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.login);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password", false);

        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwrodEdit.setText(password);
            rememberPass.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwrodEdit.getText().toString();

                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                    } else {
                        editor.clear();
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActiviy.this, BroadcastPracticeMain.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActiviy.this , "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
