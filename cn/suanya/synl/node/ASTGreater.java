package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;

public class ASTGreater extends ExpressionNote
{
  protected String getFlag()
  {
    return ">";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    if (OgnlOps.greater(this._children[0].getValue(paramObject), this._children[1].getValue(paramObject)))
      return Boolean.TRUE;
    return Boolean.FALSE;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTGreater
 * JD-Core Version:    0.6.0
 */