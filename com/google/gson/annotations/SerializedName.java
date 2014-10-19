package com.google.gson.annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface SerializedName
{
  public abstract String value();
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.annotations.SerializedName
 * JD-Core Version:    0.6.0
 */