package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * Created by Qingweid on 2016/1/8.
 */
public class ShowImgActivity extends Activity {

    ListView imgListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_img_layout);

        imgListView = (ListView) findViewById(R.id.img_listview);

        ShowImgActivityAdapter adapter = new ShowImgActivityAdapter(this , imgListView);
        imgListView.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
