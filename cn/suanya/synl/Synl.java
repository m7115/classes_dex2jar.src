package cn.suanya.synl;

public class Synl
{
  public Object getValue(String paramString, Object paramObject)
    throws Exception
  {
    return new SynlParser().parser(paramString).getValue(paramObject);
  }

  public void setValue(String paramString, Object paramObject1, Object paramObject2)
    throws Exception
  {
    new SynlParser().parser(paramString).setValue(paramObject1, paramObject2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.Synl
 * JD-Core Version:    0.6.0
 */