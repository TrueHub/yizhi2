package com.meihuayishu.vone.UTILS;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longyang on 2017/12/28.
 *
 */

public class NumPickerUtil {


    /**
     * 调整FrameLayout大小
     *
     * @param tp
     */
    public static void resizePikcer(FrameLayout tp , Context context) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np , context);
        }
    }

    /*
     * 调整numberpicker大小
     */
    private static void resizeNumberPicker(NumberPicker np, Context context) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(PhoneUtil.dip2px(context, 45),
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(PhoneUtil.dip2px(context, 5), 0, PhoneUtil.dip2px(context, 5), 0);
        np.setLayoutParams(params);

    }

    /**
     * 得到viewGroup里面的numberpicker组件
     *
     * @param viewGroup
     * @return
     */
    private static List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }
}
