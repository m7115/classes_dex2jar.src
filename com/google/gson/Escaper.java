package com.google.gson;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class Escaper
{
  private static final char[] HEX_CHARS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  private static final Set<Character> HTML_ESCAPE_CHARS;
  private static final Set<Character> JS_ESCAPE_CHARS;
  private final boolean escapeHtmlCharacters;

  static
  {
    HashSet localHashSet1 = new HashSet();
    localHashSet1.add(Character.valueOf('"'));
    localHashSet1.add(Character.valueOf('\\'));
    JS_ESCAPE_CHARS = Collections.unmodifiableSet(localHashSet1);
    HashSet localHashSet2 = new HashSet();
    localHashSet2.add(Character.valueOf('<'));
    localHashSet2.add(Character.valueOf('>'));
    localHashSet2.add(Character.valueOf('&'));
    localHashSet2.add(Character.valueOf('='));
    localHashSet2.add(Character.valueOf('\''));
    HTML_ESCAPE_CHARS = Collections.unmodifiableSet(localHashSet2);
  }

  Escaper(boolean paramBoolean)
  {
    this.escapeHtmlCharacters = paramBoolean;
  }

  private static void appendHexJavaScriptRepresentation(int paramInt, Appendable paramAppendable)
    throws IOException
  {
    if (Character.isSupplementaryCodePoint(paramInt))
    {
      char[] arrayOfChar = Character.toChars(paramInt);
      appendHexJavaScriptRepresentation(arrayOfChar[0], paramAppendable);
      appendHexJavaScriptRepresentation(arrayOfChar[1], paramAppendable);
      return;
    }
    paramAppendable.append("\\u").append(HEX_CHARS[(0xF & paramInt >>> 12)]).append(HEX_CHARS[(0xF & paramInt >>> 8)]).append(HEX_CHARS[(0xF & paramInt >>> 4)]).append(HEX_CHARS[(paramInt & 0xF)]);
  }

  private void escapeJsonString(CharSequence paramCharSequence, StringBuilder paramStringBuilder)
    throws IOException
  {
    int i = 0;
    int j = paramCharSequence.length();
    int k = 0;
    if (k < j)
    {
      int m = Character.codePointAt(paramCharSequence, k);
      int n = Character.charCount(m);
      if ((!isControlCharacter(m)) && (!mustEscapeCharInJsString(m)));
      while (true)
      {
        k += n;
        break;
        paramStringBuilder.append(paramCharSequence, i, k);
        i = k + n;
        switch (m)
        {
        default:
          appendHexJavaScriptRepresentation(m, paramStringBuilder);
          break;
        case 8:
          paramStringBuilder.append("\\b");
          break;
        case 9:
          paramStringBuilder.append("\\t");
          break;
        case 10:
          paramStringBuilder.append("\\n");
          break;
        case 12:
          paramStringBuilder.append("\\f");
          break;
        case 13:
          paramStringBuilder.append("\\r");
          break;
        case 92:
          paramStringBuilder.append("\\\\");
          break;
        case 47:
          paramStringBuilder.append("\\/");
          break;
        case 34:
          paramStringBuilder.append("\\\"");
        }
      }
    }
    paramStringBuilder.append(paramCharSequence, i, j);
  }

  private static boolean isControlCharacter(int paramInt)
  {
    return (paramInt < 32) || (paramInt == 8232) || (paramInt == 8233) || ((paramInt >= 127) && (paramInt <= 159));
  }

  private boolean mustEscapeCharInJsString(int paramInt)
  {
    boolean bool1 = Character.isSupplementaryCodePoint(paramInt);
    int i = 0;
    if (!bool1)
    {
      char c = (char)paramInt;
      if (!JS_ESCAPE_CHARS.contains(Character.valueOf(c)))
      {
        boolean bool2 = this.escapeHtmlCharacters;
        i = 0;
        if (bool2)
        {
          boolean bool3 = HTML_ESCAPE_CHARS.contains(Character.valueOf(c));
          i = 0;
          if (!bool3);
        }
      }
      else
      {
        i = 1;
      }
    }
    return i;
  }

  public String escapeJsonString(CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder(20 + paramCharSequence.length());
    try
    {
      escapeJsonString(paramCharSequence, localStringBuilder);
      return localStringBuilder.toString();
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException(localIOException);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.google.gson.Escaper
 * JD-Core Version:    0.6.0
 */