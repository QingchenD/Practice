package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qingweid on 2016/4/25.
 */
public class ReadContactsActivity extends Activity {
    private ListView contactsView;
    private ArrayAdapter<String> adapter;
    private List<String> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_contact_layout);
        contactsView = (ListView) findViewById(R.id.contacts_view);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contactList);
        contactsView.setAdapter(adapter);
        readContacts();
    }

    public void readContacts() {
        Cursor cursor = null;
        try {
            //查询联系人数据
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()) {
                //获取联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                //获取联系人手机号
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactList.add(displayName + "\n" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null ) {
                cursor.close();
            }

        }
    }
}
