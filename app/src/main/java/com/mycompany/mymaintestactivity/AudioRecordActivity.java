package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Qingweid on 2017/9/14.
 */

public class AudioRecordActivity extends Activity implements View.OnClickListener {
    Button startRecordingButton, stopRecordingButton;//开始录音、停止录音
    File recordingFile;//储存AudioRecord录下来的文件
    boolean isRecording = false; //true表示正在录音
    AudioRecord audioRecord=null;
    File parent=null;//文件目录
    int bufferSize=0;//最小缓冲区大小
    int sampleRateInHz = 11025;//采样率
    int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO; //单声道
    int audioFormat = AudioFormat.ENCODING_PCM_16BIT; //量化位数
    String TAG="AudioRecord";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.record_audio_layout);
        init();
        initListener();
    }

    //初始化
    public void init() {
        startRecordingButton = (Button)findViewById(R.id.StartRecording);
        stopRecordingButton = (Button)findViewById(R.id.StopRecording);

        bufferSize = AudioRecord.getMinBufferSize(sampleRateInHz,channelConfig, audioFormat);//计算最小缓冲区
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,sampleRateInHz,channelConfig, audioFormat, bufferSize);//创建AudioRecorder对象

        parent = new File(Environment.getDataDirectory().getAbsolutePath());
        if (!parent.exists()) {
            parent.mkdirs();//创建文件夹
        }
    }

    //初始化监听器
    public void initListener() {
        startRecordingButton.setOnClickListener(this);
        stopRecordingButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //开始录音
            case R.id.StartRecording: {
                record();
                break;
            }

            //停止录音
            case R.id.StopRecording: {
                stopRecording();
                break;
            }
        }

    }

    //开始录音
    public void record() {
        isRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                isRecording = true;

                recordingFile = new File(parent,"audiotest.pcm");
                if(recordingFile.exists()){
                    recordingFile.delete();
                }

                try {
                    recordingFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"创建储存音频文件出错");
                }

                try {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(recordingFile)));
                    byte[] buffer = new byte[bufferSize];
                    audioRecord.startRecording();//开始录音
                    int r = 0;
                    while (isRecording) {
                        int bufferReadResult = audioRecord.read(buffer,0,bufferSize);
                        for (int i = 0; i < bufferReadResult; i++)
                        {
                            dos.write(buffer[i]);
                        }
                        r++;
                    }
                    audioRecord.stop();//停止录音
                    dos.close();
                } catch (Throwable t) {
                    Log.e(TAG, "Recording Failed");
                }

            }
        }).start();

    }

    //停止录音
    public void stopRecording()
    {
        isRecording = false;
    }
}
