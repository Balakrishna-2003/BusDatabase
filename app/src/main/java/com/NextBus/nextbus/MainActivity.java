package com.NextBus.nextbus;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainActivity extends AppCompatActivity {
    Scroller mScroller;
    String[][] arr = new String[26][2];
    LinearLayout bus_list;
    String tbname;
    Animation animation;
    String time12;
    public static String global_tbname;
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
                                    startActivity(new Intent(MainActivity.this, Moorkaveri_Elinje.class));
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "swipe left", Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                            }else{
                                if(Math.abs(yDiff) > threshold && Math.abs(velocityY) > velocity_threshold){
                                    if(yDiff > 0){
                                        Toast.makeText(MainActivity.this, "swipe down", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(MainActivity.this, "swipe up", Toast.LENGTH_SHORT).show();
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

    public void add(char a){
        bus_list  = findViewById(R.id.first_card_list);
        tbname = "kai_kinnigoli";
        bus_list.removeAllViews();
        switch (a){
            case 'b':
                bus_list = findViewById(R.id.second_card_list);
                tbname = "moo_elinje";
                bus_list.removeAllViews();
                break;
            case 'c':
                bus_list = findViewById(R.id.third_card_list);
                tbname = "vam_kaikamba";
                bus_list.removeAllViews();
                break;
        }

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.setMargins(20, 10, 20, 0);

        Database db = new Database(this);
        Cursor cursor = db.getData(tbname);
        cursor.moveToNext();

        while (cursor.moveToNext())  {

            CardView card = new CardView(this);
            card.setCardElevation(30);
            card.setRadius(10);
            card.setLayoutParams(ll);
            card.setBackground(getDrawable(R.drawable.cus_back));


            RelativeLayout r1 = new RelativeLayout(this);
            RelativeLayout.LayoutParams rr = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            rr.setMargins(50, 20, 50, 40);
            r1.setLayoutParams(rr);

            RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            leftParams.setMarginStart(50);
            TextView textLeft = new TextView(this);
            textLeft.setText(cursor.getString(0));
            textLeft.setTextSize(20);
            textLeft.setTypeface(null, Typeface.BOLD);
            textLeft.setTextColor(Color.BLACK);
            leftParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            textLeft.setLayoutParams(leftParams);

            TextView Rightview = new TextView(this);
            String time24 = cursor.getString(1);
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            time12 = time24;
            try {
                Date date = inputFormat.parse(time24);
                time12 = outputFormat.format(date);
                Log.d("FormattedTime", time12); // Output: 06:30 PM

            } catch (Exception e) {
                System.out.println(e);
            }
//                Date dh = new Date(cursor.getString(1));
//                Log.d("TAG", "add: "+dh);`
//                Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show();

            Rightview.setText(time12);
            Rightview.setTextSize(20);
            Rightview.setTextColor(Color.BLACK);
            Rightview.setTypeface(null, Typeface.BOLD);

            RelativeLayout.LayoutParams rightParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );
            rightParams.setMarginEnd(100);
            rightParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            Rightview.setLayoutParams(rightParams);

            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    bus_list.removeView(v);
                    db.deletebs(tbname, textLeft.getText().toString(), Rightview.getText().toString());
                    return true;
                }
            });

            r1.addView(textLeft);
            r1.addView(Rightview);

            card.addView(r1);
            bus_list.addView(card);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView textView5 = findViewById(R.id.bus_title);
        Animation anime = AnimationUtils.loadAnimation(this, R.anim.title_animation);
        textView5.startAnimation(anime);
        textView5.post(new Runnable() {
            @Override
            public void run() {
                float width = textView5.getWidth()-10;
                float textSize = textView5.getTextSize();

                Shader textShader = new LinearGradient(
                        0, 0, width, 0, // Left to Right
                        new int[]{
                                Color.parseColor("#d9366b"),
                                Color.parseColor("#5d56bd"),
                                Color.parseColor("#3b9fd1")
                        },
                        null,
                        Shader.TileMode.CLAMP
                );

                textView5.getPaint().setShader(textShader);
                textView5.invalidate();
            }
        });

//        LinearLayout bus_list = findViewById(R.id.bus_list);
//        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        ll.setMargins(20, 10, 20, 0);
//
//        RelativeLayout relativeLayout = findViewById(R.id.main);
//        swipeListener swipeListener = new swipeListener(relativeLayout);
//        ScrollView s = findViewById(R.id.bus_list_swipe);
//        swipeListener = new swipeListener(s);
//
//        TextView textView5 = findViewById(R.id.textView5);
//        textView5.post(new Runnable() {
//            @Override
//            public void run() {
//                float width = textView5.getWidth()-10;
//                float textSize = textView5.getTextSize();
//
//                Shader textShader = new LinearGradient(
//                        0, 0, width, 0, // Left to Right
//                        new int[]{
//                                Color.parseColor("#d9366b"),
//                                Color.parseColor("#5d56bd"),
//                                Color.parseColor("#3b9fd1")
//                        },
//                        null,
//                        Shader.TileMode.CLAMP
//                );
//
//                textView5.getPaint().setShader(textShader);
//                textView5.invalidate();
//            }
//        });
//
//        TextView textView = findViewById(R.id.textView);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, test_activity.class));
//            }
//        });
//
//
//
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog alertDialog = new Dialog(MainActivity.this);
                alertDialog.setContentView(R.layout.dialog_layout);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alertDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                alertDialog.getWindow().setAttributes(lp);
//                alertDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                alertDialog.setCancelable(true);
                alertDialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                alertDialog.show();
                final char[] t = {'a'};

                Spinner spiner = alertDialog.findViewById(R.id.spinner);
                String[] s = new String[]{"kaikamba~kinnigoli","vamanjoor~kaikamba", "moorkaveri~enlinje"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, s);
                spiner.setAdapter(adapter);

                final String[] tname = new String[1];
                spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0:
                                tname[0] = "kai_kinnigoli";
//                                t[0] = 'a';
                                break;
                            case 1:
                                tname[0] = "vam_kaikamba";
//                                t[0] = 'c';
                                break;
                            case 2:
                                tname[0] = "moo_elinje";
//                                t[0] = 'b';
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                EditText name = alertDialog.findViewById(R.id.alert_bus_name);
                TextView time = alertDialog.findViewById(R.id.alert_bus_time);
                Date d = new Date();
                Log.d("TAG", "onClick: "+d);


                TimePicker p = alertDialog.findViewById(R.id.timePicker);
                time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        p.setVisibility(TimePicker.VISIBLE);
                        time.setVisibility(TextView.INVISIBLE);
                    }
                });
                // Set a listener for when the time changes
                p.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        int hour = hourOfDay;
                        String amPm;

                        // Determine AM or PM and adjust hour
                        if (hour == 0) {
                            amPm = "AM";
                        } else if (hour == 12) {
                            amPm = "PM";
                        } else if (hour > 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }

                        // Format hour and minute for display
                        String formattedHour = (hour < 10) ? "0" + hour : String.valueOf(hour);
                        String formattedMinute = (minute < 10) ? "0" + minute : String.valueOf(minute);

                        // Display the selected time
                        String msg = formattedHour + ":" + formattedMinute + " " + amPm;
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        time.setText(msg);
                    }
                });
                TextView submit = alertDialog.findViewById(R.id.okay_text);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!name.getText().toString().isEmpty() && !time.getText().toString().isEmpty()){
                            Database db = new Database(MainActivity.this);
                            Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                            db.insertdb(tname[0] ,name.getText().toString(), time.getText().toString());
//                            add(t[0]);

                            alertDialog.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "Fill all the details!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
//
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        LinearLayout c = findViewById(R.id.first_card);
        LinearLayout c1 = findViewById(R.id.first_card_list);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, test_activity.class);
                global_tbname = "kai_kinnigoli";
                startActivity(intent);
                overridePendingTransition(R.anim.n_fade_in, R.anim.n_fade_out);


