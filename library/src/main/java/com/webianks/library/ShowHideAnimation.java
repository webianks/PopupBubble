package com.webianks.library;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

/**
 * Created by R Ankit on 05-06-2016.
 */

public class ShowHideAnimation {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private static final Interpolator INTERPOLATOR_OUT = new LinearOutSlowInInterpolator();

    // Animation to hide the popup bubble.
    public void animateOut(final RelativeLayout view) {
            Animation anim = AnimationUtils.loadAnimation(view.getContext(), R.anim.pop_out);
            anim.setInterpolator(INTERPOLATOR_OUT);
            anim.setDuration(250L);
            anim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationStart(Animation animation) {
                    //ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
                }

                public void onAnimationEnd(Animation animation) {
                    //ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
                    view.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(final Animation animation) {
                }
            });
            view.startAnimation(anim);

    }

    //animation when the popup bubble is made visible

    public void animateIn(RelativeLayout view) {
        view.setVisibility(View.VISIBLE);
            Animation anim = AnimationUtils.loadAnimation(view.getContext(), R.anim.pop_in);
            anim.setDuration(300L);
            anim.setInterpolator(INTERPOLATOR);
            view.startAnimation(anim);
    }
}
