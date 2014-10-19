package com.squareup.okhttp;

public enum ResponseSource
{
  static
  {
    ResponseSource[] arrayOfResponseSource = new ResponseSource[4];
    arrayOfResponseSource[0] = CACHE;
    arrayOfResponseSource[1] = CONDITIONAL_CACHE;
    arrayOfResponseSource[2] = NETWORK;
    arrayOfResponseSource[3] = NONE;
    $VALUES = arrayOfResponseSource;
  }

  public boolean requiresConnection()
  {
    return (this == CONDITIONAL_CACHE) || (this == NETWORK);
  }

  public boolean usesCache()
  {
    return (this == CACHE) || (this == CONDITIONAL_CACHE);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.ResponseSource
 * JD-Core Version:    0.6.0
 */