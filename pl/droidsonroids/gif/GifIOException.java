package pl.droidsonroids.gif;

import java.io.IOException;

public class GifIOException extends IOException
{
  private static final long serialVersionUID = 13038402904505L;
  public final GifError reason;

  GifIOException(GifError paramGifError)
  {
    super(paramGifError.getFormattedDescription());
    this.reason = paramGifError;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     pl.droidsonroids.gif.GifIOException
 * JD-Core Version:    0.6.0
 */