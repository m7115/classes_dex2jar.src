package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.util.Iterator;
import java.util.Stack;

final class MemoryRefStack
{
  private final Stack<ObjectTypePair> stack = new Stack();

  public boolean contains(ObjectTypePair paramObjectTypePair)
  {
    if (paramObjectTypePair == null)
      return false;
    Iterator localIterator = this.stack.iterator();
    while (localIterator.hasNext())
    {
      ObjectTypePair localObjectTypePair = (ObjectTypePair)localIterator.next();
      if ((localObjectTypePair.getObject() == paramObjectTypePair.getObject()) && (localObjectTypePair.type.equals(paramObjectTypePair.type)))
        return true;
    }
    return false;
  }

  public boolean isEmpty()
  {
    return this.stack.isEmpty();
  }

  public ObjectTypePair peek()
  {
    return (ObjectTypePair)this.stack.peek();
  }

  public ObjectTypePair pop()
  {
    return (ObjectTypePair)this.stack.pop();
  }

  public ObjectTypePair push(ObjectTypePair paramObjectTypePair)
  {
    .Gson.Preconditions.checkNotNull(paramObjectTypePair);
    return (ObjectTypePair)this.stack.push(paramObjectTypePair);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.MemoryRefStack
 * JD-Core Version:    0.6.0
 */