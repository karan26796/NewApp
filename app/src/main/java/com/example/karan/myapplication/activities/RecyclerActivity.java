package com.example.karan.myapplication.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.karan.myapplication.R;
import com.example.karan.myapplication.image_essentials.ImageManager;
import com.example.karan.myapplication.recyclerview.city.CitiesList;
import com.example.karan.myapplication.recyclerview.city.CityAdapter;
import com.example.karan.myapplication.utils.RecyclerViewClickListener;
import com.example.karan.myapplication.utils.RecyclerViewTouchListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewClickListener {
    private String[] city, filler;
    Dialog dialog;
    EditText etCityName, etCityDetail;
    Button btnYes, btnNo, btnCapture, btnSelect;
    CityAdapter cityAdapter;
    ArrayList<CitiesList> cities = new ArrayList<>();
    FloatingActionButton add;
    RecyclerView rvStates;
    private String city_name_img = "";
    int REQUEST_CAMERA = 1;
    int SELECT_FILE = 2;
    ImageManager imageManager = new ImageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_recycler);
        city = getResources().getStringArray(R.array.city);
        filler = getResources().getStringArray(R.array.filler);
        rvStates = (RecyclerView) findViewById(R.id.recycler_view);
        cityAdapter = new CityAdapter(RecyclerActivity.this, getData());

        rvStates.setLayoutManager(new LinearLayoutManager(this));
        rvStates.setAdapter(cityAdapter);
        rvStates.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), rvStates, this));

        add = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        add.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showDialog();
    }


    private ArrayList<CitiesList> getData() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + "images" + "/" + "icons" + "/";
        File folder = new File(path);
        CitiesList s;
        if (folder.exists()) {
            File[] files = folder.listFiles();
            for (int i = 0; i < 8; i++) {
                File file = files[i];
                s = new CitiesList(city[i], filler[i], Uri.fromFile(file));
                cities.add(s);
            }
        }
        return cities;
    }

    public void showDialog() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        startActivity(new Intent(RecyclerActivity.this, MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }

    /*This method returns result of user chosen image from gallery or user btnCaptureed image
    * from camera to be added as a thumbnail in new city's item*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Uri uri = data.getData();
                try {
                    InputStream imageStream = getContentResolver().openInputStream(uri);
                    Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    Bitmap bmp = imageManager.getResizedBitmap(selectedImage, 200);
                    imageManager.saveFile(getApplicationContext(), bmp, city_name_img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA) {

                final Bitmap bmp = (Bitmap) data.getExtras().get("data");
                imageManager.saveFile(getApplicationContext(), bmp, city_name_img);
                imageManager.getResizedBitmap(bmp, 100);
            }
        }
    }

    private void showCustomDialog() {
        this.dialog = new Dialog(RecyclerActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.show();

        this.etCityName = (EditText) dialog.findViewById(R.id.etCityName);
        this.etCityDetail = (EditText) dialog.findViewById(R.id.etCityDetail);
        this.btnYes = (Button) dialog.findViewById(R.id.btnYes);
        this.btnNo = (Button) dialog.findViewById(R.id.btnNo);
        this.btnCapture = (Button) dialog.findViewById(R.id.btnCapture);
        this.btnSelect = (Button) dialog.findViewById(R.id.btnGallery);

        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        btnCapture.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton2:
                v.startAnimation(AnimationUtils.loadAnimation(RecyclerActivity.this, R.anim.bounce));
                showCustomDialog();
                break;
            case R.id.btnYes:
                city_name_img = etCityName.getText().toString();
                if (etCityName.getText().toString().equals("") || etCityDetail.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter city details", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), etCityName.getText() + " added", Toast.LENGTH_SHORT).show();
                    cities.add(new CitiesList(etCityName.getText().toString(), etCityDetail.getText().toString(),
                            ImageManager.loadBitmap(getApplicationContext(), city_name_img)));
                    cityAdapter.notifyItemInserted(cities.size());
                    cityAdapter.notifyDataSetChanged();
                    cityAdapter.getItemCount();
                    String city7 = etCityName.getText().toString();
                    String city8 = etCityDetail.getText().toString();
                    SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                    SharedPreferences.Editor edit = prefs.edit();
                    edit.putString("city", city7);
                    edit.putString("etCityName", city8);
                    edit.commit();
                    dialog.dismiss();
                }
                break;

            case R.id.btnNo:
                dialog.dismiss();
                break;

            case R.id.btnGallery:
                city_name_img = etCityName.getText().toString();
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_FILE);
                break;

            case R.id.btnCapture:
                city_name_img = etCityName.getText().toString();
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CAMERA);
                break;
        }
    }

    @Override
    public void onClick(View view, int position) {
        startActivity(new Intent(RecyclerActivity.this, CitiesDescription.class)
                .putExtra("position", position));
    }

    //Long pressing any item in the recyclerview asks user if they want to delete that item
    @Override
    public void onLongClick(View view, final int position) {
        AlertDialog alertbox = new AlertDialog.Builder(RecyclerActivity.this)
                .setMessage("Do you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        cities.remove(position);
                        cityAdapter.notifyDataSetChanged();
                        cityAdapter.getItemCount();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
}

