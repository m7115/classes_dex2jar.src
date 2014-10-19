package com.google.gson;

import com.google.gson.internal..Gson.Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.UUID;

final class DefaultTypeAdapters
{
  private static final BigDecimalTypeAdapter BIG_DECIMAL_TYPE_ADAPTER;
  private static final BigIntegerTypeAdapter BIG_INTEGER_TYPE_ADAPTER;
  private static final BooleanTypeAdapter BOOLEAN_TYPE_ADAPTER;
  private static final ByteTypeAdapter BYTE_TYPE_ADAPTER;
  private static final CharacterTypeAdapter CHARACTER_TYPE_ADAPTER;
  private static final CollectionTypeAdapter COLLECTION_TYPE_ADAPTER;
  private static final DefaultDateTypeAdapter DATE_TYPE_ADAPTER = new DefaultDateTypeAdapter();
  private static final ParameterizedTypeHandlerMap<JsonDeserializer<?>> DEFAULT_DESERIALIZERS;
  static final ParameterizedTypeHandlerMap<JsonDeserializer<?>> DEFAULT_HIERARCHY_DESERIALIZERS;
  static final ParameterizedTypeHandlerMap<JsonSerializer<?>> DEFAULT_HIERARCHY_SERIALIZERS;
  private static final ParameterizedTypeHandlerMap<InstanceCreator<?>> DEFAULT_INSTANCE_CREATORS;
  private static final ParameterizedTypeHandlerMap<JsonSerializer<?>> DEFAULT_SERIALIZERS;
  private static final DoubleDeserializer DOUBLE_TYPE_ADAPTER;
  private static final EnumTypeAdapter ENUM_TYPE_ADAPTER;
  private static final FloatDeserializer FLOAT_TYPE_ADAPTER;
  private static final GregorianCalendarTypeAdapter GREGORIAN_CALENDAR_TYPE_ADAPTER;
  private static final DefaultInetAddressAdapter INET_ADDRESS_ADAPTER;
  private static final IntegerTypeAdapter INTEGER_TYPE_ADAPTER;
  private static final DefaultJavaSqlDateTypeAdapter JAVA_SQL_DATE_TYPE_ADAPTER = new DefaultJavaSqlDateTypeAdapter();
  private static final LocaleTypeAdapter LOCALE_TYPE_ADAPTER;
  private static final LongDeserializer LONG_DESERIALIZER;
  private static final MapTypeAdapter MAP_TYPE_ADAPTER;
  private static final NumberTypeAdapter NUMBER_TYPE_ADAPTER;
  private static final ShortTypeAdapter SHORT_TYPE_ADAPTER;
  private static final StringBufferTypeAdapter STRING_BUFFER_TYPE_ADAPTER;
  private static final StringBuilderTypeAdapter STRING_BUILDER_TYPE_ADAPTER;
  private static final StringTypeAdapter STRING_TYPE_ADAPTER;
  private static final DefaultTimestampDeserializer TIMESTAMP_DESERIALIZER;
  private static final DefaultTimeTypeAdapter TIME_TYPE_ADAPTER = new DefaultTimeTypeAdapter();
  private static final UriTypeAdapter URI_TYPE_ADAPTER;
  private static final UrlTypeAdapter URL_TYPE_ADAPTER;
  private static final UuidTypeAdapter UUUID_TYPE_ADAPTER;

