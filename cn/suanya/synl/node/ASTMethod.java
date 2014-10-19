package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlRuntime;
import java.util.List;

public class ASTMethod extends DataNote
{
  public ASTMethod(String paramString)
  {
    super(paramString);
  }

  private String getMethodName()
  {
    return this.expression;
  }

  protected Object getValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    Object localObject;
    Class localClass;
    label27: List localList1;
    if ((this._children == null) || (this._children.length == 0))
    {
      localObject = new Object[0];
      if (paramObject2 != null)
        break label140;
      localClass = null;
      localList1 = OgnlRuntime.getMethods(localClass, getMethodName(), false);
      if ((localList1 != null) && (!localList1.isEmpty()))
        break label149;
    }
    label140: label149: for (List localList2 = OgnlRuntime.getMethods(localClass, getMethodName(), true); ; localList2 = localList1)
    {
      return OgnlRuntime.callAppropriateMethod(paramObject2, getMethodName(), localList2, localObject);
      if ((this._children[0] instanceof ASTComma))
      {
        localObject = (Object[])(Object[])this._children[0].getValue(paramObject1);
        break;
      }
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this._children[0].getValue(paramObject1);
      localObject = arrayOfObject;
      break;
      localClass = paramObject2.getClass();
      break label27;
    }
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
        String str = (String)localObject + localNode.toString();
        j++;
        localObject = str;
      }
    }
    return (String)(this.expression + "(" + (String)localObject + ")");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTMethod
 * JD-Core Version:    0.6.0
 */