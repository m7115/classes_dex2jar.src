package com.squareup.okhttp.internal.http;

public final class Failure
{
  private final Throwable exception;
  private final Request request;

  private Failure(Builder paramBuilder)
  {
    this.request = paramBuilder.request;
    this.exception = paramBuilder.exception;
  }

  public Throwable exception()
  {
    return this.exception;
  }

  public Request request()
  {
    return this.request;
  }

  public static class Builder
  {
    private Throwable exception;
    private Request request;

    public Failure build()
    {
      return new Failure(this, null);
    }

    public Builder exception(Throwable paramThrowable)
    {
      this.exception = paramThrowable;
      return this;
    }

    public Builder request(Request paramRequest)
    {
      this.request = paramRequest;
      return this;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.squareup.okhttp.internal.http.Failure
 * JD-Core Version:    0.6.0
 */