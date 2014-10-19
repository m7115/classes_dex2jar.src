package com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

abstract class RecursiveFieldNamingPolicy
  implements FieldNamingStrategy2
{
  public final String translateName(FieldAttributes paramFieldAttributes)
  {
    return translateName(paramFieldAttributes.getName(), paramFieldAttributes.getDeclaredType(), paramFieldAttributes.getAnnotations());
  }

  protected abstract String translateName(String paramString, Type paramType, Collection<Annotation> paramCollection);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.RecursiveFieldNamingPolicy
 * JD-Core Version:    0.6.0
 */