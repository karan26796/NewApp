package com.example.karan.myapplication.image_essentials;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*This class is created to fetch the user picked image from gallery or camera to be displayed as a
thumbnail in newly added city*/

public class ImageManager {

    private static final String TAG = "";

    public static void saveFile(Context context, Bitmap b, String picName){
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 10, fos);
            fos.close();
        }
        catch (FileNotFoundException e) {
            Log.d(TAG, "File Not Found");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d(TAG, "IO Exception");
            e.printStackTrace();
        }

    }

    public static Bitmap loadBitmap(Context context, String picName){
        Bitmap b = null;
        FileInputStream fis;
        try {
            fis = context.openFileInput(picName);
            b = BitmapFactory.decodeStream(fis);
            fis.close();

        }
        catch (FileNotFoundException e) {
            Log.d(TAG, "File Not Found");
            e.printStackTrace();
        }
        catch (IOException e) {
            Log.d(TAG, "IO Exception");
            e.printStackTrace();
        }
        return b;
    }

    /*This method is defined to resize the image after user selects it to limit the app memory usage
    * and stop it from crashing due to big image sizes*/

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}