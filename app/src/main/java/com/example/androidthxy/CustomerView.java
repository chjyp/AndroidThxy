package com.example.androidthxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomerView extends View {
    public CustomerView(Context context) {
        super(context);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.save();
        canvas.rotate(90, 100, 100);
        canvas.drawLine(10, 20, 30, 30, paint);
        canvas.restore();

        Rect rect = new Rect(300, 200, 350, 250);
        canvas.drawRect(rect, paint);
    }
}
