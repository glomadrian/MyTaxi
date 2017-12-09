package com.github.glomadrian.customviews.curvedlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.github.glomadrian.customviews.R;

public class CurvedLayout extends FrameLayout {

  private Path basePath;
  private Path strokePath;
  private Paint basePaint;
  private Paint strokePaint;
  private float curveLength = 350;
  private int color = Color.BLACK;
  private int pressedColor;
  private float strokeWidth = 20;
  private int strokeColor = Color.BLUE;
  private ValueAnimator valueAnimator;
  private MiddlePointListener middlePointListener;

  public CurvedLayout(Context context) {
    super(context);
    init();
  }

  public CurvedLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    initAttributes(attrs);
    init();
  }

  public CurvedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initAttributes(attrs);
    init();
  }

  private void init() {
    basePaint = new Paint();
    basePaint.setAntiAlias(true);
    basePaint.setColor(color);
    basePaint.setStyle(Paint.Style.FILL);
    strokePaint = new Paint();
    strokePaint.setStrokeWidth(strokeWidth);
    strokePaint.setAntiAlias(true);
    strokePaint.setColor(strokeColor);
    strokePaint.setStyle(Paint.Style.STROKE);
    setWillNotDraw(false);
  }

  private void initAttributes(AttributeSet attributeSet) {
    TypedArray attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CurvedLayout);
    curveLength = attributes.getDimension(R.styleable.CurvedLayout_curved_layout_curve_length, curveLength);
    strokeWidth = attributes.getDimension(R.styleable.CurvedLayout_curved_layout_stroke_width, strokeWidth);
    color = attributes.getColor(R.styleable.CurvedLayout_curved_layout_color, color);
    pressedColor = attributes.getColor(R.styleable.CurvedLayout_curved_layout_pressed_color, color);
    strokeColor = attributes.getColor(R.styleable.CurvedLayout_curved_layout_stroke_color, strokeColor);
    attributes.recycle();
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    this.basePath = generateCurvedPath(w, h);
    this.strokePath = generateStrokePath(w, h);
    createCurveValueAnimator();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawPath(basePath, basePaint);
    if (strokeWidth > 0) {
      canvas.drawPath(strokePath, strokePaint);
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (!isPointInsidePathArea((int) event.getX(), (int) event.getY(), basePath)) {
      if (event.getAction() == MotionEvent.ACTION_UP) {
        changeBasePaintColor(color);
      }
      return true;
    }
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        changeBasePaintColor(pressedColor);
        break;
      case MotionEvent.ACTION_UP:
      case MotionEvent.ACTION_CANCEL:
        changeBasePaintColor(color);
        break;
    }
    return super.onTouchEvent(event);
  }

  private void changeBasePaintColor(int color) {
    basePaint.setColor(color);
    invalidate();
  }

  private boolean isPointInsidePathArea(int x, int y, Path path) {
    RectF rectF = new RectF();
    path.computeBounds(rectF, true);
    Region region = new Region();
    region.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
    return region.contains(x, y);
  }

  private Path generateCurvedPath(int width, int height) {
    int halfWidth = width / 2;
    int start = 0;
    Path path = new Path();
    path.moveTo(start, start);
    path.quadTo(halfWidth, curveLength, width, start);
    path.lineTo(width, height);
    path.lineTo(0, height);
    path.close();
    return path;
  }

  private Path generateStrokePath(int width, int height) {
    int halfWidth = width / 2;
    int start = 0;
    Path path = new Path();
    path.moveTo(start, start);
    path.quadTo(halfWidth, curveLength, width, start);
    return path;
  }

  public void setCurvePercent(int percent) {
    valueAnimator.setCurrentPlayTime(percent);
  }

  public void setCurvedBackgroundColor(int color) {
    this.color = color;
    basePaint.setColor(color);
  }

  private ValueAnimator createCurveValueAnimator() {
    valueAnimator = ValueAnimator.ofFloat(curveLength, 0);
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        curveLength = (float) animation.getAnimatedValue();
        strokePath = generateStrokePath(getWidth(), getHeight());
        basePath = generateCurvedPath(getWidth(), getHeight());
        if (middlePointListener != null) {
          middlePointListener.onUpdate(getMiddlePoint());
        }
        invalidate();
      }
    });
    valueAnimator.setDuration(100);
    return valueAnimator;
  }

  private FloatPoint getMiddlePoint() {
    float[] coordinates = getPathCoordinates(strokePath, 0.5f);
    FloatPoint point = new FloatPoint(coordinates[0], coordinates[1]);
    return point;
  }

  private float[] getPathCoordinates(Path path, float fraction) {
    float aCoordinates[] = { 0f, 0f };
    PathMeasure pm = new PathMeasure(path, false);
    pm.getPosTan(pm.getLength() * fraction, aCoordinates, null);
    return aCoordinates;
  }

  public void setMiddlePointListener(MiddlePointListener middlePointListener) {
    this.middlePointListener = middlePointListener;
  }

  public FloatPoint getCurvedMiddlePoint() {
    return getMiddlePoint();
  }

  public interface MiddlePointListener {
    void onUpdate(FloatPoint point);
  }
}
