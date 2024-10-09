package com.overnightstay.data.dto.player

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDtoOut(
    @SerialName("id") val id: Int? = null,
    @SerialName("login") val login: String? = null,
    @SerialName("email") val email: String? = null,
    @SerialName("phone") val phone: String? = null,
    @SerialName("first_name") val first_name: String? = null,
    @SerialName("last_name") val last_name: String? = null,
    @SerialName("gender") val gender: String? = null,
    @SerialName("trainong_check") val trainong_check: String? = null,
)