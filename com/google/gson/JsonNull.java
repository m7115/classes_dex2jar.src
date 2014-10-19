package com.google.gson;

import java.io.IOException;

public final class JsonNull extends JsonElement
{
  private static final JsonNull INSTANCE = new JsonNull();

  static JsonNull createJsonNull()
  {
    return INSTANCE;
  }

  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || ((paramObject instanceof JsonNull));
  }

  public int hashCode()
  {
    return JsonNull.class.hashCode();
  }

  protected void toString(Appendable paramAppendable, Escaper paramEscaper)
    throws IOException
  {
    paramAppendable.append("null");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonNull
 * JD-Core Version:    0.6.0
 */