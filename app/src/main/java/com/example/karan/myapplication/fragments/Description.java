package com.example.karan.myapplication.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karan.myapplication.R;
import com.example.karan.myapplication.activities.RecyclerActivity;

import java.io.File;


/*This fragment contains an image of the clicked city from
* previous activity and a few lines of description user entered
* or pre-defined*/
public class Description extends Fragment {
    String[] locationDesc;
    ImageView img;
    TextView textView;
    public int cityNo;
    public String city, desc, path;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.desc_frag, container, false);
        locationDesc = getResources().getStringArray(R.array.desc);
        cityNo = this.getArguments().getInt("cityIndex");
        city = this.getArguments().getString("name");
        desc = this.getArguments().getString("desc");
        textView = (TextView) view.findViewById(R.id.city1);
        img = (ImageView) view.findViewById(R.id.image);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + "images" + "/" + "icons" + "/";
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (int i = 0; i < 8; i++) {
            if (cityNo == i) {
                File file = files[i];
                textView.setText(locationDesc[i]);
                img.setImageURI(Uri.fromFile(file));
            }
            else if (cityNo >= 8) {
                textView.setText(desc);
                img.setImageResource(R.drawable.bus);
            }
        }
        super.onViewCreated(view, savedInstanceState);
    }
}


