# PopupBubble
Easily Add  "New Post" popup button with the feeds (recyclerview) of your app.

<img src="http://www.webianks.com/popupbubble/2.png" height="700" width="400" >

#Min SDK
Minimum sdk is 14 and Support is limited to recyclerview for now.

#Add With Gradle Dependency
```groovy
compile 'com.webianks.library:popup-bubble:1.0.0'
```
**Maven:**
```xml
<dependency>
  <groupId>com.webianks.library</groupId>
  <artifactId>popup-bubble</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
#Add PopupBubble to layout
```xml
  <com.webianks.library.PopupBubble
        android:id="@+id/popup_bubble"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />
```
Postioning of this view can be done according to the need. By default it should be placed in <b>top center</b>. Also it should be placed <b>below recyclerview</b> in layout so that it shows on top of recyclerview.

#Example positioning
If its inside RelativeLayout then
```xml
  <com.webianks.library.PopupBubble
        android:layout_margin="16dp"
        android:id="@+id/popup_bubble"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        />
```
#Customization Through XML
```xml
<!--Change background Color-->
    app:backgroundColor="?attr/colorPrimary"
<!--Change text -->
    app:text="New Stories"
<!--Change text color-->
    app:textColor="#ffffff"
<!--Show/Hide Icon inside the button. By default its true.-->
    app:showIcon="false"
<!--Change icon color-->
    app:iconColor="#ffffff"
<!--Set Different Icons-->
    app:setIcon="@drawable/ic_new.png"
        
```
**Example : Full Customization**
```xml
<com.webianks.library.PopupBubble
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_margin="16dp"
        android:id="@+id/popup_bubble"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        app:backgroundColor="?attr/colorPrimary"
        app:iconColor="#ffffff"
        app:text="New Stories"
        app:textColor="#ffffff"
        app:setIcon="@drawable/ic_keyboard_arrow_up_white_18dp"
        />

```
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
Hide/Show PopupBubble according to your need
```java
   popupBubble.hide();
   popupBubble.show();
```

