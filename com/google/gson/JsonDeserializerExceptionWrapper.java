package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.lang.reflect.Type;

final class JsonDeserializerExceptionWrapper<T>
  implements JsonDeserializer<T>
{
  private final JsonDeserializer<T> delegate;

  JsonDeserializerExceptionWrapper(JsonDeserializer<T> paramJsonDeserializer)
  {
    this.delegate = ((JsonDeserializer).Gson.Preconditions.checkNotNull(paramJsonDeserializer));
  }

  public T deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    throws JsonParseException
  {
    try
    {
      Object localObject = this.delegate.deserialize(paramJsonElement, paramType, paramJsonDeserializationContext);
      return localObject;
    }
    catch (JsonParseException localJsonParseException)
    {
      throw localJsonParseException;
    }
    catch (Exception localException)
    {
    }
    throw new JsonParseException("The JsonDeserializer " + this.delegate + " failed to deserialize json object " + paramJsonElement + " given the type " + paramType, localException);
  }

  public String toString()
  {
    return this.delegate.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonDeserializerExceptionWrapper
 * JD-Core Version:    0.6.0
 */