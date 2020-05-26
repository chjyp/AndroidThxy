package com.example.androidthxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class DemoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    LoopThread thread;
    public DemoSurfaceView(Context context) {
        super(context);
        init();
    }

    private void init() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new LoopThread(holder, getContext());
    }

    public DemoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.isRunning = true;
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRunning = false;
        try{
          thread.join();
        } catch (InterruptedException e){

        }

    }

    class LoopThread extends  Thread{
        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;
        Paint paint;

        public LoopThread(SurfaceHolder surfaceHolder, Context context) {
            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
        }

        @Override
        public void run() {
            Canvas c = null;
            while(isRunning){
                try{
                    c = surfaceHolder.lockCanvas(null);
                    doDraw(c);
                    surfaceHolder.unlockCanvasAndPost(c);
                    Thread.sleep(50);
                } catch (InterruptedException e){

                }
            }

        }

        public void doDraw(Canvas c){
            c.drawColor(Color.BLACK);
            c.translate(200, 200);
            c.drawCircle(0, 0, radius++, paint);
            if (radius > 100)
                radius = 10f;
        }
    }
}
