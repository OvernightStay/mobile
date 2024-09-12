package com.overnightstay.data.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("login") val login: String,
    @SerialName("password") val password: String,
    @SerialName("email") val email: String = "",
    @SerialName("phone") val phone: String,
    @SerialName("first_name") val first_name: String,
    @SerialName("last_name") val last_name: String,
)
