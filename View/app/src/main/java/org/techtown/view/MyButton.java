package org.techtown.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {

    public MyButton(Context context) {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {//뷰가 그려질 때
        super.onDraw(canvas);

        Log.d("My Button", "onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {//뷰가 터치될 때
        Log.d("MyButton", "onTouchEvent 호출됨");

        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);

                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);

                break;
        }
        invalidate();

        return true;
    }

    private void init(Context context){
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);

        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
    }
}
