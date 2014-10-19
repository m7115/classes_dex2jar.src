package cn.suanya.hotel.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

public class IndexableListView extends ListView
{
  private GestureDetector mGestureDetector = null;
  private boolean mIsFastScrollEnabled = false;
  private IndexScroller mScroller = null;

  public IndexableListView(Context paramContext)
  {
    super(paramContext);
  }

  public IndexableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public IndexableListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (this.mScroller != null)
      this.mScroller.draw(paramCanvas);
  }

  public boolean isFastScrollEnabled()
  {
    return this.mIsFastScrollEnabled;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mScroller != null)
      this.mScroller.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mScroller != null) && (this.mScroller.onTouchEvent(paramMotionEvent)))
      return true;
    if (this.mGestureDetector == null)
      this.mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
      {
        public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
        {
          IndexableListView.this.mScroller.show();
          return super.onFling(paramMotionEvent1, paramMotionEvent2, paramFloat1, paramFloat2);
        }
      });
    this.mGestureDetector.onTouchEvent(paramMotionEvent);
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
    if (this.mScroller != null)
      this.mScroller.setAdapter(paramListAdapter);
  }

  public void setFastScrollEnabled(boolean paramBoolean)
  {
    this.mIsFastScrollEnabled = paramBoolean;
    if (this.mIsFastScrollEnabled)
      if (this.mScroller == null)
        this.mScroller = new IndexScroller(getContext(), this);
    do
      return;
    while (this.mScroller == null);
    this.mScroller.hide();
    this.mScroller = null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.android.widget.IndexableListView
 * JD-Core Version:    0.6.0
 */