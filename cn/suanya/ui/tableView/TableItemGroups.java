package cn.suanya.ui.tableView;

import java.util.ArrayList;
import java.util.Iterator;

public class TableItemGroups extends ArrayList<TableItemGroup>
{
  private static final long serialVersionUID = 8942813387508148576L;

  public TableItemGroups(TableItemGroup[] paramArrayOfTableItemGroup)
  {
    for (int i = 0; i < paramArrayOfTableItemGroup.length; i++)
      add(paramArrayOfTableItemGroup[i]);
  }

  public void removeItem(Object paramObject)
  {
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      ((TableItemGroup)localIterator.next()).remove(paramObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemGroups
 * JD-Core Version:    0.6.0
 */