package com.webianks.popupbubble;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.webianks.library.PopupBubble;

/**
 * Created by R Ankit on 20-05-2016.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        PopupBubble popupBubble = (PopupBubble) findViewById(R.id.popup_bubble);
        popupBubble.setPopupBubbleListener(new PopupBubble.PopupBubbleClickListener(){

            @Override
            public void bubbleClicked(Context context) {

              Toast.makeText(context,"Popup Bubble Clicked",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
