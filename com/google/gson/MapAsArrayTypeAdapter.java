package com.google.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class MapAsArrayTypeAdapter extends BaseMapTypeAdapter
  implements JsonDeserializer<Map<?, ?>>, JsonSerializer<Map<?, ?>>
{
  private void checkSize(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2)
  {
    if (paramInt1 != paramInt2)
      throw new JsonSyntaxException("Input size " + paramInt1 + " != output size " + paramInt2 + " for input " + paramObject1 + " and output " + paramObject2);
  }

  private Type[] typeToTypeArguments(Type paramType)
  {
    Type[] arrayOfType;
    if ((paramType instanceof ParameterizedType))
    {
      arrayOfType = ((ParameterizedType)paramType).getActualTypeArguments();
      if (arrayOfType.length != 2)
        throw new IllegalArgumentException("MapAsArrayTypeAdapter cannot handle " + paramType);
    }
    else
    {
      arrayOfType = new Type[] { Object.class, Object.class };
    }
    return arrayOfType;
  }

  public Map<?, ?> deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    throws JsonParseException
  {
    Map localMap = constructMapType(paramType, paramJsonDeserializationContext);
    Type[] arrayOfType = typeToTypeArguments(paramType);
    if (paramJsonElement.isJsonArray())
    {
      JsonArray localJsonArray1 = paramJsonElement.getAsJsonArray();
      for (int i = 0; i < localJsonArray1.size(); i++)
      {
        JsonArray localJsonArray2 = localJsonArray1.get(i).getAsJsonArray();
        localMap.put(paramJsonDeserializationContext.deserialize(localJsonArray2.get(0), arrayOfType[0]), paramJsonDeserializationContext.deserialize(localJsonArray2.get(1), arrayOfType[1]));
      }
      checkSize(localJsonArray1, localJsonArray1.size(), localMap, localMap.size());
      return localMap;
    }
    JsonObject localJsonObject = paramJsonElement.getAsJsonObject();
    Iterator localIterator = localJsonObject.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localMap.put(paramJsonDeserializationContext.deserialize(new JsonPrimitive((String)localEntry.getKey()), arrayOfType[0]), paramJsonDeserializationContext.deserialize((JsonElement)localEntry.getValue(), arrayOfType[1]));
    }
    checkSize(localJsonObject, localJsonObject.entrySet().size(), localMap, localMap.size());
    return localMap;
  }

  public JsonElement serialize(Map<?, ?> paramMap, Type paramType, JsonSerializationContext paramJsonSerializationContext)
  {
    int i = 0;
    Type[] arrayOfType = typeToTypeArguments(paramType);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramMap.entrySet().iterator();
    int j = 0;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      JsonElement localJsonElement = serialize(paramJsonSerializationContext, localEntry.getKey(), arrayOfType[0]);
      if ((localJsonElement.isJsonObject()) || (localJsonElement.isJsonArray()));
      for (int k = 1; ; k = 0)
      {
        j |= k;
        localArrayList.add(localJsonElement);
        localArrayList.add(serialize(paramJsonSerializationContext, localEntry.getValue(), arrayOfType[1]));
        break;
      }
    }
    if (j != 0)
    {
      JsonArray localJsonArray1 = new JsonArray();
      while (i < localArrayList.size())
      {
        JsonArray localJsonArray2 = new JsonArray();
        localJsonArray2.add((JsonElement)localArrayList.get(i));
        localJsonArray2.add((JsonElement)localArrayList.get(i + 1));
        localJsonArray1.add(localJsonArray2);
        i += 2;
      }
      return localJsonArray1;
    }
    JsonObject localJsonObject = new JsonObject();
    while (i < localArrayList.size())
    {
      localJsonObject.add(((JsonElement)localArrayList.get(i)).getAsString(), (JsonElement)localArrayList.get(i + 1));
      i += 2;
    }
    checkSize(paramMap, paramMap.size(), localJsonObject, localJsonObject.entrySet().size());
    return localJsonObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.MapAsArrayTypeAdapter
 * JD-Core Version:    0.6.0
 */