package com.google.gson;

public final class JsonSyntaxException extends JsonParseException
{
  private static final long serialVersionUID = 1L;

  public JsonSyntaxException(String paramString)
  {
    super(paramString);
  }

  public JsonSyntaxException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }

  public JsonSyntaxException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonSyntaxException
 * JD-Core Version:    0.6.0
 */