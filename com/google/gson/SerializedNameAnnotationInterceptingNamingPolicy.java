package com.google.gson;

import com.google.gson.annotations.SerializedName;

final class SerializedNameAnnotationInterceptingNamingPolicy
  implements FieldNamingStrategy2
{
  private final FieldNamingStrategy2 delegate;

  SerializedNameAnnotationInterceptingNamingPolicy(FieldNamingStrategy2 paramFieldNamingStrategy2)
  {
    this.delegate = paramFieldNamingStrategy2;
  }

  public String translateName(FieldAttributes paramFieldAttributes)
  {
    SerializedName localSerializedName = (SerializedName)paramFieldAttributes.getAnnotation(SerializedName.class);
    if (localSerializedName == null)
      return this.delegate.translateName(paramFieldAttributes);
    return localSerializedName.value();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.SerializedNameAnnotationInterceptingNamingPolicy
 * JD-Core Version:    0.6.0
 */