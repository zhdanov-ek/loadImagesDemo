package com.example.gek.loadingimagesdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

public class ShowImageActivity extends AppCompatActivity {

    ImageView imageView;
    String link;
    int lib;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        imageView = (ImageView) findViewById(R.id.imageView);
        ctx = this;


        Intent intent = getIntent();
        link = intent.getExtras().getString(MainActivity.URL_LINK);
        lib = intent.getExtras().getInt(MainActivity.LIBRARY);

        // При выводе больших картинок крайне важно обрезать их до нужного размера
        // Иначе размер Bitmap будет слишком большим и процесс загрузки растет

        if (lib == MainActivity.LIB_GLIDE){
            Glide.with(ctx)
                    .load(link)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.person_default)
                    .into(imageView);

        }else {
            Picasso.with(ctx)
                    .load(Uri.parse(link))
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.person_default)
                    .into(imageView);
        }


    }

}
