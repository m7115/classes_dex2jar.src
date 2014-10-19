package cn.suanya.synl.og;

import cn.suanya.synl.node.SimpleNode;

public class Evaluation
{
  private Throwable exception;
  private Evaluation firstChild;
  private Evaluation lastChild;
  private Evaluation next;
  private SimpleNode node;
  private Evaluation parent;
  private Evaluation previous;
  private Object result;
  private boolean setOperation;
  private Object source;

  public Evaluation(SimpleNode paramSimpleNode, Object paramObject)
  {
    this.node = paramSimpleNode;
    this.source = paramObject;
  }

  public Evaluation(SimpleNode paramSimpleNode, Object paramObject, boolean paramBoolean)
  {
    this(paramSimpleNode, paramObject);
    this.setOperation = paramBoolean;
  }

  public void addChild(Evaluation paramEvaluation)
  {
    if (this.firstChild == null)
    {
      this.lastChild = paramEvaluation;
      this.firstChild = paramEvaluation;
    }
    while (true)
    {
      paramEvaluation.parent = this;
      return;
      if (this.firstChild == this.lastChild)
      {
        this.firstChild.next = paramEvaluation;
        this.lastChild = paramEvaluation;
        this.lastChild.previous = this.firstChild;
        continue;
      }
      paramEvaluation.previous = this.lastChild;
      this.lastChild.next = paramEvaluation;
      this.lastChild = paramEvaluation;
    }
  }

  public Throwable getException()
  {
    return this.exception;
  }

  public Evaluation getFirstChild()
  {
    return this.firstChild;
  }

  public Evaluation getFirstDescendant()
  {
    if (this.firstChild != null)
      this = this.firstChild.getFirstDescendant();
    return this;
  }

  public Evaluation getLastChild()
  {
    return this.lastChild;
  }

  public Evaluation getLastDescendant()
  {
    if (this.lastChild != null)
      this = this.lastChild.getLastDescendant();
    return this;
  }

  public Evaluation getNext()
  {
    return this.next;
  }

  public SimpleNode getNode()
  {
    return this.node;
  }

  public Evaluation getParent()
  {
    return this.parent;
  }

  public Evaluation getPrevious()
  {
    return this.previous;
  }

  public Object getResult()
  {
    return this.result;
  }

  public Object getSource()
  {
    return this.source;
  }

  public void init(SimpleNode paramSimpleNode, Object paramObject, boolean paramBoolean)
  {
    this.node = paramSimpleNode;
    this.source = paramObject;
    this.setOperation = paramBoolean;
    this.result = null;
    this.exception = null;
    this.parent = null;
    this.next = null;
    this.previous = null;
    this.firstChild = null;
    this.lastChild = null;
  }

  public boolean isSetOperation()
  {
    return this.setOperation;
  }

  public void reset()
  {
    init(null, null, false);
  }

  public void setException(Throwable paramThrowable)
  {
    this.exception = paramThrowable;
  }

  public void setNode(SimpleNode paramSimpleNode)
  {
    this.node = paramSimpleNode;
  }

  public void setResult(Object paramObject)
  {
    this.result = paramObject;
  }

  public void setSetOperation(boolean paramBoolean)
  {
    this.setOperation = paramBoolean;
  }

  public void setSource(Object paramObject)
  {
    this.source = paramObject;
  }

  public String toString()
  {
    return toString(false, "");
  }

  public String toString(boolean paramBoolean, String paramString)
  {
    return toString(paramBoolean, true, paramString);
  }

  public String toString(boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    Object localObject;
    if (paramBoolean1)
    {
      localObject = paramString + "<" + this.node.getClass().getName() + " " + System.identityHashCode(this) + ">";
      if (paramBoolean2)
      {
        Evaluation localEvaluation = this.firstChild;
        String str4;
        for (localObject = (String)localObject + "\n"; localEvaluation != null; localObject = str4)
        {
          str4 = (String)localObject + localEvaluation.toString(paramBoolean1, new StringBuilder().append(paramString).append("  ").toString());
          localEvaluation = localEvaluation.next;
        }
      }
    }
    else
    {
      String str1;
      label170: String str2;
      label189: StringBuilder localStringBuilder;
      if (this.source != null)
      {
        str1 = this.source.getClass().getName();
        if (this.result == null)
          break label295;
        str2 = this.result.getClass().getName();
        localStringBuilder = new StringBuilder().append(paramString).append("<").append(this.node.getClass().getName()).append(": [");
        if (!this.setOperation)
          break label302;
      }
      label295: label302: for (String str3 = "set"; ; str3 = "get")
      {
        localObject = str3 + "] source = " + str1 + ", result = " + this.result + " [" + str2 + "]>";
        break;
        str1 = "null";
        break label170;
        str2 = "null";
        break label189;
      }
    }
    return (String)localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.Evaluation
 * JD-Core Version:    0.6.0
 */