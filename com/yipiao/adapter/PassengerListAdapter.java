package com.yipiao.adapter;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.yipiao.Config;
import com.yipiao.activity.PassengerSetActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.UserInfo;
import java.util.List;

public class PassengerListAdapter extends BaseViewAdapter<UserInfo>
{
  public PassengerListAdapter(BaseActivity paramBaseActivity, List<UserInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  protected void removeItem(View paramView)
  {
    ((PassengerSetActivity)this.mContext).removePassenger((UserInfo)paramView.getTag());
  }

  protected View renderItem(UserInfo paramUserInfo, View paramView)
  {
    ((TextView)paramView.findViewById(2131296689)).setText(paramUserInfo.getName());
    setTv(2131296691, paramUserInfo.getCardId());
    setTv(2131296693, Config.getInstance().tickTypesHint.getByCode(paramUserInfo.getTickType(), Config.getInstance().tickTypesHint.get(0)).getName());
    Button localButton = (Button)paramView.findViewById(2131296947);
    localButton.setTag(paramUserInfo);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        PassengerListAdapter.this.removeItem(paramView);
      }
    });
    paramView.findViewById(2131296946).setOnClickListener(new View.OnClickListener(localButton)
    {
      public void onClick(View paramView)
      {
        this.val$passengerDelete.performClick();
      }
    });
    paramView.setTag(paramUserInfo);
    return paramView;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.PassengerListAdapter
 * JD-Core Version:    0.6.0
 */