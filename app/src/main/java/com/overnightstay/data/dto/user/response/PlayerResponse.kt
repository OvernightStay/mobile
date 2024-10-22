package com.overnightstay.data.dto.user.response

import com.overnightstay.data.dto.player.PlayerDtoOut
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("player") val player: PlayerDtoOut? = PlayerDtoOut(),
 )

