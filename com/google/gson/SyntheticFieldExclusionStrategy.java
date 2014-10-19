package com.google.gson;

final class SyntheticFieldExclusionStrategy
  implements ExclusionStrategy
{
  private final boolean skipSyntheticFields;

  SyntheticFieldExclusionStrategy(boolean paramBoolean)
  {
    this.skipSyntheticFields = paramBoolean;
  }

  public boolean shouldSkipClass(Class<?> paramClass)
  {
    return false;
  }

  public boolean shouldSkipField(FieldAttributes paramFieldAttributes)
  {
    return (this.skipSyntheticFields) && (paramFieldAttributes.isSynthetic());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.SyntheticFieldExclusionStrategy
 * JD-Core Version:    0.6.0
 */