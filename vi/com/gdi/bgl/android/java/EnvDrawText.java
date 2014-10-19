package vi.com.gdi.bgl.android.java;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.SparseArray;

public class EnvDrawText
{
  public static boolean bBmpChange;
  public static Bitmap bmp;
  public static int[] buffer;
  public static Canvas canvasTemp;
  public static SparseArray<a> fontCache;
  public static int iWordHightMax;
  public static int iWordWidthMax;
  public static Paint pt = null;

  static
  {
    iWordWidthMax = 0;
    iWordHightMax = 0;
    bBmpChange = false;
    bmp = null;
    canvasTemp = null;
    buffer = null;
    fontCache = null;
  }

  public static int[] drawText(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    int i;
    if (pt == null)
    {
      pt = new Paint();
      pt.setSubpixelText(true);
      pt.setAntiAlias(true);
      if ((paramInt2 != 0) && (fontCache != null))
      {
        a locala = (a)fontCache.get(paramInt2);
        if (locala != null)
          pt.setTypeface(locala.a);
      }
      pt.setTextSize(paramInt1);
      i = paramString.indexOf('\\', 0);
      if (i != -1)
        break label469;
      Paint.FontMetrics localFontMetrics2 = pt.getFontMetrics();
      int i13 = (int)pt.measureText(paramString);
      int i14 = (int)Math.ceil(localFontMetrics2.descent - localFontMetrics2.ascent);
      paramArrayOfInt[0] = i13;
      paramArrayOfInt[1] = i14;
      int i15 = (int)Math.pow(2.0D, (int)Math.ceil(Math.log(i13) / Math.log(2.0D)));
      int i16 = (int)Math.pow(2.0D, (int)Math.ceil(Math.log(i14) / Math.log(2.0D)));
      if ((iWordWidthMax < i15) || (iWordHightMax < i16))
      {
        bBmpChange = true;
        iWordWidthMax = i15;
        iWordHightMax = i16;
      }
      paramArrayOfInt[2] = iWordWidthMax;
      paramArrayOfInt[3] = iWordHightMax;
      if (bBmpChange != true)
        break label448;
      bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Bitmap.Config.ARGB_8888);
      canvasTemp = new Canvas(bmp);
      label269: if ((0xFF000000 & paramInt5) != 0)
        break label458;
      canvasTemp.drawColor(33554431);
      label285: if (paramInt6 != 0)
      {
        pt.setStrokeWidth(paramInt6);
        pt.setStrokeCap(Paint.Cap.ROUND);
        pt.setStrokeJoin(Paint.Join.ROUND);
        pt.setStyle(Paint.Style.STROKE);
        pt.setColor(paramInt4);
        canvasTemp.drawText(paramString, 0.0F, 0.0F - localFontMetrics2.ascent, pt);
      }
      pt.setStyle(Paint.Style.FILL);
      pt.setColor(paramInt3);
      canvasTemp.drawText(paramString, 0.0F, 0.0F - localFontMetrics2.ascent, pt);
    }
    while (true)
    {
      int i9 = iWordWidthMax * iWordHightMax;
      if (bBmpChange == true)
        buffer = new int[i9];
      bmp.getPixels(buffer, 0, iWordWidthMax, 0, 0, iWordWidthMax, iWordHightMax);
      bBmpChange = false;
      return buffer;
      pt.reset();
      break;
      label448: bmp.eraseColor(0);
      break label269;
      label458: canvasTemp.drawColor(paramInt5);
      break label285;
      label469: int j = i + 1;
      int k = 2;
      int m = (int)pt.measureText(paramString.substring(0, i));
      while (true)
      {
        int n = paramString.indexOf('\\', j);
        if (n <= 0)
          break;
        int i12 = (int)pt.measureText(paramString.substring(j, n));
        if (i12 > m)
          m = i12;
        j = n + 1;
        k++;
      }
      if (j != paramString.length())
      {
        int i11 = (int)pt.measureText(paramString.substring(j, paramString.length()));
        if (i11 > m)
          m = i11;
      }
      Paint.FontMetrics localFontMetrics1 = pt.getFontMetrics();
      int i1 = (int)Math.ceil(localFontMetrics1.descent - localFontMetrics1.ascent);
      int i2 = k * i1;
      paramArrayOfInt[0] = m;
      paramArrayOfInt[1] = i2;
      int i3 = (int)Math.pow(2.0D, (int)Math.ceil(Math.log(m) / Math.log(2.0D)));
      int i4 = (int)Math.pow(2.0D, (int)Math.ceil(Math.log(i2) / Math.log(2.0D)));
      if ((iWordWidthMax < i3) || (iWordHightMax < i4))
      {
        bBmpChange = true;
        iWordWidthMax = i3;
        iWordHightMax = i4;
      }
      paramArrayOfInt[2] = iWordWidthMax;
      paramArrayOfInt[3] = iWordHightMax;
      if (bBmpChange == true)
      {
        bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Bitmap.Config.ARGB_8888);
        canvasTemp = new Canvas(bmp);
        if ((0xFF000000 & paramInt5) != 0)
          break label972;
        canvasTemp.drawColor(33554431);
      }
      int i5;
      int i6;
      while (true)
      {
        i5 = 0;
        for (i6 = 0; ; i6++)
        {
          int i7 = paramString.indexOf('\\', i5);
          if (i7 <= 0)
            break;
          String str2 = paramString.substring(i5, i7);
          int i10 = (int)pt.measureText(str2);
          i5 = i7 + 1;
          if (paramInt6 != 0)
          {
            pt.setStrokeWidth(paramInt6);
            pt.setStrokeCap(Paint.Cap.ROUND);
            pt.setStrokeJoin(Paint.Join.ROUND);
            pt.setStyle(Paint.Style.STROKE);
            pt.setColor(paramInt4);
            canvasTemp.drawText(str2, (paramArrayOfInt[0] - i10) / 2, i6 * i1 - localFontMetrics1.ascent, pt);
          }
          pt.setStyle(Paint.Style.FILL);
          pt.setColor(paramInt3);
          canvasTemp.drawText(str2, (paramArrayOfInt[0] - i10) / 2, i6 * i1 - localFontMetrics1.ascent, pt);
        }
        bmp.eraseColor(0);
        break;
        label972: canvasTemp.drawColor(paramInt5);
      }
      if (i5 == paramString.length())
        continue;
      String str1 = paramString.substring(i5, paramString.length());
      int i8 = (int)pt.measureText(str1);
      if (paramInt6 != 0)
      {
        pt.setStrokeWidth(paramInt6);
        pt.setStrokeCap(Paint.Cap.ROUND);
        pt.setStrokeJoin(Paint.Join.ROUND);
        pt.setStyle(Paint.Style.STROKE);
        pt.setColor(paramInt4);
        canvasTemp.drawText(str1, (paramArrayOfInt[0] - i8) / 2, i6 * i1 - localFontMetrics1.ascent, pt);
      }
      pt.setStyle(Paint.Style.FILL);
      pt.setColor(paramInt3);
      canvasTemp.drawText(str1, (paramArrayOfInt[0] - i8) / 2, i6 * i1 - localFontMetrics1.ascent, pt);
    }
  }

  public static short[] getTextSize(String paramString, int paramInt)
  {
    int i = paramString.length();
    short[] arrayOfShort;
    if (i == 0)
      arrayOfShort = null;
    while (true)
    {
      return arrayOfShort;
      Paint localPaint = new Paint();
      localPaint.setSubpixelText(true);
      localPaint.setAntiAlias(true);
      localPaint.setTextSize(paramInt);
      arrayOfShort = new short[i];
      for (int j = 0; j < i; j++)
        arrayOfShort[j] = (short)(int)localPaint.measureText(paramString.substring(0, j + 1));
    }
  }

  public static void registFontCache(int paramInt, Typeface paramTypeface)
  {
    if ((paramInt == 0) || (paramTypeface == null))
      return;
    if (fontCache == null)
      fontCache = new SparseArray();
    a locala1 = (a)fontCache.get(paramInt);
    if (locala1 == null)
    {
      a locala2 = new a();
      locala2.a = paramTypeface;
      locala2.b = (1 + locala2.b);
      fontCache.put(paramInt, locala2);
      return;
    }
    locala1.b = (1 + locala1.b);
  }

  public static void removeFontCache(int paramInt)
  {
    a locala = (a)fontCache.get(paramInt);
    if (locala == null);
    do
    {
      return;
      locala.b = (-1 + locala.b);
    }
    while (locala.b != 0);
    fontCache.remove(paramInt);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     vi.com.gdi.bgl.android.java.EnvDrawText
 * JD-Core Version:    0.6.0
 */