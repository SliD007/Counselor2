package test.example.com.counselor.view.service.showworklog;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.example.com.counselor.R;
import test.example.com.counselor.base.BaseActivity;

/**
 * Created by Sli.D on 2018/1/20.
 */

public class ShowImageActivity extends BaseActivity {

    @BindView(R.id.titleBarTv)
    TextView titleBarTv;
    @BindView(R.id.showImage)
    ImageView showImage;
    @BindView(R.id.showImageName)
    TextView showImageName;
    int imageIndex = 0;
    String images[] = new String[]{};
    Bitmap bitmap = null;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_showimage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        titleBarTv.setText("");
        super.allow_quit = false;

        Intent i = getIntent();
        String imageUrl = i.getStringExtra("imageUrl");
        images = imageUrl.split("#");


        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams para = showImage.getLayoutParams();
        para.height = width;
        para.width = width;
        showImage.setLayoutParams(para);

        bitmap = getLoacalBitmap(images[imageIndex]); //从本地取图片
        showImage.setImageBitmap(bitmap);
        showImageName.setText(images[imageIndex].split("/")[images[imageIndex].split("/").length-1]);
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @OnClick({R.id.backTv, R.id.showForwordImage, R.id.showNextImage})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backTv:
                break;
            case R.id.showForwordImage:
                imageIndex -- ;
                if(imageIndex>-1){
                    bitmap = getLoacalBitmap(images[imageIndex]); //从本地取图片
                    showImage.setImageBitmap(bitmap);
                    showImageName.setText(images[imageIndex].split("/")[images[imageIndex].split("/").length-1]);
                }else {
                    toast("没有了",false);
                }
                break;
            case R.id.showNextImage:
                imageIndex ++ ;
                if(imageIndex<images.length){
                    bitmap = getLoacalBitmap(images[imageIndex]); //从本地取图片
                    showImage.setImageBitmap(bitmap);
                    showImageName.setText(images[imageIndex].split("/")[images[imageIndex].split("/").length-1]);
                }else {
                    toast("没有了",false);
                }

                break;
        }
    }
}
