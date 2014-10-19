package com.google.gson;

final class InnerClassExclusionStrategy
  implements ExclusionStrategy
{
  private boolean isInnerClass(Class<?> paramClass)
  {
    return (paramClass.isMemberClass()) && (!isStatic(paramClass));
  }

  private boolean isStatic(Class<?> paramClass)
  {
    return (0x8 & paramClass.getModifiers()) != 0;
  }

  public boolean shouldSkipClass(Class<?> paramClass)
  {
    return isInnerClass(paramClass);
  }

  public boolean shouldSkipField(FieldAttributes paramFieldAttributes)
  {
    return isInnerClass(paramFieldAttributes.getDeclaredClass());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.InnerClassExclusionStrategy
 * JD-Core Version:    0.6.0
 */