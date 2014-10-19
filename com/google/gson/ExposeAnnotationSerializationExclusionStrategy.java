package com.google.gson;

import com.google.gson.annotations.Expose;

final class ExposeAnnotationSerializationExclusionStrategy
  implements ExclusionStrategy
{
  public boolean shouldSkipClass(Class<?> paramClass)
  {
    return false;
  }

  public boolean shouldSkipField(FieldAttributes paramFieldAttributes)
  {
    Expose localExpose = (Expose)paramFieldAttributes.getAnnotation(Expose.class);
    if (localExpose == null)
      return true;
    if (!localExpose.serialize());
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ExposeAnnotationSerializationExclusionStrategy
 * JD-Core Version:    0.6.0
 */