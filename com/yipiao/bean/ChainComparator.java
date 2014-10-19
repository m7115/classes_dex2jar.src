package com.yipiao.bean;

import java.util.Comparator;

public class ChainComparator<T>
  implements Comparator<T>
{
  private Comparator<T>[] chain = new Comparator[3];

  public void add(Comparator<T> paramComparator)
  {
    if ((this.chain[0] != null) && (this.chain[0] == paramComparator))
      return;
    this.chain[2] = this.chain[1];
    this.chain[1] = this.chain[0];
    this.chain[0] = paramComparator;
  }

  public int compare(T paramT1, T paramT2)
  {
    int i = 0;
    int j = -1;
    int k = 0;
    Comparator localComparator;
    if (i < this.chain.length)
    {
      localComparator = this.chain[i];
      if (k != 0)
        if (k <= 0)
          break label102;
    }
    label102: for (int n = 1; ; n = k)
    {
      if (n < 0)
        n = j;
      return n;
      if (localComparator != null)
        k = localComparator.compare(paramT1, paramT2);
      i++;
      break;
      if (k > 0);
      for (int m = 1; ; m = k)
      {
        if (m < 0);
        while (true)
        {
          return j;
          j = m;
        }
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.ChainComparator
 * JD-Core Version:    0.6.0
 */