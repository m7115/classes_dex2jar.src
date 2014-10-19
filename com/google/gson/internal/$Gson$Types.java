package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types
{
  static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

  public static GenericArrayType arrayOf(Type paramType)
  {
    return new GenericArrayTypeImpl(paramType);
  }

  public static Type canonicalize(Type paramType)
  {
    if ((paramType instanceof Class))
    {
      paramType = (Class)paramType;
      if (paramType.isArray())
        paramType = new GenericArrayTypeImpl(canonicalize(paramType.getComponentType()));
    }
    do
    {
      return paramType;
      if ((paramType instanceof ParameterizedType))
      {
        ParameterizedType localParameterizedType = (ParameterizedType)paramType;
        return new ParameterizedTypeImpl(localParameterizedType.getOwnerType(), localParameterizedType.getRawType(), localParameterizedType.getActualTypeArguments());
      }
      if ((paramType instanceof GenericArrayType))
        return new GenericArrayTypeImpl(((GenericArrayType)paramType).getGenericComponentType());
    }
    while (!(paramType instanceof WildcardType));
    WildcardType localWildcardType = (WildcardType)paramType;
    return new WildcardTypeImpl(localWildcardType.getUpperBounds(), localWildcardType.getLowerBounds());
  }

  private static void checkNotPrimitive(Type paramType)
  {
    if ((!(paramType instanceof Class)) || (!((Class)paramType).isPrimitive()));
    for (boolean bool = true; ; bool = false)
    {
      .Gson.Preconditions.checkArgument(bool);
      return;
    }
  }

  private static Class<?> declaringClassOf(TypeVariable paramTypeVariable)
  {
    GenericDeclaration localGenericDeclaration = paramTypeVariable.getGenericDeclaration();
    if ((localGenericDeclaration instanceof Class))
      return (Class)localGenericDeclaration;
    return null;
  }

  static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static boolean equals(Type paramType1, Type paramType2)
  {
    int i = 1;
    int j;
    if (paramType1 == paramType2)
      j = i;
    boolean bool2;
    do
    {
      boolean bool1;
      do
      {
        while (true)
        {
          return j;
          if ((paramType1 instanceof Class))
            return paramType1.equals(paramType2);
          if ((paramType1 instanceof ParameterizedType))
          {
            boolean bool5 = paramType2 instanceof ParameterizedType;
            j = 0;
            if (!bool5)
              continue;
            ParameterizedType localParameterizedType1 = (ParameterizedType)paramType1;
            ParameterizedType localParameterizedType2 = (ParameterizedType)paramType2;
            if ((equal(localParameterizedType1.getOwnerType(), localParameterizedType2.getOwnerType())) && (localParameterizedType1.getRawType().equals(localParameterizedType2.getRawType())) && (Arrays.equals(localParameterizedType1.getActualTypeArguments(), localParameterizedType2.getActualTypeArguments())));
            while (true)
            {
              return i;
              i = 0;
            }
          }
          if ((paramType1 instanceof GenericArrayType))
          {
            boolean bool4 = paramType2 instanceof GenericArrayType;
            j = 0;
            if (!bool4)
              continue;
            GenericArrayType localGenericArrayType1 = (GenericArrayType)paramType1;
            GenericArrayType localGenericArrayType2 = (GenericArrayType)paramType2;
            return equals(localGenericArrayType1.getGenericComponentType(), localGenericArrayType2.getGenericComponentType());
          }
          if (!(paramType1 instanceof WildcardType))
            break;
          boolean bool3 = paramType2 instanceof WildcardType;
          j = 0;
          if (!bool3)
            continue;
          WildcardType localWildcardType1 = (WildcardType)paramType1;
          WildcardType localWildcardType2 = (WildcardType)paramType2;
          if ((Arrays.equals(localWildcardType1.getUpperBounds(), localWildcardType2.getUpperBounds())) && (Arrays.equals(localWildcardType1.getLowerBounds(), localWildcardType2.getLowerBounds())));
          while (true)
          {
            return i;
            i = 0;
          }
        }
        bool1 = paramType1 instanceof TypeVariable;
        j = 0;
      }
      while (!bool1);
      bool2 = paramType2 instanceof TypeVariable;
      j = 0;
    }
    while (!bool2);
    TypeVariable localTypeVariable1 = (TypeVariable)paramType1;
    TypeVariable localTypeVariable2 = (TypeVariable)paramType2;
    if ((localTypeVariable1.getGenericDeclaration() == localTypeVariable2.getGenericDeclaration()) && (localTypeVariable1.getName().equals(localTypeVariable2.getName())));
    while (true)
    {
      return i;
      i = 0;
    }
  }

  public static Type getArrayComponentType(Type paramType)
  {
    if ((paramType instanceof GenericArrayType))
      return ((GenericArrayType)paramType).getGenericComponentType();
    return ((Class)paramType).getComponentType();
  }

  public static Type getCollectionElementType(Type paramType, Class<?> paramClass)
  {
    return ((ParameterizedType)getSupertype(paramType, paramClass, java.util.Collection.class)).getActualTypeArguments()[0];
  }

  static Type getGenericSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1)
      return paramType;
    if (paramClass2.isInterface())
    {
      Class[] arrayOfClass = paramClass1.getInterfaces();
      int i = 0;
      int j = arrayOfClass.length;
      while (i < j)
      {
        if (arrayOfClass[i] == paramClass2)
          return paramClass1.getGenericInterfaces()[i];
        if (paramClass2.isAssignableFrom(arrayOfClass[i]))
          return getGenericSupertype(paramClass1.getGenericInterfaces()[i], arrayOfClass[i], paramClass2);
        i++;
      }
    }
    if (!paramClass1.isInterface())
      while (paramClass1 != Object.class)
      {
        Class localClass = paramClass1.getSuperclass();
        if (localClass == paramClass2)
          return paramClass1.getGenericSuperclass();
        if (paramClass2.isAssignableFrom(localClass))
          return getGenericSupertype(paramClass1.getGenericSuperclass(), localClass, paramClass2);
        paramClass1 = localClass;
      }
    return paramClass2;
  }

  public static Type[] getMapKeyAndValueTypes(Type paramType, Class<?> paramClass)
  {
    if (paramType == Properties.class)
      return new Type[] { String.class, String.class };
    return ((ParameterizedType)getSupertype(paramType, paramClass, Map.class)).getActualTypeArguments();
  }

  public static Class<?> getRawType(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
    {
      Type localType = ((ParameterizedType)paramType).getRawType();
      .Gson.Preconditions.checkArgument(localType instanceof Class);
      return (Class)localType;
    }
    if ((paramType instanceof GenericArrayType))
      return Array.newInstance(getRawType(((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
    if ((paramType instanceof TypeVariable))
      return Object.class;
    if ((paramType instanceof WildcardType))
      return getRawType(((WildcardType)paramType).getUpperBounds()[0]);
    if (paramType == null);
    for (String str = "null"; ; str = paramType.getClass().getName())
      throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + paramType + "> is of type " + str);
  }

  static Type getSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    .Gson.Preconditions.checkArgument(paramClass2.isAssignableFrom(paramClass1));
    return resolve(paramType, paramClass1, getGenericSupertype(paramType, paramClass1, paramClass2));
  }

  private static int hashCodeOrZero(Object paramObject)
  {
    if (paramObject != null)
      return paramObject.hashCode();
    return 0;
  }

  private static int indexOf(Object[] paramArrayOfObject, Object paramObject)
  {
    for (int i = 0; i < paramArrayOfObject.length; i++)
      if (paramObject.equals(paramArrayOfObject[i]))
        return i;
    throw new NoSuchElementException();
  }

  public static boolean isArray(Type paramType)
  {
    return ((paramType instanceof GenericArrayType)) || (((paramType instanceof Class)) && (((Class)paramType).isArray()));
  }

  public static ParameterizedType newParameterizedTypeWithOwner(Type paramType1, Type paramType2, Type[] paramArrayOfType)
  {
    return new ParameterizedTypeImpl(paramType1, paramType2, paramArrayOfType);
  }

  public static Type resolve(Type paramType1, Class<?> paramClass, Type paramType2)
  {
    Object localObject = paramType2;
    Type localType9;
    if ((localObject instanceof TypeVariable))
    {
      TypeVariable localTypeVariable = (TypeVariable)localObject;
      localType9 = resolveTypeVariable(paramType1, paramClass, localTypeVariable);
      if (localType9 == localTypeVariable)
        localObject = localType9;
    }
    label92: Type[] arrayOfType2;
    label134: label282: label343: Type localType1;
    do
    {
      do
      {
        Type[] arrayOfType1;
        Type localType2;
        do
        {
          do
          {
            Type localType4;
            int k;
            Type[] arrayOfType4;
            do
            {
              Type localType6;
              Type localType7;
              do
              {
                Class localClass;
                Type localType8;
                do
                {
                  return localObject;
                  localObject = localType9;
                  break;
                  if ((!(localObject instanceof Class)) || (!((Class)localObject).isArray()))
                    break label92;
                  localObject = (Class)localObject;
                  localClass = ((Class)localObject).getComponentType();
                  localType8 = resolve(paramType1, paramClass, localClass);
                }
                while (localClass == localType8);
                return arrayOf(localType8);
                if (!(localObject instanceof GenericArrayType))
                  break label134;
                localObject = (GenericArrayType)localObject;
                localType6 = ((GenericArrayType)localObject).getGenericComponentType();
                localType7 = resolve(paramType1, paramClass, localType6);
              }
              while (localType6 == localType7);
              return arrayOf(localType7);
              if (!(localObject instanceof ParameterizedType))
                break label282;
              localObject = (ParameterizedType)localObject;
              Type localType3 = ((ParameterizedType)localObject).getOwnerType();
              localType4 = resolve(paramType1, paramClass, localType3);
              if (localType4 != localType3);
              for (int i = 1; ; i = 0)
              {
                Type[] arrayOfType3 = ((ParameterizedType)localObject).getActualTypeArguments();
                int j = arrayOfType3.length;
                k = i;
                arrayOfType4 = arrayOfType3;
                for (int m = 0; m < j; m++)
                {
                  Type localType5 = resolve(paramType1, paramClass, arrayOfType4[m]);
                  if (localType5 == arrayOfType4[m])
                    continue;
                  if (k == 0)
                  {
                    arrayOfType4 = (Type[])arrayOfType4.clone();
                    k = 1;
                  }
                  arrayOfType4[m] = localType5;
                }
              }
            }
            while (k == 0);
            return newParameterizedTypeWithOwner(localType4, ((ParameterizedType)localObject).getRawType(), arrayOfType4);
          }
          while (!(localObject instanceof WildcardType));
          localObject = (WildcardType)localObject;
          arrayOfType1 = ((WildcardType)localObject).getLowerBounds();
          arrayOfType2 = ((WildcardType)localObject).getUpperBounds();
          if (arrayOfType1.length != 1)
            break label343;
          localType2 = resolve(paramType1, paramClass, arrayOfType1[0]);
        }
        while (localType2 == arrayOfType1[0]);
        return supertypeOf(localType2);
      }
      while (arrayOfType2.length != 1);
      localType1 = resolve(paramType1, paramClass, arrayOfType2[0]);
    }
    while (localType1 == arrayOfType2[0]);
    return (Type)subtypeOf(localType1);
  }

  static Type resolveTypeVariable(Type paramType, Class<?> paramClass, TypeVariable paramTypeVariable)
  {
    Class localClass = declaringClassOf(paramTypeVariable);
    if (localClass == null);
    Type localType;
    do
    {
      return paramTypeVariable;
      localType = getGenericSupertype(paramType, paramClass, localClass);
    }
    while (!(localType instanceof ParameterizedType));
    int i = indexOf(localClass.getTypeParameters(), paramTypeVariable);
    return ((ParameterizedType)localType).getActualTypeArguments()[i];
  }

  public static WildcardType subtypeOf(Type paramType)
  {
    return new WildcardTypeImpl(new Type[] { paramType }, EMPTY_TYPE_ARRAY);
  }

  public static WildcardType supertypeOf(Type paramType)
  {
    return new WildcardTypeImpl(new Type[] { Object.class }, new Type[] { paramType });
  }

  public static String typeToString(Type paramType)
  {
    if ((paramType instanceof Class))
      return ((Class)paramType).getName();
    return paramType.toString();
  }

  private static final class GenericArrayTypeImpl
    implements Serializable, GenericArrayType
  {
    private static final long serialVersionUID;
    private final Type componentType;

    public GenericArrayTypeImpl(Type paramType)
    {
      this.componentType = .Gson.Types.canonicalize(paramType);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof GenericArrayType)) && (.Gson.Types.equals(this, (GenericArrayType)paramObject));
    }

    public Type getGenericComponentType()
    {
      return this.componentType;
    }

    public int hashCode()
    {
      return this.componentType.hashCode();
    }

    public String toString()
    {
      return .Gson.Types.typeToString(this.componentType) + "[]";
    }
  }

  private static final class ParameterizedTypeImpl
    implements Serializable, ParameterizedType
  {
    private static final long serialVersionUID;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Type paramType1, Type paramType2, Type[] paramArrayOfType)
    {
      boolean bool2;
      if ((paramType2 instanceof Class))
      {
        Class localClass = (Class)paramType2;
        if ((paramType1 != null) || (localClass.getEnclosingClass() == null))
        {
          bool2 = bool1;
          .Gson.Preconditions.checkArgument(bool2);
          if ((paramType1 != null) && (localClass.getEnclosingClass() == null))
            break label153;
          label56: .Gson.Preconditions.checkArgument(bool1);
        }
      }
      else
      {
        if (paramType1 != null)
          break label159;
      }
      label153: label159: for (Type localType = null; ; localType = .Gson.Types.canonicalize(paramType1))
      {
        this.ownerType = localType;
        this.rawType = .Gson.Types.canonicalize(paramType2);
        this.typeArguments = ((Type[])paramArrayOfType.clone());
        while (i < this.typeArguments.length)
        {
          .Gson.Preconditions.checkNotNull(this.typeArguments[i]);
          .Gson.Types.access$000(this.typeArguments[i]);
          this.typeArguments[i] = .Gson.Types.canonicalize(this.typeArguments[i]);
          i++;
        }
        bool2 = false;
        break;
        bool1 = false;
        break label56;
      }
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof ParameterizedType)) && (.Gson.Types.equals(this, (ParameterizedType)paramObject));
    }

    public Type[] getActualTypeArguments()
    {
      return (Type[])this.typeArguments.clone();
    }

    public Type getOwnerType()
    {
      return this.ownerType;
    }

    public Type getRawType()
    {
      return this.rawType;
    }

    public int hashCode()
    {
      return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ .Gson.Types.access$100(this.ownerType);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(30 * (1 + this.typeArguments.length));
      localStringBuilder.append(.Gson.Types.typeToString(this.rawType));
      if (this.typeArguments.length == 0)
        return localStringBuilder.toString();
      localStringBuilder.append("<").append(.Gson.Types.typeToString(this.typeArguments[0]));
      for (int i = 1; i < this.typeArguments.length; i++)
        localStringBuilder.append(", ").append(.Gson.Types.typeToString(this.typeArguments[i]));
      return ">";
    }
  }

  private static final class WildcardTypeImpl
    implements Serializable, WildcardType
  {
    private static final long serialVersionUID;
    private final Type lowerBound;
    private final Type upperBound;

    public WildcardTypeImpl(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      if (paramArrayOfType2.length <= i)
      {
        int k = i;
        .Gson.Preconditions.checkArgument(k);
        if (paramArrayOfType1.length != i)
          break label88;
        int n = i;
        label29: .Gson.Preconditions.checkArgument(n);
        if (paramArrayOfType2.length != i)
          break label99;
        .Gson.Preconditions.checkNotNull(paramArrayOfType2[0]);
        .Gson.Types.access$000(paramArrayOfType2[0]);
        if (paramArrayOfType1[0] != Object.class)
          break label94;
      }
      while (true)
      {
        .Gson.Preconditions.checkArgument(i);
        this.lowerBound = .Gson.Types.canonicalize(paramArrayOfType2[0]);
        this.upperBound = Object.class;
        return;
        int m = 0;
        break;
        label88: int i1 = 0;
        break label29;
        label94: int j = 0;
      }
      label99: .Gson.Preconditions.checkNotNull(paramArrayOfType1[0]);
      .Gson.Types.access$000(paramArrayOfType1[0]);
      this.lowerBound = null;
      this.upperBound = .Gson.Types.canonicalize(paramArrayOfType1[0]);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof WildcardType)) && (.Gson.Types.equals(this, (WildcardType)paramObject));
    }

    public Type[] getLowerBounds()
    {
      if (this.lowerBound != null)
      {
        Type[] arrayOfType = new Type[1];
        arrayOfType[0] = this.lowerBound;
        return arrayOfType;
      }
      return .Gson.Types.EMPTY_TYPE_ARRAY;
    }

    public Type[] getUpperBounds()
    {
      Type[] arrayOfType = new Type[1];
      arrayOfType[0] = this.upperBound;
      return arrayOfType;
    }

    public int hashCode()
    {
      if (this.lowerBound != null);
      for (int i = 31 + this.lowerBound.hashCode(); ; i = 1)
        return i ^ 31 + this.upperBound.hashCode();
    }

    public String toString()
    {
      if (this.lowerBound != null)
        return "? super " + .Gson.Types.typeToString(this.lowerBound);
      if (this.upperBound == Object.class)
        return "?";
      return "? extends " + .Gson.Types.typeToString(this.upperBound);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.internal..Gson.Types
 * JD-Core Version:    0.6.0
 */