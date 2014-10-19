package com.squareup.timessquare;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class CalendarRowView extends ViewGroup
  implements View.OnClickListener
{
  private int cellSize;
  private boolean isHeaderRow;
  private MonthView.Listener listener;
  private int oldHeightMeasureSpec;
  private int oldWidthMeasureSpec;

  public CalendarRowView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    paramView.setOnClickListener(this);
    super.addView(paramView, paramInt, paramLayoutParams);
  }

  public void onClick(View paramView)
  {
    if (this.listener != null)
      this.listener.handleClick((MonthCellDescriptor)paramView.getTag());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    long l = System.currentTimeMillis();
    int i = paramInt4 - paramInt2;
    int j = getChildCount();
    for (int k = 0; k < j; k++)
      getChildAt(k).layout(k * this.cellSize, 0, (k + 1) * this.cellSize, i);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l);
    Logr.d("Row.onLayout %d ms", arrayOfObject);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((this.oldWidthMeasureSpec == paramInt1) && (this.oldHeightMeasureSpec == paramInt2))
    {
      Logr.d("SKIP Row.onMeasure");
      setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
      return;
    }
    long l = System.currentTimeMillis();
    int i = View.MeasureSpec.getSize(paramInt1);
    this.cellSize = (i / 7);
    int j = View.MeasureSpec.makeMeasureSpec(this.cellSize, 1073741824);
    if (this.isHeaderRow);
    int i1;
    for (int k = j; ; k = j)
    {
      int m = getChildCount();
      int n = 0;
      i1 = 0;
      while (n < m)
      {
        View localView = getChildAt(n);
        localView.measure(j, k);
        if (localView.getMeasuredHeight() > i1)
          i1 = localView.getMeasuredHeight();
        n++;
      }
    }
    setMeasuredDimension(i + getPaddingLeft() + getPaddingRight(), i1 + getPaddingTop() + getPaddingBottom());
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(System.currentTimeMillis() - l);
    Logr.d("Row.onMeasure %d ms", arrayOfObject);
    this.oldWidthMeasureSpec = paramInt1;
    this.oldHeightMeasureSpec = paramInt2;
  }

  public void setIsHeaderRow(boolean paramBoolean)
  {
    this.isHeaderRow = paramBoolean;
  }

  public void setListener(MonthView.Listener paramListener)
  {
    this.listener = paramListener;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.CalendarRowView
 * JD-Core Version:    0.6.0
 */