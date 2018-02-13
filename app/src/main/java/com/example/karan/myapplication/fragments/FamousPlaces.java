package com.example.karan.myapplication.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.karan.myapplication.R;
import com.example.karan.myapplication.recyclerview.sites.SitesAdapter;
import com.example.karan.myapplication.recyclerview.sites.SitesList;

import java.io.File;
import java.util.ArrayList;
/*This tab contains list of a few city photos with a line of description each*/
public class FamousPlaces extends Fragment {
    RecyclerView recyclerView;
    String[] site;
    String path;
    ArrayList<SitesList> sites= new ArrayList<>();
    public int cityNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sites_frag, container, false);

        cityNo = this.getArguments().getInt("cityIndex");
        recyclerView=(RecyclerView) view.findViewById(R.id.famousPlacesRecycler);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(new SitesAdapter(getContext(), getData()));
    }

    private ArrayList<SitesList> getData(){
        File folder;
        SitesList s;
        File[] files;
        //Common path defined for each of city folders later to be extended by city name while accessing city
        path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"images"+"/";
        if(cityNo==0)
        {
            path = path +"ahem"+"/";
            folder= new File(path);
            if (folder.exists()) {

                for (int i = 0; i < 3; i++) {
                    files = folder.listFiles();
                    File file = files[i];
                    site=getResources().getStringArray(R.array.ahmedabad);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==1)
        {
            path= path+"kolkata"+"/";
            folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();
                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.kolkata);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==2)
        {
            path= path+"delhi"+"/";
             folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();

                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.delhi);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==3)
        {
            path= path+"hyderabad"+"/";
            folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();

                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.hyderabad);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==4)
        {
            path= path+"luck"+"/";
            folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();

                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.lucknow);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==5)
        {
            path= path+"chennai";
            folder= new File(path);
            if (folder.exists()) {
               files = folder.listFiles();
                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.chennai);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==6)
        {
            path= path+"bang"+"/";
            folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();

                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.bangalore);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else if(cityNo==7)
        {
            path= path+"mumbai"+"/";
            folder= new File(path);
            if (folder.exists()) {
                files = folder.listFiles();

                for (int i = 0; i < 3; i++) {
                    File file = files[i];
                    site=getResources().getStringArray(R.array.bombay);
                    s = new SitesList(site[i], Uri.fromFile(file));
                    sites.add(s);
                }
            }
        }
        else{
            s=new SitesList("Hello",null);
            sites.add(s);
        }
        return sites;
    }
}
