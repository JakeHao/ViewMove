package com.jake.viewmove;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * @author yinhao
 * @date 2019/4/2
 */
public class DragView6 extends FrameLayout {

  private ViewDragHelper mViewDragHelper;

  public DragView6(Context context) {
    super(context);
    init(context);
  }

  public DragView6(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public DragView6(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    mViewDragHelper = ViewDragHelper.create(this, new Callback() {
      /**
       * 根据返回结果决定当前child是否可以拖拽
       *
       * @param child     当前被拖拽的view
       * @param pointerId 区分多点触摸的id
       * @return
       */
      @Override
      public boolean tryCaptureView(@NonNull View child, int pointerId) {
        return true;
      }

      /**
       * 返回拖拽的范围，不对拖拽进行真正的限制，仅仅决定了动画执行速度
       *
       * @param child
       * @return
       */
      @Override
      public int getViewHorizontalDragRange(View child) {
        return 0;
      }

      /**
       * @param child
       * @param left  代表你将要移动到的位置的坐标,返回值就是最终确定的移动的位置,
       *              判断如果这个坐标在layout之内,那我们就返回这个坐标值，
       *              不能让他超出这个范围, 除此之外就是如果你的layout设置了padding的话，
       *              让子view的活动范围在padding之内的
       * @param dx
       * @return
       */
      @Override
      public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
        // 两个if主要是为了让view ViewGroup里
        if (getPaddingLeft() > left) {
          return getPaddingLeft();
        }
        if (getWidth() - child.getWidth() < left) {
          return getWidth() - child.getWidth();
        }
        return left;
      }

      /**
       * @param child
       * @param top  代表你将要移动到的位置的坐标,返回值就是最终确定的移动的位置,
       *              判断如果这个坐标在layout之内,那我们就返回这个坐标值，
       *              不能让他超出这个范围, 除此之外就是如果你的layout设置了padding的话，
       *              让子view的活动范围在padding之内的
       * @param dy
       * @return
       */
      @Override
      public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
        // 两个if主要是为了让view ViewGroup里
        if (getPaddingTop() > top) {
          return getPaddingTop();
        }
        if (getHeight() - child.getHeight() < top) {
          return getHeight() - child.getHeight();
        }
        return top;
      }

      /**
       * 位置改变时回调，常用于滑动是更改scale进行缩放等效果
       *
       * @param changedView
       * @param left
       * @param top
       * @param dx          横向滑动的加速度
       * @param dy
       */
      @Override
      public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
      }

      /**
       * 拖动结束后调用
       *
       * @param releasedChild
       * @param xvel          水平方向的速度  向右为正
       * @param yvel          竖直方向的速度  向下为正
       */
      @Override
      public void onViewReleased(View releasedChild, float xvel, float yvel) {
      }
    });
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean b = mViewDragHelper.shouldInterceptTouchEvent(ev);
    return b;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    mViewDragHelper.processTouchEvent(event);
    return true;
  }
}
