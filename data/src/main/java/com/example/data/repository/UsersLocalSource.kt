package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.db.UserDao
import com.example.domain.Constants
import com.example.domain.model.User

class UsersLocalSource(private val userDao: UserDao, private val rq: User.RQ) :
    PagingSource<Int, User.Item>() {
    override fun getRefreshKey(state: PagingState<Int, User.Item>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User.Item> {
        return try {
            val page = params.key ?: 1
            val results = userDao.getAllUsers()
            results.toMutableList().run {
                this.sortBy { item -> item.login }
                val nextPage = if (count() == Constants.DEFAULT_LIMIT) page + 1 else null
                LoadResult.Page(data = this, nextKey = nextPage, prevKey = null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}