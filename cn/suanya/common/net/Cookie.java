package cn.suanya.common.net;

import java.io.Serializable;
import java.sql.Date;

public class Cookie
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected long lastUsed;
  protected String mComment;
  protected String mDomain;
  protected Date mExpiry;
  protected String mName;
  protected String mPath;
  protected boolean mSecure;
  protected String mValue;
  protected int mVersion;

  public Cookie()
  {
  }

  public Cookie(String paramString)
  {
    this.lastUsed = System.currentTimeMillis();
    String[] arrayOfString1 = paramString.split(";");
    int i = arrayOfString1.length;
    int j = 0;
    int k = 0;
    if (j < i)
    {
      String str1 = arrayOfString1[j];
      String str2 = "";
      String[] arrayOfString2 = str1.split("=");
      String str3 = arrayOfString2[0];
      if (str3 != null)
        str3 = str3.trim();
      if (arrayOfString2.length > 1)
        str2 = arrayOfString2[1];
      if (k == 0)
      {
        this.mName = str3;
        this.mValue = str2;
      }
      while (true)
      {
        int m = k + 1;
        j++;
        k = m;
        break;
        setProperty(str3, str2);
      }
    }
  }

  public long getLastUsed()
  {
    return this.lastUsed;
  }

  public String getmComment()
  {
    return this.mComment;
  }

  public String getmDomain()
  {
    return this.mDomain;
  }

  public Date getmExpiry()
  {
    return this.mExpiry;
  }

  public String getmName()
  {
    return this.mName;
  }

  public String getmPath()
  {
    return this.mPath;
  }

  public String getmValue()
  {
    return this.mValue;
  }

  public int getmVersion()
  {
    return this.mVersion;
  }

  public boolean ismSecure()
  {
    return this.mSecure;
  }

  public void setLastUsed(long paramLong)
  {
    this.lastUsed = paramLong;
  }

  public void setProperty(String paramString1, String paramString2)
  {
    if (paramString1.equalsIgnoreCase("Comment"))
      setmComment(paramString2);
    if ((!paramString1.equalsIgnoreCase("Discard")) || (paramString1.equalsIgnoreCase("Domain")))
      setmDomain(paramString2);
    if ((!paramString1.equalsIgnoreCase("Expires")) || ((!paramString1.equalsIgnoreCase("Max-Age")) || (paramString1.equalsIgnoreCase("Path"))))
      setmPath(paramString2);
    if ((!paramString1.equalsIgnoreCase("Secure")) || (paramString1.equalsIgnoreCase("Version")));
  }

  public void setmComment(String paramString)
  {
    this.mComment = paramString;
  }

  public void setmDomain(String paramString)
  {
    this.mDomain = paramString;
  }

  public void setmExpiry(Date paramDate)
  {
    this.mExpiry = paramDate;
  }

  public void setmName(String paramString)
  {
    this.mName = paramString;
  }

  public void setmPath(String paramString)
  {
    this.mPath = paramString;
  }

  public void setmSecure(boolean paramBoolean)
  {
    this.mSecure = paramBoolean;
  }

  public void setmValue(String paramString)
  {
    this.mValue = paramString;
  }

  public void setmVersion(int paramInt)
  {
    this.mVersion = paramInt;
  }

  public String toString()
  {
    return this.mName + "=" + this.mValue;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.net.Cookie
 * JD-Core Version:    0.6.0
 */