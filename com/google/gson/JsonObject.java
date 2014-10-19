package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class JsonObject extends JsonElement
{
  private final Map<String, JsonElement> members = new LinkedHashMap();

  private JsonElement createJsonElement(Object paramObject)
  {
    if (paramObject == null)
      return JsonNull.createJsonNull();
    return new JsonPrimitive(paramObject);
  }

  public void add(String paramString, JsonElement paramJsonElement)
  {
    if (paramJsonElement == null)
      paramJsonElement = JsonNull.createJsonNull();
    this.members.put(.Gson.Preconditions.checkNotNull(paramString), paramJsonElement);
  }

  public void addProperty(String paramString, Boolean paramBoolean)
  {
    add(paramString, createJsonElement(paramBoolean));
  }

  public void addProperty(String paramString, Character paramCharacter)
  {
    add(paramString, createJsonElement(paramCharacter));
  }

  public void addProperty(String paramString, Number paramNumber)
  {
    add(paramString, createJsonElement(paramNumber));
  }

  public void addProperty(String paramString1, String paramString2)
  {
    add(paramString1, createJsonElement(paramString2));
  }

  public Set<Map.Entry<String, JsonElement>> entrySet()
  {
    return this.members.entrySet();
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof JsonObject)) && (((JsonObject)paramObject).members.equals(this.members)));
  }

  public JsonElement get(String paramString)
  {
    if (this.members.containsKey(paramString))
    {
      Object localObject = (JsonElement)this.members.get(paramString);
      if (localObject == null)
        localObject = JsonNull.createJsonNull();
      return localObject;
    }
    return (JsonElement)null;
  }

  public JsonArray getAsJsonArray(String paramString)
  {
    return (JsonArray)this.members.get(paramString);
  }

  public JsonObject getAsJsonObject(String paramString)
  {
    return (JsonObject)this.members.get(paramString);
  }

  public JsonPrimitive getAsJsonPrimitive(String paramString)
  {
    return (JsonPrimitive)this.members.get(paramString);
  }

  public boolean has(String paramString)
  {
    return this.members.containsKey(paramString);
  }

  public int hashCode()
  {
    return this.members.hashCode();
  }

  public JsonElement remove(String paramString)
  {
    return (JsonElement)this.members.remove(paramString);
  }

  protected void toString(Appendable paramAppendable, Escaper paramEscaper)
    throws IOException
  {
    paramAppendable.append('{');
    Iterator localIterator = this.members.entrySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (i != 0);
      for (int j = 0; ; j = i)
      {
        paramAppendable.append('"');
        paramAppendable.append(paramEscaper.escapeJsonString((CharSequence)localEntry.getKey()));
        paramAppendable.append("\":");
        ((JsonElement)localEntry.getValue()).toString(paramAppendable, paramEscaper);
        i = j;
        break;
        paramAppendable.append(',');
      }
    }
    paramAppendable.append('}');
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonObject
 * JD-Core Version:    0.6.0
 */