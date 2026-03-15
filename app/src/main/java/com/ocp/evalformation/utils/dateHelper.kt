package com.ocp.evalformation.utils

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object dateHelper {

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

}