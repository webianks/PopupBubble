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
package com.nextcloud.ui.popupbubble;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
                    popupBubble.setVisibility(View.VISIBLE);
        } else {
            // Scrolling down
            if (popupBubble != null)
                   popupBubble.setVisibility(View.INVISIBLE);
            if(isTopVisible(recyclerView))
                popupBubble.deactivate();
        }
    }

    private boolean isTopVisible(RecyclerView recyclerView) {
        int position = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
        return position == 0;
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
