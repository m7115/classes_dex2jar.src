package cn.suanya.synl;

import cn.suanya.synl.node.ASTConst;
import cn.suanya.synl.node.ASTList;
import cn.suanya.synl.node.ASTMethod;
import cn.suanya.synl.node.ASTProperty;

class Operator
{
  int defLevel;
  int floatLevel;
  String opFlag;
  Class<? extends Node> topNode;

  public Operator(String paramString, int paramInt1, int paramInt2, Class<? extends Node> paramClass)
  {
    this.opFlag = paramString;
    this.defLevel = paramInt1;
    this.floatLevel = paramInt2;
    this.topNode = paramClass;
  }

  private void relative(Node paramNode1, Node paramNode2)
  {
    Node localNode = paramNode2.GetParent();
    if (localNode != null)
      localNode.delChild(paramNode2);
    paramNode1.AddChild(paramNode2);
    paramNode2.SetParent(paramNode1);
  }

  protected Node create(Node paramNode, int paramInt)
    throws InstantiationException, IllegalAccessException
  {
    Node localNode = (Node)this.topNode.newInstance();
    localNode.setLevel(paramInt);
    localNode.SetParent(paramNode);
    if (paramNode != null)
      paramNode.AddChild(localNode);
    return localNode;
  }

  public Node createChild(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.trim().length() <= 0))
      return null;
    int i = paramString.charAt(0);
    Object localObject = null;
    if (i == 39)
    {
      int j = paramString.charAt(-1 + paramString.length());
      localObject = null;
      if (j == 39)
        localObject = new ASTConst(paramString.substring(1, -1 + paramString.length()));
    }
    if ((paramString.charAt(0) >= '0') && (paramString.charAt(0) <= '9'));
    try
    {
      localObject = new ASTConst(Integer.valueOf(Integer.parseInt(paramString)));
      if (this.opFlag.equals("("))
        localObject = new ASTMethod(paramString);
      if (this.opFlag.equals("["))
        localObject = new ASTList(paramString);
      if (localObject == null)
        localObject = new ASTProperty(paramString);
      ((Node)localObject).setLevel(paramInt);
      return localObject;
    }
    catch (Exception localException1)
    {
      while (true)
        try
        {
          localObject = new ASTConst(Double.valueOf(Double.parseDouble(paramString)));
        }
        catch (Exception localException2)
        {
          localObject = new ASTConst(paramString);
        }
    }
  }

  public Node createNode(Node paramNode, String paramString, int paramInt)
    throws InstantiationException, IllegalAccessException
  {
    int i = paramInt + this.defLevel;
    Node localNode1 = createChild(paramString, i);
    Class localClass = this.topNode;
    Node localNode2 = null;
    if (localClass != null)
      localNode2 = friendNode(null, paramNode, i);
    if (localNode1 != null)
    {
      if ((paramNode != null) && (localNode2 != null))
      {
        if (paramNode.getLevel() < localNode2.getLevel())
          break label128;
        relative(paramNode, localNode1);
      }
      if ((paramNode != null) && (localNode2 == null))
        relative(paramNode, localNode1);
      if ((paramNode == null) && (localNode2 != null))
        relative(localNode2, localNode1);
    }
    if ((this.floatLevel > 0) && (localNode1 != null))
      paramNode = localNode1;
    label128: 
    do
    {
      return paramNode;
      relative(localNode2, localNode1);
      break;
      if (localNode2 != null)
        return localNode2;
    }
    while (paramNode != null);
    return localNode1;
  }

  protected Node friendNode(Node paramNode1, Node paramNode2, int paramInt)
    throws InstantiationException, IllegalAccessException
  {
    if (paramNode2 == null)
    {
      paramNode2 = create(paramNode2, paramInt);
      if (paramNode1 != null)
        relative(paramNode2, paramNode1);
    }
    do
      while (true)
      {
        return paramNode2;
        if (paramNode2.getLevel() >= paramInt)
          break;
        paramNode2 = create(paramNode2, paramInt);
        if (paramNode1 == null)
          continue;
        relative(paramNode2, paramNode1);
        return paramNode2;
      }
    while ((paramNode2.getLevel() == paramInt) && (this.topNode == paramNode2.getClass()));
    return friendNode(paramNode2, paramNode2.GetParent(), paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.Operator
 * JD-Core Version:    0.6.0
 */