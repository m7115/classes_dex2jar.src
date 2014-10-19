package com.baidu.location;

import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

final class g$2 extends Thread
{
  public void run()
  {
    int i = g.jdMethod_new();
    while (true)
    {
      if (i > 0);
      try
      {
        HttpPost localHttpPost = new HttpPost(j.jdMethod_do());
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new BasicNameValuePair("bloc", g.jdMethod_if()));
        if (g.a() != null)
          localArrayList.add(new BasicNameValuePair("up", g.a()));
        localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
        localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.b()));
        localDefaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.b()));
        HttpProtocolParams.setUseExpectContinue(localDefaultHttpClient.getParams(), false);
        if (g.e() == 1)
        {
          HttpHost localHttpHost = new HttpHost(g.jdMethod_void(), g.g(), "http");
          localDefaultHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
        }
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpPost);
        int j = localHttpResponse.getStatusLine().getStatusCode();
        j.jdMethod_if(g.jdMethod_long(), "===status error : " + j);
        if (j == 200)
        {
          String str = EntityUtils.toString(localHttpResponse.getEntity(), "utf-8");
          Message localMessage1 = g.jdMethod_int().obtainMessage(26);
          localMessage1.obj = str;
          localMessage1.sendToTarget();
          g.jdMethod_do(null);
          if ((i <= 0) && (g.jdMethod_int() != null))
          {
            j.jdMethod_if(g.jdMethod_long(), "have tried 3 times...");
            g.jdMethod_int().obtainMessage(64).sendToTarget();
          }
          g.a(null);
          g.jdMethod_if(false);
          return;
        }
        localHttpPost.abort();
        Message localMessage2 = g.jdMethod_int().obtainMessage(65);
        localMessage2.obj = "HttpStatus error";
        localMessage2.sendToTarget();
        label361: i--;
      }
      catch (Exception localException)
      {
        break label361;
      }
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.g.2
 * JD-Core Version:    0.6.0
 */