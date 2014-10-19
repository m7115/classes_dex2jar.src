package com.yipiao.service;

import java.io.Serializable;

public abstract interface SYPersistent
{
  public abstract Serializable readObject(String paramString);

  public abstract String readString(String paramString);

  public abstract void writeObject(String paramString, Serializable paramSerializable);

  public abstract void writeString(String paramString1, String paramString2);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.service.SYPersistent
 * JD-Core Version:    0.6.0
 */