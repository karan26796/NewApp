package com.example.karan.myapplication.recyclerview.sites;

import android.net.Uri;

/**
 * Created by karan on 7/5/2017.
 */

public class SitesList {
    private String site;
    private Uri uri;

    public SitesList(String site,Uri uri){
        this.site=site;
        this.uri=uri;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

}
