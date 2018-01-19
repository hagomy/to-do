package com.pickth.haeun.todo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.utils.StringUtil
import kotlinx.android.synthetic.main.activity_add.*
import java.text.SimpleDateFormat

class AddActivity : AppCompatActivity() {

    private val TAG = "AddActivity"
    lateinit var item: TodoItem
    var mdate = StringUtil.getCurrentDay()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btn_add_cancel.setOnClickListener {
            finish()
        }

        btn_add_ok.setOnClickListener {
            item = TodoItem(et_add_title.text.toString(), et_add_more.text.toString(), mdate)
            finish()
        }

        cv_add_date.setOnDateChangeListener { p0, year, month, day ->
            mdate = "${year}:${month +1}:${day}"
            cv_add_date.date = SimpleDateFormat("yyyy:MM:dd")
                    .parse("${year}:${month +1}:${day}")
                    .time
        }
    }

    override fun finish() {
        var intent = Intent()
        intent.putExtra("title", item.title)
        intent.putExtra("more", item.more)
        intent.putExtra("date", item.date)
        setResult(Activity.RESULT_OK, intent)
        super.finish()
    }
}