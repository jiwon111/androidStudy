package org.techtown.graphics.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
//뷰 상속
public class CustomView extends View {

    private Paint paint;

    public CustomView(Context context) {
        super(context);

        init(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    //onTouch() 메서드 구현
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Toast.makeText(super.getContext(), "MotionEvent.ACTION_DOWN : "+
                    event.getX()+", "+event.getY(), Toast.LENGTH_LONG).show();
        }
        return super.onTouchEvent(event);
    }

    //onDraw() 메서드 구현
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(100, 100, 200, 200, paint);
    }

    //페인트 객체 초기화
    private void init(Context context){
        paint = new Paint();
        paint.setColor(Color.RED);
    }
}
