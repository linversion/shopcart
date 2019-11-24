package com.linversion.commondemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;

import androidx.annotation.Nullable;

public class MyCheckBox extends View implements Checkable {

    Paint mCircleStrokePaint;
    Paint mNikePaint;
    Paint mCircleFillPaint;

    private boolean isChecked = false;
    private boolean invalid = false;
    private OnCheckedChangeListener onCheckedChangeListener;

    public MyCheckBox(Context context) {
        super(context);
        init();
    }

    public MyCheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCircleStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleStrokePaint.setColor(Color.BLUE);
        mCircleStrokePaint.setStrokeWidth(2f);
        mCircleStrokePaint.setStyle(Paint.Style.STROKE);

        mCircleFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleFillPaint.setColor(getResources().getColor(R.color.color_green_00EE00));

        mNikePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNikePaint.setColor(Color.WHITE);
        mNikePaint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int r = Math.min(width, height) / 2 - 2;


        if (invalid) {
            canvas.drawCircle(width / 2, height / 2, r, mCircleStrokePaint);
            mCircleFillPaint.setColor(getResources().getColor(R.color.color_gray_7FFFD4));
            canvas.drawCircle(width / 2, height / 2, r, mCircleFillPaint);
        } else if (isChecked) {

            canvas.drawCircle(width / 2, height / 2, r+2, mCircleFillPaint);

            canvas.drawLine(r / 2, r, r, (3 * r) / 2, mNikePaint);
            canvas.drawLine(r, (3 * r) / 2, (6 * r) / 4, r / 2, mNikePaint);
        } else {
            canvas.drawCircle(width / 2, height / 2, r, mCircleStrokePaint);
        }
    }


    @Override
    public void setChecked(boolean checked) {
        isChecked = checked;
        invalidate();
    }

    @Override
    public boolean isChecked() {
        return isChecked;

    }

    @Override
    public void toggle() {
        setChecked(!isChecked);

    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                if (x + getLeft() < getRight() && y + getTop() < getBottom()) {
                    if (!invalid) {
                        toggle();
                    }
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.OnCheckedChange(isChecked);
                    }
                }

                break;
                default:
        }
        return true;
    }

    interface OnCheckedChangeListener{
        void OnCheckedChange(boolean isChecked);
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }
}
