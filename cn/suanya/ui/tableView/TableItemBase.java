package cn.suanya.ui.tableView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import cn.suanya.a.c;

public class TableItemBase
  implements View.OnClickListener, TableItem
{
  private int id = (int)(1024.0D * (1024.0D * (1024.0D * Math.random())));
  protected Context mContext;
  protected TableItemDelegate mDelegate;
  protected Object mValue;
  protected View mView;

  public TableItemBase(Context paramContext, int paramInt, TableItemDelegate paramTableItemDelegate, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mView = ((LinearLayout)((LayoutInflater)this.mContext.getSystemService("layout_inflater")).inflate(paramInt, null));
    this.mDelegate = paramTableItemDelegate;
    if (paramBoolean)
    {
      this.mView.findViewById(a.c.arrow).setVisibility(0);
      this.mView.setOnClickListener(this);
      return;
    }
    this.mView.findViewById(a.c.arrow).setVisibility(8);
  }

  public int getId()
  {
    return this.id;
  }

  public Object getValue()
  {
    return this.mValue;
  }

  public View getView()
  {
    return this.mView;
  }

  public void onClick(View paramView)
  {
    if (this.mDelegate != null)
      this.mDelegate.onTableItemClick(this);
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setLabel(String paramString)
  {
  }

  public void setValue(Object paramObject)
  {
    this.mValue = paramObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemBase
 * JD-Core Version:    0.6.0
 */