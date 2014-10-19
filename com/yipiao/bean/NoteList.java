package com.yipiao.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class NoteList extends ArrayList<Note>
  implements Serializable
{
  private static final long serialVersionUID = -3063014443254001756L;

  public NoteList()
  {
  }

  public NoteList(int paramInt)
  {
    super(paramInt);
  }

  public NoteList(Collection<? extends Note> paramCollection)
  {
    super(paramCollection);
  }

  public NoteList clone()
  {
    return new NoteList(this);
  }

  public NoteList filter(String paramString)
  {
    if (paramString == null)
      return this;
    NoteList localNoteList = new NoteList();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      if (!localNote.filter(paramString))
        continue;
      localNoteList.add(localNote);
    }
    return localNoteList;
  }

  public Note get(int paramInt)
  {
    if (paramInt < 0)
      paramInt = 0;
    while (true)
    {
      return (Note)super.get(paramInt);
      if (paramInt < size())
        continue;
      paramInt = -1 + size();
    }
  }

  public Note getByCode(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      if (paramString.equals(localNote.getCode()))
        return localNote;
    }
    return null;
  }

  public Note getByCode(String paramString, Note paramNote)
  {
    Note localNote = getByCode(paramString);
    if (localNote != null)
      paramNote = localNote;
    return paramNote;
  }

  public Note getByName(String paramString)
  {
    if (paramString == null)
      return null;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      if (paramString.equals(localNote.getName()))
        return localNote;
    }
    return null;
  }

  public String[] getLabels()
  {
    String[] arrayOfString = new String[size()];
    for (int i = 0; i < arrayOfString.length; i++)
      arrayOfString[i] = get(i).toString();
    return arrayOfString;
  }

  public boolean[] getSelectArray(List<Note> paramList)
  {
    boolean[] arrayOfBoolean = new boolean[size()];
    for (int i = 0; i < arrayOfBoolean.length; i++)
      arrayOfBoolean[i] = paramList.contains(get(i));
    return arrayOfBoolean;
  }

  public NoteList getSelectNote(boolean[] paramArrayOfBoolean)
  {
    NoteList localNoteList = new NoteList();
    for (int i = 0; i < paramArrayOfBoolean.length; i++)
    {
      if (paramArrayOfBoolean[i] == 0)
        continue;
      localNoteList.add(get(i));
    }
    return localNoteList;
  }

  public String linkCode(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
      localStringBuilder.append(((Note)localIterator.next()).getCode()).append(paramString);
    return localStringBuilder.toString();
  }

  public String linkName(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      Note localNote = (Note)localIterator.next();
      localStringBuilder.append(paramString).append(localNote.getName());
    }
    localStringBuilder.delete(0, paramString.length());
    return localStringBuilder.toString();
  }

  public int posByCode(String paramString)
  {
    if (paramString == null)
    {
      i = -1;
      return i;
    }
    Iterator localIterator = iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label49;
      if (paramString.equals(((Note)localIterator.next()).getCode()))
        break;
    }
    label49: return -1;
  }

  public int posByName(String paramString)
  {
    if (paramString == null)
    {
      i = -1;
      return i;
    }
    Iterator localIterator = iterator();
    for (int i = 0; ; i++)
    {
      if (!localIterator.hasNext())
        break label49;
      if (paramString.equals(((Note)localIterator.next()).getName()))
        break;
    }
    label49: return -1;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.bean.NoteList
 * JD-Core Version:    0.6.0
 */