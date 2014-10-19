package com.google.common.base;

import java.lang.ref.PhantomReference;

public abstract class FinalizablePhantomReference<T> extends PhantomReference<T>
  implements FinalizableReference
{
  protected FinalizablePhantomReference(T paramT, FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramT, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.common.base.FinalizablePhantomReference
 * JD-Core Version:    0.6.0
 */