  static
  {
    TIMESTAMP_DESERIALIZER = new DefaultTimestampDeserializer();
    ENUM_TYPE_ADAPTER = new EnumTypeAdapter(null);
    URL_TYPE_ADAPTER = new UrlTypeAdapter(null);
    URI_TYPE_ADAPTER = new UriTypeAdapter(null);
    UUUID_TYPE_ADAPTER = new UuidTypeAdapter(null);
    LOCALE_TYPE_ADAPTER = new LocaleTypeAdapter(null);
    INET_ADDRESS_ADAPTER = new DefaultInetAddressAdapter();
    COLLECTION_TYPE_ADAPTER = new CollectionTypeAdapter(null);
    MAP_TYPE_ADAPTER = new MapTypeAdapter();
    BIG_DECIMAL_TYPE_ADAPTER = new BigDecimalTypeAdapter(null);
    BIG_INTEGER_TYPE_ADAPTER = new BigIntegerTypeAdapter(null);
    BOOLEAN_TYPE_ADAPTER = new BooleanTypeAdapter(null);
    BYTE_TYPE_ADAPTER = new ByteTypeAdapter(null);
    CHARACTER_TYPE_ADAPTER = new CharacterTypeAdapter(null);
    DOUBLE_TYPE_ADAPTER = new DoubleDeserializer(null);
    FLOAT_TYPE_ADAPTER = new FloatDeserializer(null);
    INTEGER_TYPE_ADAPTER = new IntegerTypeAdapter(null);
    LONG_DESERIALIZER = new LongDeserializer(null);
    NUMBER_TYPE_ADAPTER = new NumberTypeAdapter(null);
    SHORT_TYPE_ADAPTER = new ShortTypeAdapter(null);
    STRING_TYPE_ADAPTER = new StringTypeAdapter(null);
    STRING_BUILDER_TYPE_ADAPTER = new StringBuilderTypeAdapter(null);
    STRING_BUFFER_TYPE_ADAPTER = new StringBufferTypeAdapter(null);
    GREGORIAN_CALENDAR_TYPE_ADAPTER = new GregorianCalendarTypeAdapter(null);
    DEFAULT_SERIALIZERS = createDefaultSerializers();
    DEFAULT_HIERARCHY_SERIALIZERS = createDefaultHierarchySerializers();
    DEFAULT_DESERIALIZERS = createDefaultDeserializers();
    DEFAULT_HIERARCHY_DESERIALIZERS = createDefaultHierarchyDeserializers();
    DEFAULT_INSTANCE_CREATORS = createDefaultInstanceCreators();
  }

