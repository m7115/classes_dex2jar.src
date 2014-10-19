package com.squareup.okhttp.internal;

import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.internal.okio.ByteString;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import javax.net.ssl.SSLSocket;

public class Platform
{
  private static final Platform PLATFORM = findPlatform();
  private Constructor<DeflaterOutputStream> deflaterConstructor;

  static byte[] concatLengthPrefixed(List<Protocol> paramList)
  {
    Iterator localIterator1 = paramList.iterator();
    int i = 0;
    while (localIterator1.hasNext())
      i += 1 + ((Protocol)localIterator1.next()).name.size();
    byte[] arrayOfByte = new byte[i];
    Iterator localIterator2 = paramList.iterator();
    int k;
    int m;
    for (int j = 0; localIterator2.hasNext(); j = m + k)
    {
      Protocol localProtocol = (Protocol)localIterator2.next();
      k = localProtocol.name.size();
      m = j + 1;
      arrayOfByte[j] = (byte)k;
      System.arraycopy(localProtocol.name.toByteArray(), 0, arrayOfByte, m, k);
    }
    return arrayOfByte;
  }

  // ERROR //
  private static Platform findPlatform()
  {
    // Byte code:
    //   0: ldc 66
    //   2: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   5: astore 22
    //   7: aload 22
    //   9: astore 11
    //   11: iconst_1
    //   12: anewarray 68	java/lang/Class
    //   15: astore 12
    //   17: aload 12
    //   19: iconst_0
    //   20: getstatic 78	java/lang/Boolean:TYPE	Ljava/lang/Class;
    //   23: aastore
    //   24: aload 11
    //   26: ldc 80
    //   28: aload 12
    //   30: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   33: astore 13
    //   35: aload 11
    //   37: ldc 86
    //   39: iconst_1
    //   40: anewarray 68	java/lang/Class
    //   43: dup
    //   44: iconst_0
    //   45: ldc 88
    //   47: aastore
    //   48: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   51: astore 14
    //   53: aload 11
    //   55: ldc 90
    //   57: iconst_1
    //   58: anewarray 68	java/lang/Class
    //   61: dup
    //   62: iconst_0
    //   63: ldc 92
    //   65: aastore
    //   66: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   69: astore 19
    //   71: aload 19
    //   73: astore 16
    //   75: aload 11
    //   77: ldc 94
    //   79: iconst_0
    //   80: anewarray 68	java/lang/Class
    //   83: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   86: astore 21
    //   88: aload 21
    //   90: astore 18
    //   92: aload 16
    //   94: astore 17
    //   96: new 96	com/squareup/okhttp/internal/Platform$Android
    //   99: dup
    //   100: aload 11
    //   102: aload 13
    //   104: aload 14
    //   106: aload 17
    //   108: aload 18
    //   110: aconst_null
    //   111: invokespecial 99	com/squareup/okhttp/internal/Platform$Android:<init>	(Ljava/lang/Class;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Lcom/squareup/okhttp/internal/Platform$1;)V
    //   114: areturn
    //   115: astore 8
    //   117: ldc 101
    //   119: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   122: astore 10
    //   124: aload 10
    //   126: astore 11
    //   128: goto -117 -> 11
    //   131: astore 15
    //   133: aconst_null
    //   134: astore 16
    //   136: aload 16
    //   138: astore 17
    //   140: aconst_null
    //   141: astore 18
    //   143: goto -47 -> 96
    //   146: astore_0
    //   147: ldc 103
    //   149: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   152: astore_3
    //   153: new 105	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   160: ldc 103
    //   162: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc 112
    //   167: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   176: astore 4
    //   178: new 105	java/lang/StringBuilder
    //   181: dup
    //   182: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   185: ldc 103
    //   187: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: ldc 118
    //   192: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   201: astore 5
    //   203: new 105	java/lang/StringBuilder
    //   206: dup
    //   207: invokespecial 106	java/lang/StringBuilder:<init>	()V
    //   210: ldc 103
    //   212: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: ldc 120
    //   217: invokevirtual 110	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   226: astore 6
    //   228: new 122	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform
    //   231: dup
    //   232: aload_3
    //   233: ldc 124
    //   235: iconst_2
    //   236: anewarray 68	java/lang/Class
    //   239: dup
    //   240: iconst_0
    //   241: ldc 126
    //   243: aastore
    //   244: dup
    //   245: iconst_1
    //   246: aload 4
    //   248: aastore
    //   249: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   252: aload_3
    //   253: ldc 128
    //   255: iconst_1
    //   256: anewarray 68	java/lang/Class
    //   259: dup
    //   260: iconst_0
    //   261: ldc 126
    //   263: aastore
    //   264: invokevirtual 84	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   267: aload 5
    //   269: aload 6
    //   271: invokespecial 131	com/squareup/okhttp/internal/Platform$JdkWithJettyNpnPlatform:<init>	(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;Ljava/lang/Class;Ljava/lang/Class;)V
    //   274: astore 7
    //   276: aload 7
    //   278: areturn
    //   279: astore_2
    //   280: new 2	com/squareup/okhttp/internal/Platform
    //   283: dup
    //   284: invokespecial 132	com/squareup/okhttp/internal/Platform:<init>	()V
    //   287: areturn
    //   288: astore_1
    //   289: goto -9 -> 280
    //   292: astore 9
    //   294: goto -147 -> 147
    //   297: astore 20
    //   299: goto -163 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	115	java/lang/ClassNotFoundException
    //   53	71	131	java/lang/NoSuchMethodException
    //   0	7	146	java/lang/NoSuchMethodException
    //   11	53	146	java/lang/NoSuchMethodException
    //   96	115	146	java/lang/NoSuchMethodException
    //   117	124	146	java/lang/NoSuchMethodException
    //   147	276	279	java/lang/ClassNotFoundException
    //   147	276	288	java/lang/NoSuchMethodException
    //   11	53	292	java/lang/ClassNotFoundException
    //   53	71	292	java/lang/ClassNotFoundException
    //   75	88	292	java/lang/ClassNotFoundException
    //   96	115	292	java/lang/ClassNotFoundException
    //   117	124	292	java/lang/ClassNotFoundException
    //   75	88	297	java/lang/NoSuchMethodException
  }

