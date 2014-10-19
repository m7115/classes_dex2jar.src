package cn.suanya.synl.og;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class OgnlException extends Exception
{
  static Method _initCause;
  private Throwable _reason;

  static
  {
    try
    {
      _initCause = OgnlException.class.getMethod("initCause", new Class[] { Throwable.class });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
  }

  public OgnlException()
  {
    this(null, null);
  }

  public OgnlException(String paramString)
  {
    this(paramString, null);
  }

  public OgnlException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    this._reason = paramThrowable;
    if (_initCause != null);
    try
    {
      _initCause.invoke(this, new Object[] { paramThrowable });
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public Throwable getReason()
  {
    return this._reason;
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    monitorenter;
    try
    {
      super.printStackTrace(paramPrintStream);
      if (this._reason != null)
      {
        paramPrintStream.println("/-- Encapsulated exception ------------\\");
        this._reason.printStackTrace(paramPrintStream);
        paramPrintStream.println("\\--------------------------------------/");
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    monitorenter;
    try
    {
      super.printStackTrace(paramPrintWriter);
      if (this._reason != null)
      {
        paramPrintWriter.println("/-- Encapsulated exception ------------\\");
        this._reason.printStackTrace(paramPrintWriter);
        paramPrintWriter.println("\\--------------------------------------/");
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String toString()
  {
    if (this._reason == null)
      return super.toString();
    return super.toString() + " [" + this._reason + "]";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.og.OgnlException
 * JD-Core Version:    0.6.0
 */