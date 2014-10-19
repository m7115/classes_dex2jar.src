package com.squareup.okhttp.internal;

public abstract class NamedRunnable
  implements Runnable
{
  private final String name;

  public NamedRunnable(String paramString, Object[] paramArrayOfObject)
  {
    this.name = String.format(paramString, paramArrayOfObject);
  }

  protected abstract void execute();

  public final void run()
  {
    String str = Thread.currentThread().getName();
    Thread.currentThread().setName(this.name);
    try
    {
      execute();
      return;
    }
    finally
    {
      Thread.currentThread().setName(str);
    }
    throw localObject;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.NamedRunnable
 * JD-Core Version:    0.6.0
 */