package cn.suanya.ui.tableView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import cn.suanya.a.b;
import cn.suanya.a.c;
import cn.suanya.a.d;
import java.util.Iterator;

public class SYTableView extends LinearLayout
{
  private int bgBottom = a.b.background_view_rounded_bottom;
  private int bgMiddle = a.b.background_view_rounded_middle;
  private int bgSingle = a.b.background_view_rounded_single;
  private int bgTop = a.b.background_view_rounded_top;
  private int listContain = a.d.list_container;
  private TableItemGroups mGroups;
  private LayoutInflater mInflater;

  public SYTableView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  private void addGroup(TableItemGroup paramTableItemGroup)
  {
    LinearLayout localLinearLayout1 = (LinearLayout)this.mInflater.inflate(this.listContain, null);
    addView(localLinearLayout1, new LinearLayout.LayoutParams(-1, -2));
    LinearLayout localLinearLayout2 = (LinearLayout)localLinearLayout1.findViewById(a.c.buttonsContainer);
    int i = 0;
    if (i < paramTableItemGroup.size())
    {
      View localView = ((TableItem)paramTableItemGroup.get(i)).getView();
      Drawable localDrawable;
      if (paramTableItemGroup.size() == 1)
        localDrawable = getResources().getDrawable(this.bgSingle);
      while (true)
      {
        localView.setBackgroundDrawable(localDrawable);
        localLinearLayout2.addView(localView);
        i++;
        break;
        if (i == 0)
        {
          localDrawable = getResources().getDrawable(this.bgTop);
          continue;
        }
        if (i == -1 + paramTableItemGroup.size())
        {
          localDrawable = getResources().getDrawable(this.bgBottom);
          continue;
        }
        localDrawable = getResources().getDrawable(this.bgMiddle);
      }
    }
  }

  public void refresh()
  {
    removeAllViews();
    Iterator localIterator = this.mGroups.iterator();
    while (localIterator.hasNext())
      addGroup((TableItemGroup)localIterator.next());
  }

  public void setUpItems(TableItemGroups paramTableItemGroups)
  {
    this.mGroups = paramTableItemGroups;
    refresh();
  }

  public void setUpItems(TableItemGroups paramTableItemGroups, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (paramInt1 != 0)
      this.listContain = paramInt1;
    if (paramInt2 != 0)
      this.bgSingle = paramInt2;
    if (paramInt3 != 0)
      this.bgTop = paramInt3;
    if (paramInt4 != 0)
      this.bgMiddle = paramInt4;
    if (paramInt5 != 0)
      this.bgBottom = paramInt5;
    this.mGroups = paramTableItemGroups;
    refresh();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.SYTableView
 * JD-Core Version:    0.6.0
 */