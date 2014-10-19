package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;

public class ASTAnd extends ExpressionNote
{
  protected String getFlag()
  {
    return "&&";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    int i = -1 + this._children.length;
    Object localObject = null;
    for (int j = 0; ; j++)
      if (j <= i)
      {
        localObject = this._children[j].getValue(paramObject);
        if ((j == i) || (OgnlOps.booleanValue(localObject)))
          continue;
      }
      else
      {
        return localObject;
      }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTAnd
 * JD-Core Version:    0.6.0
 */