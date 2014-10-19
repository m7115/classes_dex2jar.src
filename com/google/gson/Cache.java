package com.google.gson;

abstract interface Cache<K, V>
{
  public abstract void addElement(K paramK, V paramV);

  public abstract V getElement(K paramK);

  public abstract V removeElement(K paramK);
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.Cache
 * JD-Core Version:    0.6.0
 */