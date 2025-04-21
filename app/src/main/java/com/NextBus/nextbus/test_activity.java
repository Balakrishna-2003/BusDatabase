package com.NextBus.nextbus;

import static com.NextBus.nextbus.MainActivity.global_tbname;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class test_activity extends AppCompatActivity {
    String time12;
    int previousIndex = -1; // Start with an invalid value

    int getNonRepeatingRandomIndex() {
        Random random = new Random();
        int newIndex = random.nextInt(5);
         while (newIndex == previousIndex)
            newIndex = random.nextInt(5);
        previousIndex = newIndex;
        return newIndex;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        TextView textView5 = findViewById(R.id.bus_title);
        CardView m_card = findViewById(R.id.middle_card);
        textView5.post(new Runnable() {
            @Override
            public void run() {
                float width = textView5.getWidth();
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

        Animation title_anime = AnimationUtils.loadAnimation(this, R.anim.title_animation);
        textView5.startAnimation(title_anime);


        Database db  = new Database(this);
        Cursor c = db.getData(global_tbname);
        c.moveToNext();

        LinearLayout scroll_add = findViewById(R.id.scroll_test);
        while(c.moveToNext()) {


            CardView card = new CardView(this);

            LinearLayout.LayoutParams cl = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cl.setMargins(50, 0, 50, 30);

            LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            LinearLayout.LayoutParams ll1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll1.setMargins(50, 40, 40, 0);

            LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll2.setMargins(0, 0, 40, 0);

            LinearLayout.LayoutParams ll4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll2.setMargins(0, 20, 40, 0);

            LinearLayout.LayoutParams il = new LinearLayout.LayoutParams(120, 120);
            il.setMargins(10, 0, 0, 0);

            LinearLayout.LayoutParams il1 = new LinearLayout.LayoutParams(40, 40);
            il1.setMargins(10, 0, 0, 3);

            LinearLayout.LayoutParams ll3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll3.setMargins(215, 0, 0, 0);

            card.setLayoutParams(cl);
            card.setBackground(getDrawable(R.drawable.vamanjoor_to_kaikamba));


            LinearLayout l1 = new LinearLayout(this);
            l1.setLayoutParams(ll);
            l1.setOrientation(LinearLayout.VERTICAL);
            l1.setBackground(getDrawable(R.drawable.vamanjoor_to_kaikamba));


            LinearLayout l2 = new LinearLayout(this);
            l2.setLayoutParams(ll1);
            l2.setOrientation(LinearLayout.HORIZONTAL);
            l2.setGravity(Gravity.CENTER);


            LinearLayout l3 = new LinearLayout(this);
            l3.setLayoutParams(ll3);
            l3.setGravity(Gravity.CENTER_VERTICAL);
            l3.setOrientation(LinearLayout.HORIZONTAL);
            l3.setPadding(0, 20,0,80);

            String k = c.getString(0);


            TextView g = new TextView(this);
            if(!k.isEmpty() || !k.isBlank()){
                if(String.valueOf(k.charAt(0)).equals(" ")){
                    g.setText(String.valueOf(k.charAt(1)));

                }else{
                    g.setText(String.valueOf(k.charAt(0)));
                }
            }
            Random ran = new Random();
            int[] backgrounds = {
                    R.drawable.back_img_1,
                    R.drawable.back_img_2,
                    R.drawable.back_img_3,
                    R.drawable.back_img_4,
                    R.drawable.back_img_5
            };
            int[] card_backgrounds = {
                    R.drawable.card_back_0,
                    R.drawable.card_back_3,
                    R.drawable.card_back_2,
                    R.drawable.card_back_1,
                    R.drawable.card_back_4
            };




            int i = getNonRepeatingRandomIndex();
            g.setBackground(getDrawable(backgrounds[i]));
            l1.setBackground(getDrawable(card_backgrounds[i]));
            g.setTextColor(Color.WHITE);
            g.setTypeface(Typeface.DEFAULT_BOLD);
            g.setTextSize(23);
            g.setLayoutParams(il);
            g.setGravity(Gravity.CENTER);

            TextView t = new TextView(this);
            t.setText(k);
            t.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setTextSize(20);
            t.setLayoutParams(ll);
            t.setPadding(40, 40, 40, 30);
            t.setTextColor(Color.BLACK);


            String time24 = c.getString(1);
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

            ImageView img = new ImageView(this);
            img.setLayoutParams(il1);
            img.setImageDrawable(getDrawable(R.drawable.img_3));

            TextView t1 = new TextView(this);
            t1.setText(time12);
            t1.setTypeface(Typeface.SERIF);
            t1.setLayoutParams(ll4);
            t1.setPadding(5, 0, 40, 10);
            t1.setTextColor(Color.BLACK);
//            t1.setGravity(Gravity.TOP);

            l2.addView(g);
            l2.addView(t);

            l3.addView(img);
            l3.addView(t1);

            l1.addView(l2);
            l1.addView(l3);
            card.addView(l1);
            scroll_add.addView(card);

            Animation anima = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            card.startAnimation(anima);



            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    db.deletebs(global_tbname, t.getText().toString(), time24);
                    scroll_add.removeView(v);
                    return true;
                }
            });


        }
    }
}