package com.yipiao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.example.pathview.ResultActivity;
import com.yipiao.YipiaoApplication;
import com.yipiao.adapter.StationListAdapter;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.Train;
import com.yipiao.bean.TrainStationInfo;
import com.yipiao.service.Huoche;
import java.util.List;

public class StationActivity extends BaseActivity
{
  private StationListAdapter adapter;
  private List<TrainStationInfo> items;
  private ListView mListView;
  private Train train;

  public void chainQuery(Train paramTrain)
  {
    new MyAsyncTask(this)
    {
      protected List<TrainStationInfo> myInBackground(Train[] paramArrayOfTrain)
        throws Exception
      {
        return StationActivity.this.getHc().arrStationInfo(paramArrayOfTrain[0]);
      }

      protected void myPostExecute(List<TrainStationInfo> paramList)
      {
        StationActivity.this.adapter.setMlist(paramList);
        StationActivity.this.adapter.notifyDataSetChanged();
      }
    }
    .execute(new Train[] { paramTrain });
  }

  protected int getMainLayout()
  {
    return 2130903170;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    this.train = ((Train)this.app.getParms("train"));
    this.adapter = new StationListAdapter(this, this.items, 2130903171);
    this.mListView = ((ListView)findViewById(2131296764));
    this.mListView.setAdapter(this.adapter);
    chainQuery(this.train);
    setTv(2131296258, this.train.getCode());
    setClick(2131296259, new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Intent localIntent = new Intent(StationActivity.this, ResultActivity.class);
        localIntent.putExtra("code", StationActivity.this.train.getCode());
        StationActivity.this.startActivity(localIntent);
      }
    });
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.StationActivity
 * JD-Core Version:    0.6.0
 */