package com.google.gson;

final class NullExclusionStrategy
  implements ExclusionStrategy
{
  public boolean shouldSkipClass(Class<?> paramClass)
  {
    return false;
  }

  public boolean shouldSkipField(FieldAttributes paramFieldAttributes)
  {
    return false;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.NullExclusionStrategy
 * JD-Core Version:    0.6.0
 */