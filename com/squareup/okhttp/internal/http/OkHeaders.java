package com.squareup.okhttp.internal.http;

import com.squareup.okhttp.internal.Platform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class OkHeaders
{
  private static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator()
  {
    public int compare(String paramString1, String paramString2)
    {
      if (paramString1 == paramString2)
        return 0;
      if (paramString1 == null)
        return -1;
      if (paramString2 == null)
        return 1;
      return String.CASE_INSENSITIVE_ORDER.compare(paramString1, paramString2);
    }
  };
  static final String PREFIX = Platform.get().getPrefix();
  public static final String RECEIVED_MILLIS;
  public static final String RESPONSE_SOURCE;
  public static final String SELECTED_PROTOCOL;
  public static final String SENT_MILLIS = PREFIX + "-Sent-Millis";

  static
  {
    RECEIVED_MILLIS = PREFIX + "-Received-Millis";
    RESPONSE_SOURCE = PREFIX + "-Response-Source";
    SELECTED_PROTOCOL = PREFIX + "-Selected-Protocol";
  }

  public static void addCookies(Request.Builder paramBuilder, Map<String, List<String>> paramMap)
  {
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if (((!"Cookie".equalsIgnoreCase(str)) && (!"Cookie2".equalsIgnoreCase(str))) || (((List)localEntry.getValue()).isEmpty()))
        continue;
      paramBuilder.addHeader(str, buildCookieHeader((List)localEntry.getValue()));
    }
  }

  private static String buildCookieHeader(List<String> paramList)
  {
    if (paramList.size() == 1)
      return (String)paramList.get(0);
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; i < paramList.size(); i++)
    {
      if (i > 0)
        localStringBuilder.append("; ");
      localStringBuilder.append((String)paramList.get(i));
    }
    return localStringBuilder.toString();
  }

  public static long contentLength(Headers paramHeaders)
  {
    return stringToLong(paramHeaders.get("Content-Length"));
  }

  public static long contentLength(Request paramRequest)
  {
    return contentLength(paramRequest.headers());
  }

  public static long contentLength(Response paramResponse)
  {
    return contentLength(paramResponse.headers());
  }

  private static long stringToLong(String paramString)
  {
    if (paramString == null)
      return -1L;
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return -1L;
  }

  public static Map<String, List<String>> toMultimap(Headers paramHeaders, String paramString)
  {
    TreeMap localTreeMap = new TreeMap(FIELD_NAME_COMPARATOR);
    for (int i = 0; i < paramHeaders.size(); i++)
    {
      String str1 = paramHeaders.name(i);
      String str2 = paramHeaders.value(i);
      ArrayList localArrayList = new ArrayList();
      List localList = (List)localTreeMap.get(str1);
      if (localList != null)
        localArrayList.addAll(localList);
      localArrayList.add(str2);
      localTreeMap.put(str1, Collections.unmodifiableList(localArrayList));
    }
    if (paramString != null)
      localTreeMap.put(null, Collections.unmodifiableList(Collections.singletonList(paramString)));
    return Collections.unmodifiableMap(localTreeMap);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.OkHeaders
 * JD-Core Version:    0.6.0
 */