package com.webianks.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by R Ankit on 20-05-2016.
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
    private Context context;
    private boolean animation = true;

    //Java Inflation
    public PopupBubble(Context context) {
        super(context);

        this.context = context;
        textView = new TextView(context);
        imageView = new ImageView(context);

        init(context);

    }


    //XML Inflation
    public PopupBubble(Context context, AttributeSet attrs) {
        super(context, attrs);

        textView = new TextView(context, attrs);
        imageView = new ImageView(context);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PopupBubble, 0, 0);

        try {

            String text = typedArray.getString(R.styleable.PopupBubble_text);
            String text_color = typedArray.getString(R.styleable.PopupBubble_textColor);
            String icon_color = typedArray.getString(R.styleable.PopupBubble_iconColor);
            String background_color = typedArray.getString(R.styleable.PopupBubble_backgroundColor);
            Drawable icon_drawable = typedArray.getDrawable(R.styleable.PopupBubble_setIcon);

            if (text!=null)
                TEXT = text;
            if (text_color!=null)
                TEXT_COLOR = text_color;
            if (icon_color!=null)
                ICON_COLOR = icon_color;
            if (background_color!=null)
                BACKGROUND_COLOR = background_color;
            if (icon_drawable!=null)
                ICON_DRAWABLE = icon_drawable;

            SHOW_ICON = typedArray.getBoolean(R.styleable.PopupBubble_showIcon, true);

            init(context);

        } finally {
            typedArray.recycle();
        }


    }

    //XMl Inflation
    public PopupBubble(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textView = new TextView(context, attrs, defStyleAttr);
        imageView = new ImageView(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PopupBubble, 0, 0);

        try {

            String text = typedArray.getString(R.styleable.PopupBubble_text);
            String text_color = typedArray.getString(R.styleable.PopupBubble_textColor);
            String icon_color = typedArray.getString(R.styleable.PopupBubble_iconColor);
            String background_color = typedArray.getString(R.styleable.PopupBubble_backgroundColor);
            Drawable icon_drawable = typedArray.getDrawable(R.styleable.PopupBubble_setIcon);

            if (text!=null)
                TEXT = text;
            if (text_color!=null)
                TEXT_COLOR = text_color;
            if (icon_color!=null)
                ICON_COLOR = icon_color;
            if (background_color!=null)
                BACKGROUND_COLOR = background_color;
            if (icon_drawable!=null)
                ICON_DRAWABLE = icon_drawable;

            SHOW_ICON = typedArray.getBoolean(R.styleable.PopupBubble_showIcon, true);

        } finally {
            typedArray.recycle();
        }

        init(context);
    }

    private void init(Context context) {

        //prepare background of the bubble
        setRoundedBackground(context);
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

    private void setRoundedBackground(Context context) {

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
                        new int[]{Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd"),
                                Color.parseColor("#dddddd")}, new float[]{0,
                        0.50f, 0.50f, 1}, Shader.TileMode.REPEAT);
                return lg;
            }
        });

        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{sds, sd});
        layerDrawable.setLayerInset(0, 5, 5, 0, 0); // inset the shadow so it doesn't start right at the left/top
        layerDrawable.setLayerInset(1, 0, 0, 5, 5); //

        this.setBackgroundDrawable(layerDrawable);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int maskedAction = event.getActionMasked();

        if (maskedAction == MotionEvent.ACTION_DOWN) {
            if (mListener != null) {

                mListener.bubbleClicked(getContext());



                if(animation){

                    if (recyclerView != null)
                        recyclerView.smoothScrollToPosition(0);

                    ShowHideAnimation showHideAnimation=new ShowHideAnimation();
                    showHideAnimation.animateOut(this);
                }else{

                    this.removeAllViews();
                    if (recyclerView != null){
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


       if(animation) {
           ShowHideAnimation showHideAnimation = new ShowHideAnimation();
           showHideAnimation.animateIn(this);
       }else{
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

    public void withAnimation(boolean animation){
        this.animation = animation;
    }

}
