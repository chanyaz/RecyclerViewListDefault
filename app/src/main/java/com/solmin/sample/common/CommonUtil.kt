package com.solmin.sample.common

import android.content.Context

/**
 * Created by h_sol on 2018/02/28.
 */
object CommonUtil {
    /**
     * dpをpxに置換
     *
     * @param context
     * @param dp
     * @return
     */
    fun convertDpToPx(context: Context, dp: Float): Float {
        val density = context.resources.displayMetrics.density
        return dp * density + 0.5f
    }
}