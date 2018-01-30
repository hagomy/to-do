package com.pickth.haeun.todo.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.extensions.convertDpToPixel

/**
 * Created by HaEun on 2018-01-30.
 */
class DividerItemDecoration(context: Context, val orientation: Int, var spacing: Int, val isDivider: Boolean) : RecyclerView.ItemDecoration() {
    init {
        spacing = context.convertDpToPixel(spacing)
    }

    private val mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)

    override fun getItemOffsets(outRect: Rect, view: View?, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)

        if (orientation == LinearLayoutManager.VERTICAL) {
            outRect.bottom = spacing

            if (position == 0) outRect.top = spacing / 2
        } else {
            outRect.right = spacing

            if (position == 0) outRect.left = spacing / 2
        }
    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {

        val childCount = parent.childCount
        for (i in 0..childCount - 2) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            if (isDivider) {
                if (orientation == LinearLayoutManager.VERTICAL) {
                    val left = params.leftMargin
                    val right: Int = child.right
                    // 아이템의 바텀 위치 + 바텀 마진
                    val top = child.bottom + params.bottomMargin + (spacing / 2)
                    // top + 디바이더 고유 높이
                    val bottom = top + mDivider.intrinsicHeight

                    // 좌표값 설정
                    mDivider.setBounds(left, top, right, bottom)
                    mDivider.draw(c)
                } else {
                    val left = child.right + params.rightMargin + (spacing / 2)
                    val right = left + mDivider.intrinsicWidth
                    val top = params.topMargin
                    val bottom = child.bottom

                    // 좌표값 설정
                    mDivider.setBounds(left, top, right, bottom)
                    mDivider.draw(c)
                }
            }


        }
    }
}