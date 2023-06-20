package com.example.wechat_padc.utils

import java.text.SimpleDateFormat
import java.util.*

fun convertMillisTo12HourFormat(millis: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = millis

    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return dateFormat.format(calendar.time)
}


    val monthInMapping = hashMapOf(
        "January" to 1,
        "February" to 2,
        "March" to 3,
        "April" to 4,
        "May" to 5,
        "June" to 6,
        "July" to 7,
        "August" to 8,
        "September" to 9,
        "October" to 10,
        "November" to 11,
        "December" to 12
    )

