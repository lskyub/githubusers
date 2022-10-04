package com.example.githubusers.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.User
import com.example.domain.usecase.UsersUseCase
import com.example.githubusers.R
import com.example.githubusers.adapter.OnItemClickListener
import com.example.githubusers.adapter.UserAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UsersUseCase
) : ViewModel(), View.OnClickListener {
    val searchValue = MutableLiveData("")

    val adapter = UserAdapter()

    fun setAdapter(listener: OnItemClickListener) {
        adapter.listener = listener
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_search) {
            viewModelScope.launch {
                searchValue.value?.also { value ->
                    useCase.fetchList(User.RQ(value)).collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }
    }

}