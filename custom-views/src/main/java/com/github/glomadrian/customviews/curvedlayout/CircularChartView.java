package com.github.glomadrian.customviews.curvedlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ScrollView;
import com.github.glomadrian.customviews.R;
import java.util.Locale;

public class CircularChartView extends View {

  private static final long DEFAULT_ANIMATION_DURATION = 1000;
  private static final float PERCENT_100 = 100;
  private static final float DEGREES_90 = 90;
  private static final float DEGREES_360 = 360;
  private static final float CIRCULAR_PERCENT_MULTIPLIER = 3.6f;
  private static final float VALUE_NOT_DEFINED = -1;
  private static final String PERCENT_TEXT = "%";
  private static final String NOT_DEFINED_TEXT = "-";
  private static final Locale FORMAT_LOCALE = java.util.Locale.US;
  private static final String FORMAT_FLOAT_PRECISION = "%.2f";
  private float strokeWidth = 60;
  private Paint arcsPaint;
  private Paint globalPercentTextPaint;
  private int firstArcColor = Color.GREEN;
  private int secondArcColor = Color.RED;
  private int noValueArcColor = Color.GRAY;
  private int arcSeparatorsAngle = 8;
  private int firstValuePercent = 75;
  private int secondValuePercent = 25;
  private Arc firstArc;
  private Arc secondArc;
  private Arc noValueArc;
  private RectF oval = new RectF();
  private float globalValue = VALUE_NOT_DEFINED;
  private float globalCurrentValue;
  private float globalValueTextSize = 90;
  private int globalValueTextColor = Color.BLACK;
  private boolean showGlobalTextAsPercent = true;
  private boolean alreadyAnimated;

  public CircularChartView(Context context) {
    super(context);
    init();
  }

  public CircularChartView(Context context, AttributeSet attrs) {
    super(context, attrs);
    initAttributes(attrs);
    init();
  }

