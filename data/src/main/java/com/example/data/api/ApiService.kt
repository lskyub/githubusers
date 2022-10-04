package com.example.data.api

import com.example.domain.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    suspend fun users(
        @Query("q") value: String,
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 100
    ): Response<User.RS>
}