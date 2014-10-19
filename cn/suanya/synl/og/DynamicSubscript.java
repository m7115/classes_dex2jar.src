package cn.suanya.synl.og;

public class DynamicSubscript
{
  public static final int ALL = 3;
  public static final int FIRST = 0;
  public static final int LAST = 2;
  public static final int MID = 1;
  public static final DynamicSubscript all;
  public static final DynamicSubscript first = new DynamicSubscript(0);
  public static final DynamicSubscript last;
  public static final DynamicSubscript mid = new DynamicSubscript(1);
  private int flag;

  static
  {
    last = new DynamicSubscript(2);
    all = new DynamicSubscript(3);
  }

  private DynamicSubscript(int paramInt)
  {
    this.flag = paramInt;
  }

  public int getFlag()
  {
    return this.flag;
  }

  public String toString()
  {
    switch (this.flag)
    {
    default:
      return "?";
    case 0:
      return "^";
    case 1:
      return "|";
    case 2:
      return "$";
    case 3:
    }
    return "*";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.DynamicSubscript
 * JD-Core Version:    0.6.0
 */