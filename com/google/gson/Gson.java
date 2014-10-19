package com.google.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public final class Gson
{
  static final AnonymousAndLocalClassExclusionStrategy DEFAULT_ANON_LOCAL_CLASS_EXCLUSION_STRATEGY = new AnonymousAndLocalClassExclusionStrategy();
  private static final ExclusionStrategy DEFAULT_EXCLUSION_STRATEGY;
  static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
  static final ModifierBasedExclusionStrategy DEFAULT_MODIFIER_BASED_EXCLUSION_STRATEGY;
  static final FieldNamingStrategy2 DEFAULT_NAMING_POLICY;
  static final SyntheticFieldExclusionStrategy DEFAULT_SYNTHETIC_FIELD_EXCLUSION_STRATEGY = new SyntheticFieldExclusionStrategy(true);
  private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
  private final ExclusionStrategy deserializationExclusionStrategy;
  private final ParameterizedTypeHandlerMap<JsonDeserializer<?>> deserializers;
  private final FieldNamingStrategy2 fieldNamingPolicy;
  private final boolean generateNonExecutableJson;
  private final boolean htmlSafe;
  private final MappedObjectConstructor objectConstructor;
  private final boolean prettyPrinting;
  private final ExclusionStrategy serializationExclusionStrategy;
  private final boolean serializeNulls;
  private final ParameterizedTypeHandlerMap<JsonSerializer<?>> serializers;

  static
  {
    DEFAULT_MODIFIER_BASED_EXCLUSION_STRATEGY = new ModifierBasedExclusionStrategy(new int[] { 128, 8 });
    DEFAULT_NAMING_POLICY = new SerializedNameAnnotationInterceptingNamingPolicy(new JavaFieldNamingPolicy());
    DEFAULT_EXCLUSION_STRATEGY = createExclusionStrategy();
  }

  public Gson()
  {
    this(DEFAULT_EXCLUSION_STRATEGY, DEFAULT_EXCLUSION_STRATEGY, DEFAULT_NAMING_POLICY, new MappedObjectConstructor(DefaultTypeAdapters.getDefaultInstanceCreators()), false, DefaultTypeAdapters.getAllDefaultSerializers(), DefaultTypeAdapters.getAllDefaultDeserializers(), false, true, false);
  }

  Gson(ExclusionStrategy paramExclusionStrategy1, ExclusionStrategy paramExclusionStrategy2, FieldNamingStrategy2 paramFieldNamingStrategy2, MappedObjectConstructor paramMappedObjectConstructor, boolean paramBoolean1, ParameterizedTypeHandlerMap<JsonSerializer<?>> paramParameterizedTypeHandlerMap, ParameterizedTypeHandlerMap<JsonDeserializer<?>> paramParameterizedTypeHandlerMap1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.deserializationExclusionStrategy = paramExclusionStrategy1;
    this.serializationExclusionStrategy = paramExclusionStrategy2;
    this.fieldNamingPolicy = paramFieldNamingStrategy2;
    this.objectConstructor = paramMappedObjectConstructor;
    this.serializeNulls = paramBoolean1;
    this.serializers = paramParameterizedTypeHandlerMap;
    this.deserializers = paramParameterizedTypeHandlerMap1;
    this.generateNonExecutableJson = paramBoolean2;
    this.htmlSafe = paramBoolean3;
    this.prettyPrinting = paramBoolean4;
  }

  private static void assertFullConsumption(Object paramObject, JsonReader paramJsonReader)
  {
    if (paramObject != null)
      try
      {
        if (paramJsonReader.peek() != JsonToken.END_DOCUMENT)
          throw new JsonIOException("JSON document was not fully consumed.");
      }
      catch (MalformedJsonException localMalformedJsonException)
      {
        throw new JsonSyntaxException(localMalformedJsonException);
      }
      catch (IOException localIOException)
      {
        throw new JsonIOException(localIOException);
      }
  }

  private static ExclusionStrategy createExclusionStrategy()
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(DEFAULT_ANON_LOCAL_CLASS_EXCLUSION_STRATEGY);
    localLinkedList.add(DEFAULT_SYNTHETIC_FIELD_EXCLUSION_STRATEGY);
    localLinkedList.add(DEFAULT_MODIFIER_BASED_EXCLUSION_STRATEGY);
    return new DisjunctionExclusionStrategy(localLinkedList);
  }

  public <T> T fromJson(JsonElement paramJsonElement, Class<T> paramClass)
    throws JsonSyntaxException
  {
    Object localObject = fromJson(paramJsonElement, paramClass);
    return Primitives.wrap(paramClass).cast(localObject);
  }

  public <T> T fromJson(JsonElement paramJsonElement, Type paramType)
    throws JsonSyntaxException
  {
    if (paramJsonElement == null)
      return null;
    return new JsonDeserializationContextDefault(new ObjectNavigator(this.deserializationExclusionStrategy), this.fieldNamingPolicy, this.deserializers, this.objectConstructor).deserialize(paramJsonElement, paramType);
  }

  public <T> T fromJson(JsonReader paramJsonReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    boolean bool = paramJsonReader.isLenient();
    paramJsonReader.setLenient(true);
    try
    {
      Object localObject2 = fromJson(Streams.parse(paramJsonReader), paramType);
      return localObject2;
    }
    finally
    {
      paramJsonReader.setLenient(bool);
    }
    throw localObject1;
  }

  public <T> T fromJson(Reader paramReader, Class<T> paramClass)
    throws JsonSyntaxException, JsonIOException
  {
    JsonReader localJsonReader = new JsonReader(paramReader);
    Object localObject = fromJson(localJsonReader, paramClass);
    assertFullConsumption(localObject, localJsonReader);
    return Primitives.wrap(paramClass).cast(localObject);
  }

  public <T> T fromJson(Reader paramReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    JsonReader localJsonReader = new JsonReader(paramReader);
    Object localObject = fromJson(localJsonReader, paramType);
    assertFullConsumption(localObject, localJsonReader);
    return localObject;
  }

  public <T> T fromJson(String paramString, Class<T> paramClass)
    throws JsonSyntaxException
  {
    Object localObject = fromJson(paramString, paramClass);
    return Primitives.wrap(paramClass).cast(localObject);
  }

  public <T> T fromJson(String paramString, Type paramType)
    throws JsonSyntaxException
  {
    if (paramString == null)
      return null;
    return fromJson(new StringReader(paramString), paramType);
  }

  public String toJson(JsonElement paramJsonElement)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramJsonElement, localStringWriter);
    return localStringWriter.toString();
  }

  public String toJson(Object paramObject)
  {
    if (paramObject == null)
      return toJson(JsonNull.createJsonNull());
    return toJson(paramObject, paramObject.getClass());
  }

  public String toJson(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(toJsonTree(paramObject, paramType), localStringWriter);
    return localStringWriter.toString();
  }

  public void toJson(JsonElement paramJsonElement, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    boolean bool1 = paramJsonWriter.isLenient();
    paramJsonWriter.setLenient(true);
    boolean bool2 = paramJsonWriter.isHtmlSafe();
    paramJsonWriter.setHtmlSafe(this.htmlSafe);
    try
    {
      Streams.write(paramJsonElement, this.serializeNulls, paramJsonWriter);
      return;
    }
    catch (IOException localIOException)
    {
      throw new JsonIOException(localIOException);
    }
    finally
    {
      paramJsonWriter.setLenient(bool1);
      paramJsonWriter.setHtmlSafe(bool2);
    }
    throw localObject;
  }

  public void toJson(JsonElement paramJsonElement, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      if (this.generateNonExecutableJson)
        paramAppendable.append(")]}'\n");
      JsonWriter localJsonWriter = new JsonWriter(Streams.writerForAppendable(paramAppendable));
      if (this.prettyPrinting)
        localJsonWriter.setIndent("  ");
      toJson(paramJsonElement, localJsonWriter);
      return;
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException(localIOException);
  }

  public void toJson(Object paramObject, Appendable paramAppendable)
    throws JsonIOException
  {
    if (paramObject != null)
    {
      toJson(paramObject, paramObject.getClass(), paramAppendable);
      return;
    }
    toJson(JsonNull.createJsonNull(), paramAppendable);
  }

  public void toJson(Object paramObject, Type paramType, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    toJson(toJsonTree(paramObject, paramType), paramJsonWriter);
  }

  public void toJson(Object paramObject, Type paramType, Appendable paramAppendable)
    throws JsonIOException
  {
    toJson(toJsonTree(paramObject, paramType), paramAppendable);
  }

  public JsonElement toJsonTree(Object paramObject)
  {
    if (paramObject == null)
      return JsonNull.createJsonNull();
    return toJsonTree(paramObject, paramObject.getClass());
  }

  public JsonElement toJsonTree(Object paramObject, Type paramType)
  {
    return new JsonSerializationContextDefault(new ObjectNavigator(this.serializationExclusionStrategy), this.fieldNamingPolicy, this.serializeNulls, this.serializers).serialize(paramObject, paramType);
  }

  public String toString()
  {
    return "{" + "serializeNulls:" + this.serializeNulls + ",serializers:" + this.serializers + ",deserializers:" + this.deserializers + ",instanceCreators:" + this.objectConstructor + "}";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.Gson
 * JD-Core Version:    0.6.0
 */