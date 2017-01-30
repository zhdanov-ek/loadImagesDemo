package com.example.gek.loadingimagesdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Сравнение глайд и пикассо
 * https://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en
 *
 * Демонстрация работы с библиотеками
 *
 * Отличия библиотек:
 * Glide испольует Bitmap  RGB_565, а Picasso - ARGB_8888. Это позволяет глайду использовать меньше
 * памяти но где-то терять в качестве. Если необходимо то эту опцию можно переопределить у глайд
 *
 * Глайд загружает картинку и кеширует ее уже измененную в размере (под ImageView), а пикассо
 * кеширует картинку полного размера и меняет ее размер каждый раз при выводе (за счет этого он
 * дольше выводит ее потому, что каждый раз меняет размер). Если нужно будет глайду вывести картинку
 * в ImageView другого размера то картинка будет скачиваться снова, а пикассо этого делать уже не
 * будет. Глайд также можно заставить кешировать полноразмерные картинки.
 *
 * Глайд использует не только контекст (как пикассо) но и активити или фрагмент, что позволяет
 * привязываться к жизненному циклу активити: паузы и т.д.
 *
 * Таким образом глайд быстрее выводит картинку но ему может потребоваться чаще ее скачивать.
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLoad, btnGlide, btnPicasso, btnDrawable;
    ImageView ivG0, ivG1, ivG2, ivG3, ivG4;
    ImageView ivP0, ivP1, ivP2, ivP3, ivP4;
    ArrayList<String> links;
    Context ctx;

    public static final String LIBRARY = "library";
    public static final int LIB_GLIDE = 1;
    public static final int LIB_PICASSO = 2;
    public static final String URL_LINK = "url_link";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        links = Photos.getLinks();
        ctx = this;

        btnDrawable = (Button) findViewById(R.id.btnDrawable);
        btnDrawable.setOnClickListener(clickListener);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(clickListener);
        btnGlide = (Button) findViewById(R.id.btnGlide);
        btnGlide.setOnClickListener(clickListener);
        btnPicasso = (Button) findViewById(R.id.btnPicasso);
        btnPicasso.setOnClickListener(clickListener);

        ivG0 = (ImageView) findViewById(R.id.ivG0);
        ivG1 = (ImageView) findViewById(R.id.ivG1);
        ivG1.setOnClickListener(this);
        ivG2 = (ImageView) findViewById(R.id.ivG2);
        ivG3 = (ImageView) findViewById(R.id.ivG3);
        ivG4 = (ImageView) findViewById(R.id.ivG4);
        ivG4.setOnClickListener(this);

        ivP0 = (ImageView) findViewById(R.id.ivP0);
        ivP1 = (ImageView) findViewById(R.id.ivP1);
        ivP1.setOnClickListener(this);
        ivP2 = (ImageView) findViewById(R.id.ivP2);
        ivP3 = (ImageView) findViewById(R.id.ivP3);
        ivP4 = (ImageView) findViewById(R.id.ivP4);
        ivP4.setOnClickListener(this);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnGlide:
                    loadGlide();
                    break;

                case R.id.btnPicasso:
                    loadPicasso();
                    break;

                case R.id.btnLoad:
                    loadGlide();
                    loadPicasso();
                    break;
                case R.id.btnDrawable:
                    startActivity(new Intent(getBaseContext(), DrawableActivity.class));
                    break;
            }



        }
    };

    private void loadGlide(){
        Glide.with(ctx)
                .load(R.drawable.person_default)
                .fitCenter()
                .into(ivG0);



        // Включаем кеширование на диске оригинала изображения.
        // Такое же кеширование нужно указать и в другом активити где это изображение будет выводиться
        // в другом разрешении
        CircleTransform circleTransform = new CircleTransform(this);
        BlurTransformation blurTransformation = new BlurTransformation(this);

        Glide.with(ctx)
                .load(links.get(1))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .fitCenter()
                .transform(circleTransform)
                .into(ivG1);
        Glide.with(ctx)
                .load(links.get(2))
                .fitCenter()
                .into(ivG2);
        Glide.with(ctx)
                .load(links.get(3))
                .fitCenter()
                .into(ivG3);
        Glide.with(ctx)
                .load(links.get(4))
                .fitCenter()
                .into(ivG4);
    }

    private void loadPicasso(){
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ctx, ShowImageActivity.class);
        switch (view.getId()){
            case R.id.ivG1:
                intent.putExtra(LIBRARY, LIB_GLIDE);
                intent.putExtra(URL_LINK, links.get(1));
                break;
            case R.id.ivG4:
                intent.putExtra(LIBRARY, LIB_GLIDE);
                intent.putExtra(URL_LINK, links.get(4));
                break;
            case R.id.ivP1:
                intent.putExtra(LIBRARY, LIB_PICASSO);
                intent.putExtra(URL_LINK, links.get(1));
                break;
            case R.id.ivP4:
                intent.putExtra(LIBRARY, LIB_PICASSO);
                intent.putExtra(URL_LINK, links.get(4));
                break;
        }
        ctx.startActivity(intent);
    }


}
