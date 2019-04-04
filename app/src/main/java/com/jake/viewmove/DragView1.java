package com.jake.viewmove;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author yinhao
 * @date 2019/4/4
 *
 * //在当前left、top、right、bottom的基础上加上偏移量
 * layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX, getBottom() + offsetY);
 */
public class DragView1 extends View {

  public DragView1(Context context) {
    super(context);
  }

  public DragView1(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public DragView1(Context context, AttributeSet attrs,
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
        //在当前left、top、right、bottom的基础上加上偏移量
        layout(getLeft() + offsetX, getTop() + offsetY, getRight() + offsetX,
            getBottom() + offsetY);
        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return true;
  }
}
