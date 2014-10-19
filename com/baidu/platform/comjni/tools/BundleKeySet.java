package com.baidu.platform.comjni.tools;

import android.os.Bundle;
import java.util.Iterator;
import java.util.Set;

public class BundleKeySet
{
  public String[] getBundleKeys(Bundle paramBundle)
  {
    if (paramBundle == null);
    do
      return null;
    while (paramBundle.isEmpty());
    String[] arrayOfString = new String[paramBundle.size()];
    Iterator localIterator = paramBundle.keySet().iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      arrayOfString[i] = ((String)localIterator.next()).toString();
    return arrayOfString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.platform.comjni.tools.BundleKeySet
 * JD-Core Version:    0.6.0
 */