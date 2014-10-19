package com.example.pathview;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import cn.suanya.hc.service.PathService;
import com.example.pathview.adapter.OfflineQueryResultAdapter;
import com.example.pathview.bean.RecentTrain;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.view.MyToast;
import java.util.List;

public class OfflineQueryResultActivity extends BaseActivity
{
  private OfflineQueryResultAdapter mAdapter;
  private String mFrom;
  private ListView mListView;
  private PathService mService;
  private String mTo;
  private TextView mTvTitle;

  private void loadResult(List<RecentTrain> paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0))
    {
      MyToast.makeText(this, "没有对应的车次", 1).show();
      finish();
      return;
    }
    if (this.mAdapter == null)
    {
      this.mAdapter = new OfflineQueryResultAdapter(this, paramList);
      this.mListView.setAdapter(this.mAdapter);
      return;
    }
    this.mAdapter.refresh(paramList);
  }

  private void showRescentTrain()
  {
    new MyAsyncTask(this)
    {
      protected List<RecentTrain> myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return OfflineQueryResultActivity.this.mService.getTrainByfromTo(OfflineQueryResultActivity.this.mFrom, OfflineQueryResultActivity.this.mTo);
      }

      protected void myPostExecute(List<RecentTrain> paramList)
      {
        OfflineQueryResultActivity.this.loadResult(paramList);
      }

      protected void onException(Exception paramException)
      {
        OfflineQueryResultActivity.this.finish();
        super.onException(paramException);
      }
    }
    .execute(new String[0]);
  }

  protected int getMainLayout()
  {
    return 2130903059;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mTvTitle = ((TextView)findViewById(2131296258));
    this.mFrom = getIntent().getStringExtra("from");
    this.mTo = getIntent().getStringExtra("to");
    this.mTvTitle.setText(this.mFrom + " - " + this.mTo);
    this.mService = PathService.getInstance();
    this.mListView = ((ListView)findViewById(2131296746));
    this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        Intent localIntent = new Intent(OfflineQueryResultActivity.this, ResultActivity.class);
        localIntent.putExtra("code", OfflineQueryResultActivity.this.mAdapter.getItem(paramInt).getCode());
        OfflineQueryResultActivity.this.startActivity(localIntent);
      }
    });
    showRescentTrain();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    return true;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.OfflineQueryResultActivity
 * JD-Core Version:    0.6.0
 */