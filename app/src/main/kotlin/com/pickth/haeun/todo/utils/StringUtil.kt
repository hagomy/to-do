package com.pickth.haeun.todo.utils

import android.util.Log
import android.util.LogPrinter
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.LogManager
import java.util.logging.LogRecord

/**
 * Created by HaEun on 2018-01-18.
 */
object StringUtil {
    private val TAG = "StringUtil"
    fun getCurrentDay(): String = SimpleDateFormat("yyyy:MM:dd")
            .format(Date(System.currentTimeMillis()))

    fun formatDayToString(day: String): String {
        var result = (System.currentTimeMillis() - SimpleDateFormat("yyyy:MM:dd")
                .parse(day)
                .time) / 1000

        Log.i(TAG, "${result}")

        if (result < 0) {
            result -=86400
        }

        // 초
        result /= 60
        // 분
        result /= 60
        // 시
        result /= 24

        result *= -1

//        return result.toString()
        if(result.toInt() == 0) {
            return "오늘"
        }
        if (result < 0) {
            return "${-result}일 지남"
        }
        return "${result}일 남음"
    }

}