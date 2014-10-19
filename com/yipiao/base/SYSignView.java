package com.yipiao.base;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import cn.suanya.common.a.m;
import com.yipiao.view.MyAlertDialog;
import com.yipiao.view.MyToast;
import java.io.InputStream;
import pl.droidsonroids.gif.GifImageView;

public class SYSignView extends RelativeLayout
{
  public static final int KEY_CANCEL = 0;
  public static final int KEY_FINISH = 1;
  private Handler handler;
  private MyAlertDialog inputDialog;
  private int loginSignTimes;
  private BaseActivity mContext;
  private GifImageView mImage;
  private LayoutInflater mInflater;
  private SignListener mLoad;
  private ProgressBar mProgress;
  public EditText mText;
  private RelativeLayout mView;
  private boolean showInputDialog = false;
  private MulImage signCash = null;

  public SYSignView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = ((BaseActivity)paramContext);
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }

  public EditText getEditText()
  {
    return this.mText;
  }

  public String getSign()
  {
    if (this.mText == null)
      return "";
    return this.mText.getText().toString().trim();
  }

  public void init(int paramInt, SignListener paramSignListener)
  {
    this.mLoad = paramSignListener;
    this.mView = ((RelativeLayout)this.mInflater.inflate(paramInt, null));
    this.mText = ((EditText)this.mView.findViewById(2131297099));
    this.mImage = ((GifImageView)this.mView.findViewById(2131297100));
    this.mImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SYSignView.this.refreshSign();
      }
    });
    this.mText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        if (SYSignView.this.mLoad != null)
          SYSignView.this.mLoad.onChange();
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }
    });
    this.mProgress = ((ProgressBar)this.mView.findViewById(2131297101));
    this.mProgress.setVisibility(8);
    addView(this.mView);
  }

  public void init(int paramInt, SignListener paramSignListener, String paramString1, String paramString2)
  {
    init(paramInt, paramSignListener);
    if (!paramString2.equals(""))
      this.mText.setHint(paramString2);
  }

  public void init(int paramInt, SignListener paramSignListener, boolean paramBoolean)
  {
    init(paramInt, paramSignListener);
    if (paramBoolean)
      this.mText.setInputType(2);
  }

  public void invalidSign()
  {
    this.signCash = null;
  }

  public void loadSign()
  {
    this.loginSignTimes = 1;
    new MyAsyncTask(this.mContext, false)
    {
      protected SYSignView.MulImage myInBackground(Object[] paramArrayOfObject)
        throws Exception
      {
        SYSignView.MulImage localMulImage;
        if (SYSignView.this.signCash != null)
          localMulImage = SYSignView.this.signCash;
        do
        {
          return localMulImage;
          localMulImage = SYSignView.this.mLoad.load();
          SYSignView.access$102(SYSignView.this, localMulImage);
        }
        while (localMulImage != null);
        throw new m("获取验证码失败！");
      }

      protected void myPostExecute(SYSignView.MulImage paramMulImage)
      {
        if ("gif".equalsIgnoreCase(paramMulImage.type))
          SYSignView.this.mImage.updateImageInputStream(paramMulImage.image);
        while (true)
        {
          SYSignView.this.mProgress.setVisibility(8);
          SYSignView.this.mImage.setVisibility(0);
          if (SYSignView.this.showInputDialog)
            SYSignView.this.showKeyboard();
          return;
          SYSignView.this.mImage.setImageBitmap(BitmapFactory.decodeStream(paramMulImage.image));
        }
      }

      protected void onException(Exception paramException)
      {
        SYSignView.access$510(SYSignView.this);
        if (SYSignView.this.loginSignTimes <= 0)
        {
          MyToast.makeText(this.context, "获取验证码失败:" + paramException.getMessage(), 1).show();
          SYSignView.this.mImage.setImageResource(2130837823);
          SYSignView.this.mProgress.setVisibility(8);
          SYSignView.this.mImage.setVisibility(0);
          return;
        }
        execute(new Object[0]);
      }
    }
    .execute(new Object[0]);
  }

  public void refreshSign()
  {
    this.mProgress.setVisibility(0);
    this.mImage.setVisibility(8);
    invalidSign();
    this.mText.setText("");
    this.mText.setFocusable(true);
    this.mText.requestFocus();
    loadSign();
  }

  public void setSign(String paramString)
  {
    if (this.mText != null)
      this.mText.setText(paramString);
  }

  public void showKeyboard()
  {
    if ((this.inputDialog != null) && (this.inputDialog.isShowing()))
      return;
    if (this.handler == null)
      this.handler = new Handler();
    View localView = this.mInflater.inflate(2130903165, null);
    EditText localEditText = (EditText)localView.findViewById(2131297099);
    localEditText.setInputType(0);
    ((GifImageView)localView.findViewById(2131297100)).setImageDrawable(this.mImage.getDrawable());
    KeyboardView localKeyboardView = (KeyboardView)localView.findViewById(2131297102);
    WindowManager localWindowManager = (WindowManager)this.mContext.getSystemService("window");
    int i = localWindowManager.getDefaultDisplay().getWidth();
    int j = localWindowManager.getDefaultDisplay().getHeight();
    localView.setMinimumWidth(i);
    WindowManager.LayoutParams localLayoutParams1 = new WindowManager.LayoutParams();
    localLayoutParams1.width = -1;
    localLayoutParams1.height = -2;
    this.inputDialog = new MyAlertDialog(this.mContext, 2131427458);
    this.inputDialog.setCanceledOnTouchOutside(true);
    this.inputDialog.addContentView(localView, localLayoutParams1);
    WindowManager.LayoutParams localLayoutParams2 = this.inputDialog.getWindow().getAttributes();
    localLayoutParams2.x = 0;
    localLayoutParams2.y = j;
    this.inputDialog.onWindowAttributesChanged(localLayoutParams2);
    this.inputDialog.show();
    localKeyboardView.setKeyboard(new Keyboard(this.mContext, 2131034112));
    localKeyboardView.setEnabled(true);
    localKeyboardView.setPreviewEnabled(false);
    localKeyboardView.setVisibility(0);
    localKeyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener(localEditText)
    {
      public void onKey(int paramInt, int[] paramArrayOfInt)
      {
        Editable localEditable = this.val$et.getText();
        int i = this.val$et.getSelectionStart();
        if (paramInt == -5)
          if ((localEditable != null) && (localEditable.length() > 0) && (i > 0))
            localEditable.delete(i - 1, i);
        while (true)
        {
          SYSignView.this.mText.setText(this.val$et.getText());
          return;
          if (paramInt == 4896)
          {
            localEditable.clear();
            continue;
          }
          if (paramInt == -1000)
          {
            localEditable.clear();
            SYSignView.this.inputDialog.dismiss();
            SYSignView.this.handler.sendEmptyMessage(0);
            continue;
          }
          if (paramInt == -3)
          {
            SYSignView.this.inputDialog.dismiss();
            SYSignView.this.handler.sendEmptyMessage(1);
            continue;
          }
          localEditable.insert(i, Character.toString((char)paramInt));
        }
      }

      public void onPress(int paramInt)
      {
      }

      public void onRelease(int paramInt)
      {
      }

      public void onText(CharSequence paramCharSequence)
      {
      }

      public void swipeDown()
      {
      }

      public void swipeLeft()
      {
      }

      public void swipeRight()
      {
      }

      public void swipeUp()
      {
      }
    });
  }

  public static class MulImage
  {
    InputStream image;
    String type;

    public MulImage(InputStream paramInputStream)
    {
      this.image = paramInputStream;
      this.type = "jpg";
    }

    public MulImage(InputStream paramInputStream, String paramString)
    {
      this.image = paramInputStream;
      if (paramString == null)
        paramString = "jpg";
      this.type = paramString;
    }
  }

  public static abstract interface SignListener
  {
    public abstract SYSignView.MulImage load()
      throws Exception;

    public abstract void onCancel();

    public abstract void onChange();

    public abstract void onFinish(String paramString);
  }

  public static class SignListenerBase
    implements SYSignView.SignListener
  {
    public SYSignView.MulImage load()
      throws Exception
    {
      return null;
    }

    public void onCancel()
    {
    }

    public void onChange()
    {
    }

    public void onFinish(String paramString)
    {
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.base.SYSignView
 * JD-Core Version:    0.6.0
 */