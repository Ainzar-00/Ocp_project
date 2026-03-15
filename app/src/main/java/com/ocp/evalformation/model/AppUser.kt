package com.ocp.evalformation.model

import com.ocp.evalformation.data.local.entity.UserRole


data class AppUser(
    val uid: String,
    val email: String,
    val nom: String,
    val matricule: String,
    val role: UserRole,
    val entite: String = ""
)

