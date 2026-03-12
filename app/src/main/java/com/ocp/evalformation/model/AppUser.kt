package com.ocp.evalformation.model

enum class UserRole { RH, ADMIN }

data class AppUser(
    val uid: String,
    val email: String,
    val nom: String,
    val matricule: String,
    val role: UserRole,
    val entite: String = ""
)

