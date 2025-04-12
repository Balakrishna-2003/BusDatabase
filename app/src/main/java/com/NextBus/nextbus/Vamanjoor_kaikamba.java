package com.NextBus.nextbus;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
                                startActivity(new Intent(Vamanjoor_kaikamba.this, Moorkaveri_Elinje.class));
                                finish();
                            } else {
                                Toast.makeText(Vamanjoor_kaikamba.this, "swipe left", Toast.LENGTH_SHORT).show();
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
        
    }
}