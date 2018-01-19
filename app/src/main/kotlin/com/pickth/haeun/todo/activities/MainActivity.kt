package com.pickth.haeun.todo.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.adapters.TodoAdapter
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.utils.TodoManager

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = TodoAdapter(this)
        rv_main_todo.adapter = mAdapter
        rv_main_todo.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        for(todo in TodoManager.getTodos(this)) {
            mAdapter.addItem(todo)
        }
        mAdapter.notifyDataSetChanged()


        btn_main_new.setOnClickListener {
            startActivityForResult(Intent(applicationContext, AddActivity::class.java),1)
            //startActivity<AddActivity>()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val title = data.getStringExtra("title")
                val more = data.getStringExtra("more")
                val date = data.getStringExtra("date")
                val item = TodoItem(title, more, date)

                mAdapter.addItem(item)
                mAdapter.notifyDataSetChanged()

                TodoManager.addTodo(this, item)
            }
        }
    }
}
