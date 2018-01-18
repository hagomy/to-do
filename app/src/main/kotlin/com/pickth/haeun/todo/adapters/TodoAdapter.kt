package com.pickth.haeun.todo.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.viewholders.TodoViewHolder

/**
 * Created by HaEun on 2018-01-18.
 */
class TodoAdapter:RecyclerView.Adapter<TodoViewHolder>() {
    private var mItems = ArrayList<TodoItem>()


    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(mItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun getItemCount(): Int = mItems.size

    fun addItem(item: TodoItem) {
        mItems.add(item)
    }
}