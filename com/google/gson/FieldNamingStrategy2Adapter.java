package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;

final class FieldNamingStrategy2Adapter
  implements FieldNamingStrategy2
{
  private final FieldNamingStrategy adaptee;

  FieldNamingStrategy2Adapter(FieldNamingStrategy paramFieldNamingStrategy)
  {
    this.adaptee = ((FieldNamingStrategy).Gson.Preconditions.checkNotNull(paramFieldNamingStrategy));
  }

  public String translateName(FieldAttributes paramFieldAttributes)
  {
    return this.adaptee.translateName(paramFieldAttributes.getFieldObject());
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.FieldNamingStrategy2Adapter
 * JD-Core Version:    0.6.0
 */