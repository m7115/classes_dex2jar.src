package com.squareup.okhttp;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MediaType
{
  private static final Pattern PARAMETER;
  private static final String QUOTED = "\"([^\"]*)\"";
  private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
  private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
  private final String charset;
  private final String mediaType;
  private final String subtype;
  private final String type;

  static
  {
    PARAMETER = Pattern.compile(";\\s*([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\")");
  }

  private MediaType(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.mediaType = paramString1;
    this.type = paramString2;
    this.subtype = paramString3;
    this.charset = paramString4;
  }

  public static MediaType parse(String paramString)
  {
    Matcher localMatcher1 = TYPE_SUBTYPE.matcher(paramString);
    if (!localMatcher1.lookingAt());
    String str1;
    String str2;
    String str3;
    while (true)
    {
      return null;
      str1 = localMatcher1.group(1).toLowerCase(Locale.US);
      str2 = localMatcher1.group(2).toLowerCase(Locale.US);
      Matcher localMatcher2 = PARAMETER.matcher(paramString);
      int i = localMatcher1.end();
      str3 = null;
      if (i >= paramString.length())
        break;
      localMatcher2.region(i, paramString.length());
      if (!localMatcher2.lookingAt())
        continue;
      String str4 = localMatcher2.group(1);
      if ((str4 == null) || (!str4.equalsIgnoreCase("charset")));
      while (true)
      {
        i = localMatcher2.end();
        break;
        if (str3 != null)
          throw new IllegalArgumentException("Multiple charsets: " + paramString);
        if (localMatcher2.group(2) != null)
        {
          str3 = localMatcher2.group(2);
          continue;
        }
        str3 = localMatcher2.group(3);
      }
    }
    return new MediaType(paramString, str1, str2, str3);
  }

  public Charset charset()
  {
    if (this.charset != null)
      return Charset.forName(this.charset);
    return null;
  }

  public Charset charset(Charset paramCharset)
  {
    if (this.charset != null)
      paramCharset = Charset.forName(this.charset);
    return paramCharset;
  }

  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof MediaType)) && (((MediaType)paramObject).mediaType.equals(this.mediaType));
  }

  public int hashCode()
  {
    return this.mediaType.hashCode();
  }

  public String subtype()
  {
    return this.subtype;
  }

  public String toString()
  {
    return this.mediaType;
  }

  public String type()
  {
    return this.type;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.MediaType
 * JD-Core Version:    0.6.0
 */