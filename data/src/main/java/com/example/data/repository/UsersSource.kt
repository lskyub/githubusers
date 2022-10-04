package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.ApiService
import com.example.domain.Constants
import com.example.domain.model.User

class UsersSource(private val api: ApiService, private val rq: User.RQ) :
    PagingSource<Int, User.RS>() {
    override fun getRefreshKey(state: PagingState<Int, User.RS>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User.RS> {
        return try {
            val page = params.key ?: 1
            val results = api.users(rq.value, page, Constants.DEFAULT_LIMIT).body()?.toMutableList()
            results?.run {
                val nextPage = if (count() == Constants.DEFAULT_LIMIT) page + 1 else null
                LoadResult.Page(data = this, nextKey = nextPage, prevKey = null)
            } ?: LoadResult.Error(NullPointerException())
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}