package org.techtown.graphics.custom.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomViewImage extends View {

    private Bitmap cacheBitmap;//메모리에 만들어질 Bitmap 객체 선언
    private Canvas cacheCanvas;//메모리에 만들어질 Bitmap 객체에 그리기 위한 Canvas 객체 선언
    private Paint mPaint;


    public CustomViewImage(Context context) {
        super(context);

        init(context);
    }

    public CustomViewImage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        mPaint = new Paint();
    }

    //뷰가 화면에 보이기 전에 Bitmap 객체 만들고 그 위에 그리기
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        createCacheBitmap(w, h);
        testDrawing();
    }

    //메모리에 Bitmap 객체를 만들고 Canvas 객체 설정
    private void createCacheBitmap(int w, int h){
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }

    //빨간 사각형 그리기
    private void testDrawing(){
        cacheCanvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.RED);
        cacheCanvas.drawRect(100, 100, 200, 200, mPaint);

        //리소스의 이미지 파일을 읽어 들여 화면에 그리기
        Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.waterdrop);
        cacheCanvas.drawBitmap(srcImg, 30, 30, mPaint);

        //매트릭스 객체를 이용해 좌우 대칭이 되는 비트맵 이미지를 만들어 그리기
        Matrix horInverseMatrix = new Matrix();
        horInverseMatrix.setScale(-1, 1);
        Bitmap horInverseImg = Bitmap.createBitmap(srcImg, 0, 0,
                srcImg.getWidth(), srcImg.getHeight(), horInverseMatrix, false);
        cacheCanvas.drawBitmap(horInverseImg, 30, 130, mPaint);

        Matrix verInverseMatrix = new Matrix();
        verInverseMatrix.setScale(1, -1);
        Bitmap verInverseImg = Bitmap.createBitmap(srcImg, 0, 0,
                srcImg.getWidth(), srcImg.getHeight(), verInverseMatrix, false);
        cacheCanvas.drawBitmap(verInverseImg, 30, 230, mPaint);

        //번짐 효과
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
        Bitmap scaledImg = Bitmap.createScaledBitmap(srcImg, srcImg.getWidth()*3, srcImg.getHeight()*3, false);
        cacheCanvas.drawBitmap(scaledImg, 30, 300, mPaint);

    }

    //메모리의 Bitmap을 이용해 화면에 그리기
    protected void onDraw(Canvas canvas){
        if(cacheBitmap!=null){
            canvas.drawBitmap(cacheBitmap, 0, 0, null);
        }
    }
}
