package com.squareup.okhttp.internal.http;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public final class Headers
{
  private final String[] namesAndValues;

  private Headers(Builder paramBuilder)
  {
    this.namesAndValues = ((String[])paramBuilder.namesAndValues.toArray(new String[paramBuilder.namesAndValues.size()]));
  }

  private static String get(String[] paramArrayOfString, String paramString)
  {
    for (int i = -2 + paramArrayOfString.length; i >= 0; i -= 2)
      if (paramString.equalsIgnoreCase(paramArrayOfString[i]))
        return paramArrayOfString[(i + 1)];
    return null;
  }

  public String get(String paramString)
  {
    return get(this.namesAndValues, paramString);
  }

  public Headers getAll(Set<String> paramSet)
  {
    Builder localBuilder = new Builder();
    for (int i = 0; i < this.namesAndValues.length; i += 2)
    {
      String str = this.namesAndValues[i];
      if (!paramSet.contains(str))
        continue;
      localBuilder.add(str, this.namesAndValues[(i + 1)]);
    }
    return localBuilder.build();
  }

  public String name(int paramInt)
  {
    int i = paramInt * 2;
    if ((i < 0) || (i >= this.namesAndValues.length))
      return null;
    return this.namesAndValues[i];
  }

  public Set<String> names()
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    for (int i = 0; i < size(); i++)
      localTreeSet.add(name(i));
    return Collections.unmodifiableSet(localTreeSet);
  }

  public Builder newBuilder()
  {
    Builder localBuilder = new Builder();
    localBuilder.namesAndValues.addAll(Arrays.asList(this.namesAndValues));
    return localBuilder;
  }

  public int size()
  {
    return this.namesAndValues.length / 2;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < size(); i++)
      localStringBuilder.append(name(i)).append(": ").append(value(i)).append("\n");
    return localStringBuilder.toString();
  }

  public String value(int paramInt)
  {
    int i = 1 + paramInt * 2;
    if ((i < 0) || (i >= this.namesAndValues.length))
      return null;
    return this.namesAndValues[i];
  }

  public List<String> values(String paramString)
  {
    ArrayList localArrayList = null;
    for (int i = 0; i < size(); i++)
    {
      if (!paramString.equalsIgnoreCase(name(i)))
        continue;
      if (localArrayList == null)
        localArrayList = new ArrayList(2);
      localArrayList.add(value(i));
    }
    if (localArrayList != null)
      return Collections.unmodifiableList(localArrayList);
    return Collections.emptyList();
  }

  public static class Builder
  {
    private final List<String> namesAndValues = new ArrayList(20);

    private Builder addLenient(String paramString1, String paramString2)
    {
      this.namesAndValues.add(paramString1);
      this.namesAndValues.add(paramString2.trim());
      return this;
    }

    public Builder add(String paramString1, String paramString2)
    {
      if (paramString1 == null)
        throw new IllegalArgumentException("fieldname == null");
      if (paramString2 == null)
        throw new IllegalArgumentException("value == null");
      if ((paramString1.length() == 0) || (paramString1.indexOf(0) != -1) || (paramString2.indexOf(0) != -1))
        throw new IllegalArgumentException("Unexpected header: " + paramString1 + ": " + paramString2);
      return addLenient(paramString1, paramString2);
    }

    public Builder addLine(String paramString)
    {
      int i = paramString.indexOf(":", 1);
      if (i != -1)
        return addLenient(paramString.substring(0, i), paramString.substring(i + 1));
      if (paramString.startsWith(":"))
        return addLenient("", paramString.substring(1));
      return addLenient("", paramString);
    }

    public Headers build()
    {
      return new Headers(this, null);
    }

    public String get(String paramString)
    {
      for (int i = -2 + this.namesAndValues.size(); i >= 0; i -= 2)
        if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
          return (String)this.namesAndValues.get(i + 1);
      return null;
    }

    public Builder removeAll(String paramString)
    {
      for (int i = 0; i < this.namesAndValues.size(); i += 2)
      {
        if (!paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
          continue;
        this.namesAndValues.remove(i);
        this.namesAndValues.remove(i);
      }
      return this;
    }

    public Builder set(String paramString1, String paramString2)
    {
      removeAll(paramString1);
      add(paramString1, paramString2);
      return this;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.Headers
 * JD-Core Version:    0.6.0
 */