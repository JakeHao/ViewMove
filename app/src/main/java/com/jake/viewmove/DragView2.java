package com.jake.viewmove;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author yinhao
 * @date 2019/4/4
 *
 */
public class DragView2 extends View {

  public DragView2(Context context) {
    super(context);
  }

  public DragView2(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DragView2(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private int x, y;

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int currentX = (int) event.getX();
    int currentY = (int) event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        //记录触摸点坐标
        x = (int) event.getX();
        y = (int) event.getY();
        break;
      case MotionEvent.ACTION_MOVE:
        //计算偏移量
        int offsetX = currentX - x;
        int offsetY = currentY - y;
        //同时对left和right偏移
        offsetLeftAndRight(offsetX);
        //同时对top和bottom偏移
        offsetTopAndBottom(offsetY);
        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return true;
  }
}
