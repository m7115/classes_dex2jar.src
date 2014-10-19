package cn.suanya.hotel.android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class IndexScroller
{
  private static final int STATE_HIDDEN = 0;
  private static final int STATE_HIDING = 3;
  private static final int STATE_SHOWING = 1;
  private static final int STATE_SHOWN = 2;
  private float mAlphaRate;
  private int mCurrentSection = -1;
  private float mDensity;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      switch (IndexScroller.this.mState)
      {
      default:
        return;
      case 1:
        IndexScroller.access$102(IndexScroller.this, (float)(IndexScroller.this.mAlphaRate + 0.2D * (1.0F - IndexScroller.this.mAlphaRate)));
        if (IndexScroller.this.mAlphaRate > 0.9D)
        {
          IndexScroller.access$102(IndexScroller.this, 1.0F);
          IndexScroller.this.setState(2);
        }
        IndexScroller.this.mListView.invalidate();
        IndexScroller.this.fade(10L);
        return;
      case 2:
        IndexScroller.this.setState(3);
        return;
      case 3:
      }
      IndexScroller.access$102(IndexScroller.this, (float)(IndexScroller.this.mAlphaRate - 0.2D * IndexScroller.this.mAlphaRate));
      if (IndexScroller.this.mAlphaRate < 0.1D)
      {
        IndexScroller.access$102(IndexScroller.this, 0.0F);
        IndexScroller.this.setState(0);
      }
      IndexScroller.this.mListView.invalidate();
      IndexScroller.this.fade(10L);
    }
  };
  private float mIndexbarMargin;
  private RectF mIndexbarRect;
  private float mIndexbarWidth;
  private SectionIndexer mIndexer = null;
  private boolean mIsIndexing = false;
  private ListView mListView = null;
  private int mListViewHeight;
  private int mListViewWidth;
  private float mPreviewPadding;
  private float mScaledDensity;
  private String[] mSections = null;
  private int mState = 0;

  public IndexScroller(Context paramContext, ListView paramListView)
  {
    this.mDensity = paramContext.getResources().getDisplayMetrics().density;
    this.mScaledDensity = paramContext.getResources().getDisplayMetrics().scaledDensity;
    this.mListView = paramListView;
    setAdapter(this.mListView.getAdapter());
    this.mIndexbarWidth = (30.0F * this.mDensity);
    this.mIndexbarMargin = (10.0F * this.mDensity);
    this.mPreviewPadding = (5.0F * this.mDensity);
  }

  private boolean contains(float paramFloat1, float paramFloat2)
  {
    return (paramFloat1 >= this.mIndexbarRect.left) && (paramFloat2 >= this.mIndexbarRect.top) && (paramFloat2 <= this.mIndexbarRect.top + this.mIndexbarRect.height());
  }

  private void fade(long paramLong)
  {
    this.mHandler.removeMessages(0);
    this.mHandler.sendEmptyMessageAtTime(0, paramLong + SystemClock.uptimeMillis());
  }

  private int getSectionByPoint(float paramFloat)
  {
    if ((this.mSections == null) || (this.mSections.length == 0));
    do
      return 0;
    while (paramFloat < this.mIndexbarRect.top + this.mIndexbarMargin);
    if (paramFloat >= this.mIndexbarRect.top + this.mIndexbarRect.height() - this.mIndexbarMargin)
      return -1 + this.mSections.length;
    return (int)((paramFloat - this.mIndexbarRect.top - this.mIndexbarMargin) / ((this.mIndexbarRect.height() - 2.0F * this.mIndexbarMargin) / this.mSections.length));
  }

  private void setState(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 3))
      return;
    this.mState = paramInt;
    switch (this.mState)
    {
    default:
      return;
    case 0:
      this.mHandler.removeMessages(0);
      return;
    case 1:
      this.mAlphaRate = 0.0F;
      fade(0L);
      return;
    case 2:
      this.mHandler.removeMessages(0);
      return;
    case 3:
    }
    this.mAlphaRate = 1.0F;
    fade(3000L);
  }

  public void draw(Canvas paramCanvas)
  {
    int i = 0;
    if (this.mState == 0);
    while (true)
    {
      return;
      Paint localPaint1 = new Paint();
      localPaint1.setColor(-16777216);
      localPaint1.setAlpha((int)(64.0F * this.mAlphaRate));
      localPaint1.setAntiAlias(true);
      paramCanvas.drawRoundRect(this.mIndexbarRect, 5.0F * this.mDensity, 5.0F * this.mDensity, localPaint1);
      if ((this.mSections == null) || (this.mSections.length <= 0))
        continue;
      if (this.mCurrentSection >= 0)
      {
        Paint localPaint2 = new Paint();
        localPaint2.setColor(-16777216);
        localPaint2.setAlpha(96);
        localPaint2.setAntiAlias(true);
        localPaint2.setShadowLayer(3.0F, 0.0F, 0.0F, Color.argb(64, 0, 0, 0));
        Paint localPaint3 = new Paint();
        localPaint3.setColor(-1);
        localPaint3.setAntiAlias(true);
        localPaint3.setTextSize(50.0F * this.mScaledDensity);
        float f1 = localPaint3.measureText(this.mSections[this.mCurrentSection]);
        float f2 = 2.0F * this.mPreviewPadding + localPaint3.descent() - localPaint3.ascent();
        RectF localRectF = new RectF((this.mListViewWidth - f2) / 2.0F, (this.mListViewHeight - f2) / 2.0F, f2 + (this.mListViewWidth - f2) / 2.0F, f2 + (this.mListViewHeight - f2) / 2.0F);
        paramCanvas.drawRoundRect(localRectF, 5.0F * this.mDensity, 5.0F * this.mDensity, localPaint2);
        paramCanvas.drawText(this.mSections[this.mCurrentSection], localRectF.left + (f2 - f1) / 2.0F - 1.0F, 1.0F + (localRectF.top + this.mPreviewPadding - localPaint3.ascent()), localPaint3);
      }
      Paint localPaint4 = new Paint();
      localPaint4.setColor(-1);
      localPaint4.setAlpha((int)(255.0F * this.mAlphaRate));
      localPaint4.setAntiAlias(true);
      localPaint4.setTextSize(18.0F * this.mScaledDensity);
      float f3 = (this.mIndexbarRect.height() - 2.0F * this.mIndexbarMargin) / this.mSections.length;
      float f4 = (f3 - (localPaint4.descent() - localPaint4.ascent())) / 2.0F;
      while (i < this.mSections.length)
      {
        float f5 = (this.mIndexbarWidth - localPaint4.measureText(this.mSections[i])) / 2.0F;
        paramCanvas.drawText(this.mSections[i], f5 + this.mIndexbarRect.left, f4 + (this.mIndexbarRect.top + this.mIndexbarMargin + f3 * i) - localPaint4.ascent(), localPaint4);
        i++;
      }
    }
  }

  public void hide()
  {
    if (this.mState == 2)
      setState(3);
  }

  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListViewWidth = paramInt1;
    this.mListViewHeight = paramInt2;
    this.mIndexbarRect = new RectF(paramInt1 - this.mIndexbarMargin - this.mIndexbarWidth, this.mIndexbarMargin, paramInt1 - this.mIndexbarMargin, paramInt2 - this.mIndexbarMargin);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      bool = false;
      do
      {
        return bool;
        if ((this.mState == 0) || (!contains(paramMotionEvent.getX(), paramMotionEvent.getY())))
          break;
        setState(2);
        this.mIsIndexing = bool;
        this.mCurrentSection = getSectionByPoint(paramMotionEvent.getY());
        this.mListView.setSelection(this.mIndexer.getPositionForSection(this.mCurrentSection));
        return bool;
        if (!this.mIsIndexing)
          break;
      }
      while (!contains(paramMotionEvent.getX(), paramMotionEvent.getY()));
      this.mCurrentSection = getSectionByPoint(paramMotionEvent.getY());
      this.mListView.setSelection(this.mIndexer.getPositionForSection(this.mCurrentSection));
      return bool;
      if (this.mIsIndexing)
      {
        this.mIsIndexing = false;
        this.mCurrentSection = -1;
      }
      if (this.mState != 2)
        continue;
      setState(3);
    }
  }

  public void setAdapter(Adapter paramAdapter)
  {
    if ((paramAdapter instanceof SectionIndexer))
    {
      this.mIndexer = ((SectionIndexer)paramAdapter);
      this.mSections = ((String[])(String[])this.mIndexer.getSections());
    }
  }

  public void show()
  {
    if (this.mState == 0)
      setState(1);
    do
      return;
    while (this.mState != 3);
    setState(3);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.android.widget.IndexScroller
 * JD-Core Version:    0.6.0
 */