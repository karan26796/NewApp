package com.example.karan.myapplication.recyclerview.city;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karan.myapplication.R;

/**
 * Created by karan on 7/3/2017.
 */
/*Recycler view items are defined here to be held in the view*/
public class CityViewHolder extends RecyclerView.ViewHolder {
    TextView txtCityName,txtCityFiller;
    ImageView imageView;

    public CityViewHolder(View itemView) {
        super(itemView);

        txtCityFiller= (TextView) itemView.findViewById(R.id.txtCityFiller);
        txtCityName= (TextView) itemView.findViewById(R.id.txtCityName);
        imageView=(ImageView) itemView.findViewById(R.id.imgCity);
    }


}
