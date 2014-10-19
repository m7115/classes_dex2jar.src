package cn.suanya.rule.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;

public class Each
{
  private Object element;
  private int index = -1;
  private List<Object> src = new ArrayList();

  public Each(Object paramObject)
  {
    if ((paramObject instanceof Collection))
      this.src.addAll((Collection)paramObject);
    while (true)
    {
      return;
      if ((paramObject instanceof JSONArray))
      {
        JSONArray localJSONArray = (JSONArray)paramObject;
        while (i < localJSONArray.length())
        {
          this.src.add(localJSONArray.opt(i));
          i++;
        }
        continue;
      }
      if (!paramObject.getClass().isArray())
        continue;
      Object[] arrayOfObject = (Object[])(Object[])paramObject;
      while (i < arrayOfObject.length)
      {
        this.src.add(arrayOfObject[i]);
        i++;
      }
    }
  }

  public Object getElement()
  {
    return this.element;
  }

  public int getIndex()
  {
    return this.index;
  }

  public Object next()
  {
    this.index = (1 + this.index);
    if (this.src.isEmpty());
    do
      return null;
    while (this.src.size() <= this.index);
    this.element = this.src.get(this.index);
    return this.element;
  }

  public void setElement(Object paramObject)
  {
    this.element = paramObject;
  }

  public void setIndex(int paramInt)
  {
    this.index = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.rule.bean.Each
 * JD-Core Version:    0.6.0
 */