package cn.suanya.hotel.domain;

import cn.suanya.common.a.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListSectionGroup
{
  private List<ListSection> sectionGroup;

  public ListSectionGroup()
  {
    setSectionGroup(new ArrayList());
  }

  public int getCount()
  {
    Iterator localIterator = this.sectionGroup.iterator();
    int i = 0;
    while (localIterator.hasNext())
      i += ((ListSection)localIterator.next()).getSection().size();
    return i;
  }

  public p getItem(int paramInt)
  {
    Iterator localIterator = this.sectionGroup.iterator();
    while (localIterator.hasNext())
    {
      ListSection localListSection = (ListSection)localIterator.next();
      if (paramInt < localListSection.getSection().size())
        return (p)localListSection.getSection().get(paramInt);
      paramInt -= localListSection.getSection().size();
    }
    return null;
  }

  public int getPositionForSection(int paramInt)
  {
    Iterator localIterator = this.sectionGroup.iterator();
    int j;
    for (int i = 0; ; i = j)
    {
      ListSection localListSection;
      if (localIterator.hasNext())
      {
        localListSection = (ListSection)localIterator.next();
        if (paramInt > 0);
      }
      else
      {
        return i;
      }
      j = i + localListSection.getSection().size();
      paramInt--;
    }
  }

  public String[] getSection()
  {
    String[] arrayOfString = new String[this.sectionGroup.size()];
    Iterator localIterator = this.sectionGroup.iterator();
    for (int i = 0; localIterator.hasNext(); i++)
      arrayOfString[i] = ((ListSection)localIterator.next()).getKey();
    return arrayOfString;
  }

  public ListSection getSectionForPosition(int paramInt)
  {
    int i = getSectionIndexForPosition(paramInt);
    return (ListSection)this.sectionGroup.get(i);
  }

  public List<ListSection> getSectionGroup()
  {
    return this.sectionGroup;
  }

  public int getSectionIndexForPosition(int paramInt)
  {
    Iterator localIterator = this.sectionGroup.iterator();
    for (int i = 0; ; i++)
      if (localIterator.hasNext())
      {
        paramInt -= ((ListSection)localIterator.next()).getSection().size();
        if (paramInt >= 0)
          continue;
      }
      else
      {
        return i;
      }
  }

  public boolean isFirstItem(int paramInt)
  {
    Iterator localIterator = this.sectionGroup.iterator();
    while (localIterator.hasNext())
    {
      ListSection localListSection = (ListSection)localIterator.next();
      if (paramInt == 0)
        return true;
      paramInt -= localListSection.getSection().size();
    }
    return false;
  }

  public void setSectionGroup(List<ListSection> paramList)
  {
    this.sectionGroup = paramList;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.hotel.domain.ListSectionGroup
 * JD-Core Version:    0.6.0
 */