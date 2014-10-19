package com.google.common.base;

import java.lang.ref.WeakReference;

public abstract class FinalizableWeakReference<T> extends WeakReference<T>
  implements FinalizableReference
{
  protected FinalizableWeakReference(T paramT, FinalizableReferenceQueue paramFinalizableReferenceQueue)
  {
    super(paramT, paramFinalizableReferenceQueue.queue);
    paramFinalizableReferenceQueue.cleanUp();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.common.base.FinalizableWeakReference
 * JD-Core Version:    0.6.0
 */