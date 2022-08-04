[![Releases](https://img.shields.io/github/release/nextcloud/android.svg)](https://github.com/nextcloud/PopupBubble/releases/latest)
[![License](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg)](https://github.com/nextcloud/PopupBubble/blob/master/LICENCE)

# PopupBubble

Easily Add  "New Post" popup button with the feeds (recyclerview) of your app.

<img src="/docs/screenshot1.png" alt="screenshot" height="1206" width="540">

# Min SDK

Minimum sdk is 19 and support is limited to recyclerview for now.

# Add With Gradle Dependency

```groovy
compile 'com.github.nextcloud:PopupBubble:2.0.0'
```

# Add PopupBubble to layout

```xml
<com.nextcloud.ui.popupbubble.PopupBubble
  android:id="@+id/popup_bubble"
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
/>
```

Positioning of this view can be done according to the need and is based on `MaterialButton`so all themeing/customization is applicable.

#Access the bubble from Java

```java
PopupBubble popupBubble = (PopupBubble) findViewById(R.id.popup_bubble);
```

Add listener if you want to know when the bubble is clicked

```java
popupBubble.setPopupBubbleListener(new PopupBubble.PopupBubbleClickListener() {
            @Override
            public void bubbleClicked(Context context) {
  
                //popup_bubble is clicked  
            }
        });
```
**Attach with your RecyclerView**
```java
 //necessary to add
popupBubble.setRecyclerView(recyclerView);
```

#Helper Methods

**Hide/Show** PopupBubble according to your need

```java
popupBubble.hide();
popupBubble.show();
```

**Update text dynamically**
Call this method before the activate method to set the new text.

```java
popupBubble.setText("10 new stories");
```

**Update icon dynamically**
Call this method before the activate method to set the new Icon.

```java
popupBubble.setIcon(R.drawable.new_icon);
```

**Update typeface of text dynamically**
Call this method before the activate method to set the new Typeface.

```java
popupBubble.setTypeFace(myCustomTypeface);
```

# Most Important

Now <b>download/fetch new content</b> in background and then notify your recyclerview adapter about range of items added and finally <b>activate the PopupBubble</b> to make it appear with animation (if not set false).

```java
//Own logic for fetching new content
 .....      
adapter.notifyItemRangeInserted(0,size_of_new_items_added); // size_of_new_items_added = 10 if 10 new items are added.
popupBubble.activate(); 
```

# License

```
PopupBubble library for Android
Copyright (c) 2016 Ramankit Singh (http://github.com/webianks).
Copyright (c) 2022 Andy Scherzinger

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
