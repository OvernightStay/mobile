package com.overnightstay.data.api

import com.overnightstay.data.dto.user.request.LoginRequest
import com.overnightstay.data.dto.user.request.RegisterRequest
import com.overnightstay.data.dto.user.response.LoginResponse
import com.overnightstay.data.dto.user.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/register/")
    suspend fun reg(@Body registerRequest: RegisterRequest): Result<RegisterResponse>

    @POST("/login/")
    suspend fun login(@Body loginRequest: LoginRequest): Result<LoginResponse>

}
