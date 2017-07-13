package com.example.karan.myapplication.recyclerview.sites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karan.myapplication.R;

/**
 * Created by karan on 7/5/2017.
 */
/*Recycler view items are defined here to be held in the view*/
public class SitesViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    ImageView iv;
    public SitesViewHolder(View itemView) {
        super(itemView);
        tv=( TextView) itemView.findViewById(R.id.tv);
        iv=(ImageView) itemView.findViewById(R.id.iv);

    }
}
