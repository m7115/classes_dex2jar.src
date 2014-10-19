package com.google.gson.internal;

public final class $Gson$Preconditions
{
  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    return paramT;
  }

  public static void checkState(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalStateException();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.internal..Gson.Preconditions
 * JD-Core Version:    0.6.0
 */