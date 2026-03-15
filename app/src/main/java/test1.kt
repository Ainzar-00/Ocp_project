package com.ocp.evalformation

import androidx.room.Room
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.repository.MainRepository


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

object testr{
    suspend fun getForms(){

    }
}

fun main(){
    println(getDateAppreciation(45611.0))

}