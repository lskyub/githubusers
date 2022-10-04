package com.example.data.api

import com.example.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun users(
        @Query("p") value: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 100
    ): Response<Array<User.RS>>
}