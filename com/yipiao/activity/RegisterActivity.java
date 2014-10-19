package com.yipiao.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import cn.suanya.common.a.i;
import cn.suanya.common.a.m;
import cn.suanya.common.a.v;
import cn.suanya.common.net.LogInfo;
import com.yipiao.Config;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.MulImage;
import com.yipiao.base.SYSignView.SignListenerBase;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyAlertDialog.Builder;
import com.yipiao.view.MyToast;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity
  implements View.OnFocusChangeListener
{
  protected static final int dialog_regist_success = 10030;
  private Note enterYear;
  private String nameRegex = "^[A-Za-z]{1}([A-Za-z0-9]|[_]){5,29}$";
  private Note passengerType;
  private Note preference1;
  private Note preference2;
  private Note province;
  private Map<String, String> regInfo = new HashMap();
  private Note school;
  private NoteList schoolList = new NoteList();
  private Note schoolSystem;
  private SYSignView signView;
  private View studentInfo;

  private void checkUserName(String paramString)
  {
    new MyAsyncTask(this, false, paramString)
    {
      protected Boolean myInBackground(Map<String, String>[] paramArrayOfMap)
        throws Exception
      {
        return Boolean.valueOf(RegisterActivity.this.getHc().registerCheckName(this.val$name));
      }

      protected void myPostExecute(Boolean paramBoolean)
      {
        if (paramBoolean.booleanValue())
        {
          RegisterActivity.this.setTv(2131297050, "该用户名已经占用，请重新选择用户名！");
          RegisterActivity.this.setVisibility(2131297050, 0);
        }
      }

      protected void onException(Exception paramException)
      {
      }
    }
    .execute(new Map[0]);
  }

  public void checkInput()
    throws m
  {
    if (!getVString(2131297008).matches(this.nameRegex))
      throw new m("账号不能少于6位,并以字母开头");
    if (isEmptyV(2131297051))
      throw new m("请输入密码");
    String str = getVString(2131297051);
    if ((str.length() < 6) || (!str.matches(".*\\d+.*")) || (!str.matches(".*[a-zA-Z]+.*")))
      throw new m("密码不能少于6位,并同时包含数字和字母");
    if (isEmptyV(2131296419))
      throw new m("请输入姓名");
    if (isEmptyV(2131297054))
      throw new m("请输入证件号码");
    if (isEmptyV(2131297004))
      throw new m("请输入电话号码");
    if (!v.b(getVString(2131296971)))
      throw new m("请输入正确的邮件地址");
    if (isEmpty(this.signView.getSign()))
      throw new m("请输入验证码");
    if (!"3".equals(this.passengerType.getCode()));
    do
    {
      return;
      if (this.province == null)
        throw new m("请选择学校省份");
      if (this.school == null)
        throw new m("请选择学校");
      if (isEmptyV(2131296981))
        throw new m("请输入学号");
      if (this.schoolSystem == null)
        throw new m("请选择学制");
      if (this.enterYear == null)
        throw new m("请选择入学年份");
      if (this.preference1 != null)
        continue;
      throw new m("请选择优惠区间");
    }
    while (this.preference2 != null);
    throw new m("请选择优惠区间");
  }

  protected void enterYearSelect(Note paramNote)
  {
    this.enterYear = paramNote;
    super.enterYearSelect(paramNote);
  }

  protected int getMainLayout()
  {
    return 2130903155;
  }

  public Map<String, String> getRegInfo()
  {
    this.regInfo.put("randCode", this.signView.getSign());
    this.regInfo.put("loginUser_user_name", getVString(2131297008));
    this.regInfo.put("user_password", getVString(2131297051));
    this.regInfo.put("confirmPassWord", getVString(2131297051));
    this.regInfo.put("user_IVR_passwd", "888888");
    this.regInfo.put("confirmIvr_pwd", "888888");
    this.regInfo.put("user_pwd_question", "");
    this.regInfo.put("otherpasswordQuestion", "");
    this.regInfo.put("user_pwd_answer", "");
    this.regInfo.put("loginUser_name", getVString(2131296419));
    this.regInfo.put("user_sex_code", this.cfg.sexTypes.getByName(getVString(2131296651)).getCode());
    this.regInfo.put("user_born_date", "1970-01-01");
    this.regInfo.put("user_country_code", "CN");
    this.regInfo.put("loginUser_id_type_code", this.cfg.cardTypes.getByName(getVString(2131296652)).getCode());
    this.regInfo.put("loginUser_id_no", getVString(2131297054));
    this.regInfo.put("user_mobile_no", getVString(2131297004));
    this.regInfo.put("user_phone_no", "");
    this.regInfo.put("user_email", getVString(2131296971));
    this.regInfo.put("user_address", "");
    this.regInfo.put("user_postalcode", "");
    this.regInfo.put("passenger_type", this.passengerType.getCode());
    if (!"3".equals(this.passengerType.getCode()))
      return this.regInfo;
    this.regInfo.put("studentInfo_province_code", this.province.getCode());
    this.regInfo.put("studentInfo_school_code", this.school.getCode());
    this.regInfo.put("studentInfo_school_name", this.school.getName());
    this.regInfo.put("studentInfo_department", "");
    this.regInfo.put("studentInfo_school_class", "");
    this.regInfo.put("studentInfo_student_no", getVString(2131296981));
    this.regInfo.put("studentInfo_school_system", this.schoolSystem.getCode());
    this.regInfo.put("studentInfo_enter_year", this.enterYear.getCode());
    this.regInfo.put("studentInfo_preference_card_no", "");
    this.regInfo.put("studentInfo_preference_from_station_name", this.preference1.getName());
    this.regInfo.put("studentInfo_preference_from_station_code", this.preference1.getCode());
    this.regInfo.put("studentInfo_preference_to_station_name", this.preference2.getName());
    this.regInfo.put("studentInfo_preference_to_station_code", this.preference2.getCode());
    return this.regInfo;
  }

  public void getSchool(String paramString)
  {
    new MyAsyncTask(this)
    {
      protected NoteList myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return RegisterActivity.this.getHc().getSchool(paramArrayOfString[0]);
      }

      protected void myPostExecute(NoteList paramNoteList)
      {
        RegisterActivity.access$302(RegisterActivity.this, paramNoteList);
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
      }
    }
    .execute(new String[] { paramString });
  }

  protected SYSignView.MulImage loadSign()
    throws Exception
  {
    return getHc().registerSign();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 2131296977)
    {
      if (paramInt2 != 0)
      {
        this.province = ((Note)paramIntent.getExtras().get("DATA"));
        setTv(2131296978, this.province.getName());
        getSchool(this.province.getCode());
        setVisibility(2131297059, 8);
      }
    }
    else
    {
      if (paramInt1 == 2131296979)
      {
        if (paramInt2 == 0)
          break label250;
        this.school = ((Note)paramIntent.getExtras().get("DATA"));
        setTv(2131296980, this.school.getName());
        setVisibility(2131297060, 8);
      }
      label117: if (paramInt1 == 2131296987)
      {
        if (paramInt2 == 0)
          break label269;
        this.preference1 = ((Note)paramIntent.getExtras().get("DATA"));
        setTv(2131296988, this.preference1.getName());
        setVisibility(2131297062, 8);
      }
      label170: if (paramInt1 == 2131296989)
      {
        if (paramInt2 == 0)
          break label288;
        this.preference2 = ((Note)paramIntent.getExtras().get("DATA"));
        setTv(2131296990, this.preference2.getName());
        setVisibility(2131297063, 8);
      }
    }
    while (true)
    {
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
      if (this.province != null)
        break;
      setVisibility(2131297059, 0);
      break;
      label250: if (this.school != null)
        break label117;
      setVisibility(2131297060, 0);
      break label117;
      label269: if (this.preference1 != null)
        break label170;
      setVisibility(2131297062, 0);
      break label170;
      label288: if (this.preference2 != null)
        continue;
      setVisibility(2131297063, 0);
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131297064:
    case 2131296977:
    case 2131296979:
    case 2131296987:
    case 2131296989:
    }
    while (true)
    {
      super.onClick(paramView);
      return;
      submitRegister();
      continue;
      noteQueryDialog(2131296977, this.cfg.province);
      continue;
      if ((this.province == null) || (this.province.getCode() == null) || (this.province.getCode().equals("")))
      {
        showToast("请先选择省份");
        continue;
      }
      if ((this.schoolList == null) || (this.schoolList.size() <= 0))
      {
        showToast("请重新先选择省份，并等待数据加载完后再继续");
        continue;
      }
      noteQueryDialog(2131296979, this.schoolList);
      continue;
      noteQueryDialog(2131296987, YipiaoService.getInstance().all12306City());
      continue;
      noteQueryDialog(2131296989, YipiaoService.getInstance().all12306City());
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.passengerType = this.cfg.passengerTypes.getByCode("1");
    this.enterYear = this.cfg.enterYears.get(0);
    this.schoolSystem = this.cfg.schoolSystems.get(3);
    this.signView = ((SYSignView)findViewById(2131296771));
    this.signView.init(2130903166, new SYSignView.SignListenerBase()
    {
      public SYSignView.MulImage load()
        throws Exception
      {
        return RegisterActivity.this.getHc().registerSign();
      }
    });
    setTv(2131296985, this.enterYear.getName());
    setTv(2131296983, this.schoolSystem.getName());
    setClick(2131296967, 107);
    setClick(2131296968, 105);
    setClick(2131297058, 108);
    this.studentInfo = findViewById(2131296976);
    setClick(2131296984, 10020);
    setClick(2131296982, 10010);
    setClick(2131296977, this);
    setClick(2131297064, this);
    setClick(2131296979, this);
    setClick(2131296987, this);
    setClick(2131296989, this);
    setFocusChanger(2131297008, this);
    setFocusChanger(2131297051, this);
    setFocusChanger(2131296419, this);
    setFocusChanger(2131297054, this);
    setFocusChanger(2131297004, this);
    setFocusChanger(2131296971, this);
    setFocusChanger(2131296981, this);
    setTv(2131296265, Html.fromHtml(""));
    this.signView.refreshSign();
    this.signView.getEditText().clearFocus();
    findViewById(2131297008).requestFocus();
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 10030:
    }
    return new MyAlertDialog.Builder(this).setTitle("提示").setMessage("注册成功,请登录邮箱" + getVString(2131296971) + "激活账号，就可以订票了。").setPositiveButton("进入邮箱", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        String str1 = RegisterActivity.this.getVString(2131296971).split("@")[1];
        if (str1.equalsIgnoreCase("gmail.com"));
        for (String str2 = "http://gmail.com"; ; str2 = "http://mail." + str1)
        {
          RegisterActivity.this.goWebActivity(str2, null, null);
          RegisterActivity.this.finish();
          return;
        }
      }
    }).setNegativeButton("直接登录", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        Intent localIntent = new Intent(RegisterActivity.this, UserSetActivity.class);
        RegisterActivity.this.startActivity(localIntent);
        RegisterActivity.this.finish();
      }
    }).create();
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    showErrorTip(paramView.getId(), paramBoolean);
  }

  protected void onRuleMessage(int paramInt, String paramString)
  {
    if (paramInt == 101)
    {
      String str = this.signView.getSign();
      if ((str == null) || (str.length() == 0))
        this.signView.setSign(paramString);
    }
    super.onRuleMessage(paramInt, paramString);
  }

  protected void passengerTypeSelect(Note paramNote)
  {
    super.passengerTypeSelect(paramNote);
    this.passengerType = paramNote;
    if ("3".equals(paramNote.getCode()))
    {
      this.studentInfo.setVisibility(0);
      return;
    }
    this.studentInfo.setVisibility(8);
  }

  protected void schoolSystemSelect(Note paramNote)
  {
    this.schoolSystem = paramNote;
    super.schoolSystemSelect(paramNote);
  }

  protected void showErrorTip(int paramInt, boolean paramBoolean)
  {
    switch (paramInt)
    {
    default:
    case 2131297008:
    case 2131297051:
    case 2131296419:
    case 2131297054:
    case 2131297004:
    case 2131296971:
    case 2131296981:
    }
    do
    {
      return;
      if (paramBoolean)
      {
        setVisibility(2131297050, 8);
        return;
      }
      String str2 = getVString(2131297008);
      if (!str2.matches(this.nameRegex))
      {
        setTv(2131297050, "请输入6-30位数字或字母，并以字母开头");
        setVisibility(2131297050, 0);
        return;
      }
      setVisibility(2131297050, 8);
      checkUserName(str2);
      return;
      if (paramBoolean)
      {
        setVisibility(2131297052, 8);
        return;
      }
      String str1 = getVString(2131297051);
      if (str1.length() < 6)
      {
        setTv(2131297052, "密码不能少于6位数字和字母");
        setVisibility(2131297052, 0);
        return;
      }
      if ((!str1.matches(".*\\d+.*")) || (!str1.matches(".*[a-zA-Z]+.*")))
      {
        setTv(2131297052, "密码必须同时包含数字和字母");
        setVisibility(2131297052, 0);
        return;
      }
      if (!str1.matches("^[A-Za-z0-9_]+$"))
      {
        setTv(2131297052, "密码只能包含字母，数字和下划线");
        setVisibility(2131297052, 0);
        return;
      }
      setVisibility(2131297052, 8);
      return;
      if (paramBoolean)
      {
        setVisibility(2131297053, 8);
        return;
      }
      if (isEmptyV(2131296419))
      {
        setVisibility(2131297053, 0);
        return;
      }
      setVisibility(2131297053, 8);
      return;
      if (paramBoolean)
      {
        setVisibility(2131297055, 8);
        return;
      }
      if (isEmptyV(2131297054))
      {
        setVisibility(2131297055, 0);
        return;
      }
      setVisibility(2131297055, 8);
      return;
      if (paramBoolean)
      {
        setVisibility(2131297056, 8);
        return;
      }
      if (isEmptyV(2131297004))
      {
        setVisibility(2131297056, 0);
        return;
      }
      setVisibility(2131297056, 8);
      return;
      if (paramBoolean)
      {
        setVisibility(2131297057, 8);
        return;
      }
      if (!v.b(getVString(2131296971)))
      {
        setVisibility(2131297057, 0);
        return;
      }
      setVisibility(2131297057, 8);
      return;
      if (!paramBoolean)
        continue;
      setVisibility(2131297061, 8);
      return;
    }
    while (!"3".equals(this.passengerType.getCode()));
    if (isEmptyV(2131296981))
    {
      setVisibility(2131297061, 0);
      return;
    }
    setVisibility(2131297061, 8);
  }

  public void submitRegister()
  {
    try
    {
      checkInput();
      new MyAsyncTask(this)
      {
        protected Object myInBackground(Map<String, String>[] paramArrayOfMap)
          throws Exception
        {
          Map localMap = RegisterActivity.this.getRegInfo();
          RegisterActivity.this.getHc().submitRegister(localMap);
          return null;
        }

        protected void myPostExecute(Object paramObject)
        {
          UserInfo localUserInfo = new UserInfo();
          localUserInfo.setCardType(RegisterActivity.this.cfg.cardTypes.getByName(RegisterActivity.this.getVString(2131296652)).getCode());
          localUserInfo.setCardId(RegisterActivity.this.getVString(2131297054));
          localUserInfo.setPhone(RegisterActivity.this.getVString(2131297004));
          localUserInfo.setName(RegisterActivity.this.getVString(2131296419));
          RegisterActivity.this.logToServer("regist", localUserInfo.getSimpleText());
          RegisterActivity.this.showDialog(10030);
        }

        protected void onException(Exception paramException)
        {
          RegisterActivity.this.logToServer(new LogInfo("registerError", paramException + "" + i.a(RegisterActivity.this.regInfo)));
          RegisterActivity.this.signView.refreshSign();
          super.onException(paramException);
        }
      }
      .execute(new Map[0]);
      return;
    }
    catch (m localm)
    {
      logToServer(new LogInfo("registerError", localm));
      MyToast.makeText(this, localm.getMessage(), 1).show();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.RegisterActivity
 * JD-Core Version:    0.6.0
 */