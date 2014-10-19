package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;

public class ASTAdd extends ExpressionNote
{
  protected String getFlag()
  {
    return "+";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    Object localObject = this._children[0].getValue(paramObject);
    for (int i = 1; i < this._children.length; i++)
      localObject = OgnlOps.add(localObject, this._children[i].getValue(paramObject));
    return localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTAdd
 * JD-Core Version:    0.6.0
 */