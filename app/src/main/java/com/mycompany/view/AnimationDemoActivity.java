package com.mycompany.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycompany.mymaintestactivity.R;

public class AnimationDemoActivity extends Activity {
    private TextView textWidget;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_demo_layout);

        Animation anim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_set);
        //监听动画的状态（开始，结束）
        anim.setAnimationListener(new EffectAnimationListener());
        textWidget = findViewById(R.id.text_widget);
        textWidget.setText("画面旋转动画效果");
        textWidget.startAnimation(anim);
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable(R.drawable.frame_anim);
        textWidget.setBackground(animationDrawable);
        animationDrawable.start();

        imageView = findViewById(R.id.image_anim);
        imageView.setImageResource(R.drawable.arrow);
        //Animation a = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_alpha);
        //imageView.startAnimation(a);
        //animationSet(imageView);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Click Image", Toast.LENGTH_SHORT).show();
                animator();
            }
        });

    }

    private void animator() {
        ImageHolder imageHolder = new ImageHolder(imageView);
        ObjectAnimator animator = ObjectAnimator.ofInt(imageHolder, "width",700);
        animator.setDuration(2000);
        animator.start();
    }

    private void animationSet(View v) {
        //Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_scale);
        //Animation alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha);

        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0.5f);
        translateAnimation.setStartOffset(0);
        translateAnimation.setDuration(2000);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setStartOffset(2000);
        rotateAnimation.setDuration(2000);

        AnimationSet animationSet = new AnimationSet(true);
        //animationSet.addAnimation(alphaAnimation);
        //animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotateAnimation);


        v.startAnimation(animationSet);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    class EffectAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    class ImageHolder {
        ImageView imageView;

        public ImageHolder(ImageView v) {
            this.imageView = v;
        }

        public void setWidth(int width) {
            imageView.getLayoutParams().width = width;
            imageView.requestLayout();
        }

        public int getWidth() {
            return imageView.getLayoutParams().width;
        }

    }
}
