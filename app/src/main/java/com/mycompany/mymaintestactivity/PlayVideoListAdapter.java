package com.mycompany.mymaintestactivity;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import java.util.ArrayList;

/**
 * Created by Qingweid on 2015/12/31.
 */
public class PlayVideoListAdapter extends BaseAdapter {

    private ListView videoListView;
    private Context mContext;
    private LayoutInflater layoutInflater;

    private ArrayList<String>  videoNameList = new ArrayList<String>();


    PlayVideoListAdapter(Context context , ListView videoListView){
        this.videoListView = videoListView;
        this.mContext = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        SetVideoName();

    }

    private void SetVideoName() {

        if ( videoNameList == null) {
            videoNameList = new ArrayList<String>();
        }

        for ( int i = 1 ; i <= 5 ; i++) {
            String videoName = "VIDEO_" + i;
            videoNameList.add(videoName);
        }


    }

    @Override
    public int getCount() {
        if (videoNameList != null)
            return videoNameList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (videoListView != null && position < videoListView.getCount())
            videoListView.getItemAtPosition(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.single_video_layout,null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.video_title);
            viewHolder.videoView = (VideoView) convertView.findViewById(R.id.view_video);
            viewHolder.btnPlayVideo = (Button) convertView.findViewById(R.id.btn_play_video);
            viewHolder.btnPauseVideo = (Button) convertView.findViewById(R.id.btn_pause_video);
            viewHolder.btnStopVideo = (Button) convertView.findViewById(R.id.btn_stop_video);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        VideoOnClickListener listener = new VideoOnClickListener(convertView);
        viewHolder.btnPlayVideo.setOnClickListener(listener);
        viewHolder.btnPauseVideo.setOnClickListener(listener);
        viewHolder.btnStopVideo.setOnClickListener(listener);

        String title =  videoNameList.get(position);
        viewHolder.title.setText(title);
        openFile(viewHolder.videoView , 0);


        return convertView;
    }

    private void  openFile(View view, int msec) {

        VideoView videoView = (VideoView) view.findViewById(R.id.view_video);

        String fname = "android.resource://" + mContext.getPackageName() + "/" + R.raw.wildlife;
        System.out.println("[Mydebug] " + " openFile() " + " fname:" + fname);
        Uri uri = Uri.parse(fname);
        MediaController mediaController = new MediaController(mContext);
        mediaController.show();
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.seekTo(msec);
    }

    public void onPlayVideo(View view) {

        VideoView videoView = (VideoView) view.findViewById(R.id.view_video);

        System.out.println("[Mydebug] " + " onPlayVideo() " + " videoView:" + videoView);

        if (videoView != null && !videoView.isPlaying()) {
            openFile(view,0);
            videoView.start();
            Toast.makeText(mContext, "Replay", Toast.LENGTH_SHORT).show();
        } else if (videoView != null) {
            videoView.start();
            Toast.makeText(mContext, "Play", Toast.LENGTH_SHORT).show();
        }
    }

    public void onPauseVideo(View view) {

        Button btnPauseVideo = (Button) view.findViewById(R.id.btn_pause_video);
        VideoView videoView = (VideoView) view.findViewById(R.id.view_video);

        System.out.println("[Mydebug] " + " onPauseVideo() " + " btnPauseVideo:" + btnPauseVideo);

        if (btnPauseVideo.getText().toString().trim().equals("Continue")) {
            videoView.start();
            btnPauseVideo.setText("Pause");
            Toast.makeText(mContext, "Pause", Toast.LENGTH_SHORT).show();
            return;
        }

        if (videoView != null && videoView.isPlaying()) {
            videoView.pause();
            btnPauseVideo.setText("Continue");
            Toast.makeText(mContext, "Continue", Toast.LENGTH_SHORT).show();
        }
    }

    public void onStopVideo(View view) {

        VideoView videoView = (VideoView) view.findViewById(R.id.view_video);

        if (videoView != null && videoView.isPlaying()) {
            videoView.stopPlayback();
            Toast.makeText(mContext, "Stop", Toast.LENGTH_SHORT).show();
        }
    }


    class VideoOnClickListener implements View.OnClickListener {

        private View convertView;

        VideoOnClickListener(View convertView) {
            this.convertView = convertView;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_play_video:
                    System.out.println("[Mydebug] " + " VideoOnClickListener " + " click play");
                    onPlayVideo(convertView);
                    break;

                case R.id.btn_pause_video:
                    onPauseVideo(convertView);
                    break;

                case R.id.btn_stop_video:
                    onStopVideo(convertView);
                    break;

                default:
                    break;
            }
        }
    }


    class ViewHolder {
        TextView title;
        VideoView videoView;
        Button btnPlayVideo;
        Button btnPauseVideo;
        Button btnStopVideo;
    }
}
