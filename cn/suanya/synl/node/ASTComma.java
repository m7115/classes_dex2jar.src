package cn.suanya.synl.node;

import cn.suanya.synl.Node;

public class ASTComma extends ExpressionNote
{
  protected String getFlag()
  {
    return ",";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    Object[] arrayOfObject = new Object[this._children.length];
    int i = 0;
    int j = -1 + this._children.length;
    while (i <= j)
    {
      arrayOfObject[i] = this._children[i].getValue(paramObject);
      i++;
    }
    return arrayOfObject;
  }

  public String toString()
  {
    Object localObject = "";
    if (this._children != null)
    {
      Node[] arrayOfNode = this._children;
      int i = arrayOfNode.length;
      int j = 0;
      while (j < i)
      {
        Node localNode = arrayOfNode[j];
        String str = (String)localObject + getFlag() + localNode.toString();
        j++;
        localObject = str;
      }
    }
    if (((String)localObject).trim().length() <= 0)
      return localObject;
    return (String)((String)localObject).substring(getFlag().length());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTComma
 * JD-Core Version:    0.6.0
 */