  public static Platform get()
  {
    return PLATFORM;
  }

  public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
    throws IOException
  {
    paramSocket.connect(paramInetSocketAddress, paramInt);
  }

  public void enableTlsExtensions(SSLSocket paramSSLSocket, String paramString)
  {
  }

  public ByteString getNpnSelectedProtocol(SSLSocket paramSSLSocket)
  {
    return null;
  }

  public String getPrefix()
  {
    return "OkHttp";
  }

  public void logW(String paramString)
  {
    System.out.println(paramString);
  }

  public OutputStream newDeflaterOutputStream(OutputStream paramOutputStream, Deflater paramDeflater, boolean paramBoolean)
  {
    try
    {
      Constructor localConstructor = this.deflaterConstructor;
      if (localConstructor == null)
      {
        Class[] arrayOfClass = new Class[3];
        arrayOfClass[0] = OutputStream.class;
        arrayOfClass[1] = Deflater.class;
        arrayOfClass[2] = Boolean.TYPE;
        localConstructor = DeflaterOutputStream.class.getConstructor(arrayOfClass);
        this.deflaterConstructor = localConstructor;
      }
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramOutputStream;
      arrayOfObject[1] = paramDeflater;
      arrayOfObject[2] = Boolean.valueOf(paramBoolean);
      OutputStream localOutputStream = (OutputStream)localConstructor.newInstance(arrayOfObject);
      return localOutputStream;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new UnsupportedOperationException("Cannot SPDY; no SYNC_FLUSH available");
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      if ((localInvocationTargetException.getCause() instanceof RuntimeException));
      for (RuntimeException localRuntimeException = (RuntimeException)localInvocationTargetException.getCause(); ; localRuntimeException = new RuntimeException(localInvocationTargetException.getCause()))
        throw localRuntimeException;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new RuntimeException(localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
    }
    throw new AssertionError();
  }

  public void setNpnProtocols(SSLSocket paramSSLSocket, List<Protocol> paramList)
  {
  }

  public void supportTlsIntolerantServer(SSLSocket paramSSLSocket)
  {
    paramSSLSocket.setEnabledProtocols(new String[] { "SSLv3" });
  }

  public void tagSocket(Socket paramSocket)
    throws SocketException
  {
  }

  public URI toUriLenient(URL paramURL)
    throws URISyntaxException
  {
    return paramURL.toURI();
  }

  public void untagSocket(Socket paramSocket)
    throws SocketException
  {
  }

  private static class Android extends Platform
  {
    private final Method getNpnSelectedProtocol;
    protected final Class<?> openSslSocketClass;
    private final Method setHostname;
    private final Method setNpnProtocols;
    private final Method setUseSessionTickets;

    private Android(Class<?> paramClass, Method paramMethod1, Method paramMethod2, Method paramMethod3, Method paramMethod4)
    {
      this.openSslSocketClass = paramClass;
      this.setUseSessionTickets = paramMethod1;
      this.setHostname = paramMethod2;
      this.setNpnProtocols = paramMethod3;
      this.getNpnSelectedProtocol = paramMethod4;
    }

    public void connectSocket(Socket paramSocket, InetSocketAddress paramInetSocketAddress, int paramInt)
      throws IOException
    {
      IOException localIOException;
      try
      {
        paramSocket.connect(paramInetSocketAddress, paramInt);
        return;
      }
      catch (SecurityException localSecurityException)
      {
        localIOException = new IOException("Exception in connect");
        localIOException.initCause(localSecurityException);
      }
      throw localIOException;
    }

    public void enableTlsExtensions(SSLSocket paramSSLSocket, String paramString)
    {
      super.enableTlsExtensions(paramSSLSocket, paramString);
      if (!this.openSslSocketClass.isInstance(paramSSLSocket))
        return;
      try
      {
        Method localMethod = this.setUseSessionTickets;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(true);
        localMethod.invoke(paramSSLSocket, arrayOfObject);
        this.setHostname.invoke(paramSSLSocket, new Object[] { paramString });
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new RuntimeException(localInvocationTargetException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
      throw new AssertionError(localIllegalAccessException);
    }

    public ByteString getNpnSelectedProtocol(SSLSocket paramSSLSocket)
    {
      if (this.getNpnSelectedProtocol == null)
        return null;
      if (!this.openSslSocketClass.isInstance(paramSSLSocket))
        return null;
      try
      {
        byte[] arrayOfByte = (byte[])(byte[])this.getNpnSelectedProtocol.invoke(paramSSLSocket, new Object[0]);
        if (arrayOfByte == null)
          return null;
        ByteString localByteString = ByteString.of(arrayOfByte);
        return localByteString;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new RuntimeException(localInvocationTargetException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
      throw new AssertionError(localIllegalAccessException);
    }

    public void setNpnProtocols(SSLSocket paramSSLSocket, List<Protocol> paramList)
    {
      if (this.setNpnProtocols == null);
      do
        return;
      while (!this.openSslSocketClass.isInstance(paramSSLSocket));
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = concatLengthPrefixed(paramList);
        this.setNpnProtocols.invoke(paramSSLSocket, arrayOfObject);
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError(localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
      }
      throw new RuntimeException(localInvocationTargetException);
    }
  }

  private static class JdkWithJettyNpnPlatform extends Platform
  {
    private final Class<?> clientProviderClass;
    private final Method getMethod;
    private final Method putMethod;
    private final Class<?> serverProviderClass;

    public JdkWithJettyNpnPlatform(Method paramMethod1, Method paramMethod2, Class<?> paramClass1, Class<?> paramClass2)
    {
      this.putMethod = paramMethod1;
      this.getMethod = paramMethod2;
      this.clientProviderClass = paramClass1;
      this.serverProviderClass = paramClass2;
    }

    public ByteString getNpnSelectedProtocol(SSLSocket paramSSLSocket)
    {
      Object localObject;
      try
      {
        Platform.JettyNpnProvider localJettyNpnProvider = (Platform.JettyNpnProvider)Proxy.getInvocationHandler(this.getMethod.invoke(null, new Object[] { paramSSLSocket }));
        if ((!Platform.JettyNpnProvider.access$100(localJettyNpnProvider)) && (Platform.JettyNpnProvider.access$200(localJettyNpnProvider) == null))
        {
          Logger.getLogger("com.squareup.okhttp.OkHttpClient").log(Level.INFO, "NPN callback dropped so SPDY is disabled. Is npn-boot on the boot class path?");
          return null;
        }
        if (Platform.JettyNpnProvider.access$100(localJettyNpnProvider))
        {
          localObject = null;
        }
        else
        {
          ByteString localByteString = ByteString.encodeUtf8(Platform.JettyNpnProvider.access$200(localJettyNpnProvider));
          localObject = localByteString;
        }
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new AssertionError();
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new AssertionError();
      }
      return localObject;
    }

    public void setNpnProtocols(SSLSocket paramSSLSocket, List<Protocol> paramList)
    {
      try
      {
        ArrayList localArrayList = new ArrayList(paramList.size());
        int i = paramList.size();
        for (int j = 0; j < i; j++)
          localArrayList.add(((Protocol)paramList.get(j)).name.utf8());
        ClassLoader localClassLoader = Platform.class.getClassLoader();
        Class[] arrayOfClass = new Class[2];
        arrayOfClass[0] = this.clientProviderClass;
        arrayOfClass[1] = this.serverProviderClass;
        Object localObject = Proxy.newProxyInstance(localClassLoader, arrayOfClass, new Platform.JettyNpnProvider(localArrayList));
        this.putMethod.invoke(null, new Object[] { paramSSLSocket, localObject });
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new AssertionError(localInvocationTargetException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
      }
      throw new AssertionError(localIllegalAccessException);
    }
  }

  private static class JettyNpnProvider
    implements InvocationHandler
  {
    private final List<String> protocols;
    private String selected;
    private boolean unsupported;

    public JettyNpnProvider(List<String> paramList)
    {
      this.protocols = paramList;
    }

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
      throws Throwable
    {
      String str1 = paramMethod.getName();
      Class localClass = paramMethod.getReturnType();
      if (paramArrayOfObject == null)
        paramArrayOfObject = Util.EMPTY_STRING_ARRAY;
      if ((str1.equals("supports")) && (Boolean.TYPE == localClass))
        return Boolean.valueOf(true);
      if ((str1.equals("unsupported")) && (Void.TYPE == localClass))
      {
        this.unsupported = true;
        return null;
      }
      if ((str1.equals("protocols")) && (paramArrayOfObject.length == 0))
        return this.protocols;
      if ((str1.equals("selectProtocol")) && (String.class == localClass) && (paramArrayOfObject.length == 1) && ((paramArrayOfObject[0] == null) || ((paramArrayOfObject[0] instanceof List))))
      {
        List localList = (List)paramArrayOfObject[0];
        int i = localList.size();
        for (int j = 0; j < i; j++)
        {
          if (!this.protocols.contains(localList.get(j)))
            continue;
          String str3 = (String)localList.get(j);
          this.selected = str3;
          return str3;
        }
        String str2 = (String)this.protocols.get(0);
        this.selected = str2;
        return str2;
      }
      if ((str1.equals("protocolSelected")) && (paramArrayOfObject.length == 1))
      {
        this.selected = ((String)paramArrayOfObject[0]);
        return null;
      }
      return paramMethod.invoke(this, paramArrayOfObject);
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.Platform
 * JD-Core Version:    0.6.0
 */