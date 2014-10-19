package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import com.google.gson.internal..Gson.Types;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lcom.google.gson.FieldAttributes;>;

final class ReflectingFieldNavigator
{
  private static final Cache<Type, List<FieldAttributes>> fieldsCache = new LruCache(500);
  private final ExclusionStrategy exclusionStrategy;

  ReflectingFieldNavigator(ExclusionStrategy paramExclusionStrategy)
  {
    this.exclusionStrategy = ((ExclusionStrategy).Gson.Preconditions.checkNotNull(paramExclusionStrategy));
  }

  private List<FieldAttributes> getAllFields(Type paramType1, Type paramType2)
  {
    Object localObject = (List)fieldsCache.getElement(paramType1);
    if (localObject == null)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = getInheritanceHierarchy(paramType1).iterator();
      while (localIterator.hasNext())
      {
        Class localClass = (Class)localIterator.next();
        Field[] arrayOfField = localClass.getDeclaredFields();
        AccessibleObject.setAccessible(arrayOfField, true);
        int i = arrayOfField.length;
        for (int j = 0; j < i; j++)
          localArrayList.add(new FieldAttributes(localClass, arrayOfField[j], paramType2));
      }
      fieldsCache.addElement(paramType1, localArrayList);
      localObject = localArrayList;
    }
    return (List<FieldAttributes>)localObject;
  }

  private List<Class<?>> getInheritanceHierarchy(Type paramType)
  {
    ArrayList localArrayList = new ArrayList();
    for (Class localClass = .Gson.Types.getRawType(paramType); (localClass != null) && (!localClass.equals(Object.class)); localClass = localClass.getSuperclass())
    {
      if (localClass.isSynthetic())
        continue;
      localArrayList.add(localClass);
    }
    return localArrayList;
  }

  void visitFieldsReflectively(ObjectTypePair paramObjectTypePair, ObjectNavigator.Visitor paramVisitor)
  {
    Type localType1 = paramObjectTypePair.getMoreSpecificType();
    Object localObject = paramObjectTypePair.getObject();
    Iterator localIterator = getAllFields(localType1, paramObjectTypePair.getType()).iterator();
    while (localIterator.hasNext())
    {
      FieldAttributes localFieldAttributes = (FieldAttributes)localIterator.next();
      if ((this.exclusionStrategy.shouldSkipField(localFieldAttributes)) || (this.exclusionStrategy.shouldSkipClass(localFieldAttributes.getDeclaredClass())))
        continue;
      Type localType2 = localFieldAttributes.getResolvedType();
      if (paramVisitor.visitFieldUsingCustomHandler(localFieldAttributes, localType2, localObject))
        continue;
      if (.Gson.Types.isArray(localType2))
      {
        paramVisitor.visitArrayField(localFieldAttributes, localType2, localObject);
        continue;
      }
      paramVisitor.visitObjectField(localFieldAttributes, localType2, localObject);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ReflectingFieldNavigator
 * JD-Core Version:    0.6.0
 */