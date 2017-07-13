package com.example.karan.myapplication.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.karan.myapplication.R;
import com.example.karan.myapplication.image_essentials.ImageManager;
import com.example.karan.myapplication.recyclerview.city.CityAdapter;
import com.example.karan.myapplication.recyclerview.city.CitiesList;
import com.example.karan.myapplication.utils.RecyclerViewClickListener;
import com.example.karan.myapplication.utils.RecyclerViewTouchListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*After the login screen, a user is directed to this activity is displayed
* containing a list of 8 cities in a recyclerview containing name, one line
* info of that city along with a thumbnail accessed from the internal storage
* of the user and an add button using which a user can add additional cities to
* the list along with a photo chosen from gallery or clicked via camera*/

public class RecyclerActivity extends AppCompatActivity  implements View.OnClickListener,RecyclerViewClickListener{
    private String[] city,filler;
    private Dialog dialog;
    EditText city1;
    EditText city2;
    Button bt_yes;
    Button bt_no;
    Button click;
    Button click1;
    Vibrator vibrator;
    ArrayList<CitiesList> cities= new ArrayList<>();
    private RecyclerView.Adapter adapter;
    FloatingActionButton add;
    RecyclerView rv;
    private String city_name_img="";
    int REQUEST_CAMERA=1;
    int SELECT_FILE=2;
    ImageManager imageManager= new ImageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_recycler);
        city = getResources().getStringArray(R.array.city);
        filler = getResources().getStringArray(R.array.filler);
        rv = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new CityAdapter(RecyclerActivity.this, getData());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setAdapter(adapter);
        rv.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(),rv,this));
        add = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        /*Clicking the add button opens a custom dialog box for the user to enter city details, one line
        * info and an image from gallery or camera*/
        add.setOnClickListener(this);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {
        showDialog();
    }

    /*Method defined to fill the recycler view with predefined String and image
    * extracted from internal storage of user*/

    private ArrayList<CitiesList> getData(){
        /*Path string gives the location of a user's internal storage which is images/icons in
        * internal storage*/
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + "images" + "/" + "icons" +"/";
        File folder= new File(path);
        CitiesList s;
        if(folder.exists())
        {
            File[] files=folder.listFiles();
            for(int i=0;i<8;i++){
                File file= files[i];
                s= new CitiesList(city[i],filler[i],Uri.fromFile(file));
                cities.add(s);
            }
        }
        return cities;
    }
    //Dialog box defined for user input
    public void showDialog() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit the application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        Intent intent = new Intent(RecyclerActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        return;
                    }
                })
                .show();
    }

    /*This method returns result of user chosen image from gallery or user clicked image
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton2:
                v.startAnimation(AnimationUtils.loadAnimation(RecyclerActivity.this, R.anim.bounce));
                final Dialog dialog;
                dialog = new Dialog(RecyclerActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.show();
                city1 = (EditText) dialog.findViewById(R.id.city_name);
                city2 = (EditText) dialog.findViewById(R.id.city_desc);
                bt_yes = (Button) dialog.findViewById(R.id.btn_yes);
                bt_no = (Button) dialog.findViewById(R.id.btn_no);
                click = (Button) dialog.findViewById(R.id.capture);
                click1 = (Button) dialog.findViewById(R.id.gallery);

                //Clicking this button displays a Toast and adds the newly created city to list
                bt_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        city_name_img = city1.getText().toString();
                        if (city1.getText().toString().equals("") || city2.getText().toString().equals("")) {
                            Toast.makeText(getApplicationContext(), "Enter city details", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), city1.getText() + " added", Toast.LENGTH_SHORT).show();
                            cities.add(new CitiesList(city1.getText().toString(), city2.getText().toString(),
                                    imageManager.loadBitmap(getApplicationContext(), city_name_img)));
                            adapter.notifyItemInserted(cities.size());
                            adapter.notifyDataSetChanged();
                            adapter.getItemCount();
                            String city7 = city1.getText().toString();
                            String city8 = city2.getText().toString();
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("city", city7);
                            edit.putString("city1", city8);
                            edit.commit();
                            dialog.dismiss();
                        }
                    }
                });
                bt_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //Opens camera to allow user capture image to be later displayed in city added
                click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        city_name_img = city1.getText().toString();
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);

                    }
                });
                //Opens gallery app on user's device to allow user to choose an image for new city
                click1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        city_name_img = city1.getText().toString();
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent, SELECT_FILE);
                    }
                });
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent t= new Intent(RecyclerActivity.this,CitiesDescription.class);
        t.putExtra("position",position);
        startActivity(t);
    }
    //Long pressing any item in the recyclerview asks user if they want to delete that item
    @Override
    public void onLongClick(View view, final int position) {
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
        AlertDialog alertbox = new AlertDialog.Builder(RecyclerActivity.this)
                .setMessage("Do you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        cities.remove(position);
                        adapter.notifyDataSetChanged();
                        adapter.getItemCount();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();
    }
}