  public CircularChartView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initAttributes(attrs);
    init();
  }

  private void initAttributes(AttributeSet attributeSet) {
    TypedArray attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CircularChart);
    try {
      strokeWidth = attributes.getDimension(R.styleable.CircularChart_circular_chart_stroke_width, strokeWidth);
      firstArcColor = attributes.getColor(R.styleable.CircularChart_circular_chart_first_arc_color, firstArcColor);
      secondArcColor = attributes.getColor(R.styleable.CircularChart_circular_chart_second_arc_color, secondArcColor);
      noValueArcColor = attributes.getColor(R.styleable.CircularChart_circular_chart_no_value_arc_color, noValueArcColor);
      arcSeparatorsAngle =
      attributes.getInteger(R.styleable.CircularChart_circular_chart_separators_arc_angle, arcSeparatorsAngle);
      globalValueTextSize =
      attributes.getDimension(R.styleable.CircularChart_circular_chart_global_percent_text_size, globalValueTextSize);
      globalValueTextColor =
      attributes.getColor(R.styleable.CircularChart_circular_chart_global_percent_text_color, globalValueTextColor);
    } finally {
      attributes.recycle();
    }
  }

  private void init() {
    initArcsPaint();
    initGlobalPercentTextPaint();
    initArcs();
    initGlobalValue();
  }

  private void initArcsPaint() {
    arcsPaint = new Paint();
    arcsPaint.setStrokeWidth(strokeWidth);
    arcsPaint.setAntiAlias(true);
    arcsPaint.setStyle(Paint.Style.STROKE);
  }

  private void initGlobalPercentTextPaint() {
    globalPercentTextPaint = new Paint();
    globalPercentTextPaint.setAntiAlias(true);
    globalPercentTextPaint.setColor(globalValueTextColor);
    globalPercentTextPaint.setTextSize(globalValueTextSize);
    globalPercentTextPaint.setTextAlign(Paint.Align.CENTER);
  }

  private void initGlobalValue() {
    if (showGlobalTextAsPercent) {
      globalValue = firstValuePercent;
    }
  }

  private void initArcs() {
    initFirstArc();
    initSecondArc();
    initNoValueArc();
  }

  private void initFirstArc() {
    float firstArcAngle = firstValuePercent * CIRCULAR_PERCENT_MULTIPLIER;
    float firstArcStartAngle;
    float firstArcMaxSweepAngle;
    if (firstValuePercent == PERCENT_100) {
      firstArcStartAngle = -DEGREES_90;
      firstArcMaxSweepAngle = firstArcAngle;
    } else {
      firstArcStartAngle = -DEGREES_90 + (arcSeparatorsAngle / 2);
      firstArcMaxSweepAngle = firstArcAngle - arcSeparatorsAngle;
    }
    firstArc = new Arc(firstArcMaxSweepAngle, firstArcStartAngle);
  }

  private void initSecondArc() {
    float firstArcAngle = firstValuePercent * CIRCULAR_PERCENT_MULTIPLIER;
    float secondArcAngle = secondValuePercent * CIRCULAR_PERCENT_MULTIPLIER;
    float secondArcStartAngle;
    float secondArcMaxSweepAngle;
    if (secondValuePercent == PERCENT_100) {
      secondArcStartAngle = -DEGREES_90;
      secondArcMaxSweepAngle = secondArcAngle;
    } else {
      secondArcStartAngle = -DEGREES_90 + firstArcAngle + (arcSeparatorsAngle / 2);
      secondArcMaxSweepAngle = secondArcAngle - arcSeparatorsAngle;
    }
    secondArc = new Arc(secondArcMaxSweepAngle, secondArcStartAngle);
  }

  private void initNoValueArc() {
    noValueArc = new Arc(-DEGREES_90, -DEGREES_90);
    noValueArc.setCurrentSweepAngle(DEGREES_360);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    float halfStrokeWidth = strokeWidth / 2f;
    oval.set(halfStrokeWidth, halfStrokeWidth, w - halfStrokeWidth, h - halfStrokeWidth);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawPercent(canvas);
    if (hasAnyValue()) {
      if (firstValuePercent != 0) {
        drawFirstArc(canvas);
      }
      if (secondValuePercent != 0) {
        drawSecondArc(canvas);
      }
    } else {
      drawNotValueArc(canvas);
    }
  }

  private boolean hasAnyValue() {
    return firstValuePercent != 0 || secondValuePercent != 0;
  }

  private void drawPercent(Canvas canvas) {
    float yPos = ((canvas.getHeight() / 2) - ((globalPercentTextPaint.descent() + globalPercentTextPaint.ascent()) / 2));
    String formattedGlobalCurrentValue = getFormattedGlobalCurrentValue();
    canvas.drawText(formattedGlobalCurrentValue, getWidth() / 2, yPos, globalPercentTextPaint);
  }

  private String getFormattedGlobalCurrentValue() {
    String formattedGlobalCurrentValue;
    if (!hasAnimationStarted() || !hasAnyValue()) {
      formattedGlobalCurrentValue = NOT_DEFINED_TEXT;
    } else {
      if (!showGlobalTextAsPercent) {
        formattedGlobalCurrentValue = String.format(FORMAT_LOCALE, FORMAT_FLOAT_PRECISION, globalCurrentValue);
      } else {
        formattedGlobalCurrentValue = String.valueOf((int) globalCurrentValue);
      }
      if (showGlobalTextAsPercent) {
        formattedGlobalCurrentValue += PERCENT_TEXT;
      }
    }
    return formattedGlobalCurrentValue;
  }

  private boolean hasAnimationStarted() {
    return !(globalCurrentValue == 0 && globalValue != 0);
  }

  private void drawFirstArc(Canvas canvas) {
    arcsPaint.setColor(firstArcColor);
    firstArc.draw(oval, arcsPaint, canvas);
  }

  private void drawSecondArc(Canvas canvas) {
    arcsPaint.setColor(secondArcColor);
    secondArc.draw(oval, arcsPaint, canvas);
  }

  private void drawNotValueArc(Canvas canvas) {
    arcsPaint.setColor(noValueArcColor);
    noValueArc.draw(oval, arcsPaint, canvas);
  }

  public void setGlobalTextValue(float value) {
    this.showGlobalTextAsPercent = false;
    this.globalValue = value;
  }

  private boolean isVisibleInScrollView(ScrollView scrollView) {
    Rect scrollBounds = new Rect();
    scrollView.getHitRect(scrollBounds);
    return getLocalVisibleRect(scrollBounds);
  }

  public void setGlobalPercentTextTypeface(Typeface globalPercentTextTypeface) {
    globalPercentTextPaint.setTypeface(globalPercentTextTypeface);
  }

  public void setFirstValuePercent(int firstValuePercent) {
    this.firstValuePercent = firstValuePercent;
    initArcs();
    initGlobalValue();
  }

  public void setSecondValuePercent(int secondValuePercent) {
    this.secondValuePercent = secondValuePercent;
    initArcs();
    initGlobalValue();
  }

  private void onBecomeVisibleToParent() {
    if (!alreadyAnimated) {
      startAnimation();
      alreadyAnimated = true;
    }
  }

  public void startAnimation() {
    startAnimation(DEFAULT_ANIMATION_DURATION);
  }

  public void startAnimation(long animationDuration) {
    ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, DEGREES_360);
    valueAnimator.setDuration(animationDuration);
    valueAnimator.setInterpolator(new DecelerateInterpolator());
    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      private float prevArcDegrees = 0;

      @Override
      public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float degrees = (float) valueAnimator.getAnimatedValue();
        int isFirstArcInsideLimits = isDegreeValueInsideArcLimits(degrees, firstArc);
        int isSecondArcInsideLimits = isDegreeValueInsideArcLimits(degrees, secondArc);
        if (isFirstArcInsideLimits == 0) {
          firstArc.setCurrentSweepAngle(degrees);
          prevArcDegrees = degrees;
        } else if (isFirstArcInsideLimits == 1) {
          firstArc.setCurrentSweepAngle(firstArc.getMaxSweepAngle());
          prevArcDegrees = firstArc.getMaxSweepAngle();
        }
        if (isSecondArcInsideLimits == 0) {
          float secondArcDegrees = degrees - prevArcDegrees;
          if (secondArcDegrees <= secondArc.getMaxSweepAngle()) {
            secondArc.setCurrentSweepAngle(secondArcDegrees);
          } else {
            secondArc.setCurrentSweepAngle(secondArc.getMaxSweepAngle());
          }
        } else if (isSecondArcInsideLimits == 1) {
          secondArc.setCurrentSweepAngle(secondArc.getMaxSweepAngle());
        }
        if (showGlobalTextAsPercent) {
          globalCurrentValue = Math.round((degrees * globalValue) / DEGREES_360);
        } else {
          globalCurrentValue = (degrees * globalValue) / DEGREES_360;
        }
        invalidate();
      }
    });
    valueAnimator.start();
  }

  /**
   * Checks if a degree is inside arc limits
   *
   * @return 0 if is inside limits, -1 if is lower than limit, 1 if is greater than limit
   */
  private int isDegreeValueInsideArcLimits(float degrees, Arc arc) {
    if (degrees < (DEGREES_90 + arc.getStartAngle())) {
      return -1;
    } else if (degrees > arc.getMaxSweepAngle() + (DEGREES_90 + arc.getStartAngle())) {
      return 1;
    } else {
      return 0;
    }
  }

  private class Arc {

    private final float maxSweepAngle;
    private final float startAngle;
    private float currentSweepAngle;

    private Arc(float maxSweepAngle, float startAngle) {
      this.maxSweepAngle = maxSweepAngle;
      this.startAngle = startAngle;
    }

    private void draw(RectF oval, Paint paint, Canvas canvas) {
      canvas.drawArc(oval, startAngle, currentSweepAngle, false, paint);
    }

    public float getMaxSweepAngle() {
      return maxSweepAngle;
    }

    public float getStartAngle() {
      return startAngle;
    }

    public void setCurrentSweepAngle(float currentSweepAngle) {
      this.currentSweepAngle = currentSweepAngle;
    }
  }
}
