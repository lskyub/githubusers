package com.example.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.ApiService
import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    @ApplicationContext
    val applicationContext: Context,
    private val api: ApiService
) : UsersRepository {
    override suspend fun search(rq: User.RQ): Response<User.RS> {
        return api.users(rq.value)
    }
}