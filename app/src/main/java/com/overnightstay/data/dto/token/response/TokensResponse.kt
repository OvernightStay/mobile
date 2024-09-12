package com.overnightstay.data.dto.token.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    @SerialName("refresh") val refresh: String? = null,
    @SerialName("access") val access: String? = null,
)