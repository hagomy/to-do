package com.pickth.haeun.todo.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.utils.StringUtil
import kotlinx.android.synthetic.main.activity_add.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class AddActivity : AppCompatActivity() {

    private val TAG = "AddActivity"
    lateinit var item: TodoItem
    var mdate = StringUtil.getCurrentDay()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btn_add_cancel.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        btn_add_ok.setOnClickListener {
            if (et_add_title.text.isBlank()) {
                toast(getString(R.string.is_title))
                return@setOnClickListener
            }

            item = TodoItem(et_add_title.text.toString(), et_add_more.text.toString(), mdate)

            var intent = Intent()
            intent.putExtra("title", item.title)
            intent.putExtra("more", item.more)
            intent.putExtra("date", item.date)
            setResult(10, intent)

            finish()
        }

        cv_add_date.setOnDateChangeListener { p0, year, month, day ->
            mdate = "${year}:${month + 1}:${day}"
            cv_add_date.date = SimpleDateFormat("yyyy:MM:dd")
                    .parse("${year}:${month + 1}:${day}")
                    .time
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        super.onBackPressed()
    }
}