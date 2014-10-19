package com.google.gson;

import java.lang.reflect.Type;

final class ObjectTypePair
{
  private Object obj;
  private final boolean preserveType;
  final Type type;

  ObjectTypePair(Object paramObject, Type paramType, boolean paramBoolean)
  {
    this.obj = paramObject;
    this.type = paramType;
    this.preserveType = paramBoolean;
  }

  static Type getActualTypeIfMoreSpecific(Type paramType, Class<?> paramClass)
  {
    if ((paramType instanceof Class))
    {
      if (((Class)paramType).isAssignableFrom(paramClass))
        paramType = paramClass;
      if (paramType == Object.class)
        paramType = paramClass;
    }
    return paramType;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    int j;
    if (this == paramObject)
      j = i;
    ObjectTypePair localObjectTypePair;
    while (true)
    {
      return j;
      j = 0;
      if (paramObject == null)
        continue;
      Class localClass1 = getClass();
      Class localClass2 = paramObject.getClass();
      j = 0;
      if (localClass1 != localClass2)
        continue;
      localObjectTypePair = (ObjectTypePair)paramObject;
      if (this.obj != null)
        break;
      Object localObject = localObjectTypePair.obj;
      j = 0;
      if (localObject != null)
        continue;
      if (this.type != null)
        break label114;
      Type localType = localObjectTypePair.type;
      j = 0;
      if (localType != null)
        continue;
      label86: if (this.preserveType != localObjectTypePair.preserveType)
        break label131;
    }
    while (true)
    {
      return i;
      if (this.obj == localObjectTypePair.obj)
        break;
      return false;
      label114: if (this.type.equals(localObjectTypePair.type))
        break label86;
      return false;
      label131: i = 0;
    }
  }

  <HANDLER> Pair<HANDLER, ObjectTypePair> getMatchingHandler(ParameterizedTypeHandlerMap<HANDLER> paramParameterizedTypeHandlerMap)
  {
    if ((!this.preserveType) && (this.obj != null))
    {
      ObjectTypePair localObjectTypePair = toMoreSpecificType();
      Object localObject2 = paramParameterizedTypeHandlerMap.getHandlerFor(localObjectTypePair.type);
      if (localObject2 != null)
        return new Pair(localObject2, localObjectTypePair);
    }
    Object localObject1 = paramParameterizedTypeHandlerMap.getHandlerFor(this.type);
    if (localObject1 == null)
      return null;
    return new Pair(localObject1, this);
  }

  Type getMoreSpecificType()
  {
    if ((this.preserveType) || (this.obj == null))
      return this.type;
    return getActualTypeIfMoreSpecific(this.type, this.obj.getClass());
  }

  Object getObject()
  {
    return this.obj;
  }

  Type getType()
  {
    return this.type;
  }

  public int hashCode()
  {
    if (this.obj == null)
      return 31;
    return this.obj.hashCode();
  }

  public boolean isPreserveType()
  {
    return this.preserveType;
  }

  void setObject(Object paramObject)
  {
    this.obj = paramObject;
  }

  ObjectTypePair toMoreSpecificType()
  {
    if ((this.preserveType) || (this.obj == null));
    Type localType;
    do
    {
      return this;
      localType = getActualTypeIfMoreSpecific(this.type, this.obj.getClass());
    }
    while (localType == this.type);
    return new ObjectTypePair(this.obj, localType, this.preserveType);
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Boolean.valueOf(this.preserveType);
    arrayOfObject[1] = this.type;
    arrayOfObject[2] = this.obj;
    return String.format("preserveType: %b, type: %s, obj: %s", arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ObjectTypePair
 * JD-Core Version:    0.6.0
 */