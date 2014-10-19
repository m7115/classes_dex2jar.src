package com.baidu.mapapi.cloud;

import android.content.ContentValues;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class RegionSearchInfo
{
  public String ak;
  public int cityID;
  public String cityName;
  public ContentValues filter = new ContentValues();
  public int pageIndex = 0;
  public int pageSize = 10;
  public String queryWords;
  public int scope = 1;
  public String sn;
  public int timeStamp = 0;

  String a()
  {
    if (this.queryWords == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder("?");
    if (this.queryWords != null);
    try
    {
      localStringBuilder.append("q=").append(URLEncoder.encode(this.queryWords, "UTF-8"));
      if (this.cityID != 0)
      {
        localStringBuilder.append("&region=").append(this.cityID);
        if (this.filter.size() <= 0)
          break label272;
        localStringBuilder.append("&filter=");
        Iterator localIterator = this.filter.valueSet().iterator();
        if (!localIterator.hasNext())
          break label272;
        Map.Entry localEntry1 = (Map.Entry)localIterator.next();
        String str1 = (String)localEntry1.getKey();
        Object localObject1 = localEntry1.getValue();
        localStringBuilder.append(str1).append(':').append(localObject1);
        while (localIterator.hasNext())
        {
          Map.Entry localEntry2 = (Map.Entry)localIterator.next();
          String str2 = (String)localEntry2.getKey();
          Object localObject2 = localEntry2.getValue();
          localStringBuilder.append('|').append(str2).append(':').append(localObject2);
        }
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException2)
    {
      while (true)
      {
        localUnsupportedEncodingException2.printStackTrace();
        continue;
        if (this.cityName == null)
          continue;
        try
        {
          localStringBuilder.append("&region=").append(URLEncoder.encode(this.cityName, "UTF-8"));
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException1)
        {
          localUnsupportedEncodingException1.printStackTrace();
        }
      }
      label272: localStringBuilder.append("&page_index=").append(this.pageIndex);
      localStringBuilder.append("&scope=").append(this.scope);
      localStringBuilder.append("&page_size=").append(this.pageSize);
      if (this.ak != null)
        localStringBuilder.append("&ak=").append(this.ak);
      if (this.sn != null)
      {
        localStringBuilder.append("&sn=").append(this.sn);
        localStringBuilder.append("&timestamp=").append(this.timeStamp);
      }
    }
    return localStringBuilder.toString();
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.mapapi.cloud.RegionSearchInfo
 * JD-Core Version:    0.6.0
 */