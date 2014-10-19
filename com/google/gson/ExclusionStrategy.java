package com.google.gson;

public abstract interface ExclusionStrategy
{
  public abstract boolean shouldSkipClass(Class<?> paramClass);

  public abstract boolean shouldSkipField(FieldAttributes paramFieldAttributes);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ExclusionStrategy
 * JD-Core Version:    0.6.0
 */