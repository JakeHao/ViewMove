package com.jake.viewmove;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * @author yinhao
 * @date 2019/4/4
 *
 * mScroller.getCurrX() //获取mScroller当前水平滚动的位置
 * mScroller.getCurrY() //获取mScroller当前竖直滚动的位置
 * mScroller.getFinalX() //获取mScroller最终停止的水平位置
 * mScroller.getFinalY() //获取mScroller最终停止的竖直位置
 * mScroller.setFinalX(int newX) //设置mScroller最终停留的水平位置，没有动画效果，直接跳到目标位置
 * mScroller.setFinalY(int newY) //设置mScroller最终停留的竖直位置，没有动画效果，直接跳到目标位置
 *
 * //滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量, duration为完成滚动的时间
 * mScroller.startScroll(int startX, int startY, int dx, int dy) //使用默认完成时间250ms
 * mScroller.startScroll(int startX, int startY, int dx, int dy, int duration)
 * mScroller.computeScrollOffset() //返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成。
 * 这是一个很重要的方法，通常放在View.computeScroll()中，用来判断是否滚动是否结束。
 *
 * Scroller只是封装了将要滚动的操作，并不是立即执行的，执行了startScroll方法后，
 * 调用了父控件的computeScroll方法来执行的滚动操作，并且滚动并不是按钮的滚动，而是布局滚动，
 * 那么里面的所有子元素也会跟着滚动
 */
public class DragView4 extends View {

  public DragView4(Context context) {
    super(context);
    init(context);
  }

  public DragView4(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DragView4(Context context, AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private Scroller mScroller;
  private int x, y;

  private void init(Context context) {
    mScroller = new Scroller(context);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    int currentX = (int) event.getRawX();
    int currentY = (int) event.getRawY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        //记录触摸点坐标
        x = (int) event.getRawX();
        y = (int) event.getRawY();
        break;
      case MotionEvent.ACTION_MOVE:
        //计算偏移量
        int offsetX = currentX - x;
        int offsetY = currentY - y;
        //
        ((View) getParent()).scrollBy(-offsetX, -offsetY);
        //
        x = currentX;
        y = currentY;
        break;
      case MotionEvent.ACTION_UP:
        /**
         * 实现拖动回弹回去，需配合方法一、二、四中任一方法
         *
         * mScroller.startScroll 并不会导致 View 立即进行scroll，它只会导致当前 View 无效，从而重新绘制，
         * 在 View 调用绘制的时候，它的 computeScroll 函数会被调用，
         * 所以会在computeScroll这个函数中让 View 调用 scrollTo 函数进行实际的移动。
         */
        View parent = (View) getParent();
        mScroller.startScroll(parent.getScrollX(), parent.getScrollY(), -parent.getScrollX(),
            -parent.getScrollY());
        invalidate();
        break;
    }
    return true;
  }

  @Override
  public void computeScroll() {
    super.computeScroll();
    /**
     * 判断Scroller是否执行完毕
     * 返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成
     */
    if (mScroller.computeScrollOffset()) {
      ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
      invalidate();
    }
  }
}
