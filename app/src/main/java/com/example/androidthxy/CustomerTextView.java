package com.example.androidthxy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomerTextView extends TextView {
    public CustomerTextView(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"Vivaldi.ttf");
        this.setTypeface(typeface);
    }

    public CustomerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"Vivaldi.ttf");
        this.setTypeface(typeface);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
