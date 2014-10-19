package cn.suanya.synl;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class OgnlOps
  implements NumericTypes
{
  public static Object add(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2, true);
    switch (i)
    {
    default:
      return newInteger(i, longValue(paramObject1) + longValue(paramObject2));
    case 6:
      return bigIntValue(paramObject1).add(bigIntValue(paramObject2));
    case 9:
      return bigDecValue(paramObject1).add(bigDecValue(paramObject2));
    case 7:
    case 8:
      return newReal(i, doubleValue(paramObject1) + doubleValue(paramObject2));
    case 10:
    }
    int j = getNumericType(paramObject1);
    int k = getNumericType(paramObject2);
    if (((j != 10) && (paramObject2 == null)) || ((k != 10) && (paramObject1 == null)))
      throw new NullPointerException("Can't add values " + paramObject1 + " , " + paramObject2);
    return stringValue(paramObject1) + stringValue(paramObject2);
  }

  public static BigDecimal bigDecValue(Object paramObject)
    throws NumberFormatException
  {
    long l = 0L;
    if (paramObject == null)
      return BigDecimal.valueOf(l);
    Class localClass = paramObject.getClass();
    if (localClass == BigDecimal.class)
      return (BigDecimal)paramObject;
    if (localClass == BigInteger.class)
      return new BigDecimal((BigInteger)paramObject);
    if (localClass == Boolean.class)
    {
      if (((Boolean)paramObject).booleanValue())
        l = 1L;
      return BigDecimal.valueOf(l);
    }
    if (localClass == Character.class)
      return BigDecimal.valueOf(((Character)paramObject).charValue());
    return new BigDecimal(stringValue(paramObject, true));
  }

  public static BigInteger bigIntValue(Object paramObject)
    throws NumberFormatException
  {
    long l = 0L;
    if (paramObject == null)
      return BigInteger.valueOf(l);
    Class localClass = paramObject.getClass();
    if (localClass == BigInteger.class)
      return (BigInteger)paramObject;
    if (localClass == BigDecimal.class)
      return ((BigDecimal)paramObject).toBigInteger();
    if (localClass.getSuperclass() == Number.class)
      return BigInteger.valueOf(((Number)paramObject).longValue());
    if (localClass == Boolean.class)
    {
      if (((Boolean)paramObject).booleanValue())
        l = 1L;
      return BigInteger.valueOf(l);
    }
    if (localClass == Character.class)
      return BigInteger.valueOf(((Character)paramObject).charValue());
    return new BigInteger(stringValue(paramObject, true));
  }

  public static Object binaryAnd(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).and(bigIntValue(paramObject2));
    return newInteger(i, longValue(paramObject1) & longValue(paramObject2));
  }

  public static Object binaryOr(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).or(bigIntValue(paramObject2));
    return newInteger(i, longValue(paramObject1) | longValue(paramObject2));
  }

  public static Object binaryXor(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).xor(bigIntValue(paramObject2));
    return newInteger(i, longValue(paramObject1) ^ longValue(paramObject2));
  }

  public static Object bitNegate(Object paramObject)
  {
    int i = getNumericType(paramObject);
    switch (i)
    {
    case 7:
    case 8:
    default:
      return newInteger(i, 0xFFFFFFFF ^ longValue(paramObject));
    case 6:
    case 9:
    }
    return bigIntValue(paramObject).not();
  }

  public static boolean booleanValue(double paramDouble)
  {
    return paramDouble > 0.0D;
  }

  public static boolean booleanValue(float paramFloat)
  {
    return paramFloat > 0.0F;
  }

  public static boolean booleanValue(int paramInt)
  {
    return paramInt > 0;
  }

  public static boolean booleanValue(long paramLong)
  {
    return paramLong > 0L;
  }

  public static boolean booleanValue(Object paramObject)
  {
    int i = 1;
    if (paramObject == null)
      i = 0;
    do
      while (true)
      {
        return i;
        Class localClass = paramObject.getClass();
        if (localClass == Boolean.class)
          return ((Boolean)paramObject).booleanValue();
        if (localClass != Character.class)
          break;
        if (((Character)paramObject).charValue() == 0)
          return false;
      }
    while ((!(paramObject instanceof Number)) || (((Number)paramObject).doubleValue() != 0.0D));
    return false;
  }

  public static boolean booleanValue(boolean paramBoolean)
  {
    return paramBoolean;
  }

  public static int compareWithConversion(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == paramObject2);
    while (true)
    {
      return 0;
      int i = getNumericType(paramObject1);
      int j = getNumericType(paramObject2);
      switch (getNumericType(i, j, true))
      {
      default:
        long l1 = longValue(paramObject1);
        long l2 = longValue(paramObject2);
        if (l1 == l2)
          continue;
        if (l1 < l2)
          return -1;
      case 6:
        return bigIntValue(paramObject1).compareTo(bigIntValue(paramObject2));
      case 9:
        return bigDecValue(paramObject1).compareTo(bigDecValue(paramObject2));
      case 10:
        if ((i == 10) && (j == 10))
        {
          if (((paramObject1 instanceof Comparable)) && (paramObject1.getClass().isAssignableFrom(paramObject2.getClass())))
            return ((Comparable)paramObject1).compareTo(paramObject2);
          throw new IllegalArgumentException("invalid comparison: " + paramObject1.getClass().getName() + " and " + paramObject2.getClass().getName());
        }
      case 7:
      case 8:
        double d1 = doubleValue(paramObject1);
        double d2 = doubleValue(paramObject2);
        if (d1 == d2)
          continue;
        if (d1 < d2)
          return -1;
        return 1;
      }
    }
    return 1;
  }

  public static Object convertValue(byte paramByte, Class paramClass)
  {
    return convertValue(new Byte(paramByte), paramClass);
  }

  public static Object convertValue(byte paramByte, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Byte(paramByte), paramClass, paramBoolean);
  }

  public static Object convertValue(char paramChar, Class paramClass)
  {
    return convertValue(new Character(paramChar), paramClass);
  }

  public static Object convertValue(char paramChar, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Character(paramChar), paramClass, paramBoolean);
  }

  public static Object convertValue(double paramDouble, Class paramClass)
  {
    return convertValue(new Double(paramDouble), paramClass);
  }

  public static Object convertValue(double paramDouble, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Double(paramDouble), paramClass, paramBoolean);
  }

  public static Object convertValue(float paramFloat, Class paramClass)
  {
    return convertValue(new Float(paramFloat), paramClass);
  }

  public static Object convertValue(float paramFloat, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Float(paramFloat), paramClass, paramBoolean);
  }

  public static Object convertValue(int paramInt, Class paramClass)
  {
    return convertValue(new Integer(paramInt), paramClass);
  }

  public static Object convertValue(int paramInt, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Integer(paramInt), paramClass, paramBoolean);
  }

  public static Object convertValue(long paramLong, Class paramClass)
  {
    return convertValue(new Long(paramLong), paramClass);
  }

  public static Object convertValue(long paramLong, Class paramClass, boolean paramBoolean)
  {
    return convertValue(new Long(paramLong), paramClass, paramBoolean);
  }

  public static Object convertValue(Object paramObject, Class paramClass)
  {
    return convertValue(paramObject, paramClass, false);
  }

  public static Object convertValue(Object paramObject, Class paramClass, boolean paramBoolean)
  {
    int i = 0;
    if ((paramObject != null) && (paramClass.isAssignableFrom(paramObject.getClass())));
    Object localObject;
    while (true)
    {
      return paramObject;
      if (paramObject != null)
      {
        Class localClass3;
        int j;
        if ((paramObject.getClass().isArray()) && (paramClass.isArray()))
        {
          localClass3 = paramClass.getComponentType();
          localObject = Array.newInstance(localClass3, Array.getLength(paramObject));
          j = Array.getLength(paramObject);
        }
        while (i < j)
        {
          Array.set(localObject, i, convertValue(Array.get(paramObject, i), localClass3));
          i++;
          continue;
          if ((paramObject.getClass().isArray()) && (!paramClass.isArray()))
            return convertValue(Array.get(paramObject, 0), paramClass);
          if ((paramObject.getClass().isArray()) || (!paramClass.isArray()))
            break label254;
          if (paramClass.getComponentType() != Character.TYPE)
            break label229;
          localObject = stringValue(paramObject).toCharArray();
        }
      }
      while ((localObject != null) || (!paramBoolean))
      {
        if ((paramObject == null) || (localObject != null))
          break label590;
        throw new IllegalArgumentException("Unable to convert type " + paramObject.getClass().getName() + " of " + paramObject + " to type of " + paramClass.getName());
        label229: Class localClass2 = paramClass.getComponentType();
        localObject = null;
        if (localClass2 == Object.class)
        {
          return new Object[] { paramObject };
          label254: if (paramClass != Integer.class)
          {
            Class localClass1 = Integer.TYPE;
            localObject = null;
            if (paramClass != localClass1);
          }
          else
          {
            localObject = new Integer((int)longValue(paramObject));
          }
          if ((paramClass == Double.class) || (paramClass == Double.TYPE))
            localObject = new Double(doubleValue(paramObject));
          if ((paramClass == Boolean.class) || (paramClass == Boolean.TYPE))
            if (!booleanValue(paramObject))
              break label516;
          label516: for (localObject = Boolean.TRUE; ; localObject = Boolean.FALSE)
          {
            if ((paramClass == Byte.class) || (paramClass == Byte.TYPE))
              localObject = new Byte((byte)(int)longValue(paramObject));
            if ((paramClass == Character.class) || (paramClass == Character.TYPE))
              localObject = new Character((char)(int)longValue(paramObject));
            if ((paramClass == Short.class) || (paramClass == Short.TYPE))
              localObject = new Short((short)(int)longValue(paramObject));
            if ((paramClass == Long.class) || (paramClass == Long.TYPE))
              localObject = new Long(longValue(paramObject));
            if ((paramClass == Float.class) || (paramClass == Float.TYPE))
              localObject = new Float(doubleValue(paramObject));
            if (paramClass == BigInteger.class)
              localObject = bigIntValue(paramObject);
            if (paramClass == BigDecimal.class)
              localObject = bigDecValue(paramObject);
            if (paramClass != String.class)
              break;
            localObject = stringValue(paramObject);
            break;
          }
          if (paramClass.isPrimitive())
          {
            localObject = OgnlRuntime.getPrimitiveDefaultValue(paramClass);
            continue;
          }
          if ((paramBoolean) && (paramClass == Boolean.class))
          {
            localObject = Boolean.FALSE;
            continue;
          }
          localObject = null;
          if (!paramBoolean)
            continue;
          boolean bool = Number.class.isAssignableFrom(paramClass);
          localObject = null;
          if (!bool)
            continue;
          localObject = OgnlRuntime.getNumericDefaultValue(paramClass);
        }
      }
    }
    label590: return localObject;
  }

  public static Object convertValue(boolean paramBoolean, Class paramClass)
  {
    return convertValue(new Boolean(paramBoolean), paramClass);
  }

  public static Object convertValue(boolean paramBoolean1, Class paramClass, boolean paramBoolean2)
  {
    return convertValue(new Boolean(paramBoolean1), paramClass, paramBoolean2);
  }

  public static Object divide(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    switch (i)
    {
    default:
      return newInteger(i, longValue(paramObject1) / longValue(paramObject2));
    case 6:
      return bigIntValue(paramObject1).divide(bigIntValue(paramObject2));
    case 9:
      return bigDecValue(paramObject1).divide(bigDecValue(paramObject2), 6);
    case 7:
    case 8:
    }
    return newReal(i, doubleValue(paramObject1) / doubleValue(paramObject2));
  }

  public static double doubleValue(Object paramObject)
    throws NumberFormatException
  {
    if (paramObject == null);
    String str;
    do
    {
      Class localClass;
      while (true)
      {
        return 0.0D;
        localClass = paramObject.getClass();
        if (localClass.getSuperclass() == Number.class)
          return ((Number)paramObject).doubleValue();
        if (localClass != Boolean.class)
          break;
        if (((Boolean)paramObject).booleanValue())
          return 1.0D;
      }
      if (localClass == Character.class)
        return ((Character)paramObject).charValue();
      str = stringValue(paramObject, true);
    }
    while (str.length() == 0);
    return Double.parseDouble(str);
  }

  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      if (paramObject2 != null);
    while (true)
    {
      return true;
      return false;
      if ((paramObject1 == paramObject2) || (isEqual(paramObject1, paramObject2)))
        continue;
      if ((!(paramObject1 instanceof Number)) || (!(paramObject2 instanceof Number)))
        break;
      if (((Number)paramObject1).doubleValue() != ((Number)paramObject2).doubleValue())
        return false;
    }
    return false;
  }

  public static String getEscapeString(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    int j = paramString.length();
    while (i < j)
    {
      localStringBuffer.append(getEscapedChar(paramString.charAt(i)));
      i++;
    }
    return new String(localStringBuffer);
  }

  public static String getEscapedChar(char paramChar)
  {
    String str1;
    int i;
    String str2;
    switch (paramChar)
    {
    default:
      if (!Character.isISOControl(paramChar))
        break label234;
      str1 = Integer.toString(paramChar, 16);
      i = str1.length();
      str2 = "\\u";
      if (i >= 4)
        break;
      if (i == 3)
        str2 = str2 + "0";
    case '\b':
    case '\t':
    case '\n':
    case '\f':
    case '\r':
    case '"':
    case '\'':
    case '\\':
    }
    while (true)
    {
      return str2 + str1;
      return "\b";
      return "\\t";
      return "\\n";
      return "\\f";
      return "\\r";
      return "\\\"";
      return "\\'";
      return "\\\\";
      if (i == 2)
      {
        str2 = str2 + "00";
        continue;
      }
      str2 = str2 + "000";
    }
    label234: return new String(paramChar + "");
  }

  public static int getIntValue(Object paramObject)
  {
    if (paramObject == null)
      return -1;
    try
    {
      if (Number.class.isInstance(paramObject))
        return ((Number)paramObject).intValue();
      if (String.class.isInstance(paramObject));
      String str;
      for (Object localObject = (String)paramObject; ; localObject = str)
      {
        return Integer.parseInt((String)localObject);
        str = paramObject.toString();
      }
    }
    catch (Throwable localThrowable)
    {
    }
    throw new RuntimeException("Error converting " + paramObject + " to integer:", localThrowable);
  }

  public static int getNumericType(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt1 == paramInt2)
      return paramInt1;
    if ((paramBoolean) && ((paramInt1 == 10) || (paramInt2 == 10) || (paramInt1 == 2) || (paramInt2 == 2)))
      return 10;
    if (paramInt1 == 10)
      paramInt1 = 8;
    if (paramInt2 == 10);
    for (int i = 8; ; i = paramInt2)
    {
      if (paramInt1 >= 7)
      {
        if (i >= 7)
          return Math.max(paramInt1, i);
        if (i < 4)
          break;
        if (i == 6)
          return 9;
        return Math.max(8, paramInt1);
      }
      if (i >= 7)
      {
        if (paramInt1 < 4)
          return i;
        if (paramInt1 == 6)
          return 9;
        return Math.max(8, i);
      }
      return Math.max(paramInt1, i);
    }
  }

  public static int getNumericType(Object paramObject)
  {
    if (paramObject != null)
    {
      Class localClass = paramObject.getClass();
      if (localClass == Integer.class)
        return 4;
      if (localClass == Double.class)
        return 8;
      if (localClass == Boolean.class)
        return 0;
      if (localClass == Byte.class)
        return 1;
      if (localClass == Character.class)
        return 2;
      if (localClass == Short.class)
        return 3;
      if (localClass == Long.class)
        return 5;
      if (localClass == Float.class)
        return 7;
      if (localClass == BigInteger.class)
        return 6;
      if (localClass == BigDecimal.class)
        return 9;
    }
    return 10;
  }

  public static int getNumericType(Object paramObject1, Object paramObject2)
  {
    return getNumericType(paramObject1, paramObject2, false);
  }

  public static int getNumericType(Object paramObject1, Object paramObject2, boolean paramBoolean)
  {
    return getNumericType(getNumericType(paramObject1), getNumericType(paramObject2), paramBoolean);
  }

  public static boolean greater(Object paramObject1, Object paramObject2)
  {
    return compareWithConversion(paramObject1, paramObject2) > 0;
  }

  public static boolean isEqual(Object paramObject1, Object paramObject2)
  {
    int i = 1;
    int j;
    if (paramObject1 == paramObject2)
      j = i;
    while (true)
    {
      return j;
      if ((paramObject1 == null) || (!paramObject1.getClass().isArray()))
        break;
      j = 0;
      if (paramObject2 == null)
        continue;
      boolean bool1 = paramObject2.getClass().isArray();
      j = 0;
      if (!bool1)
        continue;
      Class localClass1 = paramObject2.getClass();
      Class localClass2 = paramObject1.getClass();
      j = 0;
      if (localClass1 != localClass2)
        continue;
      if (Array.getLength(paramObject1) == Array.getLength(paramObject2));
      while (true)
      {
        if (i == 0)
          break label167;
        int k = Array.getLength(paramObject1);
        j = i;
        int m = 0;
        while ((j != 0) && (m < k))
        {
          boolean bool2 = isEqual(Array.get(paramObject1, m), Array.get(paramObject2, m));
          m++;
          j = bool2;
        }
        break;
        i = 0;
      }
    }
    if ((paramObject1 != null) && (paramObject2 != null) && ((paramObject1.equals(paramObject2)) || (compareWithConversion(paramObject1, paramObject2) == 0)));
    while (true)
    {
      return i;
      i = 0;
    }
    label167: return i;
  }

  public static boolean less(Object paramObject1, Object paramObject2)
  {
    return compareWithConversion(paramObject1, paramObject2) < 0;
  }

  public static long longValue(Object paramObject)
    throws NumberFormatException
  {
    if (paramObject == null);
    Class localClass;
    while (true)
    {
      return 0L;
      localClass = paramObject.getClass();
      if (localClass.getSuperclass() == Number.class)
        return ((Number)paramObject).longValue();
      if (localClass != Boolean.class)
        break;
      if (((Boolean)paramObject).booleanValue())
        return 1L;
    }
    if (localClass == Character.class)
      return ((Character)paramObject).charValue();
    return Long.parseLong(stringValue(paramObject, true));
  }

  public static Object multiply(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    switch (i)
    {
    default:
      return newInteger(i, longValue(paramObject1) * longValue(paramObject2));
    case 6:
      return bigIntValue(paramObject1).multiply(bigIntValue(paramObject2));
    case 9:
      return bigDecValue(paramObject1).multiply(bigDecValue(paramObject2));
    case 7:
    case 8:
    }
    return newReal(i, doubleValue(paramObject1) * doubleValue(paramObject2));
  }

  public static Object negate(Object paramObject)
  {
    int i = getNumericType(paramObject);
    switch (i)
    {
    default:
      return newInteger(i, -longValue(paramObject));
    case 6:
      return bigIntValue(paramObject).negate();
    case 9:
      return bigDecValue(paramObject).negate();
    case 7:
    case 8:
    }
    return newReal(i, -doubleValue(paramObject));
  }

  public static Number newInteger(int paramInt, long paramLong)
  {
    switch (paramInt)
    {
    case 6:
    default:
      return BigInteger.valueOf(paramLong);
    case 0:
    case 2:
    case 4:
      return new Integer((int)paramLong);
    case 7:
      if (()(float)paramLong != paramLong)
        break;
      return new Float((float)paramLong);
    case 8:
      if (()paramLong == paramLong)
        return new Double(paramLong);
    case 5:
      return new Long(paramLong);
    case 1:
      return new Byte((byte)(int)paramLong);
    case 3:
    }
    return new Short((short)(int)paramLong);
  }

  public static Number newReal(int paramInt, double paramDouble)
  {
    if (paramInt == 7)
      return new Float((float)paramDouble);
    return new Double(paramDouble);
  }

  public static Object remainder(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    switch (i)
    {
    case 7:
    case 8:
    default:
      return newInteger(i, longValue(paramObject1) % longValue(paramObject2));
    case 6:
    case 9:
    }
    return bigIntValue(paramObject1).remainder(bigIntValue(paramObject2));
  }

  public static Object returnValue(Object paramObject1, Object paramObject2)
  {
    return paramObject2;
  }

  public static Object shiftLeft(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).shiftLeft((int)longValue(paramObject2));
    return newInteger(i, longValue(paramObject1) << (int)longValue(paramObject2));
  }

  public static Object shiftRight(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).shiftRight((int)longValue(paramObject2));
    return newInteger(i, longValue(paramObject1) >> (int)longValue(paramObject2));
  }

  public static String stringValue(Object paramObject)
  {
    return stringValue(paramObject, false);
  }

  public static String stringValue(Object paramObject, boolean paramBoolean)
  {
    String str;
    if (paramObject == null)
      str = "";
    do
    {
      return str;
      str = paramObject.toString();
    }
    while (!paramBoolean);
    return str.trim();
  }

  public static Object subtract(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1, paramObject2);
    switch (i)
    {
    default:
      return newInteger(i, longValue(paramObject1) - longValue(paramObject2));
    case 6:
      return bigIntValue(paramObject1).subtract(bigIntValue(paramObject2));
    case 9:
      return bigDecValue(paramObject1).subtract(bigDecValue(paramObject2));
    case 7:
    case 8:
    }
    return newReal(i, doubleValue(paramObject1) - doubleValue(paramObject2));
  }

  public static Object toArray(byte paramByte, Class paramClass)
  {
    return toArray(new Byte(paramByte), paramClass);
  }

  public static Object toArray(byte paramByte, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Byte(paramByte), paramClass, paramBoolean);
  }

  public static Object toArray(char paramChar, Class paramClass)
  {
    return toArray(new Character(paramChar), paramClass);
  }

  public static Object toArray(char paramChar, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Character(paramChar), paramClass, paramBoolean);
  }

  public static Object toArray(double paramDouble, Class paramClass)
  {
    return toArray(new Double(paramDouble), paramClass);
  }

  public static Object toArray(double paramDouble, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Double(paramDouble), paramClass, paramBoolean);
  }

  public static Object toArray(float paramFloat, Class paramClass)
  {
    return toArray(new Float(paramFloat), paramClass);
  }

  public static Object toArray(float paramFloat, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Float(paramFloat), paramClass, paramBoolean);
  }

  public static Object toArray(int paramInt, Class paramClass)
  {
    return toArray(new Integer(paramInt), paramClass);
  }

  public static Object toArray(int paramInt, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Integer(paramInt), paramClass, paramBoolean);
  }

  public static Object toArray(long paramLong, Class paramClass)
  {
    return toArray(new Long(paramLong), paramClass);
  }

  public static Object toArray(long paramLong, Class paramClass, boolean paramBoolean)
  {
    return toArray(new Long(paramLong), paramClass, paramBoolean);
  }

  public static Object toArray(Object paramObject, Class paramClass)
  {
    return toArray(paramObject, paramClass, false);
  }

  public static Object toArray(Object paramObject, Class paramClass, boolean paramBoolean)
  {
    int i = 0;
    if (paramObject == null)
      paramObject = null;
    Object localObject1;
    do
    {
      do
        return paramObject;
      while ((paramObject.getClass().isArray()) && (paramClass.isAssignableFrom(paramObject.getClass().getComponentType())));
      if (!paramObject.getClass().isArray())
      {
        if (paramClass == Character.TYPE)
          return stringValue(paramObject).toCharArray();
        Object localObject2 = Array.newInstance(paramClass, 1);
        Array.set(localObject2, 0, convertValue(paramObject, paramClass, paramBoolean));
        return localObject2;
      }
      localObject1 = Array.newInstance(paramClass, Array.getLength(paramObject));
      int j = Array.getLength(paramObject);
      while (i < j)
      {
        Array.set(localObject1, i, convertValue(Array.get(paramObject, i), paramClass));
        i++;
      }
    }
    while ((localObject1 == null) && (paramBoolean));
    return localObject1;
  }

  public static Object toArray(boolean paramBoolean, Class paramClass)
  {
    return toArray(new Boolean(paramBoolean), paramClass);
  }

  public static Object toArray(boolean paramBoolean1, Class paramClass, boolean paramBoolean2)
  {
    return toArray(new Boolean(paramBoolean1), paramClass, paramBoolean2);
  }

  public static Object unsignedShiftRight(Object paramObject1, Object paramObject2)
  {
    int i = getNumericType(paramObject1);
    if ((i == 6) || (i == 9))
      return bigIntValue(paramObject1).shiftRight((int)longValue(paramObject2));
    if (i <= 4)
      return newInteger(4, (int)longValue(paramObject1) >>> (int)longValue(paramObject2));
    return newInteger(i, longValue(paramObject1) >>> (int)longValue(paramObject2));
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.OgnlOps
 * JD-Core Version:    0.6.0
 */