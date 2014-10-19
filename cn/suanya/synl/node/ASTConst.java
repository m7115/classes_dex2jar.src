package cn.suanya.synl.node;

public class ASTConst extends DataNote
{
  private Object value;

  public ASTConst(Object paramObject)
  {
    super(null);
    this.value = paramObject;
  }

  protected Object getValueBody(Object paramObject)
  {
    return this.value;
  }

  public String toString()
  {
    if (this.value == null)
      return "null";
    return this.value.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTConst
 * JD-Core Version:    0.6.0
 */