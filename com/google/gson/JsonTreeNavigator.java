package com.google.gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

final class JsonTreeNavigator
{
  private final boolean visitNulls;
  private final JsonElementVisitor visitor;

  JsonTreeNavigator(JsonElementVisitor paramJsonElementVisitor, boolean paramBoolean)
  {
    this.visitor = paramJsonElementVisitor;
    this.visitNulls = paramBoolean;
  }

  private void visitChild(JsonArray paramJsonArray, JsonElement paramJsonElement, boolean paramBoolean)
    throws IOException
  {
    if (paramJsonElement.isJsonNull())
    {
      this.visitor.visitNullArrayMember(paramJsonArray, paramBoolean);
      navigate(paramJsonElement);
      return;
    }
    if (paramJsonElement.isJsonArray())
    {
      JsonArray localJsonArray = paramJsonElement.getAsJsonArray();
      this.visitor.visitArrayMember(paramJsonArray, localJsonArray, paramBoolean);
      navigate(localJsonArray);
      return;
    }
    if (paramJsonElement.isJsonObject())
    {
      JsonObject localJsonObject = paramJsonElement.getAsJsonObject();
      this.visitor.visitArrayMember(paramJsonArray, localJsonObject, paramBoolean);
      navigate(localJsonObject);
      return;
    }
    this.visitor.visitArrayMember(paramJsonArray, paramJsonElement.getAsJsonPrimitive(), paramBoolean);
  }

  private boolean visitChild(JsonObject paramJsonObject, String paramString, JsonElement paramJsonElement, boolean paramBoolean)
    throws IOException
  {
    if (paramJsonElement.isJsonNull())
      if (this.visitNulls)
      {
        this.visitor.visitNullObjectMember(paramJsonObject, paramString, paramBoolean);
        navigate(paramJsonElement.getAsJsonNull());
      }
    while (true)
    {
      return true;
      return false;
      if (paramJsonElement.isJsonArray())
      {
        JsonArray localJsonArray = paramJsonElement.getAsJsonArray();
        this.visitor.visitObjectMember(paramJsonObject, paramString, localJsonArray, paramBoolean);
        navigate(localJsonArray);
        continue;
      }
      if (paramJsonElement.isJsonObject())
      {
        JsonObject localJsonObject = paramJsonElement.getAsJsonObject();
        this.visitor.visitObjectMember(paramJsonObject, paramString, localJsonObject, paramBoolean);
        navigate(localJsonObject);
        continue;
      }
      this.visitor.visitObjectMember(paramJsonObject, paramString, paramJsonElement.getAsJsonPrimitive(), paramBoolean);
    }
  }

  public void navigate(JsonElement paramJsonElement)
    throws IOException
  {
    if (paramJsonElement.isJsonNull())
    {
      this.visitor.visitNull();
      return;
    }
    JsonArray localJsonArray;
    boolean bool3;
    if (paramJsonElement.isJsonArray())
    {
      localJsonArray = paramJsonElement.getAsJsonArray();
      this.visitor.startArray(localJsonArray);
      Iterator localIterator2 = localJsonArray.iterator();
      bool3 = true;
      if (localIterator2.hasNext())
      {
        visitChild(localJsonArray, (JsonElement)localIterator2.next(), bool3);
        if (!bool3)
          break label238;
      }
    }
    label231: label238: for (boolean bool4 = false; ; bool4 = bool3)
    {
      bool3 = bool4;
      break;
      this.visitor.endArray(localJsonArray);
      return;
      JsonObject localJsonObject;
      boolean bool1;
      if (paramJsonElement.isJsonObject())
      {
        localJsonObject = paramJsonElement.getAsJsonObject();
        this.visitor.startObject(localJsonObject);
        Iterator localIterator1 = localJsonObject.entrySet().iterator();
        bool1 = true;
        if (localIterator1.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator1.next();
          if ((!visitChild(localJsonObject, (String)localEntry.getKey(), (JsonElement)localEntry.getValue(), bool1)) || (!bool1))
            break label231;
        }
      }
      for (boolean bool2 = false; ; bool2 = bool1)
      {
        bool1 = bool2;
        break;
        this.visitor.endObject(localJsonObject);
        return;
        this.visitor.visitPrimitive(paramJsonElement.getAsJsonPrimitive());
        return;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.JsonTreeNavigator
 * JD-Core Version:    0.6.0
 */