package com.overnightstay.data.dto.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersRequest(
    @SerialName("username") val username: String,
    @SerialName("gender") val gender: String,
)
