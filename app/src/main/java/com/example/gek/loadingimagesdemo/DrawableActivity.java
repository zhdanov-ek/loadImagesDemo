package com.example.gek.loadingimagesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class DrawableActivity extends AppCompatActivity {

    ImageView ivTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        ivTest = (ImageView) findViewById(R.id.ivTest);

        CircleTransform circleTransform = new CircleTransform(this);
        Glide.with(this)
                .load(R.drawable.person_default)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .transform(circleTransform)
                .into(ivTest);
    }
}
