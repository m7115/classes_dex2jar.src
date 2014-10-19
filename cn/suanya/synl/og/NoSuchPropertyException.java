package cn.suanya.synl.og;

public class NoSuchPropertyException extends OgnlException
{
  private Object name;
  private Object target;

  public NoSuchPropertyException(Object paramObject1, Object paramObject2)
  {
    super(getReason(paramObject1, paramObject2));
  }

  public NoSuchPropertyException(Object paramObject1, Object paramObject2, Throwable paramThrowable)
  {
    super(getReason(paramObject1, paramObject2), paramThrowable);
    this.target = paramObject1;
    this.name = paramObject2;
  }

  static String getReason(Object paramObject1, Object paramObject2)
  {
    String str;
    if (paramObject1 == null)
      str = "null";
    while (true)
    {
      return str + "." + paramObject2;
      if ((paramObject1 instanceof Class))
      {
        str = ((Class)paramObject1).getName();
        continue;
      }
      str = paramObject1.getClass().getName();
    }
  }

  public Object getName()
  {
    return this.name;
  }

  public Object getTarget()
  {
    return this.target;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.NoSuchPropertyException
 * JD-Core Version:    0.6.0
 */