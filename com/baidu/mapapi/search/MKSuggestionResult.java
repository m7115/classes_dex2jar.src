package com.baidu.mapapi.search;

import java.util.ArrayList;

public class MKSuggestionResult
{
  private int a = 0;
  private ArrayList<MKSuggestionInfo> b;

  void a(ArrayList<MKSuggestionInfo> paramArrayList)
  {
    this.b = paramArrayList;
  }

  public ArrayList<MKSuggestionInfo> getAllSuggestions()
  {
    return this.b;
  }

  public MKSuggestionInfo getSuggestion(int paramInt)
  {
    if ((this.b == null) || (paramInt < 0) || (paramInt > -1 + this.b.size()))
      return null;
    return (MKSuggestionInfo)this.b.get(paramInt);
  }

  public int getSuggestionNum()
  {
    if (this.b != null);
    for (this.a = this.b.size(); ; this.a = 0)
      return this.a;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.search.MKSuggestionResult
 * JD-Core Version:    0.6.0
 */