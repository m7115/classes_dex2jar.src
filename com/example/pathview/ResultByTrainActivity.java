package com.example.pathview;

import com.example.pathview.bean.TrainInfo;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.Train;
import com.yipiao.service.HuocheBase;
import java.util.List;

public class ResultByTrainActivity extends ResultActivity
{
  private Train mtrain;

  protected void queryTrainInfo()
  {
    1 local1 = new MyAsyncTask(this)
    {
      protected TrainInfo myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        List localList = ResultByTrainActivity.this.app.getHC().arrStationInfo(ResultByTrainActivity.this.mtrain);
        return new TrainInfo(ResultByTrainActivity.this.mtrain.getCode(), localList);
      }

      protected void myPostExecute(TrainInfo paramTrainInfo)
      {
        ResultByTrainActivity.this.loadResult(paramTrainInfo);
        if (ResultByTrainActivity.this.mGpsOrTime == 0)
          ResultByTrainActivity.this.timerStart();
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
        ResultByTrainActivity.this.finish();
      }
    };
    this.mtrain = ((Train)this.app.getParms("train"));
    this.mTrainCode = this.mtrain.getCode();
    initTitleView(this.mTrainCode);
    local1.execute(new String[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.ResultByTrainActivity
 * JD-Core Version:    0.6.0
 */