  private static ParameterizedTypeHandlerMap<JsonDeserializer<?>> createDefaultDeserializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    localParameterizedTypeHandlerMap.register(URL.class, wrapDeserializer(URL_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(URI.class, wrapDeserializer(URI_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(UUID.class, wrapDeserializer(UUUID_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(Locale.class, wrapDeserializer(LOCALE_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(java.util.Date.class, wrapDeserializer(DATE_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(java.sql.Date.class, wrapDeserializer(JAVA_SQL_DATE_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(Timestamp.class, wrapDeserializer(TIMESTAMP_DESERIALIZER));
    localParameterizedTypeHandlerMap.register(Time.class, wrapDeserializer(TIME_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(Calendar.class, GREGORIAN_CALENDAR_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(GregorianCalendar.class, GREGORIAN_CALENDAR_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(BigDecimal.class, BIG_DECIMAL_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(BigInteger.class, BIG_INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Boolean.class, BOOLEAN_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Boolean.TYPE, BOOLEAN_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Byte.class, BYTE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Byte.TYPE, BYTE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Character.class, wrapDeserializer(CHARACTER_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(Character.TYPE, wrapDeserializer(CHARACTER_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(Double.class, DOUBLE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Double.TYPE, DOUBLE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Float.class, FLOAT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Float.TYPE, FLOAT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Integer.class, INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Integer.TYPE, INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Long.class, LONG_DESERIALIZER);
    localParameterizedTypeHandlerMap.register(Long.TYPE, LONG_DESERIALIZER);
    localParameterizedTypeHandlerMap.register(Number.class, NUMBER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Short.class, SHORT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Short.TYPE, SHORT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(String.class, wrapDeserializer(STRING_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(StringBuilder.class, wrapDeserializer(STRING_BUILDER_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.register(StringBuffer.class, wrapDeserializer(STRING_BUFFER_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.makeUnmodifiable();
    return localParameterizedTypeHandlerMap;
  }

  private static ParameterizedTypeHandlerMap<JsonDeserializer<?>> createDefaultHierarchyDeserializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Enum.class, wrapDeserializer(ENUM_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(InetAddress.class, wrapDeserializer(INET_ADDRESS_ADAPTER));
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Collection.class, wrapDeserializer(COLLECTION_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Map.class, wrapDeserializer(MAP_TYPE_ADAPTER));
    localParameterizedTypeHandlerMap.makeUnmodifiable();
    return localParameterizedTypeHandlerMap;
  }

  private static ParameterizedTypeHandlerMap<JsonSerializer<?>> createDefaultHierarchySerializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Enum.class, ENUM_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(InetAddress.class, INET_ADDRESS_ADAPTER);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Collection.class, COLLECTION_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Map.class, MAP_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.makeUnmodifiable();
    return localParameterizedTypeHandlerMap;
  }

  private static ParameterizedTypeHandlerMap<InstanceCreator<?>> createDefaultInstanceCreators()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    DefaultConstructorAllocator localDefaultConstructorAllocator = new DefaultConstructorAllocator(50);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Map.class, new DefaultConstructorCreator(LinkedHashMap.class, localDefaultConstructorAllocator));
    DefaultConstructorCreator localDefaultConstructorCreator1 = new DefaultConstructorCreator(ArrayList.class, localDefaultConstructorAllocator);
    DefaultConstructorCreator localDefaultConstructorCreator2 = new DefaultConstructorCreator(LinkedList.class, localDefaultConstructorAllocator);
    DefaultConstructorCreator localDefaultConstructorCreator3 = new DefaultConstructorCreator(HashSet.class, localDefaultConstructorAllocator);
    DefaultConstructorCreator localDefaultConstructorCreator4 = new DefaultConstructorCreator(TreeSet.class, localDefaultConstructorAllocator);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Collection.class, localDefaultConstructorCreator1);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Queue.class, localDefaultConstructorCreator2);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(Set.class, localDefaultConstructorCreator3);
    localParameterizedTypeHandlerMap.registerForTypeHierarchy(SortedSet.class, localDefaultConstructorCreator4);
    localParameterizedTypeHandlerMap.makeUnmodifiable();
    return localParameterizedTypeHandlerMap;
  }

  private static ParameterizedTypeHandlerMap<JsonSerializer<?>> createDefaultSerializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    localParameterizedTypeHandlerMap.register(URL.class, URL_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(URI.class, URI_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(UUID.class, UUUID_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Locale.class, LOCALE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(java.util.Date.class, DATE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(java.sql.Date.class, JAVA_SQL_DATE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Timestamp.class, DATE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Time.class, TIME_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Calendar.class, GREGORIAN_CALENDAR_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(GregorianCalendar.class, GREGORIAN_CALENDAR_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(BigDecimal.class, BIG_DECIMAL_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(BigInteger.class, BIG_INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Boolean.class, BOOLEAN_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Boolean.TYPE, BOOLEAN_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Byte.class, BYTE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Byte.TYPE, BYTE_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Character.class, CHARACTER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Character.TYPE, CHARACTER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Integer.class, INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Integer.TYPE, INTEGER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Number.class, NUMBER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Short.class, SHORT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(Short.TYPE, SHORT_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(String.class, STRING_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(StringBuilder.class, STRING_BUILDER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.register(StringBuffer.class, STRING_BUFFER_TYPE_ADAPTER);
    localParameterizedTypeHandlerMap.makeUnmodifiable();
    return localParameterizedTypeHandlerMap;
  }

  static ParameterizedTypeHandlerMap<JsonDeserializer<?>> getAllDefaultDeserializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = getDefaultDeserializers().copyOf();
    localParameterizedTypeHandlerMap.register(DEFAULT_HIERARCHY_DESERIALIZERS);
    return localParameterizedTypeHandlerMap;
  }

  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getAllDefaultSerializers()
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = getDefaultSerializers(false, LongSerializationPolicy.DEFAULT);
    localParameterizedTypeHandlerMap.register(DEFAULT_HIERARCHY_SERIALIZERS);
    return localParameterizedTypeHandlerMap;
  }

  static ParameterizedTypeHandlerMap<JsonDeserializer<?>> getDefaultDeserializers()
  {
    return DEFAULT_DESERIALIZERS;
  }

  static ParameterizedTypeHandlerMap<InstanceCreator<?>> getDefaultInstanceCreators()
  {
    return DEFAULT_INSTANCE_CREATORS;
  }

  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers()
  {
    return getDefaultSerializers(false, LongSerializationPolicy.DEFAULT);
  }

  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers(boolean paramBoolean, LongSerializationPolicy paramLongSerializationPolicy)
  {
    ParameterizedTypeHandlerMap localParameterizedTypeHandlerMap = new ParameterizedTypeHandlerMap();
    DoubleSerializer localDoubleSerializer = new DoubleSerializer(paramBoolean);
    localParameterizedTypeHandlerMap.registerIfAbsent(Double.class, localDoubleSerializer);
    localParameterizedTypeHandlerMap.registerIfAbsent(Double.TYPE, localDoubleSerializer);
    FloatSerializer localFloatSerializer = new FloatSerializer(paramBoolean);
    localParameterizedTypeHandlerMap.registerIfAbsent(Float.class, localFloatSerializer);
    localParameterizedTypeHandlerMap.registerIfAbsent(Float.TYPE, localFloatSerializer);
    LongSerializer localLongSerializer = new LongSerializer(paramLongSerializationPolicy, null);
    localParameterizedTypeHandlerMap.registerIfAbsent(Long.class, localLongSerializer);
    localParameterizedTypeHandlerMap.registerIfAbsent(Long.TYPE, localLongSerializer);
    localParameterizedTypeHandlerMap.registerIfAbsent(DEFAULT_SERIALIZERS);
    return localParameterizedTypeHandlerMap;
  }

  private static JsonDeserializer<?> wrapDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    return new JsonDeserializerExceptionWrapper(paramJsonDeserializer);
  }

  private static final class BigDecimalTypeAdapter
    implements JsonDeserializer<BigDecimal>, JsonSerializer<BigDecimal>
  {
    public BigDecimal deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        BigDecimal localBigDecimal = paramJsonElement.getAsBigDecimal();
        return localBigDecimal;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(BigDecimal paramBigDecimal, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramBigDecimal);
    }

    public String toString()
    {
      return BigDecimalTypeAdapter.class.getSimpleName();
    }
  }

  private static final class BigIntegerTypeAdapter
    implements JsonDeserializer<BigInteger>, JsonSerializer<BigInteger>
  {
    public BigInteger deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        BigInteger localBigInteger = paramJsonElement.getAsBigInteger();
        return localBigInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(BigInteger paramBigInteger, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramBigInteger);
    }

    public String toString()
    {
      return BigIntegerTypeAdapter.class.getSimpleName();
    }
  }

  private static final class BooleanTypeAdapter
    implements JsonDeserializer<Boolean>, JsonSerializer<Boolean>
  {
    public Boolean deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Boolean localBoolean = Boolean.valueOf(paramJsonElement.getAsBoolean());
        return localBoolean;
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(Boolean paramBoolean, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramBoolean);
    }

    public String toString()
    {
      return BooleanTypeAdapter.class.getSimpleName();
    }
  }

  private static final class ByteTypeAdapter
    implements JsonDeserializer<Byte>, JsonSerializer<Byte>
  {
    public Byte deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Byte localByte = Byte.valueOf(paramJsonElement.getAsByte());
        return localByte;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(Byte paramByte, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramByte);
    }

    public String toString()
    {
      return ByteTypeAdapter.class.getSimpleName();
    }
  }

  private static final class CharacterTypeAdapter
    implements JsonDeserializer<Character>, JsonSerializer<Character>
  {
    public Character deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return Character.valueOf(paramJsonElement.getAsCharacter());
    }

    public JsonElement serialize(Character paramCharacter, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramCharacter);
    }

    public String toString()
    {
      return CharacterTypeAdapter.class.getSimpleName();
    }
  }

  private static final class CollectionTypeAdapter
    implements JsonDeserializer<Collection>, JsonSerializer<Collection>
  {
    private Collection constructCollectionType(Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    {
      return (Collection)((JsonDeserializationContextDefault)paramJsonDeserializationContext).getObjectConstructor().construct(paramType);
    }

    public Collection deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      if (paramJsonElement.isJsonNull())
        return null;
      Collection localCollection = constructCollectionType(paramType, paramJsonDeserializationContext);
      Type localType = .Gson.Types.getCollectionElementType(paramType, .Gson.Types.getRawType(paramType));
      Iterator localIterator = paramJsonElement.getAsJsonArray().iterator();
      while (localIterator.hasNext())
      {
        JsonElement localJsonElement = (JsonElement)localIterator.next();
        if ((localJsonElement == null) || (localJsonElement.isJsonNull()))
        {
          localCollection.add(null);
          continue;
        }
        localCollection.add(paramJsonDeserializationContext.deserialize(localJsonElement, localType));
      }
      return localCollection;
    }

    public JsonElement serialize(Collection paramCollection, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      if (paramCollection == null)
        return JsonNull.createJsonNull();
      JsonArray localJsonArray = new JsonArray();
      boolean bool = paramType instanceof ParameterizedType;
      Type localType = null;
      if (bool)
        localType = .Gson.Types.getCollectionElementType(paramType, .Gson.Types.getRawType(paramType));
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Object localObject1 = localIterator.next();
        if (localObject1 == null)
        {
          localJsonArray.add(JsonNull.createJsonNull());
          continue;
        }
        if ((localType == null) || (localType == Object.class));
        for (Object localObject2 = localObject1.getClass(); ; localObject2 = localType)
        {
          localJsonArray.add(paramJsonSerializationContext.serialize(localObject1, (Type)localObject2));
          break;
        }
      }
      return (JsonElement)localJsonArray;
    }
  }

  private static final class DefaultConstructorCreator<T>
    implements InstanceCreator<T>
  {
    private final DefaultConstructorAllocator allocator;
    private final Class<? extends T> defaultInstance;

    public DefaultConstructorCreator(Class<? extends T> paramClass, DefaultConstructorAllocator paramDefaultConstructorAllocator)
    {
      this.defaultInstance = paramClass;
      this.allocator = paramDefaultConstructorAllocator;
    }

    public T createInstance(Type paramType)
    {
      Class localClass = .Gson.Types.getRawType(paramType);
      try
      {
        Object localObject1 = this.allocator.newInstance(localClass);
        if (localObject1 == null)
        {
          Object localObject2 = this.allocator.newInstance(this.defaultInstance);
          localObject1 = localObject2;
        }
        return localObject1;
      }
      catch (Exception localException)
      {
      }
      throw new JsonIOException(localException);
    }

    public String toString()
    {
      return DefaultConstructorCreator.class.getSimpleName();
    }
  }

  static final class DefaultDateTypeAdapter
    implements JsonDeserializer<java.util.Date>, JsonSerializer<java.util.Date>
  {
    private final DateFormat enUsFormat;
    private final DateFormat iso8601Format;
    private final DateFormat localFormat;

    DefaultDateTypeAdapter()
    {
      this(DateFormat.getDateTimeInstance(2, 2, Locale.US), DateFormat.getDateTimeInstance(2, 2));
    }

    DefaultDateTypeAdapter(int paramInt)
    {
      this(DateFormat.getDateInstance(paramInt, Locale.US), DateFormat.getDateInstance(paramInt));
    }

    public DefaultDateTypeAdapter(int paramInt1, int paramInt2)
    {
      this(DateFormat.getDateTimeInstance(paramInt1, paramInt2, Locale.US), DateFormat.getDateTimeInstance(paramInt1, paramInt2));
    }

    DefaultDateTypeAdapter(String paramString)
    {
      this(new SimpleDateFormat(paramString, Locale.US), new SimpleDateFormat(paramString));
    }

    DefaultDateTypeAdapter(DateFormat paramDateFormat1, DateFormat paramDateFormat2)
    {
      this.enUsFormat = paramDateFormat1;
      this.localFormat = paramDateFormat2;
      this.iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
      this.iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private java.util.Date deserializeToDate(JsonElement paramJsonElement)
    {
      synchronized (this.localFormat)
      {
        try
        {
          java.util.Date localDate3 = this.localFormat.parse(paramJsonElement.getAsString());
          return localDate3;
        }
        catch (ParseException localParseException1)
        {
        }
      }
      try
      {
        java.util.Date localDate2 = this.enUsFormat.parse(paramJsonElement.getAsString());
        monitorexit;
        return localDate2;
        localObject = finally;
        monitorexit;
        throw localObject;
      }
      catch (ParseException localParseException2)
      {
        try
        {
          java.util.Date localDate1 = this.iso8601Format.parse(paramJsonElement.getAsString());
          monitorexit;
          return localDate1;
        }
        catch (ParseException localParseException3)
        {
        }
      }
      throw new JsonSyntaxException(paramJsonElement.getAsString(), localParseException3);
    }

    public java.util.Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      if (!(paramJsonElement instanceof JsonPrimitive))
        throw new JsonParseException("The date should be a string value");
      java.util.Date localDate = deserializeToDate(paramJsonElement);
      if (paramType == java.util.Date.class)
        return localDate;
      if (paramType == Timestamp.class)
        return new Timestamp(localDate.getTime());
      if (paramType == java.sql.Date.class)
        return new java.sql.Date(localDate.getTime());
      throw new IllegalArgumentException(getClass() + " cannot deserialize to " + paramType);
    }

    public JsonElement serialize(java.util.Date paramDate, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      synchronized (this.localFormat)
      {
        JsonPrimitive localJsonPrimitive = new JsonPrimitive(this.enUsFormat.format(paramDate));
        return localJsonPrimitive;
      }
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(DefaultDateTypeAdapter.class.getSimpleName());
      localStringBuilder.append('(').append(this.localFormat.getClass().getSimpleName()).append(')');
      return localStringBuilder.toString();
    }
  }

  static final class DefaultInetAddressAdapter
    implements JsonDeserializer<InetAddress>, JsonSerializer<InetAddress>
  {
    public InetAddress deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        InetAddress localInetAddress = InetAddress.getByName(paramJsonElement.getAsString());
        return localInetAddress;
      }
      catch (UnknownHostException localUnknownHostException)
      {
      }
      throw new JsonParseException(localUnknownHostException);
    }

    public JsonElement serialize(InetAddress paramInetAddress, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramInetAddress.getHostAddress());
    }
  }

  static final class DefaultJavaSqlDateTypeAdapter
    implements JsonDeserializer<java.sql.Date>, JsonSerializer<java.sql.Date>
  {
    private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

    public java.sql.Date deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      if (!(paramJsonElement instanceof JsonPrimitive))
        throw new JsonParseException("The date should be a string value");
      try
      {
        synchronized (this.format)
        {
          java.sql.Date localDate = new java.sql.Date(this.format.parse(paramJsonElement.getAsString()).getTime());
          return localDate;
        }
      }
      catch (ParseException localParseException)
      {
      }
      throw new JsonSyntaxException(localParseException);
    }

    public JsonElement serialize(java.sql.Date paramDate, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      synchronized (this.format)
      {
        JsonPrimitive localJsonPrimitive = new JsonPrimitive(this.format.format(paramDate));
        return localJsonPrimitive;
      }
    }
  }

  static final class DefaultTimeTypeAdapter
    implements JsonDeserializer<Time>, JsonSerializer<Time>
  {
    private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

    public Time deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      if (!(paramJsonElement instanceof JsonPrimitive))
        throw new JsonParseException("The date should be a string value");
      try
      {
        synchronized (this.format)
        {
          Time localTime = new Time(this.format.parse(paramJsonElement.getAsString()).getTime());
          return localTime;
        }
      }
      catch (ParseException localParseException)
      {
      }
      throw new JsonSyntaxException(localParseException);
    }

    public JsonElement serialize(Time paramTime, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      synchronized (this.format)
      {
        JsonPrimitive localJsonPrimitive = new JsonPrimitive(this.format.format(paramTime));
        return localJsonPrimitive;
      }
    }
  }

  static final class DefaultTimestampDeserializer
    implements JsonDeserializer<Timestamp>
  {
    public Timestamp deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return new Timestamp(((java.util.Date)paramJsonDeserializationContext.deserialize(paramJsonElement, java.util.Date.class)).getTime());
    }
  }

  private static final class DoubleDeserializer
    implements JsonDeserializer<Double>
  {
    public Double deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Double localDouble = Double.valueOf(paramJsonElement.getAsDouble());
        return localDouble;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public String toString()
    {
      return DoubleDeserializer.class.getSimpleName();
    }
  }

  static final class DoubleSerializer
    implements JsonSerializer<Double>
  {
    private final boolean serializeSpecialFloatingPointValues;

    DoubleSerializer(boolean paramBoolean)
    {
      this.serializeSpecialFloatingPointValues = paramBoolean;
    }

    public JsonElement serialize(Double paramDouble, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      if ((!this.serializeSpecialFloatingPointValues) && ((Double.isNaN(paramDouble.doubleValue())) || (Double.isInfinite(paramDouble.doubleValue()))))
        throw new IllegalArgumentException(paramDouble + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialDoubleValues() method.");
      return new JsonPrimitive(paramDouble);
    }
  }

  private static final class EnumTypeAdapter<T extends Enum<T>>
    implements JsonDeserializer<T>, JsonSerializer<T>
  {
    public T deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return Enum.valueOf((Class)paramType, paramJsonElement.getAsString());
    }

    public JsonElement serialize(T paramT, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramT.name());
    }

    public String toString()
    {
      return EnumTypeAdapter.class.getSimpleName();
    }
  }

  private static final class FloatDeserializer
    implements JsonDeserializer<Float>
  {
    public Float deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Float localFloat = Float.valueOf(paramJsonElement.getAsFloat());
        return localFloat;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public String toString()
    {
      return FloatDeserializer.class.getSimpleName();
    }
  }

  static final class FloatSerializer
    implements JsonSerializer<Float>
  {
    private final boolean serializeSpecialFloatingPointValues;

    FloatSerializer(boolean paramBoolean)
    {
      this.serializeSpecialFloatingPointValues = paramBoolean;
    }

    public JsonElement serialize(Float paramFloat, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      if ((!this.serializeSpecialFloatingPointValues) && ((Float.isNaN(paramFloat.floatValue())) || (Float.isInfinite(paramFloat.floatValue()))))
        throw new IllegalArgumentException(paramFloat + " is not a valid float value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
      return new JsonPrimitive(paramFloat);
    }
  }

  private static final class GregorianCalendarTypeAdapter
    implements JsonDeserializer<GregorianCalendar>, JsonSerializer<GregorianCalendar>
  {
    private static final String DAY_OF_MONTH = "dayOfMonth";
    private static final String HOUR_OF_DAY = "hourOfDay";
    private static final String MINUTE = "minute";
    private static final String MONTH = "month";
    private static final String SECOND = "second";
    private static final String YEAR = "year";

    public GregorianCalendar deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      JsonObject localJsonObject = paramJsonElement.getAsJsonObject();
      return new GregorianCalendar(localJsonObject.get("year").getAsInt(), localJsonObject.get("month").getAsInt(), localJsonObject.get("dayOfMonth").getAsInt(), localJsonObject.get("hourOfDay").getAsInt(), localJsonObject.get("minute").getAsInt(), localJsonObject.get("second").getAsInt());
    }

    public JsonElement serialize(GregorianCalendar paramGregorianCalendar, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      JsonObject localJsonObject = new JsonObject();
      localJsonObject.addProperty("year", Integer.valueOf(paramGregorianCalendar.get(1)));
      localJsonObject.addProperty("month", Integer.valueOf(paramGregorianCalendar.get(2)));
      localJsonObject.addProperty("dayOfMonth", Integer.valueOf(paramGregorianCalendar.get(5)));
      localJsonObject.addProperty("hourOfDay", Integer.valueOf(paramGregorianCalendar.get(11)));
      localJsonObject.addProperty("minute", Integer.valueOf(paramGregorianCalendar.get(12)));
      localJsonObject.addProperty("second", Integer.valueOf(paramGregorianCalendar.get(13)));
      return localJsonObject;
    }

    public String toString()
    {
      return GregorianCalendarTypeAdapter.class.getSimpleName();
    }
  }

  private static final class IntegerTypeAdapter
    implements JsonDeserializer<Integer>, JsonSerializer<Integer>
  {
    public Integer deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Integer localInteger = Integer.valueOf(paramJsonElement.getAsInt());
        return localInteger;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(Integer paramInteger, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramInteger);
    }

    public String toString()
    {
      return IntegerTypeAdapter.class.getSimpleName();
    }
  }

  private static final class LocaleTypeAdapter
    implements JsonDeserializer<Locale>, JsonSerializer<Locale>
  {
    public Locale deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      StringTokenizer localStringTokenizer = new StringTokenizer(paramJsonElement.getAsString(), "_");
      if (localStringTokenizer.hasMoreElements());
      for (String str1 = localStringTokenizer.nextToken(); ; str1 = null)
      {
        if (localStringTokenizer.hasMoreElements());
        for (String str2 = localStringTokenizer.nextToken(); ; str2 = null)
        {
          if (localStringTokenizer.hasMoreElements());
          for (String str3 = localStringTokenizer.nextToken(); ; str3 = null)
          {
            if ((str2 == null) && (str3 == null))
              return new Locale(str1);
            if (str3 == null)
              return new Locale(str1, str2);
            return new Locale(str1, str2, str3);
          }
        }
      }
    }

    public JsonElement serialize(Locale paramLocale, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramLocale.toString());
    }

    public String toString()
    {
      return LocaleTypeAdapter.class.getSimpleName();
    }
  }

  private static final class LongDeserializer
    implements JsonDeserializer<Long>
  {
    public Long deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Long localLong = Long.valueOf(paramJsonElement.getAsLong());
        return localLong;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public String toString()
    {
      return LongDeserializer.class.getSimpleName();
    }
  }

  private static final class LongSerializer
    implements JsonSerializer<Long>
  {
    private final LongSerializationPolicy longSerializationPolicy;

    private LongSerializer(LongSerializationPolicy paramLongSerializationPolicy)
    {
      this.longSerializationPolicy = paramLongSerializationPolicy;
    }

    public JsonElement serialize(Long paramLong, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return this.longSerializationPolicy.serialize(paramLong);
    }

    public String toString()
    {
      return LongSerializer.class.getSimpleName();
    }
  }

  private static final class NumberTypeAdapter
    implements JsonDeserializer<Number>, JsonSerializer<Number>
  {
    public Number deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Number localNumber = paramJsonElement.getAsNumber();
        return localNumber;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(Number paramNumber, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramNumber);
    }

    public String toString()
    {
      return NumberTypeAdapter.class.getSimpleName();
    }
  }

  private static final class ShortTypeAdapter
    implements JsonDeserializer<Short>, JsonSerializer<Short>
  {
    public Short deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        Short localShort = Short.valueOf(paramJsonElement.getAsShort());
        return localShort;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new JsonSyntaxException(localNumberFormatException);
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        throw new JsonSyntaxException(localUnsupportedOperationException);
      }
      catch (IllegalStateException localIllegalStateException)
      {
      }
      throw new JsonSyntaxException(localIllegalStateException);
    }

    public JsonElement serialize(Short paramShort, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramShort);
    }

    public String toString()
    {
      return ShortTypeAdapter.class.getSimpleName();
    }
  }

  private static final class StringBufferTypeAdapter
    implements JsonDeserializer<StringBuffer>, JsonSerializer<StringBuffer>
  {
    public StringBuffer deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return new StringBuffer(paramJsonElement.getAsString());
    }

    public JsonElement serialize(StringBuffer paramStringBuffer, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramStringBuffer.toString());
    }

    public String toString()
    {
      return StringBufferTypeAdapter.class.getSimpleName();
    }
  }

  private static final class StringBuilderTypeAdapter
    implements JsonDeserializer<StringBuilder>, JsonSerializer<StringBuilder>
  {
    public StringBuilder deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return new StringBuilder(paramJsonElement.getAsString());
    }

    public JsonElement serialize(StringBuilder paramStringBuilder, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramStringBuilder.toString());
    }

    public String toString()
    {
      return StringBuilderTypeAdapter.class.getSimpleName();
    }
  }

  private static final class StringTypeAdapter
    implements JsonDeserializer<String>, JsonSerializer<String>
  {
    public String deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return paramJsonElement.getAsString();
    }

    public JsonElement serialize(String paramString, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramString);
    }

    public String toString()
    {
      return StringTypeAdapter.class.getSimpleName();
    }
  }

  private static final class UriTypeAdapter
    implements JsonDeserializer<URI>, JsonSerializer<URI>
  {
    public URI deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        URI localURI = new URI(paramJsonElement.getAsString());
        return localURI;
      }
      catch (URISyntaxException localURISyntaxException)
      {
      }
      throw new JsonSyntaxException(localURISyntaxException);
    }

    public JsonElement serialize(URI paramURI, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramURI.toASCIIString());
    }

    public String toString()
    {
      return UriTypeAdapter.class.getSimpleName();
    }
  }

  private static final class UrlTypeAdapter
    implements JsonDeserializer<URL>, JsonSerializer<URL>
  {
    public URL deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      try
      {
        URL localURL = new URL(paramJsonElement.getAsString());
        return localURL;
      }
      catch (MalformedURLException localMalformedURLException)
      {
      }
      throw new JsonSyntaxException(localMalformedURLException);
    }

    public JsonElement serialize(URL paramURL, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramURL.toExternalForm());
    }

    public String toString()
    {
      return UrlTypeAdapter.class.getSimpleName();
    }
  }

  private static final class UuidTypeAdapter
    implements JsonDeserializer<UUID>, JsonSerializer<UUID>
  {
    public UUID deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
      throws JsonParseException
    {
      return UUID.fromString(paramJsonElement.getAsString());
    }

    public JsonElement serialize(UUID paramUUID, Type paramType, JsonSerializationContext paramJsonSerializationContext)
    {
      return new JsonPrimitive(paramUUID.toString());
    }

    public String toString()
    {
      return UuidTypeAdapter.class.getSimpleName();
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.DefaultTypeAdapters
 * JD-Core Version:    0.6.0
 */