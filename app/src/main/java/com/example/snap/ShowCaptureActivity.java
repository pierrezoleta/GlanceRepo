package com.example.snap;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class ShowCaptureActivity extends AppCompatActivity {

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_capture);
        try {
            bitmap = BitmapFactory.decodeStream(getApplication().openFileInput("imageToSend"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }

        ImageView mImage = findViewById(R.id.imageCaptured);
        mImage.setImageBitmap(bitmap);


//        Bundle extras = getIntent().getExtras();
//        assert extras != null;
//        byte[] b = extras.getByteArray("capture");

//        if(b!=null){
//            ImageView image = findViewById(R.id.imageCaptured);
//
////            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
////
////            Bitmap rotateBitmap = rotate(decodedBitmap);
//
//
//
//            image.setImageBitmap(decodedBitmap);
//        }



    }

//    private Bitmap rotate(Bitmap decodedBitmap) {
//        int w = decodedBitmap.getWidth();
//        int h = decodedBitmap.getHeight();
//
//        Matrix matrix = new Matrix();
//        matrix.setRotate(90);
//
//        return Bitmap.createBitmap(decodedBitmap, 0,0 , w, h, matrix, true);
//    }
}