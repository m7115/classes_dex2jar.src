package com.yipiao.bean;

import java.util.List;

public class Page<T>
{
  private int beginIndex;
  private List<T> items;
  private int pageSize;
  private int totalCount;

  public Page()
  {
  }

  public Page(int paramInt1, int paramInt2, int paramInt3, List<T> paramList)
  {
    this.totalCount = paramInt1;
    this.beginIndex = paramInt2;
    this.pageSize = paramInt3;
    this.items = paramList;
  }

  public int getBeginIndex()
  {
    return this.beginIndex;
  }

  public List<T> getItems()
  {
    return this.items;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public int getTotalCount()
  {
    return this.totalCount;
  }

  public void setBeginIndex(int paramInt)
  {
    this.beginIndex = paramInt;
  }

  public void setItems(List<T> paramList)
  {
    this.items = paramList;
  }

  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }

  public void setTotalCount(int paramInt)
  {
    this.totalCount = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.Page
 * JD-Core Version:    0.6.0
 */