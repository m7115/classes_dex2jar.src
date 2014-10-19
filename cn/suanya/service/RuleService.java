package cn.suanya.service;

import android.content.res.AssetManager;
import cn.suanya.common.a.f;
import cn.suanya.common.a.l;
import cn.suanya.common.a.n;
import cn.suanya.common.a.t;
import cn.suanya.common.net.LogInfo;
import cn.suanya.common.ui.SYApplication;
import cn.suanya.rule.b.b;
import cn.suanya.rule.s;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.xml.sax.SAXException;

public class RuleService
{
  static RuleService ruleService;

  private InputStream change(InputStream paramInputStream)
    throws Exception
  {
    return new ByteArrayInputStream(t.b("1234567812345678", inputStreamtoString(paramInputStream)).getBytes("UTF-8"));
  }

  public static RuleService getInstance()
  {
    if (ruleService == null)
      ruleService = new RuleService();
    return ruleService;
  }

  private String inputStreamtoString(InputStream paramInputStream)
    throws Exception
  {
    return IOUtils.toString(paramInputStream);
  }

  private s loadDefautRule(String paramString)
    throws Exception
  {
    SYApplication localSYApplication = SYApplication.app;
    s locals = new s(paramString);
    if ((paramString != null) && (paramString.length() > 0))
    {
      String[] arrayOfString = paramString.split("\\|");
      for (int i = 0; i < arrayOfString.length; i++)
      {
        String str = arrayOfString[i];
        InputStream localInputStream = localSYApplication.getAssets().open(str);
        pase(locals, str, localInputStream);
        IOUtils.closeQuietly(localInputStream);
      }
    }
    return locals;
  }

  private void pase(s params, String paramString, InputStream paramInputStream)
    throws Exception
  {
    SAXParser localSAXParser = SAXParserFactory.newInstance().newSAXParser();
    b localb = new b(params);
    if (paramString.endsWith("xml"))
    {
      localSAXParser.parse(paramInputStream, localb);
      return;
    }
    localSAXParser.parse(change(paramInputStream), localb);
  }

  public s loadRule(String paramString)
    throws Exception
  {
    SYApplication localSYApplication = SYApplication.app;
    if ((paramString != null) && (paramString.length() > 0))
    {
      n.b("loadRule:" + paramString);
      String str1 = localSYApplication.launchInfo.optString(l.rule_url_name, l.rule_url_defaut);
      String str2 = localSYApplication.getFilesDir().getAbsolutePath();
      String[] arrayOfString = paramString.split("\\|");
      s locals = new s(paramString);
      int i = 0;
      while (i < arrayOfString.length)
      {
        String str3 = arrayOfString[i];
        InputStream localInputStream = f.a().a(str3, str2 + '/' + str3, str1 + '/' + str3);
        try
        {
          pase(locals, str3, localInputStream);
          new File(str2 + '/' + str3).deleteOnExit();
          IOUtils.closeQuietly(localInputStream);
          i++;
        }
        catch (SAXException localSAXException)
        {
          throw localSAXException;
        }
      }
      return locals;
    }
    return null;
  }

  public s loadRule(String paramString1, String paramString2)
  {
    try
    {
      s locals2 = loadRule(paramString1);
      return locals2;
    }
    catch (Exception localException1)
    {
      n.b(localException1);
      try
      {
        s locals1 = loadDefautRule(paramString2);
        return locals1;
      }
      catch (Exception localException2)
      {
        SYService.getInstance().asyncLog(new LogInfo("exceptionLoadRule", localException1));
      }
    }
    return null;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.service.RuleService
 * JD-Core Version:    0.6.0
 */