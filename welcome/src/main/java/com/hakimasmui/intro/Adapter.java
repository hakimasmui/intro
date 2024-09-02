package com.hakimasmui.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends PagerAdapter {
    Context context;
    List<String> titles = new ArrayList<>();
    List<String> descriptions = new ArrayList<>();
    List<Integer> imgs = new ArrayList<>();
    int textColor;


    public Adapter(Context context, List<String> titles, List<String> descriptions, List<Integer> imgs) {
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
        this.imgs = imgs;
    }

    public void setColorText(int color) {
        this.textColor = color;
    }

    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = inflater.inflate(R.layout.intro, null);

        ImageView imgSlide = vi.findViewById(R.id.intro_img);
        TextView title = vi.findViewById(R.id.intro_title);
        TextView description = vi.findViewById(R.id.intro_description);

        title.setText(titles.get(position));
        description.setText(descriptions.get(position));
        imgSlide.setImageResource(imgs.get(position));

        title.setTextColor(textColor);
        description.setTextColor(textColor);

        container.addView(vi);

        return vi;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
