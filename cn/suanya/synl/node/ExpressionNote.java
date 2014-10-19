package cn.suanya.synl.node;

import cn.suanya.synl.Node;

public class ExpressionNote extends SimpleNode
{
  protected String getFlag()
  {
    return "+";
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
    if (((String)localObject).length() <= 0)
      return localObject;
    return (String)("(" + ((String)localObject).substring(getFlag().length()) + ")");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ExpressionNote
 * JD-Core Version:    0.6.0
 */