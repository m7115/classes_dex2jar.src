package com.yipiao.bean;

import cn.suanya.common.net.LogInfo;
import com.yipiao.service.YipiaoService;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

public class UserInfoComparator
  implements Comparator<UserInfo>
{
  RuleBasedCollator collator = (RuleBasedCollator)Collator.getInstance(Locale.CHINA);

  public int compare(UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    try
    {
      int i = this.collator.compare(paramUserInfo1.getName(), paramUserInfo2.getName());
      return i;
    }
    catch (Exception localException)
    {
      YipiaoService.getInstance().log(new LogInfo("sortErrorUserInfo", localException));
    }
    return 0;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.UserInfoComparator
 * JD-Core Version:    0.6.0
 */