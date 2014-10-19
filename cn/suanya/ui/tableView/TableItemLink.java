package cn.suanya.ui.tableView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import java.net.URISyntaxException;

public class TableItemLink extends TableItemSimple
{
  private Intent intent;

  public TableItemLink(Context paramContext, int paramInt1, int paramInt2, String paramString, CharSequence paramCharSequence)
  {
    super(paramContext, paramInt1, paramInt2, paramString, paramCharSequence, true);
  }

  public TableItemLink(Context paramContext, int paramInt1, int paramInt2, String paramString1, CharSequence paramCharSequence, String paramString2)
  {
    super(paramContext, paramInt1, paramInt2, paramString1, paramCharSequence, true);
    if (paramString2.startsWith("#"));
    try
    {
      this.intent = Intent.parseUri(paramString2, 0);
      return;
      this.intent = new Intent("SY.WEB.VIEW", Uri.parse(paramString2));
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
    }
  }

  public Intent getIntent()
  {
    return this.intent;
  }

  public void onClick(View paramView)
  {
    this.mContext.startActivity(this.intent);
    super.onClick(paramView);
  }

  public void setIntent(Intent paramIntent)
  {
    this.intent = paramIntent;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.ui.tableView.TableItemLink
 * JD-Core Version:    0.6.0
 */