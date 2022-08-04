package com.webianks.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

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

public class PopupBubble extends MaterialButton {

    private RecyclerView recyclerView;

    private PopupBubbleClickListener mListener;
    private boolean animation = true;
    private RecyclerViewListener recyclerViewListener;

    //Java Inflation
    public PopupBubble(Context context) {
        super(context);
        init();
    }

    //XML Inflation
    public PopupBubble(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //XMl Inflation
    public PopupBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //invalidate and redraw the views.
        invalidate();
        requestLayout();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int maskedAction = event.getActionMasked();

        if (maskedAction == MotionEvent.ACTION_DOWN) {
            if (mListener != null) {

                mListener.bubbleClicked(getContext());
                if (animation) {

                    if (recyclerView != null)
                        recyclerView.smoothScrollToPosition(0);

                    AnimationUtils.popout(this, 1000, new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    }).start();

                } else {

                    //this.removeAllViews();
                    if (recyclerView != null) {
                        recyclerView.removeAllViews();
                    }
                }
                //deactivate();

            }

        }
        invalidate();
        return true;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.hide();
    }

    //onBubbleClick Setter and the interface
    public void setPopupBubbleListener(PopupBubbleClickListener listener) {
        mListener = listener;
    }

    public interface PopupBubbleClickListener {
        void bubbleClicked(Context context);
    }

    //helper methods that can be accessed through the object
    public void hide() {
        this.setVisibility(View.INVISIBLE);
    }

    public void show() {

        if (animation) {
            AnimationUtils.popup(this, 1000).start();
        } else {
            this.setVisibility(VISIBLE);
        }
    }

    public void activate() {
        this.show();
        recyclerViewListener = new RecyclerViewListener(this);
        this.recyclerView.addOnScrollListener(recyclerViewListener);
    }

    //to detach the popub bubble when it has clicked or user manually scrolls to top
    public void deactivate() {
        this.recyclerView.removeOnScrollListener(recyclerViewListener);
    }
}
