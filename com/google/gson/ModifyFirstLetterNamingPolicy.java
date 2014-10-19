package com.google.gson;

import com.google.gson.internal..Gson.Preconditions;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;

final class ModifyFirstLetterNamingPolicy extends RecursiveFieldNamingPolicy
{
  private final LetterModifier letterModifier;

  ModifyFirstLetterNamingPolicy(LetterModifier paramLetterModifier)
  {
    this.letterModifier = ((LetterModifier).Gson.Preconditions.checkNotNull(paramLetterModifier));
  }

  private String modifyString(char paramChar, String paramString, int paramInt)
  {
    if (paramInt < paramString.length())
      return paramChar + paramString.substring(paramInt);
    return String.valueOf(paramChar);
  }

  protected String translateName(String paramString, Type paramType, Collection<Annotation> paramCollection)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    char c = paramString.charAt(0);
    int i = 0;
    if ((i >= -1 + paramString.length()) || (Character.isLetter(c)))
    {
      if (i != paramString.length())
        break label77;
      paramString = localStringBuilder.toString();
    }
    label77: int j;
    do
    {
      return paramString;
      localStringBuilder.append(c);
      i++;
      c = paramString.charAt(i);
      break;
      LetterModifier localLetterModifier1 = this.letterModifier;
      LetterModifier localLetterModifier2 = LetterModifier.UPPER;
      j = 0;
      if (localLetterModifier1 == localLetterModifier2)
        j = 1;
      if ((j != 0) && (!Character.isUpperCase(c)))
        return modifyString(Character.toUpperCase(c), paramString, i + 1);
    }
    while ((j != 0) || (!Character.isUpperCase(c)));
    return modifyString(Character.toLowerCase(c), paramString, i + 1);
  }

  public static enum LetterModifier
  {
    static
    {
      LOWER = new LetterModifier("LOWER", 1);
      LetterModifier[] arrayOfLetterModifier = new LetterModifier[2];
      arrayOfLetterModifier[0] = UPPER;
      arrayOfLetterModifier[1] = LOWER;
      $VALUES = arrayOfLetterModifier;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.ModifyFirstLetterNamingPolicy
 * JD-Core Version:    0.6.0
 */