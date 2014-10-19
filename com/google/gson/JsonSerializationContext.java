package com.google.gson;

import java.lang.reflect.Type;

public abstract interface JsonSerializationContext
{
  public abstract JsonElement serialize(Object paramObject);

  public abstract JsonElement serialize(Object paramObject, Type paramType);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonSerializationContext
 * JD-Core Version:    0.6.0
 */