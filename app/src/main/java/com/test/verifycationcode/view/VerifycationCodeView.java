package com.test.verifycationcode.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.test.verifycationcode.R;

/**
 * Created by xxl on 2017/5/17.
 */

public class VerifycationCodeView extends LinearLayout {

    private Context context;
    private EditText etInputOne;
    private EditText etInputTwo;
    private EditText etInputThree;
    private EditText etInputFour;
    private EditText etInputFive;
    private EditText etInputSix;

    private AutoCommitListener autoCommitListener;

    /**
     * autoCommit when input is finished
     *
     * @param autoCommitListener
     */
    public void setAutoCommitListener(AutoCommitListener autoCommitListener) {
        this.autoCommitListener = autoCommitListener;
    }

    public VerifycationCodeView(Context context) {
        this(context, null);
    }

    public VerifycationCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public VerifycationCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    /**
     * initView
     */
    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.verify_code_view, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        etInputOne = (EditText) view.findViewById(R.id.et_input_one);
        etInputTwo = (EditText) view.findViewById(R.id.et_input_two);
        etInputThree = (EditText) view.findViewById(R.id.et_input_three);
        etInputFour = (EditText) view.findViewById(R.id.et_input_four);
        etInputFive = (EditText) view.findViewById(R.id.et_input_five);
        etInputSix = (EditText) view.findViewById(R.id.et_input_six);

        etInputOne.addTextChangedListener(mTextWatcher);
        etInputTwo.addTextChangedListener(mTextWatcher);
        etInputThree.addTextChangedListener(mTextWatcher);
        etInputFour.addTextChangedListener(mTextWatcher);
        etInputFive.addTextChangedListener(mTextWatcher);
        etInputSix.addTextChangedListener(mTextWatcher);

        etInputOne.setOnKeyListener(mOnKeyListener);
        etInputTwo.setOnKeyListener(mOnKeyListener);
        etInputThree.setOnKeyListener(mOnKeyListener);
        etInputFour.setOnKeyListener(mOnKeyListener);
        etInputFive.setOnKeyListener(mOnKeyListener);
        etInputSix.setOnKeyListener(mOnKeyListener);

        this.addView(view, layoutParams);

    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {  //inputText
            if (s.toString().length() == 1) {
                if (etInputOne.isFocused()) {//Auto jump next inputEditText when the intput is Finished
                    etInputTwo.requestFocus();
                } else if (etInputTwo.isFocused()) {
                    etInputThree.requestFocus();
                } else if (etInputThree.isFocused()) {
                    etInputFour.requestFocus();
                } else if (etInputFour.isFocused()) {
                    etInputFive.requestFocus();
                } else if (etInputFive.isFocused()) {
                    etInputSix.requestFocus();
                }
                if (getInputValue().length() >= 6) {
                    if (autoCommitListener != null) {
                        autoCommitListener.autoCommit(getInputValue().toString());
                    }
                }
            }
        }
    };

    /**
     * getInputValue
     */
    private StringBuffer getInputValue() {
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append(etInputOne.getText().toString())
                .append(etInputTwo.getText().toString())
                .append(etInputThree.getText().toString())
                .append(etInputFour.getText().toString())
                .append(etInputFive.getText().toString())
                .append(etInputSix.getText().toString());
    }

    private OnKeyListener mOnKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) { //deleteText
                if (etInputSix.isFocused() && etInputSix.length() == 0) {//Auto jump previous when the delete is finished and input.length == 0
                    etInputFive.requestFocus();
                    etInputSix.setText("");
                } else if (etInputFive.isFocused() && etInputFive.length() == 0) {
                    etInputFour.requestFocus();
                    etInputFive.setText("");
                } else if (etInputFour.isFocused() && etInputFour.length() == 0) {
                    etInputThree.requestFocus();
                    etInputFour.setText("");
                } else if (etInputThree.isFocused() && etInputThree.length() == 0) {
                    etInputTwo.requestFocus();
                    etInputThree.setText("");
                } else if (etInputTwo.isFocused() && etInputTwo.length() == 0) {
                    etInputOne.requestFocus();
                    etInputTwo.setText("");
                }
            }
            return false;
        }
    };

    /**
     * interface when input is finished autoCommit
     */
    public interface AutoCommitListener {
        void autoCommit(String inputText);
    }
}
