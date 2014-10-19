package cn.suanya.synl.og;

public final class ObjectArrayPool
{
  public Object[] create(int paramInt)
  {
    return new Object[paramInt];
  }

  public Object[] create(Object paramObject)
  {
    Object[] arrayOfObject = create(1);
    arrayOfObject[0] = paramObject;
    return arrayOfObject;
  }

  public Object[] create(Object paramObject1, Object paramObject2)
  {
    Object[] arrayOfObject = create(2);
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    return arrayOfObject;
  }

  public Object[] create(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    Object[] arrayOfObject = create(3);
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    arrayOfObject[2] = paramObject3;
    return arrayOfObject;
  }

  public Object[] create(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
  {
    Object[] arrayOfObject = create(4);
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    arrayOfObject[2] = paramObject3;
    arrayOfObject[3] = paramObject4;
    return arrayOfObject;
  }

  public Object[] create(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object paramObject5)
  {
    Object[] arrayOfObject = create(5);
    arrayOfObject[0] = paramObject1;
    arrayOfObject[1] = paramObject2;
    arrayOfObject[2] = paramObject3;
    arrayOfObject[3] = paramObject4;
    arrayOfObject[4] = paramObject5;
    return arrayOfObject;
  }

  public void recycle(Object[] paramArrayOfObject)
  {
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.ObjectArrayPool
 * JD-Core Version:    0.6.0
 */