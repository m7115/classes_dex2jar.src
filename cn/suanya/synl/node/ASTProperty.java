package cn.suanya.synl.node;

import cn.suanya.common.bean.NameValue;
import cn.suanya.common.bean.NameValueList;
import cn.suanya.synl.OgnlRuntime;
import java.util.Map;
import org.json.JSONObject;

public class ASTProperty extends DataNote
{
  public ASTProperty(String paramString)
  {
    super(paramString);
  }

  public String getProperty()
  {
    return this.expression;
  }

  protected Object getValueBody(Object paramObject)
    throws Exception
  {
    if (paramObject == null)
      throw new NoSuchFieldException(getProperty());
    Object localObject;
    if ((paramObject instanceof Map))
      localObject = ((Map)paramObject).get(getProperty());
    do
    {
      return localObject;
      if ((paramObject instanceof JSONObject))
      {
        if (((JSONObject)paramObject).isNull(getProperty()))
          return null;
        return ((JSONObject)paramObject).opt(getProperty());
      }
      if ((paramObject instanceof NameValueList))
      {
        NameValue localNameValue = ((NameValueList)paramObject).getByName(getProperty());
        if (localNameValue == null)
          return null;
        return localNameValue.getValue();
      }
      localObject = OgnlRuntime.getMethodValue(paramObject, getProperty());
    }
    while (localObject != OgnlRuntime.NotFound);
    return OgnlRuntime.getFieldValue(paramObject, getProperty());
  }

  protected void setValueBody(Object paramObject1, Object paramObject2)
    throws Exception
  {
    if (paramObject1 == null)
      throw new NoSuchFieldException(getProperty());
    if ((paramObject1 instanceof Map))
      ((Map)paramObject1).put(getProperty(), paramObject2);
    do
    {
      return;
      if ((paramObject1 instanceof JSONObject))
      {
        ((JSONObject)paramObject1).put(getProperty(), paramObject2);
        return;
      }
      if (!(paramObject1 instanceof NameValueList))
        continue;
      NameValue localNameValue = ((NameValueList)paramObject1).getByName(getProperty());
      String str = null;
      if (paramObject2 != null)
        str = paramObject2.toString();
      if (localNameValue != null)
      {
        localNameValue.setValue(str);
        return;
      }
      ((NameValueList)paramObject1).add(new NameValue(getProperty(), str));
      return;
    }
    while (OgnlRuntime.setMethodValue(paramObject1, getProperty(), paramObject2));
    OgnlRuntime.setFieldValue(paramObject1, getProperty(), paramObject2);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.synl.node.ASTProperty
 * JD-Core Version:    0.6.0
 */