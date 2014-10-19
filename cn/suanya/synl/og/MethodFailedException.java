package cn.suanya.synl.og;

public class MethodFailedException extends OgnlException
{
  public MethodFailedException(Object paramObject, String paramString)
  {
    super("Method \"" + paramString + "\" failed for object " + paramObject);
  }

  public MethodFailedException(Object paramObject, String paramString, Throwable paramThrowable)
  {
    super("Method \"" + paramString + "\" failed for object " + paramObject, paramThrowable);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.MethodFailedException
 * JD-Core Version:    0.6.0
 */