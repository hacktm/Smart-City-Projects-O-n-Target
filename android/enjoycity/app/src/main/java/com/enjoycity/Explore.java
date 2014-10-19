package com.enjoycity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.enjoycity.adapter.FullScreenImageAdapter;
import com.enjoycity.enjoycity.R;
import com.enjoycity.utils.Utils;

import java.util.ArrayList;

/**
 * Created by norbert on 10/19/14.
 */
public class Explore extends Activity {

    private Utils utils;
    private ArrayList<Bitmap> imagePaths = new ArrayList<Bitmap>();
    private FullScreenImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.explore);

        ViewPager pageViewer = (ViewPager)findViewById(R.id.pager);

        utils = new Utils(this);
        imagePaths = utils.getCategorySlideFiles();
        adapter = new FullScreenImageAdapter(Explore.this, imagePaths);

        pageViewer.setAdapter(adapter);
        pageViewer.setCurrentItem(0);
    }
}
