package com.jiyun.da_one_eleme.modle.bezier;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by 张凯雅 on 2017/8/15.
 */

public class BezierTypeEvaluator implements TypeEvaluator<PointF> {
    private PointF mControllPoint;

    public BezierTypeEvaluator(PointF mControllPoint) {
        this.mControllPoint = mControllPoint;
    }

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        PointF pointCur = new PointF();
        pointCur.x = (1 - fraction) * (1 - fraction) * startValue.x + 2 * fraction * (1 - fraction) * mControllPoint.x + fraction * fraction * endValue.x;
        pointCur.y = (1 - fraction) * (1 - fraction) * startValue.y + 2 * fraction * (1 - fraction) * mControllPoint.y + fraction * fraction * endValue.y;
//        return pointCur;
//        PointF pointCur = new PointF();
//        pointCur.x = startValue.x + fraction * (endValue.x - startValue.x);
//        pointCur.y = startValue.y + fraction * (endValue.y - startValue.y);
        return pointCur;
    }
}
