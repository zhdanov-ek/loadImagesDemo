package com.example.gek.loadingimagesdemo;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnGlide, btnPicasso;
    ImageView iv1, iv2, iv3, iv4, iv5;
    ArrayList<String> links;
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        links = Photos.getLinks();
        ctx = this;

        btnGlide = (Button) findViewById(R.id.btnGlide);
        btnGlide.setOnClickListener(clickListener);
        btnPicasso = (Button) findViewById(R.id.btnPicasso);
        btnPicasso.setOnClickListener(clickListener);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        iv5 = (ImageView) findViewById(R.id.iv5);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGlide:

                    break;

                case R.id.btnPicasso:

                    break;

            }

        }
    };

}
