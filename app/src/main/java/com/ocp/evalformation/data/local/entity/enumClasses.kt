package com.ocp.evalformation.data.local.entity

enum class InvitationStatus {
    NON_EXPEDIEE,   // not sent yet
    EN_ATTENTE,     // sent, waiting for reply
    REPONDUE        // manager submitted the form
}

enum class UserRole {
    RH, ADMIN
}
