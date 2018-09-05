package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/4/22.
 */
public class SQLiteDatabaseActivity extends Activity {

    MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_database_layout);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);
        Button createDatabase = (Button) findViewById(R.id.btn_create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });

        Button addData = (Button) findViewById(R.id.btn_add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",455);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                //开始组装第二条数据
                values.put("name","The Lost Symbol");
                values.put("author","Dan Brown");
                values.put("pages",510);
                values.put("price", 19.95);
                db.insert("Book",null,values);
                db.close();
            }
        });

        Button updateData = (Button) findViewById(R.id.btn_update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price" , 10.99);
                db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
            }
        });


        Button deleteData = (Button) findViewById(R.id.btn_delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "pages > ?", new String[]{"500"});
            }
        });

        Button queryButton = (Button) findViewById(R.id.btn_query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        System.out.println("[Mydebug] " + " Query" + " book name:" + name + " author:" + author +
                                " pages:" + pages + " price:" + price);
                    } while (cursor.moveToNext());
                    cursor.close();
                }
            }
        });

        Button replaceData = (Button) findViewById(R.id.btn_replace_data);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.beginTransaction(); //开始事务
                try {
                    db.delete("Book",null,null);
//                    if (true) {
//                        //在这里手动抛出一个异常,让事务失败
//                        throw new NullPointerException();
//                    }
                    ContentValues values = new ContentValues();
                    values.put("name" , "Game of Thrones");
                    values.put("author" , "George Martin");
                    values.put("pages",720);
                    values.put("price",20.85);
                    db.insert("Book",null ,values);
                    db.setTransactionSuccessful();  //事务已经执行成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction(); //结束事务
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
