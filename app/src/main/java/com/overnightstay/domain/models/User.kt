package com.overnightstay.domain.models

data class User(
    val login: String? = null,
    val password: String? = null,
    val userName: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val gender: String? = null,
    val trainingCheck: Boolean = false,
)