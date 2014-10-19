package com.tencent.mm.sdk.platformtools;

import android.content.Context;
import android.os.Build.VERSION;
import java.util.List;

public final class d
{
  private static final int a = 17;

  public static String a(List<b> paramList)
  {
    Object localObject1 = "";
    if ((paramList == null) || (paramList.size() <= 0))
    {
      localObject1 = "";
      return localObject1;
    }
    int i = 0;
    label23: String str4;
    if (i < paramList.size())
    {
      if ((paramList.get(i) == null) || (((b)paramList.get(i)).a.length() != a))
        break label213;
      String str1 = (String)localObject1 + "<mac ";
      String str2 = str1 + "macDbm=\"" + ((b)paramList.get(i)).b + "\"";
      String str3 = str2 + ">";
      str4 = str3 + ((b)paramList.get(i)).a;
    }
    label213: for (Object localObject2 = str4 + "</mac>"; ; localObject2 = localObject1)
    {
      i++;
      localObject1 = localObject2;
      break label23;
      break;
    }
  }

  public static List<a> a(Context paramContext)
  {
    if (Integer.valueOf(Build.VERSION.SDK).intValue() >= 5)
      return new g().a(paramContext);
    return new e().a(paramContext);
  }

  public static String b(List<a> paramList)
  {
    Object localObject;
    if ((paramList == null) || (paramList.size() <= 0))
      localObject = "";
    while (true)
    {
      return localObject;
      localObject = "";
      int i = 0;
      while (i < paramList.size())
      {
        String str1 = (String)localObject + "<cell ";
        String str2 = str1 + "mcc=\"" + ((a)paramList.get(i)).a + "\" ";
        String str3 = str2 + "mnc=\"" + ((a)paramList.get(i)).b + "\" ";
        String str4 = str3 + "lac=\"" + ((a)paramList.get(i)).c + "\" ";
        String str5 = str4 + "type=\"" + ((a)paramList.get(i)).e + "\" ";
        String str6 = str5 + "stationId=\"" + ((a)paramList.get(i)).f + "\" ";
        String str7 = str6 + "networkId=\"" + ((a)paramList.get(i)).g + "\" ";
        String str8 = str7 + "systemId=\"" + ((a)paramList.get(i)).h + "\" ";
        String str9 = str8 + "dbm=\"" + ((a)paramList.get(i)).i + "\" ";
        String str10 = str9 + " >";
        String str11 = str10 + ((a)paramList.get(i)).d;
        String str12 = str11 + "</cell>";
        i++;
        localObject = str12;
      }
    }
  }

  public static class a
  {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;

    public a(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramString3;
      this.e = paramString6;
      this.d = paramString4;
      this.f = paramString7;
      this.g = paramString8;
      this.h = paramString9;
      this.i = paramString5;
    }
  }

  public static class b
  {
    public String a;
    public String b;

    public b(String paramString1, String paramString2)
    {
      this.a = paramString1;
      this.b = paramString2;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.platformtools.d
 * JD-Core Version:    0.6.0
 */