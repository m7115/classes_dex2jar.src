package com.google.gson;

import java.lang.reflect.Type;
import java.util.Map;

abstract class BaseMapTypeAdapter
  implements JsonDeserializer<Map<?, ?>>, JsonSerializer<Map<?, ?>>
{
  protected static final Map<Object, Object> constructMapType(Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
  {
    return (Map)((JsonDeserializationContextDefault)paramJsonDeserializationContext).getObjectConstructor().construct(paramType);
  }

  protected static final JsonElement serialize(JsonSerializationContext paramJsonSerializationContext, Object paramObject, Type paramType)
  {
    return ((JsonSerializationContextDefault)paramJsonSerializationContext).serialize(paramObject, paramType, false);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.BaseMapTypeAdapter
 * JD-Core Version:    0.6.0
 */