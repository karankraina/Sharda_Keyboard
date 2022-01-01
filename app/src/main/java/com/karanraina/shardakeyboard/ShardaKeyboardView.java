package com.karanraina.shardakeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShardaKeyboardView extends KeyboardView {

    private TextView preview;
    Typeface font = Typeface.createFromAsset(this.getContext().getAssets(),
            "font/satisar.ttf"); //Insert your font here.
    public ShardaKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }


    @Override
        public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        preview = (TextView) findViewById(R.id.preview_key);
//        if ( preview != null){
//
//            Log.d("A", "====================SETTING TYPEFACE FOR PREVIEW======================");
//            preview.setTypeface(font);
//        }

            Paint paint = new Paint();
//            paint.setTextAlign(Paint.Align.CENTER);

            paint.setTypeface(font);
//        paint.setARGB(255, 255, 255, 255);
//            paint.setAntiAlias(true);
            paint.setTextSize(20 * getResources().getDisplayMetrics().density);
            paint.setColor(Color.WHITE);

            List<Keyboard.Key> keys = getKeyboard().getKeys();
            for(Keyboard.Key key: keys) {
                if(key.label != null) {
                    canvas.drawText(key.label.toString(), key.x + 40, key.y + 95, paint);
                }
            }

        }
}
