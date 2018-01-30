package com.pickth.haeun.todo.extensions

import android.content.Context

/**
 * Created by HaEun on 2018-01-30.
 */

fun Context.convertPixelToDp(px: Float): Float = px / resources.displayMetrics.density

fun Context.convertPixelToDp(px: Int): Int = convertPixelToDp(px.toFloat()).toInt()

fun Context.convertDpToPixel(dp: Float): Float = dp * resources.displayMetrics.density

fun Context.convertDpToPixel(dp: Int): Int = convertDpToPixel(dp.toFloat()).toInt()
