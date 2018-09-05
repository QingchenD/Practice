package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Qingweid on 2015/12/16.
 */
public class PopWindowTestActivity extends Activity {

    ArrayList<String> strList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_window_test_layout);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    public  void onPopWindow( View view) {
         showPopupWindow(view);

    }

    private void showPopupWindow(View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        // 设置按钮的点击事件
        Button button = (Button) contentView.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(PopWindowTestActivity.this, "button is pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ListView listView = (ListView) contentView.findViewById(R.id.popup_list_view);
        initList();
        MyListViewAdapter adapter = new MyListViewAdapter(strList);
        listView.setAdapter(adapter);
        setListViewHeight(listView);

        final PopupWindow popupWindow = new PopupWindow(contentView,
                GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.i("mengdd", "onTouch : ");

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        float alpha = 0.8f;
        contentView.setAlpha(alpha);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));

//        popupWindow.setWidth(100);
//        popupWindow.setHeight(100);

        int popupWindowWidth = popupWindow.getBackground().getIntrinsicWidth();
        int popupWindowHeight = popupWindow.getBackground().getIntrinsicHeight();
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " showPopupWindow()" + " popupWindowWidth:" + popupWindowWidth + " popupWindowHeight:" + popupWindowHeight);

        int left = view.getLeft();
        int bottom = view.getBottom();

        WindowManager winManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int width = winManager.getDefaultDisplay().getWidth();
        int height = winManager.getDefaultDisplay().getHeight();
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " showPopupWindow()" + " width:" + width + " height:" + height);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        int screenHeight = dm.heightPixels;
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " showPopupWindow()" + " left:" + left + " bottom:" + bottom);
        System.out.println("[Mydebug] " + this.getClass().getSimpleName() + " showPopupWindow()" + " screenWidth:" + screenWidth + " screenHeight:" + screenHeight);

//        popupWindow.setWidth(screenWidth);

        // 设置好参数之后再show
        Drawable d = new ColorDrawable(Color.WHITE);
        d.setAlpha(188);
        popupWindow.setBackgroundDrawable(d);
        popupWindow.showAsDropDown(view, 0, 0);
    }

    private void setListViewHeight(ListView lv) {
        ListAdapter la = lv.getAdapter();
        if(null == la) {
            return;
        }
        // calculate height of all items.
        int h = 0;
        final int cnt = la.getCount();
        for(int i=0; i<cnt; i++) {
            View item = la.getView(i, null, lv);
            item.measure(0, 0);
            h += item.getMeasuredHeight();
            System.out.println("[Mydebug] " + " height:" + item.getMeasuredHeight());
        }
        // reset ListView height
        ViewGroup.LayoutParams lp = lv.getLayoutParams();
        lp.height = h + (lv.getDividerHeight() * (cnt - 1));
        lv.setLayoutParams(lp);
    }

    private void initList() {
        strList.clear();
        for ( int i = 0 ; i < 7 ; i++) {
            String str = Integer.valueOf(i).toString() + "\n" + "**" + i;
            strList.add(str);
        }
    }


    class MyListViewAdapter extends BaseAdapter {

        ArrayList<String> strArrayList ;
        LayoutInflater inflater ;
        MyListViewAdapter(ArrayList<String> strList) {
            strArrayList = strList;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if(strArrayList != null) {
                return strArrayList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return strArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            String tmpstr = (String)getItem(position);

            if(convertView == null){
                view = inflater.inflate(R.layout.popup_list_layout,null);

            } else {
                view = convertView;
            }

            if (position % 2 == 0) {
                LinearLayout line = (LinearLayout) view.findViewById(R.id.line_layout_popup);
                TextView text = (TextView) view.findViewById(R.id.text);
                TextView lineText = (TextView) view.findViewById(R.id.line_text);
                line.setVisibility(View.VISIBLE);
                text.setVisibility(View.GONE);
                String str = "" + position + "*********************************************************************";
                lineText.setText(str);
            } else {
                TextView text = (TextView) view.findViewById(R.id.text);
                text.setText(tmpstr);
            }

            return view;
        }
    }
}

