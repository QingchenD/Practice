package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/3/18.
 */
public class FragmentTestActivity extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_layout);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
     }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                AnotherRightFragment fragment = new AnotherRightFragment();
                Log.d("FragmentTestActivity", "1 tag:" + fragment.getTag());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (fragmentManager.findFragmentByTag("Another") != null) {
                    transaction.remove(fragmentManager.findFragmentByTag("Another"));
                }
                transaction.add(R.id.right_layout, fragment, "Another");
                //transaction.add(R.id.right_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.d("FragmentTestActivity", "2 tag:" + fragment.getTag());
                break;
            }

            case R.id.button2: {
                RightFragment fragment = new RightFragment();
                Log.d("FragmentTestActivity", "1 tag:" + fragment.getTag());
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.right_layout, fragment, "Right");
                //transaction.add(R.id.right_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Log.d("FragmentTestActivity", "2 tag:" + fragment.getTag());

                break;
            }

            default:
                break;
        }
    }
}
