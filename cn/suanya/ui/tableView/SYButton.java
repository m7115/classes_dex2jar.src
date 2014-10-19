package cn.suanya.ui.tableView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import cn.suanya.a.c;
import cn.suanya.a.d;
import cn.suanya.a.f;

public class SYButton extends LinearLayout
{
  private LinearLayout mButtonContainer;
  private ClickListener mClickListener;
  private int mImage;
  private LayoutInflater mInflater;
  private CharSequence mSubtitle;
  private CharSequence mTitle;

  public SYButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setClickable(true);
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    this.mButtonContainer = ((LinearLayout)this.mInflater.inflate(a.d.list_item_single, null));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, a.f.SYButton, 0, 0);
    this.mTitle = localTypedArray.getString(0);
    this.mSubtitle = localTypedArray.getString(1);
    this.mImage = localTypedArray.getResourceId(2, -1);
    if (this.mTitle != null)
    {
      ((TextView)this.mButtonContainer.findViewById(a.c.title)).setText(this.mTitle.toString());
      if (this.mSubtitle == null)
        break label233;
      ((TextView)this.mButtonContainer.findViewById(a.c.subtitle)).setText(this.mSubtitle.toString());
    }
    while (true)
    {
      if (this.mImage > -1)
        ((ImageView)this.mButtonContainer.findViewById(a.c.image)).setImageResource(this.mImage);
      this.mButtonContainer.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (SYButton.this.mClickListener != null)
            SYButton.this.mClickListener.onClick(SYButton.this);
        }
      });
      addView(this.mButtonContainer, localLayoutParams);
      return;
      ((TextView)this.mButtonContainer.findViewById(a.c.title)).setText("subtitle");
      break;
      label233: ((TextView)this.mButtonContainer.findViewById(a.c.subtitle)).setVisibility(8);
    }
  }

  public void addClickListener(ClickListener paramClickListener)
  {
    this.mClickListener = paramClickListener;
  }

  public void removeClickListener()
  {
    this.mClickListener = null;
  }

  public static abstract interface ClickListener
  {
    public abstract void onClick(View paramView);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.SYButton
 * JD-Core Version:    0.6.0
 */