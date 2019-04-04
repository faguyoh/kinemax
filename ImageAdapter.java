package com.example.collo.kinemax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    private int[] image_id={R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four, R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight, R.drawable.nine, R.drawable.ten, R.drawable.eleven, R.drawable.twelve};

    Context ctx;

    ImageAdapter(Context ctx){
        this.ctx=ctx;


    }


    @Override
    public int getCount() {
        return image_id.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return image_id[position];
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

                   //gridview layout
        View gridView=convertView;
        if(gridView == null){
            LayoutInflater inflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView=inflater.inflate(R.layout.imagelayout_booking,null);
        }
        ImageView i1=(ImageView)gridView.findViewById(R.id.imgbook);
        i1.setImageResource(image_id[position]);



        return gridView;
    }



}
