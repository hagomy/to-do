package com.pickth.haeun.todo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pickth.haeun.todo.items.TodoItem

/**
 * Created by HaEun on 2018-01-18.
 */
object TodoManager {
    private var mTodos: ArrayList<TodoItem> = ArrayList()

    fun getTodos(context: Context): ArrayList<TodoItem> {
        if (mTodos.size == 0) {
            val json = context
                    .getSharedPreferences("toodos", 0)
                    .getString("todos", "")

            if (json == "") return mTodos

            val type = object : TypeToken<ArrayList<TodoItem>>() {}.type
            mTodos = Gson().fromJson<ArrayList<TodoItem>>(json, type)
        }
        return mTodos
    }

    fun addTodo(context: Context, item: TodoItem) {
        mTodos.add(item)
        notifyDataSetChanged(context)
    }

    fun notifyDataSetChanged(context: Context) {
        context.getSharedPreferences("toodos", 0)
                .edit()
                .putString("todos", Gson().toJson(mTodos).toString())
                .apply()
    }

    fun removeTodo(context: Context, position: Int) {
        mTodos.removeAt(position)
        notifyDataSetChanged(context)
    }
}