package com.overnightstay.domain.istorage

import com.overnightstay.domain.models.Tokens

interface ITokenStorage {
    fun get(): Tokens
    fun save(tokens: Tokens)
}
