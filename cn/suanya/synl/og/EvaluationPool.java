package cn.suanya.synl.og;

import cn.suanya.synl.node.SimpleNode;
import java.util.List;

public final class EvaluationPool
{
  public EvaluationPool()
  {
    this(0);
  }

  public EvaluationPool(int paramInt)
  {
  }

  public Evaluation create(SimpleNode paramSimpleNode, Object paramObject)
  {
    return create(paramSimpleNode, paramObject, false);
  }

  public Evaluation create(SimpleNode paramSimpleNode, Object paramObject, boolean paramBoolean)
  {
    return new Evaluation(paramSimpleNode, paramObject, paramBoolean);
  }

  public int getCreatedCount()
  {
    return 0;
  }

  public int getRecoveredCount()
  {
    return 0;
  }

  public int getRecycledCount()
  {
    return 0;
  }

  public int getSize()
  {
    return 0;
  }

  public void recycle(Evaluation paramEvaluation)
  {
  }

  public void recycleAll(Evaluation paramEvaluation)
  {
  }

  public void recycleAll(List paramList)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.EvaluationPool
 * JD-Core Version:    0.6.0
 */