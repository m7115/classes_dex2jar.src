package cn.suanya.synl;

import cn.suanya.synl.internal.ClassCache;
import cn.suanya.synl.internal.ClassCacheImpl;
import cn.suanya.synl.og.ClassCacheInspector;
import cn.suanya.synl.og.EvaluationPool;
import cn.suanya.synl.og.IntHashMap;
import cn.suanya.synl.og.NoSuchPropertyException;
import cn.suanya.synl.og.NullHandler;
import cn.suanya.synl.og.ObjectArrayPool;
import cn.suanya.synl.og.OgnlException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class OgnlRuntime
{
  private static final String GET_PREFIX = "get";
  private static final int HEX_LENGTH = 8;
  private static final Map HEX_PADDING;
  public static int INDEXED_PROPERTY_INT = 0;
  public static int INDEXED_PROPERTY_NONE = 0;
  public static int INDEXED_PROPERTY_OBJECT = 0;
  private static final String IS_PREFIX = "is";
  private static final String NULL_OBJECT_STRING = "<null>";
  public static final String NULL_STRING = "";
  private static final Map NUMERIC_CASTS;
  private static final Map NUMERIC_DEFAULTS;
  private static final Map NUMERIC_LITERALS;
  private static final Map NUMERIC_VALUES;
  public static final Class[] NoArgumentTypes;
  public static final Object[] NoArguments;
  public static final Object NoConversionPossible;
  public static final Object NotFound = new Object();
  public static final List NotFoundList = new ArrayList();
  public static final Map NotFoundMap = new HashMap();
  private static IdentityHashMap PRIMITIVE_WRAPPER_CLASSES;
  private static final String SET_PREFIX = "set";
  static ClassCacheInspector _cacheInspector;
  static final ClassCache _constructorCache;
  static final Map _ctorParameterTypesCache;
  static final ClassCache[] _declaredMethods;
  static final ClassCache _elementsAccessors;
  static final EvaluationPool _evaluationPool;
  static final ClassCache _fieldCache;
  static final Map _genericMethodParameterTypesCache;
  static final ClassCache _instanceMethodCache;
  static final ClassCache _invokePermissionCache;
  private static boolean _jdk15;
  private static boolean _jdkChecked;
  static final IntHashMap _methodAccessCache;
  static final ClassCache _methodAccessors;
  static final Map _methodParameterTypesCache;
  static final IntHashMap _methodPermCache;
  static final ClassCache _nullHandlers;
  static final ObjectArrayPool _objectArrayPool;
  static final ClassCache _primitiveDefaults;
  static final Map _primitiveTypes;
  static final ClassCache _propertyAccessors;
  static final ClassCache _propertyDescriptorCache;
  static SecurityManager _securityManager;
  static final ClassCache _staticMethodCache;
  static final List _superclasses;
  static final Map cacheGetMethod;
  static final Map cacheSetMethod;

  static
  {
    NoArguments = new Object[0];
    NoArgumentTypes = new Class[0];
    NoConversionPossible = "ognl.NoConversionPossible";
    INDEXED_PROPERTY_NONE = 0;
    INDEXED_PROPERTY_INT = 1;
    INDEXED_PROPERTY_OBJECT = 2;
    HEX_PADDING = new HashMap();
    _jdk15 = false;
    _jdkChecked = false;
    _methodAccessors = new ClassCacheImpl();
    _propertyAccessors = new ClassCacheImpl();
    _elementsAccessors = new ClassCacheImpl();
    _nullHandlers = new ClassCacheImpl();
    _propertyDescriptorCache = new ClassCacheImpl();
    _constructorCache = new ClassCacheImpl();
    _staticMethodCache = new ClassCacheImpl();
    _instanceMethodCache = new ClassCacheImpl();
    _invokePermissionCache = new ClassCacheImpl();
    _fieldCache = new ClassCacheImpl();
    _superclasses = new ArrayList();
    ClassCache[] arrayOfClassCache = new ClassCache[2];
    arrayOfClassCache[0] = new ClassCacheImpl();
    arrayOfClassCache[1] = new ClassCacheImpl();
    _declaredMethods = arrayOfClassCache;
    _primitiveTypes = new HashMap(101);
    _primitiveDefaults = new ClassCacheImpl();
    _methodParameterTypesCache = new HashMap(101);
    _genericMethodParameterTypesCache = new HashMap(101);
    _ctorParameterTypesCache = new HashMap(101);
    _securityManager = System.getSecurityManager();
    _evaluationPool = new EvaluationPool();
    _objectArrayPool = new ObjectArrayPool();
    _methodAccessCache = new IntHashMap();
    _methodPermCache = new IntHashMap();
    cacheSetMethod = new HashMap();
    cacheGetMethod = new HashMap();
    PRIMITIVE_WRAPPER_CLASSES = new IdentityHashMap();
    PRIMITIVE_WRAPPER_CLASSES.put(Boolean.TYPE, Boolean.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Boolean.class, Boolean.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Byte.TYPE, Byte.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Byte.class, Byte.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Character.TYPE, Character.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Character.class, Character.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Short.TYPE, Short.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Short.class, Short.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Integer.TYPE, Integer.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Integer.class, Integer.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Long.TYPE, Long.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Long.class, Long.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Float.TYPE, Float.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Float.class, Float.TYPE);
    PRIMITIVE_WRAPPER_CLASSES.put(Double.TYPE, Double.class);
    PRIMITIVE_WRAPPER_CLASSES.put(Double.class, Double.TYPE);
    NUMERIC_CASTS = new HashMap();
    NUMERIC_CASTS.put(Double.class, "(double)");
    NUMERIC_CASTS.put(Float.class, "(float)");
    NUMERIC_CASTS.put(Integer.class, "(int)");
    NUMERIC_CASTS.put(Long.class, "(long)");
    NUMERIC_CASTS.put(BigDecimal.class, "(double)");
    NUMERIC_CASTS.put(BigInteger.class, "");
    NUMERIC_VALUES = new HashMap();
    NUMERIC_VALUES.put(Double.class, "doubleValue()");
    NUMERIC_VALUES.put(Float.class, "floatValue()");
    NUMERIC_VALUES.put(Integer.class, "intValue()");
    NUMERIC_VALUES.put(Long.class, "longValue()");
    NUMERIC_VALUES.put(Short.class, "shortValue()");
    NUMERIC_VALUES.put(Byte.class, "byteValue()");
    NUMERIC_VALUES.put(BigDecimal.class, "doubleValue()");
    NUMERIC_VALUES.put(BigInteger.class, "doubleValue()");
    NUMERIC_VALUES.put(Boolean.class, "booleanValue()");
    NUMERIC_LITERALS = new HashMap();
    NUMERIC_LITERALS.put(Integer.class, "");
    NUMERIC_LITERALS.put(Integer.TYPE, "");
    NUMERIC_LITERALS.put(Long.class, "l");
    NUMERIC_LITERALS.put(Long.TYPE, "l");
    NUMERIC_LITERALS.put(BigInteger.class, "d");
    NUMERIC_LITERALS.put(Float.class, "f");
    NUMERIC_LITERALS.put(Float.TYPE, "f");
    NUMERIC_LITERALS.put(Double.class, "d");
    NUMERIC_LITERALS.put(Double.TYPE, "d");
    NUMERIC_LITERALS.put(BigInteger.class, "d");
    NUMERIC_LITERALS.put(BigDecimal.class, "d");
    NUMERIC_DEFAULTS = new HashMap();
    NUMERIC_DEFAULTS.put(Boolean.class, Boolean.FALSE);
    NUMERIC_DEFAULTS.put(Byte.class, new Byte(0));
    NUMERIC_DEFAULTS.put(Short.class, new Short(0));
    NUMERIC_DEFAULTS.put(Character.class, new Character('\000'));
    NUMERIC_DEFAULTS.put(Integer.class, new Integer(0));
    NUMERIC_DEFAULTS.put(Long.class, new Long(0L));
    NUMERIC_DEFAULTS.put(Float.class, new Float(0.0F));
    NUMERIC_DEFAULTS.put(Double.class, new Double(0.0D));
    NUMERIC_DEFAULTS.put(BigInteger.class, new BigInteger("0"));
    NUMERIC_DEFAULTS.put(BigDecimal.class, new BigDecimal(0.0D));
    _primitiveTypes.put("boolean", Boolean.TYPE);
    _primitiveTypes.put("byte", Byte.TYPE);
    _primitiveTypes.put("short", Short.TYPE);
    _primitiveTypes.put("char", Character.TYPE);
    _primitiveTypes.put("int", Integer.TYPE);
    _primitiveTypes.put("long", Long.TYPE);
    _primitiveTypes.put("float", Float.TYPE);
    _primitiveTypes.put("double", Double.TYPE);
    _primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
    _primitiveDefaults.put(Boolean.class, Boolean.FALSE);
    _primitiveDefaults.put(Byte.TYPE, new Byte(0));
    _primitiveDefaults.put(Byte.class, new Byte(0));
    _primitiveDefaults.put(Short.TYPE, new Short(0));
    _primitiveDefaults.put(Short.class, new Short(0));
    _primitiveDefaults.put(Character.TYPE, new Character('\000'));
    _primitiveDefaults.put(Integer.TYPE, new Integer(0));
    _primitiveDefaults.put(Long.TYPE, new Long(0L));
    _primitiveDefaults.put(Float.TYPE, new Float(0.0F));
    _primitiveDefaults.put(Double.TYPE, new Double(0.0D));
    _primitiveDefaults.put(BigInteger.class, new BigInteger("0"));
    _primitiveDefaults.put(BigDecimal.class, new BigDecimal(0.0D));
  }

  private static Method _getGetMethod(Object paramObject, Class paramClass, String paramString)
    throws OgnlException
  {
    List localList = getDeclaredMethods(paramClass, paramString, false);
    if (localList != null)
    {
      int i = localList.size();
      for (int j = 0; j < i; j++)
      {
        Method localMethod = (Method)localList.get(j);
        if (findParameterTypes(paramClass, localMethod).length == 0)
          return localMethod;
      }
    }
    return null;
  }

  private static Method _getSetMethod(Object paramObject, Class paramClass, String paramString)
    throws OgnlException
  {
    List localList = getDeclaredMethods(paramClass, paramString, true);
    if (localList != null)
    {
      int i = localList.size();
      for (int j = 0; j < i; j++)
      {
        Method localMethod = (Method)localList.get(j);
        if (findParameterTypes(paramClass, localMethod).length == 1)
          return localMethod;
      }
    }
    return null;
  }

  public static boolean areArgsCompatible(Object[] paramArrayOfObject, Class[] paramArrayOfClass)
  {
    return areArgsCompatible(paramArrayOfObject, paramArrayOfClass, null);
  }

  public static boolean areArgsCompatible(Object[] paramArrayOfObject, Class[] paramArrayOfClass, Method paramMethod)
  {
    int i;
    int j;
    if ((paramMethod != null) && (isJdk15()) && (paramMethod.isVarArgs()))
    {
      i = 1;
      if (paramArrayOfObject.length == paramArrayOfClass.length)
        break label41;
      j = 0;
      if (i != 0)
        break label41;
    }
    while (true)
    {
      return j;
      i = 0;
      break;
      label41: if (i != 0)
      {
        int n = paramArrayOfObject.length;
        j = 1;
        int i1 = 0;
        while ((j != 0) && (i1 < n) && (i1 < paramArrayOfClass.length))
        {
          boolean bool2 = isTypeCompatible(paramArrayOfObject[i1], paramArrayOfClass[i1]);
          if ((!bool2) && (paramArrayOfClass[i1].isArray()))
            bool2 = isTypeCompatible(paramArrayOfObject[i1], paramArrayOfClass[i1].getComponentType());
          i1++;
          j = bool2;
        }
        continue;
      }
      int k = paramArrayOfObject.length;
      int m = 0;
      boolean bool1;
      for (j = 1; (j != 0) && (m < k); j = bool1)
      {
        bool1 = isTypeCompatible(paramArrayOfObject[m], paramArrayOfClass[m]);
        m++;
      }
    }
  }

  // ERROR //
  public static Object callAppropriateMethod(Object paramObject, String paramString, List paramList, Object[] paramArrayOfObject)
    throws Exception
  {
    // Byte code:
    //   0: iconst_m1
    //   1: istore 4
    //   3: iconst_0
    //   4: istore 5
    //   6: aload_0
    //   7: invokevirtual 377	java/lang/Object:getClass	()Ljava/lang/Class;
    //   10: invokevirtual 381	java/lang/Class:getName	()Ljava/lang/String;
    //   13: astore 6
    //   15: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   18: aload_3
    //   19: arraylength
    //   20: invokevirtual 385	cn/suanya/synl/og/ObjectArrayPool:create	(I)[Ljava/lang/Object;
    //   23: astore 7
    //   25: aload_0
    //   26: aload_1
    //   27: aload_2
    //   28: aload_3
    //   29: aload 7
    //   31: invokestatic 389	cn/suanya/synl/OgnlRuntime:getAppropriateMethod	(Ljava/lang/Object;Ljava/lang/String;Ljava/util/List;[Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/reflect/Method;
    //   34: astore 12
    //   36: aload 12
    //   38: ifnonnull +103 -> 141
    //   41: aload_3
    //   42: arraylength
    //   43: istore 21
    //   45: aload 7
    //   47: arraylength
    //   48: istore 4
    //   50: new 370	java/lang/NoSuchMethodException
    //   53: dup
    //   54: new 391	java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial 392	java/lang/StringBuilder:<init>	()V
    //   61: aload 6
    //   63: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: ldc_w 398
    //   69: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_1
    //   73: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc_w 400
    //   79: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: iload 21
    //   84: invokevirtual 403	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   87: ldc_w 405
    //   90: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: iload 4
    //   95: invokevirtual 403	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   98: ldc 26
    //   100: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 408	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: invokespecial 409	java/lang/NoSuchMethodException:<init>	(Ljava/lang/String;)V
    //   109: athrow
    //   110: astore 11
    //   112: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   115: aload 7
    //   117: invokevirtual 413	cn/suanya/synl/og/ObjectArrayPool:recycle	([Ljava/lang/Object;)V
    //   120: new 415	cn/suanya/synl/og/MethodFailedException
    //   123: dup
    //   124: aload_0
    //   125: aload_1
    //   126: aload 11
    //   128: invokespecial 418	cn/suanya/synl/og/MethodFailedException:<init>	(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   131: athrow
    //   132: astore 20
    //   134: iload 4
    //   136: istore 21
    //   138: goto -93 -> 45
    //   141: invokestatic 350	cn/suanya/synl/OgnlRuntime:isJdk15	()Z
    //   144: ifeq +214 -> 358
    //   147: aload 12
    //   149: invokevirtual 353	java/lang/reflect/Method:isVarArgs	()Z
    //   152: ifeq +206 -> 358
    //   155: aload 12
    //   157: invokevirtual 422	java/lang/reflect/Method:getParameterTypes	()[Ljava/lang/Class;
    //   160: astore 15
    //   162: iload 5
    //   164: aload 15
    //   166: arraylength
    //   167: if_icmpge +191 -> 358
    //   170: aload 15
    //   172: iload 5
    //   174: aaload
    //   175: invokevirtual 360	java/lang/Class:isArray	()Z
    //   178: ifeq +119 -> 297
    //   181: iload 5
    //   183: iconst_1
    //   184: iadd
    //   185: anewarray 4	java/lang/Object
    //   188: astore 13
    //   190: aload 7
    //   192: iconst_0
    //   193: aload 13
    //   195: iconst_0
    //   196: aload 13
    //   198: arraylength
    //   199: invokestatic 426	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   202: aload 7
    //   204: arraylength
    //   205: iload 5
    //   207: if_icmple +81 -> 288
    //   210: new 89	java/util/ArrayList
    //   213: dup
    //   214: invokespecial 90	java/util/ArrayList:<init>	()V
    //   217: astore 16
    //   219: iload 5
    //   221: istore 17
    //   223: iload 17
    //   225: aload 7
    //   227: arraylength
    //   228: if_icmpge +25 -> 253
    //   231: aload 7
    //   233: iload 17
    //   235: aaload
    //   236: ifnull +129 -> 365
    //   239: aload 16
    //   241: aload 7
    //   243: iload 17
    //   245: aaload
    //   246: invokevirtual 430	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   249: pop
    //   250: goto +115 -> 365
    //   253: aload 16
    //   255: invokevirtual 434	java/util/ArrayList:toArray	()[Ljava/lang/Object;
    //   258: astore 18
    //   260: aload 13
    //   262: iload 5
    //   264: aload 18
    //   266: aastore
    //   267: aload_0
    //   268: aload 12
    //   270: aload 13
    //   272: invokestatic 438	cn/suanya/synl/OgnlRuntime:invokeMethod	(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
    //   275: astore 14
    //   277: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   280: aload 7
    //   282: invokevirtual 413	cn/suanya/synl/og/ObjectArrayPool:recycle	([Ljava/lang/Object;)V
    //   285: aload 14
    //   287: areturn
    //   288: iconst_0
    //   289: anewarray 4	java/lang/Object
    //   292: astore 18
    //   294: goto -34 -> 260
    //   297: iinc 5 1
    //   300: goto -138 -> 162
    //   303: astore 11
    //   305: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   308: aload 7
    //   310: invokevirtual 413	cn/suanya/synl/og/ObjectArrayPool:recycle	([Ljava/lang/Object;)V
    //   313: goto -193 -> 120
    //   316: astore 9
    //   318: aload 9
    //   320: invokevirtual 442	java/lang/reflect/InvocationTargetException:getTargetException	()Ljava/lang/Throwable;
    //   323: astore 10
    //   325: aload 10
    //   327: astore 11
    //   329: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   332: aload 7
    //   334: invokevirtual 413	cn/suanya/synl/og/ObjectArrayPool:recycle	([Ljava/lang/Object;)V
    //   337: goto -217 -> 120
    //   340: astore 8
    //   342: getstatic 179	cn/suanya/synl/OgnlRuntime:_objectArrayPool	Lcn/suanya/synl/og/ObjectArrayPool;
    //   345: aload 7
    //   347: invokevirtual 413	cn/suanya/synl/og/ObjectArrayPool:recycle	([Ljava/lang/Object;)V
    //   350: aload 8
    //   352: athrow
    //   353: astore 22
    //   355: goto -305 -> 50
    //   358: aload 7
    //   360: astore 13
    //   362: goto -95 -> 267
    //   365: iinc 17 1
    //   368: goto -145 -> 223
    //
    // Exception table:
    //   from	to	target	type
    //   25	36	110	java/lang/NoSuchMethodException
    //   41	45	110	java/lang/NoSuchMethodException
    //   45	50	110	java/lang/NoSuchMethodException
    //   50	110	110	java/lang/NoSuchMethodException
    //   141	162	110	java/lang/NoSuchMethodException
    //   162	219	110	java/lang/NoSuchMethodException
    //   223	250	110	java/lang/NoSuchMethodException
    //   253	260	110	java/lang/NoSuchMethodException
    //   260	267	110	java/lang/NoSuchMethodException
    //   267	277	110	java/lang/NoSuchMethodException
    //   288	294	110	java/lang/NoSuchMethodException
    //   41	45	132	java/lang/Exception
    //   25	36	303	java/lang/IllegalAccessException
    //   41	45	303	java/lang/IllegalAccessException
    //   45	50	303	java/lang/IllegalAccessException
    //   50	110	303	java/lang/IllegalAccessException
    //   141	162	303	java/lang/IllegalAccessException
    //   162	219	303	java/lang/IllegalAccessException
    //   223	250	303	java/lang/IllegalAccessException
    //   253	260	303	java/lang/IllegalAccessException
    //   260	267	303	java/lang/IllegalAccessException
    //   267	277	303	java/lang/IllegalAccessException
    //   288	294	303	java/lang/IllegalAccessException
    //   25	36	316	java/lang/reflect/InvocationTargetException
    //   41	45	316	java/lang/reflect/InvocationTargetException
    //   45	50	316	java/lang/reflect/InvocationTargetException
    //   50	110	316	java/lang/reflect/InvocationTargetException
    //   141	162	316	java/lang/reflect/InvocationTargetException
    //   162	219	316	java/lang/reflect/InvocationTargetException
    //   223	250	316	java/lang/reflect/InvocationTargetException
    //   253	260	316	java/lang/reflect/InvocationTargetException
    //   260	267	316	java/lang/reflect/InvocationTargetException
    //   267	277	316	java/lang/reflect/InvocationTargetException
    //   288	294	316	java/lang/reflect/InvocationTargetException
    //   25	36	340	finally
    //   41	45	340	finally
    //   45	50	340	finally
    //   50	110	340	finally
    //   141	162	340	finally
    //   162	219	340	finally
    //   223	250	340	finally
    //   253	260	340	finally
    //   260	267	340	finally
    //   267	277	340	finally
    //   288	294	340	finally
    //   318	325	340	finally
    //   45	50	353	java/lang/Exception
  }

  public static void clearCache()
  {
    _methodParameterTypesCache.clear();
    _ctorParameterTypesCache.clear();
    _propertyDescriptorCache.clear();
    _constructorCache.clear();
    _staticMethodCache.clear();
    _instanceMethodCache.clear();
    _invokePermissionCache.clear();
    _fieldCache.clear();
    _superclasses.clear();
    _declaredMethods[0].clear();
    _declaredMethods[1].clear();
    _methodAccessCache.clear();
  }

  static Method findClosestMatchingMethod(Class paramClass1, Method paramMethod, String paramString, Class paramClass2, boolean paramBoolean)
  {
    int i = 0;
    boolean bool;
    List localList;
    if (!paramBoolean)
    {
      bool = true;
      localList = getDeclaredMethods(paramClass1, paramString, bool);
    }
    while (true)
    {
      if (i >= localList.size())
        break label113;
      Method localMethod = (Method)localList.get(i);
      if ((localMethod.getName().equals(paramMethod.getName())) && (paramMethod.getReturnType().isAssignableFrom(paramMethod.getReturnType())) && (localMethod.getReturnType() == paramClass2) && (localMethod.getParameterTypes().length == paramMethod.getParameterTypes().length))
      {
        return localMethod;
        bool = false;
        break;
      }
      i++;
    }
    label113: return paramMethod;
  }

  public static Class[] findParameterTypes(Class paramClass, Method paramMethod)
  {
    int i = 0;
    Class[] arrayOfClass1;
    if (paramClass == null)
      arrayOfClass1 = getParameterTypes(paramMethod);
    do
    {
      return arrayOfClass1;
      if ((!isJdk15()) || (paramClass.getGenericSuperclass() == null) || (!ParameterizedType.class.isInstance(paramClass.getGenericSuperclass())) || (paramMethod.getDeclaringClass().getTypeParameters() == null))
        return getParameterTypes(paramMethod);
      arrayOfClass1 = (Class[])(Class[])_genericMethodParameterTypesCache.get(paramMethod);
    }
    while ((arrayOfClass1 != null) && (Arrays.equals(arrayOfClass1, ((ParameterizedType)paramClass.getGenericSuperclass()).getActualTypeArguments())));
    while (true)
    {
      Type[] arrayOfType;
      Class[] arrayOfClass2;
      synchronized (_genericMethodParameterTypesCache)
      {
        ParameterizedType localParameterizedType = (ParameterizedType)paramClass.getGenericSuperclass();
        arrayOfType = paramMethod.getGenericParameterTypes();
        TypeVariable[] arrayOfTypeVariable = paramMethod.getDeclaringClass().getTypeParameters();
        arrayOfClass2 = new Class[arrayOfType.length];
        if (i >= arrayOfType.length)
          break label333;
        if (!TypeVariable.class.isInstance(arrayOfType[i]))
          continue;
        localTypeVariable = (TypeVariable)arrayOfType[i];
        Class localClass = resolveType(localParameterizedType, localTypeVariable, arrayOfTypeVariable);
        if (localClass == null)
          break label320;
        if (!GenericArrayType.class.isInstance(arrayOfType[i]))
          continue;
        localClass = Array.newInstance(localClass, 0).getClass();
        arrayOfClass2[i] = localClass;
        break label351;
        if (!GenericArrayType.class.isInstance(arrayOfType[i]))
          continue;
        localTypeVariable = (TypeVariable)((GenericArrayType)arrayOfType[i]).getGenericComponentType();
        continue;
        if (ParameterizedType.class.isInstance(arrayOfType[i]))
          arrayOfClass2[i] = ((Class)((ParameterizedType)arrayOfType[i]).getRawType());
      }
      boolean bool = Class.class.isInstance(arrayOfType[i]);
      TypeVariable localTypeVariable = null;
      if (!bool)
        continue;
      arrayOfClass2[i] = ((Class)arrayOfType[i]);
      break label351;
      label320: arrayOfClass2[i] = paramMethod.getParameterTypes()[i];
      break label351;
      label333: _genericMethodParameterTypesCache.put(paramMethod, arrayOfClass2);
      monitorexit;
      return arrayOfClass2;
      label351: i++;
    }
  }

  static Class findType(Type[] paramArrayOfType, Class paramClass)
  {
    for (int i = 0; i < paramArrayOfType.length; i++)
      if ((Class.class.isInstance(paramArrayOfType[i])) && (paramClass.isAssignableFrom((Class)paramArrayOfType[i])))
        return (Class)paramArrayOfType[i];
    return null;
  }

  public static Method getAppropriateMethod(Object paramObject, String paramString, List paramList, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    Object localObject3;
    Method localMethod;
    Class[] arrayOfClass;
    Object localObject4;
    Object localObject5;
    if (paramList != null)
    {
      int i = paramList.size();
      int j = 0;
      Object localObject2 = null;
      localObject3 = null;
      while (j < i)
      {
        localMethod = (Method)paramList.get(j);
        if (paramObject != null);
        for (Class localClass1 = paramObject.getClass(); ; localClass1 = null)
        {
          arrayOfClass = findParameterTypes(localClass1, localMethod);
          if ((!areArgsCompatible(paramArrayOfObject1, arrayOfClass, localMethod)) || ((localObject3 != null) && (!isMoreSpecific(arrayOfClass, localObject2))))
            break;
          System.arraycopy(paramArrayOfObject1, 0, paramArrayOfObject2, 0, paramArrayOfObject1.length);
          for (int k = 0; k < arrayOfClass.length; k++)
          {
            Class localClass2 = arrayOfClass[k];
            if ((!localClass2.isPrimitive()) || (paramArrayOfObject2[k] != null))
              continue;
            paramArrayOfObject2[k] = OgnlOps.convertValue(paramArrayOfObject1[k], localClass2);
          }
        }
        localObject4 = localObject2;
        localObject5 = localObject3;
        j++;
        localObject3 = localObject5;
        localObject2 = localObject4;
      }
    }
    for (Object localObject1 = localObject3; ; localObject1 = null)
    {
      if (localObject1 == null)
        localObject1 = getConvertedMethodAndArgs(paramObject, paramString, paramList, paramArrayOfObject1, paramArrayOfObject2);
      return localObject1;
      localObject5 = localMethod;
      localObject4 = arrayOfClass;
      break;
    }
  }

  public static final Class getArgClass(Object paramObject)
  {
    Class localClass;
    if (paramObject == null)
      localClass = null;
    do
      while (true)
      {
        return localClass;
        localClass = paramObject.getClass();
        if (localClass == Boolean.class)
          return Boolean.TYPE;
        if (localClass.getSuperclass() != Number.class)
          break;
        if (localClass == Integer.class)
          return Integer.TYPE;
        if (localClass == Double.class)
          return Double.TYPE;
        if (localClass == Byte.class)
          return Byte.TYPE;
        if (localClass == Long.class)
          return Long.TYPE;
        if (localClass == Float.class)
          return Float.TYPE;
        if (localClass == Short.class)
          return Short.TYPE;
      }
    while (localClass != Character.class);
    return Character.TYPE;
  }

  public static String getBaseName(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return getClassBaseName(paramObject.getClass());
  }

  public static String getClassBaseName(Class paramClass)
  {
    String str = paramClass.getName();
    return str.substring(1 + str.lastIndexOf('.'));
  }

  public static String getClassName(Class paramClass, boolean paramBoolean)
  {
    if (paramBoolean)
      return paramClass.getName();
    return getClassBaseName(paramClass);
  }

  public static String getClassName(Object paramObject, boolean paramBoolean)
  {
    if (!(paramObject instanceof Class));
    for (Object localObject = paramObject.getClass(); ; localObject = paramObject)
      return getClassName((Class)localObject, paramBoolean);
  }

  public static String getClassPackageName(Class paramClass)
  {
    String str = paramClass.getName();
    int i = str.lastIndexOf('.');
    if (i < 0)
      return null;
    return str.substring(0, i);
  }

  public static List getConstructors(Class paramClass)
  {
    List localList1 = (List)_constructorCache.get(paramClass);
    if (localList1 == null)
      synchronized (_constructorCache)
      {
        List localList2 = (List)_constructorCache.get(paramClass);
        if (localList2 == null)
        {
          ClassCache localClassCache2 = _constructorCache;
          localList2 = Arrays.asList(paramClass.getConstructors());
          localClassCache2.put(paramClass, localList2);
        }
        return localList2;
      }
    return localList1;
  }

  public static Method getConvertedMethodAndArgs(Object paramObject, String paramString, List paramList, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int j;
    Object localObject1;
    Object localObject2;
    Class localClass;
    if (paramList != null)
    {
      int i = paramList.size();
      j = 0;
      localObject1 = null;
      if ((localObject1 != null) || (j >= i))
        break label92;
      localObject2 = (Method)paramList.get(j);
      if (paramObject != null)
      {
        localClass = paramObject.getClass();
        label53: if (!getConvertedTypes(paramObject, (Member)localObject2, paramString, findParameterTypes(localClass, (Method)localObject2), paramArrayOfObject1, paramArrayOfObject2))
          break label95;
      }
    }
    while (true)
    {
      j++;
      localObject1 = localObject2;
      break;
      localClass = null;
      break label53;
      localObject1 = null;
      label92: return localObject1;
      label95: localObject2 = localObject1;
    }
  }

  public static Object getConvertedType(Object paramObject1, Member paramMember, String paramString, Object paramObject2, Class paramClass)
  {
    return OgnlOps.convertValue(paramObject2, paramClass);
  }

  public static boolean getConvertedTypes(Object paramObject, Member paramMember, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    int i;
    if (paramArrayOfClass.length == paramArrayOfObject1.length)
    {
      i = 1;
      int j = -1 + paramArrayOfClass.length;
      int k = 0;
      if ((i != 0) && (k <= j))
      {
        Object localObject1 = paramArrayOfObject1[k];
        Class localClass = paramArrayOfClass[k];
        if (isTypeCompatible(localObject1, localClass))
          paramArrayOfObject2[k] = localObject1;
        while (true)
        {
          k++;
          break;
          Object localObject2 = getConvertedType(paramObject, paramMember, paramString, localObject1, localClass);
          if (localObject2 == NoConversionPossible)
          {
            i = 0;
            continue;
          }
          paramArrayOfObject2[k] = localObject2;
        }
      }
    }
    else
    {
      i = 0;
    }
    return i;
  }

  public static List getDeclaredMethods(Class paramClass, String paramString, boolean paramBoolean)
  {
    int i = 1;
    ClassCache[] arrayOfClassCache = _declaredMethods;
    if (paramBoolean)
      i = 0;
    ClassCache localClassCache = arrayOfClassCache[i];
    Map localMap1 = (Map)localClassCache.get(paramClass);
    Object localObject3;
    Object localObject1;
    if (localMap1 != null)
    {
      localObject3 = (List)localMap1.get(paramString);
      if (localObject3 == null)
      {
        localObject1 = localObject3;
        monitorenter;
      }
    }
    while (true)
    {
      Map localMap2;
      Class localClass;
      label132: int j;
      label147: boolean bool2;
      try
      {
        localMap2 = (Map)localClassCache.get(paramClass);
        if (localMap2 == null)
          continue;
        localObject1 = (List)localMap2.get(paramString);
        if (localObject1 == null)
        {
          String str1 = Character.toUpperCase(paramString.charAt(0)) + paramString.substring(1);
          localClass = paramClass;
          if (localClass != null)
          {
            Method[] arrayOfMethod = localClass.getDeclaredMethods();
            j = 0;
            if (j < arrayOfMethod.length)
            {
              if (!isMethodCallable(arrayOfMethod[j]))
                break label411;
              String str2 = arrayOfMethod[j].getName();
              if (!str2.endsWith(str1))
                break label411;
              boolean bool1 = str2.startsWith("set");
              if ((bool1) || (str2.startsWith("get")))
                break label392;
              bool2 = str2.startsWith("is");
              if (!bool2)
                break label411;
              break label417;
              label230: if ((bool1 != paramBoolean) || (str1.length() != str2.length() - k))
                break label411;
              if (localObject1 != null)
                continue;
              localObject1 = new ArrayList();
              ((List)localObject1).add(arrayOfMethod[j]);
            }
          }
        }
      }
      finally
      {
        monitorexit;
      }
      label347: 
      do
      {
        k = 3;
        break label230;
        localClass = localClass.getSuperclass();
        break label132;
        Object localObject4;
        Object localObject5;
        if (localMap2 == null)
        {
          HashMap localHashMap = new HashMap(101);
          localClassCache.put(paramClass, localHashMap);
          localObject4 = localHashMap;
          if (localObject1 == null)
          {
            localObject5 = NotFoundList;
            ((Map)localObject4).put(paramString, localObject5);
          }
        }
        for (localObject3 = localObject1; ; localObject3 = localObject1)
        {
          monitorexit;
          if (localObject3 == NotFoundList)
          {
            return null;
            localObject5 = localObject1;
            break label347;
          }
          return localObject3;
          localObject4 = localMap2;
          break;
          bool2 = false;
          break label417;
        }
        localObject1 = null;
        break;
        j++;
        break label147;
      }
      while (!bool2);
      label392: label411: label417: int k = 2;
    }
  }

  public static EvaluationPool getEvaluationPool()
  {
    return _evaluationPool;
  }

  public static Field getField(Class paramClass, String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = getFields(paramClass).get(paramString);
    if (localObject2 == null);
    while (true)
    {
      synchronized (_fieldCache)
      {
        Object localObject4 = getFields(paramClass).get(paramString);
        if (localObject4 != null)
          continue;
        _superclasses.clear();
        if (paramClass == null)
          continue;
        Object localObject5 = getFields(paramClass).get(paramString);
        if (localObject5 != NotFound)
          continue;
        int i = _superclasses.size();
        int j = 0;
        if (j >= i)
          break label255;
        Map localMap = getFields((Class)_superclasses.get(j));
        if (localObject1 != null)
          break label249;
        localObject6 = NotFound;
        localMap.put(paramString, localObject6);
        j++;
        continue;
        _superclasses.add(paramClass);
        Field localField = (Field)localObject5;
        if (localField == null)
          continue;
        localObject1 = localField;
        continue;
        paramClass = paramClass.getSuperclass();
        localObject1 = localField;
        continue;
        return localObject7;
        if (!(localObject4 instanceof Field))
          continue;
        localObject7 = (Field)localObject4;
        continue;
        if (NotFound != null)
          break label243;
        localObject7 = null;
      }
      if ((localObject2 instanceof Field))
        return (Field)localObject2;
      if (NotFound == null)
        return null;
      return null;
      label243: Object localObject7 = null;
      continue;
      label249: Object localObject6 = localObject1;
      continue;
      label255: localObject7 = localObject1;
    }
  }

  public static Object getFieldValue(Object paramObject, String paramString)
    throws NoSuchFieldException
  {
    if (paramObject == null);
    Field localField;
    for (Class localClass = null; ; localClass = paramObject.getClass())
    {
      localField = getField(localClass, paramString);
      if (localField != null)
        break;
      throw new NoSuchFieldException(paramString);
    }
    try
    {
      if (!Modifier.isStatic(localField.getModifiers()))
      {
        localField.setAccessible(true);
        return localField.get(paramObject);
      }
      throw new NoSuchFieldException(paramString);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new NoSuchFieldException(paramString);
  }

  public static Map getFields(Class paramClass)
  {
    Map localMap = (Map)_fieldCache.get(paramClass);
    if (localMap == null)
      synchronized (_fieldCache)
      {
        Object localObject2 = (Map)_fieldCache.get(paramClass);
        if (localObject2 == null)
        {
          localObject2 = new HashMap(23);
          Field[] arrayOfField = paramClass.getDeclaredFields();
          for (int i = 0; i < arrayOfField.length; i++)
            ((Map)localObject2).put(arrayOfField[i].getName(), arrayOfField[i]);
          _fieldCache.put(paramClass, localObject2);
        }
        return localObject2;
      }
    return (Map)localMap;
  }

  public static Method getGetMethod(Object paramObject, Class paramClass, String paramString)
    throws OgnlException
  {
    Integer localInteger = new Integer(31 * paramClass.hashCode() + paramString.hashCode());
    if (cacheGetMethod.containsKey(localInteger))
      return (Method)cacheGetMethod.get(localInteger);
    while (true)
    {
      synchronized (cacheGetMethod)
      {
        if (!cacheGetMethod.containsKey(localInteger))
        {
          localMethod = _getGetMethod(paramObject, paramClass, paramString);
          cacheGetMethod.put(localInteger, localMethod);
          return localMethod;
        }
      }
      Method localMethod = (Method)cacheGetMethod.get(localInteger);
    }
  }

  private static Object getHandler(Class paramClass, ClassCache paramClassCache)
  {
    Object localObject1 = paramClassCache.get(paramClass);
    if (localObject1 == null)
      monitorenter;
    while (true)
    {
      int j;
      Class localClass;
      Object localObject5;
      try
      {
        localObject3 = paramClassCache.get(paramClass);
        if (localObject3 != null)
          continue;
        if (!paramClass.isArray())
          break label173;
        localObject3 = paramClassCache.get([Ljava.lang.Object.class);
        localObject4 = null;
        if ((localObject3 == null) || (localObject4 == paramClass))
          continue;
        paramClassCache.put(paramClass, localObject3);
        return localObject3;
        if (localObject4 != null)
        {
          localObject3 = paramClassCache.get((Class)localObject4);
          if (localObject3 != null)
            continue;
          Class[] arrayOfClass = ((Class)localObject4).getInterfaces();
          int i = arrayOfClass.length;
          j = 0;
          if (j >= i)
            continue;
          localClass = arrayOfClass[j];
          localObject5 = paramClassCache.get(localClass);
          if (localObject5 != null)
            break label179;
          localObject5 = getHandler(localClass, paramClassCache);
          break label179;
          localObject4 = ((Class)localObject4).getSuperclass();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Object localObject4 = paramClass;
      continue;
      return localObject1;
      label173: localObject4 = paramClass;
      continue;
      label179: if (localObject5 != null)
      {
        localObject4 = localClass;
        localObject3 = localObject5;
        continue;
      }
      j++;
      Object localObject3 = localObject5;
    }
  }

  public static final Object getMethodValue(Object paramObject, String paramString)
    throws Exception
  {
    Method localMethod = getGetMethod(paramObject, paramObject.getClass(), paramString);
    Object localObject1 = null;
    if (localMethod == null)
      localObject1 = NotFound;
    if ((localObject1 != null) || (localMethod != null))
      try
      {
        Object localObject2 = invokeMethod(paramObject, localMethod, NoArguments);
        localObject1 = localObject2;
        return localObject1;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new OgnlException(paramString, localInvocationTargetException.getTargetException());
      }
    throw new NoSuchMethodException(paramString);
  }

  public static List getMethods(Class paramClass, String paramString, boolean paramBoolean)
  {
    return (List)getMethods(paramClass, paramBoolean).get(paramString);
  }

  public static Map getMethods(Class paramClass, boolean paramBoolean)
  {
    ClassCache localClassCache;
    Object localObject1;
    HashMap localHashMap;
    if (paramBoolean)
    {
      localClassCache = _staticMethodCache;
      localObject1 = (Map)localClassCache.get(paramClass);
      if (localObject1 == null)
        monitorenter;
    }
    else
    {
      while (true)
      {
        try
        {
          localObject1 = (Map)localClassCache.get(paramClass);
          if (localObject1 != null)
            break label216;
          localHashMap = new HashMap(23);
          localClassCache.put(paramClass, localHashMap);
          if (paramClass == null)
            break label220;
          Method[] arrayOfMethod = paramClass.getDeclaredMethods();
          int i = arrayOfMethod.length;
          int j = 0;
          if (j < i)
          {
            boolean bool = isMethodCallable(arrayOfMethod[j]);
            if (bool)
              continue;
            j++;
            continue;
            localClassCache = _instanceMethodCache;
            break;
            if (Modifier.isStatic(arrayOfMethod[j].getModifiers()) != paramBoolean)
              continue;
            Object localObject3 = (List)localHashMap.get(arrayOfMethod[j].getName());
            if (localObject3 != null)
              continue;
            String str = arrayOfMethod[j].getName();
            localObject3 = new ArrayList();
            localHashMap.put(str, localObject3);
            ((List)localObject3).add(arrayOfMethod[j]);
            continue;
          }
        }
        finally
        {
          monitorexit;
        }
        paramClass = paramClass.getSuperclass();
      }
    }
    while (true)
    {
      label216: monitorexit;
      return localObject1;
      label220: localObject1 = localHashMap;
    }
  }

  public static String getModifierString(int paramInt)
  {
    String str;
    if (Modifier.isPublic(paramInt))
      str = "public";
    while (true)
    {
      if (Modifier.isStatic(paramInt))
        str = "static " + str;
      if (Modifier.isFinal(paramInt))
        str = "final " + str;
      if (Modifier.isNative(paramInt))
        str = "native " + str;
      if (Modifier.isSynchronized(paramInt))
        str = "synchronized " + str;
      if (Modifier.isTransient(paramInt))
        str = "transient " + str;
      return str;
      if (Modifier.isProtected(paramInt))
      {
        str = "protected";
        continue;
      }
      if (Modifier.isPrivate(paramInt))
      {
        str = "private";
        continue;
      }
      str = "";
    }
  }

  public static NullHandler getNullHandler(Class paramClass)
    throws OgnlException
  {
    NullHandler localNullHandler = (NullHandler)getHandler(paramClass, _nullHandlers);
    if (localNullHandler != null)
      return localNullHandler;
    throw new OgnlException("No null handler for class " + paramClass);
  }

  public static String getNumericCast(Class paramClass)
  {
    return (String)NUMERIC_CASTS.get(paramClass);
  }

  public static Object getNumericDefaultValue(Class paramClass)
  {
    return NUMERIC_DEFAULTS.get(paramClass);
  }

  public static String getNumericLiteral(Class paramClass)
  {
    return (String)NUMERIC_LITERALS.get(paramClass);
  }

  public static String getNumericValueGetter(Class paramClass)
  {
    return (String)NUMERIC_VALUES.get(paramClass);
  }

  public static ObjectArrayPool getObjectArrayPool()
  {
    return _objectArrayPool;
  }

  public static String getPackageName(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return getClassPackageName(paramObject.getClass());
  }

  public static Class[] getParameterTypes(Constructor paramConstructor)
  {
    Class[] arrayOfClass1 = (Class[])(Class[])_ctorParameterTypesCache.get(paramConstructor);
    if (arrayOfClass1 == null)
      synchronized (_ctorParameterTypesCache)
      {
        Class[] arrayOfClass2 = (Class[])(Class[])_ctorParameterTypesCache.get(paramConstructor);
        if (arrayOfClass2 == null)
        {
          Map localMap2 = _ctorParameterTypesCache;
          arrayOfClass2 = paramConstructor.getParameterTypes();
          localMap2.put(paramConstructor, arrayOfClass2);
        }
        return arrayOfClass2;
      }
    return arrayOfClass1;
  }

  public static Class[] getParameterTypes(Method paramMethod)
  {
    synchronized (_methodParameterTypesCache)
    {
      Class[] arrayOfClass = (Class[])(Class[])_methodParameterTypesCache.get(paramMethod);
      if (arrayOfClass == null)
      {
        Map localMap2 = _methodParameterTypesCache;
        arrayOfClass = paramMethod.getParameterTypes();
        localMap2.put(paramMethod, arrayOfClass);
      }
      return arrayOfClass;
    }
  }

  public static String getPointerString(int paramInt)
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    String str1 = Integer.toHexString(paramInt);
    Integer localInteger = new Integer(str1.length());
    String str2 = (String)HEX_PADDING.get(localInteger);
    if (str2 == null)
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      for (int i = str1.length(); i < 8; i++)
        localStringBuffer2.append('0');
      str2 = new String(localStringBuffer2);
      HEX_PADDING.put(localInteger, str2);
    }
    localStringBuffer1.append(str2);
    localStringBuffer1.append(str1);
    return new String(localStringBuffer1);
  }

  public static String getPointerString(Object paramObject)
  {
    if (paramObject == null);
    for (int i = 0; ; i = System.identityHashCode(paramObject))
      return getPointerString(i);
  }

  public static Object getPrimitiveDefaultValue(Class paramClass)
  {
    return _primitiveDefaults.get(paramClass);
  }

  public static Class getPrimitiveWrapperClass(Class paramClass)
  {
    return (Class)PRIMITIVE_WRAPPER_CLASSES.get(paramClass);
  }

  public static SecurityManager getSecurityManager()
  {
    return _securityManager;
  }

  public static Method getSetMethod(Object paramObject, Class paramClass, String paramString)
    throws OgnlException
  {
    Integer localInteger = new Integer(27 * paramClass.hashCode() + paramString.hashCode());
    if (cacheSetMethod.containsKey(localInteger))
      return (Method)cacheSetMethod.get(localInteger);
    while (true)
    {
      synchronized (cacheSetMethod)
      {
        if (!cacheSetMethod.containsKey(localInteger))
        {
          localMethod = _getSetMethod(paramObject, paramClass, paramString);
          cacheSetMethod.put(localInteger, localMethod);
          return localMethod;
        }
      }
      Method localMethod = (Method)cacheSetMethod.get(localInteger);
    }
  }

  public static Class getTargetClass(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Class))
      return (Class)paramObject;
    return paramObject.getClass();
  }

  public static String getUniqueDescriptor(Object paramObject)
  {
    return getUniqueDescriptor(paramObject, false);
  }

  public static String getUniqueDescriptor(Object paramObject, boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (paramObject != null)
    {
      if ((paramObject instanceof Proxy))
      {
        localStringBuffer.append(getClassName(paramObject.getClass().getInterfaces()[0], paramBoolean));
        localStringBuffer.append('^');
        paramObject = Proxy.getInvocationHandler(paramObject);
      }
      localStringBuffer.append(getClassName(paramObject, paramBoolean));
      localStringBuffer.append('@');
      localStringBuffer.append(getPointerString(paramObject));
    }
    while (true)
    {
      return new String(localStringBuffer);
      localStringBuffer.append("<null>");
    }
  }

  private static final boolean indexMethodCheck(List paramList)
  {
    Class[] arrayOfClass1;
    int j;
    int i;
    Class[] arrayOfClass2;
    if (paramList.size() > 0)
    {
      Method localMethod = (Method)paramList.get(0);
      arrayOfClass1 = getParameterTypes(localMethod);
      j = arrayOfClass1.length;
      Class localClass1 = localMethod.getDeclaringClass();
      int k = 1;
      Object localObject = localClass1;
      i = 1;
      while ((i != 0) && (k < paramList.size()))
      {
        Class localClass2 = ((Method)paramList.get(k)).getDeclaringClass();
        if (localObject == localClass2)
        {
          m = 0;
          k++;
          localObject = localClass2;
          i = m;
          continue;
        }
        arrayOfClass2 = getParameterTypes(localMethod);
        if (j == arrayOfClass1.length)
          break label152;
      }
    }
    label146: label152: for (int m = 0; ; m = i)
    {
      for (int n = 0; ; n++)
      {
        if (n >= j)
          break label146;
        if (arrayOfClass1[n] == arrayOfClass2[n])
          continue;
        m = 0;
        break;
      }
      break;
      i = 0;
      return i;
    }
  }

  // ERROR //
  public static Object invokeMethod(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws InvocationTargetException, IllegalAccessException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 790	java/lang/reflect/Method:hashCode	()I
    //   4: istore_3
    //   5: aload_1
    //   6: monitorenter
    //   7: getstatic 184	cn/suanya/synl/OgnlRuntime:_methodAccessCache	Lcn/suanya/synl/og/IntHashMap;
    //   10: iload_3
    //   11: invokevirtual 791	cn/suanya/synl/og/IntHashMap:get	(I)Ljava/lang/Object;
    //   14: ifnull +336 -> 350
    //   17: getstatic 184	cn/suanya/synl/OgnlRuntime:_methodAccessCache	Lcn/suanya/synl/og/IntHashMap;
    //   20: iload_3
    //   21: invokevirtual 791	cn/suanya/synl/og/IntHashMap:get	(I)Ljava/lang/Object;
    //   24: getstatic 794	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   27: if_acmpne +317 -> 344
    //   30: goto +320 -> 350
    //   33: getstatic 169	cn/suanya/synl/OgnlRuntime:_securityManager	Ljava/lang/SecurityManager;
    //   36: ifnull +13 -> 49
    //   39: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   42: iload_3
    //   43: invokevirtual 791	cn/suanya/synl/og/IntHashMap:get	(I)Ljava/lang/Object;
    //   46: ifnull +310 -> 356
    //   49: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   52: iload_3
    //   53: invokevirtual 791	cn/suanya/synl/og/IntHashMap:get	(I)Ljava/lang/Object;
    //   56: astore 6
    //   58: getstatic 273	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   61: astore 7
    //   63: iconst_0
    //   64: istore 8
    //   66: aload 6
    //   68: aload 7
    //   70: if_acmpne +6 -> 76
    //   73: goto +283 -> 356
    //   76: aload_1
    //   77: monitorexit
    //   78: iload 5
    //   80: ifeq +194 -> 274
    //   83: aload_1
    //   84: monitorenter
    //   85: iload 8
    //   87: ifeq +14 -> 101
    //   90: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   93: iload_3
    //   94: getstatic 794	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   97: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   100: pop
    //   101: aload_1
    //   102: invokevirtual 681	java/lang/reflect/Method:getModifiers	()I
    //   105: invokestatic 685	java/lang/reflect/Modifier:isPublic	(I)Z
    //   108: ifeq +16 -> 124
    //   111: aload_1
    //   112: invokevirtual 479	java/lang/reflect/Method:getDeclaringClass	()Ljava/lang/Class;
    //   115: invokevirtual 798	java/lang/Class:getModifiers	()I
    //   118: invokestatic 685	java/lang/reflect/Modifier:isPublic	(I)Z
    //   121: ifne +136 -> 257
    //   124: aload_1
    //   125: invokevirtual 803	java/lang/reflect/AccessibleObject:isAccessible	()Z
    //   128: istore 13
    //   130: iload 13
    //   132: ifne +107 -> 239
    //   135: aload_1
    //   136: iconst_1
    //   137: invokevirtual 804	java/lang/reflect/AccessibleObject:setAccessible	(Z)V
    //   140: getstatic 184	cn/suanya/synl/OgnlRuntime:_methodAccessCache	Lcn/suanya/synl/og/IntHashMap;
    //   143: iload_3
    //   144: getstatic 794	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   147: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   150: pop
    //   151: iload 13
    //   153: istore 15
    //   155: aload_1
    //   156: aload_0
    //   157: aload_2
    //   158: invokevirtual 808	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   161: astore 16
    //   163: iload 15
    //   165: ifne +8 -> 173
    //   168: aload_1
    //   169: iconst_0
    //   170: invokevirtual 804	java/lang/reflect/AccessibleObject:setAccessible	(Z)V
    //   173: aload_1
    //   174: monitorexit
    //   175: aload 16
    //   177: areturn
    //   178: astore 4
    //   180: aload_1
    //   181: monitorexit
    //   182: aload 4
    //   184: athrow
    //   185: astore 19
    //   187: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   190: iload_3
    //   191: getstatic 273	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   194: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   197: pop
    //   198: new 372	java/lang/IllegalAccessException
    //   201: dup
    //   202: new 391	java/lang/StringBuilder
    //   205: dup
    //   206: invokespecial 392	java/lang/StringBuilder:<init>	()V
    //   209: ldc_w 810
    //   212: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: aload_1
    //   216: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   219: ldc_w 812
    //   222: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   225: invokevirtual 408	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokespecial 813	java/lang/IllegalAccessException:<init>	(Ljava/lang/String;)V
    //   231: athrow
    //   232: astore 12
    //   234: aload_1
    //   235: monitorexit
    //   236: aload 12
    //   238: athrow
    //   239: getstatic 184	cn/suanya/synl/OgnlRuntime:_methodAccessCache	Lcn/suanya/synl/og/IntHashMap;
    //   242: iload_3
    //   243: getstatic 273	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   246: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   249: pop
    //   250: iload 13
    //   252: istore 15
    //   254: goto -99 -> 155
    //   257: getstatic 184	cn/suanya/synl/OgnlRuntime:_methodAccessCache	Lcn/suanya/synl/og/IntHashMap;
    //   260: iload_3
    //   261: getstatic 273	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   264: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   267: pop
    //   268: iconst_1
    //   269: istore 15
    //   271: goto -116 -> 155
    //   274: iload 8
    //   276: ifeq +14 -> 290
    //   279: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   282: iload_3
    //   283: getstatic 794	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   286: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   289: pop
    //   290: aload_1
    //   291: aload_0
    //   292: aload_2
    //   293: invokevirtual 808	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   296: areturn
    //   297: astore 9
    //   299: getstatic 186	cn/suanya/synl/OgnlRuntime:_methodPermCache	Lcn/suanya/synl/og/IntHashMap;
    //   302: iload_3
    //   303: getstatic 273	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   306: invokevirtual 797	cn/suanya/synl/og/IntHashMap:put	(ILjava/lang/Object;)Ljava/lang/Object;
    //   309: pop
    //   310: new 372	java/lang/IllegalAccessException
    //   313: dup
    //   314: new 391	java/lang/StringBuilder
    //   317: dup
    //   318: invokespecial 392	java/lang/StringBuilder:<init>	()V
    //   321: ldc_w 810
    //   324: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: aload_1
    //   328: invokevirtual 728	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   331: ldc_w 812
    //   334: invokevirtual 396	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: invokevirtual 408	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   340: invokespecial 813	java/lang/IllegalAccessException:<init>	(Ljava/lang/String;)V
    //   343: athrow
    //   344: iconst_0
    //   345: istore 5
    //   347: goto -314 -> 33
    //   350: iconst_1
    //   351: istore 5
    //   353: goto -320 -> 33
    //   356: iconst_1
    //   357: istore 8
    //   359: goto -283 -> 76
    //
    // Exception table:
    //   from	to	target	type
    //   7	30	178	finally
    //   33	49	178	finally
    //   49	63	178	finally
    //   76	78	178	finally
    //   180	182	178	finally
    //   90	101	185	java/lang/SecurityException
    //   90	101	232	finally
    //   101	124	232	finally
    //   124	130	232	finally
    //   135	151	232	finally
    //   155	163	232	finally
    //   168	173	232	finally
    //   173	175	232	finally
    //   187	232	232	finally
    //   234	236	232	finally
    //   239	250	232	finally
    //   257	268	232	finally
    //   279	290	297	java/lang/SecurityException
  }

  public static boolean isBoolean(String paramString)
  {
    if (paramString == null);
    do
      return false;
    while ((!"true".equals(paramString)) && (!"false".equals(paramString)) && (!"!true".equals(paramString)) && (!"!false".equals(paramString)) && (!"(true)".equals(paramString)) && (!"!(true)".equals(paramString)) && (!"(false)".equals(paramString)) && (!"!(false)".equals(paramString)) && (!paramString.startsWith("ognl.OgnlOps")));
    return true;
  }

  public static boolean isJdk15()
  {
    if (_jdkChecked)
      return _jdk15;
    try
    {
      Class.forName("java.lang.annotation.Annotation");
      _jdk15 = true;
      label21: _jdkChecked = true;
      return _jdk15;
    }
    catch (Exception localException)
    {
      break label21;
    }
  }

  static boolean isMethodCallable(Method paramMethod)
  {
    return ((!isJdk15()) || (!paramMethod.isSynthetic())) && (!Modifier.isVolatile(paramMethod.getModifiers()));
  }

  public static final boolean isMoreSpecific(Class[] paramArrayOfClass1, Class[] paramArrayOfClass2)
  {
    int i = paramArrayOfClass1.length;
    int j = 0;
    if (j < i)
    {
      Class localClass1 = paramArrayOfClass1[j];
      Class localClass2 = paramArrayOfClass2[j];
      if (localClass1 == localClass2);
      do
      {
        j++;
        break;
        if (localClass1.isPrimitive())
          return true;
        if (localClass1.isAssignableFrom(localClass2))
          return false;
      }
      while (!localClass2.isAssignableFrom(localClass1));
      return true;
    }
    return false;
  }

  public static final boolean isTypeCompatible(Object paramObject, Class paramClass)
  {
    if (paramObject != null)
      if (paramClass.isPrimitive())
      {
        if (getArgClass(paramObject) == paramClass);
      }
      else
        do
          return false;
        while (!paramClass.isInstance(paramObject));
    return true;
  }

  static Class resolveType(ParameterizedType paramParameterizedType, TypeVariable paramTypeVariable, TypeVariable[] paramArrayOfTypeVariable)
  {
    if (paramParameterizedType.getActualTypeArguments().length < 1)
      return null;
    for (int i = 0; i < paramArrayOfTypeVariable.length; i++)
      if ((!TypeVariable.class.isInstance(paramParameterizedType.getActualTypeArguments()[i])) && (paramArrayOfTypeVariable[i].getName().equals(paramTypeVariable.getName())))
        return (Class)paramParameterizedType.getActualTypeArguments()[i];
    return null;
  }

  public static void setClassCacheInspector(ClassCacheInspector paramClassCacheInspector)
  {
    _cacheInspector = paramClassCacheInspector;
    _propertyDescriptorCache.setClassInspector(_cacheInspector);
    _constructorCache.setClassInspector(_cacheInspector);
    _staticMethodCache.setClassInspector(_cacheInspector);
    _instanceMethodCache.setClassInspector(_cacheInspector);
    _invokePermissionCache.setClassInspector(_cacheInspector);
    _fieldCache.setClassInspector(_cacheInspector);
    _declaredMethods[0].setClassInspector(_cacheInspector);
    _declaredMethods[1].setClassInspector(_cacheInspector);
  }

  public static void setFieldValue(Object paramObject1, String paramString, Object paramObject2)
    throws Exception
  {
    Class localClass;
    if (paramObject1 == null)
      localClass = null;
    try
    {
      while (true)
      {
        Field localField = getField(localClass, paramString);
        if ((localField != null) && (!Modifier.isStatic(localField.getModifiers())))
        {
          if (!isTypeCompatible(paramObject2, localField.getType()))
          {
            paramObject2 = OgnlOps.convertValue(paramObject2, localField.getType());
            if (paramObject2 == null)
              break;
          }
          else
          {
            localField.set(paramObject1, paramObject2);
            return;
            localClass = paramObject1.getClass();
            continue;
          }
        }
        else
          throw new NoSuchPropertyException(paramObject1, paramString);
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new NoSuchPropertyException(paramObject1, paramString, localIllegalAccessException);
    }
  }

  public static boolean setMethodValue(Object paramObject1, String paramString, Object paramObject2)
    throws Exception
  {
    Class localClass;
    Method localMethod;
    if (paramObject1 == null)
    {
      localClass = null;
      localMethod = getSetMethod(paramObject1, localClass, paramString);
      if (localMethod != null)
        break label69;
    }
    label69: for (int i = 0; ; i = 1)
    {
      if (i != 0)
      {
        if (localMethod != null)
        {
          Object[] arrayOfObject = { paramObject2 };
          callAppropriateMethod(paramObject1, paramString, Collections.nCopies(1, localMethod), arrayOfObject);
        }
      }
      else
      {
        return i;
        localClass = paramObject1.getClass();
        break;
      }
      return false;
    }
  }

  public static void setNullHandler(Class paramClass, NullHandler paramNullHandler)
  {
    synchronized (_nullHandlers)
    {
      _nullHandlers.put(paramClass, paramNullHandler);
      return;
    }
  }

  public static void setSecurityManager(SecurityManager paramSecurityManager)
  {
    _securityManager = paramSecurityManager;
  }

  public static Object[] toArray(List paramList)
  {
    int i = paramList.size();
    Object[] arrayOfObject;
    if (i == 0)
      arrayOfObject = NoArguments;
    while (true)
    {
      return arrayOfObject;
      arrayOfObject = getObjectArrayPool().create(paramList.size());
      for (int j = 0; j < i; j++)
        arrayOfObject[j] = paramList.get(j);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.OgnlRuntime
 * JD-Core Version:    0.6.0
 */