package com.example.karan.myapplication.recyclerview.sites;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karan.myapplication.R;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by karan on 7/5/2017.
 */
/*Adapter gets the data for recyclerview items*/
public class SitesAdapter extends RecyclerView.Adapter<SitesViewHolder> {
    Context context;
    ArrayList<SitesList> sites;

    public SitesAdapter( Context context, ArrayList<SitesList> sites){

        this.context=context;
        this.sites=sites;
    }

    @Override
    public SitesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.sites_row,parent,false);
        return new SitesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SitesViewHolder holder, int position) {
        SitesList c= sites.get(position);
        holder.tv.setText(c.getSite());
        int Width=250,Height=200;
        //Picasso image loader used to load images from sd card into imageview
        Picasso.with(context)
                .load(c.getUri()).
                resize(Width,Height)
                .centerCrop();

    }

    @Override
    public int getItemCount() {
        return sites.size();
    }
}
