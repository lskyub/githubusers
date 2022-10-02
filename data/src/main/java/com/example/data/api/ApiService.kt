package com.example.data.api

import com.example.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{value}")
    suspend fun users(
        @Path("value") value: String
    ): Response<User.RS>
}