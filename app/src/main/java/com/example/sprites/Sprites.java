package com.example.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;

public class Sprites {
    TestSurfaceView testSurfaceView;
    Bitmap image;
    Paint paint = new Paint();
    float width, height, touchX = -1000, touchY = -1000, stepX = 0, stepY = 0;
    Pair<Float, Float> current;
    public Sprites(Bitmap img, TestSurfaceView view, Pair<Float, Float> point) {
        image = img;
        testSurfaceView = view;
        current = point;
    }

    public  void draw(Canvas canvas){
        canvas.drawBitmap(image, new Rect(0, 0, 10, 10), new Rect(0, 0, 30, 30), paint);
    }
}
