package com.nosorogstudio.views;

import android.view.View;

public class Layouts {
    public static class Anchor {
        public static final int CENTER_HORIZONTAL = 0x01;
        public static final int LEFT = 0x03;
        public static final int RIGHT = 0x05;

        public static final int CENTER_VERTICAL = 0x10;
        public static final int TOP = 0x30;
        public static final int BOTTOM = 0x50;
        public static final int BASELINE = 0x90;

        public static final int CENTER = 0x11;

        public static float getX(View view, int anchor) {
            switch (anchor & 0x0f) {
                case CENTER_HORIZONTAL:
                    return 0.5f;
                case LEFT:
                    return 0.0f;
                case RIGHT:
                    return 1.0f;
                default:
                    return 0.5f;
            }
        }

        public static float getY(View view, int anchor) {
            switch (anchor & 0xf0) {
                case CENTER_VERTICAL:
                    return 0.5f;
                case TOP:
                    return 0.0f;
                case BOTTOM:
                    return 1.0f;
                case BASELINE: {
                    int baseline = view.getBaseline();
                    if (baseline < 0) {
                        return 1.0f;
                    }
                    int height = view.getMeasuredHeight();
                    return (float) baseline / (float) height;
                }
                default:
                    return 0.5f;
            }
        }
    }

    public static void layoutChildAt(View child, int x, int y, int anchor) {
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();

        float anchorX = Layouts.Anchor.getX(child, anchor);
        float anchorY = Layouts.Anchor.getY(child, anchor);

        int offsetX = Math.round(childWidth * anchorX);
        int offsetY = Math.round(childHeight * anchorY);

        child.layout(
                x - offsetX,
                y - offsetY,
                x - offsetX + childWidth,
                y - offsetY + childHeight
        );
    }

    public static void layoutChildCenterAt(View child, int x, int y) {
        layoutChildAt(child, x, y, Anchor.CENTER);
    }
}
