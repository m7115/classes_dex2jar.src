package com.yipiao.adapter;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.suanya.common.net.LogInfo;
import com.yipiao.Config;
import com.yipiao.activity.PassengerSetActivity;
import com.yipiao.base.BaseActivity;
import com.yipiao.base.BaseViewAdapter;
import com.yipiao.bean.Note;
import com.yipiao.bean.NoteList;
import com.yipiao.bean.Ticket;
import com.yipiao.bean.UserInfo;
import com.yipiao.service.PassengerService;
import com.yipiao.service.YipiaoService;
import com.yipiao.view.MyAlertDialog.Builder;
import java.util.Iterator;
import java.util.List;

public class OrderPassengerListAdapter extends BaseViewAdapter<UserInfo>
{
  private List<Ticket> tickets = null;
  private String[] ticketsArray;

  public OrderPassengerListAdapter(BaseActivity paramBaseActivity, List<UserInfo> paramList, int paramInt)
  {
    super(paramBaseActivity, paramList, paramInt);
  }

  private int getSeatTypeIndex(UserInfo paramUserInfo, List<Ticket> paramList)
  {
    if (paramList == null);
    while (true)
    {
      return 0;
      Note localNote = Config.getInstance().seatTypesAll.getByCode(paramUserInfo.getSeatType());
      if (localNote == null)
        continue;
      for (int i = 0; i < paramList.size(); i++)
      {
        Ticket localTicket = (Ticket)paramList.get(i);
        if (localNote.getName().equals(localTicket.getSeatName()))
          return i;
      }
    }
  }

  private int hasTicket(Note paramNote, List<Ticket> paramList)
  {
    if ((paramNote == null) || (paramList == null))
    {
      i = -1;
      return i;
    }
    Iterator localIterator = paramList.iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label73;
      Ticket localTicket = (Ticket)localIterator.next();
      if ((paramNote.getName().equals(localTicket.getSeatName())) && (localTicket.hasTicket()))
        break;
    }
    label73: return -1;
  }

  private void iniUserListSeatType()
  {
    Iterator localIterator = this.mlist.iterator();
    while (localIterator.hasNext())
      iniUserSeatType((UserInfo)localIterator.next());
  }

  public List<Ticket> getTickets()
  {
    return this.tickets;
  }

  public Note iniUserSeatType(UserInfo paramUserInfo)
  {
    for (int i = 0; i < paramUserInfo.getLikeSeatTypes().length; i++)
    {
      String str = paramUserInfo.getLikeSeatTypes()[i];
      Note localNote2 = Config.getInstance().seatTypesAll.getByCode(str);
      if (hasTicket(localNote2, this.tickets) < 0)
        continue;
      paramUserInfo.setSeatType(localNote2.getCode());
      return localNote2;
    }
    Iterator localIterator = Config.getInstance().seatTypesAll.iterator();
    while (localIterator.hasNext())
    {
      Note localNote1 = (Note)localIterator.next();
      if (hasTicket(localNote1, this.tickets) < 0)
        continue;
      paramUserInfo.setSeatType(localNote1.getCode());
      return localNote1;
    }
    paramUserInfo.setSeatType(paramUserInfo.getLikeSeatTypes()[0]);
    return null;
  }

  protected View renderItem(UserInfo paramUserInfo, View paramView)
  {
    ((TextView)paramView.findViewById(2131296949)).setText(paramUserInfo.getName());
    setTv(2131296950, Config.getInstance().tickTypesHint.getByCode(paramUserInfo.getTickType(), Config.getInstance().tickTypesHint.get(0)).getName());
    Button localButton1 = (Button)paramView.findViewById(2131296948);
    localButton1.setTag(paramUserInfo);
    if ((this.tickets != null) && (!this.tickets.isEmpty()))
    {
      localButton1.setText(this.ticketsArray[getSeatTypeIndex(paramUserInfo, this.tickets)]);
      localButton1.setOnClickListener(new View.OnClickListener(paramUserInfo)
      {
        public void onClick(View paramView)
        {
          Button localButton = (Button)paramView;
          new MyAlertDialog.Builder(OrderPassengerListAdapter.this.mContext).setSingleChoiceItems(OrderPassengerListAdapter.this.ticketsArray, OrderPassengerListAdapter.this.getSeatTypeIndex(this.val$item, OrderPassengerListAdapter.this.tickets), new DialogInterface.OnClickListener(localButton)
          {
            public void onClick(DialogInterface paramDialogInterface, int paramInt)
            {
              if ((OrderPassengerListAdapter.this.tickets == null) || (OrderPassengerListAdapter.this.tickets.size() <= paramInt))
                return;
              Ticket localTicket = (Ticket)OrderPassengerListAdapter.this.tickets.get(paramInt);
              Note localNote = Config.getInstance().seatTypesAll.getByName(localTicket.getSeatName());
              if (localNote != null)
              {
                UserInfo localUserInfo = (UserInfo)this.val$btn.getTag();
                localUserInfo.setSeatType(localNote.getCode());
                PassengerService.getInstance().setLikedSeatType(localUserInfo, localNote.getCode());
                ((PassengerSetActivity)OrderPassengerListAdapter.this.mContext).seatType = localNote.getCode();
                this.val$btn.setText(OrderPassengerListAdapter.this.ticketsArray[paramInt]);
              }
              while (true)
              {
                paramDialogInterface.dismiss();
                return;
                YipiaoService.getInstance().asyncLog(new LogInfo("noSeatType", "noSeatType=" + localTicket.getSeatName()));
              }
            }
          }).show();
        }
      });
    }
    Button localButton2 = (Button)paramView.findViewById(2131296947);
    if (localButton2 != null)
    {
      localButton2.setTag(paramUserInfo);
      localButton2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ((PassengerSetActivity)OrderPassengerListAdapter.this.mContext).removePassenger((UserInfo)paramView.getTag());
        }
      });
    }
    View localView = paramView.findViewById(2131296946);
    if (localView != null)
      localView.setOnClickListener(new View.OnClickListener(localButton2)
      {
        public void onClick(View paramView)
        {
          this.val$passengerDelete.performClick();
        }
      });
    paramView.setTag(paramUserInfo);
    return paramView;
  }

  public void setTickets(List<Ticket> paramList)
  {
    if (paramList == null)
      return;
    this.ticketsArray = new String[paramList.size()];
    int i = 0;
    if (i < paramList.size())
    {
      Ticket localTicket = (Ticket)paramList.get(i);
      if (localTicket.hasTicket())
        this.ticketsArray[i] = (localTicket.getSeatName() + "¥" + localTicket.getPrice() + "元");
      while (true)
      {
        i++;
        break;
        this.ticketsArray[i] = (localTicket.getSeatName() + "¥" + localTicket.getPrice() + "元(无票)");
      }
    }
    this.tickets = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.adapter.OrderPassengerListAdapter
 * JD-Core Version:    0.6.0
 */