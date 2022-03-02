package com.lazydeveloper.covid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lazydeveloper.covid.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AdapterPager extends PagerAdapter
{
    Context context;
    int images[] =
            {
                com.lazydeveloper.covid.R.drawable.item1,
                com.lazydeveloper.covid.R.drawable.item2,
                com.lazydeveloper.covid.R.drawable.item3
            };
    int heading[] =
            {
                R.string.heading1,
                R.string.heading2,
                R.string.heading3
            };

    public AdapterPager(Context context)
    {
        this.context = context;
    }
    @Override
    public int getCount()
    {
        return heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(com.lazydeveloper.covid.R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(com.lazydeveloper.covid.R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(com.lazydeveloper.covid.R.id.titleHeading);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(heading[position]);

        container.addView(view);

        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((LinearLayout)object);
    }
}
