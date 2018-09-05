package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Qingweid on 2016/1/29.
 */
public class ScanFileActivity  extends Activity {

    TextView textFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_file_layout);

        textFile = (TextView) findViewById(R.id.txt_file_name);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onScan(View v) {

        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = textFile.getHint().toString();

        String fileAbsolutePath = absolutePath + "/Home8/" + fileName;

        File file = new File(fileAbsolutePath ) ;

        mediaScan(file);

        if (file.exists()) {
            System.out.println("[Mydebug] " + " file:" + fileAbsolutePath + " is exist!!");
        } else {
            System.out.println("[Mydebug] " + " file:" + fileAbsolutePath + " is not exist!!");
        }
    }

    public void mediaScan(File file) {
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = textFile.getText().toString();

        String[] paths = new String[] {
                absolutePath + "/Home8/" + "rec01272016113541.mp4",
                absolutePath + "/Home8/" + "rec01282016204026.mp4",
                absolutePath + "/Home8/" + "rec01282016205848.mp4",
                absolutePath + "/Home8/" + "rec01292016083021.mp4",
                absolutePath + "/Home8/" + "rec01292016083443.mp4",
                absolutePath + "/Home8/" + "rec01292016084545.mp4",
                absolutePath + "/Home8/" + "rec01292016085946.mp4",
                absolutePath + "/Home8/" + "rec01292016090736.mp4",
                absolutePath + "/Home8/" + "rec01292016091534.mp4",
                absolutePath + "/Home8/" + "rec01292016092937.mp4",
                absolutePath + "/Home8/" + "rec01292016093415.mp4",
                absolutePath + "/Home8/" + "rec01292016094115.mp4",
                absolutePath + "/Home8/" + "rec01292016095540.mp4",
                absolutePath + "/Home8/" + "rec01292016101250.mp4",
                absolutePath + "/Home8/" + "rec01292016102021.mp4",
                absolutePath + "/Home8/" + "rec01292016103253.mp4",
                absolutePath + "/Home8/" + "rec01292016105449.mp4",
                absolutePath + "/Home8/" + "rec01292016110430.mp4",
                absolutePath + "/Home8/" + "rec01292016111545.mp4",
                absolutePath + "/Home8/" + "rec01292016112641.mp4",

        };
        MediaScannerConnection.scanFile(this,
                paths, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.v("MediaScanWork", "file " + path
                                + " was scanned seccessfully: " + uri);
                    }
                });

        System.out.println("[Mydebug] " + " one file :" + file.getAbsolutePath());

        MediaScannerConnection.scanFile(this,
                new String[]{file.getAbsolutePath(), ""}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        Log.v("MediaScanWork", "file " + path
                                + " was scanned seccessfully: " + uri);
                    }
                });
    }

}
