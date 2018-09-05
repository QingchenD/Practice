package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Intents;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;


/**
 * Created by Qingweid on 2016/9/26.
 */
public class QRCodeTestActivity extends Activity {

    private static final int IMG_SIZE = 256;
    private static final int sBgColor = Color.parseColor("#F9F9F9");

    private final int REQUEST_CODE_SCAN = 0;

    private Button btnScanQRCode;
    private TextView tvQRCode;
    private Button btnCreateQRCode;
    private ImageView imgQRCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code_layout);

        btnScanQRCode = (Button) findViewById(R.id.btn_scan_qrcode);
        tvQRCode = (TextView) findViewById(R.id.tv_qrcode);
        btnCreateQRCode = (Button) findViewById(R.id.btn_create_qrcode);
        imgQRCode = (ImageView) findViewById(R.id.img_qrcode);

        MyOnClickListener listener = new MyOnClickListener();

        btnScanQRCode.setOnClickListener(listener);
        btnCreateQRCode.setOnClickListener(listener);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_scan_qrcode:
                    scanQRCode();
                    break;

                case R.id.btn_create_qrcode:
                    createQRCode();
                    break;

                default:
                    break;
            }
        }
    }

    private void scanQRCode() {
        startActivityForResult(new Intent(QRCodeTestActivity.this,
                CaptureActivity.class), REQUEST_CODE_SCAN);

    }



    private void createQRCode() {
        String str = "123456";
        Bitmap bmp = null ;
        try {
            bmp = generateQrCode(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bmp != null) {
            imgQRCode.setImageBitmap(bmp);
        }
    }

    public static Bitmap generateQrCode(String myCodeText) throws WriterException {
        Hashtable<EncodeHintType, Object> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hintMap.put(EncodeHintType.MARGIN, 0);
        QRCodeWriter writer = new QRCodeWriter();

        BitMatrix bm = writer.encode(myCodeText, BarcodeFormat.QR_CODE, IMG_SIZE, IMG_SIZE, hintMap);
        Bitmap bmp = Bitmap.createBitmap(IMG_SIZE, IMG_SIZE, Bitmap.Config.ARGB_8888);

        for (int i = 0; i < IMG_SIZE; i++) {
            for (int j = 0; j < IMG_SIZE; j++) {
                bmp.setPixel(i, j, bm.get(i, j) ? Color.BLACK : sBgColor);
            }
        }
        return bmp;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE_SCAN == requestCode) {
            if (RESULT_OK == resultCode) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString(Intents.Scan.RESULT);
                tvQRCode.setText(result);
            }
        }
    }
}
