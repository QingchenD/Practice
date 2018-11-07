package com.mycompany.hencoder_practice;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;

import com.mycompany.hencoder_practice.homework10.MyEditTextView;
import com.mycompany.hencoder_practice.homework11.CircleView;
import com.mycompany.hencoder_practice.homework11.SquareImageView;
import com.mycompany.hencoder_practice.homework11.TagLayout;
import com.mycompany.hencoder_practice.homework9.AnimatorView;
import com.mycompany.mymaintestactivity.R;


public class HencoderActivity extends AppCompatActivity {

    private Animator animator;

    private TagLayout view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hencoder);

        view = (TagLayout) findViewById(R.id.tag_layout);

//        PropertyValuesHolder bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip", 45);
//        PropertyValuesHolder flipRotationHolder = PropertyValuesHolder.ofFloat("flipRotation", 270);
//        PropertyValuesHolder topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -45);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, bottomFlipHolder, flipRotationHolder, topFlipHolder);
//        objectAnimator.setStartDelay(1000);
//        objectAnimator.setDuration(2000);
//        objectAnimator.start();

        /*
        ObjectAnimator bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 45);
        bottomFlipAnimator.setDuration(1500);
        ObjectAnimator flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", -270);
        flipRotationAnimator.setDuration(1500);
        ObjectAnimator topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", - 45);
        topFlipAnimator.setDuration(1500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(bottomFlipAnimator, flipRotationAnimator, topFlipAnimator);
        animatorSet.setStartDelay(1000);

        ObjectAnimator bottomBackFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 0);
        bottomBackFlipAnimator.setDuration(1500);
       // ObjectAnimator flipBackRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 0);
       // flipBackRotationAnimator.setDuration(1500);
        ObjectAnimator topBackFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", 0);
        topBackFlipAnimator.setDuration(1500);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(bottomBackFlipAnimator, topBackFlipAnimator);

        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playSequentially(animatorSet, animatorSet2);
        animatorSet3.start();
        */
    }
}
