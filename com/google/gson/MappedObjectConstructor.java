package com.google.gson;

import com.google.gson.internal..Gson.Types;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

final class MappedObjectConstructor
  implements ObjectConstructor
{
  private static final DefaultConstructorAllocator defaultConstructorAllocator;
  private static final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();
  private final ParameterizedTypeHandlerMap<InstanceCreator<?>> instanceCreatorMap;

  static
  {
    defaultConstructorAllocator = new DefaultConstructorAllocator(500);
  }

  public MappedObjectConstructor(ParameterizedTypeHandlerMap<InstanceCreator<?>> paramParameterizedTypeHandlerMap)
  {
    this.instanceCreatorMap = paramParameterizedTypeHandlerMap;
  }

  private <T> T constructWithAllocators(Type paramType)
  {
    try
    {
      Class localClass = .Gson.Types.getRawType(paramType);
      Object localObject1 = defaultConstructorAllocator.newInstance(localClass);
      if (localObject1 == null)
      {
        Object localObject2 = unsafeAllocator.newInstance(localClass);
        localObject1 = localObject2;
      }
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException("Unable to invoke no-args constructor for " + paramType + ". " + "Register an InstanceCreator with Gson for this type may fix this problem.", localException);
  }

  public <T> T construct(Type paramType)
  {
    InstanceCreator localInstanceCreator = (InstanceCreator)this.instanceCreatorMap.getHandlerFor(paramType);
    if (localInstanceCreator != null)
      return localInstanceCreator.createInstance(paramType);
    return constructWithAllocators(paramType);
  }

  public Object constructArray(Type paramType, int paramInt)
  {
    return Array.newInstance(.Gson.Types.getRawType(paramType), paramInt);
  }

  public String toString()
  {
    return this.instanceCreatorMap.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.MappedObjectConstructor
 * JD-Core Version:    0.6.0
 */