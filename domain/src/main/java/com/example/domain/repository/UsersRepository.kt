package com.example.domain.repository

import com.example.domain.model.User
import retrofit2.Response

interface UsersRepository {
    suspend fun search(rq: User.RQ): Response<User.RS>
}