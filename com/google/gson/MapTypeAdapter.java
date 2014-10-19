package com.google.gson;

import com.google.gson.internal..Gson.Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class MapTypeAdapter extends BaseMapTypeAdapter
{
  public Map deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    throws JsonParseException
  {
    Map localMap = constructMapType(paramType, paramJsonDeserializationContext);
    Type[] arrayOfType = .Gson.Types.getMapKeyAndValueTypes(paramType, .Gson.Types.getRawType(paramType));
    Iterator localIterator = paramJsonElement.getAsJsonObject().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localMap.put(paramJsonDeserializationContext.deserialize(new JsonPrimitive((String)localEntry.getKey()), arrayOfType[0]), paramJsonDeserializationContext.deserialize((JsonElement)localEntry.getValue(), arrayOfType[1]));
    }
    return localMap;
  }

  public JsonElement serialize(Map paramMap, Type paramType, JsonSerializationContext paramJsonSerializationContext)
  {
    JsonObject localJsonObject = new JsonObject();
    if ((paramType instanceof ParameterizedType));
    for (Type localType = .Gson.Types.getMapKeyAndValueTypes(paramType, .Gson.Types.getRawType(paramType))[1]; ; localType = null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object localObject1 = localEntry.getValue();
        Object localObject3;
        if (localObject1 == null)
        {
          localObject3 = JsonNull.createJsonNull();
          localJsonObject.add(String.valueOf(localEntry.getKey()), (JsonElement)localObject3);
          continue;
        }
        if (localType == null);
        for (Object localObject2 = localObject1.getClass(); ; localObject2 = localType)
        {
          localObject3 = serialize(paramJsonSerializationContext, localObject1, (Type)localObject2);
          break;
        }
      }
      return localJsonObject;
    }
  }

  public String toString()
  {
    return MapTypeAdapter.class.getSimpleName();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.MapTypeAdapter
 * JD-Core Version:    0.6.0
 */