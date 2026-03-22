package com.ocp.evalformation.utils

import org.apache.poi.ss.usermodel.DateUtil
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Calendar

object dateHelper {

    fun getTodayExcelDate(): Double {
        val calendar = Calendar.getInstance()
        return DateUtil.getExcelDate(calendar.time)
    }

    fun getDateAppreciation(excelDate: Double): Double {
        return try {
            val date     = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(excelDate, false)
            val calendar = java.util.Calendar.getInstance().apply {
                time = date
                add(java.util.Calendar.MONTH, 1)                         // go to next month
                set(java.util.Calendar.DAY_OF_MONTH, getActualMaximum(java.util.Calendar.DAY_OF_MONTH)) // last day
            }
            org.apache.poi.ss.usermodel.DateUtil.getExcelDate(calendar.time, false)
        } catch (e: Exception) {
            0.0
        }
    }

    /**
     * Converts a date string to an Excel numeric date value.
     * Excel stores dates as number of days since 1900-01-01.
     */
    fun stringToExcelDate(dateStr: String, pattern: String = "dd/MM/yyyy"): Double? {
        return try {
            val sdf  = java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
            val date = sdf.parse(dateStr.trim()) ?: return null
            org.apache.poi.ss.usermodel.DateUtil.getExcelDate(date)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Converts an Excel numeric date (e.g. 45730.0) to a formatted date string.
     * Excel stores dates as number of days since 1900-01-01.
     */
    fun excelDateToString(numericValue: Double, pattern: String = "dd/MM/yyyy"): String {
        return try {
            val date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(numericValue)
            val sdf  = java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
            sdf.format(date)
        } catch (e: Exception) {
            ""
        }
    }

    fun currentYearExcelRange(): Pair<Int, Int> {
        val cal = Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"))
        val year = cal.get(Calendar.YEAR)

        cal.set(year, 0, 1, 0, 0, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = ((cal.timeInMillis / 86400000) + 25568).toInt()

        cal.set(year, 11, 31, 23, 59, 59)
        cal.set(Calendar.MILLISECOND, 999)
        val end = ((cal.timeInMillis / 86400000) + 25568).toInt()

        android.util.Log.d("Dashboard", "yearRange=$start to $end (year=$year)")

        return Pair(start, end)
    }

}