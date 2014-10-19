package cn.suanya.synl;

import cn.suanya.synl.node.ASTAdd;
import cn.suanya.synl.node.ASTAnd;
import cn.suanya.synl.node.ASTAssign;
import cn.suanya.synl.node.ASTChain;
import cn.suanya.synl.node.ASTComma;
import cn.suanya.synl.node.ASTDivide;
import cn.suanya.synl.node.ASTEq;
import cn.suanya.synl.node.ASTGreater;
import cn.suanya.synl.node.ASTGreaterEq;
import cn.suanya.synl.node.ASTLess;
import cn.suanya.synl.node.ASTLessEq;
import cn.suanya.synl.node.ASTMultiply;
import cn.suanya.synl.node.ASTNot;
import cn.suanya.synl.node.ASTNotEq;
import cn.suanya.synl.node.ASTOr;
import cn.suanya.synl.node.ASTSubtract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SynlParser
{
  private static OpArray opArray;
  private static List<Operator> opList = new ArrayList();
  private static char quotation_marks;
  private int baseLevel;
  private StringBuilder buf;
  private Node currNode;
  private int index;

  static
  {
    opList.add(new Operator("=", 0, 0, ASTAssign.class));
    opList.add(new Operator("+", 30, 0, ASTAdd.class));
    opList.add(new Operator("-", 30, 0, ASTSubtract.class));
    opList.add(new Operator("*", 40, 0, ASTMultiply.class));
    opList.add(new Operator("/", 40, 0, ASTDivide.class));
    opList.add(new Operator(">", 20, 0, ASTGreater.class));
    opList.add(new Operator("<", 20, 0, ASTLess.class));
    opList.add(new Operator("==", 20, 0, ASTEq.class));
    opList.add(new Operator("!=", 20, 0, ASTNotEq.class));
    opList.add(new Operator(">=", 20, 0, ASTGreaterEq.class));
    opList.add(new Operator("<=", 20, 0, ASTLessEq.class));
    opList.add(new Operator("&&", 15, 0, ASTAnd.class));
    opList.add(new Operator("||", 10, 0, ASTOr.class));
    opList.add(new Operator("!", 25, 0, ASTNot.class));
    opList.add(new Operator(".", 80, 0, ASTChain.class));
    opList.add(new Operator("(", 90, 100, null));
    opList.add(new Operator(")", 0, -100, null));
    opList.add(new Operator("[", 90, 100, null));
    opList.add(new Operator("]", 0, -100, null));
    opList.add(new Operator(",", 0, 0, ASTComma.class));
    opArray = new OpArray();
    Iterator localIterator = opList.iterator();
    while (localIterator.hasNext())
    {
      Operator localOperator = (Operator)localIterator.next();
      String str = localOperator.opFlag;
      OpArray localOpArray = opArray;
      for (int i = 0; i < str.length(); i++)
      {
        int j = str.charAt(i);
        if (localOpArray.opa[j] == null)
          localOpArray.opa[j] = new OpArray();
        localOpArray = localOpArray.opa[j];
      }
      localOpArray.op = localOperator;
    }
    quotation_marks = '\'';
  }

  private Operator findOp(String paramString)
    throws IOException
  {
    this.buf = new StringBuilder();
    int i = 0;
    label72: label77: OpArray localOpArray1;
    OpArray localOpArray2;
    while (true)
    {
      if (this.index >= paramString.length())
        break label208;
      char c = paramString.charAt(this.index);
      if (quotation_marks == c)
        if (i != 0)
          break label72;
      for (i = 1; ; i = 0)
      {
        if (i == 0)
          break label77;
        this.buf.append(c);
        this.index = (1 + this.index);
        break;
      }
      localOpArray1 = opArray.opa[c];
      if (localOpArray1 == null)
      {
        this.buf.append(c);
        this.index = (1 + this.index);
        continue;
      }
      this.index = (1 + this.index);
      if (this.index >= paramString.length())
        break label202;
      int j = paramString.charAt(this.index);
      localOpArray2 = localOpArray1.opa[j];
      if (localOpArray2 != null)
        break;
      if (localOpArray1.op == null)
      {
        this.buf.append(c);
        continue;
      }
      return localOpArray1.op;
    }
    this.index = (1 + this.index);
    return localOpArray2.op;
    label202: return localOpArray1.op;
    label208: return null;
  }

  private Node getRoot(Node paramNode)
  {
    if (paramNode == null);
    do
      return paramNode;
    while (paramNode.GetParent() == null);
    return getRoot(paramNode.GetParent());
  }

  public Node parser(String paramString)
    throws IOException, InstantiationException, IllegalAccessException
  {
    this.index = 0;
    this.baseLevel = 0;
    this.currNode = null;
    for (Operator localOperator = findOp(paramString); localOperator != null; localOperator = findOp(paramString))
    {
      this.currNode = localOperator.createNode(this.currNode, this.buf.toString().trim(), this.baseLevel);
      this.baseLevel += localOperator.floatLevel;
    }
    this.currNode = new Operator(")", 0, -100, null).createNode(this.currNode, this.buf.toString().trim(), this.baseLevel);
    return getRoot(this.currNode);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.SynlParser
 * JD-Core Version:    0.6.0
 */