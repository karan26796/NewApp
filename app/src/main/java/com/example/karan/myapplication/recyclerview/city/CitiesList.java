package com.example.karan.myapplication.recyclerview.city;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by karan on 7/3/2017.
 */

public class CitiesList {
    private String city,filler;
    private Uri uri;
    private Bitmap bitmap;

    //Defined for predefined cities
    public CitiesList(String city, String filler, Uri uri)
    {
        this.city=city;
        this.filler=filler;
        this.uri=uri;
    }
    //Defined for user entered city
    public CitiesList(String city, String filler, Bitmap bitmap){

        this.city=city;
        this.filler=filler;
        this.bitmap=bitmap;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
