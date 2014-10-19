package com.example.pathview;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import cn.suanya.hc.service.PathService;
import com.example.pathview.adapter.RecentTrainAdapter;
import com.example.pathview.bean.RecentTrain;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.bean.ChainQuery;
import com.yipiao.bean.Note;
import com.yipiao.bean.StationNode;
import com.yipiao.view.MyToast;
import java.util.List;

public class MainActivity extends BaseActivity
  implements View.OnClickListener
{
  private RecentTrainAdapter adapter;
  private Button btnQuery;
  private Button btnQueryByCity;
  private Button btnSwitch;
  private View layoutFrom;
  private View layoutTo;
  private ListView listView;
  private RadioButton rbCity;
  private RadioButton rbCode;
  private PathService service;
  private TabHost tabHost;
  private String trainCode;
  private TextView tvFrom;
  private EditText tvQuery;
  private TextView tvTo;

  private void initAutoComplete(Context paramContext, AutoCompleteTextView paramAutoCompleteTextView)
  {
    paramAutoCompleteTextView.setDropDownHeight(350);
    paramAutoCompleteTextView.setThreshold(1);
    paramAutoCompleteTextView.addTextChangedListener(new TextWatcher(paramAutoCompleteTextView, paramContext)
    {
      public void afterTextChanged(Editable paramEditable)
      {
        Cursor localCursor = MainActivity.this.service.getTrainCursorByCode(this.val$auto.getText().toString());
        1 local1 = new SimpleCursorAdapter(this.val$context, 2130903090, localCursor, new String[] { "sub_train_number" }, new int[] { 2131296850 })
        {
          public CharSequence convertToString(Cursor paramCursor)
          {
            if (paramCursor == null)
              return "";
            return paramCursor.getString(paramCursor.getColumnIndex("sub_train_number"));
          }
        };
        this.val$auto.setAdapter(local1);
        this.val$auto.setOnItemClickListener(new AdapterView.OnItemClickListener(localCursor)
        {
          public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
          {
            if (this.val$cursor.moveToPosition(paramInt))
              MainActivity.this.startResultActivity(this.val$cursor.getString(this.val$cursor.getColumnIndex("sub_train_number")));
          }
        });
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }
    });
  }

  private void initRecentTrain()
  {
    int i = 10;
    List localList1 = this.service.getAllRecentStation();
    List localList2;
    if ((localList1 != null) && (localList1.size() > 0))
      if (localList1.size() > i)
      {
        localList2 = localList1.subList(0, i);
        if (this.adapter != null)
          break label108;
        this.adapter = new RecentTrainAdapter(this, localList2);
        this.listView.setAdapter(this.adapter);
        label74: this.listView.setVisibility(0);
      }
    while (true)
    {
      this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          MainActivity.this.startResultActivity(MainActivity.this.adapter.getItem(paramInt).getCode());
        }
      });
      return;
      i = localList1.size();
      break;
      label108: this.adapter.refresh(localList2);
      break label74;
      this.listView.setVisibility(8);
    }
  }

  private void initViews()
  {
    this.tabHost = ((TabHost)findViewById(2131296740));
    this.tabHost.setup();
    this.tabHost.addTab(this.tabHost.newTabSpec("tab1").setIndicator("车次").setContent(2131296744));
    this.tabHost.addTab(this.tabHost.newTabSpec("tab2").setIndicator("站点").setContent(2131296745));
    this.listView = ((ListView)findViewById(2131296739));
    this.rbCode = ((RadioButton)findViewById(2131296742));
    this.rbCode.setOnClickListener(this);
    this.rbCity = ((RadioButton)findViewById(2131296743));
    this.rbCity.setOnClickListener(this);
    this.tvQuery = ((EditText)findViewById(2131297107));
    this.tvQuery.setFocusable(true);
    this.tvQuery.requestFocus();
    this.btnQuery = ((Button)findViewById(2131297109));
    this.btnQuery.setOnClickListener(this);
    this.layoutFrom = findViewById(2131296798);
    this.layoutFrom.setOnClickListener(this);
    this.tvFrom = ((TextView)findViewById(2131296800));
    this.layoutTo = findViewById(2131296802);
    this.layoutTo.setOnClickListener(this);
    this.tvTo = ((TextView)findViewById(2131296804));
    this.btnSwitch = ((Button)findViewById(2131296801));
    this.btnSwitch.setOnClickListener(this);
    this.btnQueryByCity = ((Button)findViewById(2131296805));
    this.btnQueryByCity.setOnClickListener(this);
    ChainQuery localChainQuery = this.app.getCq();
    if (localChainQuery != null)
    {
      this.tvFrom.setText(localChainQuery.getOrg().getName());
      this.tvTo.setText(localChainQuery.getArr().getName());
    }
  }

  private void startResultActivity(String paramString)
  {
    Intent localIntent = new Intent(this, ResultActivity.class);
    localIntent.putExtra("code", paramString);
    startActivity(localIntent);
  }

  protected int getMainLayout()
  {
    return 2130903057;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2131296798)
      if (paramInt2 != 0)
      {
        Note localNote2 = (Note)paramIntent.getExtras().get("DATA");
        if (localNote2.getName().equals(this.tvTo.getText()))
          this.tvTo.setText(this.tvFrom.getText());
        this.tvFrom.setText(localNote2.getName());
      }
    do
      return;
    while ((paramInt1 != 2131296802) || (paramInt2 == 0));
    Note localNote1 = (Note)paramIntent.getExtras().get("DATA");
    if (localNote1.getName().equals(this.tvFrom.getText()))
      this.tvFrom.setText(this.tvTo.getText());
    this.tvTo.setText(localNote1.getName());
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296742:
      this.rbCode.setChecked(true);
      this.rbCity.setChecked(false);
      this.tabHost.setCurrentTab(0);
      return;
    case 2131296743:
      this.rbCode.setChecked(false);
      this.rbCity.setChecked(true);
      this.tabHost.setCurrentTab(1);
      return;
    case 2131297109:
      this.trainCode = this.tvQuery.getText().toString().toUpperCase();
      if (this.trainCode.equals(""))
      {
        MyToast.makeText(this, "请输入车次", 0).show();
        return;
      }
      startResultActivity(this.trainCode);
      return;
    case 2131296798:
      noteFilterDialog(2131296798);
      return;
    case 2131296802:
      noteFilterDialog(2131296802);
      return;
    case 2131296801:
      CharSequence localCharSequence = this.tvFrom.getText();
      this.tvFrom.setText(this.tvTo.getText());
      this.tvTo.setText(localCharSequence);
      return;
    case 2131296805:
    }
    Intent localIntent = new Intent(this, OfflineQueryResultActivity.class);
    localIntent.putExtra("from", this.tvFrom.getText().toString().trim());
    localIntent.putExtra("to", this.tvTo.getText().toString().trim());
    startActivity(localIntent);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initViews();
    this.service = PathService.getInstance();
  }

  protected void onResume()
  {
    initRecentTrain();
    super.onResume();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.MainActivity
 * JD-Core Version:    0.6.0
 */