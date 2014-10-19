package com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

abstract class CompositionFieldNamingPolicy extends RecursiveFieldNamingPolicy
{
  private final RecursiveFieldNamingPolicy[] fieldPolicies;

  public CompositionFieldNamingPolicy(RecursiveFieldNamingPolicy[] paramArrayOfRecursiveFieldNamingPolicy)
  {
    if (paramArrayOfRecursiveFieldNamingPolicy == null)
      throw new NullPointerException("naming policies can not be null.");
    this.fieldPolicies = paramArrayOfRecursiveFieldNamingPolicy;
  }

  protected String translateName(String paramString, Type paramType, Collection<Annotation> paramCollection)
  {
    RecursiveFieldNamingPolicy[] arrayOfRecursiveFieldNamingPolicy = this.fieldPolicies;
    int i = arrayOfRecursiveFieldNamingPolicy.length;
    for (int j = 0; j < i; j++)
      paramString = arrayOfRecursiveFieldNamingPolicy[j].translateName(paramString, paramType, paramCollection);
    return paramString;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.CompositionFieldNamingPolicy
 * JD-Core Version:    0.6.0
 */