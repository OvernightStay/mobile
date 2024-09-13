package com.overnightstay.data.dto.user.response

import com.overnightstay.data.dto.token.response.TokensResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("detail") val detail: String,
    @SerialName("tokens") val tokens : TokensResponse? = TokensResponse()
 )

