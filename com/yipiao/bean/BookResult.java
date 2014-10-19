package com.yipiao.bean;

import java.util.Iterator;
import java.util.List;

public class BookResult
{
  private String leftTicketStr;
  private List<Ticket> tickets;
  private String token;
  private List<UserInfo> userList;

  public String getLeftTicketStr()
  {
    return this.leftTicketStr;
  }

  public List<Ticket> getTickets()
  {
    return this.tickets;
  }

  public String getToken()
  {
    return this.token;
  }

  public List<UserInfo> getUserList()
  {
    return this.userList;
  }

  public void setLeftTicketStr(String paramString)
  {
    this.leftTicketStr = paramString;
  }

  public void setTickets(List<Ticket> paramList)
  {
    this.tickets = paramList;
  }

  public void setToken(String paramString)
  {
    this.token = paramString;
  }

  public void setUserList(List<UserInfo> paramList)
  {
    this.userList = paramList;
  }

  public String ticketString()
  {
    Iterator localIterator = this.tickets.iterator();
    Ticket localTicket;
    for (String str = ""; localIterator.hasNext(); str = str + localTicket.toString() + "&nbsp;")
      localTicket = (Ticket)localIterator.next();
    return str;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.BookResult
 * JD-Core Version:    0.6.0
 */