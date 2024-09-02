package com.hakimasmui.intro;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class IntroActivity extends LinearLayout {
    RelativeLayout relMain;
    ViewPager viewPager;
    LinearLayout btnNext;
    TextView next;

    List<String> titles = new ArrayList<>();
    List<String> descriptions = new ArrayList<>();
    List<Integer> imgs = new ArrayList<>();
    int textColor;

    Adapter adapter;

    OnFinishIntro onFinishIntro;

    public IntroActivity(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.welcome, 0, 0);
        Drawable backgroundmain = a.getDrawable(R.styleable.welcome_background_main);
        textColor = a.getColor(R.styleable.welcome_text_color, 0);

        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.intro_activity, this, true);

        relMain = v.findViewById(R.id.relMain);
        viewPager = v.findViewById(R.id.viewpager);
        btnNext = v.findViewById(R.id.btn_next);
        next = v.findViewById(R.id.next);

        relMain.setBackground(backgroundmain);
        next.setTextColor(textColor);

        btnNext.setOnClickListener(view -> {
            int position = viewPager.getCurrentItem();
            if (position < titles.size()) {

                position++;
                viewPager.setCurrentItem(position);
            }

            if (position == titles.size()) {
                if (onFinishIntro != null) {
                    onFinishIntro.onFinished();
                }
            }
        });
    }

    public void setAdapter(List<String> titles, List<String> descriptions, List<Integer> imgs) {
        this.titles = titles;
        this.descriptions = descriptions;
        this.imgs = imgs;

        adapter = new Adapter(getContext(), titles, descriptions, imgs);
        adapter.setColorText(textColor);

        viewPager.setAdapter(adapter);
    }

    public interface OnFinishIntro {
        void onFinished();
    }

    public void setOnFinishIntro(OnFinishIntro onFinishIntro) {
        this.onFinishIntro = onFinishIntro;
    }
}
