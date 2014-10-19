package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;

public class ASTNot extends ExpressionNote
{
  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    if (OgnlOps.booleanValue(this._children[0].getValue(paramObject)))
      return Boolean.FALSE;
    return Boolean.TRUE;
  }

  public String toString()
  {
    if ((this._children == null) || (this._children.length == 0))
      return "!";
    return "!" + this._children[0].toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTNot
 * JD-Core Version:    0.6.0
 */