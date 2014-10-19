package com.efor18.rangeseekbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.widget.ImageView;
import java.math.BigDecimal;

public class RangeSeekBar<T extends Number> extends ImageView
{
  public static final int ACTION_POINTER_INDEX_MASK = 65280;
  public static final int ACTION_POINTER_INDEX_SHIFT = 8;
  public static final int ACTION_POINTER_UP = 6;
  public static final int BACKGROUND_COLOR = -7829368;
  public static final int DEFAULT_COLOR = 0;
  public static final int INVALID_POINTER_ID = 255;
  public final boolean IS_MULTI_COLORED;
  public final int LEFT_COLOR;
  public final int MIDDLE_COLOR;
  public final int RIGHT_COLOR;
  public final int SINGLE_COLOR;
  private final T absoluteMaxValue;
  private final double absoluteMaxValuePrim;
  private final T absoluteMinValue;
  private final double absoluteMinValuePrim;
  private final float lineHeight;
  private OnRangeSeekBarChangeListener<T> listener;
  private int mActivePointerId = 255;
  private float mDownMotionX;
  private boolean mIsDragging;
  private int mScaledTouchSlop;
  float mTouchProgressOffset;
  private double normalizedMaxValue = 1.0D;
  private double normalizedMinValue = 0.0D;
  private boolean notifyWhileDragging = true;
  private final NumberType numberType;
  private final float padding;
  private final Paint paint = new Paint(1);
  private Thumb pressedThumb = null;
  private final float thumbHalfHeight;
  private final float thumbHalfWidth;
  private final Bitmap thumbImage;
  private final Bitmap thumbPressedImage;
  private final float thumbWidth;

  public RangeSeekBar(T paramT1, T paramT2, Context paramContext)
    throws IllegalArgumentException
  {
    super(paramContext);
    this.absoluteMinValue = paramT1;
    this.absoluteMaxValue = paramT2;
    this.absoluteMinValuePrim = paramT1.doubleValue();
    this.absoluteMaxValuePrim = paramT2.doubleValue();
    this.numberType = NumberType.fromNumber(paramT1);
    this.IS_MULTI_COLORED = false;
    this.SINGLE_COLOR = Color.argb(255, 51, 181, 229);
    this.LEFT_COLOR = 0;
    this.MIDDLE_COLOR = 0;
    this.RIGHT_COLOR = 0;
    this.thumbImage = BitmapFactory.decodeResource(getResources(), 2130837813);
    this.thumbPressedImage = BitmapFactory.decodeResource(getResources(), 2130837814);
    this.thumbWidth = this.thumbImage.getWidth();
    this.thumbHalfWidth = (0.5F * this.thumbWidth);
    this.thumbHalfHeight = (0.5F * this.thumbImage.getHeight());
    this.lineHeight = (0.3F * this.thumbHalfHeight);
    this.padding = this.thumbHalfWidth;
    setFocusable(true);
    setFocusableInTouchMode(true);
    init();
  }

  public RangeSeekBar(T paramT1, T paramT2, Context paramContext, int paramInt1, int paramInt2, int paramInt3)
    throws IllegalArgumentException
  {
    super(paramContext);
    this.absoluteMinValue = paramT1;
    this.absoluteMaxValue = paramT2;
    this.absoluteMinValuePrim = paramT1.doubleValue();
    this.absoluteMaxValuePrim = paramT2.doubleValue();
    this.numberType = NumberType.fromNumber(paramT1);
    this.IS_MULTI_COLORED = false;
    if (paramInt1 < 0);
    while (true)
    {
      this.SINGLE_COLOR = paramInt1;
      this.LEFT_COLOR = 0;
      this.MIDDLE_COLOR = 0;
      this.RIGHT_COLOR = 0;
      this.thumbImage = BitmapFactory.decodeResource(getResources(), paramInt2);
      this.thumbPressedImage = BitmapFactory.decodeResource(getResources(), paramInt3);
      this.thumbWidth = this.thumbImage.getWidth();
      this.thumbHalfWidth = (0.5F * this.thumbWidth);
      this.thumbHalfHeight = (0.5F * this.thumbImage.getHeight());
      this.lineHeight = (0.3F * this.thumbHalfHeight);
      this.padding = this.thumbHalfWidth;
      setFocusable(true);
      setFocusableInTouchMode(true);
      init();
      return;
      paramInt1 = Color.argb(255, 51, 181, 229);
    }
  }

