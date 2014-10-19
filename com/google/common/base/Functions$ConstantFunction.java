package com.google.common.base;

import java.io.Serializable;
import javax.annotation.Nullable;

class Functions$ConstantFunction<E>
  implements Function<Object, E>, Serializable
{
  private static final long serialVersionUID;
  private final E value;

  public Functions$ConstantFunction(@Nullable E paramE)
  {
    this.value = paramE;
  }

  public E apply(@Nullable Object paramObject)
  {
    return this.value;
  }

  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof ConstantFunction))
    {
      ConstantFunction localConstantFunction = (ConstantFunction)paramObject;
      return Objects.equal(this.value, localConstantFunction.value);
    }
    return false;
  }

  public int hashCode()
  {
    if (this.value == null)
      return 0;
    return this.value.hashCode();
  }

  public String toString()
  {
    return "constant(" + this.value + ")";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.common.base.Functions.ConstantFunction
 * JD-Core Version:    0.6.0
 */