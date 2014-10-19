package com.yipiao.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.suanya.common.a.m;
import cn.suanya.common.a.n;
import com.yipiao.Config;
import com.yipiao.YipiaoApplication;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.MyAsyncTask;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.Huoche;
import com.yipiao.service.PassengerService;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyToast;

public class PassengerEditActivity extends BaseActivity
{
  private EditText ID;
  private TextView cardType;
  private EditText email;
  private EditText name;
  protected UserInfo oldPassenger;
  private UserInfo passenger;
  private TextView perferenceNo;
  private EditText phone;
  private TextView stuNo;
  private View studentInfo;
  private TextView tickType;

  private void showStuInfo(UserInfo paramUserInfo)
  {
    if ("3".equals(paramUserInfo.getTickType()))
    {
      new MyAsyncTask(this, paramUserInfo)
      {
        protected NoteList myInBackground(String[] paramArrayOfString)
          throws Exception
        {
          YipiaoService.getInstance().all12306City();
          YipiaoService.getInstance().all12306University();
          return null;
        }

        protected void myPostExecute(NoteList paramNoteList)
        {
          PassengerEditActivity.this.setTv(2131296980, YipiaoService.getInstance().universityByCode(this.val$passenger.getStuSchoolCode()).getName());
          PassengerEditActivity.access$102(PassengerEditActivity.this, PassengerEditActivity.this.setTv(2131296981, this.val$passenger.getStuNo()));
          Note localNote1 = PassengerEditActivity.this.cfg.schoolSystems.getByCode(this.val$passenger.getStuSchoolSystem());
          PassengerEditActivity localPassengerEditActivity1 = PassengerEditActivity.this;
          String str1;
          String str2;
          if (localNote1 != null)
          {
            str1 = localNote1.getName();
            localPassengerEditActivity1.setTv(2131296983, str1);
            Note localNote2 = PassengerEditActivity.this.cfg.enterYears.getByCode(this.val$passenger.getStuEnterYear());
            PassengerEditActivity localPassengerEditActivity2 = PassengerEditActivity.this;
            if (localNote2 == null)
              break label287;
            str2 = localNote2.getName();
            label140: localPassengerEditActivity2.setTv(2131296985, str2);
            Note localNote3 = PassengerEditActivity.this.cfg.province.getByCode(this.val$passenger.getStuProvinceCode());
            if (localNote3 == null)
              break label294;
            PassengerEditActivity.this.setTv(2131296978, localNote3.getName());
          }
          while (true)
          {
            PassengerEditActivity.this.setTv(2131296988, YipiaoService.getInstance().get12306CityByCode(this.val$passenger.getPreferenceFromNo()).getName());
            PassengerEditActivity.this.setTv(2131296990, YipiaoService.getInstance().get12306CityByCode(this.val$passenger.getPreferenceToNo()).getName());
            PassengerEditActivity.access$502(PassengerEditActivity.this, PassengerEditActivity.this.setTv(2131296986, this.val$passenger.getPreferenceCardNo()));
            PassengerEditActivity.this.studentInfo.setVisibility(0);
            return;
            str1 = "";
            break;
            label287: str2 = "";
            break label140;
            label294: PassengerEditActivity.this.setTv(2131296978, "");
          }
        }

        protected void onException(Exception paramException)
        {
        }
      }
      .execute(new String[0]);
      return;
    }
    this.studentInfo.setVisibility(8);
  }

