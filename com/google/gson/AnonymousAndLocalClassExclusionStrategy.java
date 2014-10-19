package com.google.gson;

final class AnonymousAndLocalClassExclusionStrategy
  implements ExclusionStrategy
{
  private boolean isAnonymousOrLocal(Class<?> paramClass)
  {
    return (!Enum.class.isAssignableFrom(paramClass)) && ((paramClass.isAnonymousClass()) || (paramClass.isLocalClass()));
  }

  public boolean shouldSkipClass(Class<?> paramClass)
  {
    return isAnonymousOrLocal(paramClass);
  }

  public boolean shouldSkipField(FieldAttributes paramFieldAttributes)
  {
    return isAnonymousOrLocal(paramFieldAttributes.getDeclaredClass());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.AnonymousAndLocalClassExclusionStrategy
 * JD-Core Version:    0.6.0
 */