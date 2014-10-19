package com.google.gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

final class UpperCaseNamingPolicy extends RecursiveFieldNamingPolicy
{
  protected String translateName(String paramString, Type paramType, Collection<Annotation> paramCollection)
  {
    return paramString.toUpperCase();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.UpperCaseNamingPolicy
 * JD-Core Version:    0.6.0
 */