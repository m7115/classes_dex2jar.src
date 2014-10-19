package com.yipiao.adapter;

import com.yipiao.activity.AdvancedQueryActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Train;
import java.util.List;

public class AdvanceSeatListAdapter extends SeatListAdapter
{
  public AdvanceSeatListAdapter(BaseActivity paramBaseActivity, List<Train> paramList, int paramInt, ChainQuery paramChainQuery)
  {
    super(paramBaseActivity, paramList, paramInt, paramChainQuery);
  }

  protected void goToBook(ChainQuery paramChainQuery, Train paramTrain)
  {
    ((AdvancedQueryActivity)this.mContext).goToBook(paramChainQuery, paramTrain);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.AdvanceSeatListAdapter
 * JD-Core Version:    0.6.0
 */