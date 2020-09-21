package com.leo.copytoutiao.utils.popup;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;


/**
 * Created by MQ on 2017/5/2.
 */

public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    public interface PopItemListener {
        void getChildView(View view);
    }

    private CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        setBackgroundAlpha((Activity) controller.context, 1f);
    }

    //显示在控件上方
    public void showUpView(View view) {
        this.showAsDropDown(view, -(this.getWidth() - view.getMeasuredWidth()) / 2, -(this.getHeight() + view.getMeasuredHeight()));
    }

    //显示控件上方，并带背景阴影
    public void showUpView(View view, float bgAlpha) {
        this.showAsDropDown(view, -(this.getWidth() - view.getMeasuredWidth()) / 2, -(this.getHeight() + view.getMeasuredHeight()));
        setBackgroundAlpha((Activity) controller.context, bgAlpha);
    }


    public void showBottom(View view, float bgAlpha) {
        this.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        setBackgroundAlpha((Activity) controller.context, bgAlpha);
    }

    //显示控件下方
    public void showDownView(View view) {
        this.showAsDropDown(view, -(this.getWidth() - view.getMeasuredWidth()) / 2, 0);
    }

    //显示控件下方，并带背景阴影
    public void showDownView(View view, float bgAlpha) {
        this.showAsDropDown(view, -(this.getWidth() - view.getMeasuredWidth()) / 2, 0);
        setBackgroundAlpha((Activity) controller.context, bgAlpha);
    }


    //显示控件左方
    public void showLeftView(View view) {
        this.showAsDropDown(view, -view.getMeasuredWidth(), -(this.getHeight() + view.getMeasuredHeight()) / 2);
    }

    //显示控件左方，并带背景阴影
    public void showLeftView(View view, float bgAlpha) {
        this.showAsDropDown(view, -view.getMeasuredWidth(), -(this.getHeight() + view.getMeasuredHeight()) / 2);
        setBackgroundAlpha((Activity) controller.context, bgAlpha);
    }

    //显示控件右方
    public void showRightView(View view) {
        this.showAsDropDown(view, view.getMeasuredWidth(), -(this.getHeight() + view.getMeasuredHeight()) / 2);
    }

    //显示控件右方，并带背景阴影
    public void showRightView(View view, float bgAlpha) {
        this.showAsDropDown(view, view.getMeasuredWidth(), -(this.getHeight() + view.getMeasuredHeight()) / 2);
        setBackgroundAlpha((Activity) controller.context, bgAlpha);
    }

    public void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }


    public static class Builder {
        private final PopupController.PopupParams params;
        private PopItemListener listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         * @return Builder
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(PopItemListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }


        /**
         * 是否可点击Outside消失
         *
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         *
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }


        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView);
            }
            measureWidthAndHeight(popupWindow.controller.mPopupView);
            return popupWindow;
        }
    }

    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    public void showAsDropDownLeo(View anchor, int xoff, int yoff,float alpha) {
        this.showAsDropDown(anchor, xoff, yoff);
        setBackgroundAlpha((Activity) controller.context, alpha);
    }
}
