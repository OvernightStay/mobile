package com.overnightstay.data.api

import com.overnightstay.data.dto.user.request.LoginRequest
import com.overnightstay.data.dto.user.request.PersRequest
import com.overnightstay.data.dto.user.request.RegisterRequest
import com.overnightstay.data.dto.user.response.LoginResponse
import com.overnightstay.data.dto.user.response.PlayerResponse
import com.overnightstay.data.dto.user.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {
    @POST("/register/")
    suspend fun reg(@Body registerRequest: RegisterRequest): Result<RegisterResponse>

    @POST("/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Result<LoginResponse>

    @GET("/player/")
    suspend fun getPlayer(
        @Header("Authorization") token: String,
    ): Result<PlayerResponse>

    @PUT("/player/")
    suspend fun updatePlayer(
        @Header("Authorization") token: String,
        @Body persRequest: PersRequest
    ): Result<PlayerResponse>

}
