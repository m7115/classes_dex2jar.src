package com.squareup.timessquare;

class MonthDescriptor
{
  private final String label;
  private final int month;
  private final int year;

  public MonthDescriptor(int paramInt1, int paramInt2, String paramString)
  {
    this.month = paramInt1;
    this.year = paramInt2;
    this.label = paramString;
  }

  public String getLabel()
  {
    return this.label;
  }

  public int getMonth()
  {
    return this.month;
  }

  public int getYear()
  {
    return this.year;
  }

  public String toString()
  {
    return "MonthDescriptor{label='" + this.label + '\'' + ", month=" + this.month + ", year=" + this.year + '}';
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.timessquare.MonthDescriptor
 * JD-Core Version:    0.6.0
 */