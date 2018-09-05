package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Qingweid on 2015/12/23.
 */
public class PlayVideoActivity extends Activity {

    ListView videoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_video_layout);

        videoListView = (ListView) findViewById(R.id.video_list_view);

        PlayVideoListAdapter playVideoListAdapter = new PlayVideoListAdapter(this, videoListView);
        videoListView.setAdapter(playVideoListAdapter);

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
    protected void onStop() {
        super.onStop();
    }

}
