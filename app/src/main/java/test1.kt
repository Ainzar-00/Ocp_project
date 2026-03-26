package com.ocp.evalformation

import androidx.room.Room
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.repository.MainRepository
import com.ocp.evalformation.utils.dateHelper.getDateAppreciation


object testr{
    suspend fun getForms(){

    }
}

fun main(){

    println(getDateAppreciation(45569.0))

}