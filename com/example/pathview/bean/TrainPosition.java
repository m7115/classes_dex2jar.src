package com.example.pathview.bean;

public class TrainPosition
{
  private float scale;
  private int stationIndex;

  public TrainPosition(int paramInt, float paramFloat)
  {
    this.stationIndex = paramInt;
    this.scale = paramFloat;
  }

  public float getScale()
  {
    return this.scale;
  }

  public int getStationIndex()
  {
    return this.stationIndex;
  }

  public void setScale(float paramFloat)
  {
    this.scale = paramFloat;
  }

  public void setStationIndex(int paramInt)
  {
    this.stationIndex = paramInt;
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.example.pathview.bean.TrainPosition
 * JD-Core Version:    0.6.0
 */