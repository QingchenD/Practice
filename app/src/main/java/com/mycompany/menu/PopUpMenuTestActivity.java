package com.mycompany.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.mymaintestactivity.StartOneActitity;


public class PopUpMenuTestActivity extends AppCompatActivity  {
    private TextView txt_menu;
    private PopupMenu popupMenu;

    /**字体大小*/
    public static final int FONT_10 = 0x111;
    public static final int FONT_12 = 0x112;
    public static final int FONT_14 = 0x113;
    public static final int FONT_16 = 0x114;
    public static final int FONT_18 = 0x115;

    /**字体颜色*/
    public static final int FONT_RED = 0x116;
    public static final int FONT_BLUE = 0x117;
    public static final int FONT_GREEN = 0x118;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_test);
        txt_menu = (TextView) findViewById(R.id.txt_menu);
        txt_menu.setText("菜单测试");

        popupMenu = new PopupMenu(PopUpMenuTestActivity.this,txt_menu);
        getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
//        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.fount_10:
                        txt_menu.setTextSize(10*2);
                        break;
                    case R.id.fount_12:
                        txt_menu.setTextSize(12*2);
                        break;
                    case R.id.fount_14:
                        txt_menu.setTextSize(14*2);
                        break;
                    case R.id.fount_16:
                        txt_menu.setTextSize(16*2);
                        break;
                    case R.id.fount_18:
                        txt_menu.setTextSize(18*2);
                        break;
                }
                return true;
            }
        });


        txt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });


        TextView contentMenu = (TextView) findViewById(R.id.content_menu);
        contentMenu.setText("Content Menu");
        registerForContextMenu(contentMenu);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Menu Test");

            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setLogo(R.mipmap.ic_launcher);
            actionBar.setDisplayUseLogoEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //添加子菜单
        SubMenu subMenu = menu.addSubMenu("字体大小");
        //添加头部图标 android 3。0之后貌似就没有效果了
        subMenu.setHeaderIcon(R.mipmap.ic_launcher);
        //添加图标 android 3。0之后貌似就没有效果了
        //subMenu.setIcon(R.mipmap.pic_1);
        //添加头部标题 android 3。0之后貌似就没有效果了
        subMenu.setHeaderTitle("选择字体");
        //添加菜单项， 其实返回的是 MenuItem FONT_10设置的是item的id
        subMenu.add(0,FONT_10,0,"10号");
        subMenu.add(0,FONT_12,0,"12号");
        subMenu.add(0,FONT_14,0,"14号");
        subMenu.add(0,FONT_16,0,"16号");
        subMenu.add(0,FONT_18,0,"18号");

        SubMenu colorMenu = menu.addSubMenu(0,1,0,"字体颜色");
        colorMenu.setHeaderIcon(R.mipmap.ic_launcher);
        //colorMenu.setIcon(R.mipmap.ic_launcher);
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(0,FONT_RED,0,"红色");
        colorMenu.add(0,FONT_BLUE,0,"蓝色");
        colorMenu.add(0,FONT_GREEN,0,"绿色");
//        menu.setGroupCheckable(1,true,false);
        //关联Activity
        SubMenu intentMenu = menu.addSubMenu("启动Activity");
        intentMenu.setHeaderTitle("选择要启动的Activity");
        MenuItem menuItem = intentMenu.add("查看ViewAnimator使用");
        menuItem.setIntent(new Intent(this,StartOneActitity.class));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //添加上下菜单
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        //menu.setHeaderIcon(R.mipmap.pic_1);
        menu.setHeaderTitle("选择背景色");
        menu.add(0,FONT_RED,0,"红色");
        menu.add(0,FONT_BLUE,0,"蓝色");
        menu.add(0,FONT_GREEN,0,"绿色");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case FONT_RED:
                txt_menu.setBackgroundColor(Color.RED);
                break;
            case FONT_BLUE:
                txt_menu.setBackgroundColor(Color.BLUE);
                break;
            case FONT_GREEN:
                txt_menu.setBackgroundColor(Color.GREEN);
                break;

        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
