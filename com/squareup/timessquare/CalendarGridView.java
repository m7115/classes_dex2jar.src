package com.squareup.timessquare;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import cn.suanya.a.a;

public class CalendarGridView extends ViewGroup
{
  private final Paint dividerPaint = new Paint();

  public CalendarGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.dividerPaint.setColor(getResources().getColor(a.a.calendar_divider));
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (getChildCount() == 0)
      ((CalendarRowView)paramView).setIsHeaderRow(true);
    super.addView(paramView, paramInt, paramLayoutParams);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    int i = 0;
    super.dispatchDraw(paramCanvas);
    ViewGroup localViewGroup = (ViewGroup)getChildAt(1);
    int j = localViewGroup.getTop();
    int k = getBottom();
    int m = localViewGroup.getChildAt(0).getLeft() + getLeft();
    paramCanvas.drawLine(m, j, m, k, this.dividerPaint);
    while (i < 7)
    {
      int n = -1 + (m + localViewGroup.getChildAt(i).getRight());
      paramCanvas.drawLine(n, j, n, k, this.dividerPaint);
      i++;
    }
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    int i = -1 + paramView.getBottom();
    paramCanvas.drawLine(paramView.getLeft(), i, paramView.getRight(), i, this.dividerPaint);
    return bool;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    long l = System.currentTimeMillis();
    int i = getChildCount();
    int j = 0;
    int k = 0;
    while (j < i)
    {
      View localView = getChildAt(j);
      int m = localView.getMeasuredHeight();
      localView.layout(paramInt1, k, paramInt3, k + m);
      k += m;
      j++;
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l);
    Logr.d("Grid.onLayout %d ms", arrayOfObject);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    long l = System.currentTimeMillis();
    int i = View.MeasureSpec.getSize(paramInt1) / 7;
    int j = i * 7;
    int k = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
    int m = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
    int n = getChildCount();
    int i1 = 0;
    int i2 = 0;
    if (i1 < n)
    {
      View localView = getChildAt(i1);
      if (localView.getVisibility() == 0)
      {
        if (i1 != 0)
          break label109;
        measureChild(localView, k, View.MeasureSpec.makeMeasureSpec(i, -2147483648));
      }
      while (true)
      {
        i2 += localView.getMeasuredHeight();
        i1++;
        break;
        label109: measureChild(localView, k, m);
      }
    }
    setMeasuredDimension(j + 2, i2);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l);
    Logr.d("Grid.onMeasure %d ms", arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.CalendarGridView
 * JD-Core Version:    0.6.0
 */