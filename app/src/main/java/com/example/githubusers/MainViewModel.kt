package com.example.githubusers

import android.view.View
import androidx.lifecycle.MutableLiveData
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
class MainViewModel @Inject constructor(
    private val useCase: UsersUseCase
) : ViewModel(), View.OnClickListener {

    val searchValue = MutableLiveData("")

    val moveValue = MutableLiveData(0)

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_search) {
            viewModelScope.launch {
                searchValue.value?.also { value ->
                    useCase.fetchList(User.RQ(value)).collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        } else {
            moveValue.value = v?.id
        }
    }

    val adapter = UserAdapter()

    fun setAdapter(listener: OnItemClickListener) {
        adapter.listener = listener
    }

    fun addUser(item: User.Item) {
        viewModelScope.launch {
            useCase.addUser(item)
        }
    }

    fun removeUser(item: User.Item) {
        viewModelScope.launch {
            useCase.removeUser(item)
        }
    }
}