package cn.suanya.synl.node;

import cn.suanya.synl.Node;

public class SimpleNode
  implements Node
{
  protected Node[] _children;
  protected Node _parent;
  protected int level;

  public void AddChild(Node paramNode)
  {
    if (this._children == null);
    Node[] arrayOfNode;
    for (this._children = new Node[1]; ; this._children = arrayOfNode)
    {
      this._children[(-1 + this._children.length)] = paramNode;
      return;
      arrayOfNode = new Node[1 + this._children.length];
      System.arraycopy(this._children, 0, arrayOfNode, 0, this._children.length);
    }
  }

  public Node GetParent()
  {
    return this._parent;
  }

  public void SetParent(Node paramNode)
  {
    this._parent = paramNode;
  }

  public void delChild(Node paramNode)
  {
    int i = 0;
    if (i < this._children.length)
      if (this._children[i] != paramNode);
    while (true)
    {
      if (i != -1)
      {
        Node[] arrayOfNode = new Node[-1 + this._children.length];
        if (i > 0)
          System.arraycopy(this._children, 0, arrayOfNode, 0, i);
        if (this._children.length - i > 1)
          System.arraycopy(this._children, i + 1, arrayOfNode, i, -1 + (this._children.length - i));
        this._children = arrayOfNode;
      }
      return;
      i++;
      break;
      i = -1;
    }
  }

  public int getLevel()
  {
    return this.level;
  }

  public Object getValue(Object paramObject)
    throws Exception
  {
    return getValueBody(paramObject, paramObject);
  }

  public Object getValue(Object paramObject1, Object paramObject2)
    throws Exception
  {
    return getValueBody(paramObject1, paramObject2);
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    return null;
  }

  protected Object getValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    return getValueBody(paramObject2);
  }

  public void setLevel(int paramInt)
  {
    this.level = paramInt;
  }

  public void setValue(Object paramObject1, Object paramObject2)
    throws Exception
  {
    setValueBody(paramObject1, paramObject2);
  }

  protected void setValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.SimpleNode
 * JD-Core Version:    0.6.0
 */