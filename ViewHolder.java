package com.example.collo.kinemax;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mview;

    public ViewHolder(View itemView) {
        super(itemView);
        mview =itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           mClicklistener.OnItemClick(view, getAdapterPosition());
            }
        });
        //item long click

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                mClicklistener.OnLongClick(view, getAdapterPosition());
                return true;
            }
        });


    }

      //set datails to the recyclerview row
    public void setDetails(Context ctx ,String title, String image, String description){

        TextView mTitleTv=mview.findViewById(R.id.rTitletv);
        TextView mDetailsTv=mview.findViewById(R.id.rDescriptionTv);
        ImageView mImageTv=mview.findViewById(R.id.rImageView);

        //Setdata To Views
     mTitleTv.setText(title);
     mDetailsTv.setText(description);
        Picasso.get().load(image).into(mImageTv);


    }
    private ViewHolder.ClickListener mClicklistener;

    //interface to send callbacks
    public interface ClickListener{
        void OnItemClick(View view, int position);
        void OnLongClick(View view, int position);

    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClicklistener=clickListener;

    }
}
