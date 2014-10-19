package cn.suanya.common.a;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class t
{
  public static String a(String paramString1, String paramString2)
    throws Exception
  {
    IvParameterSpec localIvParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString1.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
    return a.a(localCipher.doFinal(paramString2.getBytes()));
  }

  public static String b(String paramString1, String paramString2)
    throws Exception
  {
    byte[] arrayOfByte = a.a(paramString2);
    IvParameterSpec localIvParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramString1.getBytes(), "AES");
    Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    localCipher.init(2, localSecretKeySpec, localIvParameterSpec);
    return new String(localCipher.doFinal(arrayOfByte), "UTF-8");
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     cn.suanya.common.a.t
 * JD-Core Version:    0.6.0
 */