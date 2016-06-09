package com.webianks.popupbubble;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.webianks.library.PopupBubble;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by R Ankit on 20-05-2016.
 */

public class MainActivity extends AppCompatActivity {


    private List<DataClass> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private PopupBubble popupBubble;
    private ContentAdapter contentAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);

        setContent();

        //XML based use of PopupBubble

        popupBubble = (PopupBubble) findViewById(R.id.popup_bubble);

        popupBubble.setPopupBubbleListener(new PopupBubble.PopupBubbleClickListener() {

            @Override
            public void bubbleClicked(Context context) {

            }
        });

        //necessary to add
        popupBubble.setRecyclerView(recyclerView);
        //popupBubble.withAnimation(false);

        addNewContent();

    }

    private void addNewContent() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DataClass dataClass;
                for (int i=10;i>0;i--){
                    dataClass = new DataClass();
                    dataClass.setName("New Name "+i);
                    list.add(0,dataClass);
                }
                contentAdapter.notifyItemRangeInserted(0,10);
                popupBubble.activate();

            }
        }, 3000);

    }

    private void setContent() {


        DataClass dataClass;

        for (int i=0;i<50;i++){

            dataClass = new DataClass();
            dataClass.setName("Cool Name "+(i+1));
            list.add(dataClass);
        }

        contentAdapter = new ContentAdapter(this,list);
        recyclerView.setAdapter(contentAdapter);

    }
}
