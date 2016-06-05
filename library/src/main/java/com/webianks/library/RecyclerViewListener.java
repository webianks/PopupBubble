package com.webianks.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by R Ankit on 04-06-2016.
 */

public class RecyclerViewListener extends RecyclerView.OnScrollListener {


    private PopupBubble popupBubble;


    public RecyclerViewListener(PopupBubble popupBubble) {

        this.popupBubble = popupBubble;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy >= 0) {
            // Scrolling up

                if (popupBubble != null)
                    popupBubble.show();
                System.out.println("Scrolling up");

        } else {
            // Scrolling down

                if (popupBubble != null)
                popupBubble.hide();
            System.out.println("Scrolling down");

            if(isTopVisible(recyclerView))
            {
                popupBubble.deactivate();

            }


        }
    }

    private boolean isTopVisible(RecyclerView recyclerView) {


        //System.out.println("Item visible "+((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition());

        if (((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition()==0)
            return true;
        else
            return false;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                //System.out.println("The RecyclerView is not scrolling");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                //System.out.println("Scrolling now");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                //System.out.println("Scroll Settling");
                break;

        }

    }
}
