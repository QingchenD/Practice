package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/10/18.
 */

public class DialogTestActivity extends Activity implements View.OnClickListener {

    private Button btnShowDialog;
    private Button btnDialogWithLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tst_layout);

        btnShowDialog = (Button) findViewById(R.id.btn_show_dialog);
        btnDialogWithLayout = (Button) findViewById(R.id.btn_dialog_layout);
        btnShowDialog.setOnClickListener(this);
        btnDialogWithLayout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_dialog:
                showDialog();
                break;

            case R.id.btn_dialog_layout:
                ShowDialogWithLayout();
                break;

            default:
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setMessage("Hello!!!");
        builder.setPositiveButton("OK",null);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.circle_corner);
        dialog.show();
    }

    private void ShowDialogWithLayout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setMessage(" Hello!!");
        builder.setPositiveButton("Yes",null);
        builder.setNegativeButton("No",null);

        try {
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setContentView(R.layout.dialog_layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyDialog extends Dialog {

        private int default_width = 160; //默认宽度
        private int default_height = 120;//默认高度

        private MyDialog(Context context, int width, int height, View layout, int style) {
            super(context, style);
            setContentView(layout);
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
        }

        public MyDialog(Context context, View layout, int style) {
            this(context, 160, 120, layout, style);
        }
    }
}
