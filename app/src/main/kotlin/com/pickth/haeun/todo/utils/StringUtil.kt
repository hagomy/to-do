package com.pickth.haeun.todo.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by HaEun on 2018-01-18.
 */
object StringUtil {
    fun getCurrentDay(): String = SimpleDateFormat("yyyy:MM:dd")
            .format(Date(System.currentTimeMillis()))

    fun formatDayToString(day: String): String {
        var result = (System.currentTimeMillis() - SimpleDateFormat("yyyy:MM:dd")
                .parse(day)
                .time) / 1000

        // 초
        result /= 60
        // 분
        result /= 60
        // 시
        result /= 24

//        return result.toString()
        if(result.toInt() == 0) {
            return "오늘"
        }
        return "${result}일 전"
    }

    fun deadline(day: Long): String {
        var result = (day - System.currentTimeMillis()) / 1000 / 60 / 60 / 24

        val date = result++
        if(date.toInt() == 0) {
            return "오늘"
        }
        return "${date.toInt()}일 전"
    }
}