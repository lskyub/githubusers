package com.example.githubusers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecase.UsersUseCase
import com.example.githubusers.adapter.OnItemClickListener
import com.example.githubusers.adapter.UserAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val useCase: UsersUseCase) : ViewModel() {

    val adapter = UserAdapter()

    fun setAdapter(listener: OnItemClickListener) {
        adapter.listener = listener
    }

    val search = fun(value: String) {
        viewModelScope.launch {
            useCase.fetchList(User.RQ(value)).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}