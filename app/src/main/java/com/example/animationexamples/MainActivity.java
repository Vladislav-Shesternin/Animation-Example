package com.example.animationexamples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout mRlvLayout;
    ImageView mRocket;
    ImageView mDog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRlvLayout = findViewById(R.id.rlv_layout);
        mRocket = findViewById(R.id.img_rocket);
        mDog = findViewById(R.id.img_dog);

        mRlvLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onValueAnimator();
        //onObjectAnimator();
        //onColorAnimation();
        //onAnimatorSet();
        //onViewPropertyAnimator();
        //onRocketWithDog();
        //onAnimationInXML();
    }

    public void onValueAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, -730);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mRocket.setTranslationY(value);
            }
        });
        animator.setInterpolator(new AccelerateInterpolator(2.0f));
        animator.setDuration(1000);
        animator.start();
    }

    public void onObjectAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mRocket, "translationY", 0, -730);
        animator.setInterpolator(new AccelerateInterpolator(2.0f));
        animator.setDuration(1000);
        animator.start();
    }

    public void onColorAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofObject(mRlvLayout, "backgroundColor",
                new ArgbEvaluator(),
                ContextCompat.getColor(this, R.color.background_from),
                ContextCompat.getColor(this, R.color.background_to));

        animator.setRepeatCount(1);
        //animator.setRepeatMode(ValueAnimator.REVERSE);

        animator.setDuration(1000);
        animator.start();
    }

    public void onAnimatorSet() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, -750);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mRocket.setTranslationY(value);
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mRocket, "rotation", 0, 360);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimator).before(objectAnimator);
        animatorSet.setDuration(1000);
        animatorSet.start();

    }

    public void onViewPropertyAnimator() {
        ViewPropertyAnimator animator = mRocket.animate();
        animator.translationY(-750)
                .rotationBy(360f)
                .setDuration(3000)
                .start();
    }

    public void onRocketWithDog() {
        ValueAnimator animTranslationY = ValueAnimator.ofFloat(0, -750);
        animTranslationY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mRocket.setTranslationY(value);
                mDog.setTranslationY(value);
            }
        });

        animTranslationY.setInterpolator(new AccelerateInterpolator(2.0f));
        animTranslationY.setDuration(2000);
        animTranslationY.start();

    }

    public void onAnimationInXML() {
        AnimatorSet animRocket = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.jump_and_blink);
        animRocket.setTarget(mRocket);

        AnimatorSet animDog = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.jump_and_blink);
        animDog.setTarget(mDog);

        AnimatorSet bothAnimatorSet = new AnimatorSet();
        bothAnimatorSet.playTogether(animRocket, animDog);
        bothAnimatorSet.start();
    }
}