//
//                if(c1.getChildCount() <= 0){
//                    add('a');
//                    c1.startAnimation(animation);
//                }else{
////                    animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
//                    Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
//                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {}
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            c1.removeAllViews(); // safely remove children after animation ends
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {}
//                    });
//                    c1.startAnimation(fadeOut);
//
//
//                }
            }
        });
        LinearLayout se = findViewById(R.id.second_card);
        LinearLayout se1 = findViewById(R.id.second_card_list);
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, test_activity.class);
                global_tbname = "moo_elinje";
                startActivity(intent);
                overridePendingTransition(R.anim.n_fade_in, R.anim.n_fade_out);

//                if(se1.getChildCount() <= 0){
//                    add('b');
//                    se1.startAnimation(animation);
//                }else{
//                    Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
//                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
//                        @Override
//                        public void onAnimationStart(Animation animation) {}
//
//                        @Override
//                        public void onAnimationEnd(Animation animation) {
//                            se1.removeAllViews(); // safely remove children after animation ends
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animation animation) {}
//                    });
//                    se1.startAnimation(fadeOut);
//                }
//                just for commit testing
            }
        });
        LinearLayout te = findViewById(R.id.third_card);
        LinearLayout te1 = findViewById(R.id.third_card_list);
        te.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(te1.getChildCount() <= 0){
                    add('c');
                    te1.startAnimation(animation);
                }else{
                    Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {}

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            te1.removeAllViews(); // safely remove children after animation ends
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {}
                    });
                    te1.startAnimation(fadeOut);
                }
            }
        });


    }
}