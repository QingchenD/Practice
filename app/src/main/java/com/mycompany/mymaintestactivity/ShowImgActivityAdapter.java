package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.HashMap;


public class ShowImgActivityAdapter extends BaseAdapter {

    private final String TAG = "ShowImgActivityAdapter";
    private Activity mActivity;
    private LayoutInflater layoutInflater;
    private ListView parentListView;
    private Date date ;
    private int changeOffset = 0;

    private static final int MESSAGE_UPDATE_UI = 0;

    HashMap<String , Integer> imgMap = createImgMap();


    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_UPDATE_UI:
                    updateSingleRow(parentListView,0);
                    break;

                default:
                    break;
            }
        }
    };

    private static  HashMap<String , Integer> createImgMap() {
        HashMap<String , Integer> result = new HashMap<String , Integer>();

        result.put( "img_0" , R.drawable.reindeer);
        result.put( "img_1" , R.drawable.bells_wavfile);
        result.put( "img_2" , R.drawable.boot);
        result.put( "img_3" , R.drawable.candle);
        result.put( "img_4" , R.drawable.cane);
        result.put( "img_5" , R.drawable.christmas_crown);
        result.put( "img_6" , R.drawable.christmas_star);
        result.put( "img_7" , R.drawable.christmas_tree);
        result.put( "img_8" , R.drawable.cookie);
        result.put( "img_9" , R.drawable.crystal_ball_folder);
        result.put( "img_10" , R.drawable.elf);
        result.put("img_11", R.drawable.gift_root);

        return result;
    };

    ShowImgActivityAdapter(Activity activity , ListView listView) {
        this.mActivity = activity;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        date = new Date();
        parentListView = listView;
//        createChangeImgThread();

    }
    @Override
    public int getCount() {
        if ( imgMap != null ) {
            return imgMap.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null ) {
            holder = new ViewHolder();
            convertView = (View) layoutInflater.inflate(R.layout.img_item, null);
            holder.imgTitle = (TextView) convertView.findViewById(R.id.img_title);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.textureView = (TextureView) convertView.findViewById(R.id.texture_view);
            holder.textTest = (TextView) convertView.findViewById(R.id.text_click_test);
            holder.btnClick = (Button) convertView.findViewById(R.id.btn_click);
            holder.btnClick.setOnClickListener(listener);
            holder.btnClick.setTag(holder);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int index = position + changeOffset;
        if (index >= imgMap.size() ) {
            index = index % imgMap.size();
        }

        Log.i(TAG, "[Mydebug] getView()" + "position:" + position);
        String key = "img_" + (index);
        holder.img.setImageResource(imgMap.get(key));

        Bitmap rawBitmap = BitmapFactory.decodeResource(mActivity.getResources(), imgMap.get(key));
        Canvas canvas = holder.textureView.lockCanvas();
        System.out.println("[Mydebug] " + "  canvas:" + canvas);
        Matrix matrix = new Matrix();
        if ( canvas != null) {
            canvas.drawBitmap(rawBitmap, matrix, null);
        }
        holder.textureView.unlockCanvasAndPost(canvas);

        int width = holder.textureView.getWidth();
        int height = holder.textureView.getHeight();

//        matrix.postScale(0.5f , 0.5f);
//        matrix.postTranslate(100, 100);
        holder.textureView.setTransform(matrix);

        return convertView;
    }

    private void createChangeImgThread() {
        new Thread(){

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        Date curDate = new Date();
                        if (((curDate.getTime() - date.getTime()) / 1000) > 3) {
                            changeOffset++;
                            date.setTime(curDate.getTime());
                            handler.sendEmptyMessageAtTime(MESSAGE_UPDATE_UI,0);
                        }
                        System.out.println("[Mydebug] " + " createChangeImgThread() " + " changeOffset:" + changeOffset);

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void updateSingleRow(ListView listView, long id) {

        if (listView != null) {
            int start = listView.getFirstVisiblePosition();
            for (int i = start, j = listView.getLastVisiblePosition(); i <= j; i++) {
                if (true) {
                    View view = listView.getChildAt(i - start);
                    if( view.getVisibility() == View.VISIBLE ) {
                        System.out.println("[Mydebug] " + "updateSingleRow()" + " index:" + i);
                        getView(i, view, listView);
                    }
                }
            }
        }
    }

    private View.OnClickListener  listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_click:
                    onPressClickBtn(v);
                    break;

                default:
                    break;
            }
        }
    };

    private void onPressClickBtn(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        if (holder.textTest.getText().toString().equalsIgnoreCase("Click Test")) {
            holder.textTest.setText("Already Click");
        }

    }

    class ViewHolder {
        TextView imgTitle;
        ImageView img;
        TextureView textureView;
        TextView textTest;
        Button btnClick;
    }
}
