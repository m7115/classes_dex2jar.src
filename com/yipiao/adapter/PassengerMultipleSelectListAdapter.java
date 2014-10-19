package com.yipiao.adapter;

import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import com.yipiao.Config;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class PassengerMultipleSelectListAdapter extends BaseViewAdapter<UserInfo>
{
  private List<UserInfo> selectList;

  public PassengerMultipleSelectListAdapter(BaseActivity paramBaseActivity, List<UserInfo> paramList1, List<UserInfo> paramList2, int paramInt)
  {
    super(paramBaseActivity, paramList1, paramInt);
    if (paramList2 != null);
    while (true)
    {
      this.selectList = paramList2;
      return;
      paramList2 = new ArrayList();
    }
  }

  private boolean isChecked(UserInfo paramUserInfo)
  {
    return this.selectList.contains(paramUserInfo);
  }

  public List<UserInfo> getSelectList()
  {
    return this.selectList;
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
      CheckBox localCheckBox = (CheckBox)paramView.findViewById(2131297002);
      localCheckBox.setChecked(isChecked(paramUserInfo));
      localCheckBox.setOnClickListener(new View.OnClickListener(localCheckBox, paramUserInfo)
      {
        public void onClick(View paramView)
        {
          if (this.val$check.isChecked())
            if (PassengerMultipleSelectListAdapter.this.selectList.size() >= 5)
            {
              PassengerMultipleSelectListAdapter.this.mContext.showToast("只能添加5个购票人");
              this.val$check.setChecked(false);
            }
          while (true)
          {
            return;
            PassengerMultipleSelectListAdapter.this.selectList.add(this.val$item);
            return;
            for (int i = -1 + PassengerMultipleSelectListAdapter.this.selectList.size(); i >= 0; i--)
            {
              if (!this.val$item.equals(PassengerMultipleSelectListAdapter.this.selectList.get(i)))
                continue;
              PassengerMultipleSelectListAdapter.this.selectList.remove(i);
            }
          }
        }
      });
      paramView.findViewById(2131297001).setOnClickListener(new View.OnClickListener(localCheckBox)
      {
        public void onClick(View paramView)
        {
          this.val$check.performClick();
        }
      });
      this.mConvertView.setTag(paramUserInfo);
      return paramView;
      if ("2".equalsIgnoreCase(paramUserInfo.getUserStatus()))
      {
        localTextView.setTextColor(paramView.getResources().getColor(2131165284));
        continue;
      }
      localTextView.setTextColor(paramView.getResources().getColor(2131165285));
    }
  }

  public void setSelectList(List<UserInfo> paramList)
  {
    this.selectList = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.PassengerMultipleSelectListAdapter
 * JD-Core Version:    0.6.0
 */