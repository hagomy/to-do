package com.pickth.haeun.todo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.listeners.OnTodoListener
import com.pickth.haeun.todo.utils.TodoManager
import com.pickth.haeun.todo.viewholders.TodoViewHolder

/**
 * Created by HaEun on 2018-01-18.
 */
class TodoAdapter(val context: Context): RecyclerView.Adapter<TodoViewHolder>(), OnTodoListener {
    override fun onDelete(position: Int) {
        deleteItem(position)
    }

    private var mItems = ArrayList<TodoItem>()

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(mItems[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView, this)
    }

    override fun getItemCount(): Int = mItems.size

    fun addItem(item: TodoItem) {
        mItems.add(item)
    }

    fun deleteItem(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
        TodoManager.removeTodo(context, position)
    }
}