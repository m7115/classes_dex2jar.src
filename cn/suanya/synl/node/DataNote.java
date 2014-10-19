package cn.suanya.synl.node;

public class DataNote extends SimpleNode
{
  protected String expression;

  public DataNote(String paramString)
  {
    this.expression = paramString;
  }

  public String toString()
  {
    return this.expression;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.DataNote
 * JD-Core Version:    0.6.0
 */