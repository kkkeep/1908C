package com.m.k.seetaoism.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

public class EditTextButton extends androidx.appcompat.widget.AppCompatButton {


    public EditTextButton(Context context) {
        super(context);
    }

    public EditTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void bindEditText(EditText editText) {

        setEnabled(editText.getText().toString().length() > 0);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setEnabled(editText.getText().toString().length() > 0);
            }
        });
    }

}
