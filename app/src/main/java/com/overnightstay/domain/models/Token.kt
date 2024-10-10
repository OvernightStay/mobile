package com.overnightstay.domain.models

data class Token(
    val refresh : String? = null,
    val access  : String? = null
)
