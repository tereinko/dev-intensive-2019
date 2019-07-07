package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun TimeUnits.plural(value: Int): String {
    return  ""
}

fun Date.humanizeDiff(date: Date = Date()): String {

    val isFutureDetected: Boolean = this > date
    val diffSeconds: Long = abs(date.time - this.time) / 1000
    val diffMinutes: Long = diffSeconds / 60
    val diffHours: Long = diffSeconds / 3600
    val diffDays: Long = diffSeconds / 86400

    //println ("date = ${date.time}, this = ${this.time}")

    fun processTenses(sourceString: String): String {
        if (isFutureDetected)
            return "через $sourceString"
        else
            return "$sourceString назад"
    }

    fun getEnding(value: Long, form1: String, form2: String, form5: String): String {
        val ending10 = value % 10
        val ending100 = value % 100

        if (ending100 > 10 && ending100 < 20)
            return form5
        else if (ending10 > 1 && ending10 < 5)
            return form2
        else if (ending10 == 1L)
            return form1
        else
            return  form5
    }

    if (diffSeconds <= 1)
        return "только что"
    else if (diffSeconds <= 45)
        return processTenses("несколько секунд")
    else if (diffSeconds <= 75)
        return processTenses("минуту")
    else if (diffMinutes <= 45)
        return processTenses(
            "$diffMinutes ${getEnding(diffMinutes, "минуту", "минуты", "минут")}")
    else if (diffMinutes <= 75)
        return processTenses("час")
    else if (diffHours <= 22)
        return processTenses(
            "$diffHours ${getEnding(diffHours, "час", "часа", "часов")}")
    else if (diffHours <= 26)
        return processTenses("день")
    else if (diffDays <= 360)
        return processTenses(
            "$diffDays ${getEnding(diffDays, "день", "дня", "дней")}")
    else if (isFutureDetected)
        return "более чем через год"
    else
        return "более года назад"

}


enum class TimeUnits { SECOND, MINUTE, HOUR, DAY }

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw IllegalStateException("illegal unit somehow: probably TimeUnits enum was extended")
    }

    this.time = time

    return this

}