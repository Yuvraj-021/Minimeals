package com.example.mini.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;


import com.example.mini.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CurvedBottomNavigationView  extends BottomNavigationView {
    private Path mPath;
    private Paint mPaint;


    /** the CURVE_CIRCLE_RADIUS represent the radius of the fab button */
    private  int CURVE_CIRCLE_RADIUS ;//= (int)(220 / 1.7);
    // the coordinates of the first curve
    private Point mFirstCurveStartPoint = new Point();
    private Point mFirstCurveEndPoint = new Point();
    private Point mFirstCurveControlPoint1 = new Point();
    private Point mFirstCurveControlPoint2 = new Point();

    //the coordinates of the second curve
    @SuppressWarnings("FieldCanBeLocal")
    private Point mSecondCurveStartPoint = new Point();
    private Point mSecondCurveEndPoint = new Point();
    private Point mSecondCurveControlPoint1 = new Point();
    private Point mSecondCurveControlPoint2 = new Point();
    private int mNavigationBarWidth;
    private int mNavigationBarHeight;

    public CurvedBottomNavigationView(Context context) {
        super(context);
        init(null);
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CurvedBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @SuppressLint({"ResourceAsColor"})
    private void init(@Nullable AttributeSet set) {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Button b1;
      //  mPaint.setColor(Color.parseColor("#008577"));
        setBackgroundColor(Color.TRANSPARENT);

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.CurveBottomBar);
        int bottomBarColor = ta.getColor(R.styleable.CurveBottomBar_bottomBarColor, Color.rgb(255,255,255));
        //int fabcolor = ta.getColor(R.styleable., Color.rgb(255,0,0));
        CURVE_CIRCLE_RADIUS = ta.getDimensionPixelSize(R.styleable.CurveBottomBar_curveRadius, 140);

        mPaint.setColor(bottomBarColor);
        //mPaint.setColor(fabcolor);
      //  mPaint.setColor(R.drawable.drawable_purple_gradient);
      //  mPaint.setColor(R.color.colorPurple_900);
      //  mPaint.setColor(R.color.colorAmber_A400);
      //  mPaint.setColor(R.color.colorRed_900);

        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // get width and height of navigation bar
        // Navigation bar bounds (width & height)
        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();
        // the coordinates (x,y) of the start point before curve
        mFirstCurveStartPoint.set((mNavigationBarWidth / 2) - (CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
        // the coordinates (x,y) of the end point after curve
        mFirstCurveEndPoint.set(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
        // same thing for the second curve
        mSecondCurveStartPoint = mFirstCurveEndPoint;
        mSecondCurveEndPoint.set((mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);

        // the coordinates (x,y)  of the 1st control point on a cubic curve
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4), mFirstCurveStartPoint.y);
        // the coordinates (x,y)  of the 2nd control point on a cubic curve
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x - (CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);




        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)), mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0, 0);





        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y);

        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y);

        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y);

        mPath.lineTo(mNavigationBarWidth, 0);


        mPath.cubicTo(mNavigationBarWidth,mNavigationBarHeight,mSecondCurveEndPoint.x,mNavigationBarHeight,mFirstCurveEndPoint.x,mFirstCurveEndPoint.y);

        mPath.cubicTo(mFirstCurveStartPoint.x,mNavigationBarHeight,0,mNavigationBarHeight,0,0);

      //  mPath.cubicTo();

           // mPath.lineTo(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
       // mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight);
     //   mPath.lineTo(0, mNavigationBarHeight);//
     //   mPath.close();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);



    }

}