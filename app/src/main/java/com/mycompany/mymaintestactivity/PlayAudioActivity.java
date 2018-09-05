package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Qingweid on 2015/12/23.
 */

public class PlayAudioActivity extends Activity{

    private  AsyncPlayer s_soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_audio_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onPlay(View view) {
        int resId = R.raw.high;
        if (s_soundPlayer == null)
            s_soundPlayer = new AsyncPlayer("inAppSoundPlayer");

        String fname = "android.resource://" + this.getPackageName() + "/" + resId;
        s_soundPlayer.play(this, Uri.parse(fname), false, AudioManager.STREAM_MUSIC);

    }

    public void onStop(View view) {
        if (s_soundPlayer != null )
            s_soundPlayer.stop();
    }

}
