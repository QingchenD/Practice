package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Qingweid on 2018/4/19.
 */

public class ScrollContainListActivity extends Activity {

    private final String TAG = "ScrollContainList";
    private ListView listView;
    Binder binder;
    int RUNTIN_THREADS = Runtime.getRuntime().availableProcessors();
    ExecutorService executorService = new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,null);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_contain_list_layout);
        listView = (MyListView) findViewById(R.id.scroll_list);
        listView.setAdapter(new MyAdapter());

        Log.i(TAG," RUNTIN_THREADS:" + RUNTIN_THREADS);

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
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

            if (convertView == null) {
                Log.i("getView", "position=" + position);
                convertView = getLayoutInflater().inflate(R.layout.list_item, null);
            }
            return convertView;
        }
    }

}



class MyListView extends ListView {

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
