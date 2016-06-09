package com.webianks.library;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

/*
 * PopupBubble library for Android
 * Copyright (c) 2016 Ramankit Singh (http://github.com/webianks).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
