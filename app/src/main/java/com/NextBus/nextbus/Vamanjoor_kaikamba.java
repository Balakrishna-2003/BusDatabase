package com.NextBus.nextbus;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Vamanjoor_kaikamba extends AppCompatActivity {

    Scroller mScroller;
    public class swipeListener implements View.OnTouchListener {
        GestureDetector gestureDetector;

        swipeListener(View view){
        int threshold = 100;
        int velocity_threshold = 100;

        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                float xDiff = e1.getX() - e2.getX();
                float yDiff = e1.getY() - e2.getY();
                try {
                    if(Math.abs(xDiff) > Math.abs(yDiff)){
                        if(Math.abs(xDiff) > threshold && Math.abs(velocityX) > velocity_threshold) {
                            if (xDiff > 0) {

                            } else {
                                Toast.makeText(Vamanjoor_kaikamba.this, "swipe left", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Vamanjoor_kaikamba.this, Moorkaveri_Elinje.class));
                                finish();
                            }
                            return true;
                        }
                    }else{
                        if(Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold){
                            if(yDiff > 0){
                                Toast.makeText(Vamanjoor_kaikamba.this, "swipe down", Toast.LENGTH_SHORT).show();
                                int deltaX = (int) (velocityX * 10); // Adjust the scaling factor as needed
                                int deltaY = (int) (velocityY * 10);

                                ScrollView scrollView = findViewById(R.id.bus_list_swipe);
                                mScroller.startScroll(scrollView.getScrollX(), scrollView.getScrollY(), deltaX, deltaY, 50);
//                                        scrollView.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                scrollView.setScrollX(scrollView.getScrollX() + deltaX);
//                                                scrollView.setScrollY(scrollView.getScrollY() + deltaY);
//                                                scrollView.post(this);
//                                            }
//                                        });

                                return true;

                            }else{
                                Toast.makeText(Vamanjoor_kaikamba.this, "swipe up", Toast.LENGTH_SHORT).show();
                                int deltaX = (int) (velocityX * 10); // Adjust the scaling factor as needed
                                int deltaY = (int) (velocityY * 10);

                                ScrollView scrollView = findViewById(R.id.bus_list_swipe);
                                mScroller.startScroll(scrollView.getScrollX(), scrollView.getScrollY(), deltaX, deltaY, 50);
//                                        scrollView.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                scrollView.setScrollX(scrollView.getScrollX() + deltaX);
//                                                scrollView.setScrollY(scrollView.getScrollY() + deltaY);
//                                                scrollView.post(this);
//                                            }
//                                        });
                            }
                            return true;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        gestureDetector = new GestureDetector(listener);
        view.setOnTouchListener(this);
    }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vamanjoor_kaikamba);

        LinearLayout bus_list = findViewById(R.id.bus_list);
        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setMargins(20,10, 20, 0);
        LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams ll3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        RelativeLayout relativeLayout = findViewById(R.id.main);
        Vamanjoor_kaikamba.swipeListener swipeListener = new Vamanjoor_kaikamba.swipeListener(relativeLayout);
        ScrollView s = findViewById(R.id.bus_list_swipe);
        swipeListener = new Vamanjoor_kaikamba.swipeListener(s);

        TextView textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Vamanjoor_kaikamba.this, MainActivity2.class));
            }
        });




        String[][] arr = new String[26][2];
        arr[0][0] = "navadurga"; arr[0][1] = "10:00";
        arr[1][0] = "sri laxmi"; arr[1][1] = "11:00";
        arr[2][0] = "holy family"; arr[2][1] = "12:00";
        arr[3][0] = "holy family"; arr[3][1] = "12:00";
        arr[4][0] = "holy family"; arr[4][1] = "12:00";
        arr[5][0] = "nandini";     arr[5][1] = "12:00";
        arr[6][0] = "nandini";     arr[6][1] = "12:00";
        arr[7][0] = "nandini";     arr[7][1] = "12:00";
        arr[8][0] = "nandini";     arr[8][1] = "12:00";
        arr[9][0] = "nandini";     arr[9][1] = "12:00";
        arr[10][0] = "nandini";     arr[10][1] = "12:00";
        arr[11][0] = "nandini";     arr[11][1] = "12:00";
        arr[12][0] = "nandini";     arr[12][1] = "12:00";
        arr[13][0] = "nandini";     arr[13][1] = "12:00";
        arr[14][0] = "nandini";     arr[14][1] = "12:00";
        arr[15][0] = "nandini";     arr[15][1] = "12:00";
        arr[16][0] = "nandini";     arr[16][1] = "12:00";
        arr[17][0] = "nandini";     arr[17][1] = "12:00";
        arr[18][0] = "nandini";     arr[18][1] = "12:00";
        arr[19][0] = "nandini";     arr[19][1] = "12:00";
        arr[20][0] = "nandini";     arr[20][1] = "12:00";
        arr[21][0] = "nandini";     arr[21][1] = "12:00";
        arr[22][0] = "nandini";     arr[22][1] = "12:00";
        arr[23][0] = "nandini";     arr[23][1] = "12:00";
        arr[24][0] = "nandini";     arr[24][1] = "12:00";
        arr[25][0] = "nandini";     arr[25][1] = "12:00";

        for(int i = 0; i<26; i++){

            CardView card = new CardView(this);
            card.setCardElevation(30);
            card.setRadius(10);
            card.setLayoutParams(ll);
            card.setBackground(getDrawable(R.drawable.cus_back));

            RelativeLayout r1 = new RelativeLayout(this);
            RelativeLayout.LayoutParams rr = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rr.setMargins(50,20,50,40);
            r1.setLayoutParams(rr);

            RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            leftParams.setMarginStart(50);
            TextView textLeft = new TextView(this);
            textLeft.setText(arr[i][0]);
            textLeft.setTextSize(20);
            textLeft.setTypeface(null, Typeface.BOLD);
            textLeft.setTextColor(Color.BLACK);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            textLeft.setLayoutParams(leftParams);

            TextView Rightview = new TextView(this);
            Rightview.setText(arr[i][1]);
            Rightview.setTextSize(20);
            Rightview.setTextColor(Color.BLACK);
            Rightview.setTypeface(null,Typeface.BOLD);

            RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rightParams.setMarginEnd(100);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            Rightview.setLayoutParams(rightParams);

            r1.addView(textLeft);
            r1.addView(Rightview);

            card.addView(r1);
            bus_list.addView(card);
        }

    }
}