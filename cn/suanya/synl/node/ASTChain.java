package cn.suanya.synl.node;

import cn.suanya.synl.Node;

public class ASTChain extends ExpressionNote
{
  protected String getFlag()
  {
    return ".";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    int i = 0;
    int j = -1 + this._children.length;
    Object localObject = paramObject;
    while (i <= j)
    {
      localObject = this._children[i].getValue(paramObject, localObject);
      i++;
    }
    return localObject;
  }

  protected void setValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    int i = -1 + this._children.length;
    int j = 0;
    Object localObject = paramObject1;
    if (j <= i)
    {
      Node localNode = this._children[j];
      if (j == i)
        localNode.setValue(localObject, paramObject2);
      while (true)
      {
        j++;
        break;
        localObject = localNode.getValue(paramObject1, localObject);
      }
    }
  }

  public String toString()
  {
    Node[] arrayOfNode = this._children;
    int i = arrayOfNode.length;
    Object localObject = "";
    int j = 0;
    while (j < i)
    {
      Node localNode = arrayOfNode[j];
      String str = (String)localObject + getFlag() + localNode.toString();
      j++;
      localObject = str;
    }
    if (((String)localObject).trim().length() <= 0)
      return localObject;
    return (String)((String)localObject).substring(getFlag().length());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTChain
 * JD-Core Version:    0.6.0
 */