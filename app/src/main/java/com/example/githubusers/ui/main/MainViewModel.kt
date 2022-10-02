package com.example.githubusers.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UsersUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            useCase(User.RQ("sklim0221")).body()?.let {
                println(it.login)
            }
        }
    }
}