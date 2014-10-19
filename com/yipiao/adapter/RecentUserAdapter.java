package com.yipiao.adapter;

import android.view.View;
import android.widget.TextView;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.LoginUser;
import java.util.List;

public class RecentUserAdapter extends BaseViewAdapter<LoginUser>
{
  public RecentUserAdapter(BaseActivity paramBaseActivity, List<LoginUser> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(LoginUser paramLoginUser, View paramView)
  {
    ((TextView)paramView.findViewById(2131296419)).setText(paramLoginUser.getUserName().trim() + "（" + paramLoginUser.getUserLabel().trim() + "）");
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.RecentUserAdapter
 * JD-Core Version:    0.6.0
 */