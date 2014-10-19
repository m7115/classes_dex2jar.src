package com.yipiao.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.yipiao.adapter.MyAlertDialogAdapter;

public class MyAlertDialog extends Dialog
{
  public MyAlertDialog(Context paramContext)
  {
    super(paramContext);
  }

  public MyAlertDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  public MyAlertDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
  }

  public boolean isChecked()
  {
    return true;
  }

  public static class Builder
  {
    private CompoundButton.OnCheckedChangeListener checkBoxListener;
    private CharSequence checkBoxText;
    private Context context;
    private boolean isMultiChoiceDialog;
    private boolean isSingleChoiceDialog;
    private MyAlertDialogAdapter mArrayAdapter;
    public View mDialogView;
    private boolean mDismissNegative = true;
    private boolean mDismissNeutral = true;
    private boolean mDismissPositive = true;
    private EditText mEditText;
    private String mEditTextContent = "";
    private boolean mEditTextFlag = false;
    private CharSequence[] mListItem;
    private CharSequence[] mListItem2;
    private boolean[] mMultiDialogEntryIndex;
    private Drawable mNegativeBackground;
    private Button mNegativeButton;
    private DialogInterface.OnClickListener mNegativeOnClickListener;
    private CharSequence mNegativeTitle;
    private Drawable mNeutralBackground;
    private Button mNeutralButton;
    private DialogInterface.OnClickListener mNeutralOnClickListener;
    private CharSequence mNeutralTitle;
    private DialogInterface.OnClickListener mOnClickListener;
    private DialogInterface.OnMultiChoiceClickListener mOnMultiChoiceClickListener;
    private Drawable mPositiveBackground;
    private Button mPositiveButton;
    private DialogInterface.OnClickListener mPositiveOnClickListener;
    private CharSequence mPositiveTitle;
    private boolean mSelectAllOrNone;
    private int mSingleDialogEntryIndex;
    private CharSequence message;
    private CharSequence title;

    public Builder(Context paramContext)
    {
      this.context = paramContext;
    }

    private void initChoiceDialog(MyAlertDialog paramMyAlertDialog, View paramView)
    {
      if (this.mListItem == null)
        throw new RuntimeException("Entries should not be empty");
      if (this.mListItem.length > 5)
      {
        ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
        localLayoutParams.height = (int)(0.8D * ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getHeight());
        paramView.setLayoutParams(localLayoutParams);
      }
      ListView localListView = (ListView)paramView.findViewById(2131297123);
      localListView.setVisibility(0);
      this.mArrayAdapter = new MyAlertDialogAdapter(this.context, this.mListItem, this.mListItem2, this.isSingleChoiceDialog);
      localListView.setAdapter(this.mArrayAdapter);
      localListView.setOnItemClickListener(new AdapterView.OnItemClickListener(paramMyAlertDialog)
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          if (MyAlertDialog.Builder.this.isSingleChoiceDialog)
          {
            MyAlertDialog.Builder.this.mOnClickListener.onClick(this.val$dialog, paramInt);
            this.val$dialog.dismiss();
          }
          do
            return;
          while (!MyAlertDialog.Builder.this.isMultiChoiceDialog);
          DialogInterface.OnMultiChoiceClickListener localOnMultiChoiceClickListener = MyAlertDialog.Builder.this.mOnMultiChoiceClickListener;
          MyAlertDialog localMyAlertDialog = this.val$dialog;
          if (MyAlertDialog.Builder.this.mMultiDialogEntryIndex[paramInt] == 0);
          for (boolean bool = true; ; bool = false)
          {
            localOnMultiChoiceClickListener.onClick(localMyAlertDialog, paramInt, bool);
            return;
          }
        }
      });
      if (this.isSingleChoiceDialog)
      {
        localListView.setChoiceMode(1);
        localListView.setItemChecked(this.mSingleDialogEntryIndex, true);
        localListView.setSelection(this.mSingleDialogEntryIndex);
      }
      do
        return;
      while (!this.isMultiChoiceDialog);
      localListView.setChoiceMode(2);
      for (int i = -1 + this.mMultiDialogEntryIndex.length; i >= 0; i--)
      {
        localListView.setItemChecked(i, this.mMultiDialogEntryIndex[i]);
        localListView.setSelection(i);
      }
      initSelectButton(paramMyAlertDialog, paramView, localListView, this.mArrayAdapter);
    }

    private void initSelectButton(MyAlertDialog paramMyAlertDialog, View paramView, ListView paramListView, MyAlertDialogAdapter paramMyAlertDialogAdapter)
    {
      Button localButton1 = (Button)paramView.findViewById(2131297124);
      Button localButton2 = (Button)paramView.findViewById(2131297125);
      View localView = paramView.findViewById(2131297126);
      if (this.mSelectAllOrNone)
      {
        localView.setVisibility(0);
        localButton2.setVisibility(0);
        localButton1.setOnClickListener(new View.OnClickListener(localButton2, paramListView, paramMyAlertDialogAdapter, localButton1)
        {
          public void onClick(View paramView)
          {
            this.val$btnSelectNone.setVisibility(8);
            for (int i = -1 + MyAlertDialog.Builder.this.mMultiDialogEntryIndex.length; i >= 0; i--)
            {
              this.val$listView.setItemChecked(i, true);
              MyAlertDialog.Builder.this.mMultiDialogEntryIndex[i] = 1;
            }
            this.val$listView.setSelection(0);
            this.val$adapter.notifyDataSetChanged();
            this.val$btnSelectAll.setVisibility(8);
            this.val$btnSelectNone.setVisibility(0);
          }
        });
        localButton2.setOnClickListener(new View.OnClickListener(localButton2, paramListView, paramMyAlertDialogAdapter, localButton1)
        {
          public void onClick(View paramView)
          {
            this.val$btnSelectNone.setVisibility(8);
            for (int i = -1 + MyAlertDialog.Builder.this.mMultiDialogEntryIndex.length; i >= 0; i--)
            {
              this.val$listView.setItemChecked(i, false);
              MyAlertDialog.Builder.this.mMultiDialogEntryIndex[i] = 0;
            }
            this.val$listView.setSelection(0);
            this.val$adapter.notifyDataSetChanged();
            this.val$btnSelectAll.setVisibility(0);
            this.val$btnSelectNone.setVisibility(8);
          }
        });
      }
    }

    public MyAlertDialog create()
    {
      LayoutInflater localLayoutInflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
      MyAlertDialog localMyAlertDialog = new MyAlertDialog(this.context, 2131427458);
      localMyAlertDialog.setCanceledOnTouchOutside(true);
      this.mDialogView = localLayoutInflater.inflate(2130903183, null);
      WindowManager localWindowManager = (WindowManager)this.context.getSystemService("window");
      this.mDialogView.setMinimumWidth((int)(0.6D * localWindowManager.getDefaultDisplay().getWidth()));
      localMyAlertDialog.addContentView(this.mDialogView, new ViewGroup.LayoutParams(-1, -2));
      if ((this.title != null) && (!this.title.equals("")))
      {
        TextView localTextView2 = (TextView)this.mDialogView.findViewById(2131296827);
        localTextView2.setText(this.title);
        localTextView2.setVisibility(0);
      }
      if (this.mEditTextFlag)
      {
        this.mEditText = ((EditText)this.mDialogView.findViewById(2131297122));
        this.mEditText.setVisibility(0);
        this.mEditText.setText(this.mEditTextContent);
      }
      LinearLayout localLinearLayout = (LinearLayout)this.mDialogView.findViewById(2131296829);
      if ((this.mPositiveTitle != null) && (!this.mPositiveTitle.equals("")))
      {
        localLinearLayout.setVisibility(0);
        this.mPositiveButton = ((Button)this.mDialogView.findViewById(2131296830));
        this.mPositiveButton.setVisibility(0);
        this.mPositiveButton.setText(this.mPositiveTitle);
        if (this.mPositiveBackground != null)
          this.mPositiveButton.setBackgroundDrawable(this.mPositiveBackground);
        if (this.mPositiveOnClickListener != null)
          this.mPositiveButton.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
          {
            public void onClick(View paramView)
            {
              MyAlertDialog.Builder.this.mPositiveOnClickListener.onClick(this.val$dialog, -1);
              if (MyAlertDialog.Builder.this.mDismissPositive)
                this.val$dialog.dismiss();
            }
          });
      }
      if ((this.mNeutralTitle != null) && (!this.mNeutralTitle.equals("")))
      {
        if ((this.mPositiveTitle != null) && (!this.mPositiveTitle.equals("")))
          this.mDialogView.findViewById(2131296831).setVisibility(0);
        localLinearLayout.setVisibility(0);
        this.mNeutralButton = ((Button)this.mDialogView.findViewById(2131297127));
        this.mNeutralButton.setVisibility(0);
        this.mNeutralButton.setText(this.mNeutralTitle);
        if (this.mNeutralBackground != null)
          this.mNeutralButton.setBackgroundDrawable(this.mNeutralBackground);
        if (this.mNeutralOnClickListener != null)
          this.mNeutralButton.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
          {
            public void onClick(View paramView)
            {
              MyAlertDialog.Builder.this.mNeutralOnClickListener.onClick(this.val$dialog, -3);
              if (MyAlertDialog.Builder.this.mDismissNeutral)
                this.val$dialog.dismiss();
            }
          });
      }
      if ((this.mNegativeTitle != null) && (!this.mNegativeTitle.equals("")))
      {
        if (((this.mPositiveTitle != null) && (!this.mPositiveTitle.equals(""))) || ((this.mNeutralTitle != null) && (!this.mNeutralTitle.equals(""))))
          this.mDialogView.findViewById(2131297128).setVisibility(0);
        localLinearLayout.setVisibility(0);
        this.mNegativeButton = ((Button)this.mDialogView.findViewById(2131296832));
        this.mNegativeButton.setVisibility(0);
        this.mNegativeButton.setText(this.mNegativeTitle);
        if (this.mNegativeBackground != null)
          this.mNegativeButton.setBackgroundDrawable(this.mNegativeBackground);
        if (this.mNegativeOnClickListener != null)
          this.mNegativeButton.setOnClickListener(new View.OnClickListener(localMyAlertDialog)
          {
            public void onClick(View paramView)
            {
              MyAlertDialog.Builder.this.mNegativeOnClickListener.onClick(this.val$dialog, -2);
              if (MyAlertDialog.Builder.this.mDismissNegative)
                this.val$dialog.dismiss();
            }
          });
      }
      if ((this.isSingleChoiceDialog) || (this.isMultiChoiceDialog))
        initChoiceDialog(localMyAlertDialog, this.mDialogView);
      while (true)
      {
        CheckBox localCheckBox = (CheckBox)this.mDialogView.findViewById(2131297002);
        if ((this.checkBoxText != null) && (this.checkBoxText.equals("")))
        {
          localCheckBox.setVisibility(0);
          localCheckBox.setText(this.checkBoxText);
        }
        if (this.checkBoxListener != null)
        {
          localCheckBox.setVisibility(0);
          localCheckBox.setOnCheckedChangeListener(this.checkBoxListener);
        }
        return localMyAlertDialog;
        if ((this.message == null) || (this.message.equals("")))
          continue;
        TextView localTextView1 = (TextView)this.mDialogView.findViewById(2131296828);
        localTextView1.setVisibility(0);
        localTextView1.setText(this.message);
      }
    }

    public View getDialogView()
    {
      return this.mDialogView;
    }

    public String getEditText()
    {
      if (this.mEditText == null)
        return "";
      return this.mEditText.getText().toString();
    }

    public CharSequence[] getItems()
    {
      return this.mListItem;
    }

    public CharSequence[] getItems2()
    {
      return this.mListItem2;
    }

    public Builder setCheckBoxListener(CompoundButton.OnCheckedChangeListener paramOnCheckedChangeListener)
    {
      this.checkBoxListener = paramOnCheckedChangeListener;
      return this;
    }

    public Builder setCheckBoxText(CharSequence paramCharSequence)
    {
      this.checkBoxText = paramCharSequence;
      return this;
    }

    public Builder setEditText(boolean paramBoolean)
    {
      this.mEditTextFlag = paramBoolean;
      return this;
    }

    public Builder setEditTextContent(String paramString)
    {
      this.mEditTextContent = paramString;
      return this;
    }

    public Builder setItems(CharSequence[] paramArrayOfCharSequence)
    {
      this.mListItem = paramArrayOfCharSequence;
      return this;
    }

    public Builder setItems2(CharSequence[] paramArrayOfCharSequence)
    {
      this.mListItem2 = paramArrayOfCharSequence;
      return this;
    }

    public Builder setMessage(int paramInt)
    {
      this.isSingleChoiceDialog = false;
      this.isMultiChoiceDialog = false;
      this.message = ((String)this.context.getText(paramInt));
      return this;
    }

    public Builder setMessage(CharSequence paramCharSequence)
    {
      this.isSingleChoiceDialog = false;
      this.isMultiChoiceDialog = false;
      this.message = paramCharSequence;
      return this;
    }

    public Builder setMultiChoiceItems(CharSequence[] paramArrayOfCharSequence1, CharSequence[] paramArrayOfCharSequence2, boolean[] paramArrayOfBoolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener)
    {
      this.isMultiChoiceDialog = true;
      this.isSingleChoiceDialog = false;
      this.mListItem = paramArrayOfCharSequence1;
      this.mListItem2 = paramArrayOfCharSequence2;
      this.mOnMultiChoiceClickListener = paramOnMultiChoiceClickListener;
      this.mMultiDialogEntryIndex = paramArrayOfBoolean;
      return this;
    }

    public Builder setMultiChoiceItems(CharSequence[] paramArrayOfCharSequence, boolean[] paramArrayOfBoolean, DialogInterface.OnMultiChoiceClickListener paramOnMultiChoiceClickListener)
    {
      return setMultiChoiceItems(paramArrayOfCharSequence, null, paramArrayOfBoolean, paramOnMultiChoiceClickListener);
    }

    public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.mNegativeTitle = paramCharSequence;
      this.mNegativeOnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, Drawable paramDrawable)
    {
      this.mNegativeTitle = paramCharSequence;
      this.mNegativeOnClickListener = paramOnClickListener;
      this.mNegativeBackground = paramDrawable;
      return this;
    }

    public Builder setNegativeButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
    {
      this.mNegativeTitle = paramCharSequence;
      this.mNegativeOnClickListener = paramOnClickListener;
      this.mDismissNegative = paramBoolean;
      return this;
    }

    public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.mNeutralTitle = paramCharSequence;
      this.mNeutralOnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, Drawable paramDrawable)
    {
      this.mNeutralTitle = paramCharSequence;
      this.mNeutralOnClickListener = paramOnClickListener;
      this.mNegativeBackground = paramDrawable;
      return this;
    }

    public Builder setNeutralButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
    {
      this.mNeutralTitle = paramCharSequence;
      this.mNeutralOnClickListener = paramOnClickListener;
      this.mDismissNeutral = paramBoolean;
      return this;
    }

    public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.mPositiveTitle = paramCharSequence;
      this.mPositiveOnClickListener = paramOnClickListener;
      return this;
    }

    public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, Drawable paramDrawable)
    {
      this.mPositiveTitle = paramCharSequence;
      this.mPositiveOnClickListener = paramOnClickListener;
      this.mPositiveBackground = paramDrawable;
      return this;
    }

    public Builder setPositiveButton(CharSequence paramCharSequence, DialogInterface.OnClickListener paramOnClickListener, boolean paramBoolean)
    {
      this.mPositiveTitle = paramCharSequence;
      this.mPositiveOnClickListener = paramOnClickListener;
      this.mDismissPositive = paramBoolean;
      return this;
    }

    public Builder setSelectAllOrNone(boolean paramBoolean)
    {
      this.mSelectAllOrNone = paramBoolean;
      return this;
    }

    public Builder setSingleChoiceItems(CharSequence[] paramArrayOfCharSequence, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      return setSingleChoiceItems(paramArrayOfCharSequence, null, paramInt, paramOnClickListener);
    }

    public Builder setSingleChoiceItems(CharSequence[] paramArrayOfCharSequence1, CharSequence[] paramArrayOfCharSequence2, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
    {
      this.isSingleChoiceDialog = true;
      this.isMultiChoiceDialog = false;
      this.mListItem = paramArrayOfCharSequence1;
      this.mListItem2 = paramArrayOfCharSequence2;
      this.mOnClickListener = paramOnClickListener;
      this.mSingleDialogEntryIndex = paramInt;
      return this;
    }

    public Builder setTitle(int paramInt)
    {
      this.title = ((String)this.context.getText(paramInt));
      return this;
    }

    public Builder setTitle(CharSequence paramCharSequence)
    {
      this.title = paramCharSequence;
      return this;
    }

    public MyAlertDialog show()
    {
      MyAlertDialog localMyAlertDialog = create();
      localMyAlertDialog.show();
      return localMyAlertDialog;
    }

    public MyAlertDialog showByCustomView(View paramView)
    {
      MyAlertDialog localMyAlertDialog = new MyAlertDialog(this.context, 2131427458);
      localMyAlertDialog.setCanceledOnTouchOutside(true);
      localMyAlertDialog.addContentView(paramView, new ViewGroup.LayoutParams(-1, -2));
      localMyAlertDialog.show();
      return localMyAlertDialog;
    }

    public MyAlertDialog showShareDialogView(View paramView)
    {
      MyAlertDialog localMyAlertDialog = new MyAlertDialog(this.context, 2131427459);
      int i = ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getWidth();
      localMyAlertDialog.setCanceledOnTouchOutside(true);
      localMyAlertDialog.addContentView(paramView, new ViewGroup.LayoutParams(i, -2));
      localMyAlertDialog.getWindow().getAttributes().gravity = 80;
      localMyAlertDialog.show();
      return localMyAlertDialog;
    }
  }
}

/* Location:           D:\Documents\Downloads\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.yipiao.view.MyAlertDialog
 * JD-Core Version:    0.6.0
 */