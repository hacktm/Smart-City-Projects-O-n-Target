package com.enjoycity.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

import com.enjoycity.enjoycity.R;

/**
 * Created by norbert on 10/19/14.
 */
public class Utils {

    private Context _context;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

    // Reading file paths from SDCard
    public ArrayList<Bitmap> getCategoryGridFiles() {
        ArrayList<Bitmap> files = new ArrayList<Bitmap>();

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;


        Bitmap icon0 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.art_grid, options);
        Bitmap icon1 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.beauty_grid, options);
        Bitmap icon2 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.cinema_grid, options);
        Bitmap icon3 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.culture_grid, options);
        Bitmap icon4 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.food_grid, options);
        Bitmap icon5 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.new_in_city_grid, options);
        Bitmap icon6 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.party_grid, options);
        Bitmap icon7 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.sport_grid, options);
        Bitmap icon8 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.travel_grid, options);
        Bitmap icon9 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.wellness_grid, options);

       files.add(icon0);
       files.add(icon1);
       files.add(icon2);
       files.add(icon3);
       files.add(icon4);
       files.add(icon5);
       files.add(icon6);
       files.add(icon7);
       files.add(icon8);
       files.add(icon9);

        return files;
    }

    // Reading file paths from SDCard
    public ArrayList<Bitmap> getCategorySlideFiles() {
        ArrayList<Bitmap> files = new ArrayList<Bitmap>();

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        //options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap icon0 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.art, options);
        Bitmap icon1 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.beauty, options);
        Bitmap icon2 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.cinema, options);
        Bitmap icon3 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.culture, options);
        Bitmap icon4 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.food, options);
        Bitmap icon5 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.new_in_city, options);
        Bitmap icon6 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.party, options);
        Bitmap icon7 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.sport, options);
        Bitmap icon8 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.travel, options);
        Bitmap icon9 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.wellness, options);

        Bitmap icon01 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.art1, options);
        Bitmap icon11 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.beauty1, options);
        Bitmap icon21 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.cinema1, options);
        Bitmap icon31 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.culture1, options);
        Bitmap icon41 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.food1, options);
        Bitmap icon51 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.new_in_city1, options);
        Bitmap icon61 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.party1, options);
        Bitmap icon71 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.sport1, options);
        Bitmap icon81 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.travel1, options);
        Bitmap icon91 = BitmapFactory.decodeResource(_context.getResources(), R.drawable.wellness1, options);

        files.add(icon0);
        files.add(icon1);
        files.add(icon2);
        files.add(icon3);
        files.add(icon4);
        files.add(icon5);
        files.add(icon6);
        files.add(icon7);
        files.add(icon8);
        files.add(icon9);

        files.add(icon01);
        files.add(icon11);
        files.add(icon21);
        files.add(icon31);
        files.add(icon41);
        files.add(icon51);
        files.add(icon61);
        files.add(icon71);
        files.add(icon81);
        files.add(icon91);

        return files;
    }

    // Check supported file extensions
    private boolean IsSupportedFile(String filePath) {
        String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
                filePath.length());

        if (AppConstant.FILE_EXTN
                .contains(ext.toLowerCase(Locale.getDefault())))
            return true;
        else
            return false;

    }

    /*
     * getting screen width
     */
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }
}