package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;

public class ASTGreaterEq extends ExpressionNote
{
  protected String getFlag()
  {
    return ">=";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    if (OgnlOps.less(this._children[0].getValue(paramObject), this._children[1].getValue(paramObject)))
      return Boolean.FALSE;
    return Boolean.TRUE;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTGreaterEq
 * JD-Core Version:    0.6.0
 */