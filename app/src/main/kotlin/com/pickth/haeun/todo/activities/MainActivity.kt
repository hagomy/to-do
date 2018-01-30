package com.pickth.haeun.todo.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.pickth.haeun.todo.R
import com.pickth.haeun.todo.adapters.TodoAdapter
import com.pickth.haeun.todo.items.TodoItem
import com.pickth.haeun.todo.utils.DividerItemDecoration
import com.pickth.haeun.todo.utils.TodoManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_view.*

class MainActivity : AppCompatActivity() {
    lateinit var mAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // actionbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        title = getString(R.string.app_name)

        val mDrawerToggle = ActionBarDrawerToggle(this,
                dl_main,
                toolbar,
                R.string.nav_open,
                R.string.nav_close)
        dl_main.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        mAdapter = TodoAdapter(this)
        rv_main_todo.run {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL, 0, true))
        }

        for (todo in TodoManager.getTodos(this)) {
            mAdapter.addItem(todo)
        }
        mAdapter.notifyDataSetChanged()


        btn_main_new.setOnClickListener {
            startActivityForResult(Intent(applicationContext, AddActivity::class.java), 1)
            //startActivity<AddActivity>()
        }

        ll_nav.setOnClickListener {

        }

//        useAd()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == 10) {
                if (data != null) {
                    val title = data.getStringExtra("title")
                    val more = data.getStringExtra("more")
                    val date = data.getStringExtra("date")
                    val check = false

                    val item = TodoItem(title, more, date, check)

                    mAdapter.addItem(item)
                    mAdapter.notifyDataSetChanged()

                    TodoManager.addTodo(this, item)
                }
            }
        }
    }

//    fun useAd() {
//        val ADMOB_APP_ID = getString(R.string.admob_app_id)
//        val ADMOB_UNIT_ID = getString(R.string.admob_unit_id)
//        MobileAds.initialize(this, ADMOB_APP_ID)
//
//        val adRequest = AdRequest.Builder()
//                .build()
//
//        // Start loading the ad in the background.
//        ad_view.loadAd(adRequest)
//    }

    // Called when leaving the activity
    public override fun onPause() {
//        ad_view.pause()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
//        ad_view.resume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
//        ad_view.destroy()
        super.onDestroy()
    }
}
