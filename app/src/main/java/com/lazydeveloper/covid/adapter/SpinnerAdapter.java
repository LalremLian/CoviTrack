package com.lazydeveloper.covid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazydeveloper.covid.R;
import com.lazydeveloper.covid.spinner.SpinnerModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SpinnerAdapter extends ArrayAdapter<SpinnerModel>{

    LayoutInflater inflater;
    ArrayList<SpinnerModel> objects;
    ViewHolder holder = null;

    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<SpinnerModel> objects) {
        super(context, textViewResourceId, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        SpinnerModel spinnerModel = objects.get(position);
        View view = convertView;
        if (null == view)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.spinner_dropdown, parent, false);
            holder.name = view.findViewById(R.id.tvCountryName);
            //holder.img =  view.findViewById(R.id.countryImage);
            view.setTag(holder);
        } else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(spinnerModel.getName());
//        holder.img.setBackgroundResource(spinnerModel.getApp_logo());

        return view;
    }

    static class ViewHolder
    {
        TextView name;
        ImageView img;
    }
}
