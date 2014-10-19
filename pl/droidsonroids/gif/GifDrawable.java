package pl.droidsonroids.gif;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.MediaController.MediaPlayerControl;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;

public class GifDrawable extends Drawable
  implements Animatable, MediaController.MediaPlayerControl
{
  private final int[] mColors;
  private volatile int mGifInfoPtr;
  private final Runnable mInvalidateTask = new Runnable()
  {
    public void run()
    {
      GifDrawable.this.invalidateSelf();
    }
  };
  private volatile boolean mIsRunning = true;
  private final int[] mMetaData = new int[4];
  private final Paint mPaint = new Paint(6);
  private final Runnable mResetTask = new Runnable()
  {
    public void run()
    {
      GifDrawable.access$100(GifDrawable.this.mGifInfoPtr);
    }
  };
  private InputStream mStream;

  static
  {
    System.loadLibrary("gif");
  }

  public GifDrawable(AssetFileDescriptor paramAssetFileDescriptor)
    throws IOException
  {
    if (paramAssetFileDescriptor == null)
      throw new NullPointerException("Source is null");
    FileDescriptor localFileDescriptor = paramAssetFileDescriptor.getFileDescriptor();
    this.mGifInfoPtr = openFd(this.mMetaData, localFileDescriptor, paramAssetFileDescriptor.getStartOffset());
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  public GifDrawable(AssetManager paramAssetManager, String paramString)
    throws IOException
  {
    this(paramAssetManager.openFd(paramString));
  }

  public GifDrawable(Resources paramResources, int paramInt)
    throws Resources.NotFoundException, IOException
  {
    this(paramResources.openRawResourceFd(paramInt));
  }

  public GifDrawable(File paramFile)
    throws IOException
  {
    this(paramFile.getPath());
  }

  public GifDrawable(FileDescriptor paramFileDescriptor)
    throws IOException
  {
    if (paramFileDescriptor == null)
      throw new NullPointerException("Source is null");
    this.mGifInfoPtr = openFd(this.mMetaData, paramFileDescriptor, 0L);
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  public GifDrawable(InputStream paramInputStream)
    throws IOException
  {
    if (paramInputStream == null)
      throw new NullPointerException("Source is null");
    if (!paramInputStream.markSupported())
      throw new IllegalArgumentException("InputStream does not support marking");
    this.mStream = paramInputStream;
    this.mGifInfoPtr = openStream(this.mMetaData, paramInputStream);
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  public GifDrawable(String paramString)
    throws IOException
  {
    if (paramString == null)
      throw new NullPointerException("Source is null");
    this.mGifInfoPtr = openFile(this.mMetaData, paramString);
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  public GifDrawable(ByteBuffer paramByteBuffer)
    throws IOException
  {
    if (paramByteBuffer == null)
      throw new NullPointerException("Source is null");
    if (!paramByteBuffer.isDirect())
      throw new IllegalArgumentException("ByteBuffer is not direct");
    this.mGifInfoPtr = openDirectByteBuffer(this.mMetaData, paramByteBuffer);
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  public GifDrawable(byte[] paramArrayOfByte)
    throws IOException
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException("Source is null");
    this.mGifInfoPtr = openByteArray(this.mMetaData, paramArrayOfByte);
    this.mColors = new int[this.mMetaData[0] * this.mMetaData[1]];
    checkError();
  }

  private void checkError()
    throws GifIOException
  {
    if (this.mGifInfoPtr == 0)
      throw new GifIOException(GifError.fromCode(this.mMetaData[3]));
  }

  public static GifDrawable createFromResource(Resources paramResources, int paramInt)
  {
    try
    {
      GifDrawable localGifDrawable = new GifDrawable(paramResources, paramInt);
      return localGifDrawable;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  private static native void free(int paramInt);

  private static native String getComment(int paramInt);

  private static native int getCurrentPosition(int paramInt);

  private static native int getDuration(int paramInt);

  private static native int getLoopCount(int paramInt);

  private static native int openByteArray(int[] paramArrayOfInt, byte[] paramArrayOfByte);

  private static native int openDirectByteBuffer(int[] paramArrayOfInt, ByteBuffer paramByteBuffer);

  private static native int openFd(int[] paramArrayOfInt, FileDescriptor paramFileDescriptor, long paramLong);

  private static native int openFile(int[] paramArrayOfInt, String paramString);

  private static native int openStream(int[] paramArrayOfInt, InputStream paramInputStream);

  private static native int renderFrame(int[] paramArrayOfInt, int paramInt);

  private static native boolean reset(int paramInt);

  private static void runOnUiThread(Runnable paramRunnable)
  {
    if (Looper.myLooper() == Looper.getMainLooper())
    {
      paramRunnable.run();
      return;
    }
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }

  private static native void setSpeedFactor(int paramInt, float paramFloat);

  public boolean canPause()
  {
    return true;
  }

  public boolean canSeekBackward()
  {
    return false;
  }

  public boolean canSeekForward()
  {
    return getNumberOfFrames() > 1;
  }

  public void draw(Canvas paramCanvas)
  {
    if (this.mIsRunning)
    {
      this.mMetaData[3] = renderFrame(this.mColors, this.mGifInfoPtr);
      if (this.mMetaData[2] > 1)
        invalidateSelf();
    }
    paramCanvas.drawBitmap(this.mColors, 0, this.mMetaData[0], 0.0F, 0.0F, this.mMetaData[0], this.mMetaData[1], true, this.mPaint);
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      recycle();
      return;
    }
    finally
    {
      super.finalize();
    }
    throw localObject;
  }

  public int getAudioSessionId()
  {
    return 0;
  }

  public int getBufferPercentage()
  {
    return 100;
  }

  public String getComment()
  {
    return getComment(this.mGifInfoPtr);
  }

  public int getCurrentPosition()
  {
    return getCurrentPosition(this.mGifInfoPtr);
  }

  public int getDuration()
  {
    return getDuration(this.mGifInfoPtr);
  }

  public GifError getError()
  {
    return GifError.fromCode(this.mMetaData[3]);
  }

  public int getIntrinsicHeight()
  {
    return this.mMetaData[1];
  }

  public int getIntrinsicWidth()
  {
    return this.mMetaData[0];
  }

  public int getLoopCount()
  {
    return getLoopCount(this.mGifInfoPtr);
  }

  public int getNumberOfFrames()
  {
    return this.mMetaData[2];
  }

  public int getOpacity()
  {
    return -2;
  }

  public boolean isPlaying()
  {
    return this.mIsRunning;
  }

  public boolean isRunning()
  {
    return this.mIsRunning;
  }

  public void pause()
  {
    stop();
  }

  public void recycle()
  {
    this.mIsRunning = false;
    int i = this.mGifInfoPtr;
    this.mGifInfoPtr = 0;
    free(i);
    if (this.mStream != null);
    try
    {
      this.mStream.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public void reset()
  {
    runOnUiThread(this.mResetTask);
  }

  public void seekTo(int paramInt)
  {
  }

  public void setAlpha(int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mPaint.setColorFilter(paramColorFilter);
  }

  public void setSpeed(float paramFloat)
  {
    if (paramFloat <= 0.0F)
      throw new IllegalArgumentException("Speed factor is not positive");
    setSpeedFactor(this.mGifInfoPtr, paramFloat);
  }

  public void start()
  {
    this.mIsRunning = true;
    runOnUiThread(this.mInvalidateTask);
  }

  public void stop()
  {
    this.mIsRunning = false;
  }

  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.mMetaData[0]);
    arrayOfObject[1] = Integer.valueOf(this.mMetaData[1]);
    arrayOfObject[2] = Integer.valueOf(this.mMetaData[2]);
    arrayOfObject[3] = Integer.valueOf(this.mMetaData[3]);
    return String.format(localLocale, "Size: %dx%d, %d frames, error: %d", arrayOfObject);
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     pl.droidsonroids.gif.GifDrawable
 * JD-Core Version:    0.6.0
 */