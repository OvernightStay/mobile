package com.overnightstay.domain.models

data class User(
    val login: String,
    val password: String,
    val email: String? = null,
    val phone: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
)