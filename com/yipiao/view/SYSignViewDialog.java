package com.yipiao.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.yipiao.base.SYSignView;
import com.yipiao.base.SYSignView.SignListener;
import java.lang.reflect.Method;

public class SYSignViewDialog extends Dialog
{
  public SYSignView signView;

  public SYSignViewDialog(Context paramContext, SYSignView.SignListener paramSignListener)
  {
    super(paramContext, 2131427460);
    RelativeLayout localRelativeLayout = (RelativeLayout)LayoutInflater.from(paramContext).inflate(2130903167, null);
    this.signView = ((SYSignView)localRelativeLayout.findViewById(2131296771));
    this.signView.init(2130903166, paramSignListener);
    this.signView.refreshSign();
    EditText localEditText = this.signView.getEditText();
    localEditText.setInputType(0);
    getWindow().setSoftInputMode(3);
    try
    {
      Class localClass = localEditText.getClass();
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Method localMethod = localClass.getMethod("setShowSoftInputOnFocus", arrayOfClass);
      localMethod.setAccessible(true);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(false);
      localMethod.invoke(localEditText, arrayOfObject);
      KeyboardView localKeyboardView = (KeyboardView)localRelativeLayout.findViewById(2131297102);
      localKeyboardView.setKeyboard(new Keyboard(paramContext, 2131034112));
      localKeyboardView.setEnabled(true);
      localKeyboardView.setPreviewEnabled(false);
      localKeyboardView.setVisibility(0);
      localRelativeLayout.setMinimumWidth(10000);
      setCancelable(false);
      WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
      localLayoutParams.x = 0;
      localLayoutParams.y = -1000;
      localLayoutParams.gravity = 80;
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      getWindow().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
      Rect localRect = new Rect();
      getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
      localLayoutParams.width = localDisplayMetrics.widthPixels;
      onWindowAttributesChanged(localLayoutParams);
      setContentView(localRelativeLayout);
      localKeyboardView.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener(localEditText, paramSignListener)
      {
        public void onKey(int paramInt, int[] paramArrayOfInt)
        {
          Editable localEditable = this.val$et.getText();
          int i = this.val$et.getSelectionStart();
          if (paramInt == -5)
          {
            if ((localEditable != null) && (localEditable.length() > 0) && (i > 0))
              localEditable.delete(i - 1, i);
            return;
          }
          if (paramInt == 4896)
          {
            localEditable.clear();
            return;
          }
          if (paramInt == -1000)
          {
            localEditable.clear();
            SYSignViewDialog.this.dismiss();
            this.val$load.onCancel();
            return;
          }
          if (paramInt == -3)
          {
            SYSignViewDialog.this.dismiss();
            this.val$load.onFinish(localEditable.toString());
            return;
          }
          localEditable.insert(i, Character.toString((char)paramInt));
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
      return;
    }
    catch (SecurityException localSecurityException)
    {
      while (true)
        localSecurityException.printStackTrace();
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public String getSign()
  {
    if (this.signView != null)
      return this.signView.getSign();
    return "";
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.SYSignViewDialog
 * JD-Core Version:    0.6.0
 */