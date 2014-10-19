package cn.suanya.ui.tableView;

import android.view.View;

public abstract interface TableItem
{
  public abstract int getId();

  public abstract Object getValue();

  public abstract View getView();

  public abstract void setLabel(String paramString);

  public abstract void setValue(Object paramObject);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItem
 * JD-Core Version:    0.6.0
 */