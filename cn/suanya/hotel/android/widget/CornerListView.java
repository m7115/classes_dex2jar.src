package cn.suanya.hotel.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;
import cn.suanya.hotel.base.R.drawable;

public class CornerListView extends ListView
{
  public CornerListView(Context paramContext)
  {
    super(paramContext);
  }

  public CornerListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public CornerListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    }
    while (true)
    {
      return super.onInterceptTouchEvent(paramMotionEvent);
      int i = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      if (i == -1)
        continue;
      if (i == 0)
      {
        if (i != -1 + getAdapter().getCount())
          continue;
        setSelector(R.drawable.app_list_corner_round);
        continue;
      }
      if (i == -1 + getAdapter().getCount())
      {
        setSelector(R.drawable.app_list_corner_round_bottom);
        continue;
      }
      setSelector(R.drawable.app_list_corner_shape);
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, View.MeasureSpec.makeMeasureSpec(536870911, -2147483648));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.android.widget.CornerListView
 * JD-Core Version:    0.6.0
 */