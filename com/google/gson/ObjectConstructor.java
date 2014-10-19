package com.google.gson;

import java.lang.reflect.Type;

abstract interface ObjectConstructor
{
  public abstract <T> T construct(Type paramType);

  public abstract Object constructArray(Type paramType, int paramInt);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ObjectConstructor
 * JD-Core Version:    0.6.0
 */