package com.example.gek.loadingimagesdemo;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Сравнение глайд и пикассо
 * https://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en
 * <p>
 * Демонстрация работы с библиотеками
 */

public class MainActivity extends AppCompatActivity {

    Button btnLoad;
    ImageView ivG0, ivG1, ivG2, ivG3, ivG4;
    ImageView ivP0, ivP1, ivP2, ivP3, ivP4;
    ArrayList<String> links;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        links = Photos.getLinks();
        ctx = this;

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(clickListener);

        ivG0 = (ImageView) findViewById(R.id.ivG0);
        ivG1 = (ImageView) findViewById(R.id.ivG1);
        ivG2 = (ImageView) findViewById(R.id.ivG2);
        ivG3 = (ImageView) findViewById(R.id.ivG3);
        ivG4 = (ImageView) findViewById(R.id.ivG4);

        ivP0 = (ImageView) findViewById(R.id.ivP0);
        ivP1 = (ImageView) findViewById(R.id.ivP1);
        ivP2 = (ImageView) findViewById(R.id.ivP2);
        ivP3 = (ImageView) findViewById(R.id.ivP3);
        ivP4 = (ImageView) findViewById(R.id.ivP4);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Glide.with(ctx)
                    .load(R.drawable.person_default)
                    .into(ivG0);
            Glide.with(ctx)
                    .load(links.get(1))
                    .into(ivG1);
            Glide.with(ctx)
                    .load(links.get(2))
                    .into(ivG2);
            Glide.with(ctx)
                    .load(links.get(3))
                    .into(ivG3);
            Glide.with(ctx)
                    .load(links.get(4))
                    .into(ivG4);

            Picasso.with(ctx)
                    .load(R.drawable.person_default)
                    .into(ivP0);
            Picasso.with(ctx)
                    .load(Uri.parse(links.get(1)))
                    .into(ivP1);
            Picasso.with(ctx)
                    .load(Uri.parse(links.get(2)))
                    .into(ivP2);
            Picasso.with(ctx)
                    .load(Uri.parse(links.get(3)))
                    .into(ivP3);
            Picasso.with(ctx)
                    .load(Uri.parse(links.get(4)))
                    .into(ivP4);

        }
    };

}
