package com.baidu.location;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;

final class g$3 extends Thread
{
  public void run()
  {
    while (true)
    {
      int i;
      try
      {
        localHttpPost = new HttpPost(j.jdMethod_do());
        localArrayList = new ArrayList();
        i = 0;
        if (i >= g.d().size())
          continue;
        if (g.jdMethod_char() != 1)
          continue;
        localArrayList.add(new BasicNameValuePair("cldc[" + i + "]", (String)g.d().get(i)));
      }
      catch (Exception localException)
      {
        HttpPost localHttpPost;
        ArrayList localArrayList;
        return;
        localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "utf-8"));
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
        localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(g.b()));
        localDefaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(g.b()));
        if (localDefaultHttpClient.execute(localHttpPost).getStatusLine().getStatusCode() != 200)
          continue;
        j.jdMethod_if(g.jdMethod_long(), "Status ok1...");
        g.d().clear();
        g.a(null);
        return;
        j.jdMethod_if(g.jdMethod_long(), "Status err1...");
        continue;
      }
      finally
      {
        g.jdMethod_do(false);
      }
      i++;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.baidu.location.g.3
 * JD-Core Version:    0.6.0
 */