  protected void assignView(UserInfo paramUserInfo)
  {
    this.name = setEt(2131296419, paramUserInfo.getName());
    this.ID = setEt(2131296969, paramUserInfo.getCardId());
    this.phone = setEt(2131296970, paramUserInfo.getPhone());
    this.email = setEt(2131296971, paramUserInfo.getEmail());
    setTv(2131296651, this.cfg.sexTypes.getByCode(paramUserInfo.getSex(), this.cfg.sexTypes.get(0)).getName());
    this.cardType = setTv(2131296652, this.cfg.cardTypes.getByCode(paramUserInfo.getCardType(), this.cfg.cardTypes.get(0)).getName());
    this.tickType = setTv(2131296973, this.cfg.tickTypes.getByCode(paramUserInfo.getTickType(), this.cfg.tickTypes.get(0)).getName());
    showStuInfo(paramUserInfo);
    String str = paramUserInfo.getUserStatus();
    TextView localTextView1;
    label272: TextView localTextView2;
    if ("1".equalsIgnoreCase(str))
    {
      ((EditText)findViewById(2131296419)).setEnabled(false);
      ((EditText)findViewById(2131296969)).setEnabled(false);
      findViewById(2131296968).setClickable(false);
      ((TextView)findViewById(2131296652)).setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
      localTextView1 = (TextView)findViewById(2131296974);
      if (!"1".equalsIgnoreCase(str))
        break label351;
      localTextView1.setTextColor(getResources().getColor(2131165283));
      localTextView2 = (TextView)findViewById(2131296975);
      if (localTextView2 != null)
      {
        if ("3".equalsIgnoreCase(str))
          break label394;
        localTextView2.setVisibility(8);
      }
    }
    while (true)
    {
      setTv(2131296974, this.cfg.userStatus.getByCode(paramUserInfo.getUserStatus(), this.cfg.userStatus.get(0)).getName());
      return;
      setClick(2131296968, this);
      break;
      label351: if ("2".equalsIgnoreCase(str))
      {
        localTextView1.setTextColor(getResources().getColor(2131165284));
        break label272;
      }
      localTextView1.setTextColor(getResources().getColor(2131165285));
      break label272;
      label394: localTextView2.setVisibility(0);
    }
  }

  public void checkInput()
    throws m
  {
    if (isEmptyV(2131296419))
      throw new m("请输入姓名");
    if (isEmptyV(2131296969))
      throw new m("请输入证件号码");
    if (!"3".equals(this.cfg.tickTypes.getByName(this.tickType.getText().toString()).getCode()));
    do
    {
      return;
      if (isEmptyV(2131296978))
        throw new m("请选择学校省份");
      if (isEmptyV(2131296980))
        throw new m("请选择学校");
      if (isEmptyV(2131296981))
        throw new m("请输入学号");
      if (isEmptyV(2131296983))
        throw new m("请选择学制");
      if (isEmptyV(2131296985))
        throw new m("请选择入学年份");
      if (!isEmptyV(2131296988))
        continue;
      throw new m("请选择优惠区间");
    }
    while (!isEmptyV(2131296990));
    throw new m("请选择优惠区间");
  }

  protected void faceToValue()
  {
    this.passenger.setName(this.name.getText().toString().trim());
    this.passenger.setCardId(this.ID.getText().toString().trim());
    this.passenger.setPhone(this.phone.getText().toString().trim());
    this.passenger.setEmail(this.email.getText().toString().trim());
    this.passenger.setSex(this.cfg.sexTypes.getByName(getVString(2131296651)).getCode());
    this.passenger.setCardType(this.cfg.cardTypes.getByName(this.cardType.getText().toString()).getCode());
    this.passenger.setTickType(this.cfg.tickTypes.getByName(this.tickType.getText().toString()).getCode());
    if ("3".equals(this.passenger.getTickType()))
    {
      this.passenger.setStuEnterYear(this.cfg.enterYears.getByName(getVString(2131296985)).getCode());
      this.passenger.setStuNo(this.stuNo.getText().toString());
      this.passenger.setStuSchoolSystem(this.cfg.schoolSystems.getByName(getVString(2131296983)).getCode());
      this.passenger.setPreferenceCardNo(this.perferenceNo.getText().toString());
    }
  }

  protected int getMainLayout()
  {
    return 2130903127;
  }

