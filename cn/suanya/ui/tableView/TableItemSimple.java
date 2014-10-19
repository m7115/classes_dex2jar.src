package cn.suanya.ui.tableView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.suanya.a.c;
import cn.suanya.a.d;

public class TableItemSimple extends TableItemBase
{
  private ImageView mArrow = (ImageView)this.mView.findViewById(a.c.arrow);
  private ImageView mImage = (ImageView)this.mView.findViewById(a.c.image);
  private TextView mSubtitle = (TextView)this.mView.findViewById(a.c.subtitle);
  private TextView mTitle = (TextView)this.mView.findViewById(a.c.title);

  public TableItemSimple(Context paramContext, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence, boolean paramBoolean)
  {
    this(paramContext, paramInt1, (TableItemDelegate)paramContext, paramInt2, paramString, paramCharSequence, paramBoolean);
  }

  public TableItemSimple(Context paramContext, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence, boolean paramBoolean1, boolean paramBoolean2)
  {
    this(paramContext, paramInt1, (TableItemDelegate)paramContext, paramInt2, paramString, paramCharSequence, paramBoolean1, paramBoolean2);
  }

  public TableItemSimple(Context paramContext, int paramInt1, TableItemDelegate paramTableItemDelegate, int paramInt2, String paramString, CharSequence paramCharSequence, boolean paramBoolean)
  {
    this(paramContext, paramInt1, paramTableItemDelegate, paramInt2, paramString, paramCharSequence, paramBoolean, true);
  }

  public TableItemSimple(Context paramContext, int paramInt1, TableItemDelegate paramTableItemDelegate, int paramInt2, String paramString, CharSequence paramCharSequence, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext, paramInt1, paramTableItemDelegate, paramBoolean1);
    this.mTitle.setText(paramString);
    if (this.mSubtitle != null)
      this.mSubtitle.setText(paramCharSequence);
    if (this.mImage != null)
    {
      this.mImage.setImageResource(paramInt2);
      if (paramInt2 == 0)
        this.mImage.setVisibility(8);
    }
    else
    {
      if (!paramBoolean1)
        break label175;
      this.mArrow.setVisibility(0);
    }
    while (true)
    {
      if (!paramBoolean2)
        break label197;
      if (!paramBoolean1)
        break label187;
      this.mArrow.setVisibility(0);
      return;
      this.mImage.setVisibility(0);
      break;
      label175: this.mArrow.setVisibility(8);
    }
    label187: this.mArrow.setVisibility(8);
    return;
    label197: this.mArrow.setVisibility(8);
  }

  public TableItemSimple(Context paramContext, int paramInt, String paramString, CharSequence paramCharSequence)
  {
    this(paramContext, a.d.list_item, (TableItemDelegate)paramContext, paramInt, paramString, paramCharSequence, true);
  }

  public TableItemSimple(Context paramContext, String paramString, CharSequence paramCharSequence)
  {
    this(paramContext, a.d.list_item, (TableItemDelegate)paramContext, 0, paramString, paramCharSequence, true);
  }

  public TextView getmSubtitle()
  {
    return this.mSubtitle;
  }

  public TextView getmTitle()
  {
    return this.mTitle;
  }

  public void setLabel(String paramString)
  {
    this.mSubtitle.setText(paramString);
  }

  public void setmSubtitle(TextView paramTextView)
  {
    this.mSubtitle = paramTextView;
  }

  public void setmTitle(TextView paramTextView)
  {
    this.mTitle = paramTextView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemSimple
 * JD-Core Version:    0.6.0
 */