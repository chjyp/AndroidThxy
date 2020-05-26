package com.example.androidthxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class MyClock extends View {
    private int hour, minute, second;
    private Paint circlePaint, linePaint, timerPaint;
    private int radius, topY;
    private Handler handler;
    private boolean isRunning = false;

    public MyClock(Context context) {
        super(context);
        init();
    }

    public MyClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        hour = minute = 10;
        second = 20;
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        handler = new Handler();

        linePaint = new Paint();
        linePaint.setColor(0xFF000000);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(4.0f);

        timerPaint = new Paint();
        timerPaint.setColor(0xFF000000);
        timerPaint.setAntiAlias(true);
        timerPaint.setStrokeWidth(6.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 1、绘制时钟的圆圈
        // 1.1 计算外圆的半径
        radius = Math.min(this.getWidth(), this.getHeight()) / 2 - 18;
        // 1.2 绘制外圆
        circlePaint.setColor(0xFF000000);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, radius, circlePaint );
        // 1.3 绘制内圆
        circlePaint.setColor(0xFFFFFFFF);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, radius - 8, circlePaint);

        // 2.绘制时钟表盘
        if (this.getWidth() > this.getHeight())
            topY = 18;
        else
            topY = (this.getHeight() - this.getWidth()) / 2 + 18;
        canvas.save();
        for (int i = 0; i < 12; i++){
            canvas.drawLine(this.getWidth() / 2, topY, this.getWidth() / 2, topY + 20, linePaint);
            canvas.rotate(30, this.getWidth() / 2, this.getHeight() / 2);
        }
        canvas.restore();

        // 3.绘制时针、分针和秒针
        canvas.save();
        canvas.rotate(hour * 30  + minute / 2, this.getWidth() / 2, this.getHeight() / 2);
        timerPaint.setStrokeWidth(10.0f);
        canvas.drawLine(this.getWidth() /2 , this.getHeight() /2 , this.getWidth() / 2, topY + (this.getHeight() / 2 - topY) * 2 /3 , timerPaint );
        canvas.restore();

        canvas.save();
        canvas.rotate( minute * 6, this.getWidth() / 2, this.getHeight() / 2);
        timerPaint.setStrokeWidth(8.0f);
        canvas.drawLine(this.getWidth() /2 , this.getHeight() /2 , this.getWidth() / 2, topY + (this.getHeight() / 2 - topY)  / 2 , timerPaint );
        canvas.restore();

        canvas.save();
        canvas.rotate( second * 6, this.getWidth() / 2, this.getHeight() / 2);
        timerPaint.setStrokeWidth(6.0f);
        canvas.drawLine(this.getWidth() /2 , this.getHeight() /2 , this.getWidth() / 2, topY + (this.getHeight() / 2 - topY)  / 3 , timerPaint );
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isRunning = true;
        Thread t = new Thread(new TimerThread());
        t.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
    }

    private class TimerThread implements  Runnable{
        @Override
        public void run() {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                second = c.get(Calendar.SECOND);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        MyClock.this.invalidate();
                    }
                });
            }
        }
    }
}
