package com.example.akm.doodler434;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import java.util.ArrayList;

/**
 * Created by akm on 3/9/16.
 */
public class DoodleView extends View {

    private Paint _paintDoodle = new Paint();
    private Paint canvasPaint;
    private Path _path = new Path();
    private Bitmap canvasBitmap;
    private Canvas slate;
    private Canvas Saved = null;
    private ArrayList<Path> paths = new ArrayList<Path>();

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        _paintDoodle.setColor(Color.RED);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeWidth(20);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    public void clearDoodle() {
        slate.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

    public void setColorR(){
        _paintDoodle.setColor(Color.RED);
    }

    public void setColorY(){
        _paintDoodle.setColor(Color.YELLOW);
    }

    public void setColorG(){
        _paintDoodle.setColor(Color.GREEN);
    }

    public void setColorB(){
        _paintDoodle.setColor(Color.BLUE);
    }

    public void setColorM(){
        _paintDoodle.setColor(Color.MAGENTA);
    }

    public void setSize(int size) {
        _paintDoodle.setStrokeWidth(size);
    }

    public void Restore() {
        while (paths.size() > 0) {
            Path p = paths.remove(0);
            slate.drawPath(p, _paintDoodle);

        }
    }

    public void changeOpacity() {
        float opacity = _paintDoodle.getAlpha();
        if (opacity > 249)
            _paintDoodle.setAlpha(50);
        else {
            _paintDoodle.setAlpha((int) opacity + 50);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        slate = new Canvas(canvasBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(_path, _paintDoodle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                slate.drawPath(_path, _paintDoodle);
                paths.add(_path);
                _path.reset();
                break;
        }
        invalidate();
        return true;
    }
}
