package com.webianks.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class PopupBubble extends RelativeLayout {

    private TextView textView;
    private ImageView imageView;
    private RecyclerView recyclerView;

    private String TEXT = "New posts";
    private String TEXT_COLOR = "#ffffff";
    private String ICON_COLOR = "#ffffff";
    private String BACKGROUND_COLOR = "#dd424242";
    private boolean SHOW_ICON = true;
    private Drawable ICON_DRAWABLE;

    private PopupBubbleClickListener mListener;
    private boolean animation = true;

    //Java Inflation
    public PopupBubble(Context context) {
        super(context);

        textView = new TextView(context);
        imageView = new ImageView(context);

        init();
    }


    //XML Inflation
    public PopupBubble(Context context, AttributeSet attrs) {
        super(context, attrs);

        textView = new TextView(context, attrs);
        imageView = new ImageView(context);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PopupBubble, 0, 0);
        initAttributes(typedArray);
        typedArray.recycle();

        init();
    }

    //XMl Inflation
    public PopupBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textView = new TextView(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PopupBubble, 0, 0);
        initAttributes(typedArray);
        typedArray.recycle();

        init();
    }

    private void initAttributes(TypedArray typedArray) {

        String text = typedArray.getString(R.styleable.PopupBubble_pb_text);
        String text_color = typedArray.getString(R.styleable.PopupBubble_pb_textColor);
        String icon_color = typedArray.getString(R.styleable.PopupBubble_pb_iconColor);
        String background_color = typedArray.getString(R.styleable.PopupBubble_pb_backgroundColor);
        Drawable icon_drawable = typedArray.getDrawable(R.styleable.PopupBubble_pb_icon);
        String font = typedArray.getString(R.styleable.PopupBubble_pb_font);

        if (text != null)
            TEXT = text;
        if (text_color != null)
            TEXT_COLOR = text_color;
        if (icon_color != null)
            ICON_COLOR = icon_color;
        if (background_color != null)
            BACKGROUND_COLOR = background_color;
        if (icon_drawable != null)
            ICON_DRAWABLE = icon_drawable;
        if (font != null)
            textView.setTypeface(Typeface.createFromAsset(typedArray.getResources().getAssets(), font));

        SHOW_ICON = typedArray.getBoolean(R.styleable.PopupBubble_pb_showIcon, true);

    }

    private void init() {

        //prepare background of the bubble
        setRoundedBackground();
        //set the icon
        if (SHOW_ICON)
            addIcon();
        //set the text
        addText();

        //move the whole layout in the center of the screen
        moveToCenter();

        //invalidate and redraw the views.
        invalidate();
        requestLayout();

    }


    private void moveToCenter() {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, 0);
        this.setLayoutParams(layoutParams);

    }

    private void addIcon() {

        imageView.setId(R.id.image_view);

        if (ICON_DRAWABLE != null)
            imageView.setImageDrawable(ICON_DRAWABLE);
        else
            imageView.setImageResource(R.drawable.ic_arrow_upward_white_18dp);


        imageView.setPadding(35, 20, 15, 25);

        imageView.setColorFilter(Color.parseColor(ICON_COLOR), PorterDuff.Mode.SRC_ATOP);

        this.addView(imageView);

    }

    private void addText() {

        textView.setText(TEXT);
        if (SHOW_ICON)
            textView.setPadding(00, 20, 35, 25);
        else
            textView.setPadding(35, 20, 35, 25);

        textView.setTextColor(Color.parseColor(TEXT_COLOR));

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        layoutParams.addRule(RelativeLayout.RIGHT_OF, imageView.getId());

        this.addView(textView, layoutParams);

    }

    private void setRoundedBackground() {

        RoundRectShape rect = new RoundRectShape(
                new float[]{50, 50, 50, 50, 50, 50, 50, 50},
                null,
                null);


        ShapeDrawable sd = new ShapeDrawable(rect);
        sd.getPaint().setColor(Color.parseColor(BACKGROUND_COLOR));

        ShapeDrawable sds = new ShapeDrawable(rect);
        sds.setShaderFactory(new ShapeDrawable.ShaderFactory() {

            @Override
            public Shader resize(int width, int height) {

                LinearGradient lg = new LinearGradient(0, 0, 0, height,
                        new int[]{Color.parseColor("#DDDDDD"),
                                Color.parseColor("#DDDDDD"),
                                Color.parseColor("#DDDDDD"),
                                Color.parseColor("#DDDDDD")}, new float[]{0,
                        0.50f, 0.50f, 1}, Shader.TileMode.REPEAT);

                return lg;
            }
        });

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sds, sd});
        layerDrawable.setLayerInset(0, 2, 2, 0, 0); // inset the shadow so it doesn't start right at the left/top
        layerDrawable.setLayerInset(1, 0, 0, 2, 2);

        this.setBackgroundDrawable(layerDrawable);
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

                    this.removeAllViews();
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


    public void setRecyclerView(android.support.v7.widget.RecyclerView recyclerView) {

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
        this.recyclerView.addOnScrollListener(new RecyclerViewListener(this));
    }


    //to detach the popub bubble when it has clicked or user manually scrolls to top

    public void deactivate() {

        this.removeAllViews();
        this.recyclerView.removeOnScrollListener(null);
    }

    public void withAnimation(boolean animation) {
        this.animation = animation;
    }

    public void updateText(String text) {

        this.TEXT = text;
        this.textView.setText(this.TEXT);

    }

    public void updateTypeFace(Typeface font) {
        this.textView.setTypeface(font);
    }

    public void updateIcon(int iconRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            updateIcon(getResources().getDrawable(iconRes, getContext().getTheme()));
        } else {
            updateIcon(getResources().getDrawable(iconRes));
        }
    }

    public void updateIcon(Drawable icon) {
        this.SHOW_ICON = true;
        this.ICON_DRAWABLE = icon;
        if (ICON_DRAWABLE != null)
            imageView.setImageDrawable(ICON_DRAWABLE);
    }
}
