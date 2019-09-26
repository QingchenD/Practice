package com.mycompany.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
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
        //textWidget.clearAnimation();
        AnimationDrawable animationDrawable = (AnimationDrawable) getDrawable(R.drawable.frame_anim);
        textWidget.setBackground(animationDrawable);
        animationDrawable.start();

        imageView = findViewById(R.id.image_anim);
        imageView.setImageResource(R.drawable.arrow);
        //Animation a = AnimationUtils.loadAnimation(getBaseContext(), R.anim.anim_alpha);
        //imageView.startAnimation(a);
        //animationSet(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Click Image", Toast.LENGTH_SHORT).show();
                //animator();
                //reverseAnimator();
                //loadAnimator();
                startValueAnimator();
            }
        });

    }

    private void reverseAnimator() {
        ImageHolder imageHolder = new ImageHolder(imageView);
        PropertyValuesHolder holder = PropertyValuesHolder.ofInt("width", 400, 700);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageHolder, holder);
        animator.setDuration(2000);
        if (imageView.getWidth() >= 700) {
            animator.reverse();
        } else {
            animator.start();
        }
        //animator.cancel();
        //animator.addListener(new MyAnimatorListener(animator));

    }

    private void animator() {
        ImageHolder imageHolder = new ImageHolder(imageView);
        PropertyValuesHolder holder;
        if (imageView.getWidth() >= 700) {
            holder = PropertyValuesHolder.ofInt("width", 400);
        } else {
            holder = PropertyValuesHolder.ofInt("width", 700);
        }
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(imageHolder, holder);
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

    private void loadAnimator() {
        //ImageHolder imageHolder = new ImageHolder(imageView);
        Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.property_animator_set);
        animator.setTarget(imageView);
        animator.start();
    }

    private void startValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(400, 700, 500, 800);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(3000);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 动画更新过程中的动画值，可以根据动画值的变化来关联对象的属性，实现属性动画
                int value = (int)animation.getAnimatedValue();
                Log.d("ValueAnimator", "动画值：" + value );
                //imageView.getLayoutParams().width = value;
                imageView.layout(imageView.getLeft(), imageView.getTop(), imageView.getLeft() + value, imageView.getBottom() );
                //imageView.requestLayout();
            }
        });
        valueAnimator.start();
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

    class MyAnimatorListener implements Animator.AnimatorListener {
        ObjectAnimator animator;
        MyAnimatorListener(ObjectAnimator animator) {
            this.animator = animator;
        }

        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            animator.reverse();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}
