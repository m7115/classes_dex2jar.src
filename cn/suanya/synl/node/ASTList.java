package cn.suanya.synl.node;

import cn.suanya.synl.Node;
import cn.suanya.synl.OgnlOps;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class ASTList extends ASTProperty
{
  public ASTList(String paramString)
  {
    super(paramString);
  }

  private int getIndex(Object paramObject)
    throws Exception
  {
    return Integer.valueOf(OgnlOps.getIntValue(this._children[0].getValue(paramObject))).intValue();
  }

  protected Object getValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    Object localObject = super.getValueBody(paramObject1, paramObject2);
    if (localObject == null)
    {
      localObject = new ArrayList();
      super.setValueBody(paramObject2, localObject);
    }
    if (localObject.getClass().isArray())
      return Array.get(localObject, getIndex(paramObject1));
    if ((localObject instanceof List))
    {
      if (getIndex(paramObject1) >= ((List)localObject).size())
        return null;
      return ((List)localObject).get(getIndex(paramObject1));
    }
    if ((localObject instanceof JSONArray))
      return ((JSONArray)localObject).opt(getIndex(paramObject1));
    return null;
  }

  protected void setValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    Object localObject = super.getValueBody(paramObject1, paramObject1);
    if (localObject == null)
    {
      localObject = new ArrayList();
      super.setValueBody(paramObject1, localObject);
    }
    if (localObject.getClass().isArray())
      Array.set(localObject, getIndex(paramObject1), paramObject2);
    do
      return;
    while (!(localObject instanceof List));
    ((List)localObject).add(paramObject2);
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
    return (String)(this.expression + "[" + (String)localObject + "]");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTList
 * JD-Core Version:    0.6.0
 */