package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice13GetTextBoundsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    String texts[] = new String[]{"A",
            "a",
            "J",
            "j",
            "Â",
            "â"};
    int top = 200;
    int bottom = 400;

    public Practice13GetTextBoundsView(Context context) {
        super(context);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private final Rect rect;

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));
        paint2.setTextSize(160);

        rect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        // 使用 Paint.getTextBounds() 计算出文字的显示区域
        // 然后计算出文字的绘制位置，从而让文字上下居中
        // 这种居中算法的优点是，可以让文字精准地居中，分毫不差

        int middle = (top + bottom) / 2;
        int x = 0;
        int x1 = 0;
        for (String str : texts) {
            paint2.getTextBounds(str, 0, str.length(), rect);
            int offset = (rect.top + rect.bottom) / 2;
            Log.e("TAG", "top" + rect.top + "\nbottom" + rect.bottom);
            canvas.drawText(str, x += 100, middle - offset, paint2);
            /*
            * 注：1.drawText(str,x,y,paint)，(x,y)是文字的左下角的坐标+padding
            *     2.通过getTextBounds()获取的Rect来计算文字的中心y坐标 -> textHalf=(top+bottom)/2是一个负数
            *
            *     由上面两点可知，如果想文字在矩形范围的纵向中心，则需 y+textHalf
            * */
        }
    }
}