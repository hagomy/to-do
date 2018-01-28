package com.pickth.haeun.todo.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.listeners.OnTodoListener
import com.pickth.haeun.todo.utils.StringUtil
import kotlinx.android.synthetic.main.item_todo.view.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.selector
import org.jetbrains.anko.yesButton

/**
 * Created by HaEun on 2018-01-18.
 */
class TodoViewHolder(view: View, val todoListener: OnTodoListener) :RecyclerView.ViewHolder(view) {
    fun onBind(item :TodoItem, position: Int) {
//        itemView.cb_item_is_checked

        with(itemView) {
            tv_item_title.text = item.title
            tv_item_more.text = item.more
            tv_item_date.text = StringUtil.formatDayToString(item.date)

            cb_item_is_checked.setOnCheckedChangeListener { p0, p1 ->
                if(p1) {
                    // check
                    context.alert(context.getString(R.string.check_ok)) {
                        yesButton { todoListener.onDelete(position) }
                        noButton { }
                    }.show()
                } else{
                }
            }

            setOnClickListener {

            }

            setOnLongClickListener {
                val longClickItem = listOf("삭제", "취소")
                context.selector(null, longClickItem, {
                    _,
                    i -> when(i) {
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