package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UsersRepository {
    suspend fun search(rq: User.RQ): Response<Array<User.RS>>

    fun fetchList(rq: User.RQ): Flow<PagingData<User.RS>>
}