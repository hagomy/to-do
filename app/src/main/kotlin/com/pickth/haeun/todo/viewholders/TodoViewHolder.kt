package com.pickth.haeun.todo.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.listeners.OnTodoListener
import com.pickth.haeun.todo.utils.StringUtil
import com.pickth.haeun.todo.utils.TodoManager
import kotlinx.android.synthetic.main.item_todo.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.selector
import org.jetbrains.anko.yesButton

/**
 * Created by HaEun on 2018-01-18.
 */
class TodoViewHolder(view: View, val todoListener: OnTodoListener) : RecyclerView.ViewHolder(view) {
    fun onBind(item: TodoItem, position: Int) {
//        itemView.cb_item_is_checked

        with(itemView) {
            if (item.more.isBlank()) {
                tv_item_more.visibility = View.GONE
            }

            tv_item_title.text = item.title
            tv_item_more.text = item.more
            tv_item_date.text = StringUtil.formatDayToString(item.date)
            cb_item_is_checked.isChecked = item.isChecked

            cb_item_is_checked.setOnCheckedChangeListener { p0, p1 ->
                if (p1) {
                    // isChecked
                    item.isChecked = true
                    TodoManager.notifyDataSetChanged(context)
                } else {
                    item.isChecked = false
                    TodoManager.notifyDataSetChanged(context)
                }
            }

            setOnClickListener {

            }

            setOnLongClickListener {
                val longClickItem = listOf("삭제", "취소")
                context.selector("${item.title}", longClickItem, { _,
                                                                   i ->
                    when (i) {
                        0 -> context.alert(context.getString(R.string.check_delete)) {
                            yesButton { todoListener.onDelete(position) }
                            noButton { }
                        }.show()
                        1 -> {
                        }
                    }
                })
                true
            }
        }

    }
}