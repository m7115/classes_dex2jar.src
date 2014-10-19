package com.yipiao.adapter;

import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.yipiao.Config;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.UserInfo;
import java.util.List;

public class PassengerHistoryListAdapter extends BaseViewAdapter<UserInfo>
{
  public PassengerHistoryListAdapter(BaseActivity paramBaseActivity, List<UserInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected View renderItem(UserInfo paramUserInfo, View paramView)
  {
    setTv(2131296419, paramUserInfo.getName());
    setTv(2131296997, Config.getInstance().tickTypesHint.getByCode(paramUserInfo.getTickType(), Config.getInstance().tickTypesHint.get(0)).getName());
    setTv(2131297000, Config.getInstance().userStatus.getByCode(paramUserInfo.getUserStatus(), Config.getInstance().userStatus.get(0)).getName());
    setTv(2131296998, Config.getInstance().cardTypes.getByCode(paramUserInfo.getCardType(), Config.getInstance().cardTypes.get(0)).getName());
    setTv(2131296999, paramUserInfo.getCardId());
    TextView localTextView = (TextView)paramView.findViewById(2131297000);
    if ("1".equalsIgnoreCase(paramUserInfo.getUserStatus()))
      localTextView.setTextColor(paramView.getResources().getColor(2131165283));
    while (true)
    {
      paramView.setTag(paramUserInfo);
      return paramView;
      if ("2".equalsIgnoreCase(paramUserInfo.getUserStatus()))
      {
        localTextView.setTextColor(paramView.getResources().getColor(2131165284));
        continue;
      }
      localTextView.setTextColor(paramView.getResources().getColor(2131165285));
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.PassengerHistoryListAdapter
 * JD-Core Version:    0.6.0
 */