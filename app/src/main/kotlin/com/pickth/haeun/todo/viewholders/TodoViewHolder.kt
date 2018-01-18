package com.pickth.haeun.todo.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.haeun.todo.items.TodoItem
import kotlinx.android.synthetic.main.item_todo.view.*
import org.jetbrains.anko.toast

/**
 * Created by HaEun on 2018-01-18.
 */
class TodoViewHolder(view: View) :RecyclerView.ViewHolder(view) {
    fun onBind(item :TodoItem) {
//        itemView.cb_item_is_checked

        with(itemView) {
            tv_item_title.text = item.title
            tv_item_more.text = item.more
            tv_item_date.text = item.date

            cb_item_is_checked.setOnCheckedChangeListener { p0, p1 ->
                if(p1) {
                    // check
                } else{
                }
            }
        }
    }
}