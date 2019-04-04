package com.jake.viewmove;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 如图，要实现滑动效果，需要先了解以下方法。
 *
 * View提供获取坐标方法
 * getTop():获取View自身顶边到其父布局顶边的距离
 * getLeft():获取View自身左边到其父布局左边的距离
 * getRight():获取View自身右边到其父布局右边的距离
 * getBottom():获取View自身底边到其父布局底边的距离
 *
 * MotionEvent提供的方法
 * getX():获取点击点距离自身控件左边距离，即视图坐标
 * getY():获取点击点距离自身控件顶边距离，即视图坐标
 * getRawX():获取点击点距离整个屏幕左边距离，即绝对坐标
 * getRawY():获取点击点距离整个屏幕顶边距离，即绝对坐标
 */
public class MainActivity extends AppCompatActivity {

  private View dragView5;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    dragView5 = findViewById(R.id.dragView5);
    dragView5.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
//        valueAnimator.setTarget(mDragView5);
//        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
//          @Override
//          public void onAnimationUpdate(ValueAnimator animation) {
//            int value = (int) animation.getAnimatedValue();
//            mDragView5.setTranslationX(value);
//            mDragView5.requestLayout();
//          }
//        });
//        valueAnimator.start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(dragView5, "translationX", 300);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
      }
    });
  }
}