  public RangeSeekBar(T paramT1, T paramT2, Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    throws IllegalArgumentException
  {
    super(paramContext);
    this.absoluteMinValue = paramT1;
    this.absoluteMaxValue = paramT2;
    this.absoluteMinValuePrim = paramT1.doubleValue();
    this.absoluteMaxValuePrim = paramT2.doubleValue();
    this.numberType = NumberType.fromNumber(paramT1);
    this.IS_MULTI_COLORED = true;
    this.SINGLE_COLOR = 0;
    if (paramInt1 < 0)
    {
      this.LEFT_COLOR = paramInt1;
      if (paramInt2 >= 0)
        break label235;
      label104: this.MIDDLE_COLOR = paramInt2;
      if (paramInt3 >= 0)
        break label251;
    }
    while (true)
    {
      this.RIGHT_COLOR = paramInt3;
      this.thumbImage = BitmapFactory.decodeResource(getResources(), paramInt4);
      this.thumbPressedImage = BitmapFactory.decodeResource(getResources(), paramInt5);
      this.thumbWidth = this.thumbImage.getWidth();
      this.thumbHalfWidth = (0.5F * this.thumbWidth);
      this.thumbHalfHeight = (0.5F * this.thumbImage.getHeight());
      this.lineHeight = (0.3F * this.thumbHalfHeight);
      this.padding = this.thumbHalfWidth;
      setFocusable(true);
      setFocusableInTouchMode(true);
      init();
      return;
      paramInt1 = Color.argb(255, 255, 0, 0);
      break;
      label235: paramInt2 = Color.argb(255, 0, 255, 0);
      break label104;
      label251: paramInt3 = Color.argb(255, 0, 0, 255);
    }
  }

  private void attemptClaimDrag()
  {
    if (getParent() != null)
      getParent().requestDisallowInterceptTouchEvent(true);
  }

  private void drawThumb(float paramFloat, boolean paramBoolean, Canvas paramCanvas)
  {
    if (paramBoolean);
    for (Bitmap localBitmap = this.thumbPressedImage; ; localBitmap = this.thumbImage)
    {
      paramCanvas.drawBitmap(localBitmap, paramFloat - this.thumbHalfWidth, 0.5F * getHeight() - this.thumbHalfHeight, this.paint);
      return;
    }
  }

  private Thumb evalPressedThumb(float paramFloat)
  {
    boolean bool1 = isInThumbRange(paramFloat, this.normalizedMinValue);
    boolean bool2 = isInThumbRange(paramFloat, this.normalizedMaxValue);
    Thumb localThumb;
    if ((bool1) && (bool2))
      if (paramFloat / getWidth() > 0.5F)
        localThumb = Thumb.MIN;
    do
    {
      return localThumb;
      return Thumb.MAX;
      if (bool1)
        return Thumb.MIN;
      localThumb = null;
    }
    while (!bool2);
    return Thumb.MAX;
  }

  private final void init()
  {
    this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
  }

  private boolean isInThumbRange(float paramFloat, double paramDouble)
  {
    return Math.abs(paramFloat - normalizedToScreen(paramDouble)) <= this.thumbHalfWidth;
  }

  private float normalizedToScreen(double paramDouble)
  {
    return (float)(this.padding + paramDouble * (getWidth() - 2.0F * this.padding));
  }

  private T normalizedToValue(double paramDouble)
  {
    return this.numberType.toNumber(this.absoluteMinValuePrim + paramDouble * (this.absoluteMaxValuePrim - this.absoluteMinValuePrim));
  }

  private final void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = (0xFF00 & paramMotionEvent.getAction()) >> 8;
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId)
      if (i != 0)
        break label48;
    label48: for (int j = 1; ; j = 0)
    {
      this.mDownMotionX = paramMotionEvent.getX(j);
      this.mActivePointerId = paramMotionEvent.getPointerId(j);
      return;
    }
  }

  private double screenToNormalized(float paramFloat)
  {
    int i = getWidth();
    if (i <= 2.0F * this.padding)
      return 0.0D;
    return Math.min(1.0D, Math.max(0.0D, (paramFloat - this.padding) / (i - 2.0F * this.padding)));
  }

  private final void trackTouchEvent(MotionEvent paramMotionEvent)
  {
    float f = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId));
    if (Thumb.MIN.equals(this.pressedThumb))
      setNormalizedMinValue(screenToNormalized(f));
    do
      return;
    while (!Thumb.MAX.equals(this.pressedThumb));
    setNormalizedMaxValue(screenToNormalized(f));
  }

  private double valueToNormalized(T paramT)
  {
    if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim)
      return 0.0D;
    return (paramT.doubleValue() - this.absoluteMinValuePrim) / (this.absoluteMaxValuePrim - this.absoluteMinValuePrim);
  }

  public T getAbsoluteMaxValue()
  {
    return this.absoluteMaxValue;
  }

  public T getAbsoluteMinValue()
  {
    return this.absoluteMinValue;
  }

  public T getSelectedMaxValue()
  {
    return normalizedToValue(this.normalizedMaxValue);
  }

  public T getSelectedMinValue()
  {
    return normalizedToValue(this.normalizedMinValue);
  }

  public boolean isNotifyWhileDragging()
  {
    return this.notifyWhileDragging;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    monitorenter;
    try
    {
      super.onDraw(paramCanvas);
      this.paint.setStyle(Paint.Style.FILL);
      this.paint.setAntiAlias(true);
      if (!this.IS_MULTI_COLORED)
      {
        RectF localRectF1 = new RectF(this.padding, 0.5F * (getHeight() - this.lineHeight), getWidth() - this.padding, 0.5F * (getHeight() + this.lineHeight));
        this.paint.setColor(-7829368);
        paramCanvas.drawRect(localRectF1, this.paint);
        localRectF1.left = normalizedToScreen(this.normalizedMinValue);
        localRectF1.right = normalizedToScreen(this.normalizedMaxValue);
        this.paint.setColor(this.SINGLE_COLOR);
        paramCanvas.drawRect(localRectF1, this.paint);
      }
      while (true)
      {
        drawThumb(normalizedToScreen(this.normalizedMinValue), Thumb.MIN.equals(this.pressedThumb), paramCanvas);
        drawThumb(normalizedToScreen(this.normalizedMaxValue), Thumb.MAX.equals(this.pressedThumb), paramCanvas);
        return;
        RectF localRectF2 = new RectF(this.padding, 0.5F * (getHeight() - this.lineHeight), normalizedToScreen(this.normalizedMinValue), 0.5F * (getHeight() + this.lineHeight));
        this.paint.setColor(this.LEFT_COLOR);
        paramCanvas.drawRect(localRectF2, this.paint);
        RectF localRectF3 = new RectF(this.padding, 0.5F * (getHeight() - this.lineHeight), getWidth() - this.padding, 0.5F * (getHeight() + this.lineHeight));
        localRectF3.left = normalizedToScreen(this.normalizedMinValue);
        localRectF3.right = normalizedToScreen(this.normalizedMaxValue);
        this.paint.setColor(this.MIDDLE_COLOR);
        paramCanvas.drawRect(localRectF3, this.paint);
        RectF localRectF4 = new RectF(normalizedToScreen(this.normalizedMaxValue), 0.5F * (getHeight() - this.lineHeight), getWidth() - this.padding, 0.5F * (getHeight() + this.lineHeight));
        this.paint.setColor(this.RIGHT_COLOR);
        paramCanvas.drawRect(localRectF4, this.paint);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (View.MeasureSpec.getMode(paramInt1) != 0)
        {
          i = View.MeasureSpec.getSize(paramInt1);
          int j = this.thumbImage.getHeight();
          if (View.MeasureSpec.getMode(paramInt2) == 0)
            continue;
          j = Math.min(j, View.MeasureSpec.getSize(paramInt2));
          setMeasuredDimension(i, j);
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      int i = 200;
    }
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    Bundle localBundle = (Bundle)paramParcelable;
    super.onRestoreInstanceState(localBundle.getParcelable("SUPER"));
    this.normalizedMinValue = localBundle.getDouble("MIN");
    this.normalizedMaxValue = localBundle.getDouble("MAX");
  }

  protected Parcelable onSaveInstanceState()
  {
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("SUPER", super.onSaveInstanceState());
    localBundle.putDouble("MIN", this.normalizedMinValue);
    localBundle.putDouble("MAX", this.normalizedMaxValue);
    return localBundle;
  }

  void onStartTrackingTouch()
  {
    this.mIsDragging = true;
  }

  void onStopTrackingTouch()
  {
    this.mIsDragging = false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled())
      return false;
    switch (0xFF & paramMotionEvent.getAction())
    {
    case 4:
    default:
    case 0:
    case 2:
    case 1:
    case 5:
    case 6:
    case 3:
    }
    while (true)
    {
      return true;
      this.mActivePointerId = paramMotionEvent.getPointerId(-1 + paramMotionEvent.getPointerCount());
      this.mDownMotionX = paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId));
      this.pressedThumb = evalPressedThumb(this.mDownMotionX);
      if (this.pressedThumb == null)
        return super.onTouchEvent(paramMotionEvent);
      setPressed(true);
      invalidate();
      onStartTrackingTouch();
      trackTouchEvent(paramMotionEvent);
      attemptClaimDrag();
      continue;
      if (this.pressedThumb == null)
        continue;
      if (this.mIsDragging)
        trackTouchEvent(paramMotionEvent);
      while ((this.notifyWhileDragging) && (this.listener != null))
      {
        this.listener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue());
        break;
        if (Math.abs(paramMotionEvent.getX(paramMotionEvent.findPointerIndex(this.mActivePointerId)) - this.mDownMotionX) <= this.mScaledTouchSlop)
          continue;
        setPressed(true);
        invalidate();
        onStartTrackingTouch();
        trackTouchEvent(paramMotionEvent);
        attemptClaimDrag();
      }
      if (this.mIsDragging)
      {
        trackTouchEvent(paramMotionEvent);
        onStopTrackingTouch();
        setPressed(false);
      }
      while (true)
      {
        this.pressedThumb = null;
        invalidate();
        if (this.listener == null)
          break;
        this.listener.onRangeSeekBarValuesChanged(this, getSelectedMinValue(), getSelectedMaxValue());
        break;
        onStartTrackingTouch();
        trackTouchEvent(paramMotionEvent);
        onStopTrackingTouch();
      }
      int i = -1 + paramMotionEvent.getPointerCount();
      this.mDownMotionX = paramMotionEvent.getX(i);
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      invalidate();
      continue;
      onSecondaryPointerUp(paramMotionEvent);
      invalidate();
      continue;
      if (this.mIsDragging)
      {
        onStopTrackingTouch();
        setPressed(false);
      }
      invalidate();
    }
  }

  public void setNormalizedMaxValue(double paramDouble)
  {
    this.normalizedMaxValue = Math.max(0.0D, Math.min(1.0D, Math.max(paramDouble, this.normalizedMinValue)));
    invalidate();
  }

  public void setNormalizedMinValue(double paramDouble)
  {
    this.normalizedMinValue = Math.max(0.0D, Math.min(1.0D, Math.min(paramDouble, this.normalizedMaxValue)));
    invalidate();
  }

  public void setNotifyWhileDragging(boolean paramBoolean)
  {
    this.notifyWhileDragging = paramBoolean;
  }

  public void setOnRangeSeekBarChangeListener(OnRangeSeekBarChangeListener<T> paramOnRangeSeekBarChangeListener)
  {
    this.listener = paramOnRangeSeekBarChangeListener;
  }

  public void setSelectedMaxValue(T paramT)
  {
    if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim)
    {
      setNormalizedMaxValue(1.0D);
      return;
    }
    setNormalizedMaxValue(valueToNormalized(paramT));
  }

  public void setSelectedMinValue(T paramT)
  {
    if (0.0D == this.absoluteMaxValuePrim - this.absoluteMinValuePrim)
    {
      setNormalizedMinValue(0.0D);
      return;
    }
    setNormalizedMinValue(valueToNormalized(paramT));
  }

  private static enum NumberType
  {
    static
    {
      DOUBLE = new NumberType("DOUBLE", 1);
      INTEGER = new NumberType("INTEGER", 2);
      FLOAT = new NumberType("FLOAT", 3);
      SHORT = new NumberType("SHORT", 4);
      BYTE = new NumberType("BYTE", 5);
      BIG_DECIMAL = new NumberType("BIG_DECIMAL", 6);
      NumberType[] arrayOfNumberType = new NumberType[7];
      arrayOfNumberType[0] = LONG;
      arrayOfNumberType[1] = DOUBLE;
      arrayOfNumberType[2] = INTEGER;
      arrayOfNumberType[3] = FLOAT;
      arrayOfNumberType[4] = SHORT;
      arrayOfNumberType[5] = BYTE;
      arrayOfNumberType[6] = BIG_DECIMAL;
      $VALUES = arrayOfNumberType;
    }

    public static <E extends Number> NumberType fromNumber(E paramE)
      throws IllegalArgumentException
    {
      if ((paramE instanceof Long))
        return LONG;
      if ((paramE instanceof Double))
        return DOUBLE;
      if ((paramE instanceof Integer))
        return INTEGER;
      if ((paramE instanceof Float))
        return FLOAT;
      if ((paramE instanceof Short))
        return SHORT;
      if ((paramE instanceof Byte))
        return BYTE;
      if ((paramE instanceof BigDecimal))
        return BIG_DECIMAL;
      throw new IllegalArgumentException("Number class '" + paramE.getClass().getName() + "' is not supported");
    }

    public Number toNumber(double paramDouble)
    {
      switch (RangeSeekBar.1.$SwitchMap$com$efor18$rangeseekbar$RangeSeekBar$NumberType[ordinal()])
      {
      default:
        throw new InstantiationError("can't convert " + this + " to a Number object");
      case 1:
        return new Long(()paramDouble);
      case 2:
        return Double.valueOf(paramDouble);
      case 3:
        return new Integer((int)paramDouble);
      case 4:
        return new Float(paramDouble);
      case 5:
        return new Short((short)(int)paramDouble);
      case 6:
        return new Byte((byte)(int)paramDouble);
      case 7:
      }
      return new BigDecimal(paramDouble);
    }
  }

  public static abstract interface OnRangeSeekBarChangeListener<T>
  {
    public abstract void onRangeSeekBarValuesChanged(RangeSeekBar<?> paramRangeSeekBar, T paramT1, T paramT2);
  }

  private static enum Thumb
  {
    static
    {
      MAX = new Thumb("MAX", 1);
      Thumb[] arrayOfThumb = new Thumb[2];
      arrayOfThumb[0] = MIN;
      arrayOfThumb[1] = MAX;
      $VALUES = arrayOfThumb;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.efor18.rangeseekbar.RangeSeekBar
 * JD-Core Version:    0.6.0
 */