package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Qingweid on 2017/8/10.
 */

public class AnimatorActivity extends Activity implements View.OnClickListener{

    View viewToAnimate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_layout);

        Button button = (Button) findViewById(R.id.toggleButton);
        button.setOnClickListener(this);

        viewToAnimate = findViewById(R.id.theView);
    }


    @Override
    public void onClick(View v) {
        if (viewToAnimate.getAlpha() > 0f) {
            viewToAnimate.animate().alpha(0f).translationX(-1000f);
        } else {
//            viewToAnimate.setTranslationX(0f);
//            viewToAnimate.animate().alpha(1f);
//            viewToAnimate.animate().alpha(1f).translationX(100f);
            viewToAnimate.setTranslationX(100f);
            viewToAnimate.setAlpha(1f);
        }
    }
}
