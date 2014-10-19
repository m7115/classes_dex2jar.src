package cn.suanya.ui.tableView;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.widget.TextView;
import cn.suanya.a.c;
import cn.suanya.a.d;
import cn.suanya.common.a.p;
import cn.suanya.common.a.q;

public class TableItemSingleChoice extends TableItemBase
{
  private TextView mSubtitle = (TextView)this.mView.findViewById(a.c.subtitle);
  private TextView mTitle = (TextView)this.mView.findViewById(a.c.title);
  private q options;

  public TableItemSingleChoice(Context paramContext, int paramInt, TableItemDelegate paramTableItemDelegate, String paramString, q paramq, p paramp)
  {
    super(paramContext, paramInt, paramTableItemDelegate, true);
    this.mTitle.setText(paramString);
    this.options = paramq;
    setValue(paramp);
  }

  public TableItemSingleChoice(Context paramContext, String paramString, q paramq, p paramp)
  {
    this(paramContext, a.d.list_item, (TableItemDelegate)paramContext, paramString, paramq, paramp);
  }

  private int getIndex()
  {
    int i = this.options.b(getValue().getCode());
    if (i < 0)
      i = 0;
    return i;
  }

  public q getOptions()
  {
    return this.options;
  }

  public p getValue()
  {
    return (p)this.mValue;
  }

  public void onClick(View paramView)
  {
    super.onClick(paramView);
    new AlertDialog.Builder(this.mContext).setSingleChoiceItems(this.options.a(), getIndex(), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        TableItemSingleChoice.this.setValue(TableItemSingleChoice.this.options.get(paramInt));
        if (TableItemSingleChoice.this.mDelegate != null)
          TableItemSingleChoice.this.mDelegate.onTableItemChanged(TableItemSingleChoice.this);
        paramDialogInterface.dismiss();
      }
    }).show();
  }

  public void setLabel(String paramString)
  {
    this.mSubtitle.setText(paramString);
  }

  public void setOptions(q paramq)
  {
    this.options = paramq;
  }

  public void setValue(Object paramObject)
  {
    super.setValue(paramObject);
    if (paramObject != null)
      setLabel(((p)paramObject).getName());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemSingleChoice
 * JD-Core Version:    0.6.0
 */