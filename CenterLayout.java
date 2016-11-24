package com.nosorogstudio.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.hudway.carrobot.launcher.R;

public class CenterLayout extends ViewGroup {

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);

            TypedArray values = context.getResources().obtainAttributes(attrs, R.styleable.CenterLayout);

            this.x = values.getDimensionPixelOffset(R.styleable.CenterLayout_layout_x, this.x);
            this.y = values.getDimensionPixelOffset(R.styleable.CenterLayout_layout_y, this.y);
            this.anchor = values.getInteger(R.styleable.CenterLayout_layout_anchor, this.anchor);

            values.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
            this.x = x;
            this.y = y;
        }

        public LayoutParams(int width, int height, int x, int y, int anchor) {
            super(width, height);
            this.x = x;
            this.y = y;
            this.anchor = anchor;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            if (source instanceof LayoutParams) {
                this.x = ((LayoutParams) source).x;
                this.y = ((LayoutParams) source).y;
                this.anchor = ((LayoutParams) source).anchor;
            }
        }

        public int x = 0;
        public int y = 0;
        public int anchor = Layouts.Anchor.CENTER;
    }

    public CenterLayout(Context context) {
        super(context);
    }

    public CenterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        width = resolveSize(width, widthMeasureSpec);
        height = resolveSize(height, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int centerX = (right - left) / 2;
        int centerY = (bottom -top) / 2;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            Layouts.layoutChildAt(child,
                    centerX + layoutParams.x,
                    centerY + layoutParams.y,
                    layoutParams.anchor
            );
        }
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(0, 0);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }
}
