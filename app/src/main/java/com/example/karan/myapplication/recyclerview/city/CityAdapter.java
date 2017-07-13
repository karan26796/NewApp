package com.example.karan.myapplication.recyclerview.city;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karan.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/*Adapter gets the data for recyclerview items*/
public class CityAdapter extends RecyclerView.Adapter<CityViewHolder> {

    Context context;
    ArrayList<CitiesList> cities;

    public CityAdapter(Context context, ArrayList<CitiesList> cities){

        this.context=context;
        this.cities=cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.row_view,parent,false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CitiesList s= cities.get(position);
        holder.city.setText(s.getCity());
        holder.filler.setText(s.getFiller());
        int Width=50,Height=50;
        if (position < 8) {
            //Picasso image loader used to load images from sd card into imageview
            Picasso.with(context).load(s.getUri()).centerCrop().
                    resize(Width, Height).error(R.drawable.plus).
                    placeholder(R.drawable.plus).into(holder.imageView);
        } else {
            holder.imageView.setImageBitmap(s.getBitmap());
        }
    }

    //returns no. of items in view
    @Override
    public int getItemCount() {
        return cities.size();
    }


}
