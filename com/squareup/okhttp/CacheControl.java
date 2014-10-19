package com.squareup.okhttp;

import com.squareup.okhttp.internal.http.HeaderParser;
import com.squareup.okhttp.internal.http.Headers;

public final class CacheControl
{
  private final boolean isPublic;
  private final int maxAgeSeconds;
  private final int maxStaleSeconds;
  private final int minFreshSeconds;
  private final boolean mustRevalidate;
  private final boolean noCache;
  private final boolean noStore;
  private final boolean onlyIfCached;
  private final int sMaxAgeSeconds;

  private CacheControl(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3, boolean paramBoolean4, int paramInt3, int paramInt4, boolean paramBoolean5)
  {
    this.noCache = paramBoolean1;
    this.noStore = paramBoolean2;
    this.maxAgeSeconds = paramInt1;
    this.sMaxAgeSeconds = paramInt2;
    this.isPublic = paramBoolean3;
    this.mustRevalidate = paramBoolean4;
    this.maxStaleSeconds = paramInt3;
    this.minFreshSeconds = paramInt4;
    this.onlyIfCached = paramBoolean5;
  }

  public static CacheControl parse(Headers paramHeaders)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    int i = -1;
    int j = -1;
    boolean bool3 = false;
    boolean bool4 = false;
    int k = -1;
    int m = -1;
    boolean bool5 = false;
    int n = 0;
    if (n < paramHeaders.size())
      if ((paramHeaders.name(n).equalsIgnoreCase("Cache-Control")) || (paramHeaders.name(n).equalsIgnoreCase("Pragma")));
    while (true)
    {
      n++;
      break;
      String str1 = paramHeaders.value(n);
      boolean bool6 = bool1;
      int i1 = 0;
      while (i1 < str1.length())
      {
        int i2 = HeaderParser.skipUntil(str1, i1, "=,;");
        String str2 = str1.substring(i1, i2).trim();
        Object localObject;
        if ((i2 == str1.length()) || (str1.charAt(i2) == ',') || (str1.charAt(i2) == ';'))
        {
          i1 = i2 + 1;
          localObject = null;
        }
        while (true)
        {
          if (!"no-cache".equalsIgnoreCase(str2))
            break label288;
          bool6 = true;
          break;
          int i3 = HeaderParser.skipWhitespace(str1, i2 + 1);
          if ((i3 < str1.length()) && (str1.charAt(i3) == '"'))
          {
            int i5 = i3 + 1;
            int i6 = HeaderParser.skipUntil(str1, i5, "\"");
            String str4 = str1.substring(i5, i6);
            i1 = i6 + 1;
            localObject = str4;
            continue;
          }
          int i4 = HeaderParser.skipUntil(str1, i3, ",;");
          String str3 = str1.substring(i3, i4).trim();
          i1 = i4;
          localObject = str3;
        }
        label288: if ("no-store".equalsIgnoreCase(str2))
        {
          bool2 = true;
          continue;
        }
        if ("max-age".equalsIgnoreCase(str2))
        {
          i = HeaderParser.parseSeconds(localObject);
          continue;
        }
        if ("s-maxage".equalsIgnoreCase(str2))
        {
          j = HeaderParser.parseSeconds(localObject);
          continue;
        }
        if ("public".equalsIgnoreCase(str2))
        {
          bool3 = true;
          continue;
        }
        if ("must-revalidate".equalsIgnoreCase(str2))
        {
          bool4 = true;
          continue;
        }
        if ("max-stale".equalsIgnoreCase(str2))
        {
          k = HeaderParser.parseSeconds(localObject);
          continue;
        }
        if ("min-fresh".equalsIgnoreCase(str2))
        {
          m = HeaderParser.parseSeconds(localObject);
          continue;
        }
        if (!"only-if-cached".equalsIgnoreCase(str2))
          continue;
        bool5 = true;
        continue;
        return new CacheControl(bool1, bool2, i, j, bool3, bool4, k, m, bool5);
      }
      bool1 = bool6;
    }
  }

  public boolean isPublic()
  {
    return this.isPublic;
  }

  public int maxAgeSeconds()
  {
    return this.maxAgeSeconds;
  }

  public int maxStaleSeconds()
  {
    return this.maxStaleSeconds;
  }

  public int minFreshSeconds()
  {
    return this.minFreshSeconds;
  }

  public boolean mustRevalidate()
  {
    return this.mustRevalidate;
  }

  public boolean noCache()
  {
    return this.noCache;
  }

  public boolean noStore()
  {
    return this.noStore;
  }

  public boolean onlyIfCached()
  {
    return this.onlyIfCached;
  }

  public int sMaxAgeSeconds()
  {
    return this.sMaxAgeSeconds;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.CacheControl
 * JD-Core Version:    0.6.0
 */