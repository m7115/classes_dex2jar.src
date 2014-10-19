package cn.suanya.synl.node;

import cn.suanya.synl.Node;

public class ASTAssign extends ExpressionNote
{
  protected String getFlag()
  {
    return "=";
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    Object localObject = this._children[1].getValue(paramObject);
    this._children[0].setValue(paramObject, localObject);
    return localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTAssign
 * JD-Core Version:    0.6.0
 */