  protected UserInfo getPassenger()
  {
    return this.passenger;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 2131296977) && (paramInt2 != 0))
    {
      Note localNote4 = (Note)paramIntent.getExtras().get("DATA");
      setTv(2131296978, localNote4.getName());
      this.passenger.setStuProvinceCode(localNote4.getCode());
    }
    if ((paramInt1 == 2131296979) && (paramInt2 != 0))
    {
      Note localNote3 = (Note)paramIntent.getExtras().get("DATA");
      setTv(2131296980, localNote3.getName());
      this.passenger.setStuSchoolCode(localNote3.getCode());
    }
    if ((paramInt1 == 2131296987) && (paramInt2 != 0))
    {
      Note localNote2 = (Note)paramIntent.getExtras().get("DATA");
      setTv(2131296988, localNote2.getName());
      this.passenger.setPreferenceFromNo(localNote2.getCode());
    }
    if ((paramInt1 == 2131296989) && (paramInt2 != 0))
    {
      Note localNote1 = (Note)paramIntent.getExtras().get("DATA");
      setTv(2131296990, localNote1.getName());
      this.passenger.setPreferenceToNo(localNote1.getCode());
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296259:
    case 2131296885:
      savePassenger();
      return;
    case 2131296968:
      showDialog(105);
      return;
    case 2131296972:
      showDialog(106);
      return;
    case 2131296977:
      noteQueryDialog(2131296977, this.cfg.province);
      return;
    case 2131296979:
      if ((this.passenger.getStuProvinceCode() == null) || ("".equals(this.passenger.getStuProvinceCode())))
      {
        showToast("请先选择省份");
        return;
      }
      selectSchool(this.passenger.getStuProvinceCode());
      return;
    case 2131296987:
      noteQueryDialog(2131296987, YipiaoService.getInstance().all12306City());
      return;
    case 2131296989:
    }
    noteQueryDialog(2131296989, YipiaoService.getInstance().all12306City());
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (checkNeedLaunch())
      return;
    long l1 = System.currentTimeMillis();
    this.passenger = ((UserInfo)getIntent().getSerializableExtra("passenger"));
    if (this.passenger == null)
      this.passenger = ((UserInfo)this.app.getParms("passenger"));
    this.oldPassenger = this.passenger.clone();
    this.studentInfo = findViewById(2131296976);
    assignView(this.passenger);
    long l2 = System.currentTimeMillis();
    n.a(l2 - l1 + "");
    setClick(2131296984, 10020);
    setClick(2131296982, 10010);
    setClick(2131296977, this);
    setClick(2131296979, this);
    setClick(2131296987, this);
    setClick(2131296989, this);
    setClick(2131296885, this);
    setClick(2131296259, this);
    setClick(2131296780, this);
    setClick(2131296782, this);
    setClick(2131296991, this);
    setClick(2131296972, this);
    setClick(2131296967, 107);
  }

  protected void onHasLogined(int paramInt)
  {
    onLoginSuccess(paramInt);
    if (paramInt == 2130903181)
      uploadUser();
  }

  public void savePassenger()
  {
    try
    {
      checkInput();
      faceToValue();
      this.passengerService.updatePassenger(this.passenger, this.oldPassenger);
      uploadUser();
      setResult(-1, new Intent().setAction(this.passenger.getName()));
      finish();
      return;
    }
    catch (m localm)
    {
      MyToast.makeText(this, localm.getMessage(), 1).show();
    }
  }

  public void selectSchool(String paramString)
  {
    new MyAsyncTask(this)
    {
      protected NoteList myInBackground(String[] paramArrayOfString)
        throws Exception
      {
        return PassengerEditActivity.this.getHc().getSchool(paramArrayOfString[0]);
      }

      protected void myPostExecute(NoteList paramNoteList)
      {
        PassengerEditActivity.this.noteQueryDialog(2131296979, paramNoteList);
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
      }
    }
    .execute(new String[] { paramString });
  }

  protected void tickTypeSelect(Note paramNote)
  {
    super.tickTypeSelect(paramNote);
    this.passenger.setTickType(paramNote.getCode());
    showStuInfo(this.passenger);
  }

  public void uploadUser()
  {
    new MyAsyncTask(this, false)
    {
      protected Void myInBackground(Void[] paramArrayOfVoid)
        throws Exception
      {
        PassengerEditActivity.this.getHc().updatePassenger(PassengerEditActivity.this.passenger, PassengerEditActivity.this.oldPassenger);
        return null;
      }

      protected void myPostExecute(Void paramVoid)
      {
      }

      protected void onException(Exception paramException)
      {
        super.onException(paramException);
      }
    }
    .execute(new Void[0]);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.activity.PassengerEditActivity
 * JD-Core Version:    0.6.0
 */