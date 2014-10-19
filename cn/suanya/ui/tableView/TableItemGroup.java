package cn.suanya.ui.tableView;

import java.util.ArrayList;

public class TableItemGroup extends ArrayList<TableItem>
{
  private static final long serialVersionUID = 5340495058693266388L;

  public TableItemGroup(TableItem[] paramArrayOfTableItem)
  {
    for (int i = 0; i < paramArrayOfTableItem.length; i++)
      add(paramArrayOfTableItem[i]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemGroup
 * JD-Core Version:    0.6.0
 */