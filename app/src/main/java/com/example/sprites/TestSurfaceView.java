package com.example.sprites;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        canvas.drawBitmap(img,0, 0, paint);
        //canvas.drawARGB(1,255,0, 0);
    }

    final String TAG ="SPRITES";
    SurfaceThread thread;
    boolean touchevent = false;
    Sprites sprite;
    Paint paint;
    float width, height, currentX, currentY, touchX = -1000, touchY = -1000, stepX = 0, stepY = 0;
    Bitmap img, sprite_image;

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        img = BitmapFactory.decodeResource(getResources(), R.drawable.fffk);
        sprite = new Sprites(null, null, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        event.getX();
        event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            touchevent = true;

        }

        return true;
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread = new SurfaceThread(getHolder(), this);
        thread.start();



    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.running = false;
        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    class SurfaceThread extends Thread{
        boolean running = true;
        SurfaceHolder holder;
        TestSurfaceView view1;
        Paint paint = new Paint();

        public SurfaceThread(SurfaceHolder holder, TestSurfaceView surfaceview1) {

            view1 = surfaceview1;
            this.holder = holder;

        }

        @Override
        public void run(){
            while (running){
                Canvas canvas = null;
                canvas = holder.lockCanvas();
                synchronized (holder){
                    if (canvas!= null) draw(canvas);
                }
                if(canvas != null) holder.unlockCanvasAndPost(canvas);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}