package com.example.apptopet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

public class Utils {
    public static int calculateNumberOfColumns(Context context, float columnWidth) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidth = displayMetrics.widthPixels;
        return (int) (screenWidth / columnWidth + 0.5);
    }

    public static Bitmap getScaledBitmap(String imagePath, int w, int h) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.max(photoW/w, photoH/h);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        return BitmapFactory.decodeFile(imagePath, bmOptions);
    }

    public static Bitmap getBitmap(String imagePath) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath, bmOptions);
    }
}
