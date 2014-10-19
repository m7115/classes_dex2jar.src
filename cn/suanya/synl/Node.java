package cn.suanya.synl;

public abstract interface Node
{
  public abstract void AddChild(Node paramNode);

  public abstract Node GetParent();

  public abstract void SetParent(Node paramNode);

  public abstract void delChild(Node paramNode);

  public abstract int getLevel();

  public abstract Object getValue(Object paramObject)
    throws Exception;

  public abstract Object getValue(Object paramObject1, Object paramObject2)
    throws Exception;

  public abstract void setLevel(int paramInt);

  public abstract void setValue(Object paramObject1, Object paramObject2)
    throws Exception;
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.Node
 * JD-Core Version:    0.6.0
 */