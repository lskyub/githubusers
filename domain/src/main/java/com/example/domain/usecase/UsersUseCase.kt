package com.example.domain.usecase

import androidx.paging.PagingData
import com.example.domain.model.User
import com.example.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class UsersUseCase @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(rq: User.RQ): Response<User.RS> {
        return repository.search(rq)
    }

    fun fetchList(rq: User.RQ): Flow<PagingData<User.Item>> {
        return repository.fetchList(rq)
    }
}