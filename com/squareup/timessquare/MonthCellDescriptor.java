package com.squareup.timessquare;

import java.util.Date;

class MonthCellDescriptor
{
  private final Date date;
  private final boolean isCurrentMonth;
  private final boolean isSelectable;
  private boolean isSelected;
  private final boolean isToday;
  private final int value;

  MonthCellDescriptor(Date paramDate, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, int paramInt)
  {
    this.date = paramDate;
    this.isCurrentMonth = paramBoolean1;
    this.isSelectable = paramBoolean2;
    this.isSelected = paramBoolean3;
    this.isToday = paramBoolean4;
    this.value = paramInt;
  }

  public Date getDate()
  {
    return this.date;
  }

  public int getValue()
  {
    return this.value;
  }

  public boolean isCurrentMonth()
  {
    return this.isCurrentMonth;
  }

  public boolean isSelectable()
  {
    return this.isSelectable;
  }

  public boolean isSelected()
  {
    return this.isSelected;
  }

  public boolean isToday()
  {
    return this.isToday;
  }

  public void setSelected(boolean paramBoolean)
  {
    this.isSelected = paramBoolean;
  }

  public String toString()
  {
    return "MonthCellDescriptor{date=" + this.date + ", value=" + this.value + ", isCurrentMonth=" + this.isCurrentMonth + ", isSelected=" + this.isSelected + ", isToday=" + this.isToday + ", isSelectable=" + this.isSelectable + '}';
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.MonthCellDescriptor
 * JD-Core Version:    0.6.0
 */