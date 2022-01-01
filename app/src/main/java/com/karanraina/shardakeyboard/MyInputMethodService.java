package com.karanraina.shardakeyboard;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;

import java.util.List;


public class MyInputMethodService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private ShardaKeyboardView keyboardView;
    private Keyboard keyboard1;
    private Keyboard keyboard2;


    private boolean caps = true;

    @Override
    public View onCreateInputView() {
//        this.setTheme(R.style.AppTheme);
        keyboardView = (ShardaKeyboardView) getLayoutInflater().inflate(R.layout.keyboard_view, null);

        keyboard1 = new Keyboard(this, R.xml.keys_layout_1);
        keyboard2 = new Keyboard(this, R.xml.keys_layout_2);

//        preview =

        keyboardView.setKeyboard(keyboard1);
        keyboardView.setPreviewEnabled(true);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }


    @Override
    public void onText(CharSequence text) {
        InputConnection inputConnection = getCurrentInputConnection();
        if (inputConnection != null) {
            String textToInput = (String) text;
            switch(textToInput) {
                case "KEYCODE_DELETE" :
                    CharSequence selectedText = inputConnection.getSelectedText(0);

                    if (TextUtils.isEmpty(selectedText)) {
                        inputConnection.deleteSurroundingText(2, 0);
                    } else {
                        inputConnection.commitText("", 1);
                    }
                    break;
                case "KEYCODE_SHIFT":
                    if(caps) {
                        keyboardView.setKeyboard(keyboard2);
                    } else {
                        keyboardView.setKeyboard(keyboard1);
                    }
                    caps = !caps;
                    break;
                case "KEYCODE_DONE":
                    inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));

                    break;
                default :
                    inputConnection.commitText(textToInput, 1);

            }
        }
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
