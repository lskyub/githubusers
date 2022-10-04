package com.example.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.ApiService
import com.example.data.db.UserDao
import com.example.domain.Constants
import com.example.domain.model.User

/**
 * aac paging을 사용하는 클래스
 * api 에서 리스트 정보를 가져오기 위해 사용
 */
class UsersSource(
    private val api: ApiService,
    private val userDao: UserDao,
    private val rq: User.RQ
) :
    PagingSource<Int, User.Item>() {
    override fun getRefreshKey(state: PagingState<Int, User.Item>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User.Item> {
        return try {
            val page = params.key ?: 1
            val results = api.users(rq.value, page, Constants.DEFAULT_LIMIT).body()
            results?.items?.toMutableList()?.run {
                this.sortBy { item -> item.login }
                this.map { item -> item.favorites = userDao.isRowIsExist(item.id) }
                val nextPage = if (count() == Constants.DEFAULT_LIMIT) page + 1 else null
                LoadResult.Page(data = this, nextKey = nextPage, prevKey = null)
            } ?: LoadResult.Error(NullPointerException())
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}